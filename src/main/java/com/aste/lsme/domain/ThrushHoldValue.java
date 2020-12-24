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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="pred_maint_thrushHold" ,uniqueConstraints={@UniqueConstraint(columnNames={"equip_id"})})
public class ThrushHoldValue {


	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="id")
	long id;
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull(message = "Equipment Type can not be empty")
	@JoinColumn(name="equip_id")
	Equipment equipment;
	
	
	@NotEmpty(message="Comment can not be empty")
	@Column(name="comments")
	String comments;
	
	@Column(name="task_description")
	String taskDescription;
	
	@Column(name="filename")
	String fileName;
	
	
	@JoinColumn(name="created_by")
	@ManyToOne(fetch = FetchType.EAGER)
	UserDetail user;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
 	@Column(name="created_date")
 	Date createdDate;

 	@LazyCollection(LazyCollectionOption.FALSE)
 	@NotNull(message = "Parameter List can not be empty")
 	@OneToMany(mappedBy="predictiveMaint",cascade={CascadeType.ALL},orphanRemoval=true)
 	List<PredictiveParameter> parameterList;
 	
 	@LazyCollection(LazyCollectionOption.FALSE)
 	@NotNull(message = "Monitor Points can not be empty")
 	@OneToMany(mappedBy="predictiveMaint",cascade={CascadeType.ALL},orphanRemoval=true)
 	List<PredictiveMonitorPoint> monitorPointList;
 	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	

	public Equipment getEquipment() {
		return equipment;
	}


	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getTaskDescription() {
		return taskDescription;
	}


	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}


	public UserDetail getUser() {
		return user;
	}


	public void setUser(UserDetail user) {
		this.user = user;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public List<PredictiveParameter> getParameterList() {
		return parameterList;
	}


	public void setParameterList(List<PredictiveParameter> parameterList) {
		this.parameterList = parameterList;
		
	}


	public List<PredictiveMonitorPoint> getMonitorPointList() {
		return monitorPointList;
	}


	public void setMonitorPointList(List<PredictiveMonitorPoint> monitorPointList) {
		
		this.monitorPointList = monitorPointList;
	}
	
	

	
}


	
	