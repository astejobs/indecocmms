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

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.MaintainenceGroupService;

@Controller
@RequestMapping("/maintainenceGrp")
public class MaintainanceGroupController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	MaintainenceGroupService mainService;
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,RedirectAttributes redirect,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		
		 
		if(!model.asMap().containsKey("maintainenceGrp")){
		   model.addAttribute("maintainenceGrp", new MaintainenceGroup());
		} 
	
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.maintainenceGrp",
					model.asMap().get("result"));
		}
		
		int page = 1;
		pagination(page, model,w);
		return "maintainancegroup";
		
	}
	

	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("maintainenceGrp") MaintainenceGroup maintainenceGrp,BindingResult result,
			RedirectAttributes redirectAttributes,HttpSession session) {

		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		
		maintainenceGrp.setWorkspace(w);
		
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("maintainenceGrp",maintainenceGrp);
		    redirectAttributes.addFlashAttribute("result",result);
		    System.out.println("im in result");
			return "redirect:/maintainenceGrp";
		}
		else if(mainService.persist(maintainenceGrp,w)){
			System.out.println("i m in persit");
			redirectAttributes.addFlashAttribute("success","Maintainance Group saved successfully");
			return "redirect:/maintainenceGrp";
		}
		else{
			System.out.println("i m in else already");
			redirectAttributes.addFlashAttribute("fail", "Maintainance Group already exists");
			redirectAttributes.addFlashAttribute("maintainenceGrp",maintainenceGrp);
			return "redirect:/maintainenceGrp";
		}
		
		
		
		
	}

	
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,
			RedirectAttributes redirect,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");

		
		if(!model.asMap().containsKey("maintainenceGrp")){
			model.addAttribute("status",mainService.get(id).getStatus());
		  model.addAttribute("maintainenceGrp", mainService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.maintainenceGrp",
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
		
		model.addAttribute("status",mainService.get(id).getStatus());
	
		
		return "maintainancegroup";
	}
	
	
	
	
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("maintainenceGrp") MaintainenceGroup maintainenceGrp,BindingResult result,
			RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		maintainenceGrp.setWorkspace(w);
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("maintainenceGrp",maintainenceGrp);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/maintainenceGrp/edit/"+maintainenceGrp.getId()+"/"+page;
		}
		else if(mainService.update(maintainenceGrp,w)){
			redirectAttributes.addFlashAttribute("success","Maintainance Group updated successfully");
			return "redirect:/maintainenceGrp/";
		}
		else{
			redirectAttributes.addFlashAttribute("mainGrpList", mainService.getAll());
			redirectAttributes.addFlashAttribute("fail", "Maintainance Group already exists");
			redirectAttributes.addFlashAttribute("maintainenceGrp",maintainenceGrp);
			return "redirect:/maintainenceGrp/edit/"+maintainenceGrp.getId()+"/"+page;
		}	
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request , Model model,RedirectAttributes redirectAttributes){
		
		String[] id = request.getParameterValues("id");
		if(id != null){
		for (int i = 0; i < id.length; i++) {
			try {
				redirectAttributes.addFlashAttribute("success","MaintaineneceGroup(s)  deleted ");
				mainService.delete(Long.valueOf(id[i]));
			}catch (Exception e) {
				redirectAttributes.addFlashAttribute("fail","Some MaintaineneceGroup(s) cannot be  deleted ");

			}
		}
	}
	else{
		redirectAttributes.addFlashAttribute("fail", "Select MaintaineneceGroup(s) to delete");
	} 
		return "redirect:/maintainenceGrp";	
	}
	
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,RedirectAttributes redirect,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		pagination(page, model,w);
		model.addAttribute("maintainenceGrp", new MaintainenceGroup());
		return "maintainancegroup";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) mainService.getMainGrpCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("mainGrpList", mainService.getMainGrpPaginated(from,w));
		
	}
}