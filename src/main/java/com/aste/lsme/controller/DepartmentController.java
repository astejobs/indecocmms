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
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	

	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		Workspace w=(Workspace) session.getAttribute("workspace");

		if(!model.asMap().containsKey("department")){
			   model.addAttribute("department", new Department());
			} 

		//model.addAttribute("division", new Division());
		model.addAttribute("departmentList", departmentService.getAll());
		
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.department",
					model.asMap().get("result"));
		}
		
		int page = 1;
		pagination(page, model,w);
		return "department";
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDivision(@Valid @ModelAttribute("department") Department department,Model model,
			RedirectAttributes redirectAttributes,BindingResult result,HttpSession session  )
	{
		Workspace w=(Workspace) session.getAttribute("workspace");
		department.setWorkspace(w);
		System.out.println(result.toString()+" "+result.hasErrors());
		if(result.hasErrors()){
		
		    redirectAttributes.addFlashAttribute("department",department);
		    redirectAttributes.addFlashAttribute("result",result);
		   // redirectAttributes.addFlashAttribute("msg","File has "+result.getErrorCount()+" errors");
			return "redirect:/department";
		}
		else if(departmentService.persist(department,w)){
			redirectAttributes.addFlashAttribute("success","Department saved successfully");
			return "redirect:/department/";
		}
		else{
			redirectAttributes.addFlashAttribute("fail", "Department already exists");
			redirectAttributes.addFlashAttribute("department",department);
			return "redirect:/department/";
		}
		

}
	
	@RequestMapping(value = "/edit/{id}/{page}" , method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id,@PathVariable("page") int page,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");

		if(!model.asMap().containsKey("department")){
			  model.addAttribute("department", departmentService.get(id));
			}
			if(model.asMap().containsKey("result")){
				model.addAttribute(
						"org.springframework.validation.BindingResult.department",
						model.asMap().get("result"));
			}
	//	model.addAttribute("division", divisionService.get(id));
		model.addAttribute("departmentList", departmentService.getAll());
		model.addAttribute("edit", true);
		
		if(model.asMap().containsKey("page")){
			page = (Integer) model.asMap().get("page");
			pagination(page, model,w);
		}
		else{
			pagination(page, model,w);
		}
		return "department";
	}
	
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("department")Department department,BindingResult result,
			RedirectAttributes redirectAttributes,HttpServletRequest request,HttpSession session) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		department.setWorkspace(w);
		
		int page = Integer.parseInt(request.getParameter("p"));
		redirectAttributes.addFlashAttribute("page", page);
		
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("department",department);
			redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/department/edit/"+department.getId()+"/"+page;
		
		}
		else if(departmentService.update(department,w)){
			redirectAttributes.addFlashAttribute("success","department updated successfully");
			redirectAttributes.addFlashAttribute("departmentList", departmentService.getAll());
			return "redirect:/department/";
		}
		else{
			redirectAttributes.addFlashAttribute("departmentList", departmentService.getAll());
			redirectAttributes.addFlashAttribute("fail", "department already exists");
			redirectAttributes.addFlashAttribute("department",department);
			return "redirect:/department/edit/"+department.getId()+"/"+page;
		}	
	}
	
	@RequestMapping(value = "/delete" ,method = RequestMethod.POST)
	public String delete( HttpServletRequest request , Model model,RedirectAttributes redirectAttributes) {
		
		String[] id = request.getParameterValues("id");
		if(id!=null){
		for (int i = 0; i < id.length; i++) {
			departmentService.delete(Long.valueOf(id[i]));
		}
		}else{
			redirectAttributes.addFlashAttribute("fail", "Select departments to delete");
		}
		
		model.addAttribute("departmentList", departmentService.getAll());
		return "redirect:/department";	
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		pagination(page, model,w);
		model.addAttribute("department", new Department());
		return "department";
	}
	
	public void pagination(int page,Model model,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long)departmentService.getDepartmentCount(w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("departmentList", departmentService.getDepartmentPaginated(from,w));
		
	}

	

}
