package com.aste.lsme.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.SearchPMSchedule;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.Workspace;

public interface PMDaoInterface 
{
	Schedule find(Long id);
	public void persist(Schedule schedule);
	public List<Schedule> getscheduledtoreview();
	String getCheckListNameOfSchedule(Long id);
	public void updatescheduleforreviewer(Schedule schedule);
	public List<Schedule> getscheduledforadmin();
	public void updatescheduleforadmin(Schedule schedule, HttpSession session);
	Long getSearchCount(SearchPMSchedule searchPMSchedule);
	public List<Schedule> getAll(SearchPMSchedule searchPMSchedule,Integer from, Integer rows);
	List<Task> listTasks(Schedule schedule);
	void remove(Task t);
	public Boolean update(Schedule schedule);
	public List<String> getScheduleTeamEmails(String pmsNo);
	List<Schedule> getAllPMSchedules(Workspace w);
	boolean delete(Long id);
	long getMaxScheduleId();
}
 