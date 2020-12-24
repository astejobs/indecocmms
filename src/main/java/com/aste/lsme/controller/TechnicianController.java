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
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.TechnicianService;
@Controller
@RequestMapping(value="/technician")


public class TechnicianController 
{
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	@Autowired
	TechnicianService technicianService;
	
	@RequestMapping(method = RequestMethod.GET )
	public String home( Model model,HttpSession session) 
	{
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("technician")){
			model.addAttribute("technician", new Technician() );
			} 

			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.technician",
						model.asMap().get("result"));
		
		
			}
			int page = 1;
			pagination(page, model,w);
		
	

		
		return "technician";
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAssettype(@Valid@ModelAttribute("technician") Technician technician,BindingResult result,Model model,RedirectAttributes redirectAttributes,HttpSession session)
	{

		Workspace w = (Workspace) session.getAttribute("workspace");
		technician.setWorkspace(w);
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("technician", technician);
		    redirectAttributes.addFlashAttribute("result",result);
			return  "redirect:/technician";
		}
	
			   // System.out.println( technician.getCompanyName()+"asssetname");
		
			    
			    else  if(technicianService.addTechnician(technician,w))
				{
					redirectAttributes.addFlashAttribute("success",  "Technician Added Successfully ");
				    return "redirect:/technician";
					
				}
				else
				
				{	
					redirectAttributes.addFlashAttribute("fail",  "Technician already exist");
				    redirectAttributes.addFlashAttribute("technician", technician);
				    return "redirect:/technician";
				}
	
	}
	@RequestMapping(value="/edit/{id}/{page}", method=RequestMethod.GET)
	  
			public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
				if(!model.asMap().containsKey("technician")){
					  model.addAttribute("technician", technicianService.get(id));
				}
				if(model.asMap().containsKey("result")){
					model.addAttribute(
							"org.springframework.validation.BindingResult.technician",
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
				return "technician";
		}
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(  @Valid @ModelAttribute("technician") Technician technician,BindingResult result ,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) 
			{
		Workspace w = (Workspace) session.getAttribute("workspace");
		technician.setWorkspace(w);
				 
					int page = Integer.parseInt(request.getParameter("p"));
					redirectAttributes.addFlashAttribute("page", page);
					if(result.hasErrors()){
						redirectAttributes.addFlashAttribute("technician", technician);
						redirectAttributes.addFlashAttribute("result",result);
						return "redirect:/technician/edit/"+technician.getId()+"/"+page;
					}
					else if(technicianService.update(technician,w)){
						redirectAttributes.addFlashAttribute("success","Technician updated successfully");
						return "redirect:/technician/";
					}
					else{
						redirectAttributes.addFlashAttribute("fail", "Technician already exists");
						redirectAttributes.addFlashAttribute("technician", technician);
						return "redirect:/technician/edit/"+technician.getId()+"/"+page;
					}	
			}
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.GET)
	public String delete( @PathVariable("id") Long id ,Model model,RedirectAttributes redirectAttributes) 
			{
		  		
				technicianService.delete(id);
				try {
					technicianService.delete(id);
				model.addAttribute("technicianList", technicianService.getTechnician());
				}
				catch (Exception e) {
					redirectAttributes.addFlashAttribute("fail","Some items cannot be  deleted ");
				}
				return "redirect:/technician";	
			}
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("technician", new Technician() );
		return "technician";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) technicianService.geTechnicianCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		System.out.println(total+"---------------------");
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("technicianList", technicianService.getTechnicianPaginated(from,w));
	}


}

	



	
	
		
	
	
	
	
	


