package com.aste.lsme.controller;

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
import com.aste.lsme.domain.Part;
import com.aste.lsme.service.ManufacturerService;
import com.aste.lsme.service.PartNameService;
import com.aste.lsme.service.PartService;
import com.aste.lsme.service.UnitOfMeasureService;
import com.aste.lsme.service.VendorService;

@Controller
@RequestMapping("/part")
public class PartController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	PartNameService partNameService;
	
	
	@Autowired
	ManufacturerService manufacturerService;
	
	
	@Autowired
	VendorService vendorService;
	

	@Autowired
	PartService partService;
	
	
	@Autowired
	UnitOfMeasureService unitService;
	
	@RequestMapping( method = RequestMethod.GET)
	public String createPart(Model model, HttpSession session) {
		int page = 1;
			if(!model.asMap().containsKey("part")){
			   model.addAttribute("part", new Part());
			} 
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.part",model.asMap().get("result"));
			}
		
		getAttributes(model,page);
		pagination(page, model);
		return "createPart";
		
	}
	public void getAttributes(Model model,int page){
		 	model.addAttribute("partNameList", partNameService.getall());
			model.addAttribute("manufacturerList", manufacturerService.getAll());
			model.addAttribute("vendorList", vendorService.getAll());
			model.addAttribute("unitMeasureList", unitService.getUnitOfMeasure());
			pagination(page, model);
	}
	
	@RequestMapping( method = RequestMethod.POST)
	public String addPart(@Valid @ModelAttribute("part") Part part,BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()){
			redirect.addFlashAttribute("result", result);
			redirect.addFlashAttribute("part", part);
			redirect.addFlashAttribute("fail", "Enter fields carefully");
			return "redirect:/part";
		}else{
				if(partService.save(part)){
					redirect.addFlashAttribute("success", "Part created successfully");
				}else{
					redirect.addFlashAttribute("fail", "Part Already Exists");
				}
		}
		return "redirect:/part";
	}

	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		pagination(page, model);
		model.addAttribute("part", new Part());
		getAttributes(model, page);
		return "createPart";
	}
	
	public void pagination(int page,Model model){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) partService.getCount();
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("partList", partService.getPartPaginated(from));
	}
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String editPart(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session){
		   
		if(partService.getPart(id)==null){
			   model.addAttribute("fail", "No such Record Found");
			   model.addAttribute("part", new Part());
				model.addAttribute("edit", true);

			   getAttributes(model,page);
		        return "createPart";
		   }
		
			if(!model.asMap().containsKey("part")){
				model.addAttribute("part", partService.getPart(id));	
			 }
			if(model.asMap().containsKey("result")){
				model.addAttribute("org.springframework.validation.BindingResult.part",	model.asMap().get("result"));
			}
			model.addAttribute("edit", true);
			if(model.asMap().containsKey("page")){
				page = (Integer) model.asMap().get("page");
				pagination(page, model);
			}
			else{
				pagination(page, model);
			}
		 
				
			
		getAttributes(model,page);
        return "createPart"; 
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updatePart(@Valid @ModelAttribute("part") Part part,BindingResult result,Model model,RedirectAttributes redirect,HttpSession session){
		if(result.hasErrors()){
			redirect.addFlashAttribute("result", result);
			redirect.addFlashAttribute("part", part);
			redirect.addFlashAttribute("fail", "Updation Failed");
		}
		else{
			if(partService.update(part)){
				redirect.addFlashAttribute("success", "Part Updated successfully");
			}else{
				redirect.addFlashAttribute("fail", "Part Already Exists");
			}
		}
		return "redirect:/part/edit/"+part.getId()+"/"+1;
	}
	
	

	
	@RequestMapping(value = "/delete/{id}" , method = RequestMethod.GET)
	public String deletePart(@PathVariable("id") long id,RedirectAttributes redirect,HttpSession session){
			try{
				partService.remove(id);
				redirect.addFlashAttribute("success", "Part Deleted Successfully");
			
			}catch (Exception e) {
				redirect.addFlashAttribute("fail", "Deletion Failed!");
				e.printStackTrace();
				return "redirect:/part";
		     }
			
		return "redirect:/part";
	}
	
	
	
	
}
