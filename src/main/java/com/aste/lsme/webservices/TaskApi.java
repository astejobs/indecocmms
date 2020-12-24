package com.aste.lsme.webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Content;
import com.aste.lsme.domain.Message;
import com.aste.lsme.domain.NotificationPayload;
import com.aste.lsme.domain.QrCodeGenerator;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.MessageServiceInterface;
import com.aste.lsme.service.PMTaskService;
import com.aste.lsme.service.UserDetailsServiceInterface;
import com.aste.lsme.utils.Base64;
import com.aste.lsme.utils.CommonMethods;
import com.aste.lsme.webservicesDtos.ChecklistDTO;
import com.aste.lsme.webservicesDtos.TaskDTO;
import com.aste.lsme.webservicesDtos.UserDTO;
import com.google.zxing.WriterException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@Api(value="TaskApi", description="Operations pertaining to Task")
@RequestMapping("/api/task")
public class TaskApi {

	@Autowired
	PMTaskService taskService;
	
	@Autowired
	EquipmentService equipService;

	@Autowired
	UserDetailsServiceInterface userService;
	
	@Autowired
	MessageServiceInterface msgService;
	
	
	
	  @RequestMapping(value="/equipment/{equipcode}", method = RequestMethod.GET)
	  @ApiOperation(value = "Find Task by Equipment Code and Status", notes = " Retrieves the task")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TaskDTO.class,responseContainer = "List"),
			                  @ApiResponse(code = 500, message = "Server error"),
		                      @ApiResponse(code = 401, message = "Unauthorized Access"),
		                      @ApiResponse(code = 404, message = "Not found") })	       
	  public ResponseEntity<?> getTasksByEquipmentCode(@PathVariable String equipcode ,@RequestHeader("role")String role)
	  {
		      List<TaskDTO> list=null;
		         
		        if(role.equals(Constants.ROLE_TECHNICIAN)) {
				   list=equipService.getTasksOnEquipmentCodeAndStatus(equipcode,Constants.OPEN);
						  //equipService.getAllTasks(equipcode, status);
		        }
		        else if(role.equals(Constants.ROLE_MANAGING_AGENT)) {
		        	
		        	 list=equipService.getTasksOnEquipmentCodeAndStatus(equipcode, Constants.COMPLETED);
		        	 for (TaskDTO taskDto:list) {
		        		 UserDTO user=userService.getUserOnUsername(taskDto.getCompletedBy());
		        		 taskDto.setCompletedBy(user.getFirstName()+" "+user.getLastName());
		        	 }
		        	 
		        }
				  
				  return new ResponseEntity<List<TaskDTO>>(list, HttpStatus.OK);
					  
	  }
	
	 
	  @RequestMapping(value = "/{taskid}", method = RequestMethod.GET)
	  @ApiOperation(value = "Find Task on taskid",notes = " Retrieves the task ")
	  @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TaskDTO.class),
	                          @ApiResponse(code = 500, message = "Server error"),
                              @ApiResponse(code = 401, message = "Unauthorized Access"),
                              @ApiResponse(code = 404, message = "Not found")})	 
     public ResponseEntity<?> getTaskById(@PathVariable("taskid") Long taskid  ) 
	  {
         System.out.println("get task by iddddddddddddddd" );
         System.out.println("taskkkkidd==="+taskid);
         TaskDTO taskListDto=taskService.findTask(taskid);
         
         return new ResponseEntity< TaskDTO>(taskListDto, HttpStatus.OK);
    
	  }
		/*
		 * @GetMapping("/testcase") public ResponseEntity<?> findTest(){
		 * 
		 * return ResponseEntity.ok(taskCrudService.findOnId((long) 30));
		 * 
		 * }
		 */
	 
	 @RequestMapping(value = "/updateTask" ,method = RequestMethod.PUT) 
	 @ApiOperation(value = "Update Task", notes = " Returns the updated task")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TaskDTO.class),
	 						 @ApiResponse(code = 500, message = "Server error"),
	 						 @ApiResponse(code = 401, message = "Unauthorized Access"),
	 						 @ApiResponse(code = 403, message = "Forbidden"),
	 						 @ApiResponse(code = 404, message = "Not found")})	 
	 public ResponseEntity<TaskDTO>  updateTask(@RequestBody TaskDTO taskDto,HttpServletRequest req,@RequestHeader("role")String role,@RequestAttribute("username")String username) throws ParseException
	 {          
			
		    String w = req.getHeader(Constants.POJECTECTID_HEADER);
			Task task=taskService.find(taskDto.getTaskId());
			
			taskDto.setBuildingName(task.getEquipment().getBuilding().getName());
			taskDto.setLocationName(task.getEquipment().getLocation().getName());
			if(role.equals(Constants.ROLE_TECHNICIAN))
			{
				if(taskDto.getStatus().equals(Constants.COMPLETED))
				{
					
						NotificationPayload nf=CommonMethods.generateTaskNotification(Constants.Type.TASK_COMPLETED, taskDto, w);		

					List<String> devices=new ArrayList<String>();
					List<UserDTO> user=userService.getDeviceTokenOnRole( Constants.ROLE_MANAGING_AGENT, w);
					
					for (UserDTO userDTO : user) {
						devices.add(userDTO.getDeviceToken());
						saveMessage(userDTO.getId(),nf.getTitle(),nf.getBody(),Constants.Type.TASK_COMPLETED);
					}
					
						nf.setDevices(devices);
			       CommonMethods.sendNotification(nf); 
			  
			        Date completedDate =taskDto.getCompletedDate(); 
				    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				    completedDate = sdf.parse(sdf.format(completedDate));
				
				    task.setCompletedDate(completedDate);
				    task.setCompletedTime(taskDto.getCompletedTime());
				    task.setCompletedBy(username);

			     }
				
				task.setStatus(taskDto.getStatus());
				task.setRemarks(taskDto.getRemarks());
				taskService.update(task);
				

		 }
			
		else if(role.equals(Constants.ROLE_MANAGING_AGENT))
		 {
			LocalDateTime ackTime=LocalDateTime.now();
			System.out.println("acktime====="+ackTime);
				
			if(taskDto.getStatus().equals(Constants.OPEN))
			  {
				NotificationPayload nf=CommonMethods.generateTaskNotification(Constants.Type.TASK_REOPENED, taskDto, w);		

				     List<String> devices = new ArrayList<String>();
					 System.out.println("USERNAME====="+task.getCompletedBy());
					 
					 String user=task.getCompletedBy();
					 UserDTO usr=userService.getDeviceTokenOnUsername(user);
					 saveMessage(usr.getId(), nf.getTitle(), nf.getBody(),Constants.Type.TASK_REOPENED);
					 devices.add(usr.getDeviceToken()); 
					 nf.setDevices(devices);
					 CommonMethods.sendNotification(nf); 

					task.setAcknowledgementTime(ackTime);
					
			  }
			else if(taskDto.getStatus().equals(Constants.CLOSED)) 
			 {
			
				  task.setAcknowledgementTime(ackTime);
			 
			 }
    	
		task.setStatus(taskDto.getStatus());
		taskService.update(task);
	
		}
	 
		 return new ResponseEntity<TaskDTO>(taskDto,HttpStatus.OK);

 }
	 
	 @RequestMapping(value="/{taskid}/checklist", method=RequestMethod.GET)
	 @ApiOperation(value = "Find checklists on task id ",notes = "Retrieves  checklists ")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TaskChecklist.class,responseContainer = "List"),
			 				 @ApiResponse(code = 500, message = "Server error"),
			 				 @ApiResponse(code = 401, message = "Unauthorized Access"),
			 				 @ApiResponse(code = 403, message = "Forbidden"),
			 				 @ApiResponse(code = 404, message = "Not found")})	 
	 public ResponseEntity<?> getChecklist(@PathVariable("taskid") Long taskid)
	 {	
		 	System.out.println("idddddddddd==="+taskid);
		 	List<TaskChecklist> list=taskService.gettaskchecklist(taskid);
			return new ResponseEntity<List<TaskChecklist>>(list,HttpStatus.OK);
	
	 }
	 
	 
	 @RequestMapping(value="/updateChecklists",method=RequestMethod.POST)
	 @ApiOperation(value = "Update checklists", notes = "Returns updated  checklists")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ChecklistDTO.class,responseContainer = "List"),
			                 @ApiResponse(code = 500, message = "Server error"),
                             @ApiResponse(code = 401, message = "Unauthorized Access"),
                             @ApiResponse(code = 403, message = "Forbidden"),
                             @ApiResponse(code = 404, message = "Not found")})	 
	 public ResponseEntity<?> updateChecklist(@RequestBody List<ChecklistDTO> checkDtos )
	 {
	      	
		for (ChecklistDTO checklistDTO : checkDtos)
		 {
			TaskChecklist checklist=taskService.findById(checklistDTO.getId());
			checklist.setRemarks(checklistDTO.getRemarks());
			checklist.setStatus(checklistDTO.getStatus());
			checklist.setBlanks(checklistDTO.getBlanks());
			taskService.update(checklist);
		 }
		
		 return new ResponseEntity<List<ChecklistDTO>>(checkDtos,HttpStatus.OK);
		 
	 }
	 

	 @RequestMapping(value="/beforeimage",method=RequestMethod.POST)
	 @ApiOperation(value = "Saving beforeimage to Task")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			                 @ApiResponse(code = 500, message = "Server error"),
                             @ApiResponse(code = 401, message = "Unauthorized Access"),
                             @ApiResponse(code = 403, message = "Forbidden"),
                             @ApiResponse(code = 404, message = "Not found")})
	 public ResponseEntity<?> beforeimage(@RequestBody Content taskImage,HttpServletResponse rep)
	 {	
		     String imageName = CommonMethods.randomString(Constants.TASK_BEF_IMG);
			 if(saveImage(taskImage.getData(), imageName))
			 {   
				 taskImage.setData(imageName);
				 System.out.println("imagenameeeeee==="+taskImage.getData());
				 taskService.updateImage(taskImage, true);
		
				 return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			 }
			 else
			 {
				 return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
			 }
				
	}
	 
	
	   @RequestMapping(value="/afterimage",method=RequestMethod.POST) 
	   @ApiOperation(value = "Saving afterimage to Task")
	   @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			                   @ApiResponse(code = 500, message = "Server error"),
			                   @ApiResponse(code = 401, message = "Unauthorized Access"),
			                   @ApiResponse(code = 403, message = "Forbidden"),
			                   @ApiResponse(code = 404, message = "Not found")})
	   public ResponseEntity<?> afterimage(@RequestBody Content taskImage,HttpServletResponse rep)
	   {    
		  String imageName = CommonMethods.randomString(Constants.TASK_AFT_IMG);
	 
		  if(saveImage(taskImage.getData(), imageName)) 
		  	{ 
			  	taskImage.setData(imageName);
			  	System.out.println("imagenameeeeee==="+taskImage.getData());
			  	taskService.updateImage(taskImage, false);
	  
			  	return new ResponseEntity<HttpStatus>(HttpStatus.OK); 
			 }
		  else
		  	{
			  	return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST); 
		  	}
	  
	  }
	 
	 
	   
	     @RequestMapping(value="/search",method=RequestMethod.GET)
		 @ApiOperation(value = "Search Task",notes = "Retrieves all the Tasks Of Particular User")
		 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TaskDTO.class,responseContainer = "List"),
				                 @ApiResponse(code = 500, message = "Server error"),
				                 @ApiResponse(code = 401, message = "Unauthorized Access"),
				                 @ApiResponse(code = 403, message = "Forbidden"),
				                 @ApiResponse(code = 404, message = "Not found")})
		public ResponseEntity<List<TaskDTO>> searchTasks(@RequestParam("query") String search,@RequestAttribute("username") String username,@RequestHeader("role") String role,HttpServletRequest req){
		
	    	 String w = req.getHeader(Constants.POJECTECTID_HEADER);
	    	 List<TaskDTO> taskList=taskService.getTasks(username, role, w, search);
	    	 
	    	 return new ResponseEntity<List<TaskDTO>>(taskList,HttpStatus.OK);
		
	
	     }
	           
	     
	     @RequestMapping(value="/getimage/{imageName:.+}",method=RequestMethod.GET)
		 @ApiOperation(value = "Find Image ",notes="Retrieves Image on Image Name")
		 @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
				                 @ApiResponse(code = 500, message = "Server error"),
				                 @ApiResponse(code = 401, message = "Unauthorized Access"),
				                 @ApiResponse(code = 403, message = "Forbidden"),
				                 @ApiResponse(code = 404, message = "Not found")})
		public static void getImage(@PathVariable("imageName") String imageName,Model model,
				HttpServletRequest req, HttpServletResponse rep) throws IOException, WriterException
		{
			
			try {
				InputStream is = new FileInputStream(Constants.TASK_IMAGE_PATH + imageName);
		
				byte[] bytes = IOUtils.toByteArray(is);
				rep.setContentType(QrCodeGenerator.getContentType(imageName));
				OutputStream os = rep.getOutputStream();
				os.write(bytes);
				os.close();
				is.close();
			} catch (Exception e) {
			}
			
		}
	     
	     
		
 /* ///METHODS///// */	
		 public static boolean saveImage(String bImage, String imageName) {
			String beforeImage = getImageData(bImage);
			System.out.println("image=="+beforeImage);
			byte[] bArray = Base64.decode(beforeImage,Base64.DEFAULT);
			File folder = new File(Constants.TASK_IMAGE_PATH);
			if (!folder.exists())
				folder.mkdirs();
			try {
				FileOutputStream fileOs = new FileOutputStream(Constants.TASK_IMAGE_PATH+imageName);
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
	
		public static String getImageData(String beforeImage) {

			System.err.println(beforeImage.split(",").length);
			return beforeImage.split(",").length>1?
					beforeImage.split(",")[1]:
						beforeImage;

		}
		
	 
	 
	  @ExceptionHandler
	  public void getException(Exception e) {

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
		   System.out.println("after save");
		   
		
	}
}