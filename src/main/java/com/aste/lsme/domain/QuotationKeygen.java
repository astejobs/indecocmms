package com.aste.lsme.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuotationKeygen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	Workspace workspace;
	
	long couter;
	
	long month;
	
	

	

	public long getMonth() {
		return month;
	}

	public void setMonth(long month) {
		this.month = month;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public long getCouter() {
		return couter;
	}

	public void setCouter(long couter) {
		this.couter = couter;
	}

	
	
	
	
	
}
