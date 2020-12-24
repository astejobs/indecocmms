package com.aste.lsme.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.service.GroupServiceInterface;
import com.aste.lsme.service.RoleService;

@Controller
@RequestMapping("/groupmanagement")
public class GroupAccessController {
	
	@Autowired
	GroupServiceInterface groupservice;
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/addgroup", method=RequestMethod.GET)
	public String getGroup(Model model)
	{
		if (!model.containsAttribute("group")) 
			model.addAttribute("group", new  UserGroup());
		
		model.addAttribute("roles",roleService.findAll());
		model.addAttribute("org.springframework.validation.BindingResult.group",
				      model.asMap().get("error"));
		return "addGroup";
		
	}
	
	
	@RequestMapping(value="/addgroup", method=RequestMethod.POST)
	public String addGroup(@Valid UserGroup group, BindingResult result,Model model, Locale locale, RedirectAttributes att)
	{
		
		if(result.hasErrors()){
			att.addFlashAttribute("group", group);
			att.addFlashAttribute("error", result);
			return "redirect:/groupmanagement/addgroup";
		}
		
		try{
			 groupservice.saveGroup(group);
			 att.addFlashAttribute("success","Group created Successfully");
			 
		}
		 catch(Exception ex){
			 ex.printStackTrace();
			att.addFlashAttribute("fail",  "Group name already exists");
			att.addFlashAttribute("group", group);
			
		 }
		 return "redirect:/groupmanagement/addgroup";
		
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String viewUsers(Model model)
	{
		model.addAttribute("GroupList", groupservice.getallgroups());
		return "viewGroup";
		
		
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id,Model model)
	{
		UserGroup group= groupservice.findGroup(id);
		model.addAttribute("roles",roleService.findAll());
		model.addAttribute("group", group);
		model.addAttribute("edit", true);
		return "addGroup";
		
		
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateUser(@Valid UserGroup group, BindingResult result, Locale locale,Model model,RedirectAttributes attr)
	{
		if(result.hasErrors()){
			model.addAttribute("asset", group);
			model.addAttribute("error", result);
			return "addGroup";
		}
		
		try{
			 groupservice.updateGroup(group);
			 model.addAttribute("success", "Group updated Successfully");
			 model.addAttribute("GroupList", groupservice.getallgroups());
			
			 
		}
		 catch(Exception ex){
			 model.addAttribute("edit", true);
			 model.addAttribute("fail","Failed to update group");
			 model.addAttribute("group", group);
			 return "addGroup";
				
			
		 }
		
		return "viewGroup";
		
		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id,Model model,Locale locale)
	{
		try{
			groupservice.removeGroup(id);
			 model.addAttribute("success", "Group deleted successfully");
		}
		catch(Exception ex){
			 model.addAttribute("fail", "Failed to delete Group");
		}
		
		model.addAttribute("GroupList", groupservice.getallgroups());
		return "viewGroup";
		
		
	}
	
	
	

}
