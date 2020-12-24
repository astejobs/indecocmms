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

import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.service.AssetTypeService;

@Controller
@RequestMapping(value="/assetType")
public class AssetTypeController {

	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	AssetTypeService assetTypeService ;
	
	@RequestMapping(method = RequestMethod.GET )
	public String home( Model model) {
		if(!model.asMap().containsKey("assetType")){
			   model.addAttribute("assetType", new AssetType());
			} 

			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.assetType",
						model.asMap().get("result"));
			}
			int page = 1;
			pagination(page, model);
			return "assettype";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAssettype(@Valid @ModelAttribute("assetType") AssetType assetType,BindingResult result,RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("assetType",assetType);
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/assetType";
		}
		else if(assetTypeService.addAssettype(assetType)){
			redirectAttributes.addFlashAttribute("success","Asset Type added successfully");
			return "redirect:/assetType";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Asset Type already exists");
			redirectAttributes.addFlashAttribute("assetType",assetType);
			return "redirect:/assetType";
		}
		
}
	@RequestMapping(value="/edit/{id}/{page}", method=RequestMethod.GET)
    public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model) {
		
		if(!model.asMap().containsKey("assetType")){
			  model.addAttribute("assetType", assetTypeService.get(id));
			}
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.assetType",
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
		return  "assettype";
		
	}
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("assetType") AssetType assetType ,BindingResult result,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("assetType",assetType);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/assetType/edit/"+assetType.getId()+"/"+page;
		}
		else if(assetTypeService.update(assetType)){
			redirectAttributes.addFlashAttribute("success","Asset Type updated successfully");
			return "redirect:/assetType/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Asset Type already exists");
			redirectAttributes.addFlashAttribute("assetType",assetType);
			return "redirect:/assetType/edit/"+assetType.getId()+"/"+page;
		}	
	}
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.GET)
	public String delete( @PathVariable("id") Long id ,RedirectAttributes redirectAttributes,Model model) {
		try{	
			assetTypeService.delete(id);
			redirectAttributes.addFlashAttribute("success","Asset Type deleted successfully");
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("fail", "Asset Type cannot be deleted");
		}
		return "redirect:/assetType";	
	}

	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model){
		pagination(page, model);
		model.addAttribute("assetType", new AssetType());
		return "assettype";
	}
	
	public void pagination(int page,Model model){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) assetTypeService.getAssetTypeCount();
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("assetTypeList", assetTypeService.getAssetPaginated(from));
		
	}

}
