package com.aste.lsme.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;

@Controller
@RequestMapping("/asset")
public class EquipmentController {
	
	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;

	Long records=0L;
	
	@Autowired
	LocationService locService;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	AssetSubtypeService assetSubtypeService;

	@Autowired
	BuildingService buildingService;
	
	@Autowired
	AssetTypeService systemService;
	
	@RequestMapping(value="/general",method=RequestMethod.GET)
	public String getGeneral() {
		
		
		
		return "equipmentreportgeneral";
	}
	
	@RequestMapping(value="/general",method=RequestMethod.POST)	
	public ModelAndView postGeneral() {
		
		
		
		return new ModelAndView();
	}
	
	@RequestMapping(value="/assetReport",method=RequestMethod.GET)	
	public String getAssetReports() {
		
		return "assetReport";
	}
	
	@RequestMapping(value="/assetReportGeneral",method=RequestMethod.GET)	
	public String getGeneralAssetReport(Model model) {
		
		model.addAttribute("equipmentSearch",new EquipmentSearchCriteria());
		model.addAttribute("buildingList",buildingService.getAll());
		model.addAttribute("assetTypeList",systemService.getAll());
		return "assetReportGeneral";
	}
	
	
	@RequestMapping(value="/assetReportByAge",method=RequestMethod.GET)	
	public String getAssetReportByAge(Model model) 
	{
				
		model.addAttribute("equipmentSearch",new EquipmentSearchCriteria());
		model.addAttribute("buildingList",buildingService.getAll());
		return "assetReportbyAge";
	}
	
	@RequestMapping(value="/assetReportByLifeSpan",method=RequestMethod.GET)	
	public String getAssetReportByLifeSpan(Model model) {
		
		model.addAttribute("equipmentSearch",new EquipmentSearchCriteria());
		model.addAttribute("buildingList",buildingService.getAll());
		return "assetReportByLifeSpan";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST,params ="submit")
	public String searchCivil(@ModelAttribute("equipmentSearch") EquipmentSearchCriteria esc,HttpServletRequest req,   Model model,HttpSession session,RedirectAttributes redirectAttributes) throws ParseException 
	{
		
		/*Used to check on two scenarios ie "from age" and "upto age"*/
		
		if(req.getParameter("checker").equals("from"))
		{
				
			try {
					if(!esc.getFromAge().equals(null))
					{
					
					Calendar c = Calendar.getInstance();
					c.add(Calendar.YEAR, (-esc.getFromAge()));
					
									
					DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
					String d=c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR);
					
					Date fromDate = sourceFormat.parse(d);
					
					esc.setFromDate(fromDate);
					
				    redirectAttributes.addFlashAttribute("fromAgee","fromAgee");
					
					}
				}catch (NullPointerException e) {
				 esc.setFromAge(0);
			}
				
					
		}
		else if(req.getParameter("checker").equals("to"))
		{	
			
			try
			{
			
				if(!esc.getToAge().equals(null) && !esc.getFromAge().equals(null))
				{
				
								
				Calendar c1 = Calendar.getInstance();
				c1.add(Calendar.YEAR, (-esc.getToAge()));
								
				
				Calendar c2 = Calendar.getInstance();
				c2.add(Calendar.YEAR, (-esc.getFromAge()));
				
			
				DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				String d=c1.get(Calendar.DATE)+"-"+c1.get(Calendar.MONTH)+"-"+c1.get(Calendar.YEAR);
				String d2=c2.get(Calendar.DATE)+"-"+c2.get(Calendar.MONTH)+"-"+c2.get(Calendar.YEAR);
				
				
				Date toDate = sourceFormat.parse(d);
				
				Date fromDate = sourceFormat.parse(d2);
				
				
				esc.setToDate(toDate);
				esc.setFromDate(fromDate);
				
			    redirectAttributes.addFlashAttribute("toAgee","toAgee");
			    redirectAttributes.addFlashAttribute("fromAgee","fromAgee");
				}
		
			}catch(NullPointerException e)			
			{
				 esc.setFromAge(0);
				 esc.setToAge(0);
			}
		}
		

		Workspace w =(Workspace) session.getAttribute("workspace");
		int page=1;
		esc.setWorkspace(w);
		session.setAttribute("EquipmentSearchList", esc);
		pagination(page, model, esc,w,redirectAttributes);		
		redirectAttributes.addFlashAttribute("equipmentSearch", esc);
		redirectAttributes.addFlashAttribute("buildingList",buildingService.getAll());
		redirectAttributes.addFlashAttribute("assetSubType",assetSubtypeService.getEquipmentSubTypeList(getGeneral()));
		
				try
				{
					redirectAttributes.addFlashAttribute("locationList",locService.getLocationList(esc.getBuilding().getId()));
				}
					catch(Exception e)
					{
						return "redirect:/asset/"+req.getParameter("param");		
					}
		
	return "redirect:/asset/"+req.getParameter("param");
	
	}
	
	
	@RequestMapping(value="/search",method=RequestMethod.POST,params="excel")
	public ModelAndView frExcelReportGenerate(@ModelAttribute("equipmentSearch")EquipmentSearchCriteria esc, 	
																Model model,HttpSession session,ModelMap modelMap)
	{
		
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		esc.setWorkspace(w);
	
		modelMap.put("datasource",equipmentService.getAllEquipments(esc));
		modelMap.put("format", "xls");
		
		
		return new ModelAndView("AcmvReport",modelMap);

					
			 
		}
	
	@RequestMapping(value="/assetSubtype/{id}",method=RequestMethod.GET)
	@ResponseBody
	public List<AssetSubtype> getAssetSubtype(@PathVariable("id")  String id,HttpSession  session){
		
	
		List<AssetSubtype> a = assetSubtypeService.getEquipmentSubTypeList(id);
		System.err.println(a.size()+"------------------------------------------------------");
		return a;
					
	}
	
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		
		
		Workspace w =(Workspace) session.getAttribute("workspace");
		EquipmentSearchCriteria esc = (EquipmentSearchCriteria) session.getAttribute("EquipmentSearchList");
		pagination(page, model,esc,w,redirectAttributes);
		model.addAttribute("equipmentSearch", esc);
		return "assetReportGeneral";
	}
	
	
	public void pagination(int page,Model model,EquipmentSearchCriteria esc,Workspace w,RedirectAttributes redirectAttributes){
		
		page = (page > 0) ? page : 1;
		from = ROWS*(page-1);
		records = (long) equipmentService.getEquipmentCount(esc,w);
		total = (int) Math.ceil((double) records / (double) ROWS);
		redirectAttributes.addFlashAttribute("total", total);
		redirectAttributes.addFlashAttribute("page", page);
		redirectAttributes.addFlashAttribute("listSearch",equipmentService.getEquipmentPaginated(from, esc,w));
	}


	
}
