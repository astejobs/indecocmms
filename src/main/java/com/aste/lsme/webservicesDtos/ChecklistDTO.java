package com.aste.lsme.webservicesDtos;

import java.util.List;

import com.aste.lsme.domain.ChecklistProperty;

public class ChecklistDTO {
    
	long id;
	long taskId;
	String status;
	String remarks;
	List<String> blanks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ChecklistDTO(long id, String status, String remarks, ChecklistProperty checklistProperty) {
		super();
		this.id = id;
		this.status = status;
		this.remarks = remarks;
	}
	public ChecklistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public List<String> getBlanks() {
		return blanks;
	}
	public void setBlanks(List<String> blanks) {
		this.blanks = blanks;
	}
	
	
}
