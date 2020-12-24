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
import com.aste.lsme.domain.Vendor;
import com.aste.lsme.service.VendorService;

@Controller
@RequestMapping(value="/vendor")
public class VendorController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	VendorService vendorService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getVendor(Model model)
	{
		if(!model.asMap().containsKey("vendor")){
			   model.addAttribute("vendor", new Vendor() );
			} 

			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.vendor",
						model.asMap().get("result"));
			}
		
		int page = 1;
		pagination(page, model);
			
			
		return "vendor";
	}

	
	@RequestMapping(method=RequestMethod.POST)
	public String saveVendor(@Valid @ModelAttribute("vendor") Vendor vendor,BindingResult result,
			RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			redirectAttributes.addFlashAttribute("vendor",vendor);
			 redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/vendor";
		}
			else if(vendorService.saveVendor(vendor))
			{
			redirectAttributes.addFlashAttribute("success","vendor saved successfully!");
			return "redirect:/vendor";
			}
				else 
				{
					redirectAttributes.addFlashAttribute("fail","vendor already exists!");
					return "redirect:/vendor";
				}
		
		
	}
	
	
	
	@RequestMapping(value = "/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		
		
		if(!model.asMap().containsKey("vendor")){
		  model.addAttribute("vendor", vendorService.getVendor(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.vendor",
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
		return "vendor";
	}
	
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("vendor") Vendor vendor,BindingResult result,RedirectAttributes redirectAttributes,HttpServletRequest request,
							HttpSession session) 
	{

	
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors())
		{
			redirectAttributes.addFlashAttribute("vendor",vendor);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/vendor/"+vendor.getId()+"/"+page;
		}
		else if(vendorService.update(vendor))
		{
			redirectAttributes.addFlashAttribute("success","Vendor updated successfully");
			return "redirect:/vendor/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Vendor already exists");
			redirectAttributes.addFlashAttribute("vendor",vendor);
			return "redirect:/vendor/"+vendor.getId()+"/"+page;
		}	
	}
	
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request ,RedirectAttributes redirectAttributes){
		
		String[] id = request.getParameterValues("id");
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				try {
					vendorService.delete(Long.valueOf(id[i]));
				} catch (Exception e) {
					
					redirectAttributes.addFlashAttribute("fail","Some Vendors cannot be  deleted ");
				}
			}
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Select vendor(s)to delete");
		} 
		return "redirect:/vendor";	
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
	
		pagination(page, model);
		model.addAttribute("vendor", new Vendor());
		return "vendor";
	}
	
	public void pagination(int page,Model model){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) vendorService.getCount();
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("vendorList", vendorService.getPaginated(from));

	}
	
	
}
