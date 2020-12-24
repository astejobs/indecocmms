package com.aste.lsme.webservices;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.CostCenterService;
import com.aste.lsme.service.DepartmentService;
import com.aste.lsme.service.DivisionService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.FaultCategoryService;
import com.aste.lsme.service.FaultReportService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.MaintainenceGroupService;
import com.aste.lsme.service.PriortyService;
import com.aste.lsme.service.TechnicianService;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.utils.CommonMethods;
import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.UserDTO;
import com.google.zxing.WriterException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="GeneralApi", description="General Operations ")
@RequestMapping("/api/general")
public class GeneralApi{
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
	TechnicianService technicianService;
	@Autowired
	CostCenterService costCenterService;
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	UserDetailsServiceInterface userService;
	
	
	@RequestMapping(value="/departments/{workspaceId}", method=RequestMethod.GET)
	 @ApiOperation(value = "Find Departments on Workspace id ",notes = "Retrieves  list of Departments  ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Department.class, responseContainer = "List"),
		                	 @ApiResponse(code = 500, message = "Server error"),
		                	 @ApiResponse(code = 401, message = "Unauthorized Access"),
		                	 @ApiResponse(code = 403, message = "Forbidden"),
		                	 @ApiResponse(code = 404, message = "Service not found")})	 
	public ResponseEntity<?> departments(@PathVariable String workspaceId){	
	
		    Workspace w=new Workspace();
		    w.setWorkspaceId(workspaceId);
			return new ResponseEntity<List<Department>>(departmentService.getWorkspaceDepartments(w), HttpStatus.OK);		
 
	}
	
	
	 @RequestMapping(value="/priorty/{workspaceId}", method=RequestMethod.GET) 
	 @ApiOperation(value = "Find Priority on Workspace id ", notes = "Retrieves  list of Priorities ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Priorty.class, responseContainer = "List"),
			                 @ApiResponse(code = 500, message = "Server error"),
			                 @ApiResponse(code = 401, message = "Unauthorized Access"),
			                 @ApiResponse(code = 403, message = "Forbidden"),
			                 @ApiResponse(code = 404, message = "Service not found")})	 
	public ResponseEntity<?> priorty(@PathVariable String workspaceId){	
		   
		    Workspace w=new Workspace();
		    w.setWorkspaceId(workspaceId);
			return new ResponseEntity<List<Priorty>>(priorityService.getWorkspacePriorty(w), HttpStatus.OK);		
   }
	
	
	
	 @RequestMapping(value="/divisions/{workspaceId}", method=RequestMethod.GET)
	 @ApiOperation(value = "Find Division on Workspace id ",notes = "Retrieves list of Divisions ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Division.class, responseContainer = "List"),
		                	 @ApiResponse(code = 500, message = "Server error"),
		                	 @ApiResponse(code = 401, message = "Unauthorized Access"),
		                	 @ApiResponse(code = 403, message = "Forbidden"),
		                	 @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> division(@PathVariable String workspaceId){	
	    
		    Workspace w=new Workspace();
	     	w.setWorkspaceId(workspaceId);
			return new ResponseEntity<List<Division>>(divisionService.getWorkspaceDivisions(w), HttpStatus.OK);		

	}
	
	
	 @RequestMapping(value="/buildings/{workspaceId}", method=RequestMethod.GET)
	 @ApiOperation(value = "Find Building on Workspace id ", notes = "Retrieves list of Buildings  ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Building.class, responseContainer = "List"),
			                 @ApiResponse(code = 500, message = "Server error"),
			                 @ApiResponse(code = 401, message = "Unauthorized Access"),
			                 @ApiResponse(code = 403, message = "Forbidden"),
			                 @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> buildings(@PathVariable String workspaceId){	
	
		    Workspace w=new Workspace();
		    w.setWorkspaceId(workspaceId);
			return new ResponseEntity<List<Building>>(buildingService.getWorkspaceBuildings(w), HttpStatus.OK);		

	}
	
	
	 @RequestMapping(value="/locations/{buildId}", method=RequestMethod.GET)
	 @ApiOperation(value = "Find Locations on Building id ",notes = "Retrieves  list of Locations  ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Location.class, responseContainer = "List"),
			                 @ApiResponse(code = 500, message = "Server error"),
			                 @ApiResponse(code = 401, message = "Unauthorized Access"),
			                 @ApiResponse(code = 403, message = "Forbidden"),
			                 @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> locations(@PathVariable Long buildId){	
		
		return new ResponseEntity<List<Location>>(locationService.getBuildingLocations(buildId), HttpStatus.OK);		
 
	}

	
	@RequestMapping(value="/faultCategories/{workspaceId}", method=RequestMethod.GET)
	 @ApiOperation(value = "Find FaultCategory on Workspace id ", notes = "Retrieves list of FaultCategory  ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = FaultCategory.class, responseContainer = "List"),
			                 @ApiResponse(code = 500, message = "Server error"),
			                 @ApiResponse(code = 401, message = "Unauthorized Access"),
			                 @ApiResponse(code = 403, message = "Forbidden"),
			                 @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> faultCategory(@PathVariable String workspaceId){	
	
		    Workspace w=new Workspace();
		    w.setWorkspaceId(workspaceId);
			return new ResponseEntity<List<FaultCategory>>(faultCategoryService.getWorkspaceFaultCategory(w), HttpStatus.OK);		
   }
	
	
	 @RequestMapping(value="/maintenanceGrpCategory/{workspaceId}",method=RequestMethod.GET)
	 @ApiOperation(value = "Find MaintainenceGroup on Workspace id ",notes = "Retrieves list of MaintainenceGroups  ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MaintainenceGroup.class, responseContainer = "List"),
			                 @ApiResponse(code = 500, message = "Server error"),
			                 @ApiResponse(code = 401, message = "Unauthorized Access"),
			                 @ApiResponse(code = 403, message = "Forbidden"),
			                 @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> maintaingrpCategory(@PathVariable String workspaceId){	
		
		    Workspace w=new Workspace();
		    w.setWorkspaceId(workspaceId);
			return new ResponseEntity<List<MaintainenceGroup>>(maintnceGrpService.getWorkspaceMainGrp(w), HttpStatus.OK);		

	 }
	 
	
	@RequestMapping(value="/technicians",method=RequestMethod.GET)
	@ApiOperation(value = "Find Technicians on Workspace id ",notes = "Retrieves list of Technicians  ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserDTO.class, responseContainer = "List"),
			                @ApiResponse(code = 500, message = "Server error"),
			                @ApiResponse(code = 401, message = "Unauthorized Access"),
			                @ApiResponse(code = 403, message = "Forbidden"),
			                @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> getTechnicians(HttpServletRequest request){
	
		String w =request.getHeader(Constants.POJECTECTID_HEADER);
		return new ResponseEntity<List<UserDTO>>(userService.getUsersOnRole(Constants.ROLE_TECHNICIAN,w),HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/costcenter",method=RequestMethod.GET)
	@ApiOperation(value = "Find CostCenter on Workspace id ", notes = "Retrieves list of CostCenters ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response =CostCenter.class, responseContainer = "List"),
    		                @ApiResponse(code = 500, message = "Server error"),
    		                @ApiResponse(code = 401, message = "Unauthorized Access"),
    		                @ApiResponse(code = 403, message = "Forbidden"),
    		                @ApiResponse(code = 404, message = "Service not found")})
	public ResponseEntity<?> getCostCenter(HttpServletRequest request){
	
		String w =request.getHeader(Constants.POJECTECTID_HEADER);
		System.out.println("workspaceeeeeeeeeee="+w);
		return new ResponseEntity<List<CostCenter>>(costCenterService.findCostCenter(w),HttpStatus.OK);

	}
	
	
	@RequestMapping(value="/building/{bId}/location/{lId}",method=RequestMethod.GET)
	@ApiOperation(value = "Find Equipments on Building and Location id ")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EquipmentDTO.class, responseContainer = "List"),
			                @ApiResponse(code = 500, message = "Server error"),
			                @ApiResponse(code = 401, message = "Unauthorized Access"),
			                @ApiResponse(code = 403, message = "Forbidden"),
			                @ApiResponse(code = 404, message = "Service not found")})
	public  ResponseEntity<?> equipmentListOnBuildingAndLocation(@PathVariable("bId") long buildingId,@PathVariable("lId") long locationId){

		return new ResponseEntity<List<EquipmentDTO>>(equipmentService.getEquipmentsByBuildingIdAndLocationId(buildingId, locationId),HttpStatus.OK);

		
	}
	
	@RequestMapping(value="/techniciansearch",method=RequestMethod.GET)
	@ApiOperation(value = "Find Technicians on Search ", 
	notes = "Retrieves list of Technicians  ")
	public ResponseEntity<?> getTechnicians(@RequestParam("query") String search,HttpServletRequest request){
	
		String w =request.getHeader(Constants.POJECTECTID_HEADER);
		return new ResponseEntity<List<UserDTO>>(userService.getUsersOnSearch(w,search),HttpStatus.OK);
	}
	
	@RequestMapping(value ="/setlocationcodefor1", method = RequestMethod.GET)
	public ResponseEntity<?> setLocationCodeFor1() throws IOException, WriterException {
	
		Location location=locationService.get((long) 792);
		//System.out.println("size of list==="+list.size());
		System.out.println("location name==="+location.getName());
			if(location.getLocationCode()== null)
			{System.out.println("inside iffff");
				String locationCode=CommonMethods.randomString("");
				System.out.println("locationcode ==="+locationCode);
				location.setLocationCode(locationCode);
			    QrCodeGenerator.generateQrcode(locationCode, Constants.LOCATION_QR_IMAGE_PATH);
			}
           Workspace w= location.getWorkspace();
           System.out.println("workspace====="+w.getWorkspaceId()+"project id===="+w.getId());
           locationService.persist(location, w);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="/setlocationcode", method = RequestMethod.GET)
	  public ResponseEntity<?> setLocationCode() throws IOException,WriterException {
	  
	  List<Location> list=locationService.getAll();
	  System.out.println("size of list==="+list.size()); 
	  for(Location location :list)
	  { 
			
			  if(location.getLocationCode()== null)
			  {
				  System.out.println("inside iffff");
				  String locationCode=CommonMethods.randomString(""); 
				  System.out.println("locationcode ==="+locationCode);
				  location.setLocationCode(locationCode); 
				  QrCodeGenerator.generateQrcode(locationCode, Constants.LOCATION_QR_IMAGE_PATH); 
			  }
				  
		  Workspace w= location.getWorkspace();
		  System.out.println("workspace====="+w.getWorkspaceId()+"project id===="+w. getId());
		  locationService.persist(location, w);
	  
	  
	  
	  } return new ResponseEntity<>(HttpStatus.OK); }
	 
	
}