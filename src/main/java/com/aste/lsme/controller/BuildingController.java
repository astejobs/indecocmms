package com.aste.lsme.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;

@Controller
@RequestMapping("/building")
public class BuildingController {

	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	BuildingService buildingService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("building")){
		   model.addAttribute("building", new Building());
		} 

		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.building",
					model.asMap().get("result"));
		}
		int page = 1;
		pagination(page, model,w);
		return "building";
		
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("building") Building building,BindingResult result,
			RedirectAttributes redirectAttributes,HttpSession session) {

		Workspace w = (Workspace) session.getAttribute("workspace");
		building.setWorkspace(w);
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("building",building);
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/building";
		}
		else if(buildingService.persist(building,w)){
			redirectAttributes.addFlashAttribute("success","Building saved successfully");
			return "redirect:/building";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Building already exists");
			redirectAttributes.addFlashAttribute("building",building);
			return "redirect:/building";
		}
		
		
	}
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		if(!model.asMap().containsKey("building")){
		  model.addAttribute("building", buildingService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.building",
					model.asMap().get("result"));
		}
		model.addAttribute("edit", true);
		if(model.asMap().containsKey("page")){
			page = (Integer) model.asMap().get("page");
			pagination(page, model,w);
		}
		else{
			pagination(page, model,w);
		}
		return "building";
	}
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("building") Building building,BindingResult result,
							RedirectAttributes redirectAttributes,HttpServletRequest request,
							HttpSession session) {

		Workspace w = (Workspace) session.getAttribute("workspace");
		building.setWorkspace(w);
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("building",building);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/building/edit/"+building.getId()+"/"+page;
		}
		else if(buildingService.update(building,w)){
			redirectAttributes.addFlashAttribute("success","Building updated successfully");
			return "redirect:/building/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Building already exists");
			redirectAttributes.addFlashAttribute("building",building);
			return "redirect:/building/edit/"+building.getId()+"/"+page;
		}	
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request ,RedirectAttributes redirectAttributes){
		
		String[] id = request.getParameterValues("id");
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				try {
					buildingService.delete(Long.valueOf(id[i]));
				} catch (Exception e) {
					
					redirectAttributes.addFlashAttribute("fail","Some buildings cannot be  deleted ");
				}
			}
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Select building(s) to delete");
		} 
		return "redirect:/building";	
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("building", new Building());
		return "building";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) buildingService.getBuildingCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("buildingList", buildingService.getBuildingPaginated(from,w));

	}
}
