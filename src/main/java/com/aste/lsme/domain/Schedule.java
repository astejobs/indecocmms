
package com.aste.lsme.domain;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "schedule", uniqueConstraints = { @UniqueConstraint(columnNames = { "schedule_no" }) })
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@Column(name = "schedule_no")
	String scheduleNumber;

	@Column(name = "brief_description")
	String briefDescription;

	@Column(name = "detailed_description")
	String detailedDescription;

	@Column(name = "user_ref_no")
	String userRefNumber;

	@Column(name = "task_performed")
	String taskPerformed;

	@Column(name = "start_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date startDate;

	@Column(name = "end_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date endDate;

	
	@OneToMany(mappedBy="schedule",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Equipment> equipment;

	@Column(name = "frequency")
	Integer frequency;

	@Column(name = "scedule_by_date", columnDefinition = "BIT", length = 1)
	Boolean scheduleByDate;

	// @Future
	@Column(name = "schedule_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date scheduleDate;

	@Column(name = "schedule_by_meter", columnDefinition = "BIT", length = 1)
	Boolean scheduleByMeter;

	@JoinColumn(name = "meter_name")
	@ManyToOne(fetch = FetchType.EAGER)
	Meter meter;

	@Column(name = "above", columnDefinition = "BIT", length = 1)
	Boolean above;

	@Column(name = "trigger_reading")
	Double triggerReading;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
	List<Task> tasks;
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="Schedule_Team")
	List<Team> teams;
	
	@Column(name="APPROVER_STATUS")
	String status;
	  
	  @Column(name="REVIEWER_STATUS")
	  String reviewstatus;
	  
	@ManyToOne(fetch=FetchType.EAGER)
	ChecklistHeader checklistHeader;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public String getUserRefNumber() {
		return userRefNumber;
	}

	public void setUserRefNumber(String userRefNumber) {
		this.userRefNumber = userRefNumber;
	}

	public String getTaskPerformed() {
		return taskPerformed;
	}

	public void setTaskPerformed(String taskPerformed) {
		this.taskPerformed = taskPerformed;
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

	

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Boolean getScheduleByDate() {
		return scheduleByDate;
	}

	public void setScheduleByDate(Boolean scheduleByDate) {
		this.scheduleByDate = scheduleByDate;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Boolean getScheduleByMeter() {
		if (scheduleByMeter == null)
			return false;
		else
			return scheduleByMeter;
	}

	public void setScheduleByMeter(Boolean scheduleByMeter) {
		this.scheduleByMeter = scheduleByMeter;
	}

	public Meter getMeter() {
		return meter;
	}

	public void setMeter(Meter meter) {
		this.meter = meter;
	}

	public Boolean getAbove() {
		return above;
	}

	public void setAbove(Boolean above) {
		this.above = above;
	}

	public Double getTriggerReading() {
		return triggerReading;
	}

	public void setTriggerReading(Double triggerReading) {
		this.triggerReading = triggerReading;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReviewstatus() {
		return reviewstatus;
	}

	public void setReviewstatus(String reviewstatus) {
		this.reviewstatus = reviewstatus;
	}

	public ChecklistHeader getChecklistHeader() {
		return checklistHeader;
	}

	public void setChecklistHeader(ChecklistHeader checklistHeader) {
		this.checklistHeader = checklistHeader;
	}
	
	
}

