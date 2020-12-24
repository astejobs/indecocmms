package com.aste.lsme.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name="exception_date")
@Entity
public class ExceptionDate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;
	
	@Column(name="exception_date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date exception_date;
	
	@Column(name="exception_description")
	String exception_description;
	
	@JoinColumn(name="project_id")
	@ManyToOne(fetch = FetchType.EAGER)
	Workspace workspace;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getException_date() {
		return exception_date;
	}

	public void setException_date(Date exception_date) {
		this.exception_date = exception_date;
	}

	public String getException_description() {
		return exception_description;
	}

	public void setException_description(String exception_description) {
		this.exception_description = exception_description;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	
	
}
