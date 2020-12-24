package com.aste.lsme.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.service.GroupServiceInterface;
import com.aste.lsme.service.UserDetailsServiceInterface;

@Controller
@RequestMapping("/usermanagement")
public class UserAccessController {
	
	@Autowired
	UserDetailsServiceInterface userservice;
	
	 
	 @Autowired
	 GroupServiceInterface groupservice;
	 
	
	
	@RequestMapping(value="/adduser", method=RequestMethod.GET)
	public String getUser(Model model)
	{
		if (!model.containsAttribute("asset")) 
			model.addAttribute("user", new UserDetail());
		else
			model.addAttribute("user",  model.asMap().get("asset"));
		
		model.addAttribute("org.springframework.validation.BindingResult.user",
				      model.asMap().get("error"));
		model.addAttribute("groupList", groupservice.getallgroups());
		return "addUser";
		
	}
	
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public String addUser(@Valid UserDetail user, BindingResult result,@RequestParam(value = "image", required = false) MultipartFile Image, Model model, Locale locale, RedirectAttributes att) throws IllegalStateException, IOException
	{
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(result.hasErrors())
		{
			att.addFlashAttribute("asset", user);
			att.addFlashAttribute("error", result);
			return "redirect:/usermanagement/adduser";
		}
		
		if(!Image.isEmpty()){
	    	String imageName = String.valueOf(System.currentTimeMillis());
	    	if(uploadImage(Image, att, imageName)){
	    		 att.addFlashAttribute("asset", user);
	    		return "redirect:/usermanagement/adduser";
	    	}
	    	user.setUserImage(imageName);
    	}
	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		try{
			userservice.saveUser(user);
			att.addFlashAttribute("success", "User Added Successfully");
			 return "redirect:/usermanagement/adduser";
		}catch (Exception e) {
			model.addAttribute("fail",  "User Name or Email Already Exists");
			model.addAttribute("user", user);
			model.addAttribute("groupList", groupservice.getallgroups());
			return "addUser";
		
		}
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String viewUsers(Model model)
	{
		model.addAttribute("UserList", userservice.getallusers());
		return "viewUsers";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editUser(@PathVariable("id") Long id,Model model)
	{
		UserDetail user= userservice.findUser(id);
		model.addAttribute("user", user);
		model.addAttribute("groupList", groupservice.getallgroups());
		model.addAttribute("edit", true);
		return "addUser";
		
		
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateUser(@Valid UserDetail user,@RequestParam(value = "image", required = false) MultipartFile Image, BindingResult result, Locale locale,Model model,RedirectAttributes attr)
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(result.hasErrors()){
			attr.addFlashAttribute("asset", user);
			attr.addFlashAttribute("error", result);
			return "redirect:/usermanagement/adduser";
		}
		if(!Image.isEmpty()){
	    	String imageName = String.valueOf(System.currentTimeMillis());
	    	if(uploadImage(Image, attr, imageName)){
	    		return "redirect:/usermanagement/adduser";
	    	}
	    	user.setUserImage(imageName);
    	}
		if(!user.getPassword().equals(userservice.findUser(user.getId()).getPassword())){
			if(!passwordEncoder.matches(user.getPassword(), userservice.findUser(user.getId()).getPassword()))
				user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if(!userservice.updateUser(user).equals(null)){
				model.addAttribute("success", "User Updated Successfully");
				model.addAttribute("UserList", userservice.getallusers());
			}
			else{
				attr.addFlashAttribute("fail", "User Name already Exists");
				attr.addFlashAttribute("asset", user);
				 return "redirect:/usermanagement/adduser";
			}
        model.addAttribute("groupList", groupservice.getallgroups());
		return "viewUsers";
		
		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id,Model model,Locale locale)
	{
		
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  
		 UserDetail user= userservice.loadUserByUsername(userDetails.getUsername());
		if(user.getId().equals(id))
		{
			 model.addAttribute("fail", "Cannot Delete Logged In User"); 
		     model.addAttribute("UserList", userservice.getallusers());
		     return "viewUsers";
		}
		if(userservice.removeUser(id).equals("success"))
			 model.addAttribute("success", "User Deleted Successfully");
		else
			 model.addAttribute("fail", "Delete Fail");

		model.addAttribute("UserList", userservice.getallusers());
		return "viewUsers";
		
		
	}
	
	 public boolean uploadImage(MultipartFile pimage,RedirectAttributes redirectAttributes,String imageName){
	    	if(!pimage.isEmpty()){
	 		    if(!new File(Constants.PATH).exists());
		   		   new File(Constants.PATH).mkdirs();
		   		   
	    	   if(!checkExtension(pimage.getContentType())){
	    		   	redirectAttributes.addFlashAttribute("fail","Allowed Image Type (png/jpg,jpeg/gif)");
	    		   	return true;
	    		   }	
	    	   }
		    	try {
					pimage.transferTo(new File(Constants.PATH +imageName+"."+FilenameUtils.getExtension(pimage.getOriginalFilename())));
	
				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("fail", "Image Cannot be uploaded");
					return true;
				}	
		    return false;	
	    }
	   public Boolean checkExtension(String extension)
		{
			if(extension.equals("image/gif"))
			return true;
			else if (extension.equals("image/jpg"))
				return true;
			else if (extension.equals("image/png"))
			return true;
			else if (extension.equals("image/jpeg"))
				return true;
			
			else
				return false;
		}
	
	
	

}
