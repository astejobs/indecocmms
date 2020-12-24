package com.aste.lsme.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;

import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.LabourRateSearchCriteria;
import com.aste.lsme.domain.Workspace;

import com.aste.lsme.service.LabourRateService;

@Controller
@RequestMapping(value = "/labourRate")
public class LabourRateController {
	
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	LabourRateService labourrateService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		Workspace w=(Workspace) session.getAttribute("workspace");
			System.out.println(w.getId());
		
		
		if (model.asMap().containsKey("error"))
			model.addAttribute(
					"org.springframework.validation.BindingResult.labourrate",
					model.asMap().get("error"));
		else {
			
			LabourRate labourrate=new LabourRate();
			model.addAttribute("labourrate",labourrate);
		
		}
				
		return "labourrate";
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("labourrate") LabourRate labourrate,Errors errors, 
			 BindingResult result, Model model,
			HttpSession session,RedirectAttributes redirectAttributes){
		
        Workspace w=(Workspace) session.getAttribute("workspace");
		labourrate.setWorkspace(w);
		
        if(result.hasErrors()){
			
        	model.addAttribute("fail","Enter fields correctly");
        	model.addAttribute("labourrate",labourrate);
		  
			return "labourrate";
		}
       if(labourrateService.persist(labourrate,w)){
			redirectAttributes.addFlashAttribute("success","LabourRate saved successfully");

			return "redirect:/labourRate/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "LabourRate already exists");
			redirectAttributes.addFlashAttribute("labourrate",labourrate);
			return "redirect:/labourRate/";
		}
		
	}
	
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable Long id,Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		
		LabourRate  labourrate=labourrateService.get(id);
		model.addAttribute("labourrate", labourrate);
		return "labourrateupdate";
		
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("labourrate")LabourRate labourrate,Errors errors,
			final RedirectAttributes redirectAttributes, BindingResult result,HttpSession session,Model model)
			
	      {
		   
		     Workspace w = (Workspace) session.getAttribute("workspace");
		  
		     labourrate.setWorkspace(w);
		     
		     
		     if(result.hasErrors()){
					model.addAttribute("labourrate",labourrate);
					model.addAttribute("fail","Enter fields Correctly");
					return "redirect:/labourRate/update/"+labourrate.getId();
				}

		     
				 if(labourrateService.update(labourrate,w)){
					redirectAttributes.addFlashAttribute("success","Labourrate updated successfully");
					return "redirect:/labourRate/update/"+labourrate.getId();

				}
				else{
					
					redirectAttributes.addFlashAttribute("fail", "Labourrate already exists");
					redirectAttributes.addFlashAttribute("labourrate",labourrate);
					return "redirect:/labourRate/update/"+labourrate.getId();
				}	
	      }
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String fireList(Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		  Workspace w = (Workspace) session.getAttribute("workspace");
		  System.out.println(w.getId());
		  
		  if (model.asMap().containsKey("error"))
				
				model.addAttribute("org.springframework.validation.BindingResult.LabourRate",model.asMap().get("error"));

			else 
			{
				LabourRateSearchCriteria labourrateSearch=new LabourRateSearchCriteria();
				labourrateSearch.setWorkspace(w);
				model.addAttribute("labourrateSearch",labourrateSearch);
				
			}
				
	    
		return "labourratesearch";
	}


@RequestMapping(value="/search",method=RequestMethod.POST)
public String searchCriteria(@ModelAttribute("labourrateSearch")LabourRateSearchCriteria labourrateSearch,Model model,
		HttpSession session,RedirectAttributes redirectAttributes)
{
	 int page=0;
	 Workspace w = (Workspace) session.getAttribute("workspace");
	session.setAttribute("laboursession", labourrateSearch);
	pagination(page, model,labourrateSearch,w);
	List<LabourRate> list=labourrateService.getSearchList(page,labourrateSearch,w);
	
	model.addAttribute("listSearch", list);
	
	return "labourratesearch";
	
}

public void pagination(int page,Model model,LabourRateSearchCriteria labourrateSearch,Workspace w)
{

	page = (page > 0) ? page : 1;
	from = ROWS*(page-1);
	records = (long)labourrateService.count(labourrateSearch,w);
	total = (int) Math.ceil((double) records / (double) ROWS);
	model.addAttribute("total", total);
	model.addAttribute("page", page);
	model.addAttribute("listSearch", labourrateService.getSearchList(from,labourrateSearch,w));

}

@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
public String paginate(@PathVariable("page") int page,Model model,HttpSession session){

	
	 Workspace w = (Workspace) session.getAttribute("workspace");
	LabourRateSearchCriteria labourrateSearch=(LabourRateSearchCriteria) session.getAttribute("laboursession");

	pagination(page, model,labourrateSearch,w);

	model.addAttribute("labourrateSearch",labourrateSearch );

	return "labourratesearch";
}


@RequestMapping(value="/delete/{id}")
public String deleteAcmv(@PathVariable("id")Long id,final RedirectAttributes redirectAttributes)
	{

		LabourRate labourrate=	labourrateService.get(id);
		if(labourrate.equals("null"))
				{
	
					redirectAttributes.addFlashAttribute("fail","Not Deleted......");
					return "redirect:/labourRate/search";
				}
		else
				{
					labourrateService.delete(id);
					redirectAttributes.addFlashAttribute("success","Deleted......");
					return "redirect:/labourRate/search";
				}

	}

@RequestMapping(value="getlabourrate", method=RequestMethod.GET)
public @ResponseBody List<LabourRate> getlabourrate(@RequestParam("id") Long[] id)
{
	return labourrateService.get(id);
}

@RequestMapping(value="getworkspacelabourrate", method=RequestMethod.GET)
public @ResponseBody List<LabourRate> getlabourrates(HttpSession session)
{
	 Workspace w = (Workspace) session.getAttribute("workspace");
	 return labourrateService.getWorkspaceLabourrate(w);
}

	
	
}
