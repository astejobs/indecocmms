package com.aste.lsme.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.SOR;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.LabourRateService;
import com.aste.lsme.service.QuotServiceInterface;
import com.aste.lsme.service.QuotationServiceInterface;
import com.aste.lsme.service.SORService;
import com.aste.lsme.serviceImpl.QuotationserviceImpl;



@Controller
@RequestMapping("/quotation")
public class QuotationController {
	
	
	@Autowired
	SORService sorService;
	
	@Autowired
	LabourRateService loabourservice;
	
	@Autowired
	QuotServiceInterface quotservice;
	
	@Autowired
	QuotationserviceImpl quotationservice;
	
	@Autowired
	QuotationServiceInterface quotationService;
	
	@RequestMapping(value = "addquotation", method = RequestMethod.GET)
	String getQuotation(Model model,HttpSession session)
	{
	  
	  if (!model.containsAttribute("asset")) 
		  model.addAttribute("quotation",new Quotation());
	  else
		  model.addAttribute("quotation", model.asMap().get("asset"));
	  
	  model.addAttribute("org.springframework.validation.BindingResult.quotation",
		      model.asMap().get("error"));
		  
		
		
		return "quotation";
	}
	
	@RequestMapping(value = "addquotation", method = RequestMethod.POST)
	String addQuotation(@Valid Quotation quotation, BindingResult result, Model model,HttpSession session, RedirectAttributes att)
	{
		if(result.hasErrors()){
			att.addFlashAttribute("asset", quotation);
		    att.addFlashAttribute("error", result);
		    return "redirect:/quotation/addquotation";
		}
		Workspace w=(Workspace)session.getAttribute("workspace");
		quotation.setWorkspace(w);
		quotation.setType("NORMAL");
		try{
		   quotationservice.addQuotation(quotation);
		   att.addFlashAttribute("success", "Quotation Added Successfully");
		}catch(Exception ex){
			   att.addFlashAttribute("fail", "Cannot Add Quotation");
		}
		 
			 
		 return "redirect:/quotation/addquotation";
	}
	
	@RequestMapping(value = "addstarratequotation", method = RequestMethod.GET)
	String getstarRateQuotation(Model model,HttpSession session)
	{
	  
	  if (!model.containsAttribute("asset")) 
		  model.addAttribute("quotation",new Quotation());
	  else
		  model.addAttribute("quotation", model.asMap().get("asset"));
	  
	  model.addAttribute("org.springframework.validation.BindingResult.quotation",
		      model.asMap().get("error"));
		  
	
		
		return "starratequotation";
	}
	
	@RequestMapping(value = "addstarratequotation", method = RequestMethod.POST)
	String addstarRateQuotation(@Valid Quotation quotation, BindingResult result, Model model,HttpSession session, RedirectAttributes att)
	{
		if(result.hasErrors()){
			att.addFlashAttribute("asset", quotation);
		    att.addFlashAttribute("error", result);
		    return "redirect:/quotation/addstarratequotation";
		}
		Workspace w=(Workspace)session.getAttribute("workspace");
		quotation.setWorkspace(w);
		quotation.setType("STARRATE");
		try{
		   quotationservice.addQuotation(quotation);
		   att.addFlashAttribute("success", "Star Rate Quotation Added Successfully");
		}catch(Exception ex){
			   att.addFlashAttribute("fail", "Cannot Add Star Rate Quotation");
		}
		 
			 
		 return "redirect:/quotation/addstarratequotation";
	}
	
	@RequestMapping(value = "getallquotations", method = RequestMethod.GET)
	String getallquotations(Model model,HttpSession session){
	
	  model.addAttribute("searchquotation", new QuotationSearch());
	  return"searchquotation";
	  
       
	}
	
	@RequestMapping(value = "searchquotation", method = RequestMethod.GET)
	String searchquotation(QuotationSearch quotationSearch, @RequestParam( value="page", defaultValue="1") int page, Model model,HttpSession session){
		
		session.setAttribute("search", quotationSearch);
		return  "redirect:/quotation/pageno="+page;	
	   
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w,session);
	    model.addAttribute("searchquotation", session.getAttribute("search"));
	    return"searchquotation";
	}
	
   public void pagination(int page,Model model,Workspace w, HttpSession session){
		
		int from = 0;
		int total = 0;
		final int ROWS = Constants.ROWS;
		Long records = 0L;
		from = ROWS*(page-1);
		page = (page > 0) ? page : 1;
		QuotationSearch search = (QuotationSearch) session.getAttribute("search");
		search.setWorkspace(w);
		search.setFromPage(from);
		records = (long) quotationservice.countSearchPage(search);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("quotations", quotationservice.getSearchPage(search));

	}
	
	@RequestMapping(value = "editquotation/{id}", method = RequestMethod.GET)
	String editquotation(@PathVariable("id") Long id,  Model model,HttpSession session)
	{
		
	  
	    Quotation quotation = quotationservice.findquotation(id);
	    if(quotation.getType().equals("STARRATE")){
	    	 model.addAttribute("edit", true);
	    	 model.addAttribute("quotation",quotation);	
	    	 return "starratequotation";
	    }
	  
		model.addAttribute("edit", true);
		
		model.addAttribute("quotation",quotation);	
	    return "quotation";
	}
	
	@RequestMapping(value = "updatequotation", method = RequestMethod.POST)
	String updatequotation(@Valid Quotation quotation,BindingResult result, RedirectAttributes att, Model model,HttpSession session)
	{
		 
		 
		if(result.hasErrors()){
			model.addAttribute("edit", true);	
			model.addAttribute("asset", quotation);
			model.addAttribute("error", result);
			return"quotation";
		}
		
		if(quotationservice.update(quotation).equals(null))
			model.addAttribute("fail","Quotation updated Failed");
		else
		    model.addAttribute("success", "Quotation updated Sucessfully");
		model.addAttribute("edit", true);
	    return"quotation";
	  
       
	}
	
	@RequestMapping(value = "updatestarratequotation", method = RequestMethod.POST)
	String updatstarrateequotation(@Valid Quotation quotation,BindingResult result, RedirectAttributes att, Model model,HttpSession session)
	{
		
		if(result.hasErrors()){
			model.addAttribute("edit", true);	
			model.addAttribute("asset", quotation);
			model.addAttribute("error", result);
			return"starratequotation";
		}
		
		if(quotationservice.update(quotation).equals(null))
			model.addAttribute("fail","Star Rate Quotation updated Failed");
		else
		    model.addAttribute("success", "Star Rate Quotation updated Sucessfully");
		model.addAttribute("edit", true);
	    return"starratequotation";
	  
       
	}
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	String viewquotation(@PathVariable("id") Long id,  Model model,HttpSession session)
	{
		
        Quotation quotation = quotationservice.findquotation(id);
	    if(quotation.getType().equals("STARRATE")){
	    	 model.addAttribute("quotation",quotation);	
	    	 return "starratequotationview";
	    }
	    model.addAttribute("quotation",quotation);	
	    return "quotationview";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, HttpServletRequest req,
			Model model, HttpSession session,
			final RedirectAttributes redirectAttributes) throws Exception {
		
		Quotation quotation = quotationService.findquotation(id);
		if (quotation != null)
			try {
				quotationService.remove(quotation);
				redirectAttributes.addFlashAttribute(
						"success",
						"Quotation removed successfully.");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute(
						"fail",
						"Quotation deletion failed.");
			}
		else
			redirectAttributes.addFlashAttribute("fail",
					"Cant find Quotation");
		return "redirect:/quotation/getallquotations";
	}
	

	
	@RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
	public ModelAndView report(@PathVariable Long id, ModelMap modelMap,
			ModelAndView modelAndView, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");//workspaceService.getworkspaceonid("STIE-NLB-032015-001"); //(Workspace) session.getAttribute("projid");
		Quotation header = quotationService.findquotation(id);
		if (quotationService != null && header.getWorkspace() != w) {
			
			modelMap.put("datasource", Arrays.asList(header));
			modelMap.put("ROOT_DIR","/jrxmlTemplates/");

			if (w.getWorkspaceId().contains("-MHA-")){
				modelMap.put("headerType", "MHA");
				modelMap.put("format", "pdf");
				modelAndView = new ModelAndView("quotationreport", modelMap);
			}
			else{
				
				modelMap.put("headerType", "STIE");
				modelMap.put("format", "pdf");
				modelAndView = new ModelAndView("quotationreport", modelMap);
			}
			return modelAndView;
		}
		redirectAttributes.addFlashAttribute(Constants.CUSTOM_ERRORMESSAGE,
				"Quotation does not exist");
		return new ModelAndView("redirect:/quotation/view/");

	}



}
