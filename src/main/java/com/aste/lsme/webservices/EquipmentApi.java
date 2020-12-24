package com.aste.lsme.webservices;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.TaskDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value="EquipmentApi", description="Operations pertaining to Equipment")
@RequestMapping("/api/equip")
public class EquipmentApi {

	@Autowired
	EquipmentService equipService;
	
	
	  @RequestMapping(value="/{equipcode}", method=RequestMethod.GET)
	  @ApiOperation(value = "Find Equipment by Equipment Code ", notes = " Retrieves  equipment by equipment code ")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EquipmentDTO.class),
			  				  @ApiResponse(code = 500, message = "Server error"),
			  				  @ApiResponse(code = 401, message = "Unauthorized Access"),
			  				  @ApiResponse(code = 403, message = "Forbidden"),
			  				  @ApiResponse(code = 404, message = "Service not found") })
	 public ResponseEntity<?> getEquipments(@PathVariable String equipcode){
		
		System.out.println("in controllerrrr");
		EquipmentDTO equip=equipService.getEquipmentByEquipCode(equipcode);
		System.out.println("equipment====="+equip);
		
		return new ResponseEntity<EquipmentDTO>(equip,HttpStatus.OK);
		
	}
	
	  @RequestMapping(value="/search",method=RequestMethod.GET)
	  @ApiOperation(value = "Find Equipment  ")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = EquipmentDTO.class),
				  @ApiResponse(code = 500, message = "Server error"),
				  @ApiResponse(code = 401, message = "Unauthorized Access"),
				  @ApiResponse(code = 403, message = "Forbidden"),
				  @ApiResponse(code = 404, message = "Service not found") })
		 public ResponseEntity<?> getEquipmentsOnSearch(@RequestParam("query") String search,HttpServletRequest req){
		  
		     String w = req.getHeader(Constants.POJECTECTID_HEADER);
	    	 List<EquipmentDTO> equipList=equipService.getEquipments(w, search);
	    	 return new ResponseEntity<List<EquipmentDTO>>(equipList,HttpStatus.OK);
			
		  
	  }

	
	
}
