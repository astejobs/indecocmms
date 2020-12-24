package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "cust_dtl")
public class CustDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMPANY_ID")
	private Long id;
	
	@Column(name = "COMPANY_NAME_ID", length = 50)
	@NotEmpty(message = "reg can't be empty")
	@NotNull
	private String companyNameId;
	
	@Column(name = "COMPANY_NAME", length = 50)
	@NotNull
	@NotEmpty(message = "User Name can't be empty")
	private String companyName;

	@Column(name = "COMPANY_REG", length = 50)
	@NotEmpty(message = "reg can't be empty")
	@NotNull
	private String reg;

	@Column(name = "COMPANY_ADDR", length = 100)
	@NotEmpty(message = "address Name can't be empty")
	@NotNull
	private String address;

	@Column(name = "POSTAL_CODE", length = 10)
	@NotEmpty(message = "Last can't be empty")
	@NotNull
	private String postalCode;

	@Column(name = "COMPANY_DETAILS", length = 100)
	@NotEmpty(message = "COMPANY_DETAILS can't be empty")
	@NotNull
	private String details;

	/*@OneToMany(mappedBy="custDetail",fetch=FetchType.LAZY)
	private List<UserDetail> userDetails;*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}



	public String getCompanyNameId() {
		return companyNameId;
	}

	public void setCompanyNameId(String companyNameId) {
		this.companyNameId = companyNameId;
	}

	
}
