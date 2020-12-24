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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentBaseline;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Fire;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.FireEquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;



@Controller
@RequestMapping(value = "/fireEquipment")
public class FireController {
	
	/*int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	AssetSubtypeService assetSubTypeService;
	
	@Autowired
	FireEquipmentService fireequipmentservice;
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String get(Model model,HttpSession session) {
		
		Workspace w=(Workspace) session.getAttribute("workspace");
		
		
		if(!model.asMap().containsKey("fireequipment")){
			model.addAttribute("fireequipment",new Fire());

		}	
			
		if (model.asMap().containsKey("error")){
		
			model.addAttribute(
					"org.springframework.validation.BindingResult.fireequipment",
					model.asMap().get("error"));
		}
		else {	
			getdata(model, w);
		    }
		
	
		return "firecreate";
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("fireequipment") Fire fireequipment,Errors errors, 
			 BindingResult result,@RequestParam(value="drawing_Image", required = false) MultipartFile image, Model model,
			HttpSession session,RedirectAttributes redirectAttributes) throws IOException {

		Workspace w=(Workspace) session.getAttribute("workspace");
		fireequipment.setWorkspace(w);
		
		if(result.hasErrors()){
			System.out.println(result.toString());
		
			model.addAttribute("fail","Enter fields correctly");
			model.addAttribute("fireequipment", fireequipment);
			
			 if(fireequipment.getBuilding()!=null) {
				 model.addAttribute("locationList",locationService.getLocationList(fireequipment.getBuilding().getId()));
			 }
			
			 getdata(model, w);
		
				return "firecreate";
				}
	
		try
		{
			
				if(!image.isEmpty()){
							
									String imageName=CommonMethods.randomString(Constants.FIRESUBSYSTEM);
									if(QrCodeGenerator.uploadImage(image, redirectAttributes, imageName)){
										fireequipment.setDrawingImage(imageName);
									}else{
										return "redirect:/fireEquipment/add";
									}
						}
			
						String equipmentCode=Constants.FIRESUBSYSTEM+System.currentTimeMillis();
			    		fireequipment.setEquipmentCode(equipmentCode);
			    		QrCodeGenerator.generateQrcode(equipmentCode);
			    		
			    		if(fireequipment.getAssetNo().isEmpty()){
			    				String assetnum=Constants.FIRESUBSYSTEM+System.currentTimeMillis();
					    		fireequipment.setAssetNo(assetnum);
					    		QrCodeGenerator.generateQrcode(assetnum);
			    			 
			    		}
			    			
			    		 if(fireequipment.getBuilding()!=null) {
			    			 model.addAttribute("locationList",locationService.getLocationList(fireequipment.getBuilding().getId()));
						 }
			    		 
		}catch(Exception e){
			
			return  "redirect:/fireEquipment/add";
		}
		
		 if(fireequipment.getBaseline()!=null){	
				for(EquipmentBaseline e:fireequipment.getBaseline()){
					e.setEquipment(fireequipment);	
				}
				
			}
		
		 if(fireequipmentservice.persist(fireequipment)){
				redirectAttributes.addFlashAttribute("success","Equipment saved successfully");
				
		 	}
		else{
			  if(fireequipment.getBuilding()!=null) {
				  redirectAttributes.addFlashAttribute("locationList",locationService.getLocationList(fireequipment.getBuilding().getId()));
				 }
			  redirectAttributes.addFlashAttribute("fireequipment", fireequipment);	
			  redirectAttributes.addFlashAttribute("fail", "Equipment already exists");
			}
		 return "redirect:/fireEquipment/add";
		
	}
		

@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
public String edit(@PathVariable Long id,Model model,HttpSession session,RedirectAttributes redirectAttributes)
{
	
	Workspace w = (Workspace) session.getAttribute("workspace");
	Fire  fireequipment=fireequipmentservice.get(id);
	model.addAttribute("fireequipment", fireequipment);
	 
	if(fireequipment.getBuilding()!=null) {
		   model.addAttribute("locationList",locationService.getLocationList(fireequipment.getBuilding().getId()));
		 }
	 
	getdata(model, w);
	model.addAttribute("proplist",fireequipment.getBaseline());

	return "fireedit";

}



@RequestMapping(value="/update",method=RequestMethod.POST)
public String update(@Valid @ModelAttribute("fireequipment")Fire fireequipment,Errors errors,
		final RedirectAttributes redirectAttributes, BindingResult result,HttpSession session,
		@RequestParam(value="drawing_Image", required = false) MultipartFile draw,@RequestParam("drawingImage")String name,Model model)
      {
	
	    Workspace w = (Workspace) session.getAttribute("workspace"); 
	    fireequipment.setWorkspace(w);
	  	   
	    Fire temp=	fireequipmentservice.get(fireequipment.getId());
		fireequipment.setDrawingImage(temp.getDrawingImage());
		
		if(result.hasErrors()){
				    model.addAttribute("fail","Enter fields correctly");
					model.addAttribute("fireequipment", fireequipment);
			
					 if(fireequipment.getBuilding()!=null){
						 model.addAttribute("locationList",locationService.getLocationList(fireequipment.getBuilding().getId()));
					 }
			 
					 getdata(model, w);
		
			return "fireedit";
		}
	
	     
	     try{
	    
	    	 if(!draw.isEmpty()){
	    		 
	    		     if(name.isEmpty()){
	    		    	 	String imageName=CommonMethods.randomString(Constants.FIRESUBSYSTEM);
							if(QrCodeGenerator.uploadImage(draw, redirectAttributes, imageName)){
										fireequipment.setDrawingImage(imageName);
								}
							else{
									return "redirect:/fireEquipment/update/"+fireequipment.getId();
								}
	    		     }
	    		     else{
							QrCodeGenerator.uploadImage(draw, redirectAttributes, name);
							return "redirect:/fireEquipment/update/"+fireequipment.getId();
	    		     }			
				}
	    	 	
			}
		
			catch(Exception e){
				return "redirect:/fireEquipment/update/"+fireequipment.getId();
				
			}
	     
	
	        List<EquipmentBaseline> eq=fireequipment.getBaseline();
			if(fireequipment.getBaseline()!=null){
				
					for(EquipmentBaseline e:eq){
						e.setEquipment(fireequipment);
					}
			}
			
			 if(fireequipmentservice.update(fireequipment)){
	    		 redirectAttributes.addFlashAttribute("fireequipment", fireequipment);
		    	 redirectAttributes.addFlashAttribute("success","Equipment Updated Succesfully......");
	    	 }
			
	    	 else{
		    	 	redirectAttributes.addFlashAttribute("fail","Equipment already exists");
	    	 }

	return "redirect:/fireEquipment/update/"+fireequipment.getId();
	
}


@RequestMapping(value = "/search", method = RequestMethod.GET)
public String fireList(Model model,HttpSession session,RedirectAttributes redirectAttributes) {
	
    Workspace w = (Workspace) session.getAttribute("workspace");
    System.out.println(w.getId());
	getdata(model, w);
    
      if (model.asMap().containsKey("error"))
	           model.addAttribute("org.springframework.validation.BindingResult.fireEquipment",model.asMap().get("error"));

      else {
				EquipmentSearchCriteria fireSearch=new EquipmentSearchCriteria();
				fireSearch.setWorkspace(w);
				model.addAttribute("fireSearch",fireSearch);
      		}
	
	return "fireview";
}



@RequestMapping(value="/search",method=RequestMethod.POST)
public String searchCriteria(@ModelAttribute("fireSearch")EquipmentSearchCriteria fireSearch,Model model,HttpSession session,RedirectAttributes redirectAttributes)
{
	
	int page=0;
	Workspace w = (Workspace) session.getAttribute("workspace");
	
	session.setAttribute("firesession", fireSearch);
	pagination(page, model,fireSearch,w);
	
	List<Fire> list=fireequipmentservice.getSearchList(page,fireSearch,w);
	if(list.isEmpty()){
			model.addAttribute("fail", "No Record found");
		}
	else{
			model.addAttribute("listSearch", list);
		}
	
	getdata(model, w);
	return "fireview";
	
}


@RequestMapping(value="/delete/{id}")
public String deleteAcmv(@PathVariable("id")Long id,final RedirectAttributes redirectAttributes)
	{

		Fire fire=	fireequipmentservice.get(id);
		if(fire.equals("null")){
					redirectAttributes.addFlashAttribute("fail","Deletion Failed");
					return "redirect:/fireEquipment/search";
				}
		else{
					fireequipmentservice.delete(fire);
					redirectAttributes.addFlashAttribute("success","Deleted Equipment Successfully");
					return "redirect:/fireEquipment/search";
				}

	}


	public void pagination(int page,Model model,EquipmentSearchCriteria fireSearch,Workspace w)
	{
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long)fireequipmentservice.count(fireSearch,w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("listSearch", fireequipmentservice.getSearchList(from,fireSearch,w));
	}

	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
	
		Workspace w = (Workspace) session.getAttribute("workspace");
		EquipmentSearchCriteria fireSearch=(EquipmentSearchCriteria) session.getAttribute("firesession");

		pagination(page, model,fireSearch,w);
		getdata(model, w);
		
		model.addAttribute("locationList",locationService.getAll());
		model.addAttribute("fireSearch",fireSearch );
	
		return "fireview";
}

	
	@RequestMapping(value="/locationlist/{bId}")
	@ResponseBody
	public List<Location> getLocationList(@PathVariable Long bId)
		{
			List<Location> list=locationService.getLocationList(bId);
			return list;
	
		}

	public void getdata(Model model,Workspace w){
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList", buildingService.getWorkspaceBuildings(w));
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.FIRESUBSYSTEM));
		model.addAttribute("et",Constants.FIRESUBSYSTEM );
	}
	
	@RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
	public void getImage(@PathVariable("imageName")String imageName,Model model,
			HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
	{
		try {
				InputStream is = new FileInputStream(Constants.PATH + imageName);
				byte[] bytes = IOUtils.toByteArray(is);
				rep.setContentType(getContentType(imageName));
				OutputStream os = rep.getOutputStream();
				os.write(bytes);
				os.close();
				is.close();
		}catch (Exception e) {
		
		}
	}


	public static String getContentType(String imageName) {
		if (imageName.contains(".pdf"))
			return "application/pdf";
		else if (imageName.contains(".dwg"))
			return "image/vnd.dwg";
		else if (imageName.contains(".gif"))
			return "image/gif";
		else
			return "image/jpeg";
	}

*/
}


