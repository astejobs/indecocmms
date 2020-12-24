package com.aste.lsme.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.SearchPMSchedule;
import com.aste.lsme.domain.StartScheduleDate;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;

public interface PMService 
{
	
	List<StartScheduleDate> getScheduleDatesOnFrequency(Date sDate,Date endDate, int frequency, Workspace w);
	public void persist(Schedule schedule);
	public List<Schedule> getscheduledtoreview();
	Schedule find(Long id);
	String getCheckListNameOfSchedule(Long id);
	public void updatescheduleforreviewer(Schedule schedule);
	public List<Schedule> getscheduledforadmin();
	public void updatescheduleforadmin(Schedule schedule, HttpSession session);
	Long getSearchCount(SearchPMSchedule searchPMSchedule);
	List<Schedule> getSearchResult(SearchPMSchedule searchPMSchedule,Integer from, Integer rows);
	public List<Schedule> getAll(SearchPMSchedule searchPMSchedule,Integer from, Integer rows);
	List<Task> listTasks(Schedule schedule);
	void remove(Task t);
	boolean update(Schedule schedule, List<Task> newTasklist, List<ChecklistPropertyTitle> checklistpropertyTitles, UserDetail ud,  HttpSession session);
	boolean delete(Long id);
	public List<String> getScheduleTeamEmails(String pmsNo);
	List<Schedule> getAllPMSchedules(Workspace w);
	long getMaxScheduleId();

}
