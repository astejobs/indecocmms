package com.aste.lsme.webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Content;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.FaultReportSearch;
import com.aste.lsme.domain.Message;
import com.aste.lsme.domain.NotificationPayload;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.DepartmentService;
import com.aste.lsme.service.DivisionService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.FaultCategoryService;
import com.aste.lsme.service.FaultReportService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MaintainenceGroupService;
import com.aste.lsme.service.MessageServiceInterface;
import com.aste.lsme.service.PriortyService;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.service.WorkspaceService;
import com.aste.lsme.utils.Base64;
import com.aste.lsme.utils.CommonMethods;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.aste.lsme.webservicesDtos.UserDTO;
import com.google.zxing.WriterException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="FaultReportApi", description="Operations pertaining to FaultReport")
@RequestMapping("/api/faultreport")
public class FaultReportApi {
	
	@Autowired
	FaultReportService faultService;
	@Autowired
	WorkspaceService workspaceService;
	
	@Autowired
	DepartmentService deptService;
	@Autowired
	PriortyService priortyService;
	@Autowired
	BuildingService buildingService;
	@Autowired
	LocationService locationService;
	@Autowired
	FaultCategoryService faultCategoryService;
	@Autowired
	MaintainenceGroupService mainGrpService;
	
	@Autowired
	DivisionService divisionService;
	
	@Autowired
	WorkspaceService workService;
	
	@Autowired 
	EquipmentService equipService;
	

	
	@Autowired
	UserDetailsServiceInterface userService;
	
	@Autowired
	MessageServiceInterface msgService;
	
	
	
	/******--------Fault Report Create--------**********/
	
	  @RequestMapping(method=RequestMethod.POST)
	  @ApiOperation(value = "Create FaultReport",notes = "Also  returns the created FaultReport")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})	 
	public ResponseEntity<?> save(@RequestBody FaultReportDto faultReportDto,HttpServletRequest req,@RequestAttribute("username") String username){	
		String w = req.getHeader(Constants.POJECTECTID_HEADER);
		FaultReport fr=new FaultReport();		
		fr=fillModel(fr,faultReportDto,w);
		fr.setActivationTime(LocalDateTime.now());		
		fr.setStatus(Constants.OPEN);
		fr.setCreatedBy(username);
		setFrid(fr, faultService, workService.getworkspaceonid(w));
		try{
		faultService.persist(fr);		
		faultReportDto.setFrId(fr.getFrId());
		
		/***----Notification Code---*****/
		NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.FAULT_CREATED, faultReportDto, w,null);		

		List<Long> technicianIds=new ArrayList<>();		
		List<String> devices=new ArrayList<>();
		for (UserDetail u : fr.getAttendedBy()) {
			technicianIds.add(u.getId());
			saveMessage(u.getId(),nf.getTitle(),nf.getBody(),Constants.Type.FAULT_CREATED);
		}
		devices=userService.getDeviceToken(technicianIds);
		devices.removeIf(Objects::isNull);

		nf.setDevices(devices);
		CommonMethods.sendNotification(nf);
		
		return new  ResponseEntity<FaultReportDto>(faultReportDto, HttpStatus.OK);		
		}catch (Exception e) {
			e.printStackTrace();	
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);		
		}
	}
	
	  	/****--------Save Before Image----*******/
	  
	 @RequestMapping(value="/beforeimage",method=RequestMethod.POST)
	 @ApiOperation(value = "Saving beforeimage to FaultReport")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
	 public ResponseEntity<?> beforeimage(@RequestBody Content faultImage,HttpServletResponse rep){	
		String imageName = CommonMethods.randomString(Constants.BEF_IMG);
		if(saveImage(faultImage.getData(), imageName))
		{
			FaultReport faultReport=faultService.get(faultImage.getId());
		faultReport.getBeforeImage().add(imageName);
		faultService.update(faultReport); 
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else{
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
				
	}
	

	  	/****--------Save After
	  	 *  Image----*******/

	 @RequestMapping(value="/afterimage",method=RequestMethod.POST)
	 @ApiOperation(value = "Saving afterimage to FaultReport")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
	public ResponseEntity<?> afterimage(@RequestBody  Content faultImage,HttpServletResponse rep){	
		String imageName = CommonMethods.randomString(Constants.AFT_IMG);
		List<String> hh=new ArrayList<String>();
		hh.add(imageName);
		if(saveImage(faultImage.getData(), imageName))
		{
			
			FaultReport faultReport=faultService.get(faultImage.getId());
			faultReport.getAfterImage().add(imageName);
			faultService.update(faultReport); 
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
				
	}
		
	 	/****------Find Fault Report on search----***/	
	@RequestMapping(value="/findOne",method=RequestMethod.POST)
	@ApiOperation(value = "Find FaultReport on id",notes = "Retrieves the FaultReport")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})	 
	public ResponseEntity<?> edit(@RequestBody FaultReportSearch frSearch,@RequestHeader("role") String role){
	
		try{
			
			FaultReportDto faultReportDto=faultService.getFaultDtoEdit(frSearch.getFrId());
			
			faultReportDto.setEquipment(equipService.getEquipmentsByFrId(faultReportDto.getFrId()));
		    faultReportDto.setCostCenter(faultService.get(frSearch.getFrId()).getCostCenter());
			faultReportDto.setRemarks(faultService.get(frSearch.getFrId()).getRemarks());
			faultReportDto.setDivision(faultService.get(frSearch.getFrId()).getDivision());
			List<UserDTO> ul=new ArrayList<UserDTO>();
			
			
			for (UserDetail u : faultService.get(frSearch.getFrId()).getAttendedBy()) {
				UserDTO uu=new UserDTO();
				uu.setId(u.getId());
				uu.setFirstName(u.getFirstName());
				uu.setLastName(u.getLastName());
				uu.setUsername(u.getUsername());
				ul.add(uu);
			}		
			faultReportDto.setAttendedBy(ul);
			//Only if fault report does not have equipment than we need to check for location and set fault report editable here
			//For equipment technician have to scan first,than we can show him is it editable or not based on location of technician.
			/*if(faultReportDto.getEquipment()==null){
					Double distanceFromFaultLocation = CommonMethods.getDistance(faultReportDto.getLocation().getGeoLocation(),frSearch.getGeoLocation());
					//check if technician is within the defined location of fault.
					boolean technicianCondition=Constants.LOCATION_FENCE>=distanceFromFaultLocation && Constants.ROLE_TECHNICIAN.equals(role) && (faultReportDto.getStatus().equals(Constants.PAUSE) || faultReportDto.getStatus().equals(Constants.OPEN)); 
					boolean fmConditionForWithinFence=Constants.LOCATION_FENCE>=distanceFromFaultLocation && Constants.ROLE_MANAGING_AGENT.equals(role)&&(faultReportDto.getStatus().equals(Constants.PAUSE_REQUESTED) || faultReportDto.getStatus().equals(Constants.COMPLETED));
					boolean fmConditionForOutsideFence=Constants.LOCATION_FENCE<distanceFromFaultLocation && Constants.ROLE_MANAGING_AGENT.equals(role)&&faultReportDto.getStatus().equals(Constants.PAUSE_REQUESTED);
					if(technicianCondition){
								updateForTechnician(faultReportDto);
								faultReportDto.setEditable(true);
					}
					else if(fmConditionForWithinFence || fmConditionForOutsideFence){
						faultReportDto.setEditable(true);
					}
					
			}*/
			
			if(role.equals(Constants.ROLE_TECHNICIAN)){
				if(faultReportDto.getLocationScanned()){
					faultReportDto.setEditable(true);
					updateForTechnician(faultReportDto);
				}				
			}
			else if(role.equals(Constants.ROLE_MANAGING_AGENT)){
				faultReportDto.setEditable(true);
			}
			
			return new  ResponseEntity<FaultReportDto>(faultReportDto, HttpStatus.OK);		
		}catch (Exception e) {
			e.printStackTrace();	
			return new  ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);		
			
		}
	}
	
	@GetMapping("/scan/location")
	public ResponseEntity<?> locationScan(@RequestBody FaultReportSearch frSearch,@RequestHeader("role") String role){
		FaultReportDto faultReportDto=faultService.getFaultDtoEdit(frSearch.getFrId());
		String existingLocationCode = faultService.findLocationCodeOfFaultReport(frSearch.getFrId());
		if(role.equals(Constants.ROLE_TECHNICIAN)){
			if(existingLocationCode.equals(frSearch.getLocationCode())){
				boolean locationScanned=true;
				faultService.updateFaultReportLocationScanned(frSearch.getFrId(),locationScanned);
				updateForTechnician(faultReportDto);
				return ResponseEntity.ok("Success");
			}
		}
		
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	} 
	
	
	 	/*****-------Update Fault Report------*****/

	 @RequestMapping(method = RequestMethod.PUT)
	 @ApiOperation(value = "Update FaultReport ", notes = "Also returns the updated FaultReport ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})	 
	public ResponseEntity<?> update(@RequestBody FaultReportDto frDTO,@RequestHeader("role") String role,
									HttpServletRequest req, HttpServletResponse response)  {				
		String w = req.getHeader(Constants.POJECTECTID_HEADER);
		String prevStatus;
		FaultReport faultReport=faultService.get(frDTO.getFrId());
		prevStatus=faultReport.getStatus();
		faultReport.setStatus(frDTO.getStatus());	
		faultReport=setRespectiveTimeBasedOnStatus(faultReport);
		 //have to add notifications functionality		
		if(!role.equals(Constants.ROLE_MANAGING_AGENT)){
		//faultReport.setEquipment(new Equipment(frDTO.getEquipment().getId()));	
		faultReport=fillModel(faultReport,frDTO, w);		
		}else{
			faultReport.setRemarks(frDTO.getRemarks());
		}
			
		try{		
				faultService.update(faultReport);			
				if(!prevStatus.equals(frDTO.getStatus()) && (frDTO.getStatus().equals(Constants.COMPLETED) || frDTO.getStatus().equals(Constants.PAUSE)))
			{
					List<UserDTO> usrDto=userService.getDeviceTokenOnRole( Constants.ROLE_MANAGING_AGENT, w);
					List<String> devices=new ArrayList<String>();					 
					if(frDTO.getStatus().equals(Constants.COMPLETED)){
						NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.FAULT_COMPLETED, frDTO, w,null);		
						for (UserDTO userDTO : usrDto) {
							devices.add(userDTO.getDeviceToken());
							saveMessage(userDTO.getId(),nf.getTitle(),nf.getBody(),Constants.Type.FAULT_COMPLETED);
						}
						devices.removeIf(Objects::isNull);

							nf.setDevices(devices);
					CommonMethods.sendNotification(nf);
					}
					else
					{
						NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.FAULT_PAUSED, frDTO, w,null);		
					for (UserDTO userDTO : usrDto) {
							devices.add(userDTO.getDeviceToken());
							saveMessage(userDTO.getId(),nf.getTitle(),nf.getBody(),Constants.Type.FAULT_PAUSED);
						}
					devices.removeIf(Objects::isNull);

						nf.setDevices(devices);
					CommonMethods.sendNotification(nf);
					}
				}
				if(!prevStatus.equals(frDTO.getStatus()) && (frDTO.getStatus().equals(Constants.OPEN))){
					List<Long> technicianIds=new ArrayList<>();
						NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.FAULT_REOPENED, frDTO, w,null);		
					List<String> devices=new ArrayList<>();
					for (UserDTO u : frDTO.getAttendedBy()) {
						technicianIds.add(u.getId());
						saveMessage(u.getId(),nf.getTitle(),nf.getBody(),Constants.Type.FAULT_REOPENED);
					}
					
					devices=userService.getDeviceToken(technicianIds);	
					devices.removeIf(Objects::isNull);

					nf.setDevices(devices);
					CommonMethods.sendNotification(nf);

				}
       
			return new ResponseEntity<FaultReportDto>(frDTO,HttpStatus.OK);		
		}catch (Exception e) {
				e.printStackTrace();	
				return new ResponseEntity<FaultReportDto>(frDTO,HttpStatus.BAD_REQUEST);		
		}
		
	}
	

	  @PostMapping("/equipment")
	  @ApiOperation(value = "Find Fault Report on EquipmentCode ", notes = "Retrieves the FaultReport ")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})	 
	public ResponseEntity<?> getFaultReportOnEquipmentCode(@RequestBody FaultReportSearch frSearch,@RequestHeader("role") String role){
		List<String> statuses = new ArrayList<>();

		//based on role search fault reports by setting respective statuses of each role
		if(role.equals(Constants.ROLE_TECHNICIAN))
			statuses=Arrays.asList(Constants.OPEN,Constants.PAUSE);
		else if(role.equals(Constants.ROLE_MANAGING_AGENT))
			statuses=Arrays.asList(Constants.COMPLETED,Constants.PAUSE_REQUESTED);
		FaultReportDto faultReportDto= faultService.getFaultDtoByEquipmentCode(frSearch.getEquipmentCode(),statuses);
		if(faultReportDto!=null){
			List<UserDTO> uList=userService.getAttendentsOnFrId(faultReportDto.getFrId());
	 	faultReportDto.setAttendedBy(uList);
	 	faultReportDto.setRemarks(faultService.getRemarks(faultReportDto.getFrId()));
		}
		if(role.equals(Constants.ROLE_TECHNICIAN)){
			if(faultReportDto.getLocationScanned()){
				faultReportDto.setEditable(true);
				//updateForTechnician(faultReportDto);
			}
		}
		else if(role.equals(Constants.ROLE_MANAGING_AGENT)){
			faultReportDto.setEditable(true);
		}
			
		/*if(faultReportDto != null){
			List<UserDTO> uList=userService.getAttendentsOnFrId(faultReportDto.getFrId());
		 	faultReportDto.setAttendedBy(uList);
		 	faultReportDto.setRemarks(faultService.get(faultReportDto.getFrId()).getRemarks());
			
		 	Double distanceFromFaultLocation = CommonMethods.getDistance(faultReportDto.getEquipment().getGeoLocation(),frSearch.getGeoLocation());
		 	//check if technician is within the defined location of fault.
		 	if(Constants.EQUIPMENT_FENCE>=distanceFromFaultLocation && role.equals(Constants.ROLE_TECHNICIAN)){
				faultReportDto.setEditable(true);
				updateForTechnician(faultReportDto);
			}//check if agent is at equipment on status completed
		 	else if(Constants.EQUIPMENT_FENCE>=distanceFromFaultLocation && role.equals(Constants.ROLE_MANAGING_AGENT)){
		 		faultReportDto.setEditable(true);
		 	}//check if status is pause requested and agent is not within boundary then agent can still take action i.e fault is editable.
		 	else if(Constants.EQUIPMENT_FENCE<=distanceFromFaultLocation && role.equals(Constants.ROLE_MANAGING_AGENT) && faultReportDto.getStatus().equals(Constants.PAUSE_REQUESTED)){
		 		faultReportDto.setEditable(true);
		 	}
			
		}*/
		return ResponseEntity.ok(faultReportDto);
	}

	 @RequestMapping(value="beforeimage/{frId}",method = RequestMethod.GET)
	 @ApiOperation(value = "Find beforeimages on FaultReport")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})
	public ResponseEntity<?> findBeforeImages(@PathVariable("frId") String frId){
		
			FaultReportDto faultReportDto=new FaultReportDto();
	          faultReportDto.setImages(faultService.getBeforeImages(frId));
			return new  ResponseEntity<FaultReportDto>(faultReportDto, HttpStatus.OK);
			
	}
	
	 @RequestMapping(value="afterimage/{frId}",method = RequestMethod.GET)
	 @ApiOperation(value = "Find afterimages on FaultReport")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})
	public ResponseEntity<?> findAfterImages(@PathVariable("frId") String frId){
			FaultReportDto faultReportDto=new FaultReportDto();
			faultReportDto.setImages(faultService.getAfterImages(frId));
			return new  ResponseEntity<FaultReportDto>(faultReportDto, HttpStatus.OK);		
	}
	
	 
	 
	 /*********-------Upload Quotation----------*****/
	 
	 @PostMapping("/quotationUpload")
	 @ApiOperation(value = "Saving FaultReportFile ", notes = "Retrieves the FaultReportFile ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response =  Content.class)})
	public ResponseEntity<?> fileUpload(@RequestBody  Content quotation,HttpServletRequest req) {
		
		 String w=req.getHeader(Constants.POJECTECTID_HEADER);
		String quotationName=quotation.getId()+Constants.PDF;
		if(CommonMethods.saveFile(quotation.getData(),quotationName, Constants.QUOTATION_PATH)){
			FaultReport faultReport= faultService.get(quotation.getId());
			faultReport.setQuotation(quotationName);
			faultService.update(faultReport);
			FaultReportDto frDto=new FaultReportDto();
			frDto.setFrId(faultReport.getFrId());
				NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.QUOTATION_UPLOADED, frDto, w,null);		

			List<UserDTO> usrDto=userService.getDeviceTokenOnRole( Constants.ROLE_MANAGING_AGENT, w);
			List<String> devices=new ArrayList<String>();
			for (UserDTO userDTO : usrDto) {
					devices.add(userDTO.getDeviceToken());
					saveMessage(userDTO.getId(),nf.getTitle(),nf.getBody(),Constants.Type.QUOTATION_UPLOADED);
				}		
			devices.removeIf(Objects::isNull);

			 nf.setDevices(devices);
			CommonMethods.sendNotification(nf);
		}
		return ResponseEntity.ok(quotation); 
	}
	
	 /*********------- Quotation Status Accepted Or Rejected----------*****/
	 
	 @RequestMapping(value="/quotationupload/status",method=RequestMethod.POST)
	 @ApiOperation(value = "Quotation Status")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
	 public ResponseEntity<?> acceptQuotationUpload(@RequestBody FaultReportDto faultDto){	
			 faultService.quotationStatus(faultDto.getFrId(),faultDto.getQuotationStatus());			
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			
				
	}
	
	 
	 
	 
	 /******--------Search Quotation--------**********/
		@RequestMapping(value="/quotationupload/search",method=RequestMethod.GET)
		 @ApiOperation(value = "Search FaultReports",notes = "Retrieves all the FaultReports for Quotation Upload")
		  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})
		public ResponseEntity<List<FaultReportDto>> searchQuotationupload(@RequestParam("query") String search,@RequestAttribute("username") String username,HttpServletRequest req){
			String w = req.getHeader(Constants.POJECTECTID_HEADER);
			List<FaultReportDto> faultsList=faultService.searchQuotationupload(w,search);
			return new ResponseEntity<List<FaultReportDto>>(faultsList, HttpStatus.OK) ;
		}

	
	 @RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
	 @ApiOperation(value = "Find Image ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success")})
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
	 

		/*-----------Search For Particular User-------------*/
		
			 

		
		
		@RequestMapping(value="/search",method=RequestMethod.GET)
		 @ApiOperation(value = "Search FaultReports",notes = "Retrieves all the FaultReports Of Particular User")
		  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})
		public ResponseEntity<List<FaultReportDto>> searchOnUser(@RequestParam("query") String search,@RequestParam("type") String type,@RequestAttribute("username") String username,@RequestHeader("role") String role,HttpServletRequest req){
			String w = req.getHeader(Constants.POJECTECTID_HEADER);
			List<FaultReportDto> faultsList=faultService.getFaultsOnUser(username,role,w,search,type);
			return new ResponseEntity<List<FaultReportDto>>(faultsList, HttpStatus.OK) ;
		}



		
		
		/*-----------Pause Request----------*/

		 @RequestMapping(value="/pauserequest",method=RequestMethod.POST)
		 @ApiOperation(value = "Request for EOT of a FaultReport")
		 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
		 public ResponseEntity<?> pauseRequest(@RequestBody FaultReportDto faultDto,@RequestAttribute("username") String username,HttpServletRequest req){
			 String w=req.getHeader("workspace");
			 FaultReportDto fDto=faultService.getFaultDto(faultDto.getFrId());
			if(faultService.updateStatus(fDto.getFrId(),Constants.PAUSE_REQUESTED)){
				List<UserDTO> usrDto=userService.getDeviceTokenOnRole( Constants.ROLE_MANAGING_AGENT, w);
				List<String> devices=new ArrayList<String>();
				NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.FAULT_PAUSE_REQUESTED, fDto, w,username);		
				for (UserDTO userDTO : usrDto) {
					devices.add(userDTO.getDeviceToken());
					saveMessage(userDTO.getId(),nf.getTitle(),nf.getBody(),Constants.Type.FAULT_PAUSE_REQUESTED);
				}
				devices.removeIf(Objects::isNull);

				nf.setDevices(devices);
				CommonMethods.sendNotification(nf);	
					return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			}else{
				return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
			}				
		}
		 
		 /****----------------Accept EOT---------*******/
		 
		 @RequestMapping(value="/pauserequest/accept",method=RequestMethod.POST)
		 @ApiOperation(value = "Acception for EOT of a FaultReport")
		 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
		 public ResponseEntity<?> acceptRequest(@RequestBody FaultReportDto faultDto,@RequestAttribute("username") String username,HttpServletRequest req){	
			     String w=req.getHeader("workspace");
				 FaultReportDto fDto=faultService.getFaultDto(faultDto.getFrId());
				 List<UserDTO> techList=userService.getAttendentsOnFrId(fDto.getFrId());			 
				NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.EOT_ACCEPTED, fDto, w,null);		

				 if(faultService.updateStatus(faultDto.getFrId(),Constants.PAUSE)){
					 List<String> devices=new ArrayList<>();
						for (UserDTO u : techList) {
							devices.add(u.getDeviceToken());				
							saveMessage(u.getId(),nf.getTitle(),nf.getBody(),Constants.Type.EOT_ACCEPTED);
						}
						devices.removeIf(Objects::isNull);

						nf.setDevices(devices);
						CommonMethods.sendNotification(nf);	
					return new ResponseEntity<HttpStatus>(HttpStatus.OK);
				}
				else{
					return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
				}
					
		}
		 
		 /********----------Reject EOT--------**********/
		
		 @RequestMapping(value="/pauserequest/reject",method=RequestMethod.POST)
		 @ApiOperation(value = "Rejection for EOT of a FaultReport")
		 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class)})
		 public ResponseEntity<?> rejectRequest(@RequestBody FaultReportDto faultDto,@RequestAttribute("username") String username,HttpServletRequest req){	
			     String w=req.getHeader("workspace");
				 FaultReportDto fDto=faultService.getFaultDto(faultDto.getFrId());
				 List<UserDTO> techList=userService.getAttendentsOnFrId(fDto.getFrId());			 
					NotificationPayload nf=CommonMethods.generatefaultRepNotification(Constants.Type.EOT_REJECTED, fDto, w,null);		

				 if(faultService.updateStatus(faultDto.getFrId(),Constants.OPEN)){
					 List<String> devices=new ArrayList<>();
						for (UserDTO u : techList) {
							devices.add(u.getDeviceToken());				
							saveMessage(u.getId(),nf.getTitle(),nf.getBody(),Constants.Type.EOT_REJECTED);
						}
						devices.removeIf(Objects::isNull);

						nf.setDevices(devices);
						CommonMethods.sendNotification(nf);	
						return new ResponseEntity<HttpStatus>(HttpStatus.OK);
				}else{
					return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
				}
					
		}
		 
		 /**-----Purchase Order-------***/
		 
		 @PostMapping("/purchaseOrder")
		 @ApiOperation(value = "Saving PurchaseOrder ", notes = "Retrieves the Purchase Order ")
		 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response =  Content.class)})
		public ResponseEntity<?> purchaseOrderUpload(@RequestBody  Content purchaseOrder,HttpServletRequest req) {
			
			 String w=req.getHeader(Constants.POJECTECTID_HEADER);
			String purchaseOrderName=purchaseOrder.getId()+Constants.PDF;
			if(CommonMethods.saveFile(purchaseOrder.getData(),purchaseOrderName, Constants.PURCHASEORDER_PATH)){
				FaultReport faultReport= faultService.get(purchaseOrder.getId());
				
				faultReport.setPurchaseOrder(purchaseOrderName);
				faultReport.setDeliveryDate(purchaseOrder.getDeliveryDate());
				faultService.update(faultReport);
				 
			}
			return ResponseEntity.ok(purchaseOrder); 
		}
		 
		 
		 
		

			 /******--------Search PurchaseOrder--------**********/
			@RequestMapping(value="/purchaseOrderupload/search",method=RequestMethod.GET)
			 @ApiOperation(value = "Search FaultReports",notes = "Retrieves all the FaultReports for Purchase Order")
			  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultReportDto.class)})
			public ResponseEntity<List<FaultReportDto>> searchpurchaseOrderupload(@RequestParam("query") String search,@RequestAttribute("username") String username,HttpServletRequest req){
				String w = req.getHeader(Constants.POJECTECTID_HEADER);
				List<FaultReportDto> faultsList=faultService.searchpurchaseOrderupload(w,search);
				return new ResponseEntity<List<FaultReportDto>>(faultsList, HttpStatus.OK) ;
			}
	 
	 
	 
	
	/*
	 * 
	 * Methods
	 * 
	 */
	private FaultReport fillModel(FaultReport faultReport,FaultReportDto faultReportDto,String w){
		
		
      List<UserDetail> attndedBy=new ArrayList<UserDetail>();
		
		if(faultReportDto.getAttendedBy()!=null)
		for (UserDTO user : faultReportDto.getAttendedBy()) {
			UserDetail u=new UserDetail();
			u.setId(user.getId());
			u.setUsername(user.getUsername());
			u.setFirstName(user.getFirstName());
			u.setLastName(user.getLastName());
			attndedBy.add(u);			
			
		}
		
		
		faultReport.setAttendedBy(attndedBy);
			
		faultReport.setWorkspace(workService.getworkspaceonid(w));
		
		if(faultReportDto.getEquipment() != null)
			faultReport.setEquipment(new Equipment(faultReportDto.getEquipment().getId()));

		faultReport.setRequestorName(faultReportDto.getRequestorName());
		faultReport.setRequestorContactNo(faultReportDto.getRequestorContactNo());
		faultReport.setDepartment(faultReportDto.getDepartment());
		faultReport.setPriority(faultReportDto.getPriority());
		faultReport.setBuilding(faultReportDto.getBuilding());
		faultReport.setLocation(faultReportDto.getLocation());
		faultReport.setLocationDesc(faultReportDto.getLocationDesc());
		//locationService.get(faultReportDto.getLocation().getId()).getDescription());
		if(faultReportDto.getDivision()!=null)
			faultReport.setDivision(faultReportDto.getDivision());
		faultReport.setFaultCategory(faultReportDto.getFaultCategory());
		faultReport.setFaultCategoryDesc(faultReportDto.getFaultCategoryDesc());
			 	//faultCategoryService.get(faultReportDto.getFaultCategory().getId()).getDescription());
		faultReport.setMaintGrp(faultReportDto.getMaintGrp());
		faultReport.setActionTaken(faultReportDto.getActionTaken());
		faultReport.setObservation(faultReportDto.getObservation());
		faultReport.setRemarks(faultReportDto.getRemarks());		
		faultReport.setCostCenter(faultReportDto.getCostCenter());
		
		return faultReport;
		
	}
	

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
	

	
	public static boolean saveImage(String bImage, String imageName) {
		String beforeImage = getImageData(bImage);
		byte[] bArray = Base64.decode(beforeImage,Base64.DEFAULT);
		File folder = new File(Constants.PATH);
		if (!folder.exists())
			folder.mkdirs();
		try {
			FileOutputStream fileOs = new FileOutputStream(Constants.PATH+imageName);
			fileOs.write(bArray);
			fileOs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void updateForTechnician(FaultReportDto faultReportDto){
		if(faultReportDto !=null){
			if(faultReportDto.getArrivalTime() == null){
				LocalDateTime arrivalTime = LocalDateTime.now();
				LocalDateTime activationTime = faultReportDto.getActivationTime();
				String responseTime = CommonMethods.durationBetweenTwoDates(activationTime, arrivalTime);
				faultService.updateArrivalAndResponseTime(arrivalTime, responseTime, faultReportDto.getFrId());
			}
			else if(faultReportDto.getStatus().equals(Constants.PAUSE) && faultReportDto.getRestartTime() == null){
				faultService.updateRestartTime(LocalDateTime.now(), faultReportDto.getFrId());
			}
		}
	}
	
	public FaultReport setRespectiveTimeBasedOnStatus(FaultReport faultReport){
		//if(faultReport.getStatus().equals(Constants.PAUSE) && faultReport.getPauseTime() == null)
		//	faultReport.setPauseTime(LocalDateTime.now());
		 if(faultReport.getStatus().equals(Constants.COMPLETED) && faultReport.getCompletionTime() == null)
			faultReport.setCompletionTime(LocalDateTime.now());;
		if(faultReport.getStatus().equals(Constants.CLOSED)&& faultReport.getAcknowledgementTime() == null){
			faultReport.setAcknowledgementTime(LocalDateTime.now());
			faultReport.setDownTime(getDownTime(faultReport));
		}
		
		return faultReport;
	}
	
	public String getDownTime(FaultReport faultReport){
		String totalTime = CommonMethods.durationBetweenTwoDates(faultReport.getArrivalTime(),faultReport.getAcknowledgementTime());
		if(faultReport.getPauseTime() != null){
			String waitingTime = CommonMethods.durationBetweenTwoDates(faultReport.getPauseTime(),faultReport.getRestartTime());
			String[] totalTimeArr = totalTime.split("\\:");
			String[] waitingTimeArr = waitingTime.split("\\:");
			for (int i = 0; i < waitingTimeArr.length; i++) {
				totalTime="";
				totalTime = totalTime.concat(String.valueOf(Integer.valueOf(totalTimeArr[i])-Integer.valueOf(waitingTimeArr[i])));
				if(i<waitingTimeArr.length-1)
					totalTime = totalTime.concat(":");
			}
		}
		
		return totalTime;
	}
	
	public static String getImageData(String beforeImage) {

		System.err.println(beforeImage.split(",").length);
		return beforeImage.split(",").length>1?
				beforeImage.split(",")[1]:
					beforeImage;

	}
	
	 
	@ExceptionHandler
	public void getException(Exception e){
		e.printStackTrace();
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
		   
		
	}
}
