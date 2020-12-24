package com.aste.lsme.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.ThrushHoldValue;
import com.aste.lsme.domain.PredictiveMonitorClass;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.MonitorClassService;

@Controller
@RequestMapping("/monitorclass")
public class MonitorClassController {
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	MonitorClassService monitorclassService;
	
	
	@RequestMapping(value = "/PMAddNewClass", method = RequestMethod.GET)
	public String pMAddNewClassForm(Model model, HttpSession session) {
		
		
		
		
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(!model.asMap().containsKey("building")){
		   model.addAttribute("monitorclass", new PredictiveMonitorClass());
		} 

		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.monitorclass",
					model.asMap().get("result"));
		}
		int page = 1;
		pagination(page, model,w);
		
		
		return "PMAddNewClass";
	}
 
	
	
	@RequestMapping(value = "/PMAddNewClass", method = RequestMethod.POST)
	public String pMAddNewClass(
			@Valid @ModelAttribute("monitorclass") PredictiveMonitorClass monitorclass,
			final BindingResult result, Model model, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		monitorclass.setWorkspace(w);
		
		if(result.hasErrors()){
		    redirectAttributes.addFlashAttribute("monitorclass",monitorclass);
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/monitorclass/PMAddNewClass";
		}
		else if(monitorclassService.save(monitorclass,w)){
			redirectAttributes.addFlashAttribute("success","MonitorClass saved successfully");
			return "redirect:/monitorclass/PMAddNewClass";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "MonitorClass already exists");
			redirectAttributes.addFlashAttribute("monitorclass",monitorclass);
			return "redirect:/monitorclass/PMAddNewClass";
		}
		
		
		

	}

	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("monitorclass", new PredictiveMonitorClass());
		return "PMAddNewClass";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) monitorclassService.getClassCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("monitorclasses", monitorclassService.getPaginatedMonitor(from,w));

	}

	
	
	
	
	
	@RequestMapping(value = "/monitorclasslist", method = RequestMethod.GET)
	 @ResponseBody public List<PredictiveMonitorClass> getMonitorClassList(HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");

	
		  return monitorclassService.getMonitorClasses(w);
	  }
	
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		if(!model.asMap().containsKey("monitorclass")){
		  model.addAttribute("monitorclass", monitorclassService.get(id));
		}
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.monitorclass",
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
		return "PMAddNewClass";
	}
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("monitorclass") PredictiveMonitorClass pm,BindingResult result,
							RedirectAttributes redirectAttributes,HttpServletRequest request,
							HttpSession session) {

		Workspace w = (Workspace) session.getAttribute("workspace");
		pm.setWorkspace(w);
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("monitorclass",pm);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/monitorclass/edit/"+pm.getId()+"/"+page;
		}
		else if(monitorclassService.update(pm,w)){
			redirectAttributes.addFlashAttribute("success","MonitorClass updated successfully");
			return "redirect:/monitorclass/edit/"+pm.getId()+"/"+page;
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "MonitorClass already exists");
			redirectAttributes.addFlashAttribute("monitorclass",pm);
			return "redirect:/monitorclass/edit/"+pm.getId()+"/"+page;
		}	
	}

	
	
	
	
	@RequestMapping(value = "/delete" , method = RequestMethod.POST)
	public String delete(HttpServletRequest request ,RedirectAttributes redirectAttributes){
		
		String[] id = request.getParameterValues("id");
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				try {
					monitorclassService.delete(Long.valueOf(id[i]));
					redirectAttributes.addFlashAttribute("success"," MonitorClass(s)  deleted Successfully");

				} catch (Exception e) {
					
					redirectAttributes.addFlashAttribute("fail","Some MonitorClass(s) cannot be  deleted ");
				}
			}
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Select Monitor(s) to delete");
		} 
		return "redirect:/monitorclass/PMAddNewClass";	
	}
	
	

	
	
	
	
	
}
