package com.aste.lsme.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.ModuleDetail;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.GroupServiceInterface;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.service.WorkspaceService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	UserDetailsServiceInterface userservice;
	
	@Autowired
	WorkspaceService workspaceservice;
	
	@Autowired
	GroupServiceInterface groupservice;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getdashboard(Model model,HttpSession session)
	{
	  UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	     UserDetail user= userservice.loadUserByUsername(userDetails.getUsername());
		 session.setAttribute("user", user);
		 session.setAttribute("modules", user.getUsergroup().getModuleDetails());
		 if(!user.getUsergroup().getWorkspaces().isEmpty())
		    model.addAttribute("listofworkspaces", user.getUsergroup().getWorkspaces());
		 else
		    model.addAttribute("noworkspace", "No Sites Availible, Please Contact Admin"); 
		return "dashboard";
		
	}
	
	@RequestMapping(value="/workspace/{id}",method=RequestMethod.GET)
	public String getworkspacerelatedsite(@PathVariable("id") String id,Model model,HttpSession session)
	{
		Workspace workspace = workspaceservice.getworkspaceonid(id);
		session.setAttribute("workspace",workspace);
		List<ModuleDetail>  m =(List<ModuleDetail>) session.getAttribute("modules");
		if(m.contains(groupservice.getmodulebyName("Administration")))
		   model.addAttribute("flagadmin", true);
		
		model.addAttribute("workspaceid", id);
		return "dashboardlevel1";
	}
	
	@RequestMapping(value="/overview",method=RequestMethod.GET)
	public String getoverview(Model model,HttpSession session)
	{
		
		return "redirect:/faultReport/add";
		
	}
	
	@RequestMapping(value="/access",method=RequestMethod.GET)
	public String admincontrol(Model model,HttpSession session)
	{
		
		List<ModuleDetail>  m =(List<ModuleDetail>) session.getAttribute("modules");
		if(m.contains(groupservice.getmodulebyName("User Management")))
			   model.addAttribute("flagusermanagement", true);
		if(m.contains(groupservice.getmodulebyName("Group Management")))
			   model.addAttribute("flaggroupmanagement", true);
		if(m.contains(groupservice.getmodulebyName("Access Control")))
			   model.addAttribute("flagaccesscontrol", true);
		
		
		
		return "accessdashboard";
		
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String usercontrol(Model model,HttpSession session)
	{
		
		return "useraccessdashboard";
		
	}
	
	@RequestMapping(value="/group",method=RequestMethod.GET)
	public String groupcontrol(Model model,HttpSession session)
	{
		
		return "groupaccessdashboard";
		
	}
	
	@RequestMapping(value="/priviledge",method=RequestMethod.GET)
	public String priviledgecontrol(Model model,HttpSession session)
	{
		
		return "priviledgeaccessdashboard";
		
	}
	
	
	
	@RequestMapping(value = "/getImages/{imageName:.+}", method = RequestMethod.GET)
	public void getDashBoardImages(@PathVariable("imageName") String imageName, Model model, HttpServletRequest req,
			HttpServletResponse rep) {
		try {
            
			
			InputStream is = new FileInputStream(Constants.PATH + imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			if (imageName.contains(".pdf"))
				rep.setContentType("application/pdf");
			else if (imageName.contains(".dwg"))
				rep.setContentType("imsage/vnd.dwg");
			else if (imageName.contains(".gif"))
				rep.setContentType("image/gif");
			else if (imageName.contains(".jpg"))
				rep.setContentType("image/jpg");
			else
				rep.setContentType("image/png");
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {// e.printStackTrace();
			e.printStackTrace();
			System.out.println("Image " + imageName + " not present");
		}
	}
	
	

}
