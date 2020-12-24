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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@Entity
//@Table(name="Civil_Level4")
public class CivilLevel4 {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="CivilLevel4_id")
	Long id;
	
	@Column(name="Civil_Level4_Name")
	@NotEmpty(message="Civil Level 4 Name cannot be empty")
	String civilLevel4Name;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="civilLevel3_id")
	@NotNull(message="Please Select Civil Level 3")
	CivilLevel3 civilLevel3;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WORKSPACE")
	Workspace workspace;

	
	
	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	
	
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCivilLevel4Name() {
		return civilLevel4Name;
	}

	public void setCivilLevel4Name(String civilLevel4Name) {
		this.civilLevel4Name = civilLevel4Name;
	}

	public CivilLevel3 getCivilLevel3() {
		return civilLevel3;
	}
	
	public void setCivilLevel3(CivilLevel3 civilLevel3) {
		this.civilLevel3 = civilLevel3;
	}
	
}
