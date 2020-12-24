package com.aste.lsme.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.FaultReportSearch;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Message;
import com.aste.lsme.domain.NotificationPayload;
import com.aste.lsme.domain.PartTransaction;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.CostCenterService;
import com.aste.lsme.service.DepartmentService;
import com.aste.lsme.service.DivisionService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.FaultCategoryService;
import com.aste.lsme.service.FaultReportService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MaintainenceGroupService;
import com.aste.lsme.service.MessageServiceInterface;
import com.aste.lsme.service.PartTransactionService;
import com.aste.lsme.service.PartsInWarehouseService;
import com.aste.lsme.service.PriortyService;
import com.aste.lsme.service.TechnicianService;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.utils.CommonMethods;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.google.zxing.WriterException;
 
@Controller
@RequestMapping(value="/faultReport")
public class FaultReportController {

	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	FaultReportService faultReportService;
	@Autowired
	BuildingService buildingService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	PriortyService priorityService;
	@Autowired
	DivisionService divisionService;
	@Autowired
	MaintainenceGroupService maintnceGrpService;
	@Autowired
	FaultCategoryService faultCategoryService;
	@Autowired
	LocationService locationService;
	@Autowired
	CostCenterService costCenterService;
	@Autowired
	TechnicianService technicianService;
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	AssetTypeService assetTypeService;
	@Autowired
	AssetSubtypeService assetSubtypeService;
	@Autowired
	PartsInWarehouseService partsInWarehouseService;
	@Autowired
	PartTransactionService partTransactionService;
	@Autowired
	UserDetailsServiceInterface userService;
	
	@Autowired
	MessageServiceInterface msgService;
	
	
	/****Get First add Fault Report Page*******/
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String get(Model model,HttpSession session) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		fillModel(model, w);
		if(model.asMap().containsKey("faultReport")){
			FaultReport faultReport = (FaultReport) model.asMap().get("faultReport");
			if(faultReport.getBuilding() != null)
				model.addAttribute("locationList",locationService.getLocationList(faultReport.getBuilding().getId()));
		}
		else
		 model.addAttribute("faultReport",new FaultReport());
		
		if(model.asMap().containsKey("result"))
			model.addAttribute("org.springframework.validation.BindingResult.faultReport",model.asMap().get("result"));
		
		return "faultreportcreate";
	}
	/****Post Data of Add Fault Report Page*******/
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String save(@Valid @ModelAttribute("faultReport") FaultReport faultReport,BindingResult result,HttpSession session,
						@RequestParam(value="imageBefore[]",required=false) String[] beforeImage,RedirectAttributes redirectAttributes){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		UserDetail userDetail = (UserDetail) session.getAttribute("user");
		
		if(beforeImage != null)
			if(beforeImage.length > 5){
				result.rejectValue("beforeImage","beforeImage","Maximum no. of Images can be only 5");
			}
		
		if(result.hasErrors()){
			redirectAttributes.addFlashAttribute("faultReport",faultReport);
			redirectAttributes.addFlashAttribute("result", result);
			redirectAttributes.addFlashAttribute("fail","Please fill all fields correctly");
			return "redirect:/faultReport/add";
		} 
		if(beforeImage != null)
			if(beforeImage.length > 0){
				imageUpload(faultReport, beforeImage, redirectAttributes, Constants.BEF_IMG, true);
			} 
		
		faultReport.setActivationTime(LocalDateTime.now());
		faultReport.setCreatedBy(userDetail.getUsername());
		faultReport.setStatus(Constants.OPEN);
		faultReport.setWorkspace(w);
		setFrid(faultReport, faultReportService, w);
		faultReportService.persist(faultReport);
		
		/*---------Notification Code------*/
		FaultReportDto faultDto=new FaultReportDto();
		faultDto.setFrId(faultReport.getFrId());
		faultDto.setBuilding(faultReport.getBuilding());
		faultDto.setLocation(faultReport.getLocation());
		String title="Fault Report is created";
		 String text="Fault Report is created with Fault Report Id :"+" "+faultReport.getFrId() +" " +"in building" +" " +faultReport.getBuilding().getName() + " " +"& location"+" " + faultReport.getLocation().getName();				
		 NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.FAULT_CREATED, faultDto, w.getWorkspaceId(),null);		
         List<Long> technicianIds=new ArrayList<>();		
		List<String> devices=new ArrayList<>();
		for (UserDetail u : faultReport.getAttendedBy()) {
			technicianIds.add(u.getId());
			System.out.println("Befre add");
			saveMessage(u.getId(),title,text,Constants.Type.FAULT_CREATED);
		}
		devices=userService.getDeviceToken(technicianIds);
		
		
		devices.removeIf(Objects::isNull);
		nf.setDevices(devices);
		
		CommonMethods.sendNotification(nf);		
		
		/*------End of Notification Code------*/
		
		redirectAttributes.addFlashAttribute("success","Fault Report created successfully");
		return "redirect:/faultReport/add";
	}

	/****Get Update Fault Report Page*******/
	@RequestMapping(value="/update/{frId}",method=RequestMethod.GET)
	public String  edit(@PathVariable("frId") String frId,Model model,
						HttpSession session,RedirectAttributes redirectAttributes) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		try{
			if(!model.asMap().containsKey("faultReport"))
				model.addAttribute("faultReport",faultReportService.get(frId));
			if(model.asMap().containsKey("result")){
				model.addAttribute("org.springframework.validation.BindingResult.faultReport",model.asMap().get("result"));
			}
		}catch(Exception e){
			 redirectAttributes.addAttribute("fail","Fault Report doesnt exists");
			 return "redirect:/faultReport/search";
		}
		fillModel(model, w);
		FaultReport faultReport = (FaultReport) model.asMap().get("faultReport");
		if(faultReport.getBuilding() != null)
			model.addAttribute("locationList",locationService.getLocationList(faultReport.getBuilding().getId()));
		return "faultreportupdate";
	}
	/****Post data of update Fault Report Page*******/
	@RequestMapping(value="/update/{frId}",method=RequestMethod.POST)
	public String update(@Valid @ModelAttribute("faultReport") FaultReport faultReport,BindingResult result,HttpSession session,
							RedirectAttributes redirectAttributes,Model model,
							@RequestParam(value="imageBefore[]",required=false) String[] beforeImage,
							@RequestParam(value="imageAfter[]",required=false) String[] afterImage) {
		System.out.println(beforeImage+"kkkkkkkkkkkkkkkkkk");
		System.out.println(afterImage+"jjjjjjjjjj");
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		faultReport.setWorkspace(w);
		checkForValidityOnStatus(faultReport, result);
		if(beforeImage!= null)
			if(beforeImage.length>5){
				result.rejectValue("beforeImage","beforeImage","Maximum no. of Images can be only 5");
			}
		if(afterImage!=null)
			if(afterImage.length>5){
				result.rejectValue("afterImage","afterImage","Maximum no. of Images can be only 5");
			}
		if(result.hasErrors()){
			System.out.println(result.getAllErrors()+"llklklkkllkl");
			redirectAttributes.addFlashAttribute("faultReport",faultReport);
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("fail", "Please fill all fields correctly");
			return "redirect:/faultReport/update/"+faultReport.getFrId();
		}
		
		if(afterImage!= null)
			if(afterImage.length>0){
				imageUpload(faultReport, afterImage, redirectAttributes, Constants.AFT_IMG, false);
			}
		if(beforeImage != null)
			if(beforeImage.length>0){
				imageUpload(faultReport, beforeImage, redirectAttributes, Constants.BEF_IMG, true);
			}
		
		faultReportService.update(faultReport); 
		
		fillModel(model, w);
		model.addAttribute("faultReport",faultReportService.get(faultReport.getFrId()));
		model.addAttribute("success", "Fault Report updated successfully");
		if(faultReport.getBuilding() != null)
			model.addAttribute("locationList",locationService.getLocationList(faultReport.getBuilding().getId()));
		return "faultreportupdate";
	}
	
	/****Get Search Fault Report Page*******/
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchGet(Model model,HttpSession session){
		Workspace w = (Workspace) session.getAttribute("workspace");
		fillModel(model, w);
		model.addAttribute("FRSearch", new FaultReportSearch());
		return "faultreportsearch";
	}
	
	/****Post Data of Search Fault Report Page*******/
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String searchPost(@ModelAttribute("FRSearch") FaultReportSearch frSearch,
								HttpSession session,Model model){
		Workspace w = (Workspace) session.getAttribute("workspace");
		frSearch.setWorkspace(w);
		session.setAttribute("FRSearch",frSearch);
		int page=1;
		pagination(page, model,frSearch,true,w);
		fillModel(model, w);
		model.addAttribute("FRSearch", frSearch);
		if(frSearch.getBuilding() != null)
			model.addAttribute("locationList",locationService.getLocationList(frSearch.getBuilding()));
		
		return "faultreportsearch";
	}
	
	/****Get Equipment Search child window in update Fault Report Page*******/
	@RequestMapping(value="/equipment",method=RequestMethod.GET)
	public String getEquipment(Model model,HttpSession session,HttpServletRequest req) {
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		model.addAttribute("assetTypeList", assetTypeService.getAll());
		if(req.getParameter("buildingId")!=null)
		model.addAttribute("building",buildingService.get(Long.parseLong(req.getParameter("buildingId"))));
		if(req.getParameter("locationId")!=null)
		model.addAttribute("location",locationService.get(Long.parseLong(req.getParameter("locationId"))));
		
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
		model.addAttribute("equipmentSearch", new EquipmentSearchCriteria());	
		return "faultreportequipment";
	}

	/****Post Data of Equipment Search child window in update Fault Report Page*******/	
	@RequestMapping(value="/equipment",method=RequestMethod.POST)
	public String frEquipmentSearch(@ModelAttribute("equipmentSearch") EquipmentSearchCriteria equipmentSearch,HttpSession session,
									Model model){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		equipmentSearch.setWorkspace(w);
		session.setAttribute("equipmentSearch", equipmentSearch);
		int page=1;
		pagination(page, model, equipmentSearch,false,w);
		model.addAttribute("assetTypeList", assetTypeService.getAll());
		model.addAttribute("building",equipmentSearch.getBuilding());
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));

		model.addAttribute("equipmentSearch", equipmentSearch);
		if(equipmentSearch.getBuilding()!=null){
			model.addAttribute("location",equipmentSearch.getLocation());
			model.addAttribute("locationList",locationService.getLocationList(equipmentSearch.getBuilding().getId()));
		}
		if(equipmentSearch.getAssetType()!=null){
			model.addAttribute("assetSubtypeList",assetSubtypeService.getEquipmentSubTypeList(equipmentSearch.getEquipmentType()));
		}
			return "faultreportequipment";
	}
	
	/********/
	@RequestMapping(value="/report",method=RequestMethod.GET)
	public String reportDashboard(){
		
		return "frreport";
	}
	
	@RequestMapping(value="/report/search",method=RequestMethod.GET)
	public String frExcelReport(Model model,HttpSession session){
	
		Workspace w = (Workspace) session.getAttribute("workspace");
		fillModel(model, w);
		model.addAttribute("FRSearchExcel", new FaultReportSearch());
		return "frexcelsearch";
	}
	
	@RequestMapping(value="/report/search",method=RequestMethod.POST,params="view")
	public String frExcelReportPost(@ModelAttribute("FRSearchExcel")FaultReportSearch frSearch,Model model,HttpSession session){
	
		Workspace w = (Workspace) session.getAttribute("workspace");
		frSearch.setWorkspace(w);
		session.setAttribute("FRSearchExcel", frSearch);
		fillModel(model, w);
		int page=1;
		pagination(page, model, frSearch, true,w);
		model.addAttribute("FRSearchExcel", frSearch);
		return "frexcelsearch";
	}

	@RequestMapping(value="/report/search",method=RequestMethod.POST,params="excel",produces = "application/pdf;charset=UTF-8")
	public ModelAndView frExcelReportGenerate(@ModelAttribute("FRSearchExcel")FaultReportSearch frSearch,Model model,HttpSession session,ModelMap modelMap) {
		Workspace w = (Workspace) session.getAttribute("workspace");
		frSearch.setWorkspace(w);
		System.out.println("ghsagdshgdhsgd");
		modelMap.put("datasource",faultReportService.getAllFaultReports(from, frSearch));
		modelMap.put("format", "xls");
		
		if(frSearch.getGroupBy().equals("maintenanceGroup")){
			return new ModelAndView("FaultReportMaintenanceGroup",modelMap);
		}
		else if(frSearch.getGroupBy().equals("faultCategory")){
			return new ModelAndView("FaultReportFaultCategory",modelMap);
		}
		else {
			System.out.println("ghsagdshgdhsgd");
			return new ModelAndView("FaultReport",modelMap);
		}
	}
	
	/****Ajax Request for assetSubtype List In equipment Search child window of update fault report*******/
	@RequestMapping(value="/assetSubtype/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<AssetSubtype>> getEquipmentSubtype(@PathVariable("id") Long id) {
		
		return new ResponseEntity<List<AssetSubtype>>(assetSubtypeService.getAssetSubtype(id),HttpStatus.OK);
	}
	
	/****Ajax Request for Location List Fault Report*******/
	@RequestMapping(value="/locations/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Location>> getBuildingLocation(@PathVariable("id") Long id) {
		
		return new ResponseEntity<List<Location>>(locationService.getBuildingLocations(id),HttpStatus.OK);
	}
	
	/****Get Page no in pagination***/
	@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
	public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		if(session.getAttribute("FRSearch")!= null){
			FaultReportSearch frSearch = (FaultReportSearch) session.getAttribute("FRSearch");
			pagination(page, model,frSearch,true,w);
			fillModel(model, w);
			model.addAttribute("FRSearch", frSearch);
			if(frSearch.getBuilding() != null)
				model.addAttribute("locationList",locationService.getLocationList(frSearch.getBuilding()));
			return "faultreportsearch";
		}
		else if(session.getAttribute("FRSearchExcel")!= null){
			FaultReportSearch frSearch = (FaultReportSearch) session.getAttribute("FRSearchExcel");
			pagination(page, model,frSearch,true,w);
			fillModel(model, w);
			model.addAttribute("FRSearchExcel", frSearch);
			if(frSearch.getBuilding() != null)
				model.addAttribute("locationList",locationService.getLocationList(frSearch.getBuilding()));
			return "frexcelsearch";
		}
		else{
			EquipmentSearchCriteria equipmentSearch = (EquipmentSearchCriteria) session.getAttribute("equipmentSearch");
			pagination(page, model,equipmentSearch,false,w);
			model.addAttribute("equipmentSearch", equipmentSearch);
			if(equipmentSearch.getBuilding() != null)
				model.addAttribute("locationList",locationService.getLocationList(equipmentSearch.getBuilding().getId()));
			return "faultreportequipment";
		}
	}
	
	/****Get image for view****/
	@RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
	public static void getImage(@PathVariable("imageName") String imageName,Model model,
			HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
	{
	
		try {
			InputStream is = new FileInputStream(Constants.PATH + imageName);
	
			byte[] bytes = IOUtils.toByteArray(is);
			rep.setContentType(QrCodeGenerator.getContentType(imageName));
			OutputStream os = rep.getOutputStream();
			os.write(bytes);
			os.close();
			is.close();
		} catch (Exception e) {
		}
		
	}
	
	
	/********************FaultReport Part Reserve**************************/
	@RequestMapping(value="/{frId}/parts",method=RequestMethod.GET)
	public String part(@PathVariable("frId") String frId,Model model){
		model.addAttribute("partsInWarehouseList", partsInWarehouseService.getAll());
		model.addAttribute("reservedList", partTransactionService.getReservedParts(frId));
		model.addAttribute("issuedList", partTransactionService.getIssuedParts(frId));
		model.addAttribute("recievedList", partTransactionService.getRecievedParts(frId));
		model.addAttribute("reportTaskId", frId);
		if(!model.asMap().containsKey("partTransaction"))
			model.addAttribute("partTransaction", new PartTransaction());
		
		if(model.asMap().containsKey("result"))
			model.addAttribute("org.springframework.validation.BindingResult.partTransaction", model.asMap().get("result"));
		
		return "reservepart";
	}
	
	
	/*************Additional Methods******************/	
	
	
	//SET Fault Report ID
	private void  setFrid(FaultReport faultReport,FaultReportService faultReportService,Workspace w) {
		
		DateTime d = new DateTime();
		String id = faultReport.getWorkspace().getWorkspaceId().split("-")[1];
		if (!faultReport.getWorkspace().getWorkspaceId().split("-")[2].matches("\\d+"))
			id = id + "-" + faultReport.getWorkspace().getWorkspaceId().split("-")[2];
		faultReport.setFrId("FR-"
				+ id
				+ "-"
				+ StringUtils.leftPad(String.valueOf(d.getMonthOfYear()), 2,
						"0") + d.getYear() + "-"+String.format("%05d", (faultReportService.getMaxId(w)+1)));
	}

	//Fill initial  data in model for FaultReport JSP
	private void fillModel(Model model,Workspace w) {
		model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
		model.addAttribute("departmentList", departmentService.getWorkspaceDepartments(w));
		model.addAttribute("priorityList", priorityService.getWorkspacePriorty(w));
		model.addAttribute("divisionList", divisionService.getWorkspaceDivisions(w));
		model.addAttribute("faultCategoryList", faultCategoryService.getWorkspaceFaultCategory(w));
		model.addAttribute("maintenanceGroupList", maintnceGrpService.getWorkspaceMainGrp(w));
		model.addAttribute("costCenterList", costCenterService.getWorkspaceCostCenter(w));
		model.addAttribute("userDetailList", userService.getUsersOnRole(Constants.ROLE_TECHNICIAN, w.getWorkspaceId()));
		//model.addAttribute("equipmentList",equipmentService.getWorkspaceEquipment(w));
		model.addAttribute("status", Constants.STATUS);

	}
	
	
	//Pagination
	public void pagination(int page,Model model,Object object,boolean b,Workspace w){
			
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			if(b){
				records = (long)faultReportService.getFaultReportCount((FaultReportSearch)object);
			}
			else{
				records = (long)equipmentService.getEquipmentCount((EquipmentSearchCriteria)object,w);

			}
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			if(b){
				model.addAttribute("faultReportList", faultReportService.getFaultReportPaginated(from, (FaultReportSearch)object));
			}
			else{
				model.addAttribute("equipmentList", equipmentService.getEquipmentPaginated(from,(EquipmentSearchCriteria)object, w));
			}
	}
	
	
	private String imageUpload(FaultReport faultReport,String[] images,RedirectAttributes redirectAttributes,String imgType,boolean b){
				
		for(String image:images){
			String imageName = CommonMethods.randomString(imgType);
		if(b)	
			if(QrCodeGenerator.saveImage(image, imageName))
				faultReport.getBeforeImage().add(imageName);
			else
				return "redirect:/faultReport/add";
		else
			if(QrCodeGenerator.saveImage(image, imageName))
				faultReport.getAfterImage().add(imageName);
			else
				return "redirect:/faultReport/update"+faultReport.getFrId();
		}
		return null;
	}
	
	private void checkForValidityOnStatus(FaultReport freport,
			BindingResult result) {
		if (freport.getStatus().equals("Open")){
			checkForOpen(freport, result);
		}	
		else if (freport.getStatus().equals("InProgress")){
			checkForInprog(freport, result);
		}
		else if (freport.getStatus().equals("Closed")){
			checkForClosed(freport, result);
		}
		else if (freport.getStatus().equals("KIV")){
			checkForKIV(freport, result);
		}

	}
	//changed time
	private void checkForClosed(FaultReport freport, BindingResult result) {
		/*
		 * asks for Action Taken, Reason For Outstanding, end date end time also
		 * CLOSE showing labour hours
		 */

		/*freport = checkTimeValidity(freport);
		
		if (freport.getActionTaken() == null
				|| freport.getActionTaken().equals(""))
			result.rejectValue("actionTaken","actionTaken","Action Taken is required on current status");
		if (freport.getStartDate() == null || freport.getEndDate() == null
				|| freport.getStartTime() == null
				|| freport.getEndTime() == null)
			result.rejectValue("startDate","startDate","All the Date and Time Fields are required on current status");
*/
	}
	//changed time
	private void checkForKIV(FaultReport freport, BindingResult result) {
		/*
		 * INPRogress KIV: asks for Action Taken, start time, updated by.
		 */
		
		/*if (freport.getActionTaken() == null
				|| freport.getActionTaken().equals(""))
			result.rejectValue("actionTaken","actionTaken","Action Taken is required on current status");
		if (freport.getStartTime() == null || freport.getStartDate() == null)
			result.rejectValue("startDate","startDate","Start Date and Time fields are required on status Inprogress And KIV");
		if (!(freport.getEndDate() == null)) {
			result.rejectValue("endDate","endDate","End Date And End Time Not required on status In progress Or KIV");
			if (!freport.getReasonForOutstanding().equals(""))
				result.rejectValue("reasonForOutstanding","reasonForOutstanding","ReasonForOutstanding Not required on status In progress Or KIV");
		}*/
	}
	//changed time
	private void checkForInprog(FaultReport freport, BindingResult result) {
		/*
		 * INPRogress KIV: asks for Action Taken, start time, updated by.
		 */
		/*if (freport.getActionTaken() == null
				|| freport.getActionTaken().equals(""))
			result.rejectValue("actionTaken","actionTaken","Action Taken is required on current status");
		if (freport.getStartTime() == null || freport.getStartDate() == null)
			result.rejectValue("startDate","startDate","Start Date and Time fields are required on status Inprogress And KIV");
		if (!(freport.getEndDate() == null)) {
			result.rejectValue("endDate","endDate","End Date And End Time Not required on status In progress Or KIV");
		}
*/
	}
	//changed time
	private void checkForOpen(FaultReport freport, BindingResult result) {
		/*
		 * USER not allowed to enter observation, action taken, reason for
		 * outstanding, remarks, start time, end date , end time updated by when
		 * status is open
		 */
		/*if (!(freport.getActionTaken() == null || freport.getActionTaken()
				.equals("")))
			result.rejectValue("actionTaken","actionTaken","Action Taken is not required on status open");
		if (!(freport.getReasonForOutstanding() == null || freport
				.getReasonForOutstanding().equals("")))
			result.rejectValue("reasonForOutstanding","reasonForOutstanding","ReasonForOutstanding is not required on status open");
		if (!(freport.getEndDate() == null || freport.getStartTime() == null
				|| freport.getEndTime() == null || freport.getStartDate() == null))
			result.rejectValue("startDate","startDate","Date fields are not required on status open");
	*/}
	//changed time
	private FaultReport checkTimeValidity(FaultReport freport) {

		/*try {

			
		  combining Fdate and time values for calculation of difference and
			 * labour hour generation
			 
			Date sDate = new Date(freport.getStartDate().getTime()
					+ freport.getStartTime().getTime());
			Date eDate = new Date(freport.getEndDate().getTime()
					+ freport.getEndTime().getTime());
			if (sDate.before(eDate) || sDate.equals(eDate)) {
				freport.setLabourHrs(convertSecToHours(eDate.getTime() - sDate.getTime()));

			} else {
				freport.setLabourHrs(null);
				freport.setStartTime(null);
				freport.setEndTime(null);
			}

		} catch (Exception e) {
			freport.setLabourHrs(null);
			freport.setStartTime(null);
			freport.setEndTime(null);
		}*/

		return freport;
	}
	
	public String convertSecToHours(Long ms) {
		StringBuffer text = new StringBuffer("");
		long x = 0;
		if (ms >= Constants.HOUR) {
			x = ms / Constants.HOUR;
		}
		if (ms >= Constants.DAY) {
			text.append(ms / Constants.DAY).append(" days ");
			ms %= Constants.DAY;
		}
		if (ms >= Constants.HOUR) {
			text.append(ms / Constants.HOUR).append(" hours ");
			ms %= Constants.HOUR;
		}
		if (ms >= Constants.MINUTE) {
			text.append(ms / Constants.MINUTE).append(" minutes ");
			ms %= Constants.MINUTE;
		}
		System.out.println(text.toString() + " ( " + x + "hours ) ");
		text.append(" ( " + x + "hours ) ");
		return text.toString();
	}
	
	public void saveMessage(long userId,String title,String text,Constants.Type type) {
		
		   LocalDateTime date=LocalDateTime.now();
		   UserDetail user=new UserDetail();
		   user.setId(userId);
		   
		   Message msg=new Message();
		   msg.setCreatedDate(date);
		   msg.setText(text);
		   msg.setTitle(title);
		   msg.setUserDetail(user);
		   msg.setType(type.toString());
		   msgService.persist(msg);
		   System.out.println("after Add");
		
	}
}
