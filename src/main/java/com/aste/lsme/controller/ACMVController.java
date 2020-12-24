package com.aste.lsme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.aste.lsme.domain.ACMV;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentBaseline;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AcmvServiceInterface;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;




@Controller
@RequestMapping(value="/acmv")
public class ACMVController {
	
	/*@Autowired
	AcmvServiceInterface acmvServiceInterface;
	
	@Autowired
	BuildingService buildingServiceInterface;
	
	@Autowired
	AssetSubtypeService assetSubTypeService;
	
	@Autowired
	LocationService locationServiceInterface;
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String view(Model model, HttpSession session)
	{
		Workspace w = (Workspace) session.getAttribute("workspace");
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList",
				buildingServiceInterface.getWorkspaceBuildings(w));
		
		model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
		
		
	model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
	if (model.asMap().containsKey("error"))
		model.addAttribute(
				"org.springframework.validation.BindingResult.acmvEquipment",
				model.asMap().get("error"));
	else {
		ACMV acmv = new ACMV();
		acmv.setWorkspace(w);
		model.addAttribute("acmv",acmv);
	
	}
		
		return "acmvcreate";
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String  add(@Valid @ModelAttribute("acmv") ACMV acmv,Errors errors,Model model,
						final RedirectAttributes redirectAttributes,HttpSession session,
						@RequestParam(value = "acmvimage", required = false) MultipartFile image,
						@RequestParam(value = "drawing", required = false) MultipartFile draw
			) throws ParseException
	{
		
		
	
		
		
	
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		 acmv.setWorkspace(w);
		
	
		if (errors.hasErrors()) 
		{
			System.out.println(errors.toString()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			model.addAttribute("fail","Acmv cannot be saved.Please enter data properly");
			model.addAttribute("acmv", acmv);
			 if(acmv.getBuilding()!=null)
			 {
			   
			    model.addAttribute("locationList",locationServiceInterface.getBuildingLocations(acmv.getBuilding().getId()));
			 }
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList",
					buildingServiceInterface.getWorkspaceBuildings(w));
			model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
			
			model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
		
		
			return "acmvcreate";
			}
		try
		{
			System.out.println(image.getContentType()+"888888888888");
			if(!image.isEmpty())
			{
			if(checkExtention(image.getContentType()))
			{
				String imageName=uploadImage(acmv,image,errors);
				if(!imageName.isEmpty())
					acmv.setImage(imageName);
			}
			}
			if(!draw.isEmpty())
			{
				if(checkExtention(draw.getContentType()))
				{
				String drawingName=uploadImage(acmv,draw,errors);
				if(!drawingName.isEmpty())
					acmv.setDrawingImage(drawingName);
				}
				}
			    
			   //acmv.setEquipmentCode(acmvServiceInterface.generateEquipmentCode(Constants.ACMVSUBSYSTEM));
			   String equipmentCode=CommonMethods.randomString(Constants.ACMVSUBSYSTEM);
			    		  acmv.setEquipmentCode(equipmentCode);
			    		  if(acmv.getAssetNo()==null||acmv.getAssetNo().isEmpty())
			    		  {
			    			  String assetCode=CommonMethods.randomString(Constants.ACMVSUBSYSTEM);
			    			  acmv.setAssetNo(assetCode);
			    		  }
			    		QrCodeGenerator.generateQrcode(equipmentCode);
			               for(int i=0;i<acmv.getBaseline().size();i++)
			               {
			            	   acmv.getBaseline().get(i).setEquipment(acmv);
			               }
			    
			              
				acmvServiceInterface.save(acmv);
				redirectAttributes.addFlashAttribute("acmv", acmv);
				redirectAttributes.addFlashAttribute("success","Acmv added successfully");
			
				
			
	
		
		}
		catch(Exception e)
		{
			
			redirectAttributes.addFlashAttribute("fail","Acmv cannot be saved.Please enter data properly");
			
			return  "redirect:/acmv/add";
		}
		return "redirect:/acmv/add";
	}
	@RequestMapping(value="/locationlist/{bId}")
	@ResponseBody
	public List<Location>  getLocationList(@PathVariable Long bId)
	{
	
		
		List<Location> list=locationServiceInterface.getLocationList(bId);
		
		return list;
		
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable Long id,Model model,HttpSession session)
	{
		  Workspace w=(Workspace) session.getAttribute("workspace");
		ACMV  acmv=acmvServiceInterface.find(id);
		
		model.addAttribute("acmv", acmv);
		 if(acmv.getBuilding()!=null)
		 {
		    Building build=buildingServiceInterface.get(acmv.getBuilding().getId());
		    model.addAttribute("locationList",locationServiceInterface.getBuildingLocations(acmv.getBuilding().getId()));
		 }
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList",
				buildingServiceInterface.getWorkspaceBuildings(w));
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
		
		model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
		
		model.addAttribute("edit",true);
		
		model.addAttribute("proplist",acmv.getBaseline());
		List<EquipmentBaseline> eq=acmv.getBaseline();
		for(EquipmentBaseline e:eq)
		{
			System.out.println(e.getMax()+"---------------------heree-------------------------------------------");
		}
	
		return "acmvEdit";
		
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("acmv")ACMV acmv,Errors errors,	final RedirectAttributes redirectAttributes,
			@RequestParam(value = "acmvimage", required = false) MultipartFile image,
			@RequestParam(value = "drawing", required = false) MultipartFile draw,Model model,
			@RequestParam(value="id")Long id,HttpSession session)
	{
		
		
		acmv.setId(id);
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		 acmv.setWorkspace(w);
		
		
	ACMV temp=	acmvServiceInterface.find(id);
	  List<EquipmentBaseline> listtemp =temp.getBaseline();
	  for(int i=0;i<temp.getBaseline().size();i++)
      {
   	   temp.getBaseline().get(i).setEquipment(null);
      }
	    acmvServiceInterface.update(temp);
	
		acmv.setImage(temp.getImage());
		acmv.setDrawingImage(temp.getDrawingImage());
		
		if (errors.hasErrors()) 
		{
			System.out.println(errors.toString()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			model.addAttribute("fail","Please enter data properly");
			model.addAttribute("acmv", acmv);
			 if(acmv.getBuilding()!=null)
			 {
			    Building build=buildingServiceInterface.get(acmv.getBuilding().getId());
			    model.addAttribute("locationList",locationServiceInterface.getBuildingLocations(acmv.getBuilding().getId()));
			 }
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList",
					buildingServiceInterface.getWorkspaceBuildings(w));
			model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
			
			model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
		
		
			return "acmvEdit";
			}
		try
		{
		if(!image.isEmpty())
		{
		if(checkExtention(image.getContentType()))
		{
			String imageName=uploadImage(acmv,image,errors);
			if(!imageName.isEmpty())
				acmv.setImage(imageName);
		}
		
		}
		if(!draw.isEmpty())
		{
			if(checkExtention(draw.getContentType()))
			{
			String drawingName=uploadImage(acmv,draw,errors);
			if(!drawingName.isEmpty())
				acmv.setDrawingImage(drawingName);
			}
			
			}
		
		for(int i=0;i<acmv.getBaseline().size();i++)
        {
     	   acmv.getBaseline().get(i).setEquipment(acmv);
        }
		if(acmv.getAssetNo()==null||acmv.getAssetNo().isEmpty())
		  {
			  String assetCode=CommonMethods.randomString(Constants.ACMVSUBSYSTEM);
			  acmv.setAssetNo(assetCode);
		  }
		acmvServiceInterface.update(acmv);
		redirectAttributes.addFlashAttribute("acmv", acmv);
		redirectAttributes.addFlashAttribute("success","Acmv updated successfully");
		}
	
		catch(Exception e)
		{
			
		}
		
		
		
		
		
		
		
		return "redirect:/acmv/search";
		
	}
	
	
	
	
	
	public Boolean removeImage(String imageName) {

		File f = new File(Constants.PATH + imageName);
		if (f.exists()) {
			f.delete();
			return true;
		}
		return false;
	}
	
	public  String uploadImage(ACMV acmv,MultipartFile image,Errors errors)
	{

		String imageName=CommonMethods.randomString(Constants.ACMVSUBSYSTEM);
		System.out.println(image.getContentType()+"__");
		if(saveImageToPath(image,imageName)){
			System.out.println("*********************");
			return imageName;
			}
		else
		{
			errors.getFieldError("Failed to upload Image");
		   return "redirect:/acmv/add";
		}
	}
	public boolean saveImageToPath(MultipartFile image,String imageName)
	{
		try {
			
			FileUtils.writeByteArrayToFile(new File(Constants.PATH + imageName),
					image.getBytes());

		} catch (Exception e) {
               
			System.out.println("taha");
			return false;
		}
		return true;
		
	}

	
	

	
	
	
	private Boolean checkExtention(String extension)
	{
		if(extension.equals("image/jpeg"))
		return true;
		else if (extension.equals("image/jpg"))
			return true;
		else if (extension.equals("image/png"))
		return true;
		else
			
			return false;
		
	}
	
	
	public String  imageFailed(ACMV acmv,Model model)
	{
		//System.out.println(errors.toString()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		 Workspace w=acmv.getWorkspace();
		model.addAttribute("fail","Failed to upload image");
		model.addAttribute("acmv", acmv);
		 if(acmv.getBuilding()!=null)
		 {
		    Building build=buildingServiceInterface.get(acmv.getBuilding().getId());
		    model.addAttribute("locationList",locationServiceInterface.getBuildingLocations(acmv.getBuilding().getId()));
		 }
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList",
				buildingServiceInterface.getWorkspaceBuildings(w));
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
		
		model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
		return  "acmvcreate";
		
	}
	
	@RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
	public void getImage(@PathVariable("imageName")String imageName,Model model,
			HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
	{
		System.out.println("--------------------------------------------------here----------------------------");
		//QrCodeGenerator.getImage(imageName);
		try {
			InputStream is = new FileInputStream(Constants.PATH + imageName);

			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}
		
	}
		@RequestMapping(value="/search",method=RequestMethod.GET)
		public String search(Model model,HttpSession session)
		{
			 Workspace w=(Workspace) session.getAttribute("workspace");
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList",
					buildingServiceInterface.getWorkspaceBuildings(w));
			
			model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
			
			
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
		if (model.asMap().containsKey("error"))
			model.addAttribute(
					"org.springframework.validation.BindingResult.acmvEquipment",
					model.asMap().get("error"));
		else {
			EquipmentSearchCriteria acmvSearch=new EquipmentSearchCriteria();
			//acmv.setWorkspace(w);
			model.addAttribute("acmvSearch",acmvSearch);
		}
			return "acmvSearch";
			
		}
		
		
		@RequestMapping(value="/search",method=RequestMethod.POST)
		public String searchCriteria(@ModelAttribute("acmvSearch")EquipmentSearchCriteria acmvSearch,Model model,HttpSession session)
		{
			Workspace w = (Workspace) session.getAttribute("workspace");
			if(w!=null)
	         acmvSearch.setWorkspace(w);
			else
				return "redirect:/logout";
			Long count=acmvServiceInterface.getAcmvCount(w);
			
			
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList",
					buildingServiceInterface.getWorkspaceBuildings(w));
			
		int page=0;
		page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = count;
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			
			model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
			List<ACMV> list=acmvServiceInterface.getSearchList(from,acmvSearch);
			model.addAttribute("listSearch", list);
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
			model.addAttribute("acmvSearch", acmvSearch);
		  session.setAttribute("acmvSearchCriteria", acmvSearch);
			return "acmvSearch";
			
		}
		
		public void pagination(int page,Model model,EquipmentSearchCriteria acmvSearch,Workspace w){
			
			
			//Workspace w=(Workspace) session.getAttribute("workspace");
			acmvSearch.setWorkspace(w);
			Long countt=acmvServiceInterface.getAcmvCount(acmvSearch.getWorkspace());
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = countt;
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("listSearch",acmvServiceInterface.getSearchList(from, acmvSearch));
			
		}
		
		@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
		public String paginate(@PathVariable("page") int page,Model model,@ModelAttribute("acmvSearch")EquipmentSearchCriteria acmvSearch,HttpSession session){
		
			Workspace w=(Workspace) session.getAttribute("workspace");
			
			EquipmentSearchCriteria acmvSearch1=(EquipmentSearchCriteria) session.getAttribute("acmvSearchCriteria");
			
			 
			pagination(page, model,acmvSearch1,w);
			
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList",
					buildingServiceInterface.getWorkspaceBuildings(w));
			
			model.addAttribute("ac",Constants.ACMVSUBSYSTEM );
			
			
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.ACMVSUBSYSTEM));
			model.addAttribute("acmvSearch",acmvSearch1 );
			return "acmvSearch";
		}
		
		
		
		
	
	@RequestMapping(value="/delete/{id}")
	public String deleteAcmv(@PathVariable("id")Long id,final RedirectAttributes redirectAttributes)
	{
		
		ACMV temp=	acmvServiceInterface.find(id);
		if(temp.equals("null"))
		{
			
			redirectAttributes.addFlashAttribute("fail","Acmv cannot be deleted");
			return "redirect:/acmv/add";
		}
		else
		{
			acmvServiceInterface.delete(temp);
			
			redirectAttributes.addFlashAttribute("success","Acmv deleted successfully");
			return "redirect:/acmv/search";
		}
		
		
	}
	
	
	@RequestMapping(value="/getQr/{imageName:.+}",method=RequestMethod.GET)
	public void getQrCode(@PathVariable("imageName")String imageName,Model model,
			HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
	{
		System.out.println("--------------------------------------------------here----------------------------");
		//QrCodeGenerator.getImage(imageName);
		
		try {
			InputStream is = new FileInputStream(Constants.PATH + imageName+".png");

			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}
	}
	
	
	
	
	
	
	
	*/
	
	public static String getContentType(String imageName) {
		if (imageName.contains(".pdf"))
			return "application/pdf";
		else if (imageName.contains(".dwg"))
			return "imsage/vnd.dwg";
		else if (imageName.contains(".gif"))
			return "image/gif";
		else
			return "image/jpeg";
	}
	
	
	
	

}
