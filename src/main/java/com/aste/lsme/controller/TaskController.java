package com.aste.lsme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.PartTransaction;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.ScheduledEquipment;
import com.aste.lsme.domain.SearchPMTask;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.PMService;
import com.aste.lsme.service.PMTaskService;
import com.aste.lsme.service.PartTransactionService;
import com.aste.lsme.service.PartsInWarehouseService;
import com.aste.lsme.utils.CommonMethods;
import com.google.zxing.WriterException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping(value = "/task")

public class TaskController {
	
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;		
	String PATH="/home/majid/Desktop/upload/";
	
	
	@Autowired
	PartsInWarehouseService partsInWarehouseService;
	
	@Autowired PMService pmservice;
	@Autowired PMTaskService pmtaskservice;
	@Autowired
	PartTransactionService partTransactionService;
	
	@Autowired
	BuildingService buildingService;
	
	
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	AssetTypeService assetTypeService;
	
	@Autowired
	AssetSubtypeService assetSubtypeService;
	
	@Autowired
	EquipmentService  equipmentservice;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String modifyPMtask(Model model,HttpSession session) {
		
		 Workspace w=(Workspace)session.getAttribute("workspace");
		
		model.addAttribute("pmsCriteria", new SearchPMTask());
		try{
			model.addAttribute("pmschedules",pmservice.getAllPMSchedules(w));
		}catch(Exception e){ }
		
		 return "PMTaskView";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	  public String searchPMtask(@ModelAttribute("pmsCriteria")SearchPMTask searchPMTask,Model model,HttpSession session) {
		
	    try{
	    	 int page=1;
	    	 Workspace w=(Workspace)session.getAttribute("workspace");
	    	 searchPMTask.setWorkspace(w);
	    	 session.setAttribute("searchTask", searchPMTask);
	    	 pagination(page, model, searchPMTask);
	         model.addAttribute("pmschedules",pmservice.getAllPMSchedules(w));
	         model.addAttribute( "searchPMTask", searchPMTask);
	    
	    }catch(Exception e){
	      
	    }
	    
	     return "PMTaskView";
	  }
	

	
	
	@RequestMapping(value = "/pmReport", params = "_search", method = RequestMethod.POST)
	public String searchPMtaskReportSearch(@ModelAttribute("pmsCriteria") SearchPMTask searchPMTask,
			Model model, HttpSession session, HttpServletRequest req)
	
	{
		int page = Integer.parseInt(req.getParameter("p"));
	
		Workspace workspace = (Workspace) session.getAttribute("workspace");
		searchPMTask.setWorkspace(workspace);
	
		session.setAttribute("searchTask", searchPMTask);
		
        pagination(page, model, searchPMTask);
		
		model.addAttribute("pmschedules", pmtaskservice.getAll(workspace));
		model.addAttribute("pmsCriteria", searchPMTask);
		session.setAttribute("pmReport", "pmReport");
		
		return "pmReport";

	}
	

	@RequestMapping(value="/equipment",method=RequestMethod.GET)
	
	public String getEquipment(Model model,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		model.addAttribute("assetTypeList", assetTypeService.getAll());
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
		model.addAttribute("equipmentSearch", new EquipmentSearchCriteria());	
		return "checklistequipment";
	}
	
	@RequestMapping(value="/equipment",method=RequestMethod.POST)
	public String frEquipmentSearch(@ModelAttribute("equipmentSearch") EquipmentSearchCriteria equipmentSearch,HttpSession session,
			Model model){
		
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		equipmentSearch.setWorkspace(w);
		session.setAttribute("equipmentSearch", equipmentSearch);
		
		model.addAttribute("assetTypeList", assetTypeService.getAll());
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
		model.addAttribute("equipmentSearch", equipmentSearch);
		if(equipmentSearch.getBuilding()!=null)
			model.addAttribute("locationList",locationService.getLocationList(equipmentSearch.getBuilding().getId()));
		if(!equipmentSearch.getEquipmentType().isEmpty()){
			model.addAttribute("assetSubtypeList",assetSubtypeService.getEquipmentSubTypeList(equipmentSearch.getEquipmentType()));
		}
	     return 	"checklistequipment";
      }
	
	
	 @RequestMapping(value={"/getScheduledEquipments"}, method={RequestMethod.GET})
	    public String getForm(Model model, HttpSession session) {
	        Workspace w = (Workspace)session.getAttribute("workspace");
	        model.addAttribute("equipment", (Object)this.assetTypeService.getAssetType());
	        model.addAttribute("buildingList", (Object)this.buildingService.getWorkspaceBuildings(w));
	        model.addAttribute("criterea", (Object)new ScheduledEquipment());
	        return "assetReportForPm";
	    }
	 
	 @RequestMapping(value={"/getScheduledEquipments"}, method={RequestMethod.POST} ,params="submit")
	    public String getschedule(ScheduledEquipment seq, HttpSession session, HttpServletResponse response, Model model, HttpServletRequest req) throws JRException, IOException, ParseException {
	    	
	    	int page = Integer.parseInt(req.getParameter("p"));
			page = (page > 0) ? page : 1;
			from = (ROWS * (page - 1));
			Workspace w = (Workspace) session.getAttribute("workspace");
			seq.setWorkspace(w);
			records = pmtaskservice.count(seq);
			System.out.println("records''''''''''''''''''''''''''");
					System.out.println(records);
			model.addAttribute("submenu", Constants.PMAINTENANCE);
			session.setAttribute("menu", Constants.CMMS);
			total = (int) Math.ceil((double) records / (double) ROWS);
			
			System.out.println(" no " + records + "  pages" + total);
			
			model.addAttribute("total", total);
			model.addAttribute("page", page);

			model.addAttribute("tasks", pmtaskservice
					.getAll(seq, from, ROWS));
			 model.addAttribute("equipment", (Object)this.assetTypeService.getAssetType());
		        model.addAttribute("buildingList", (Object)this.buildingService.getWorkspaceBuildings(w));
			//model.addAttribute("pmschedules", taskInterface
			//	.getAllPMSchedules(w));
			model.addAttribute("criterea",seq);
	    	

	        return "assetReportForPm";
	    	      
	    }
	 
	

	    @RequestMapping(value={"/getScheduledEquipments"}, method={RequestMethod.POST})
	    public void setForm(ScheduledEquipment seq, HttpSession session, HttpServletResponse response) throws JRException, IOException, ParseException {
	        Workspace w = (Workspace)session.getAttribute("workspace");
	        seq.setWorkspace(w);
	        List tasks = this.pmtaskservice.getScheduledEquipment(seq);
	        String name = this.equipmentservice.find(seq.getEquipmentTypeid().longValue()).getAssetSubtype().getAssetType().getAssetTypeName();
	        
	        InputStream jasperStream = this.getClass().getResourceAsStream("/jrxmlTemplates/Electrical.jasper");
	        System.out.println(jasperStream+"-0-00--0-0-00-0--0-0-00-0--0-0-0-0");
	        HashMap params = new HashMap();
	        JRDataSource jrdatasource = tasks.isEmpty() ? new JREmptyDataSource() : new JRBeanCollectionDataSource(tasks);
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	        
	        JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport)jasperReport, params, (JRDataSource)jrdatasource);
	        response.setContentType("application/vnd.ms-excel");
	        String fileName = "ScheduledEquipments";
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
	        ServletOutputStream output = response.getOutputStream();
	        JRXlsxExporter exporter = new JRXlsxExporter();
	        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, (Object)jasperPrint);
	        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, (Object)output);
	        exporter.setParameter((JRExporterParameter)JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, (Object)Boolean.FALSE);
	        exporter.exportReport();
	    }
	    
	    
	    
	    @RequestMapping(value={"/getSubsystem/{id}"}, method={RequestMethod.GET})
	    @ResponseBody
	    public List<AssetSubtype> getsubsystems(@PathVariable(value="id") Long id, HttpServletRequest request, HttpSession session) {
	        return this.assetSubtypeService.getAssetSubtype(id);
	    }

	 
	 
	    @RequestMapping(value={"/getEquipment/{id}"}, method={RequestMethod.GET})
	    @ResponseBody
	    public List<Equipment> getequipment(@PathVariable(value="id") Long id, HttpServletRequest request, HttpSession session) {
	        Workspace w = (Workspace)session.getAttribute("workspace");
	        return this.equipmentservice.getAll(id, w);
	    }
	    
	    @RequestMapping(value = "/ajaxLocationList/{id}", method = RequestMethod.GET)
		@ResponseBody
		public List<Location> ajaxsearchFR(
				@PathVariable(value="id") Long id,
				HttpSession session) {

			System.out.println("in the ajax search checking building values");
			List<Location> locationlist = locationService.getLocationList(id);
			
			if (locationlist.isEmpty()) {
				System.out.println("list is null");

				Location arrayOfLocations[] = new Location[1];
				arrayOfLocations[0] = new Location();
				arrayOfLocations[0].setId(-9999L);
				arrayOfLocations[0].setDescription("empty");

				List<Location> errorLocList = new ArrayList<Location>(
						Arrays.asList(arrayOfLocations));
				return errorLocList;
			}
			return locationlist;
	    }
	    

	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
    public String paginate(@PathVariable("page") int page,Model model,HttpSession session)
	{
		
	   SearchPMTask searchPMTask=(SearchPMTask) session.getAttribute("searchTask");
	   
       pagination(page, model, searchPMTask);
       model.addAttribute("pmsCriteria",searchPMTask);
       
       if(session.getAttribute("pmReport") != null)
    	   return "pmReport";
       	else
       		return "PMTaskView";
    }
	
	
	
	
        private void pagination(int page, Model model, SearchPMTask searchPMTask)
        {
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = (long) pmtaskservice.getSearchCount(searchPMTask);
			total = (int) Math.ceil((double) records / (double) ROWS);
	    	
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			
			pmtaskservice.getSearchResult(searchPMTask, from);
            model.addAttribute("pmTasks", pmtaskservice.getSearchResult(searchPMTask,from));
			
            
        }
	
        @RequestMapping(value = "/{taskid}", method = RequestMethod.GET)
        public String PMtaskModify(@PathVariable("taskid") Long taskid,
            Model model, HttpSession session) throws ParseException {
          
           Task pmTask= pmtaskservice.find(taskid);
           model.addAttribute("taskToUpdate", pmTask);
          return "PMtaskModify"; 
        }

	
    	@RequestMapping(value="/{taskId}/parts",method=RequestMethod.GET)
    	public String part(@PathVariable("taskId") String taskId,Model model){
    		model.addAttribute("partsInWarehouseList", partsInWarehouseService.getAll());
    		model.addAttribute("reservedList", partTransactionService.getReservedParts(taskId));
    		model.addAttribute("issuedList", partTransactionService.getIssuedParts(taskId));
    		model.addAttribute("recievedList", partTransactionService.getRecievedParts(taskId));
    		model.addAttribute("reportTaskId", taskId);
    		if(!model.asMap().containsKey("partTransaction"))
    			model.addAttribute("partTransaction", new PartTransaction());
    		
    		if(model.asMap().containsKey("result"))
    			model.addAttribute("org.springframework.validation.BindingResult.partTransaction", model.asMap().get("result"));
    		
    		return "reservepart";
    	}
        
        
    	
    	
  	  
		@RequestMapping(value = "/equipments/{status}", method = RequestMethod.GET)
		public String getPMSelectedEquipments(@PathVariable String status,Model model, HttpSession session) 
		{
	
			Workspace w = (Workspace) session.getAttribute("workspace");
			model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
			
			List<AssetType> listSystem = assetTypeService.getAll();
			model.addAttribute("assetTypeList", listSystem);
			EquipmentSearchCriteria pe = new EquipmentSearchCriteria();
			pe.setAvailable(status);
			model.addAttribute("equipmentSearch", pe);
			return "getPMSelectedEquipments";
		}
    	
        
        @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
        public String updatePMtask(@ModelAttribute("taskToUpdate") @Valid Task pmTask,
            @RequestParam(value = "completed_time", required = false) String cTime,
            @RequestParam(value = "completed_date", required = false) String cDate,
            BindingResult result,
            RedirectAttributes redirectAttributes, Model model,
            HttpSession session,HttpServletRequest req) throws ParseException {
          
          
          
          if ((!cDate.isEmpty()&& !cTime.isEmpty()))
               {
            
                  String datetime=cDate+" "+cTime;
                  
                  
                  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH : mm"); 
                    Date d = sdf.parse(datetime);
                    
                  pmTask.setCompletedDate(d);
                  
          }else if((cDate.isEmpty())){
                   
                  pmTask.setCompletedDate(null);
          
                   }

          else{
            pmTask.setCompletedDate(null);
          }
          
          
          if (pmTask.getStatus().equals("CLOSED")) {
            pmTask.setEndDate(new Date());

          } else {
            pmTask.setEndDate(null);
            pmTask.setCompletedBy(null);
          }
          
          model.addAttribute("taskToUpdate", pmTask);
          
          if (pmtaskservice.update(pmTask)) {
            redirectAttributes.addFlashAttribute(
                "success",
                "Task updated successfully.");
          } else {
            redirectAttributes.addFlashAttribute("fail",
                "Task updation failed");
          }
          return "redirect:/task/" + pmTask.getId();																																																																																																																																																																																																																											
          
        }
	
	
   @RequestMapping(value = "/checklist/{task}", method = RequestMethod.GET)
	public String ViewPMtask(@PathVariable("task") Long taskId,   Model model,  HttpSession session) {

		 Task pmtask=pmtaskservice.find(taskId);
		 model.addAttribute("task", pmtask);
		
	     pmtask.setCheckLists(pmtaskservice.listallChecklists(pmtask));
	     
		/* for (TaskChecklist taskchecklist : pmtask.getCheckLists()){

		 if (taskchecklist.getDescription().contains("___")) {
			 String newdescription = taskchecklist.getDescription().replaceAll("___",
			                    "<input type='text' style='width:60px'  name='blanks'/>");
			 taskchecklist.setDescription(newdescription);
			   }
		 }*/
			 
			    return "checklistTaskUpdate";
			}
	
   @RequestMapping(value = "/pmReport", method = RequestMethod.GET)
	public String modifyPMreport(Model model, HttpSession session) {
		

		model.addAttribute("pmschedules", pmtaskservice.getAll((Workspace) session.getAttribute("workspace")));
		model.addAttribute("pmsCriteria", new SearchPMTask());
		return "pmReport";

	}	
   
   @RequestMapping(value = "/pmReport", params = "_xls", method = RequestMethod.POST)
	public void searchPMreport(
			@ModelAttribute("pmsCriteria") SearchPMTask searchPMTask,
			Model model, HttpSession session, HttpServletRequest req, HttpServletResponse response) {
		
		
		/* Export to Jasper */
		 List<Task> tasks  = pmtaskservice
		                   .getSearchResult(searchPMTask, from, 10000);
		 
 
		InputStream jasperStream  = this.getClass().getResourceAsStream("/jrxmlTemplates/PMReportGen.jasper");
		  Map<String, Object> params = new HashMap<String, Object>();
		  JRDataSource jrdatasource;
		  if(tasks.isEmpty())
		  {
			  params.put("list", "No data to display");
			  params.put("count",null );
			  jrdatasource = new JREmptyDataSource();
		  }
		 
		  else
		  {
			  params.put("list", "PM Report");
			  params.put("count",null );
			  
			  jrdatasource= new JRBeanCollectionDataSource(tasks);
		  }
		  
		  try {
		  JasperReport jasperReport;
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		
		 
		  JasperPrint jasperPrint;
		
			jasperPrint = JasperFillManager.fillReport(jasperReport, params,jrdatasource);
		
		  
		
			
			
		  response.setContentType("application/vnd.ms-excel");
		  String fileName = "PM Report";
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
		  OutputStream output;
		
			try {
				output = response.getOutputStream();
			
		
		  
		 
		  JRXlsxExporter exporter = new JRXlsxExporter();
		  exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		  
		  exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
			
		  
		  exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
		  exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		  exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  
			} catch (IOException e) 
				{				
				e.printStackTrace();
				}
		  } 
		  		catch (JRException e) 
		  		{				
				e.printStackTrace();
		  		}
		

	}
	

   
  @RequestMapping(value = "/checklistUpdatee", method = RequestMethod.POST)
   public String saveChecklistUpdate(@Valid @ModelAttribute("pmtask") Task task, BindingResult result,
				 @RequestParam(value = "before_image", required = false) MultipartFile before_image,
				 @RequestParam(value = "after_image", required = false) MultipartFile after_image,
				 @RequestParam(value="blanks", required= false) String[] blanks,
				 HttpServletRequest req,Model model,RedirectAttributes redirectAttributes) throws IOException
				    {
				  
				  
	  for(TaskChecklist t:task.getCheckLists()){
	        
	        
	        t.setTask(task);
	      //  t.setDescription(pmtaskservice.getchecklistpropertyontask(t.getId()).get(0).getDescription());
	      //  t.setDescriptionType(pmtaskservice.getchecklistpropertyontask(t.getId()).get(0).getDescriptionType()); 
	      }
				  
				    try{
				      
				    if(!before_image.isEmpty())
				    {
				      
				      String beforeImageName=CommonMethods.randomString(Constants.PMAINTENANCE);
				      if(QrCodeGenerator.uploadImage(before_image, redirectAttributes, beforeImageName)){
				          
				        task.setBeforeImage(beforeImageName);
				        
				      }
				      else{
				        return "redirect:/task/checklist/" + task.getId();
				      }
				    }
				      
				      if(!after_image.isEmpty())
				      {
				        
				        String afterImageName=CommonMethods.randomString(Constants.PMAINTENANCE);
				        if(QrCodeGenerator.uploadImage(after_image, redirectAttributes, afterImageName)){
				          task.setAfterImage(afterImageName);
				        }else{
				          return "redirect:/task/checklist/" + task.getId();
				        }
				        
				      
				             }
				    }catch(Exception e){
				      e.printStackTrace();
				      return "redirect:/task/checklist/" + task.getId();  
				      
				    }
				    
				     
				        String leftImageBytes=""; 
				        String middleImageBytes="";
				        String rightImageBytes="";
				        
				        byte[] leftimg = null;
				        byte[] middleimg=null;
				        byte[] rightimg=null;
				        
				        
				        
				        
				        
				        
				        String left=req.getParameter("leftmg");
				        String middle=req.getParameter("middlemg");
				        String right=req.getParameter("rightmg");
				      
				        
				        String leftimageview=req.getParameter("leftImageView");
				        if(!leftimageview.equals("empty") && !leftimageview.equals(""))
				        if(!left.equals("true")){
				        
				       
				          leftImageBytes=leftimageview;
				          leftimg=javax.xml.bind.DatatypeConverter.parseBase64Binary(leftImageBytes);
				          
				          
				          String atb= "attendedBy"+System.currentTimeMillis()+".png";
				          uploadSignature(leftimg,atb);
				          task.setSrvc_verify_sign1(atb);
				        }
				    
				        
				        String middleimageview=req.getParameter("middleImageView");
				        if(!middleimageview.equals("empty") && !middleimageview.equals(""))
				        if(!middle.equals("true")){
				          
				          
				        middleImageBytes=middleimageview;
				        middleimg=javax.xml.bind.DatatypeConverter.parseBase64Binary(middleImageBytes);
				        String chk= "checkedBy"+System.currentTimeMillis()+".png";
				        uploadSignature(middleimg,chk);
				        task.setSrvc_chkd_sign1(chk);
				        }
				        
				        String rightimageview=req.getParameter("rightImageView");
				        if(!rightimageview.equals("empty") && !rightimageview.equals(""))
				        if(!right.equals("true")){
				          
         
				          rightImageBytes=rightimageview;
				          rightimg=javax.xml.bind.DatatypeConverter.parseBase64Binary(rightImageBytes);
				          String ack= "acknowledgedBy"+System.currentTimeMillis()+".png";
				          uploadSignature(rightimg,ack);
				          task.setSrvc_acknwldg_sign(ack);
				        }
				        
				        if(blanks != null)
				        {
				          if(blanks.length> 0 )
				        {
				        List<String> blanklist = new ArrayList<String> (Arrays.asList(blanks));
				        for (TaskChecklist taskchecklist : task.getCheckLists()) 
				        {
				              int n = StringUtils.countMatches(taskchecklist.getDescription(), "___");
				            
				              for (int i = 0; i < n; i++)
				              {
				              
				               
				                taskchecklist.getBlanks().add(blanklist.get(i));
				                
				              }
				            
				              blanklist.subList(0, n).clear();
				            }
				      
				        }
				          
				          
				        }
				        
				        

				    
				    if(pmtaskservice.update(task))
				    {
				        
				      
				      model.addAttribute("task", task);
				      redirectAttributes.addFlashAttribute("success", "Data saved successfully.");
				      
				          }
				    else{
				    	 redirectAttributes.addFlashAttribute("fail", "Error while saving data.");
				      
				      
				    }
				        return "redirect:/task/checklist/" + task.getId();  
				  
				  }

	@RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
	public void getImage(@PathVariable("imageName")String imageName,Model model,
			HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
	{
		try {
			    InputStream is = new FileInputStream(Constants.PATH + imageName);
				byte[] bytes = IOUtils.toByteArray(is);
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
		else if (imageName.contains(".png")){
			return "image/png";}
		else
			return "image/jpeg";
	}
	
		private void uploadSignature(byte[] img,String imgname) throws IOException {
		  if(!new File(Constants.PATH).exists());
	   		   new File(Constants.PATH).mkdirs();
	   		   
	   		   OutputStream fos= new FileOutputStream(PATH + imgname);
	   		   try {
		    	  fos.write(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
	   		   finally{
	   			   fos.close();
	   		   }
		     
        }
		
}
