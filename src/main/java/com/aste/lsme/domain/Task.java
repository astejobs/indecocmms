package com.aste.lsme.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;


@JsonIgnoreType
@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "schedule")
	Schedule schedule;
	
	@OneToOne
	private Equipment equipment;

	@Column(name = "task_number", unique = true)
	String task_number;

	@Column(name = "schedule_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date scheduleDate;

	@Column(name = "actual_schedule_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date actualScheduleDate;

	@Column(name = "completed_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date completedDate;

	@Column(name = "completed_by")
	String completedBy;

	@Column(name = "end_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm a")
	Date endDate;

	@Column(name = "due_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date dueDate;
	
	@Column(name="completed_time")
	@DateTimeFormat(pattern="HH : mm")
	@Temporal(TemporalType.TIME)
	 Date completedTime;

	@Column(name = "remarks")
	String remarks;

	@Column(name = "status")
	String status;

	@Column(name = "other_cost")
	Float otherCost = 0f;

	@Column(name = "total_cost")
	Double totalCost = 0d;

	@Column(name = "meter_reading")
	Integer meterReading;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	List<TaskChecklist> checkLists;
	
	
	@JoinColumn(name = "user_detail")
	@OneToOne(fetch = FetchType.EAGER)
	UserDetail userDetail;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	List<EquipmentTaskReading> equipmentTaskReadings;

	@Column(name = "COMMENTS")
	String comments;

	@Column(name = "SRVC_CHKD_SIGN1")
	String srvc_chkd_sign1;

	@Column(name = "SRVC_VERIFY_SIGN1")
	String srvc_verify_sign1;

	@Column(name = "SIGN_ACKNWLDG_BY")
	String srvc_acknwldg_sign;
	
	
	@Column(name = "beforeImage")
	 String beforeImage;
	
	 @Column(name = "afterImage")
	 String afterImage;
	 
	 
	 @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	 @Column(name="acknowledgementTime")
	 LocalDateTime acknowledgementTime;

	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSrvc_acknwldg_sign() {
		return srvc_acknwldg_sign;
	}

	public void setSrvc_acknwldg_sign(String srvc_acknwldg_sign) {
		this.srvc_acknwldg_sign = srvc_acknwldg_sign;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getTask_number() {
		return task_number;
	}

	public void setTask_number(String task_number) {
		this.task_number = task_number;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Date getActualScheduleDate() {
		return actualScheduleDate;
	}

	public void setActualScheduleDate(Date actualScheduleDate) {
		this.actualScheduleDate = actualScheduleDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Float otherCost) {
		this.otherCost = otherCost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(Integer meterReading) {
		this.meterReading = meterReading;
	}

	public List<TaskChecklist> getCheckLists() {
		return checkLists;
	}

	public void setCheckLists(List<TaskChecklist> checkLists) {
		this.checkLists = checkLists;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSrvc_chkd_sign1() {
		return srvc_chkd_sign1;
	}

	public void setSrvc_chkd_sign1(String srvc_chkd_sign1) {
		this.srvc_chkd_sign1 = srvc_chkd_sign1;
	}

	public String getSrvc_verify_sign1() {
		return srvc_verify_sign1;
	}

	public void setSrvc_verify_sign1(String srvc_verify_sign1) {
		this.srvc_verify_sign1 = srvc_verify_sign1;
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

	public List<EquipmentTaskReading> getEquipmentTaskReadings() {
		return equipmentTaskReadings;
	}

	public void setEquipmentTaskReadings(
			List<EquipmentTaskReading> equipmentTaskReadings) {
		this.equipmentTaskReadings = equipmentTaskReadings;
	}

	

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

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

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public LocalDateTime getAcknowledgementTime() {
		return acknowledgementTime;
	}

	public void setAcknowledgementTime(LocalDateTime acknowledgementTime) {
		this.acknowledgementTime = acknowledgementTime;
	}

	
	
	

}
