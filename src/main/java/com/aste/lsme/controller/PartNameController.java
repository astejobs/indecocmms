package com.aste.lsme.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.PartName;
import com.aste.lsme.service.PartNameService;

@Controller
@RequestMapping(value="/partname")
public class PartNameController {

	
	@Autowired
	PartNameService partNameService;
	@RequestMapping(method=RequestMethod.GET)
	public String partName(Model model){
		
		model.addAttribute("partNameList", partNameService.getall());
		if(!model.asMap().containsKey("partName"))
			model.addAttribute("partName", new PartName());
		if(model.asMap().containsKey("result"))
			model.addAttribute("org.springframework.validation.BindingResult.partName", model.asMap().get("result"));
		return "partname";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String partnameSave(@Valid @ModelAttribute("partName")PartName partName,BindingResult result,RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("partName",partName);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("fail","Please enter all fields correctly");
			return "redirect:/partname";
		}
		else if(partNameService.persist(partName))
			redirectAttributes.addFlashAttribute("success","Part Name added successfully");

		else
			redirectAttributes.addFlashAttribute("fail","Part Name already exists");
		
			return "redirect:/partname";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String partNameget(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("partName", partNameService.get(id));
		redirectAttributes.addFlashAttribute("edit", "true");
		
		return "redirect:/partname";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String partNameUpdate(@Valid @ModelAttribute("partName") PartName partName,BindingResult result,RedirectAttributes redirectAttributes ){
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("partName",partName);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("fail","Please enter all fields correctly");
			return "redirect:/partname";
		}
		else if(partNameService.update(partName))
		    redirectAttributes.addFlashAttribute("success","Part Name updated successfully");
		else
			redirectAttributes.addFlashAttribute("fail","Part Name already exists");
		
		return "redirect:/partname";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String partNameDelete(@RequestParam(value="id",required = false) Long[] id,RedirectAttributes redirectAttributes){
		
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				try {
					partNameService.delete(id[i]);
				} catch (Exception e) {
					
					redirectAttributes.addFlashAttribute("fail","Some Part Names cannot be  deleted ");
					return "redirect:/partname";

				}
				redirectAttributes.addFlashAttribute("success","Part Names deleted successfully");
			}
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Select Part Name(s) to delete");
		} 		
		return "redirect:/partname";
	}
}
