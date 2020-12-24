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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@Table(name="Cost_Center")

public class CostCenter { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CostCenter_ID")
	private Long id;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30)
	@NotEmpty(message = "Enter CostCenter Description")
    @Column(name = "Cost_Center_Description")
	String costCenterDescription;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30)
	@NotEmpty(message = "Enter CostCenter Name")
    @Column(name = "Cost_Center_Name")
	String costCenterName;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WORKSPACE")
	Workspace workspace;
    


	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCostCenterDescription() {
		return costCenterDescription;
	}

	public void setCostCenterDescription(String costCenterDescription) {
		this.costCenterDescription = costCenterDescription;
	}
	
	@Override
	public String toString() {
		return costCenterName;
	}
}
