package com.aste.lsme.controller;


import java.io.FileInputStream;
import java.io.IOException;
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

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Electrical;
import com.aste.lsme.domain.EquipmentBaseline;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.ElectricalService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;


	@Controller
	@RequestMapping("/electrical")
	public class ElectricalController {
		
		/*@Autowired
		AssetSubtypeService assetSubTypeService;
		
		@Autowired
		ElectricalService electricalService;
		
		@Autowired
		BuildingService buildingService;
		
		@Autowired
		LocationService locationService;
		
	

		int from = 0;
		int total = 0;
		public static final int ROWS = Constants.ROWS;
		Long records = 0L;	
		
		@RequestMapping(method = RequestMethod.GET)
		public String get(Model model,HttpSession  session) {
			
            getLists( model, session);
		   
	    if(!model.asMap().containsKey("electrical"))
			model.addAttribute("electrical", new Electrical());
				
		if(model.asMap().containsKey("result")) {
			model.addAttribute("org.springframework.validation.BindingResult.electrical",model.asMap().get("result"));
		  }		
				
	        return "electrical";
		}
		
		
	
		@RequestMapping(value="/add", method=RequestMethod.POST )
		public String save(@Valid @ModelAttribute("electrical") Electrical electrical,
						    BindingResult result,
							Model model,
							final RedirectAttributes redirectAttributes,
							@RequestParam(value = "drawingimage", required = false) MultipartFile drawingImage ,
							@RequestParam(value = "equipmentimage", required = false) MultipartFile equipmentImage,
							HttpSession session) throws IOException, WriterException{
			
			
			
			if(result.hasErrors()){
			    redirectAttributes.addFlashAttribute("electrical",electrical);
			    redirectAttributes.addFlashAttribute("result",result);
				return "redirect:/electrical";
			}
			
			
			 Workspace w=(Workspace)session.getAttribute("workspace");
		     electrical.setWorkspace(w);
		try {
			if(!drawingImage.isEmpty()) {
				String imageName = CommonMethods.randomString(Constants.ELECTRICAL);
			
			if(	QrCodeGenerator.uploadImage(drawingImage, redirectAttributes, imageName)) {
				electrical.setDrawingImage(imageName);
			}
			else
				return "redirect:/electrical";
		}
		if(!equipmentImage.isEmpty()) {
			
			String EquipmentImageName	= CommonMethods.randomString(Constants.ELECTRICAL);
			if(QrCodeGenerator.uploadImage(equipmentImage, redirectAttributes, EquipmentImageName)) {
				electrical.setImage(EquipmentImageName);
			}	
			else
				return "redirect:/electrical";
		   }
	 
		String equipmentCode=CommonMethods.randomString(Constants.ELECTRICAL);
		QrCodeGenerator.generateQrcode(equipmentCode);
		electrical.setEquipmentCode(equipmentCode);
		
		if(electrical.getAssetReplace()==null){
	          String assetNo=CommonMethods.randomString(Constants.ELECTRICAL);
	          electrical.setAssetNo(assetNo);
	        }
		 }		
		
	catch(Exception e){
		redirectAttributes.addFlashAttribute("fail","Image cannot be uploaded");
	  }
		

		if(electrical.getBaseline()!=null){
	          for (EquipmentBaseline eqBase : electrical.getBaseline()) {
	           eqBase.setEquipment(electrical);
	       }
	     }
		
         if(electricalService.persist(electrical,w)) {
			redirectAttributes.addFlashAttribute("success","Electrical equipment saved successfully");
				return "redirect:/electrical";
			}
		 else {
			redirectAttributes.addFlashAttribute("electrical",electrical);
				redirectAttributes.addFlashAttribute("fail","Electrical Equipment already exists" );
				return "redirect:/electrical";
			}
		}		
	

		@RequestMapping(value="/search",method=RequestMethod.GET)
		public String search(Model model,HttpSession session)
		{

			getLists(model, session);

			model.addAttribute("electricalSearch",new EquipmentSearchCriteria());
		    return "electricalSearch";
		}


		@RequestMapping(value="/search",method=RequestMethod.POST)
		public String searchCriteria(@ModelAttribute("electricalSearch")EquipmentSearchCriteria electricalSearch,
										Model model,
										HttpServletRequest request,HttpSession session,
										RedirectAttributes redirectAttributes)
		{
			
	      Workspace w=(Workspace)session.getAttribute("workspace");
          electricalSearch.setWorkspace(w);
		    int page = 1;
			session.setAttribute("esc", electricalSearch);
			pagination(page, model, electricalSearch,w);
			
			model.addAttribute("electricalSearch",electricalSearch);

			getLists(model, session);
			
			return "electricalSearch";
			
		}	
		
	
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String delete(@RequestParam("id") long id,
							final RedirectAttributes redirectAttributes,
							HttpServletRequest request)  {
			try {
				electricalService.delete(id);
				redirectAttributes.addFlashAttribute("success","Electrical equipment deleted successfully");
			} catch (Exception e) {
				
				redirectAttributes.addFlashAttribute("fail", "Cannot delete this electrical equipment");
			}
			
			return "redirect:/electrical/pageno=1";
		}
	
		@RequestMapping(value="/update/{id}" ,method=RequestMethod.GET)
		public String Edit(@PathVariable("id") long id,Model model,HttpSession session,RedirectAttributes redirectAttributes) {
			
			
			try {
					Electrical electrical=electricalService.find(id);
					
					if(!model.asMap().containsKey("electrical"))
						model.addAttribute("electrical",electrical);
					
					if(electrical.getBuilding()!=null) {
						model.addAttribute("locationList",locationService.getLocationList(electrical.getBuilding().getId()));
						}
					
					getLists(model, session);
			
			
			}catch(Exception e){
				redirectAttributes.addFlashAttribute("fail","Electrical equipment does not exists");
				return "redirect:/electrical/search";
			}
				
			if(model.asMap().containsKey("result")) {
				
				model.addAttribute("org.springframework.validation.BindingResult.electrical",model.asMap().get("result"));
			}		
			return "electricalEdit";
			
		}
		@RequestMapping(value="/update", method=RequestMethod.POST)
		public String update(@Valid @ModelAttribute("electrical")Electrical electrical,	BindingResult result,
				final RedirectAttributes redirectAttributes,
				@RequestParam(value = "drawingimage", required = false) MultipartFile drawingImage ,
				@RequestParam(value = "equipmentimage", required = false) MultipartFile equipmentImage ,
				Model model,
				HttpSession session )
		{
			
			  if(result.hasErrors()){
					
					 redirectAttributes.addFlashAttribute("electrical",electrical);
					 redirectAttributes.addFlashAttribute("result",result);
					 return	"redirect:/electrical/update/"+ electrical.getId();
				}
				
			Workspace w=(Workspace)session.getAttribute("workspace");
			electrical.setWorkspace(w);
			
			String imageName;
			String EquipmentImageName;
		
		if(!drawingImage.isEmpty()) {
				if(electrical.getDrawingImage()!=null)
					 imageName =electrical.getDrawingImage();
				else
					 imageName	= CommonMethods.randomString(Constants.ELECTRICAL);
				
			if(	QrCodeGenerator.uploadImage(drawingImage, redirectAttributes, imageName))
				electrical.setDrawingImage(imageName);
			
			else
				return "redirect:/electricalEdit";
			}
			
			if(!equipmentImage.isEmpty()) {
				if(electrical.getImage()!=null)
					 EquipmentImageName =electrical.getImage();
				else
				 EquipmentImageName	=CommonMethods.randomString(Constants.ELECTRICAL);
				
				if(QrCodeGenerator.uploadImage(equipmentImage, redirectAttributes, EquipmentImageName))
					electrical.setImage(EquipmentImageName);
					
				else
					return "redirect:/electricalEdit";
				
			}
			
		        if(electrical.getBaseline()!=null){
		           for (EquipmentBaseline eqBase : electrical.getBaseline()) {
		        	   eqBase.setEquipment(electrical);
		           }
		        }
			
			 if(electricalService.update(electrical,w)){

		
				redirectAttributes.addFlashAttribute("success","Electrical equipment updated successfully");
				model.addAttribute("electrical",electrical);
				return	"redirect:/electrical/update/"+ electrical.getId();
			}
			
		else{
			    redirectAttributes.addFlashAttribute("fail","Electrical equipment already exists");			
				model.addAttribute("electrical",electrical);
				return	"redirect:/electrical/update/"+ electrical.getId();
			}
		
		}		
		
	
		@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	    public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
			
		   EquipmentSearchCriteria esc1=(EquipmentSearchCriteria) session.getAttribute("esc");
	       Workspace w=(Workspace)session.getAttribute("workspace");
		  
	       pagination(page, model, esc1,w);
	       model.addAttribute("electricalSearch",esc1);
	       getLists(model, session);
	       
	       return "electricalSearch";
	    }
		
		private void pagination(int page, Model model,EquipmentSearchCriteria esc,Workspace w) {
			
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = (long) electricalService.getElectricalCount(esc,w);
	    	total = (int) Math.ceil((double) records / (double) ROWS);
		
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("searchedList", electricalService.getSearchList( from,esc,w));
			
		}
			
		@RequestMapping(value ="/getimage/{name}", method = RequestMethod.GET)
	     public void displayDrawingimage(@PathVariable("name") String filename,HttpServletResponse rep) throws IOException {
	      
	       String filepath  = Constants.PATH + filename;
	       FileInputStream fileInputStream = new FileInputStream(filepath);
	       byte[] bFile = IOUtils.toByteArray(fileInputStream);
	       OutputStream os=rep.getOutputStream(); 
	       
	        os.write(bFile);
	        os.close();
	        fileInputStream.close();
	     
	      }
		
		
		private void getLists(Model model,HttpSession session) {
			
			Workspace w=(Workspace)session.getAttribute("workspace");
			model.addAttribute("subTypeList", assetSubTypeService.getEquipmentSubTypeList(Constants.ELECTRICALSUBSYSTEM ));
			model.addAttribute("frequencies", Constants.frequency);
			model.addAttribute("buildingList", buildingService.getWorkspaceBuildings(w));
		   
		   
			
		}
		
		@RequestMapping(value="/locationlist/{bId}")
		@ResponseBody
		public List<Location>  getLocationList(@PathVariable("bId") Long bId)
		{
			
			List<Location> list=locationService.getLocationList(bId);
			return list;
			
		}
		
		*/
	}	
		
	