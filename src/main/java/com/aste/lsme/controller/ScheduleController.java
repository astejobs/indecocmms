package com.aste.lsme.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.ChecklistHeader;
import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentDtoSearch;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.SearchPMSchedule;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AssetSubtypeService;
import com.aste.lsme.service.AssetTypeService;
import com.aste.lsme.service.BuildingService;
import com.aste.lsme.service.ChecklistService;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.service.LocationService;
import com.aste.lsme.service.PMService;
import com.aste.lsme.service.PMTaskService;
import com.aste.lsme.service.TeamService;
import com.aste.lsme.utils.Encryptor;


@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController{

	
	int from = 0;
	int total = 0;
	public static final int ROWS = Constants.ROWS;
	Long records = 0L;
	
	@Autowired
	BuildingService buildingService;
	
	@Autowired
	LocationService locService;
	
	@Autowired
	AssetTypeService assetTypeService;
	
	@Autowired
	AssetSubtypeService assetSubService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	ChecklistService checklistService;
	
	@Autowired
	PMService pmService;
	
	@Autowired
	EquipmentService equipmentService;
	
	@Autowired
	PMTaskService taskService;
	

	@Autowired
	VelocityEngine velocityEngine;
	
	
	@Autowired
	JavaMailSender mailsender;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createPM(Model model, HttpSession session) 
	{
		
		Workspace w = (Workspace) session.getAttribute("workspace");
		
		
		if(!model.asMap().containsKey("schedule"))
		{
			Schedule Schedule = new Schedule();
			String scheduleNumber = getScheduleId(w.getWorkspaceId());
			Schedule.setScheduleNumber(scheduleNumber);
			model.addAttribute("schedule", Schedule);
		} 
					
			if(model.asMap().containsKey("result"))
			{
				model.addAttribute("org.springframework.validation.BindingResult.schedule",model.asMap().get("result"));
			}
		
		model.addAttribute("frequencies", Constants.frequency);
		model.addAttribute("teamList",teamService.getTeamList(w));

		List<ChecklistHeader> list=checklistService.getAllWorkspaceBased(w);
	
		model.addAttribute("listCheck",list);
		return "scheduleCreate";

	}
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveSchedule(@Valid @ModelAttribute ("schedule") Schedule schedule,BindingResult result,
								RedirectAttributes redirectAttributes )
	{
		if(result.hasErrors())
		{
			redirectAttributes.addFlashAttribute("result",result);
			redirectAttributes.addFlashAttribute("schedule",schedule);
			redirectAttributes.addFlashAttribute("fail","Enter All The fields correctly");
			return "redirect:/schedule/create";
		}
		
			for(int i=0;i<schedule.getEquipment().size();i++)
			{
				if(schedule.getEquipment().get(i).getChecklists()==null)
				{
					redirectAttributes.addFlashAttribute("fail","Schedule creation failed, One or More Equipments dont have Checklist assosiated!");
					return "redirect:/schedule/create";
				
				}
			}
		
			for(int i=0;i<schedule.getEquipment().size();i++)
			{
				schedule.getEquipment().get(i).setSchedule(schedule);
					
				//schedule.getEquipment().get(i).getChecklists().setPropertyTitles(checklistService.findPropertyTitles(schedule.getEquipment().get(i).getChecklists()));
			//	schedule.getEquipment().get(i).getChecklists().get(i).setPropertyTitles(checklistService.findPropertyTitles(schedule.getEquipment().get(i).getChecklists().get(i)));
				schedule.getEquipment().get(i).getChecklists().get(0).setPropertyTitles(checklistService.findPropertyTitles(schedule.getEquipment().get(i).getChecklists().get(0)));

			}
			
			 schedule.setReviewstatus("pending");
			
			 pmService.persist(schedule);
				

			   	redirectAttributes.addFlashAttribute("success","Preventive Maintenance schedule created successfully");
				return "redirect:/schedule/create";
		
	}
	 
	
	
	
	 @RequestMapping(value="/equipmentlist/{id}",method=RequestMethod.GET)
	 @ResponseBody
	 public List<EquipmentDtoSearch>getListOfEquipment(@PathVariable("id")Long id )
	 {
	  ChecklistHeader checklist=checklistService.find(id);
	  List<Equipment>list =checklist.getEquipment();
	  List<EquipmentDtoSearch> listEq=new ArrayList<EquipmentDtoSearch>();
	  for(Equipment e:list)
	  {  if(e.getSchedule()!=null)
	  {}
	  else{
	   EquipmentDtoSearch eq=new EquipmentDtoSearch();
	
	   eq.setId(e.getId());
	   eq.setName(Encryptor.encrypt(e.getName()));
	 
	   listEq.add(eq);
	  }
	  
	  }
	  return listEq;
	 }
	
	
	 	@RequestMapping(value = "/check", method = RequestMethod.GET)
		@ResponseBody
		public Map<Long, String> ajaxsearchFR(@RequestParam(value = "id", required = true) Long id,HttpSession session) 
	 	{
			Map<Long, String> checklist = new HashMap<Long, String>();
			for (ChecklistHeader ck : checklistService.listAll(id)) 
			{
				checklist.put(ck.getId(), ck.getChecklistName());
			}
			return checklist;
		}
	 
	 	@RequestMapping(value="/review",method=RequestMethod.GET)
	 	public String listForReviews(Model model)
	 	{
	 		model.addAttribute("listforreview",pmService.getscheduledtoreview());
	 		return "listForReview";
	 	}
	 	
		@RequestMapping(value="/admin",method=RequestMethod.GET)
	 	public String listForAdmin(Model model)
	 	{
	 		model.addAttribute("listforadmin",pmService.getscheduledforadmin());
	 		return "listForAdmin";
	 	}
	 	

		  @RequestMapping(value = "updateForReview/{id}", method = RequestMethod.GET)
		  public String editforreview(@PathVariable("id") Long id, Model model, HttpSession session) 	  
		  {
			  Workspace w=(Workspace) session.getAttribute("workspace");
			  Schedule schedule=pmService.find(id);
			  model.addAttribute("checklistName", pmService.getCheckListNameOfSchedule(id));
			  model.addAttribute("teamList", teamService.getTeamList(w));
			  model.addAttribute("schedule", schedule);
			  DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			  model.addAttribute("newdate", df.format(new Date()));
			  model.addAttribute("frequencies", Constants.frequency);			  
			  return "scheduleEditForReview";
	  	  }
	 	
		  @RequestMapping(value="updateForReviewer",params="accept",method=RequestMethod.POST)
		  public String updateForReviewer(@Valid @ModelAttribute("schedule") Schedule schedule,BindingResult result,
				  							Model model,HttpSession session,RedirectAttributes redirectAttributes)
		  {
			 if(result.hasErrors())
			 {
				 redirectAttributes.addFlashAttribute("fail","Review failed");
				 return "redirect:/schedule/review";
			 }
			 schedule.setReviewstatus("accepted");
			 schedule.setStatus("pending");
			 pmService.updatescheduleforreviewer(schedule);
			 redirectAttributes.addFlashAttribute("success","Schedule Reviewed Successfully");
			 return "redirect:/schedule/updateForReview/"+ schedule.getId();
		  }
		  
		
	@RequestMapping(value="updateForReviewer",params="reject",method=RequestMethod.POST)	  
	public String rejectForReviewer(@Valid @ModelAttribute("schedule") Schedule schedule,BindingResult result,
				Model model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		 if(result.hasErrors())
		 {
			 redirectAttributes.addFlashAttribute("fail","Review Updation failed");
			 return "redirect:/schedule/review";
		 }
		
		 schedule.setReviewstatus("rejected");
		 schedule.setStatus("rejected");
		 pmService.updatescheduleforreviewer(schedule);;
		 
		 redirectAttributes.addFlashAttribute("success","Schedule Reviewed Successfully");
		 return "redirect:/schedule/updateForReview/"+ schedule.getId();
		 
	
	}
		  
		 
	@RequestMapping(value="updateForAdmin/{id}",method=RequestMethod.GET)
	public String updateForAdmin(@PathVariable("id") Long id,HttpSession session,Model model)
	{
		Workspace w=(Workspace) session.getAttribute("workspace");
		Schedule schedule = pmService.find(id);
		
		  model.addAttribute("checklistName", pmService.getCheckListNameOfSchedule(id));
		  model.addAttribute("teamList",teamService.getTeamList(w));
	      model.addAttribute("schedule", schedule);
	      DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	      model.addAttribute("newdate", df.format(new Date()));
	      model.addAttribute("frequencies", Constants.frequency);
		  
		return "scheduleEditForAdmin";
	}
		  
		
	
	  @RequestMapping(value="updateForAdmin",params="accept",method=RequestMethod.POST)
	  public String updateForAdmin(@Valid @ModelAttribute("schedule") Schedule schedule,BindingResult result,
			  							Model model,HttpSession session,RedirectAttributes redirectAttributes)
	  {
		  
		 if(result.hasErrors())
		 {
			 redirectAttributes.addFlashAttribute("fail","Review failed");
			   return "redirect:/schedule/updateForAdmin/" + schedule.getId();
		 }
		 schedule.setStatus("approved");
	     System.out.println();
	     pmService.updatescheduleforadmin(schedule, session);
	   
		 redirectAttributes.addFlashAttribute("success","Schedule Reviewed Successfully");
		 return"redirect:/schedule/admin";
	  }
	
	
	  
		@RequestMapping(value = "/search", method = RequestMethod.GET)
		public String searchSchedule(Model model, HttpSession session) 
		{
			
			SearchPMSchedule searchPMSchedule = new SearchPMSchedule();
			Workspace workspace = (Workspace) session.getAttribute("workspace");
			searchPMSchedule.setWorkspace(workspace);
			session.setAttribute("searchPMSchedule", searchPMSchedule);
			pagination(1, session, model, workspace);
			model.addAttribute("scheduleSearch", searchPMSchedule);
			return "PMScheduleView";
			
		}
		
		
		public void pagination(int page,HttpSession session,Model model,Workspace w){
			
			page = (page > 0) ? page : 1;
			from = ROWS*(page-1);
			records = pmService.getSearchCount((SearchPMSchedule) session.getAttribute("searchPMSchedule"));
			total = (int) Math.ceil((double) records / (double) ROWS);
			model.addAttribute("total", total);
			model.addAttribute("page", page);
			model.addAttribute("pmschedules", pmService.getSearchResult((SearchPMSchedule) session.getAttribute("searchPMSchedule"), from, ROWS));

		}
		
		@RequestMapping(value = "/pageno={page}" , method = RequestMethod.GET)
		public String paginate(@PathVariable("page") int page,Model model,HttpSession session){
			
			Workspace w = (Workspace) session.getAttribute("workspace");
			pagination(page,session, model,w);
			model.addAttribute("scheduleSearch", new SearchPMSchedule());
			return "PMScheduleView";
		}
		
		@RequestMapping(value = "/search", method = RequestMethod.POST)
		public String searchSched(@ModelAttribute("scheduleSearch") SearchPMSchedule searchPMSchedule,
				Model model, HttpSession session, HttpServletRequest req) 
		{

			
			Workspace workspace = (Workspace) session.getAttribute("workspace");
			searchPMSchedule.setWorkspace(workspace);
		
			
			int page = 1;
			page = (page > 0) ? page : 1;
			from = (ROWS * (page - 1));
			records = pmService.getSearchCount(searchPMSchedule);

			total = (int) Math.ceil((double) records / (double) ROWS);
			System.out.println(" no " + records + "  pages" + total);
			model.addAttribute("total", total);
			model.addAttribute("page", page);

			model.addAttribute("pmschedules", pmService.getSearchResult(searchPMSchedule, from, ROWS));
			
			model.addAttribute("scheduleSearch", searchPMSchedule);
			return "PMScheduleView";

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
		
		
		@RequestMapping(value = "/equipments", method = RequestMethod.POST)
		public String searchPredictiveEquip(@ModelAttribute("equipmentSearch") EquipmentSearchCriteria equipmentSearch,
				Model model, HttpSession session, HttpServletRequest req) 
		{
			Workspace w=(Workspace) session.getAttribute("workspace");
			equipmentSearch.setWorkspace(w);
				
			int page=1;
			pagination(page, model, equipmentSearch,false);
			
			model.addAttribute("assetTypeList", assetTypeService.getAll());
			model.addAttribute("buildingList",buildingService.getWorkspaceBuildings(w));
			model.addAttribute("equipmentSearch", equipmentSearch);
			
			if(equipmentSearch.getBuilding()!=null)
				model.addAttribute("locationList",locService.getLocationList(equipmentSearch.getBuilding().getId()));
			
				if(equipmentSearch.getAssetType()!= null)
					{
					model.addAttribute("assetSubtypeList",assetSubService.getEquipmentSubTypeList(equipmentSearch.getEquipmentType()));
					}		

			
			return "getPMSelectedEquipments";
		}
		
	  
		
		public void pagination(int page,Model model,Object object,boolean b)
		{
				
				int from = 0;
				int total = 0;
				final int ROWS = Constants.ROWS;
				Long records = 0L;
					
					page = (page > 0) ? page : 1;
					from = ROWS*(page-1);
					records = (long)equipmentService.countPredictiveScheduledEquipments((EquipmentSearchCriteria)object);
	                total = (int) Math.ceil((double) records / (double) ROWS);
					model.addAttribute("total", total);
					model.addAttribute("page", page);
					model.addAttribute("equipmentList",equipmentService.getPredictiveScheduledEquipments((EquipmentSearchCriteria)object, from, ROWS));
					
					model.addAttribute("equipmentSearch", (EquipmentSearchCriteria)object);
					
		}
				

		
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model,HttpSession session) {
	
		Workspace w=(Workspace)session.getAttribute("workspace");
		Schedule schedule = pmService.find(id);
		model.addAttribute("checklistName",pmService.getCheckListNameOfSchedule(id));
		 model.addAttribute("teamList", teamService.getTeamList(w));
		model.addAttribute("schedule", schedule);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		model.addAttribute("newdate", df.format(new Date()));
		model.addAttribute("frequencies", Constants.frequency);
		return "scheduleEdit";

	}
	
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("schedule") Schedule schedule,final BindingResult result, 
					Model model, HttpSession session,HttpServletRequest request, RedirectAttributes redirectAttributes)
	
	{
		
		schedule.setTasks(pmService.listTasks(schedule));
		
		if (result.hasErrors())
		{
			if (!result.hasFieldErrors("scheduleDate")) 
			{
				redirectAttributes.addFlashAttribute("fail","Schedule updation failed!");
				 
				return "redirect:/schedule/" + schedule.getId();
			}
		}
		
		boolean success = true;
		List<Task> tasklist = schedule.getTasks();
		Task task = tasklist.get(0);
		//task.setCheckLists(taskService.listAllChecklists(task));		

		List<ChecklistPropertyTitle> checklistpropertyTitles =checklistService
				.findPropertyTitles(schedule.getChecklistHeader());
		
		List<Task> newTasklist = new ArrayList<Task>();
	
		if (tasklist.get(0).getScheduleDate().before(new LocalDate().toDateMidnight().toDate()))
			
			if (tasklist.get(0).getScheduleDate().before(schedule.getScheduleDate())) 
			{
				redirectAttributes.addFlashAttribute("fail","Tasks performed cannot be deleted!");
				return "redirect:/schedule/" + schedule.getId();

			}

		if (schedule.getStartDate().after(schedule.getScheduleDate()) || schedule.getEndDate().before(new Date()))
		{
			redirectAttributes.addFlashAttribute("fail", "Start date, End date and schedule date need to be entered properly!");
			return "redirect:/schedule/" + schedule.getId();

		}
			for (Task t : tasklist) 
				{		    
					pmService.remove(t);  
				}
	
			
		schedule.setTasks(newTasklist);
		
		success = pmService.update(schedule,newTasklist, checklistpropertyTitles,(UserDetail) session.getAttribute("user"), session );
		
		if (success) 
			{
			redirectAttributes.addFlashAttribute("success","Preventive Maintenance schedule updated successfully");

			} 
				else {
					redirectAttributes
					.addFlashAttribute("fail",	"Some Thing went wrong, Please enter all feilds correctly!");

				   }

		return "redirect:/schedule/" + schedule.getId();

	}

	
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model model,HttpSession session, RedirectAttributes redirectAttributes) 
	{
		if (pmService.delete(id))
			redirectAttributes.addFlashAttribute("success",	"Schedule deleted successfully");
		else
			redirectAttributes.addFlashAttribute("fail","Schedule can not be deleted");
		
		return "redirect:/schedule/search"; 

	}
	
	public String getScheduleId(String workspace){
		String id = "PMSCH-" + workspace.split("-")[1]+"-"+String.format("%05d",pmService.getMaxScheduleId()+1);
		return id;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/emailTask/{pmsNo}",method=RequestMethod.GET)
	 public String emailTasks(@PathVariable("pmsNo") String pmsNo,HttpSession session,
			 RedirectAttributes redirectAttributes) throws ParseException, MessagingException 
	{
		Workspace w = (Workspace) session.getAttribute("workspace");
		try {
			  MimeMessage message = mailsender.createMimeMessage();
		   	  MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

			 System.err.println(pmService.getScheduleTeamEmails(pmsNo)+"------0000000000----------------");

		   	  InternetAddress[] address =InternetAddress.parse(StringUtils.join(pmService.getScheduleTeamEmails(pmsNo).toArray(),","));
		   	  helper.setTo(address);
		   	  helper.setSubject("Tasks for Current Month");
		   	  helper.setFrom(w.getWorkspaceId().split("-")[1] + "<info@stie.com.sg>");
		   	  Map model = new HashMap();
		   	  model.put("emailTaskDTO",taskService.getEmailTasks(pmsNo, w));
		   	  String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/emailTemplate/TaskTechnicianTemplate.vm","UTF-8", model);
		   	  
		   	  helper.setText(text,true);
		   	  mailsender.send(message);
		   	  redirectAttributes.addFlashAttribute("success","Emails sent successfully");
			}
			catch (Exception e) 
			{
				
				redirectAttributes.addFlashAttribute("fail","Something went wrong, Please try again!");
				e.printStackTrace();
				System.err.println("-------------------------------------------------------");
		  
			}
				finally {
						return "redirect:/schedule/search";
						}
		  
	}
		

}