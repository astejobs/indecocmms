package com.aste.lsme.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Workspace")
public class Workspace {
	
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Size(max = 50)
	@NotEmpty(message = "Contract cannot be empty")
	@Column(name = "Workspace_ID")
	String workspaceId;

	@NotEmpty(message = "Contract Description cannot be empty")
	@Column(name = "Description")
	String description;

	@NotEmpty(message = "Building Description cannot be empty")
	@Size(max = 200)
	@Column(name = "Building_Description")
	String buildingDescription;

	@NotEmpty(message = "Building Owner cannot be empty")
	@Size(max = 100)
	@Column(name = "Building_Owner")
	String owner;

	@NotEmpty(message="Contractor cannot be empty")
	@Size(max = 50)
	@Column(name = "Contractor")
	String contractor; 
	
	@NotEmpty(message="Building Owner Pay Amount cannot be empty")
	@Column(name = "Building_Owner_Pay_Amount")
	String bldngOwnerPayAmt;

/*	@Pattern(regexp = "^(\\d{1,15})|(\\d{1,15}.\\d{0,2})$", message = "chars_not_allowed")
	@Column(name = "Contractor_Pay_Amount")
	String contractorPayAmt;
*/
	
	@Column(name = "Start_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date startDate;

	
	@Column(name = "End_Date")
	@DateTimeFormat(pattern = "dd-MM-YYYY")
	Date endDate;

	
	@Column(name = "Project_Image")
	String projectImage;

	@ManyToMany(mappedBy="workspaces")
	@JsonIgnore
	List<UserGroup> userGroups;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getWorkspaceId() {
		return workspaceId;
	}


	public void setWorkspaceId(String workspaceId) {
		this.workspaceId = workspaceId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBuildingDescription() {
		return buildingDescription;
	}


	public void setBuildingDescription(String buildingDescription) {
		this.buildingDescription = buildingDescription;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getContractor() {
		return contractor;
	}


	public void setContractor(String contractor) {
		this.contractor = contractor;
	}


	public String getBldngOwnerPayAmt() {
		return bldngOwnerPayAmt;
	}


	public void setBldngOwnerPayAmt(String bldngOwnerPayAmt) {
		this.bldngOwnerPayAmt = bldngOwnerPayAmt;
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


	public String getProjectImage() {
		return projectImage;
	}


	public void setProjectImage(String projectImage) {
		this.projectImage = projectImage;
	}


	public List<UserGroup> getUserGroups() {
		return userGroups;
	}


	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bldngOwnerPayAmt == null) ? 0 : bldngOwnerPayAmt.hashCode());
		result = prime * result + ((buildingDescription == null) ? 0 : buildingDescription.hashCode());
		result = prime * result + ((contractor == null) ? 0 : contractor.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((projectImage == null) ? 0 : projectImage.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((userGroups == null) ? 0 : userGroups.hashCode());
		result = prime * result + ((workspaceId == null) ? 0 : workspaceId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workspace other = (Workspace) obj;
		if (bldngOwnerPayAmt == null) {
			if (other.bldngOwnerPayAmt != null)
				return false;
		} else if (!bldngOwnerPayAmt.equals(other.bldngOwnerPayAmt))
			return false;
		if (buildingDescription == null) {
			if (other.buildingDescription != null)
				return false;
		} else if (!buildingDescription.equals(other.buildingDescription))
			return false;
		if (contractor == null) {
			if (other.contractor != null)
				return false;
		} else if (!contractor.equals(other.contractor))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (projectImage == null) {
			if (other.projectImage != null)
				return false;
		} else if (!projectImage.equals(other.projectImage))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (userGroups == null) {
			if (other.userGroups != null)
				return false;
		} else if (!userGroups.equals(other.userGroups))
			return false;
		if (workspaceId == null) {
			if (other.workspaceId != null)
				return false;
		} else if (!workspaceId.equals(other.workspaceId))
			return false;
		return true;
	}

	
	
}
