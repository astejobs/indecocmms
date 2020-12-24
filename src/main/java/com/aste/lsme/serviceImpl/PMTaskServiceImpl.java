package com.aste.lsme.serviceImpl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PMTaskDao;
import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.Content;
import com.aste.lsme.domain.EmailTaskDTO;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.ScheduledEquipment;
import com.aste.lsme.domain.SearchPMTask;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.PMTaskService;
import com.aste.lsme.webservicesDtos.TaskDTO;

@Primary
@Service
@Transactional
@Component
public class PMTaskServiceImpl implements PMTaskService {

	@Autowired
	PMTaskDao dao;
	
	@Override
	public boolean addList(List<Task> pmTaskList) {
		
		return dao.addList(pmTaskList);
	}

	

	@Override
	public List<Task> getSearchResult(SearchPMTask searchPMTask,int from) {
		// TODO Auto-generated method stub
		return  dao.getSearchResult(searchPMTask,from);
	}



	@Override
	public Task find(Long taskid) {
		// TODO Auto-generated method stub
		return dao.find(taskid);
	}



	@Override
	public boolean update(Task pmTask) {
		// TODO Auto-generated method stub
		return dao.update(pmTask);
	}



	@Override
	public long getSearchCount(SearchPMTask searchPMTask) {
		// TODO Auto-generated method stub
		return dao.getSearchCount(searchPMTask);
	}



	



	@Override
	public List<EmailTaskDTO> getEmailTasks(String pmsNo, Workspace w) throws ParseException {
		
		return dao.getEmailTasks(pmsNo, w);
	}



	@Override
	public List<TaskChecklist> listallChecklists(Task pmtask) {
		// TODO Auto-generated method stub
		return dao.listallChecklists(pmtask);
	}



	@Override
	public List<TaskChecklist> gettaskchecklist(Long id) {
		return dao.gettaskchecklist(id);
	}



	@Override
	public List<ChecklistProperty> getchecklistpropertyontask(Long id) {
		  return  (List<ChecklistProperty>) dao.getchecklistpropertyontask(id);
		
	}

	public List<Schedule> getAll(Workspace w){
		return dao.getAll(w);
	}

	public List<Task> getSearchResult(SearchPMTask searchCriteria, int from,
			int to) {
		return dao.getSearchResult(searchCriteria, from,to);
	}


	public Long count(ScheduledEquipment searchPMSchedule) throws ParseException{
		 return dao.count(searchPMSchedule);
	}
	public List<Task> getAll(ScheduledEquipment searchPMSchedule, Integer from, Integer rows) throws ParseException{
		 return dao.getAll(searchPMSchedule, from, rows);
	}
	 
	 public List<Task> getScheduledEquipment(ScheduledEquipment seq) throws ParseException {
		 return dao.getScheduledEquipment(seq);
	 }



	@Override
	public Task findByTaskId(String taskId) {
		return dao.findByTaskId(taskId);
	}



	@Override
	public TaskChecklist findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}



	@Override
	public void update(TaskChecklist checklist) {

		dao.update(checklist);
	}



	@Override
	public TaskDTO findTask(Long id) {
  
		return dao.findTask(id);
	}



	@Override
	 public void updateImage(Content con,boolean beforeImage) {
		
		dao.updateImage(con, beforeImage);
		
	}



	@Override
	public List<TaskDTO> getTasks(String username, String role, String w, String search) {
		
		return dao.getTasks(username, role, w, search);
		
	}







}
