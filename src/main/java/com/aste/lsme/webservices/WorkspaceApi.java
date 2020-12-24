package com.aste.lsme.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.service.WorkspaceService;
import com.aste.lsme.webservicesDtos.TaskDTO;
import com.aste.lsme.webservicesDtos.WorkspaceDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="WorkspaceApi", description="Operations pertaining to Workspace")
@RequestMapping(value="/api/workspaces")
public class WorkspaceApi {
	
	@Autowired
	WorkspaceService workspaceService;
	
	@Autowired
	UserDetailsServiceInterface userService;
	
	@RequestMapping(method=RequestMethod.GET)
	 @ApiOperation(value = "Find all the workspaces",notes = " Retrieves all workspaces")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = WorkspaceDto.class,responseContainer = "List"),
			                @ApiResponse(code = 500, message = "Server error"),
			                @ApiResponse(code = 401, message = "Unauthorized Access"),
			                @ApiResponse(code = 403, message = "Forbidden"),
			                @ApiResponse(code = 404, message = "Not found")}) 
	public ResponseEntity<?> workspaces(@RequestAttribute("username") String username){	
		UserDetail user=userService.loadUserByUsername(username);
		
		List<WorkspaceDto> wList=workspaceService.getWorkspaces(user.getUsergroup().getId());
		 if(!user.getUsergroup().getWorkspaces().isEmpty())
			 return new ResponseEntity<List<WorkspaceDto>>(wList, HttpStatus.OK);	
			 else		
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);	
			
   }
	

}

