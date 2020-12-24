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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Department_Id")
	private Long id;
	
	@Size(max = 30,message="Size must be less than 30")
	@NotEmpty(message = "Enter Division Name")
	@Column(name = "Department_Name")
    private String name;

	

	//@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 200)
	@NotEmpty(message = "Enter Division Description")
	@Column(name = "Department_Description")
	private String description;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	Workspace workspace;


	public Workspace getWorkspace() {
		return workspace;
	}



	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	@Override
	public String toString() {
		return name;
	}
	
	
	

}
