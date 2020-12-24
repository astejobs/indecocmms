package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Technician")
public class Technician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Technician_ID")
	Long id;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30)
	@NotEmpty(message = "Please enter Technician Name")
    @Column(name = "Technician_Name")
	String technicianName;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30)
	@NotEmpty(message = "Please enter Designation")
    @Column(name = "Designation")
	String designation;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30)
	@NotEmpty(message = "Please enter Company Name")
    @Column(name = "Company_Name")
	String companyName;
	
	@Email
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "Please Enter the valid EmailID")
	@Column(name = "email_ID")
	@NotNull(message="Please enter Email ID")
	@NotEmpty(message="Please enter Email ID")
	private String email;
	
	@Column(name = "Mobile_Number")
	Integer mobileNumber;
	
	@Size(max = 30)
	@NotEmpty(message = "Please enter Address")
    @Column(name = "Address")
	String address;
	
	@Size(max = 30)
	@NotEmpty(message = "Please choose Status")
    @Column(name = "Status")
	String status;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WORKSPACE")
	Workspace workspace;
	
	@JsonIgnore
	@ManyToMany(mappedBy="attendedBy")
	List<FaultReport> faultReport;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "technician",cascade = CascadeType.MERGE)
	private List<Team> team=new ArrayList<Team>();
    
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

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<FaultReport> getFaultReport() {
		return faultReport;
	}
	
	public void setFaultReport(List<FaultReport> faultReport) {
		this.faultReport = faultReport;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Technician other = (Technician) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return technicianName;
	}

	public List<Team> getTeam() {
		return team;
	}

	public void setTeam(List<Team> team) {
		this.team = team;
	}
	
	
	

}
