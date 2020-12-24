package com.aste.lsme.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchPMTask {
	
	String task_number;	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date startDueDate;	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date endDueDate;
	
	Equipment equipment;	
	
	String[] status;	

	Schedule Schedule;
	
	Workspace workspace;
	
	String value;
	
	String slot;
	
	String elapse;
	
	
	
	
    
	

	public String getElapse() {
		return elapse;
	}

	public void setElapse(String elapse) {
		this.elapse = elapse;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getTask_number() {
		return task_number;
	}

	public void setTask_number(String task_number) {
		this.task_number = task_number;
	}

	

	public Date getStartDueDate() {
		return startDueDate;
	}

	public void setStartDueDate(Date startDueDate) {
		this.startDueDate = startDueDate;
	}

	public Date getEndDueDate() {
		return endDueDate;
	}

	public void setEndDueDate(Date endDueDate) {
		this.endDueDate = endDueDate;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	
	public Schedule getSchedule() {
		return Schedule;
	}

	public void setSchedule(Schedule schedule) {
		Schedule = schedule;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	
	
	
	
	
}
