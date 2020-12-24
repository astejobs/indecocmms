package com.aste.lsme.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
@Controller
@RequestMapping("/assetSubtype")
public class AssetSubtypeController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;	
	
	@Autowired
	AssetSubtypeService assetSubtypeService;
	@Autowired
	AssetTypeService assetTypeService ;
	
	@RequestMapping(method=RequestMethod.GET)
	public String home( Model model) 
	{
		if(!model.asMap().containsKey("assetSubtype")){
			   model.addAttribute("assetSubtype", new AssetSubtype());
			} 

			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.assetSubtype",
						model.asMap().get("result"));
			}
			
			int page = 1;
			pagination(page, model);
			return "assetSubtype";
	}
	
	@RequestMapping(value="/add" , method=RequestMethod.POST)
	public String addAssetSubtype(@Valid @ModelAttribute("assetSubtype") AssetSubtype assetSubtype,BindingResult result,RedirectAttributes redirectAttributes) 
	
	{
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("assetSubtype",assetSubtype);
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/assetSubtype";
		}
		else if(assetSubtypeService.addAssetSubtype(assetSubtype)){
			redirectAttributes.addFlashAttribute("success","Asset SubType saved successfully");
			return "redirect:/assetSubtype";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Asset SubType already exists");
			redirectAttributes.addFlashAttribute("assetSubtype",assetSubtype);
			return "redirect:/assetSubtype";
		}
		
	}
	@RequestMapping(value="/edit/{id}/{page}", method=RequestMethod.GET)
     public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model) {
		
		
		if(!model.asMap().containsKey("assetSubtype")){
			  model.addAttribute("assetSubtype", assetSubtypeService.get(id));
			}
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.assetSubtype",
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
		return  "assetSubtype";
	}
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("assetSubtype") AssetSubtype assetSubtype,BindingResult result,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("assetSubtype",assetSubtype);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/assetSubtype/edit/"+assetSubtype.getId()+"/"+page;
		}
		else if(assetSubtypeService.update(assetSubtype)){
			redirectAttributes.addFlashAttribute("success","Asset SubType updated successfully");
			return "redirect:/assetSubtype/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Asset SubType already exists");
			redirectAttributes.addFlashAttribute("assetSubtype",assetSubtype);
			return "redirect:/assetSubtype/edit/"+assetSubtype.getId()+"/"+page;
		}
	}
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes)
	{
		try {
			
			assetSubtypeService.delete(id);
			redirectAttributes.addFlashAttribute("success","Asset Subtype deleted successfully");

		} catch (Exception e) {
			
			redirectAttributes.addFlashAttribute("fail","Asset Subtype cannot be deleted");
		}
		return "redirect:/assetSubtype/";
		
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model){
		pagination(page, model);
		model.addAttribute("assetSubtype", new AssetSubtype());
		return "assetSubtype";
	}
	
	public void pagination(int page,Model model){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) assetSubtypeService.getAssetSubTypeCount();
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("assetSubtypeList", assetSubtypeService.getAssetSubTypePaginated(from));
		model.addAttribute("assetTypeList", assetTypeService.getAll());

		
	}
}
	
	
	
	
	
	


