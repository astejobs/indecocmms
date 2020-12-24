package com.aste.lsme.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aste.lsme.customJSONserializer.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EmailTaskDTO {

String taskNo;
	
	String equipmentCode;
	
	String equipmentName;
	
	@JsonSerialize(using=DateSerializer.class)
	Date date;

	public EmailTaskDTO() {
	}
	
	public EmailTaskDTO(String taskNo, String equipmentCode, String equipmentName, Date date) {
		super();
		this.taskNo = taskNo;
		this.equipmentCode = equipmentCode;
		this.equipmentName = equipmentName;
		this.date = date;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public String getStringDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(this.date);
	}
	
	@Override
	public String toString() {
		return "TaskNo=" + taskNo + ",Equipment Name="+equipmentName+",Equipment Code="+equipmentCode+",Date="+getDate()+"";
	}


	
	
}
