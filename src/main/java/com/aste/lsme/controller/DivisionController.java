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

import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.DivisionService;

@Controller
@RequestMapping("/division")
public class DivisionController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	DivisionService divisionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	
		Workspace w=(Workspace) session.getAttribute("workspace");
	//	System.out.println(w);
		
		if(!model.asMap().containsKey("division")){
			   model.addAttribute("division", new Division());
			} 

	
		model.addAttribute("divisionList", divisionService.getAll());
		
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.division",
					model.asMap().get("result"));
		}
		
		
		int page = 1;
		pagination(page, model,w);
		
		return "division";
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDivision(@Valid @ModelAttribute("division") Division division,BindingResult result,Model model,
			RedirectAttributes redirectAttributes,HttpSession session)
	{
		
		Workspace w=(Workspace) session.getAttribute("workspace");
		division.setWorkspace(w);
		if(result.hasErrors()){
			
		    redirectAttributes.addFlashAttribute("division",division);
		    redirectAttributes.addFlashAttribute("result",result);
		    redirectAttributes.addFlashAttribute("fail","Please enter all fields correctly");
			return "redirect:/division";
		}
		else if(divisionService.persist(division,w)){
			redirectAttributes.addFlashAttribute("success","Division saved successfully");
			return "redirect:/division/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Division already exists");

			redirectAttributes.addFlashAttribute("division",division);
			return "redirect:/division/";
		}
		

}
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		if(!model.asMap().containsKey("division")){
			  model.addAttribute("division", divisionService.get(id));
			}
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.division",
						model.asMap().get("result"));
			}
	
		model.addAttribute("divisionList", divisionService.getAll());
		model.addAttribute("edit", true);
		
		if(model.asMap().containsKey("page")){
			page = (Integer) model.asMap().get("page");
			pagination(page, model,w);
		}
		else{
			pagination(page, model,w);
		}
		
		
		return "division";
	}
	
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("division")Division division,BindingResult result,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		division.setWorkspace(w);
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("division",division);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/division/edit/"+division.getId()+"/"+page;
		}
		else if(divisionService.update(division,w)){
			redirectAttributes.addFlashAttribute("success","division updated successfully");
			redirectAttributes.addFlashAttribute("divisionList", divisionService.getAll());
			return "redirect:/division/";
		}
		else{
			redirectAttributes.addFlashAttribute("divisionList", divisionService.getAll());
			redirectAttributes.addFlashAttribute("fail", "division already exists");
			redirectAttributes.addFlashAttribute("division",division);
			return "redirect:/division/edit/"+division.getId()+"/"+page;
		}	
	}
	
	@RequestMapping(value = "/delete" ,method = RequestMethod.POST)
	public String delete( HttpServletRequest request , Model model,RedirectAttributes redirectAttributes) {
		
		String[] id = request.getParameterValues("id");
		if(id!=null){
		for (int i = 0; i < id.length; i++) {
			divisionService.delete(Long.valueOf(id[i]));
		}
		}else{
			redirectAttributes.addFlashAttribute("fail", "Select departments to delete");
		}
		model.addAttribute("divisionList", divisionService.getAll());
		return "redirect:/division";	
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("division", new Division());
		return "division";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long)divisionService.getDivisionCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("divisionList", divisionService.getDivisionPaginated(from,w));
		
	}
	

}
