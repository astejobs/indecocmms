package com.aste.lsme.controller;


import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AdhocReport;
import com.aste.lsme.domain.AdhocReportColumnFilterCondition;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Priority;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.SelfHelpReportService;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@Controller
@RequestMapping("/selfHelp")
public class SelfHelpReportController {
	
	@Autowired
	SelfHelpReportService selfhelpservice;
	
	@RequestMapping(value="/AdhocReports",method=RequestMethod.GET)
	public String selfhelp(){
		return "adhocreport";
	}
	
	@RequestMapping(value="/CreateReport",method=RequestMethod.GET)
	public String createReport(Model model)
	{
		if(!model.containsAttribute("adhocreport")){
			model.addAttribute("adhocreport", new AdhocReport());
		}
		
	   if(model.asMap().containsKey("error"))
	       model.addAttribute("org.springframework.validation.BindingResult.adhocreport", 
    		   model.asMap().get("error"));
	 
	   model.addAttribute("reporttype", selfhelpservice.getReportTypes());
	    return "CreateSelfHelpReport";
	}
	
	@RequestMapping(value="/CreateReport",method=RequestMethod.POST)
	public String saveReport(@Valid AdhocReport adhocreport,BindingResult result,Model model,RedirectAttributes attr,HttpSession session)
	{
		if(result.hasErrors()){
			attr.addFlashAttribute("adhocreport", adhocreport);
			attr.addFlashAttribute("error", result);
			return "redirect:/selfHelp/CreateReport";
			
		}
	    try{
	     adhocreport.setWorkspace((Workspace) session.getAttribute("workspace"));
	     adhocreport.setUser((UserDetail) session.getAttribute("user")); 
	     selfhelpservice.addAdhocReport(adhocreport);
		 model.addAttribute("adhocreport", adhocreport);
		 model.addAttribute("listOfColumns", selfhelpservice.getColumns());
		 return "DesignSelfHelpReport";
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	      attr.addFlashAttribute("error", "The Adhoc Report Name Already Exists");
	      attr.addFlashAttribute("adhocreport", adhocreport);
	      return "redirect:/selfHelp/CreateReport";
	    	
	    }
	   
	}
	
	@RequestMapping(value="/BuildReport",method=RequestMethod.POST)
	public String createDesign(AdhocReport adhocreport,RedirectAttributes model){
		  
		 try{
		  adhocreport.setCreatedDate(new Date());
		  model.addFlashAttribute("success", "Report Created Sucessfully");
		 selfhelpservice.updateAdhocReport(adhocreport);
		 }catch(Exception ex) {
			 ex.printStackTrace();
			 model.addFlashAttribute("error", "Cannot Create Report");
			 
		 }
		 return "redirect:/selfHelp/CreateReport";
		
		
	}
	
	@RequestMapping(value="/viewAdhocReports",method=RequestMethod.GET)
	public String viewSelfHelpreport(HttpSession session,Model model){
		Workspace w=(Workspace) session.getAttribute("workspace");
		model.addAttribute("reporttype", selfhelpservice.getReportTypes());
		model.addAttribute("listofAdhocReports",selfhelpservice.getWorkspacebasedAdhocReports(w));
	    return "viewSelfhelpReports";
		
	}
	
	@RequestMapping(value="/editadhocReport/{id}",method=RequestMethod.GET)
	public String editSelfHelpreport(@PathVariable("id") Integer id, HttpSession session,Model model){
		AdhocReport adreport = selfhelpservice.findAdhocByid(id);
		model.addAttribute("adhocreport", adreport);
		model.addAttribute("listOfColumns", selfhelpservice.getColumns());
	    return "editadhocreport";
		
	}
	
	@RequestMapping(value="/updateadhocReport",method=RequestMethod.POST)
	public String updateSelfHelpreport(@Valid AdhocReport adhocreport,BindingResult result, HttpSession session,Model model){
		
		if(result.hasErrors()){
			model.addAttribute("listOfColumns", selfhelpservice.getColumns());
			model.addAttribute("adhocreport", adhocreport);
			model.addAttribute("error", result);
			return "editadhocreport";
			
		}
		try {
			adhocreport.setModifiedDate(new Date());
			selfhelpservice.editSelfhelpReport(adhocreport);
			model.addAttribute("success", "Adhoc Report Updated Sucessfully");
		} catch (Exception e) {
			model.addAttribute("fail", "Cannot Update AdhocReport Sucessfully");
			e.printStackTrace();
		}
		
	    
		return "redirect:/selfHelp/viewAdhocReports";
		
	}
	@RequestMapping(value="/deleteadhocReport/{id}",method=RequestMethod.GET)
	public String deleteSelfHelpreport(@PathVariable("id") Integer id){
		selfhelpservice.deleteAdhocReport(id);
		return "redirect:/selfHelp/viewAdhocReports";
	}
	
	@RequestMapping(value="runadhocReport",params="html", method=RequestMethod.GET)
	public String getSearch(Model model,@RequestParam("id") Integer id,@RequestParam("d1") String d1,
			@RequestParam("d2") String d2,HttpSession session){
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		Date startdate = null;
		Date enddate = null;
		try {
			startdate = fmt.parse(d1);
			enddate = fmt.parse(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Workspace w=(Workspace) session.getAttribute("workspace");
	   AdhocReport adreport = selfhelpservice.findAdhocByid(id);
		List<Object[]> faultreports= selfhelpservice.search(adreport, startdate, enddate,null);
		List<String> collist = new ArrayList<String>();
		for (AdhocReportColumnFilterCondition col : adreport.getColumnList()) {
			if(!col.getTableColumn().getColumnDesc().equals("Before Image") && !col.getTableColumn().getColumnDesc().equals("After Image"))
				collist.add(col.getTableColumn().getColumnDesc());
		}
		model.addAttribute("reporttype", selfhelpservice.getReportTypes());
		model.addAttribute("listofAdhocReports",selfhelpservice.getWorkspacebasedAdhocReports(w));
		model.addAttribute("tableheaders", collist);
		model.addAttribute("faultreplist", faultreports);
		return "viewSelfhelpReports";
	}
	
	@RequestMapping(value="runadhocReport",params="xls", method=RequestMethod.GET)
	public void getReport(Model model,@RequestParam("id") Integer id,@RequestParam("d1") String d1,
			@RequestParam("d2") String d2,HttpSession session,
			             HttpServletResponse response,HttpServletRequest request){
		
		List<FaultReport> faultReportList;
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
		Date startdate = null;
		Date enddate = null;
		try {
			startdate = fmt.parse(d1);
			enddate = fmt.parse(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   AdhocReport adreport = selfhelpservice.findAdhocByid(id);
		List<Object[]> faultreports= selfhelpservice.search(adreport, startdate, enddate,"report");
		if(faultreports.isEmpty()){
			 String baseUrl = String.format("%s://%s:%d/tasks/",request.getScheme(),  request.getServerName(), request.getServerPort());
			 System.out.println(baseUrl);
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Long> listofIds = new ArrayList<Long>();
		for (Object[] objects : faultreports) {
			listofIds.add((Long) objects[0]); 
		}
		
		faultReportList = selfhelpservice.getFaultReportsOnId(listofIds);
		for (FaultReport fr : faultReportList) {
			fr.getEquipment().setToStringMethod(true); 
		 }
		
		
		//Defining Style for the Column Headers
		 Style headerStyle = new Style();
	     headerStyle.setBackgroundColor(Color.CYAN);
		 headerStyle.setBorderBottom(Border.THIN());
	 	 headerStyle.setBorderColor(Color.black);
	 	 headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		 headerStyle.setTransparency(Transparency.OPAQUE);
		 
		 //Defining Style  for Title
		 Style titlestyle = new Style();
		 titlestyle.setHorizontalAlign(HorizontalAlign.CENTER);
		 titlestyle.setFont(Font.ARIAL_BIG);
		 
		DynamicReportBuilder drb = new DynamicReportBuilder();
		for (AdhocReportColumnFilterCondition col : adreport.getColumnList()) {
			 if(col.getTableColumn().getColumnFilterInputSource().equals("Reference Table")){
                 if(col.getTableColumn().getColumnName().equals("locId")){
                	 AbstractColumn column = ColumnBuilder.getNew()
         			        .setColumnProperty("name", Location.class.getName())
         			        .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
         			        .build();
         			
         		            drb.addColumn(column);
                     }
                
		    	 if(col.getTableColumn().getColumnName().equals("deptId")){
		    		 AbstractColumn column = ColumnBuilder.getNew()
	         			        .setColumnProperty("department", Department.class)
	         			        .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
	         			        .build();
	         			
	         		            drb.addColumn(column);
		    	    }
		             
		    	 if(col.getTableColumn().getColumnName().equals("bldgId")){
		    		 AbstractColumn column = ColumnBuilder.getNew()
	         			        .setColumnProperty("building", Building.class)
	         			        .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
	         			        .build();
	         			
	         		            drb.addColumn(column);
		    		 
		    	   }
		    		
		    	 if(col.getTableColumn().getColumnName().equals("faultCodeId")){
		    		 AbstractColumn column = ColumnBuilder.getNew()
	         			         .setColumnProperty("faultCategory", FaultCategory.class)
	         			         .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
	         			         .build();
	         			
	         		            drb.addColumn(column);
		    	 }
		    		
		    	 if(col.getTableColumn().getColumnName().equals("priorityId")){
		    		 AbstractColumn column = ColumnBuilder.getNew()
	         			         .setColumnProperty("priority", Priority.class)
	         			         .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
	         			         .build();
	         			
	         		            drb.addColumn(column);
		    	 }
		    		
		    	 if(col.getTableColumn().getColumnName().equals("costCenter")){
		    		 AbstractColumn column = ColumnBuilder.getNew()
	         			         .setColumnProperty("costCenter", CostCenter.class)
	         			         .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
	         			         .build();
	         			
	         		            drb.addColumn(column);
		    	 }
		    		
		    	 if(col.getTableColumn().getColumnName().equals("equipment")){
		    		 AbstractColumn column = ColumnBuilder.getNew()
	         			         .setColumnProperty("equipment", Equipment.class)
	         			         .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
	         			         .build();
	         			
	         		            drb.addColumn(column); 
		    	 }
			 }
			 else if(col.getTableColumn().getColumnFilterInputType().equals("datepicker")){
				  AbstractColumn column = ColumnBuilder.getNew()
					        .setColumnProperty(col.getTableColumn().getColumnName(), Date.class.getName())
					        .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
					         .build();
					
				            drb.addColumn(column); 
			  }	 
		    else{	
			   AbstractColumn column = ColumnBuilder.getNew()
			        .setColumnProperty(col.getTableColumn().getColumnName(), String.class.getName())
			        .setTitle(col.getTableColumn().getColumnDesc()).setWidth(90)
			         .build();
			
		            drb.addColumn(column);
			   }
			 
		}
		drb.setDefaultStyles(titlestyle, null, headerStyle, null);
	    drb.setPrintColumnNames(true);
	    drb.setMargins(0, 0, 0, 0);
		drb.setTitle(adreport.getReportName());
	    drb.setSubtitle("This report was generated at " + new Date());
		drb.setIgnorePagination(true);
		drb.setUseFullPageWidth(true);
		DynamicReport dr = drb.build();
		JRDataSource ds = new JRBeanCollectionDataSource(faultReportList);    //Create a JRDataSource, the Collection used
		response.setContentType("application/vnd.ms-excel");
		  String fileName = UUID.randomUUID().toString();
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
		  OutputStream output;

       JasperPrint jp;
	  try {
		jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
		JRXlsExporter exporter = new JRXlsExporter();
		output = response.getOutputStream();
       

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
        //Excel specific parameter
        
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
	    exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	    exporter.exportReport();
	   	

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}    //Creates the JasperPrint object, we pass as a Parameter
    catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
		
	}
	
	@RequestMapping(value="getreports/{id}",method=RequestMethod.GET)
	public @ResponseBody List<AdhocReport> getReportsOnType(@PathVariable("id") Long id,HttpSession session){
		Workspace w=(Workspace) session.getAttribute("workspace");
		return selfhelpservice.getAdhocOnType(id,w);
		
		
	}
	
	
	
	

}
