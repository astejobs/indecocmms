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
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.CostCenterService;

@Controller
@RequestMapping(value="/costCenter")
public class CostCenterController {
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	@Autowired
	CostCenterService costCenterService;
	
	@RequestMapping(method = RequestMethod.GET )
	public String home( Model model,HttpSession session) 
	{
		System.out.println("output");
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("costCenter")){
			   model.addAttribute("costCenter", new CostCenter());
			} 

			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.costCenter",
						model.asMap().get("result"));
		
		
			}
	
			int page = 1;
			pagination(page, model,w);
		
		return "costCenter";
		
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAssettype(@Valid @ModelAttribute("costCenter") CostCenter costCenter ,BindingResult result, Model model,RedirectAttributes redirectAttributes,HttpSession session)
	{
		Workspace w = (Workspace) session.getAttribute("workspace");
		costCenter.setWorkspace(w);
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute(" costCenter", costCenter);
		    redirectAttributes.addFlashAttribute("result",result);
			return  "redirect:/costCenter";
		}
		
		else  if(costCenterService.addCostCenter(costCenter,w))
				{
					redirectAttributes.addFlashAttribute("success",  "CostCenter Successfully Added");
				    return "redirect:/costCenter";
					
				}
				else
				
				{	
					redirectAttributes.addFlashAttribute("fail",  "CostCenter already exists");
				    redirectAttributes.addFlashAttribute("result",result);
				    return "redirect:/costCenter";
				}
	
	}
	@RequestMapping(value="/edit/{id}/{page}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");

		if(!model.asMap().containsKey("costCenter")){
			   model.addAttribute("costCenter", costCenterService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.costCenter",
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
		return "costCenter";
	}
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("costCenter") CostCenter costCenter ,BindingResult result,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) 
			{
		Workspace w = (Workspace) session.getAttribute("workspace");
		costCenter.setWorkspace(w);
				
				 int page = Integer.parseInt(request.getParameter("p"));
					redirectAttributes.addFlashAttribute("page", page);
					if(result.hasErrors()){
						redirectAttributes.addFlashAttribute("costCenter", costCenter);
						redirectAttributes.addFlashAttribute("result",result);
						return "redirect:/costCenter/edit/"+costCenter.getId()+"/"+page;
					}
					else if(costCenterService.update(costCenter,w)){
						redirectAttributes.addFlashAttribute("success"," CostCenter updated successfully");
						return "redirect:/costCenter";
					}
					else{
						redirectAttributes.addFlashAttribute("fail", " CostCenter already exists");
						redirectAttributes.addFlashAttribute("costCenter", costCenter);
						return "redirect:/costCenter/edit/"+costCenter.getId()+"/"+page;
					}	
				 
			}
	@RequestMapping(value = "/delete" ,method = RequestMethod.POST)
	public String delete( HttpServletRequest request ,RedirectAttributes redirectAttributes) 
			{
		String[] id = request.getParameterValues("id");
		if(id != null){
			for (int i = 0; i < id.length; i++){
				try {
			
				  costCenterService.delete(Long.valueOf(id[i]));
			}catch (Exception e) {
				redirectAttributes.addFlashAttribute("fail","Some  items cannot be  deleted ");
			}
		}
		}
		
		else{
			redirectAttributes.addFlashAttribute("fail", "Select items to delete");
		} 
				return "redirect:/costCenter";	
			
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("costCenter", new CostCenter());
		return "costCenter";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) costCenterService.getCostCenterCount(w);
		
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("costCenterList", costCenterService.getCostCenterPaginated(from,w));
		System.out.println(costCenterService.getCostCenterCount(w)+"**********"+ costCenterService.getCostCenterPaginated(from,w).size());
		
	}
}



	



	
	
		
	
	
	
	
	





