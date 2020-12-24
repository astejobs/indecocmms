package com.aste.lsme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentBaseline;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Mechanical;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MechanicalService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;


@Controller
@RequestMapping("/mechanical")
public class MechanicalController {
	
/*	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	MechanicalService mechService;
	
	
	@Autowired
	BuildingService buildingServiceInterface;
	
	@Autowired
	AssetSubtypeService assetSubTypeService;
	
	@Autowired
	LocationService locationServiceInterface;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model,RedirectAttributes redirect,HttpSession session) {
	Workspace w = (Workspace) session.getAttribute("workspace");

	
	 
	if(!model.asMap().containsKey("mechanicalEquipment")){
		model.addAttribute("mechanicalEquipment",new Mechanical());

	}	
		
	if (model.asMap().containsKey("result")){
	   	getAttributes(model,w);
		model.addAttribute("org.springframework.validation.BindingResult.mechanicalEquipment",
				model.asMap().get("result"));
	}
	else {
		
		getAttributes(model,w);
	    }
	
		
		return "mechanicalCreate";
		
		
	}
	
	
	

	public void getAttributes(Model model,Workspace w){
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("buildingList",buildingServiceInterface.getWorkspaceBuildings(w));
		model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.MECHANICALSUBSYSTEM));
		model.addAttribute("EqType", Constants.MECHANICALSUBSYSTEM);	
	}
	

	
	@RequestMapping(method = RequestMethod.POST)
	public String saveMechanicalEqp(@Valid @ModelAttribute("mechanicalEquipment") Mechanical mechanicalEquipment, BindingResult result,RedirectAttributes redirectAttributes,
			@RequestParam(value = "equipimage", required = false) MultipartFile equipimage,
			@RequestParam(value = "drawingimage", required = false) MultipartFile draw,Model model,HttpSession session) throws IOException, WriterException {
	   
		    
           if(result.hasErrors()){	
        	   if(mechanicalEquipment.getBuilding()!=null){	
        		   redirectAttributes.addFlashAttribute("locationList",locationServiceInterface.getLocationList(mechanicalEquipment.getBuilding().getId()));
				}
		    redirectAttributes.addFlashAttribute("mechanicalEquipment",mechanicalEquipment);
		    redirectAttributes.addFlashAttribute("fail","Enter fields properly");
		    redirectAttributes.addFlashAttribute("result",result);
			return "redirect:/mechanical";
			}
		   Workspace w = (Workspace) session.getAttribute("workspace");       
		   try{
				if(!draw.isEmpty())
				{	
				   String drawingFilePath=CommonMethods.randomString(Constants.MECHANICALSUBSYSTEM);			
				   	if( QrCodeGenerator.uploadImage(draw,redirectAttributes,drawingFilePath)){		  
				    mechanicalEquipment.setDrawingImage(drawingFilePath);
				   	}else{
          		     return "redirect:/mechanical";	
				   	}	
				}
		
				if(!equipimage.isEmpty())	
				{
					String	equipFilePath=CommonMethods.randomString(Constants.MECHANICALSUBSYSTEM);
          	       	if( QrCodeGenerator.uploadImage(equipimage,redirectAttributes,equipFilePath)) {
          	    	   mechanicalEquipment.setImage(equipFilePath);
          	           }else
          	           {
          		      return "redirect:/mechanical";
          	           }
				  }
				mechanicalEquipment.setWorkspace(w);
				String equipmentCode=CommonMethods.randomString(Constants.MECHANICALSUBSYSTEM);
				QrCodeGenerator.generateQrcode(equipmentCode);
				mechanicalEquipment.setEquipmentCode(equipmentCode);
				
			if(mechanicalEquipment.getAssetReplace()==null){
			        String assetNo=CommonMethods.randomString(Constants.MECHANICALSUBSYSTEM);
					
					mechanicalEquipment.setAssetNo(assetNo);
				}	
		   }catch (Exception e) {
			 return "redirect:/mechanical";
			
		    }
		   	if(mechanicalEquipment.getBuilding()!=null){
				model.addAttribute("locationList",locationServiceInterface.getLocationList(mechanicalEquipment.getBuilding().getId()));
				}
			if(mechanicalEquipment.getBaseline()!=null){
				 	for (EquipmentBaseline eqBase : mechanicalEquipment.getBaseline()) {
				 		eqBase.setEquipment(mechanicalEquipment);
				 	}
					
				}
			if(mechService.save(mechanicalEquipment)){
				redirectAttributes.addFlashAttribute("success","Equipment saved successfully");
				}
			else{
					if(mechanicalEquipment.getBuilding()!=null){
					redirectAttributes.addFlashAttribute("locationList",locationServiceInterface.getLocationList(mechanicalEquipment.getBuilding().getId()));
					}
					redirectAttributes.addFlashAttribute("mechanicalEquipment",mechanicalEquipment);
					redirectAttributes.addFlashAttribute("fail", "Equipment already exists");
			    }
			return "redirect:/mechanical";
			
     }

	
			
		@RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
		public String edit(@PathVariable("id") Long id,Model model,RedirectAttributes redirect,HttpSession session) {
			Workspace w = (Workspace) session.getAttribute("workspace");

			try{
			if(!model.asMap().containsKey("mechanicalEquipment")){
			 model.addAttribute("mechanicalEquipment", mechService.find(id));
			 model.addAttribute("locationList",locationServiceInterface.getLocationList(mechService.find(id).getBuilding().getId()));  	  
			}
			if(model.asMap().containsKey("result")){
				getAttributes(model, w);
				model.addAttribute("org.springframework.validation.BindingResult.mechanicalEquipment",model.asMap().get("result"));
			}else{
			    getAttributes(model, w);
			    model.addAttribute("edit",true);
			     }
			return "mechanicalEdit";
			}catch (Exception e) {
				redirect.addFlashAttribute("fail", "No such Record exists");
		    return "redirect:/mechanical/search";
			}	
		}
		
		
		
		@RequestMapping(value = "/update" , method = RequestMethod.POST)
		public String update(@Valid@ModelAttribute("mechanicalEquipment") Mechanical mechanical,BindingResult result,@RequestParam("id") Long id,
				@RequestParam(value = "equipimage", required = false) MultipartFile equipimage,
				@RequestParam(value = "drawingimage", required = false) MultipartFile draw,
				RedirectAttributes redirectAttributes,Model model,HttpSession session) throws IOException {	
			if(model.asMap().containsKey("result")){
				redirectAttributes.addFlashAttribute("mechanicalEquipment",mechanical);
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mechanicalEquipment",
						model.asMap().get("result"));
				redirectAttributes.addFlashAttribute("fail","Enter fields properly");
				return "redirect:/mechanical/edit/"+mechanical.getId();
			}		
			try{	
			if(!draw.isEmpty()){	
		          if(mechanical.getDrawingImage().isEmpty()){  	 
		        	  String drawingFilePath=CommonMethods.randomString(Constants.MECHANICALSUBSYSTEM);
				  		if(QrCodeGenerator.uploadImage(draw,redirectAttributes,drawingFilePath)){		  
				           mechanical.setDrawingImage(drawingFilePath);
				        }else{
				           return "redirect:/mechanical/edit/"+mechanical.getId();
				         }
			        }       
			      else{
					  String drawingFilePath=mechanical.getDrawingImage();			// drawing image is empty
						if(QrCodeGenerator.uploadImage(draw,redirectAttributes,drawingFilePath)){		  
							mechanical.setDrawingImage(drawingFilePath);
				        }else{
				        	return "redirect:/mechanical/edit/"+mechanical.getId();
				         }
				      }
			 }
			if(!equipimage.isEmpty()){
				 if(mechanical.getImage().isEmpty()){
					 String equipFilePath=CommonMethods.randomString(Constants.MECHANICALSUBSYSTEM);
						 if(QrCodeGenerator.uploadImage(equipimage,redirectAttributes,equipFilePath)){
						    mechanical.setImage(equipFilePath);
						 }else{
							return "redirect:/mechanical/edit/"+mechanical.getId();
						 }
				      }
				  else{
					  String equipFilePath=mechanical.getImage();
						 if(QrCodeGenerator.uploadImage(equipimage,redirectAttributes,equipFilePath)){	
					 		mechanical.setImage(equipFilePath);
						  }else{
							 return "redirect:/mechanical/edit/"+mechanical.getId();
						  }
					 }
			}  
		    if(mechanical.getBaseline()!=null){	
				for (EquipmentBaseline eqBase : mechanical.getBaseline()) {
			 		  eqBase.setEquipment(mechanical);
			 	}
			}
			   Workspace w = (Workspace) session.getAttribute("workspace");  
			   mechanical.setWorkspace(w);
			   
			}catch (Exception e) {	
				return "redirect:/mechanical/edit/"+mechanical.getId();
			}
			if(mechService.update(mechanical)){	
				redirectAttributes.addFlashAttribute("success","Equipment Updated successfully");
			} 	
			else{
				redirectAttributes.addFlashAttribute("fail", "Equipment already exists");
			}
			if(mechanical.getBuilding()!=null){	
				redirectAttributes.addFlashAttribute("locationList",locationServiceInterface.getLocationList(mechanical.getBuilding().getId()));
			}
			redirectAttributes.addFlashAttribute("mechanicalEquipment",mechanical);
			return "redirect:/mechanical/edit/"+mechanical.getId();
	 }
		
	
		@RequestMapping(value="/delete/{id}")
		public String deleteMechEquipment(@PathVariable("id") Long id,Model model,RedirectAttributes redirectAttributes) {			
			Mechanical mechanical=mechService.find(id);
			String drawImage=mechanical.getDrawingImage();
			String eqImage=mechanical.getImage();
			 try{
				 		if(drawImage!=null){
				 			removeImage(drawImage);
				 		}
				 		if(eqImage!=null){
				 			removeImage(eqImage);
				 		}
				 		if(mechService.remove(mechanical)){		
				 			redirectAttributes.addFlashAttribute("success", "Deleted Mechanical Equipment Successfully");
				 		}
				 		else{
				 			redirectAttributes.addFlashAttribute("fail", "Deletion Failed");
				 		}
				 return "redirect:/mechanical/pageno="+1;
			 	} catch (Exception e) {
				 redirectAttributes.addFlashAttribute("fail", "Deletion Failed");
				return "redirect:/mechanical/pageno="+1;
			}
		}
	 

		 @RequestMapping(value ="/getimage/{drawimage}", method = RequestMethod.GET)
		 public void displayDrawingimage(@PathVariable("drawimage") String filename,Model m, HttpSession session,HttpServletResponse rep,
		 final RedirectAttributes redirectAttributes){
			 String filepath	= Constants.PATH+ filename;
			 try{
				 	FileInputStream fileInputStream = new FileInputStream(filepath);
				 	byte[] bFile = IOUtils.toByteArray(fileInputStream);
				 	OutputStream os=rep.getOutputStream();  
				 	os.write(bFile);
				 	os.close();
				 	fileInputStream.close();
			    }catch (Exception e) {	 
			     }
		 }
		

		@RequestMapping(value = "/search" , method = RequestMethod.GET)
		public String view(Model model) {
			
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList",buildingServiceInterface.getAll());
			model.addAttribute("EqType",Constants.MECHANICALSUBSYSTEM );		
		    model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.MECHANICALSUBSYSTEM));
		    model.addAttribute("mechanical", new EquipmentSearchCriteria());	
			return "mechanicalView";
		}
		
		
		@RequestMapping(value="/search",method=RequestMethod.POST)
		public String searchCriteria(@ModelAttribute("mechanical")EquipmentSearchCriteria mechanicalSearch,
				Model model,RedirectAttributes redirect,HttpSession session){
				Workspace w=(Workspace)session.getAttribute("workspace");		
				mechanicalSearch.setWorkspace(w);
                int page=1;
			    session.setAttribute("esc", mechanicalSearch);
			if(model.asMap().containsKey("page")){
				page = (Integer) model.asMap().get("page");
				pagination(page, model,mechanicalSearch,w);
			}
			else{	
				pagination(page, model,mechanicalSearch,w);
				model.addAttribute("mechanical", mechanicalSearch);
				List<Mechanical> mechlist=mechService.getSearchList(from,mechanicalSearch,w);
					if(mechlist.isEmpty())
					{
						redirect.addFlashAttribute("fail", "No Record found");
						return "redirect:/mechanical/search";
					}
					else{
						model.addAttribute("mechlist", mechlist);	
							if(mechanicalSearch.getBuilding()!=null){
								model.addAttribute("locationList", locationServiceInterface.getLocationList(mechanicalSearch.getBuilding().getId()));	
							}
					}
			 }
				model.addAttribute("frequencies", Constants.frequency);
				model.addAttribute("buildingList",buildingServiceInterface.getAll());
				model.addAttribute("EqType",Constants.MECHANICALSUBSYSTEM );
				model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.MECHANICALSUBSYSTEM));
				return "mechanicalView";
		}
		
		
		
		@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
		public String paginate(@PathVariable("page") int page, Model model,RedirectAttributes redirect,HttpSession session){  
			EquipmentSearchCriteria mechanical = (EquipmentSearchCriteria) session.getAttribute("esc");
			Workspace w=(Workspace)session.getAttribute("workspace"); 
				if(model.asMap().containsKey("page")){
					page = (Integer) model.asMap().get("page");
					pagination(page, model,mechanical,w);
					model.addAttribute("mechanical", mechanical);
					List<Mechanical> mechlist=mechService.getSearchList(from,mechanical,w);
					model.addAttribute("mechlist", mechlist);
						if(mechanical.getBuilding()!=null)
						{
							model.addAttribute("locationList", locationServiceInterface.getLocationList(mechanical.getBuilding().getId()));		
						}
			 	 }
				else{	
					pagination(page, model,mechanical,w);
					model.addAttribute("mechanical", mechanical);
					List<Mechanical> mechlist=mechService.getSearchList(from,mechanical,w);
					model.addAttribute("mechlist", mechlist);
						if(mechanical.getBuilding()!=null)
						{
							model.addAttribute("locationList", locationServiceInterface.getLocationList(mechanical.getBuilding().getId()));	
						}
			    }
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList",buildingServiceInterface.getAll());	
			model.addAttribute("EqType",Constants.MECHANICALSUBSYSTEM );	
			model.addAttribute("subSystems", assetSubTypeService.getEquipmentSubTypeList(Constants.MECHANICALSUBSYSTEM));	
			return "mechanicalView";
			}
		
		
		public void pagination(int page,Model model,EquipmentSearchCriteria mechanical,Workspace w){	
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = (long) mechService.count(mechanical,w);
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("mechlist", mechService.getSearchList(from,mechanical,w));
		}
			
	   public Boolean removeImage(String imageName) {
		   File f = new File(Constants.PATH + imageName);
			if (f.exists()) {
				f.delete();
				return true;
			}
				return false;
		}
					
						
					

	 @RequestMapping(value="/{value}",method = RequestMethod.GET)
	  public @ResponseBody List<Location> getLocation(@PathVariable("value") Long buildingId,Model model) {
		 List<Location> list=locationServiceInterface.getLocationList(buildingId);
				return list;
		   }
			
*/
}