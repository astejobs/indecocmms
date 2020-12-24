package com.aste.lsme.dao;

import java.text.ParseException;
import java.util.List;

import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.Content;
import com.aste.lsme.domain.EmailTaskDTO;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.ScheduledEquipment;
import com.aste.lsme.domain.SearchPMTask;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.TaskDTO;


public interface PMTaskDao 
{
	
	boolean addList(List<Task> tasks);




	Task find(Long taskid);

	boolean update(Task pmTask);

	long getSearchCount(SearchPMTask searchPMTask);

	List<Task> getSearchResult(SearchPMTask searchCriteria, int from);
	
	public List<EmailTaskDTO> getEmailTasks(String pmsNo, Workspace w) throws ParseException;
	
	Task findByTaskId(String taskId);

	List<TaskChecklist> listallChecklists(Task pmtask);

	public List<Schedule> getAll(Workspace w);
    List<TaskChecklist> gettaskchecklist(Long id);
	public List<Task> getSearchResult(SearchPMTask searchCriteria, int from,
			int to) ;

    List<ChecklistProperty> getchecklistpropertyontask(Long id);
    public Long count(ScheduledEquipment searchPMSchedule) throws ParseException;
	public List<Task> getAll(ScheduledEquipment searchPMSchedule, Integer from, Integer rows) throws ParseException;
	 public List<Task> getScheduledEquipment(ScheduledEquipment seq) throws ParseException;

	 public TaskChecklist findById(Long id);
     public void update(TaskChecklist checklist);
     public TaskDTO findTask(Long id);
     
     public void updateImage(Content con,boolean beforeImage); 
 	public List<TaskDTO> getTasks(String username, String role, String w, String search);


}
