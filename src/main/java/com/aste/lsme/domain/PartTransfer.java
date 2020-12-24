package com.aste.lsme.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PartTransfer{

	
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne
	Warehouse requestor;
	
	@ManyToOne
	Warehouse issuer;
	
	@ManyToOne
	PartsInWarehouse partsInWarehouse;
	
	@ManyToOne
	UserDetail reservedBy;
	
	@ManyToOne
	UserDetail issuedBy;
	
	@ManyToOne
	UserDetail recievedBy;
	
	@Column(name = "Reserved_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date reservedDate;

	@Column(name = "Issued_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date issuedDate;
	
	@Column(name = "Recieved_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date recievedDate;
	
	@Column(name="Reserved_Quantity")
	Double reservedQuantity;
	
	@Column(name="Issued_Quantity")
	Double issuedQuantity;
	
	@Column(name="Status")
	String status;

	@Column(name="Batch_ID")
	long batchId;
	
	@Column(name="Part_Batch_Name")
	String batchNo;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Warehouse getRequestor() {
		return requestor;
	}

	public void setRequestor(Warehouse requestor) {
		this.requestor = requestor;
	}

	public Warehouse getIssuer() {
		return issuer;
	}

	public void setIssuer(Warehouse issuer) {
		this.issuer = issuer;
	}

	public PartsInWarehouse getPartsInWarehouse() {
		return partsInWarehouse;
	}

	public void setPartsInWarehouse(PartsInWarehouse partsInWarehouse) {
		this.partsInWarehouse = partsInWarehouse;
	}

	public UserDetail getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(UserDetail reservedBy) {
		this.reservedBy = reservedBy;
	}

	public UserDetail getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(UserDetail issuedBy) {
		this.issuedBy = issuedBy;
	}

	public UserDetail getRecievedBy() {
		return recievedBy;
	}

	public void setRecievedBy(UserDetail recievedBy) {
		this.recievedBy = recievedBy;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public Double getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(Double reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	public Double getIssuedQuantity() {
		return issuedQuantity;
	}

	public void setIssuedQuantity(Double issuedQuantity) {
		this.issuedQuantity = issuedQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	
}
