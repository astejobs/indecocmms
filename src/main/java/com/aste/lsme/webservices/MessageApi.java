package com.aste.lsme.webservices;

import java.util.List;

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
import com.aste.lsme.domain.Message;
import com.aste.lsme.service.MessageServiceInterface;
import com.aste.lsme.webservicesDtos.TaskDTO;
import com.aste.lsme.webservicesDtos.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="MessageApi", description="Operations pertaining to Message")
@RequestMapping("/api/msg")
public class MessageApi {
	
	@Autowired
	MessageServiceInterface messageService;
	
	  @RequestMapping(value="/", method = RequestMethod.GET)
	  @ApiOperation(value = "Find Messages on username", notes = " Retrieves the messages")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class,responseContainer = "List"),
			                  @ApiResponse(code = 500, message = "Server error"),
		                      @ApiResponse(code = 401, message = "Unauthorized Access"),
		                      @ApiResponse(code = 404, message = "Not found") })	       
	  public ResponseEntity<?> getMessagesOnUsername(@RequestAttribute("username")String username)
	  {  
		      List<Message> list=messageService.getAllMessages(username);
		        if(!list.isEmpty()) 
		        {
				   return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
		        }
		        else 
		        {
					 return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		        }
					  
	  }
	  
	  
	  @RequestMapping(value="/type", method = RequestMethod.GET)
	  @ApiOperation(value = "Find Messages on type", notes = " Retrieves the messages on the basis of type")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Message.class,responseContainer = "List"),
			                  @ApiResponse(code = 500, message = "Server error"),
		                      @ApiResponse(code = 401, message = "Unauthorized Access"),
		                      @ApiResponse(code = 404, message = "Not found") })	       
	  public ResponseEntity<?> getMessagesOnUsername(@RequestParam("type") String type,@RequestAttribute("username")String username)
	  {  
		      List<Message> list=messageService.getAllMessagesOnType(username,type);
		        if(!list.isEmpty()) 
		        {
				   return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
		        }
		        else 
		        {
					 return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		        }
					  
	  }
	

}
