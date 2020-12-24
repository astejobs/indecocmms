package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Fault_Category")
public class FaultCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
   private 	Long id;
	
	@NotEmpty(message="Enter Name")
	@Column(name="Fault_ID")
	String name;
	
	@NotEmpty(message = "Enter Fault_Description ")
	@Column(name="Fault_Description")
	String description;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Priorty_ID")
	Priorty priorty;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WORKSPACE")
	Workspace workspace;
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
	public Priorty getPriorty() {
		return priorty;
	}

	public void setPriorty(Priorty priorty) {
		this.priorty = priorty;
	}

	public Workspace getWorkspace() {
		return workspace;
	}
	
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	

}
