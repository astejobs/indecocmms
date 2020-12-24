package com.aste.lsme.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PMDaoInterface;
import com.aste.lsme.dao.PMTaskDao;
import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.ExceptionDate;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.SearchPMSchedule;
import com.aste.lsme.domain.StartScheduleDate;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.ExceptionDateService;
import com.aste.lsme.service.PMService;

@Transactional
@Service
@Component
public class PMServiceImpl implements PMService {

	@Autowired
	PMDaoInterface pmdao;
	
	@Autowired
	PMTaskDao pmtDao;
	
	@Autowired
	ExceptionDateService exceptionDateService;
	

	
	
	@Override
	public void persist(Schedule schedule)
	{
		pmdao.persist(schedule);
		
	}

	@Override
	public List<Schedule> getscheduledtoreview() {
		return pmdao.getscheduledtoreview();
	}

	@Override
	public Schedule find(Long id) {
		
		return pmdao.find(id);
	}

	@Override
	public String getCheckListNameOfSchedule(Long id) {
		
		return pmdao.getCheckListNameOfSchedule(id);
	}

	@Override
	public void updatescheduleforreviewer(Schedule schedule) {
	
		 Schedule s=pmdao.find(schedule.getId());
		 s.setBriefDescription(schedule.getBriefDescription());
		 s.setDetailedDescription(schedule.getDetailedDescription());
		 s.setUserRefNumber(schedule.getUserRefNumber());
		 s.setTaskPerformed(schedule.getTaskPerformed());
		 s.setFrequency(schedule.getFrequency());
		 s.setStartDate(schedule.getStartDate());
		 s.setEndDate(schedule.getEndDate());
		 s.setScheduleDate(schedule.getScheduleDate());
		 s.setTeams(schedule.getTeams());
		 s.setReviewstatus(schedule.getReviewstatus());
		pmdao.updatescheduleforreviewer(s);
		
	}

	@Override
	public List<Schedule> getscheduledforadmin() {
		return pmdao.getscheduledforadmin();
	}

	@Override
	public void updatescheduleforadmin(Schedule schedule, HttpSession session) {
		
		System.out.println(schedule.toString()+"lllllllllllllll");
	     Schedule s=pmdao.find(schedule.getId());
	       s.setBriefDescription(schedule.getBriefDescription());
	       s.setDetailedDescription(schedule.getDetailedDescription());
	       s.setUserRefNumber(schedule.getUserRefNumber());
	       s.setTaskPerformed(schedule.getTaskPerformed());
	       s.setFrequency(schedule.getFrequency());
	       s.setStartDate(schedule.getStartDate());
	       s.setEndDate(schedule.getEndDate());
	       s.setScheduleDate(schedule.getScheduleDate());
	       s.setTeams(schedule.getTeams());
	       s.setStatus(schedule.getStatus());
	       s.setReviewstatus(schedule.getReviewstatus());
	       if(s.getStatus().equals("approved"))
	    	   
	       maketasksforschedule(s,(UserDetail) session.getAttribute("user"));
		
		System.out.println("hhhhhhhhhhhhhhhhhhhhhh");
	       pmdao.updatescheduleforadmin(schedule, session);
	}

	
	public boolean maketasksforschedule(Schedule s, UserDetail ud)
	  {
			     List<StartScheduleDate> scheduleDates = new ArrayList<StartScheduleDate>();

	      scheduleDates = getScheduleDatesOnFrequency
	    	(
	    	  s.getScheduleDate(),
	          s.getEndDate(),
	          s.getFrequency(),
	          s.getEquipment().get(0).getWorkspace()
	        );
	      
	      List<Task> tasks = new ArrayList<Task>();
	      int tsk = 1;
	      
	      for (StartScheduleDate scheduleDate : scheduleDates) 
	      {
	        for(int i = 0;i<s.getEquipment().size(); i++)
	        {
	        
	        String taskNumber = "PMTASK-" + s.getEquipment().get(0).getWorkspace().getId()
	        														+ "-" + s.getId()  + "-" + tsk;
	        Task pmt = new Task();
	        pmt.setScheduleDate(scheduleDate.getScheduleDate());
	        pmt.setActualScheduleDate(scheduleDate.getActualScheduleDate());
	        pmt.setTask_number(taskNumber);
	        Calendar c = Calendar.getInstance();
	        c.setTime(scheduleDate.getScheduleDate()); 
	        c.add(Calendar.DAY_OF_MONTH, 5);
	    
	        pmt.setStatus(Constants.OPEN); 
	        pmt.setEquipment(s.getEquipment().get(i));
	        pmt.setSchedule(s);
	        pmt.setUserDetail(ud);
	        
	        List<TaskChecklist> taskCheckLists = new ArrayList<TaskChecklist>();
	        
	        for (ChecklistPropertyTitle chklist : s.getChecklistHeader().getPropertyTitles() )
	        {
			          for(ChecklistProperty cp : chklist.getProperties())
			          {
			            TaskChecklist taskitem = new TaskChecklist();
			            taskitem.setDescription(cp.getDescription());
			            taskitem.setDescriptionType(cp.getDescriptionType());
			            taskitem.setRemarks(null);
			            taskitem.setStatus(Constants.YES);
			            taskitem.setTask(pmt);
			            
			            taskCheckLists.add(taskitem);
			          }
	          
	        }
	        
	        pmt.setCheckLists(taskCheckLists);
	        tasks.add(pmt);
	        System.out.println("after addd");
	        tsk = tsk + 1;
	        System.out.println("after ----------addd");
	        
	      }
	        
	      }
	      

	       pmtDao.addList(tasks);
	       pmdao.persist(s);
	       return true;
	  }

	@Override
	public List<StartScheduleDate> getScheduleDatesOnFrequency(Date sDate, Date endDate, int frequency, Workspace w) {
		
		
		List<StartScheduleDate> scheduleList = new ArrayList<StartScheduleDate>();
		DateTime scheduleDate = new DateTime(sDate);
		DateTime end = new DateTime(endDate);
		end = end.plusDays(1);
		System.out.println("p.days="+ Days.daysBetween(scheduleDate.toLocalDate(),	end.toLocalDate()).getDays());
		Period p2 = new Period();
		int schedules = 0;
		System.out.println("total months between :"+ (Months.monthsBetween(scheduleDate.toLocalDate(),	end.toLocalDate()).getMonths()));
		
		switch (frequency)
		{
		
		case 1:
			schedules = Days.daysBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getDays();
			p2 = Period.days(1);
			break;
		case 2:
			schedules = Weeks.weeksBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getWeeks() + 1;
			p2 = Period.weeks(1);
			break;
		case 3:
			schedules = (Weeks.weeksBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getWeeks() / 2) + 1;
			p2 = Period.weeks(2);
			break;
		case 4:
			schedules = (Months.monthsBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getMonths()) + 1;
			p2 = Period.months(1);

			break;
		case 5:
			schedules = (Months.monthsBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getMonths() / 3) + 1;
			p2 = Period.months(3);
			break;
		case 6:
			schedules = (Months.monthsBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getMonths() / 6) + 1;
			p2 = Period.months(6);
			break;
		case 7:
			schedules = (Years.yearsBetween(scheduleDate.toLocalDate(),
					end.toLocalDate()).getYears()) + 1;
			p2 = Period.years(1);
			break;
		default:
			break;
		}

		List<ExceptionDate> datelist = exceptionDateService.list(w);

		for (int i = 0; i < schedules; i++) {

			StartScheduleDate startScheduleDate = new StartScheduleDate();
			Date nextStartDate = scheduleDate.toDate();
			startScheduleDate.setScheduleDate(nextStartDate);
			// System.out.println("SHEDULEE  "+i+"   Size"+schedules+" scheduleDate->"+scheduleDate);
			Date exactDate = exceptionDateService.getAllowedDate(
					scheduleDate.toDate(), datelist, w);
			/*
			 * if(exactDate.after(end.toDate())) exactDate=null;
			 */
			startScheduleDate.setActualScheduleDate(exactDate);
			scheduleDate = scheduleDate.plus(p2);
			scheduleList.add(startScheduleDate);

		}

		return scheduleList;
		
	}

	@Override
	public Long getSearchCount(SearchPMSchedule searchPMSchedule) {
		
		return pmdao.getSearchCount(searchPMSchedule);
	}

	@Override
	public List<Schedule> getSearchResult(SearchPMSchedule searchPMSchedule, Integer from, Integer rows) {
		return pmdao.getAll(searchPMSchedule, from,rows);
	}

	@Override
	public List<Schedule> getAll(SearchPMSchedule searchPMSchedule, Integer from, Integer rows) {
		
		return pmdao.getAll(searchPMSchedule, from, rows);
	}

	@Override
	public List<Task> listTasks(Schedule schedule) {
		
		return pmdao.listTasks(schedule);
	}

	@Override
	public void remove(Task t) {
		
		pmdao.remove(t);
		
	}

	@Override
	public boolean update(Schedule schedule, List<Task> newTasklist,
			List<ChecklistPropertyTitle> checklistpropertyTitles, UserDetail ud, HttpSession session) 
	{
		
		Schedule s = pmdao.find(schedule.getId());
		
		List<Equipment> eq = s.getEquipment();

		pmdao.update(schedule);
				
		List<StartScheduleDate> scheduleDates = null;
		
		if (!newTasklist.isEmpty())
		{
			scheduleDates = getScheduleDatesOnFrequency(newTasklist.get(newTasklist.size() - 1).getScheduleDate(),
					schedule.getEndDate(), schedule.getFrequency(), schedule
							.getEquipment().get(0).getWorkspace());
			
			scheduleDates = scheduleDates.subList(1, scheduleDates.size() - 1);

		} else {
			scheduleDates = getScheduleDatesOnFrequency(
					schedule.getScheduleDate(),schedule.getEndDate(),
					schedule.getFrequency(), schedule.getEquipment().get(0)
							.getWorkspace());
		}
		
		int counter = newTasklist.size() + 1;
		for (StartScheduleDate scheduleDate : scheduleDates) {
			for(int i = 0;i<eq.size(); i++)
			{
			
				
			String taskNumber = "PMTASK-"
					+ schedule.getEquipment().get(0).getWorkspace().getId() + "-" + schedule.getId()
					+ "-" + counter;
			
			pmdao.remove(pmtDao.findByTaskId(taskNumber));
			Task pmt = new Task();
			pmt.setScheduleDate(scheduleDate.getScheduleDate());
			pmt.setActualScheduleDate(scheduleDate.getActualScheduleDate());
			pmt.setTask_number(taskNumber);
			Calendar c = Calendar.getInstance();
			c.setTime(scheduleDate.getScheduleDate()); // Now use today date.
			c.add(Calendar.DAY_OF_MONTH, 5);
			// pmt.setDueDate(c.getTime());
			pmt.setStatus(Constants.OPEN);
			pmt.setEquipment(eq.get(i));
			pmt.setSchedule(schedule);
			pmt.setUserDetail(ud);
			
			List<TaskChecklist> taskCheckLists = new ArrayList<TaskChecklist>();
			
			for (ChecklistPropertyTitle chklist : eq.get(i).getChecklists().get(i).getPropertyTitles() )
			{
			
				for(ChecklistProperty cp : chklist.getProperties())
				{
					TaskChecklist taskitem = new TaskChecklist();
					taskitem.setDescription(cp.getDescription());
					taskitem.setDescriptionType(cp.getDescriptionType());
					taskitem.setRemarks(null);
					taskitem.setStatus(Constants.YES);
					taskitem.setTask(pmt);
					taskCheckLists.add(taskitem);
					
				}
				
			}

			pmt.setCheckLists(taskCheckLists);
			newTasklist.add(pmt);
			counter++;
		}
		}

		for (Task t : newTasklist)
			pmtDao.update(t);
		
		return true;
		
	}

	@Override
	public boolean delete(Long id) {
	
		return pmdao.delete(id);
	}

	@Override
	public List<String> getScheduleTeamEmails(String pmsNo) {
	
		return pmdao.getScheduleTeamEmails(pmsNo);
	}

	@Override
	public List<Schedule> getAllPMSchedules(Workspace w) {
		// TODO Auto-generated method stub
		return pmdao.getAllPMSchedules(w);
	}

	@Override
	public long getMaxScheduleId() {
		// TODO Auto-generated method stub
		return pmdao.getMaxScheduleId();
	}
	
	
}
