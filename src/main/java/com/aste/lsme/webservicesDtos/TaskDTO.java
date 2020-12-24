package com.aste.lsme.webservicesDtos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.aste.lsme.domain.Equipment;

import com.aste.lsme.domain.Schedule;
import com.aste.lsme.utils.Encryptor;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDTO {

	long taskId;
	String task_number;
	String status;
	String scheduleNumber;
	String briefDescription;
	long locationId;
	String locationName;
	long buildingId;
	String buildingName;
	String equipmentCode;
    String equipmentName;
	Date scheduleDate;
	String completedBy;

	Date completedDate;

	LocalDateTime acknowledgementTime;

	Date completedTime;

	String remarks;
	Date endDate;
	Date dueDate;
	
	String beforeImage;
	String afterImage;

	
	
	
	
	
	public String getBeforeImage() {
		return beforeImage;
	}

	public void setBeforeImage(String beforeImage) {
		this.beforeImage = beforeImage;
	}

	public String getAfterImage() {
		return afterImage;
	}

	public void setAfterImage(String afterImage) {
		this.afterImage = afterImage;
	}

	public String getEquipmentName() {
		return Encryptor.decrypt(equipmentName);
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public LocalDateTime getAcknowledgementTime() {
		return acknowledgementTime;
	}

	public void setAcknowledgementTime(LocalDateTime acknowledgementTime) {
		this.acknowledgementTime = acknowledgementTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getTask_number() {
		return task_number;
	}

	public void setTask_number(String task_number) {
		this.task_number = task_number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public Date getCompletedDate() {

		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {

		this.completedDate = completedDate;

	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {

		this.completedTime = completedTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public TaskDTO() {

	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public TaskDTO(String task_number, String status) {
		super();
		this.task_number = task_number;
		this.status = status;
	}

	public TaskDTO(long id, String status, Date completedDate, Date completedTime, String remarks) {
		super();
		this.taskId = id;
		this.status = status;
		this.completedDate = completedDate;
		this.completedTime = completedTime;
		this.remarks = remarks;
	}

	public TaskDTO(long id, String status, Date completedDate, Date completedTime, String remarks,LocalDateTime acknowledgementTime) {
		super();
		this.taskId = id;
		this.status = status;
		this.completedDate = completedDate;
		this.completedTime = completedTime;
		this.remarks = remarks;
		this.acknowledgementTime=acknowledgementTime;
	}
	public String getScheduleNumber() {
		return scheduleNumber;
	}

	public void setScheduleNumber(String scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return Encryptor.decrypt(locationName);

	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return Encryptor.decrypt(buildingName);
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public TaskDTO(long id, String task_number, String status, String scheduleNumber, String equipmentCode,
			long locationId, String locationName, long buildingId, String buildingName, Date scheduleDate,
			String completedBy, Date completedDate, Date completedTime, String remarks,String beforeImage,String afterImage) {
		super();
		this.taskId = id;
		this.task_number = task_number;
		this.status = status;
		this.scheduleNumber = scheduleNumber;
		this.equipmentCode = equipmentCode;
		this.locationId = locationId;
		this.locationName = locationName;
		this.buildingId = buildingId;
		this.buildingName = buildingName;

		this.scheduleDate = scheduleDate;
		this.completedBy = completedBy;
		this.completedDate = completedDate;
		this.completedTime = completedTime;
		this.remarks = remarks;
		this.beforeImage=beforeImage;
		this.afterImage=afterImage;
	}

	
	public TaskDTO(long id, String task_number, String status, String remarks, String scheduleNumber,
			String briefDescription, String equipmentCode, long locationId, String locationName, long buildingId,
			String buildingName, Date scheduleDate, String completedBy, Date completedDate, Date completedTime,
			Date dueDate, Date endDate) {
		super();
		this.taskId = id;
		this.task_number = task_number;
		this.status = status;
		this.remarks = remarks;
		this.scheduleNumber = scheduleNumber;
		this.briefDescription = briefDescription;
		this.equipmentCode = equipmentCode;
		this.locationId = locationId;
		this.locationName = locationName;
		this.buildingId = buildingId;
		this.buildingName = buildingName;

		this.scheduleDate = scheduleDate;
		this.completedBy = completedBy;
		this.completedDate = completedDate;
		this.completedTime = completedTime;
		this.dueDate = dueDate;
		this.endDate = endDate;

	}

	public TaskDTO( long id,String task_number, String status,String equipmentName, String locationName, String buildingName, 
			Date scheduleDate,String beforeImage,String afterImage) {
		super();
		this.taskId=id;
		this.task_number = task_number;
		this.status = status;
		this.equipmentName = equipmentName;
		this.locationName = locationName;
		this.buildingName = buildingName;
		this.scheduleDate = scheduleDate;
		this.beforeImage=beforeImage;
		this.afterImage=afterImage;
	}

	
}
