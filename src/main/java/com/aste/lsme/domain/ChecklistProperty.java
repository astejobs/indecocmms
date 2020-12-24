package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checklist_property")
public class ChecklistProperty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	ChecklistPropertyTitle checklistPropertyTitle;

	@Column(name = "description")
	String description;
	
	@Column(name = "type")
	  private String descriptionType;
	
	
	@Column(name="status")
	private String status;
	
	@Column(name="remarks")
	private String remarks;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChecklistPropertyTitle getChecklistPropertyTitle() {
		return checklistPropertyTitle;
	}

	public void setChecklistPropertyTitle(
			ChecklistPropertyTitle checklistPropertyTitle) {
		this.checklistPropertyTitle = checklistPropertyTitle;
	}

	public String getDescription() {
		System.out.println("i m getting getting dsscription0-0-0-0-0-0-0-0-0-0-0-0-0--");
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
