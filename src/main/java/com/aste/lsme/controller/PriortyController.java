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
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.PriortyService;

@Controller
@RequestMapping("/priorty")
public class PriortyController {
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	PriortyService priortyService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,HttpSession session) {
	
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("priorty")){
			   model.addAttribute("priorty", new Priorty());
			} 
			
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.priorty",
						model.asMap().get("result"));
			}
		
			int page = 1;
			pagination(page, model,w);
			
	        return "priorty";
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("priorty") Priorty priorty,BindingResult result,RedirectAttributes redirectAttributes,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
	
		
		priorty.setWorkspace(w);
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("priorty",priorty);
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/priorty";
		}
		else if(priortyService.persist(priorty, w)){
			redirectAttributes.addFlashAttribute("success","Priorty saved successfully");
			return "redirect:/priorty";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Priorty already exists");
			redirectAttributes.addFlashAttribute("priorty",priorty);
			return "redirect:/priorty";
		}
	
	}
	
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,
			HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		if(!model.asMap().containsKey("priorty")){
		  model.addAttribute("priorty", priortyService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.priorty",
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
		//model.addAttribute("priortyList", priortyService.getAll());
		
		return "priorty";
	}
	
	
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("priorty") Priorty priorty,BindingResult result,RedirectAttributes redirectAttributes,HttpSession session,HttpServletRequest request) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		priorty.setWorkspace(w);
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("priorty",priorty);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/priorty/edit/"+priorty.getId()+"/"+page;
		}
		else if(priortyService.update(priorty,w)){
			redirectAttributes.addFlashAttribute("success","Priorty updated successfully");
			return "redirect:/priorty/";
		}
		else{
		//	redirectAttributes.addFlashAttribute("priortyList", priortyService.getAll());
			redirectAttributes.addFlashAttribute("fail", "Priorty already exists");
			redirectAttributes.addFlashAttribute("priorty",priorty);
			return "redirect:/priorty/edit/"+priorty.getId()+"/"+page;
		}	
	}
	
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request , Model model,RedirectAttributes redirectAttributes){
		
		String[] id = request.getParameterValues("id");
		if(id != null){
		for (int i = 0; i < id.length; i++) {
			
			try {
			priortyService.delete(Long.valueOf(id[i]));
			redirectAttributes.addFlashAttribute("success","Priorty(s)  deleted ");
			} catch (Exception e) {
				
				redirectAttributes.addFlashAttribute("fail","Some Priorty(s) cannot be  deleted ");
			}
		}
		
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Select Priorty(s) to delete");
		} 
		
		return "redirect:/priorty";	
		
		
		
	}

	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		pagination(page, model,w);
		model.addAttribute("priorty", new Priorty());
		return "priorty";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) priortyService.getPriortyCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("priortyList", priortyService.getPriortyPaginated(from,w));
		
	}
}