package com.aste.lsme.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Quotations {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String companyName;
	
	private Double priceQuoted;
	
	private String refNumbers;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateOfQuotation;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date validityDate;

	@ManyToOne
	private RequestForApproval requestor;
	
	private String filePath;
	
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

	

	public Double getPriceQuoted() {
		return priceQuoted;
	}

	public void setPriceQuoted(Double priceQuoted) {
		this.priceQuoted = priceQuoted;
	}

	public String getRefNumbers() {
		return refNumbers;
	}

	public void setRefNumbers(String refNumbers) {
		this.refNumbers = refNumbers;
	}

	public Date getDateOfQuotation() {
		return dateOfQuotation;
	}

	public void setDateOfQuotation(Date dateOfQuotation) {
		this.dateOfQuotation = dateOfQuotation;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
    
	

	public RequestForApproval getRequestor() {
		return requestor;
	}

	public void setRequestor(RequestForApproval requestor) {
		this.requestor = requestor;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
	
