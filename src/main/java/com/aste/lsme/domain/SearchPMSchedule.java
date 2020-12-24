package com.aste.lsme.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchPMSchedule {

	String schedule_number;
	String UserReferenceNo;
	Workspace workspace;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date startDate;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date endDate;

	Equipment equipment;
	String status;

	public String getSchedule_number() {
		return schedule_number;
	}

	public void setSchedule_number(String schedule_number) {
		this.schedule_number = schedule_number;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserReferenceNo() {
		return UserReferenceNo;
	}

	public void setUserReferenceNo(String userReferenceNo) {
		UserReferenceNo = userReferenceNo;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "schedule_number="+schedule_number+"  UserReferenceNo="+UserReferenceNo+" workspace="+workspace+
				" \nstartDate="+startDate+"  endDate="+endDate+" status="+status+" equipment="+equipment;
	}

}

