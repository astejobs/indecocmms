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

import com.aste.lsme.domain.CivilLevel3;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.CivilLevel3Service;
import com.aste.lsme.domain.Constants;


@Controller
@RequestMapping(value = "/civilLevel3") 
public class CivilLevel3Controller { 

		
	@Autowired
	CivilLevel3Service civilservice;

	@Autowired
	AssetSubtypeService subTypeService;
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String getSub(Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
	
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		System.out.println(w+"  workspace");
		if(!model.asMap().containsKey("level3")){
			model.addAttribute("level3", new CivilLevel3());
		}

		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.level3",
					model.asMap().get("result"));
		}
		
        int page = 1;
		pagination(page, model,w);
		return "CivilLevel3";
		
	}
	
	
	@RequestMapping(value = "/delete/{page}/{Id}", method = RequestMethod.GET)
	public String delete(@PathVariable("page") int page,
			@PathVariable("Id") long id, Model model,
			final RedirectAttributes redirectAttributes) 
	{
		
		try {
			civilservice.remove(id);
			redirectAttributes.addFlashAttribute("success",	"Deleted Successfully");
		    } 
				catch (Exception e) 
				{
			
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("fail",	"Cannot be Deleted");
					redirectAttributes.addFlashAttribute("assetSubType",subTypeService.getAssetSubtypeList());
			
				}
		redirectAttributes.addFlashAttribute("assetSubType",subTypeService.getAssetSubtypeList());
		
		return "redirect:/civilLevel3";
	}
	
		
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEquipmentSystem(
			@Valid  CivilLevel3 c,
			final BindingResult result, Model model, HttpSession session,
			HttpServletRequest req, final RedirectAttributes redirectAttributes) throws Exception {
             
		
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		c.setWorkspace(w);
		
		int page = Integer.parseInt(req.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("level3",c);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/civilLevel3/edit/"+c.getId()+"/"+page;
		}
		
		
		
		else if(civilservice.update(c,w)){
			redirectAttributes.addFlashAttribute("success","updated successfully");
			return "redirect:/civilLevel3/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "CivilLevel3 already exists");
			redirectAttributes.addFlashAttribute("level3",c);
			return "redirect:/civilLevel3/edit/"+c.getId()+"/"+page;
		}
		
		
			}
	
	
	@RequestMapping(value="/Add", method=RequestMethod.POST)
	public String setLevel4Form(@Valid @ModelAttribute("level3") CivilLevel3 c,final BindingResult result,
			final RedirectAttributes redirectAttributes, Model model,HttpSession session)
	{
		
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		c.setWorkspace(w);
		
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("level3",c);
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/civilLevel3";
		}
		

		else if(civilservice.add(c,w))
		{
			redirectAttributes.addFlashAttribute("success","Added successfully");
			return "redirect:/civilLevel3";
		}
		   		else 
		        {
		   			redirectAttributes.addFlashAttribute("level3",c);		
		   			redirectAttributes.addFlashAttribute("fail", "Civil Level 3 already exists");
		   			return "redirect:/civilLevel3";
                }
		
	}
	
		
	
	@RequestMapping(value = "/edit/{page}/{id}", method = RequestMethod.GET)
	public String editMonitorClass(@PathVariable("page") int page,
			@PathVariable("id") long system_id, Model model,
			HttpSession session, final RedirectAttributes redirectAttributes) 
	    {
		
			
		
	       Workspace w=(Workspace)session.getAttribute("workspace");
	       
	       
	   	if(!model.asMap().containsKey("level3")){
			  model.addAttribute("level3", civilservice.find(system_id));
			}
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.level3",
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
			return "CivilLevel3";
	       
	          }
	
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session,RedirectAttributes redirectAttributes){
		
		
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("level3", new CivilLevel3());
		return "CivilLevel3";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = civilservice.count(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("level3list", civilservice.getCivilLevel3Paginated(from,w));
		model.addAttribute("assetSubType",subTypeService.getAssetSubtypeList());
		
	}
	
}
