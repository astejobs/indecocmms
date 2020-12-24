package com.aste.lsme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.WorkspaceService;
import com.google.zxing.WriterException;

@Controller
@RequestMapping("/contract")
public class WorkspaceController {

		int from = 0;
		int total = 0;
		public static final int ROWS = Constants.ROWS;
		Long records = 0L;
	
	    @Autowired 
		WorkspaceService workspaceService;
	
	    @RequestMapping(value = "/add" ,method=RequestMethod.GET)
		public String get(Model model) {
			if(!model.asMap().containsKey("workspace"))
	    	    model.addAttribute("workspace", new Workspace());
			
			if(model.asMap().containsKey("result"))
				model.addAttribute("org.springframework.validation.BindingResult.workspace",model.asMap().get("result"));
	    	int page = 1;
	    	pagination(page, model);
			return "contract";
		}
	    
	    @RequestMapping(value = "/add" ,method=RequestMethod.POST)
		public String save(@Valid @ModelAttribute("workspace") Workspace workspace,BindingResult result,
						   @RequestParam(value = "pimage", required = false) MultipartFile pimage,
						   RedirectAttributes redirectAttributes) {
		
	    	if(result.hasErrors()){
	    		
	    		redirectAttributes.addFlashAttribute("workspace", workspace);
	    		redirectAttributes.addFlashAttribute("result", result);
	    		redirectAttributes.addFlashAttribute("fail","Please enter all fields properly");
				return "redirect:/contract/add";
	    	}
	    	
	    	if(!pimage.isEmpty()){
		    	String imageName = String.valueOf(System.currentTimeMillis())+"."+FilenameUtils.getExtension(pimage.getOriginalFilename());
		    	if(uploadImage(pimage, redirectAttributes, imageName)){
		    		return "redirect:/contract/add";
		    	}
		    	workspace.setProjectImage(imageName);
	    	}
	    	
	    	workspaceService.persist(workspace);
	    	redirectAttributes.addFlashAttribute("success","Contract added successfully");
			return "redirect:/contract/add";
			
		}
	    
	    @RequestMapping(value="/edit/{id}/{page}",method=RequestMethod.GET)
	    public String edit(@PathVariable("id") String id,@PathVariable("page") int page,Model model){
	    	
	    	model.addAttribute("workspace", workspaceService.getworkspaceonid(id));	
	    	model.addAttribute("edit",true);
	    	pagination(page, model);
	    	return "contract";
	    }
	    
	    @RequestMapping(value="/update",method=RequestMethod.POST)
	    public String update(@Valid @ModelAttribute("workspace") Workspace workspace,BindingResult result,
	    					 @RequestParam(value = "pimage", required = false) MultipartFile pimage,
	    					 RedirectAttributes redirectAttributes,HttpServletRequest request){
	    	int page =Integer.parseInt(request.getParameter("p"));
	    	if(result.hasErrors()){
	    		redirectAttributes.addFlashAttribute("workspace", workspace);
	    		redirectAttributes.addFlashAttribute("result", result);
	    		return "redirect:/contract/edit/"+workspace.getWorkspaceId()+"/"+page;
	    	}
	    	if(!pimage.isEmpty()){
	    		if(workspace.getProjectImage() == null)
	    	    	workspace.setProjectImage(String.valueOf(System.currentTimeMillis())+"."+FilenameUtils.getExtension(pimage.getOriginalFilename()));

	    		if(uploadImage(pimage, redirectAttributes, workspace.getProjectImage())){
		    		redirectAttributes.addFlashAttribute("workspace", workspace);
	    		   return "redirect:/contract/edit/"+workspace.getWorkspaceId()+"/"+page;
	    		}
	    	}
	    	
	    	workspaceService.update(workspace);
	    	redirectAttributes.addFlashAttribute("success","Contract updated successfully");
	    	return "redirect:/contract/pageno="+page;
	    }
	    
	    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
		public String delete(HttpServletRequest request ,RedirectAttributes redirectAttributes){
			
			String[] id = request.getParameterValues("id");
			if(id != null){
				for (int i = 0; i < id.length; i++) {
					workspaceService.delete(id[i]);
				}
			}
			else{
				redirectAttributes.addFlashAttribute("fail", "Select items to delete");
			} 
			return "redirect:/contract/add";	
		}
	    	    
	    @RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
		public String paginate(@PathVariable("page") int page,Model model){
			pagination(page, model);
			model.addAttribute("workspace", new Workspace());
			return "contract";
		}
		
		public void pagination(int page,Model model){
			
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = (long)workspaceService.getWorkspaceCount();
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("workspaceList", workspaceService.getWorkspacePaginated(from));
			
		}

	    @RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
		public void getImage(@PathVariable("imageName") String imageName,Model model,
				HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
		{

			try {
				InputStream is = new FileInputStream(Constants.PATH + imageName);

				byte[] bytes = IOUtils.toByteArray(is);
				rep.setContentType(ACMVController.getContentType(imageName));
				OutputStream os = rep.getOutputStream();
				os.write(bytes);
				os.close();
				is.close();
			} catch (Exception e) {
			}
			
		}
	    
	    public boolean uploadImage(MultipartFile pimage,RedirectAttributes redirectAttributes,String imageName){
	    	if(!pimage.isEmpty()){
	 		    if(!new File(Constants.PATH).exists());
		   		   new File(Constants.PATH).mkdirs();
		   		   
	    	   if(!checkExtension(pimage.getContentType())){
	    		   	redirectAttributes.addFlashAttribute("fail","Allowed Image Type (png/jpg/jpeg/gif)");
	    		   	return true;
	    		   }	
	    	   }
		    	try {
					pimage.transferTo(new File(Constants.PATH +imageName));
	
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
