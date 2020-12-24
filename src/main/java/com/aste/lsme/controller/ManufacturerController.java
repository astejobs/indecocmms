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
import com.aste.lsme.domain.Manufacturer;
import com.aste.lsme.service.ManufacturerService;

@Controller
@RequestMapping(value="/manufacturer")
public class ManufacturerController {

	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	ManufacturerService manufacturerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getManufacturer(Model model)
	{
		if(!model.asMap().containsKey("manufacturer"))
		{
			model.addAttribute("manufacturer", new Manufacturer());
		}
			if(model.asMap().containsKey("result"))
			{
				model.addAttribute("org.springframework.validation.BindingResult.manufacturer",model.asMap().get("result"));
			}
	
			int page = 1;
			pagination(page, model);
				
		
		return "manufacturer";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveManufacturer(@Valid @ModelAttribute("manufacturer") Manufacturer manufacturer,BindingResult result,
			RedirectAttributes redirectAttributes)
	{
		
		if(result.hasErrors())
		{
			redirectAttributes.addFlashAttribute("vendor",manufacturer);
			 redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/manufacturer";
		}
			else if(manufacturerService.save(manufacturer))
			{
			redirectAttributes.addFlashAttribute("success","manufacturer saved successfully!");
			return "redirect:/manufacturer";
			}
				else 
				{
					redirectAttributes.addFlashAttribute("fail","manufacturer already exists!");
					return "redirect:/manufacturer";
				}
		
	}
	

	
	@RequestMapping(value="/{id}/{page}",method=RequestMethod.GET)
	public String edit(@PathVariable("id") int id,@PathVariable("page") int page, Model model)
	{
		if(!model.asMap().containsKey("manufacturer"))
		{
			model.addAttribute("manufacturer", manufacturerService.getManufacturer(id));
		}
		
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.manufacturer",
					model.asMap().get("result"));
		}
		model.addAttribute("edit", true);
		if(model.asMap().containsKey("page")){
			page = (Integer) model.asMap().get("page");
			pagination(page, model);
		}
		else{
			pagination(page, model);
		}
		
			    
		return "manufacturer";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateVendor(@Valid @ModelAttribute ("manufacturer") Manufacturer manufacturer,BindingResult result,
			RedirectAttributes redirectAttributes,HttpServletRequest req)
	{
		int page=Integer.parseInt(req.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		
		if(result.hasErrors())
		{
			redirectAttributes.addFlashAttribute("manufacturer",manufacturer);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/manufacturer/"+manufacturer.getId()+"/"+page;
		}
			else if(manufacturerService.update(manufacturer))
				{
				redirectAttributes.addFlashAttribute("success","Manufacturer updated successfully!");
				return "redirect:/manufacturer/"+manufacturer.getId()+"/"+page;
				}
					
					else
					{
						redirectAttributes.addFlashAttribute("fail","vendor updation failed!!");
						redirectAttributes.addFlashAttribute("manufacturer",manufacturer);
						return "redirect:/manufacturer/"+manufacturer.getId()+"/"+page;
					}
					
		
	}
	
	
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request ,RedirectAttributes redirectAttributes){
		
		String[] id = request.getParameterValues("id");
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				try {
					manufacturerService.delete(Long.valueOf(id[i]));
					redirectAttributes.addFlashAttribute("success","deleted successfully!");
					} catch (Exception e) {
					
					redirectAttributes.addFlashAttribute("fail","Some Manufacturers cannot be  deleted ");
				}
			}
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Select Manufacturer(s) to delete");
		} 
		return "redirect:/manufacturer";	
	}
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
	
		pagination(page, model);
		model.addAttribute("manufacturer", new Manufacturer());
		return "vendor";
	}
	
	public void pagination(int page,Model model){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) manufacturerService.getCount();
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("manufacturerList", manufacturerService.getPaginated(from));

	}
}
