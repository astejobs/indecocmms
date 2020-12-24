package com.aste.lsme.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Civil;
import com.aste.lsme.domain.CivilLevel3;
import com.aste.lsme.domain.CivilLevel4;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.CivilLevel3Service;
import com.aste.lsme.service.CivilLevel4Service;
import com.aste.lsme.service.CivilService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;

@Controller
@RequestMapping(value = "/civil")
public class CivilController {
	
/*
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;

	Long records=0L;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	AssetSubtypeService assetSubService;
	
	@Autowired
	CivilLevel3Service civilLevel3Service;
	
	@Autowired
	CivilLevel4Service civilLevel4Service;
	
	@Autowired
	CivilService civilService;

	@Autowired
	BuildingService buildingService;
	
	@Autowired
	LocationService locService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String create_Civil(Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		
		if(!model.asMap().containsKey("civilEquipment"))
		{
			model.addAttribute("civilEquipment", new Civil());
		}
		
		if(model.asMap().containsKey("result")){
			model.addAttribute(
					"org.springframework.validation.BindingResult.civilEquipment",
					model.asMap().get("result"));
		}
		
		model.addAttribute("frequencies", Constants.frequency);
		
		model.addAttribute("buildingList",buildingService.getAll());
		
		model.addAttribute("assetSubType",assetSubService.getEquipmentSubTypeList(Constants.CIVILSUBSYSTEM));
	    
		return "civilCreate";
	}

	
	@RequestMapping(value="/civilLevel3/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<CivilLevel3> getCivilLevel3(@PathVariable("id")  long id,HttpSession  session){
		
	
		List<CivilLevel3> l = civilLevel3Service.getCivilLevel3(id);
		System.err.println(l.size());
		return l;
					
	}
	
	@RequestMapping(value="/civilLevel4/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<CivilLevel4> getCivilLevel4(@PathVariable("id")  long id,HttpSession  session){
		
		//Workspace w = (Workspace) session.getAttribute("workspace");
		return civilLevel4Service.getAll(id);
		
	}
	
	
	@RequestMapping(value="/locations/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<Location> getLocation(@PathVariable("id")  long id,HttpSession  session)
	{
		
		//Workspace w = (Workspace) session.getAttribute("workspace");
		return locService.getBuildingLocations(id);
		
	}
	
	
	@RequestMapping(value = "/addCivil", method = RequestMethod.POST)
	public String addCivilEquipment( @Valid@ModelAttribute("civilEquipment") Civil civilEquipment,
		    BindingResult result,
			Model model,
			HttpSession session,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "equipimage", required = false) MultipartFile equip_image,
			@RequestParam(value = "draImage", required = false) MultipartFile drawing_Image) throws IOException, WriterException 
	
	{
		
		if(civilEquipment.getAssetNo().isEmpty())
		{
			civilEquipment.setAssetNo(CommonMethods.randomString(Constants.CIVILSUBSYSTEM));
		}
		
		
		
		
		Workspace w=(Workspace)session.getAttribute("workspace");
		
		if (result.hasErrors()) 
		  {

			redirectAttributes.addFlashAttribute("civilEquipment",civilEquipment);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("msg","please fill out all the fields correctly");
			return "redirect:/civil";
		  }
				
		if (!drawing_Image.isEmpty()) 
		{   
			if(!checkExtension(drawing_Image.getContentType())){
				redirectAttributes.addFlashAttribute("civilEquipment",civilEquipment);
			   	redirectAttributes.addFlashAttribute("fail","Allowed Image Type (png/jpg/gif)");
			   	return "redirect:/civil";
			   }
			
			createImage(civilEquipment, drawing_Image, result,true);
		}
		
		if (!equip_image.isEmpty()) {
			
			if(!checkExtension(equip_image.getContentType())){
				redirectAttributes.addFlashAttribute("civilEquipment",civilEquipment);
			   	redirectAttributes.addFlashAttribute("fail","Allowed Image Type (png/jpg/gif)");
			   	return "redirect:/civil";
			   }
			createImage(civilEquipment, equip_image, result,false);
		}
		
		civilEquipment.setEquipmentCode(CommonMethods.randomString(Constants.CIVILSUBSYSTEM));
		
		QrCodeGenerator.generateQrcode(civilEquipment.getEquipmentCode());
		
		   for(int i=0;i<civilEquipment.getBaseline().size();i++)
           {
        	   civilEquipment.getBaseline().get(i).setEquipment(civilEquipment);
           }
		
		   civilEquipment.setWorkspace(w);
	     if (civilService.add(civilEquipment, w))
	      {
			redirectAttributes.addFlashAttribute("success","Civil Added successfully");
			return "redirect:/civil";
	      }
		else{
			redirectAttributes.addFlashAttribute("fail", "Civil already exists");
			redirectAttributes.addFlashAttribute("civilEquipment",civilEquipment);
			return "redirect:/civil";
		}
	    		
	}
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit_Civil(Model model,@PathVariable("id") Long id)
	{
		
		Civil equipment=null;
		if (model.asMap().containsKey("result"))
			model.addAttribute(
					"org.springframework.validation.BindingResult.equipment",
					model.asMap().get("result"));
		else {
			equipment=civilService.find(id);
			model.addAttribute("equipment", equipment);
		}
		
        model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList",buildingService.getAll());
		try
		{
		model.addAttribute("assetSubType",assetSubService.getEquipmentSubTypeList(Constants.CIVILSUBSYSTEM));
		model.addAttribute("locations", locService.getLocationList(((Civil) model.asMap().get("equipment")).getBuilding().getId()));
		model.addAttribute("level3",civilLevel3Service.getCivilLevel3(equipment.getAssetSubtype().getId()));
		model.addAttribute("level4", civilLevel4Service.getAll(equipment.getCivilLevel3().getId()));
	
		}catch(Exception e)
		{
			return "editCivil";
		}
		return "editCivil";
		
	}
	
	
	@RequestMapping(value = "/update" ,method = RequestMethod.POST)
	public String update(@Valid@ModelAttribute("equipment") Civil equipment,
			final BindingResult result,
			Model model,
			HttpSession session,
			HttpServletRequest request,
			final RedirectAttributes redirectAttributes,
			@RequestParam(value = "temp_image", required = false) MultipartFile equip_image,
			@RequestParam(value = "temp_drawingImage", required = false) MultipartFile drawing_Image) 
	{
		
		if(equipment.getAssetNo().isEmpty())
		{
			equipment.setAssetNo(CommonMethods.randomString(Constants.CIVILSUBSYSTEM));
		}
		
		
		
        Workspace w =(Workspace)session.getAttribute("workspace");
		if (result.hasErrors()) 
		  {
			System.out.println("============ERRORS============"+result.getAllErrors());
			redirectAttributes.addFlashAttribute("equipment",equipment);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("msg","please fill out all the fields correctly");
			System.out.println("please fill out all the fields correctly+++++++++++++++++++++");
			return "redirect:/civil/edit/"+equipment.getId();
		  }
				
		if (!drawing_Image.isEmpty()) 
		{   
			if(!checkExtension(drawing_Image.getContentType())){
				redirectAttributes.addFlashAttribute("equipment",equipment);
			   	redirectAttributes.addFlashAttribute("fail","Allowed Image Type (png/jpg/gif)");
			   	return "redirect:/civil/edit/"+ equipment.getId();
			   }
			
			
			createImage(equipment, drawing_Image, result,true);
		}
		
			if (!equip_image.isEmpty()) 
			{
				
				if(!checkExtension(equip_image.getContentType())){
					redirectAttributes.addFlashAttribute("equipment",equipment);
				   	redirectAttributes.addFlashAttribute("fail","Allowed Image Type (png/jpg/gif)");
				   	return "redirect:/civil/edit/"+ equipment.getId();
				   }
			
				createImage(equipment, equip_image, result,false);
			}
		
		if(!civilLevel4Service.getAll(equipment.getCivilLevel3().getId()).isEmpty())
		{
			if(equipment.getCivilLevel4() == null)
			{
			
				redirectAttributes.addFlashAttribute("result", result);
				redirectAttributes.addFlashAttribute("civilEquipment",equipment);
				redirectAttributes.addFlashAttribute("msg", "Please Select Civil Level 4");
				return "redirect:/civil/edit/"+ equipment.getId();
			}
				else
				{
					for(int i=0;i<equipment.getBaseline().size();i++)
					{
						equipment.getBaseline().get(i).setEquipment(equipment);
					}
		    equipment.setWorkspace(w);
			if(civilService.update(equipment, w))
			{
			redirectAttributes.addFlashAttribute("success", "Civil Updated successfully");
			return "redirect:/civil/search";
			}
			
			else{
				redirectAttributes.addFlashAttribute("fail", "Civil Already Exists");
				return "redirect:/civil/search";
				}
			
				}
		}
		
		return "redirect:/civil/edit/"+ equipment.getId();
		
	}
	
	
	

	public static Boolean checkExtension(String extension)
	{
		if(extension.equals("image/gif"))
		return true;
		else if (extension.equals("image/jpg"))
			return true;
		else if (extension.equals("image/png"))
		return true;
		else
			return false;
	}
	
	private void createImage(Civil equipment,MultipartFile image, BindingResult result,boolean check)
	{
		if(check)
		{
			equipment.setDrawingImage(image.getOriginalFilename());
			equipmentService.removeImage(equipment.getDrawingImage(), Constants.PATH);
			String name = "drawing" + CommonMethods.randomString(Constants.CIVILSUBSYSTEM) + "."
					+ image.getContentType().split("/")[1];
			if (equipmentService.saveImage(image, name, Constants.PATH))
				equipment.setDrawingImage(name);
			else
				result.rejectValue("drawingImage", "select valid image");
		}
		else
		{
			equipment.setImage(image.getOriginalFilename());
			equipmentService.removeImage(equipment.getImage(), Constants.PATH);
			String name = "image" + CommonMethods.randomString(Constants.CIVILSUBSYSTEM) + "."
					+ image.getContentType().split("/")[1];
			if (equipmentService.saveImage(image, name, Constants.PATH))
				equipment.setImage(name);
			else
				result.rejectValue("image", "select valid image");
		}
	}
	
	
	@RequestMapping(value = "/getimage/{imageName:.+}", method = RequestMethod.GET)
	public void getFaultReportImage(
			@PathVariable("imageName") String imageName, Model model,
			HttpServletRequest req, HttpServletResponse rep) {
		try {
			InputStream is = new FileInputStream(Constants.PATH + imageName);
            byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(equipmentService.getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}
	}
	
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String editCivil(Model model) 
	{
		model.addAttribute("equipmentSearch",new EquipmentSearchCriteria());
        model.addAttribute("buildingList",buildingService.getAll());
		model.addAttribute("assetSubType",assetSubService.getEquipmentSubTypeList(Constants.CIVILSUBSYSTEM));
	    return "civilSearch";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchCivil(@ModelAttribute("equipmentSearch") EquipmentSearchCriteria esc,Model model,HttpSession session,RedirectAttributes redirectAttributes) 
	{
		

		
		Workspace w =(Workspace) session.getAttribute("workspace");
		int page=1;
		esc.setWorkspace(w);
		session.setAttribute("EquipmentSearchList", esc);
		pagination(page, model, esc,w);
		
		model.addAttribute("equipmentSearch", esc);
		model.addAttribute("buildingList",buildingService.getAll());
		model.addAttribute("assetSubType",assetSubService.getEquipmentSubTypeList(Constants.CIVILSUBSYSTEM));
		try{
		model.addAttribute("locationList",locService.getLocationList(esc.getBuilding().getId()));
		}catch(Exception e)
		{
			return "civilSearch";		
		}
	return "civilSearch";
	}

	
	@RequestMapping(value="/delete/{id}")
	public String deleteCivil(@PathVariable("id")Long id,final RedirectAttributes redirectAttributes)
	{
			Civil c=civilService.find(id);
		
		if(c.equals("null"))
		{
			
			redirectAttributes.addFlashAttribute("fail","Sorry! coudn't Delete");
			return "redirect:/civil";
		}
		else
		{
			civilService.delete(c);	
			redirectAttributes.addFlashAttribute("success","Deleted Successfully");
			return "redirect:/civil/search";
		}
		
	}
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		try
		{
		if(session.getAttribute("workspace").equals(null) || session.getAttribute("workspace")==null)
		{}
		}catch(NullPointerException e){
			redirectAttributes.addFlashAttribute("fail","please Select a workspace");
			return "redirect:/dashboard/";
		}
		
		Workspace w =(Workspace) session.getAttribute("workspace");
		EquipmentSearchCriteria esc = (EquipmentSearchCriteria) session.getAttribute("EquipmentSearchList");
		pagination(page, model,esc,w);
		model.addAttribute("equipmentSearch", esc);
		return "civilSearch";
	}
	
	public void pagination(int page,Model model,EquipmentSearchCriteria esc,Workspace w){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) civilService.getCount(w,esc);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("listSearch",civilService.getSearchList(esc,from,w));
		
	}
	
	*/
}