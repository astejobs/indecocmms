package com.aste.lsme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.GroupPriviledges;
import com.aste.lsme.domain.ModuleDetail;
import com.aste.lsme.domain.PasswordDTO;
import com.aste.lsme.domain.SitePriviledge;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.GroupServiceInterface;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.service.WorkspaceService;

@Controller
@RequestMapping("/access")
public class AccessController {
	
	@Autowired
	GroupServiceInterface groupservice;
	
	@Autowired
	UserDetailsServiceInterface userservice;
	
	@Autowired
	WorkspaceService workspaceservice;
	
	
	
	@RequestMapping(value="/userpriviledges", method=RequestMethod.GET)
	public String getUserPriviledges(Model model)
	{
		model.addAttribute("groupList",groupservice.getallgroups());
		model.addAttribute("grouppriviledges", new GroupPriviledges());
		return "userPriviledges";
	}
	
	@RequestMapping(value="/userpriviledges", method=RequestMethod.POST)
	public String setUserPriviledges(GroupPriviledges grouppriviledges,RedirectAttributes att)
	{
		 List<ModuleDetail>  modules =  groupservice.getmodulesbasedongroup(grouppriviledges);
		 UserGroup usergroup= grouppriviledges.getUserGroup();
		 usergroup.setModuleDetails(modules);
	     groupservice.updateGroup(usergroup);
	     att.addFlashAttribute("success", "Modules Saved for the Group Successfully");
		 return "redirect:/access/userpriviledges";
	}
	
	
	@RequestMapping(value="/siteaccess", method=RequestMethod.GET)
	public String getSiteAccess(Model model)
	{
		model.addAttribute("groupList",groupservice.getallgroups());
        model.addAttribute("workspaces", workspaceservice.getallworkspaces());
		model.addAttribute("sitepriviledges", new SitePriviledge());
	    return "sitePriviledges";
	}
	
	@RequestMapping(value="/siteaccess", method=RequestMethod.POST)
	public String setSiteAccess(SitePriviledge site,RedirectAttributes att)
	{
		 UserGroup ugroup=  site.getUserGroup();
		 ugroup.setWorkspaces(workspaceservice.getgroupprivilegeworkspace(site.getWspaces()));
		 groupservice.updateGroup(ugroup);
		 att.addFlashAttribute("success", "Sites Saved for the Group Successfully");
		 return "redirect:/access/siteaccess";
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.GET)
	public String setPasswordChange(Model model)
	{
		model.addAttribute("password", new PasswordDTO());
	    return "changepassword";
		
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public String getPasswordChange(PasswordDTO password, RedirectAttributes att)
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserDetail user = userservice.findUserByUsername(password.getUserName());
		if(user.equals(null))
			att.addFlashAttribute("nouserfound", "Cannot Find The User With the Username");
		else if(!passwordEncoder.matches(password.getOldpassword(), user.getPassword()))
			 att.addFlashAttribute("wrongpassword","Sorry the sumitted password does not match the password");
		else if(!password.getNewpassword().equals(password.getConfirmNewpassowrd()))
			 att.addFlashAttribute("nomatchpasswords", "Sorry the New Password and Confirmed Password Dont match ");
		else
		{
			 user.setPassword(passwordEncoder.encode(password.getNewpassword()));
		     userservice.updateUser(user);
		     att.addFlashAttribute("success", "Password Updated successfully");
		}
			
		
	    return "redirect:/access/changepassword";
		
	}
	
	@RequestMapping(value="/getmodulesforgroup/{id}", method= RequestMethod.GET)
	public @ResponseBody List<ModuleDetail> getmodules(@PathVariable("id") Long id)
	{
		return groupservice.getgrouprelatedmodule(id);
		
		
	}
	
	@RequestMapping(value="/getsitesforgroup/{id}", method= RequestMethod.GET)
	public @ResponseBody List<Workspace> getsites(@PathVariable("id") Long id)
	{
		return groupservice.getgrouprelatedsite(id);
		
		
	}
	


}
