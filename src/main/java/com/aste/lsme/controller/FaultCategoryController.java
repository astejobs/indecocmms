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
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.FaultCategoryService;
import com.aste.lsme.service.PriortyService;

@Controller
@RequestMapping("/faultCategory")
public class FaultCategoryController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	@Autowired
	FaultCategoryService faultCategoryService;
	
	@Autowired
	PriortyService   priortyService ;
	@RequestMapping(method = RequestMethod.GET )
	public String home( Model model,HttpSession session) 
	{
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("faultCategory")){
			model.addAttribute("faultCategory", new FaultCategory());
			model.addAttribute("priortyList", priortyService.getAll() );
			} 

			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.faultCategory",
						model.asMap().get("result"));
		
		
			}
			int page = 1;
			pagination(page, model,w);
		
//			model.addAttribute("priortyList", priortyService.getAll() );

		
		return "faultCategory";
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAssettype(@Valid @ModelAttribute("faultCategory") FaultCategory faultCategory,BindingResult result,
								Model model,RedirectAttributes redirectAttributes,HttpSession session)
	{
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		faultCategory.setWorkspace(w);
		if(result.hasErrors()){
			System.out.println(result.toString());
		    redirectAttributes.addFlashAttribute("faultCategory", faultCategory);
		    redirectAttributes.addFlashAttribute("result",result);
			return  "redirect:/faultCategory";
		}
	
			   // System.out.println( technician.getCompanyName()+"asssetname");
		
			    
			    else  if(faultCategoryService.addFaultCategory(faultCategory,w))
				{
					redirectAttributes.addFlashAttribute("success",  "FaultCategory Added Successfully ");
				    return "redirect:/faultCategory";
					
				}
				else
				
				{	
					redirectAttributes.addFlashAttribute("fail",  "FaultCategory already exist");
				    redirectAttributes.addFlashAttribute("faultCategory", faultCategory);
				    return "redirect:/faultCategory";
				}
	
	}
	@RequestMapping(value="/edit/{id}/{page}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("faultCategory")){
			   model.addAttribute("faultCategory", faultCategoryService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.faultCategory",
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
		model.addAttribute("priortyList", priortyService.getAll() );
		
		return "faultCategory";
	}
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("faultCategory")  FaultCategory faultCategory ,BindingResult result,Model model,RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) 
			{
		Workspace w = (Workspace) session.getAttribute("workspace");
		faultCategory.setWorkspace(w);
		
				 int page = Integer.parseInt(request.getParameter("p"));
					redirectAttributes.addFlashAttribute("page", page);
					if(result.hasErrors()){
						redirectAttributes.addFlashAttribute("faultCategory", faultCategory);
						redirectAttributes.addFlashAttribute("result",result);
						return "redirect:/faultCategory/edit/"+faultCategory.getId()+"/"+page;
					}
					else if(faultCategoryService.update(faultCategory,w)){
						redirectAttributes.addFlashAttribute("success"," FaultCategory updated successfully");
						return "redirect:/faultCategory";
					}
					else{
						redirectAttributes.addFlashAttribute("fail", " FaultCategory already exists");
						redirectAttributes.addFlashAttribute("faultCategory",faultCategory);
						return "redirect:/faultCategory/edit/"+faultCategory.getId()+"/"+page;
					}	
				 
			}
	@RequestMapping(value = "/delete" ,method = RequestMethod.POST)
	public String delete( HttpServletRequest request ,RedirectAttributes redirectAttributes) 
			{
		System.out.println("saba");
		String[] id = request.getParameterValues("id");
		if(id != null){
			for (int i = 0; i < id.length; i++)
			{
			
				try
				{
				faultCategoryService.delete(Long.valueOf(id[i]));
				}catch (Exception e) 
				{
					redirectAttributes.addFlashAttribute("fail","Some items cannot be  deleted ");
			}
		}
		}
		else
		{
			redirectAttributes.addFlashAttribute("fail", "Select items to delete");
		} 
				return "redirect:/faultCategory";	
			}
	
	


	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("faultCategory", new FaultCategory());
		return "faultCategory";
	}
	
   public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) faultCategoryService.getFaultCategoryCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		System.out.println(total+"---------------------");
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("faultCategoryList", faultCategoryService.getfaultCategoryPaginated(from,w));
	}
	

}
