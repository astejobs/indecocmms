package com.aste.lsme.controller;



import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.PredictiveCriteria;
import com.aste.lsme.domain.PredictiveMonitorPoint;
import com.aste.lsme.domain.PredictiveParameter;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.ThrushHoldValue;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.ElectricalService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MechanicalService;
import com.aste.lsme.service.MonitorClassService;
import com.aste.lsme.service.PredictiveMonitorPointServiceInterface;
import com.aste.lsme.service.PredictiveParameterServiceInterface;
import com.aste.lsme.service.ThrushHoldServiceInterface;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.utils.CommonMethods;



@Controller
@RequestMapping(value = "/predictive")
public class ThrushHoldController {
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	
	@Autowired
	BuildingService buildingService;

	
	
	@Autowired
	EquipmentService equipService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	EquipmentService eqSystemService;
	
	@Autowired
	MechanicalService mechanicalEquipmentService;

	@Autowired
	ElectricalService electricalEquipmentService;

	@Autowired
	AssetSubtypeService subsystemService;

	@Autowired
	MonitorClassService monitorclassService;

	@Autowired
	ThrushHoldServiceInterface predictivemaintService;

	@Autowired 
	JavaMailSender mailsender;

	@Autowired
	VelocityEngine  velocityEngine;
	
	@Autowired
	UserDetailsServiceInterface UserDetail;

	
	@Autowired
	PredictiveParameterServiceInterface paramService;
	
	@Autowired
	PredictiveMonitorPointServiceInterface monitorPointService;
	


	@RequestMapping(value = "/thrushholdValues", method = RequestMethod.GET)
	public String createThrushholdValuesForm(Model model, HttpSession session,final SessionStatus status,RedirectAttributes redirect) {	
		Workspace w = (Workspace) session.getAttribute("workspace");
			if(!model.asMap().containsKey("predictiveMaintenance")){
				model.addAttribute("predictiveMaintenance", new ThrushHoldValue());
			} 
			if(model.asMap().containsKey("errors")){  
				model.addAttribute("org.springframework.validation.BindingResult.predictiveMaintenance",model.asMap().get("errors"));
			}
		model.addAttribute("equipmentList", Constants.EquipmentTypes);
	    model.addAttribute("monitorclasses",monitorclassService.getMonitorClasses(w));
		return "thrushholdValues";

			}
	
	@RequestMapping(value="/AddThrushHold/" , method  =RequestMethod.POST)
	public String PMSave(@Valid @ModelAttribute("predictiveMaintenance") ThrushHoldValue predictiveMaintenance,final BindingResult result,RedirectAttributes redirect,Model model,
	HttpSession session,@RequestParam(value = "temp_image", required = false) MultipartFile attachment)	{	
		ArrayList<PredictiveMonitorPoint> thrushHoldvalues=new ArrayList<PredictiveMonitorPoint>();
			if(result.hasErrors()){		
					 redirect.addFlashAttribute("predictiveMaintenance",predictiveMaintenance);
					 redirect.addFlashAttribute("errors",result);	
					 System.out.println(result.toString()+"ffffffffffffff");
				     redirect.addFlashAttribute("fail", "Enter Fields Correctly");
					 return "redirect:/predictive/thrushholdValues";
			}else{
					try{
							if(predictiveMaintenance.getParameterList()!=null){
									for (PredictiveParameter pm : predictiveMaintenance.getParameterList()) {
										pm.setPredictiveMaint(predictiveMaintenance);
									}
							 }	
							if(predictiveMaintenance.getMonitorPointList()!=null){
									for (PredictiveMonitorPoint mp : predictiveMaintenance.getMonitorPointList()) {
										mp.setPredictiveMaint(predictiveMaintenance);
										double lowValue=(mp.getLowValue());
										double highValue=(mp.getHighValue());
										double actualValue=(mp.getActualValue());
							            if((actualValue<lowValue)  ||  (actualValue>highValue))
							            {
							              thrushHoldvalues.add(mp);   
							            }

									}
							}
							predictiveMaintenance.setCreatedDate(new Date());
							predictiveMaintenance.setUser((UserDetail) session.getAttribute("user"));
							if(!attachment.isEmpty()){
								String filename=CommonMethods.randomString(Constants.THRUSHOLD);			
									if( QrCodeGenerator.uploadImage(attachment,redirect,filename)) {
										predictiveMaintenance.setFileName(filename);
									}else{  
			          	        	    return "redirect:/predictive/thrushholdValues";
			          	             }
							}						
							predictivemaintService.save(predictiveMaintenance);
							if(!thrushHoldvalues.isEmpty()){	
							  ThrushHoldVoilation(thrushHoldvalues,predictiveMaintenance);
							 }
							redirect.addFlashAttribute("success", "Predictive Maintainence Added Successfully");
						}catch(Exception e){
									e.printStackTrace();
									redirect.addFlashAttribute("predictiveMaintenance", predictiveMaintenance);
									redirect.addFlashAttribute("fail", "Predictive Maintainence Already Exists");	
								}
							return "redirect:/predictive/thrushholdValues";
				}
					
			}		
					
									
		public void ThrushHoldVoilation(ArrayList<PredictiveMonitorPoint>thrushHoldvalues ,ThrushHoldValue predictiveMaintenance ){
			MimeMessage message = mailsender.createMimeMessage();
			RedirectAttributes redirect = null;
		     MimeMessageHelper helper;
					try {
					        helper = new MimeMessageHelper(message, false, "utf-8");
					        String destination =UserDetail.findUserByUsername("admin").getEmail();
					               helper.setTo(destination);
					               helper.setSubject("Tasks for Current Month");
					               helper.setFrom("<info@stie.com.sg>");
					               Map model = new HashMap();
					               model.put("thrushHoldValue",thrushHoldvalues);
					               model.put("predictiveMaintenance",predictiveMaintenance );
					               String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/emailTemplate/thrushHoldMaintainence.vm","UTF-8", model);
					               helper.setText(text,true);
					               mailsender.send(message);   
					 } catch (MessagingException e) { 
					    e.printStackTrace();
				    	redirect.addFlashAttribute("updatePMFailed", "Updation  Failed");
					      }				          
					   }			
					
					
		@RequestMapping(value = "/equipmentSearch", method = RequestMethod.GET)
		public String PMGetEquipment(Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		  model.addAttribute("equipmentSearchCriteria", new EquipmentSearchCriteria());
		  model.addAttribute("buildings", buildingService.getWorkspaceBuildings((Workspace) session.getAttribute("workspace")));
		  model.addAttribute("types", equipService.getPMEquipTypes(w));
		  return "PMGetEquipment";
				}
					

	@RequestMapping(value = "/getsubtype/{value}", method = RequestMethod.GET)
	public @ResponseBody List<AssetSubtype> getSubtypes(@PathVariable("value") String eqType, Model model, HttpSession session){
	Workspace w = (Workspace) session.getAttribute("projid");
	List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(eqType);
	return list;	
}
	
	
	@RequestMapping(value = "/getLocation/{value}", method = RequestMethod.GET)
	public @ResponseBody List<Location> getLocation(@PathVariable("value") long buildId, Model model, HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		List<Location> list=locationService.getLocationList(buildId);
		return list;	
	}
	
	
	@RequestMapping(value = "/equipmentSearch", method = RequestMethod.POST)
	public String search(@ModelAttribute("equipmentSearchCriteria") EquipmentSearchCriteria eqSearch, Model model,RedirectAttributes redirect, HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
			try{
				int page=1;
				session.setAttribute("esc", eqSearch);
					if(model.asMap().containsKey("page")){
						page = (Integer) model.asMap().get("page");
						pagination(page, model,eqSearch,w);
					}
					else{	
						pagination(page, model,eqSearch,w);
						List<Equipment> eqList=predictivemaintService.getSearchEqpList(from,eqSearch,w);
							if(eqList.isEmpty()){
								redirect.addFlashAttribute("fail", "No Record found");
								return "redirect:/predictive/equipmentSearch";
							}else{
									if(eqSearch.getEquipmentType()!=null){
										List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(eqSearch.getEquipmentType());
										model.addAttribute("subTypeList",list);   
									}
									if(eqSearch.getBuilding()!=null){	    				
										List<Location> list=locationService.getLocationList(eqSearch.getBuilding().getId());
										model.addAttribute("locationList", list);
									}
								model.addAttribute("buildings", buildingService.getWorkspaceBuildings((Workspace) session.getAttribute("workspace")));
	    				        model.addAttribute("types", equipService.getPMEquipTypes(w)); 	
	     	                 }
	    	       }
			}catch (Exception e) {
				e.printStackTrace();			}
	    		return "PMGetEquipment";
	}
	
	
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page, Model model,RedirectAttributes redirect,HttpSession session){  
		EquipmentSearchCriteria equipSearch = (EquipmentSearchCriteria) session.getAttribute("esc");
		Workspace w=(Workspace)session.getAttribute("workspace");			
			if(model.asMap().containsKey("page")){
				page = (Integer) model.asMap().get("page");
				pagination(page, model,equipSearch,w);
					if(equipSearch.getEquipmentType()!=null){
						List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(equipSearch.getEquipmentType());
						model.addAttribute("subTypeList",list);   
					}
					if(equipSearch.getBuilding()!=null){
						List<Location> list=locationService.getLocationList(equipSearch.getBuilding().getId());
						model.addAttribute("locationList", list);
					}
			}	
			else{	
				pagination(page, model,equipSearch,w);	
					if(equipSearch.getEquipmentType()!=null){
						List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(equipSearch.getEquipmentType());
						model.addAttribute("subTypeList",list);   
						}
					if(equipSearch.getBuilding()!=null){
	    				List<Location> list=locationService.getLocationList(equipSearch.getBuilding().getId());
	    				model.addAttribute("locationList", list);
					 }							
				}
			return "PMGetEquipment";
	}
	
	

	public void pagination(int page,Model model,EquipmentSearchCriteria eqp,Workspace w){
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) predictivemaintService.countEqp(eqp,w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
	    model.addAttribute("equipmentSearchCriteria", eqp) ; 
		model.addAttribute("equipmentList", predictivemaintService.getSearchEqpList(from,eqp,w));
		model.addAttribute("types", equipService.getPMEquipTypes(w));
		model.addAttribute("buildings", buildingService.getWorkspaceBuildings(w));

	}
	
	
	
	@RequestMapping(value = "/SearchThrushholdValues", method = RequestMethod.GET)
	public String SearchThrushholdValues(Model model, HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		model.addAttribute("predictiveCriteria", new PredictiveCriteria());
		model.addAttribute("buildings", buildingService.getWorkspaceBuildings((Workspace) session.getAttribute("workspace")));
		model.addAttribute("types", equipService.getPMEquipTypes(w));
		return "SearchThrushholdValues";
	}
	
	
	
	@RequestMapping(value = "/SearchThrushholdValues/", method = RequestMethod.POST)
	public String SearchThrushholdValuesPost(@ModelAttribute("predictiveCriteria") PredictiveCriteria predictiveCriteria,Model model, HttpSession session,RedirectAttributes redirect) {
			Workspace w = (Workspace) session.getAttribute("workspace");
			 int page=1;
			    session.setAttribute("pmesc", predictiveCriteria);
					if(model.asMap().containsKey("page")){
						page = (Integer) model.asMap().get("page");
						paginationPM(page, model,predictiveCriteria,w);
					}else{	
						paginationPM(page, model,predictiveCriteria,w);
						List<ThrushHoldValue> pmList=predictivemaintService.searchSpecificPredictiveMaintenance(from,predictiveCriteria,w);
					if(pmList.isEmpty()){
						redirect.addFlashAttribute("fail", "No Record Found");
						redirect.addFlashAttribute("predictiveCriteria", session.getAttribute("pmesc"));
						return "redirect:/predictive/SearchThrushholdValues/";
	    		     }else{
	    		    	 	if(predictiveCriteria.getSystem()!=""){	    				
	    		    	 		List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(predictiveCriteria.getSystem());
	    		    	 		model.addAttribute("subTypeList",list);   
	    		    	 	}
	    		    	 	if(predictiveCriteria.getBuilding()!=""){	    		
	    		    	 		List<Location> list=locationService.getLocationList(Long.parseLong(predictiveCriteria.getBuilding()));
	    		    	 		model.addAttribute("locationList", list);
	    		    	 	}
	    		    	 	model.addAttribute("buildings", buildingService.getWorkspaceBuildings((Workspace) session.getAttribute("workspace")));
	    				    model.addAttribute("types", equipService.getPMEquipTypes(w));    			    	
	    			 }
	    		}
	    	return "SearchThrushholdValues";
	}
	
	
	
	
	public void paginationPM(int page,Model model,PredictiveCriteria pm,Workspace w){
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) predictivemaintService.countSpecificPredictiveMaintenance(pm,w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
	    model.addAttribute("predictiveCriteria", pm) ; 
		model.addAttribute("predictivelist", predictivemaintService.searchSpecificPredictiveMaintenance(from,pm,w));
		model.addAttribute("types", equipService.getPMEquipTypes(w));
		model.addAttribute("buildings", buildingService.getWorkspaceBuildings(w));

	}


	@RequestMapping(value = "/PM/pageno={page}" , method = RequestMethod.GET)
	public String paginatePM(@PathVariable("page") int page, Model model,RedirectAttributes redirect,HttpSession session){  
		PredictiveCriteria PMSearch = (PredictiveCriteria) session.getAttribute("pmesc");
		Workspace w=(Workspace)session.getAttribute("workspace");		
				if(model.asMap().containsKey("page")){
					page = (Integer) model.asMap().get("page");
					paginationPM(page, model,PMSearch,w);
						if(PMSearch.getSystem()!=""){
							List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(PMSearch.getSystem());
							model.addAttribute("subTypeList",list);   
						}
						if(PMSearch.getBuilding()!=""){
							List<Location> list=locationService.getLocationList(Long.parseLong(PMSearch.getBuilding()));
							model.addAttribute("locationList", list);
					    }
	         	}else{	
	         		paginationPM(page, model,PMSearch,w);	
	         			if(PMSearch.getSystem()!=""){
	         				List<AssetSubtype> list=subsystemService.getEquipmentSubSystems(PMSearch.getSystem());
	         				model.addAttribute("subTypeList",list);   
						}
	         			if(PMSearch.getBuilding()!=""){
	         				List<Location> list=locationService.getLocationList(Long.parseLong(PMSearch.getBuilding()));
	         				model.addAttribute("locationList", list);
						}						
				}
	  return "SearchThrushholdValues";	
	}
	
	
	
	
	@RequestMapping(value = "/predictivegetsubtype/{value}", method = RequestMethod.GET)
	public @ResponseBody List<AssetSubtype> getPredictiveSubtypes(@PathVariable("value") String eqType, Model model, HttpSession session)
	{
		Workspace w = (Workspace) session.getAttribute("projid");
		List<AssetSubtype> list=predictivemaintService.getPredictEquipmentSubSystems(eqType);
		return list;	
	}
	 
	
	@RequestMapping(value = "/editThrushholdValues/{predId}", method = RequestMethod.GET)
	  public String editThrushholdValues(@PathVariable Long predId, Model model,
	      final RedirectAttributes redirectAttributes, HttpSession session) {
	    	ThrushHoldValue p = predictivemaintService.find(predId);
	    	if(p==null){
	    		redirectAttributes.addFlashAttribute("fail", "No such record Found");
			    return "redirect:/predictive/SearchThrushholdValues";
		    
	    		}
	    	if(model.asMap().containsKey("errors")){ 
		        model.addAttribute("org.springframework.validation.BindingResult.p",model.asMap().get("errors"));
		       }
	    	model.addAttribute("predictiveMaintenance", p);
	    	return "updateThrushholdValues";	  
	}
	
	
	
	 @RequestMapping(value = "/updateThrushholdValues", method = RequestMethod.POST)
	    public String updateThrushholdValues(@Valid@ModelAttribute("predictiveMaintenance") ThrushHoldValue p,BindingResult result,Model model,@RequestParam(value = "temp_image", required = false) MultipartFile attachment,RedirectAttributes redirect,HttpSession session) throws Exception{ 
	      ArrayList<PredictiveMonitorPoint> thrushHoldvalues=new ArrayList<PredictiveMonitorPoint>();
	      Workspace w = (Workspace) session.getAttribute("workspace");
	      if(result.hasErrors()){     
	          redirect.addFlashAttribute("errors",result);  
	         
	          redirect.addFlashAttribute("fail", "Enter Fields Correctly");
	          return "redirect:/predictive/editThrushholdValues/"+p.getId();
	      }    
	      if(!attachment.isEmpty()){ 
	    	  String filename=CommonMethods.randomString(Constants.THRUSHOLD);			
    	       	if( QrCodeGenerator.uploadImage(attachment,redirect,filename)) {
    	       	p.setFileName(filename); 	
 	           }else{   
 	        	 return "redirect:/predictive/thrushholdValues";
 	           } 
	      }
	      if(p.getMonitorPointList()!=null){
	          for (PredictiveMonitorPoint e : p.getMonitorPointList()) {
	              e.setPredictiveMaint(p);
	              e.setPredictiveMonClass(e.getPredictiveMonClass());
	          }
	      }
	      
	      ThrushHoldValue pmp=predictivemaintService.find(p.getId());
	       int totalcount=p.getMonitorPointList().size();
	       int count= pmp.getMonitorPointList().size();
	       if(totalcount>count){
	         for (int i = count; i < totalcount; i++) {
	        	 	  p.getMonitorPointList().get(i).setPredictiveMaint(p);    
	        	 	  double lowValue=(p.getMonitorPointList().get(i).getLowValue());
	                  double highValue=(p.getMonitorPointList().get(i).getHighValue());
	                  double actualValue=(p.getMonitorPointList().get(i).getActualValue());
	                 if((actualValue<lowValue)  ||  (actualValue>highValue)) {
	                    thrushHoldvalues.add(p.getMonitorPointList().get(i));   
	                  }
	          }
	      }
	      if(p.getParameterList()!=null){
	          for (PredictiveParameter pp : p.getParameterList()) {
	              pp.setPredictiveMaint(p);
	          }
	      }
	       try { 
	        p.setUser((UserDetail) session.getAttribute("user"));      
	        predictivemaintService.update(p);
	        	if(!thrushHoldvalues.isEmpty()){
	        		ThrushHoldVoilation(thrushHoldvalues,p);
	        	}
	       }catch (Exception e) {
	              e.printStackTrace();
	              redirect.addFlashAttribute("fail", "Updation  Failed");
	             return "redirect:/predictive/editThrushholdValues/"+p.getId();   
	    }
	      redirect.addFlashAttribute("success", "Updated SuccessFully");
	      return "redirect:/predictive/editThrushholdValues/"+p.getId(); 
	     }
	  
	
	
	 @RequestMapping(value ="/getimage/{image}", method = RequestMethod.GET)
	 public void displayDrawingimage(@PathVariable("image") String filename,Model m, HttpSession session,HttpServletResponse rep,
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
		    	e.printStackTrace();
		     }
		 }
	 
	 
	 

		@RequestMapping(value = "/delete/{predictId}", method = RequestMethod.GET)
		public String deletePredictMaint(@PathVariable("predictId") long id,
				Model model, final RedirectAttributes redirectAttributes,
				HttpSession session) {
			Workspace w = (Workspace) session.getAttribute("workspace");
			try {
				predictivemaintService.remove(id);
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("fail", "Delete fail");
			}
			redirectAttributes.addFlashAttribute("success",
					"Deleted Successfully");	
			return "redirect:/predictive/PM/pageno="+1;

		}
		

		@RequestMapping(value = "/removeThrusholdParam/{id}/{pmID}", method = RequestMethod.GET)
		public String removePARam(@PathVariable long id,@PathVariable long pmID){	
			try {
				PredictiveParameter p=paramService.find(id);
				paramService.remove(p);
			 } catch (Exception e) {
				e.printStackTrace();
			   }
		return "redirect:/predictive/editThrushholdValues/"+pmID;
		}
		
		
		
		@RequestMapping(value = "/removeThrusholdMpoints/{id}/{pmID}", method = RequestMethod.GET)
		public String removeMpoints(@PathVariable long id,@PathVariable long pmID){
			try {
				PredictiveMonitorPoint mp=monitorPointService.find(id);
				monitorPointService.remove(mp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/predictive/editThrushholdValues/"+pmID;
		}
		
	}



