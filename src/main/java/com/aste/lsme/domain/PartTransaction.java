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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PartTransaction {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PartsInWarehouse_ID")
	PartsInWarehouse partsInWarehouse;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ReservedBy_ID")
	UserDetail reservedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IssuedBy_ID")
	UserDetail issuedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RecievedBy_ID")
	UserDetail recievedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ReturnedBy_ID")
	UserDetail returnedBy;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ReturnRecievedBy_ID")
	UserDetail returnRecievedBy;
	
	@Column(name = "Reserved_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date reservedDate;

	@Column(name = "Issued_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date issuedDate;
	
	@Column(name = "Recieved_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date recievedDate;
	
	@Column(name = "Returned_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date returnedDate;
	
	@Column(name = "ReturnRecieved_Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date returnRecievedDate;
	
	@Column(name="Reserved_Quantity")
	double reservedQuantity;
	
	@Column(name="Issued_Quantity")
	double issuedQuantity;
	
	@Column(name="Returned_Quantity")
	double returnedQuantity;
	
	@Column(name="Pending_Quantity")
	double pendingQuantity;
	
	@Column(name="Partial_Issue")
	boolean partialIssue;
	
	@Column(name="Status")
	String status;

	@Column(name="Report_Task_ID")
	String reportTaskId;

	@Column(name="Transaction_Cost")
	double transactionCost;
	
	@Column(name="Batch_ID")
	Long batchId;
	
	@Transient
	String batchNo;//only used for showing batchNO in report
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public UserDetail getReturnedBy() {
		return returnedBy;
	}

	public void setReturnedBy(UserDetail returnedBy) {
		this.returnedBy = returnedBy;
	}

	public UserDetail getReturnRecievedBy() {
		return returnRecievedBy;
	}

	public void setReturnRecievedBy(UserDetail returnRecievedBy) {
		this.returnRecievedBy = returnRecievedBy;
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

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Date getReturnRecievedDate() {
		return returnRecievedDate;
	}

	public void setReturnRecievedDate(Date returnRecievedDate) {
		this.returnRecievedDate = returnRecievedDate;
	}

	public double getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(double reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

	public double getIssuedQuantity() {
		return issuedQuantity;
	}

	public void setIssuedQuantity(double issuedQuantity) {
		this.issuedQuantity = issuedQuantity;
	}

	public double getReturnedQuantity() {
		return returnedQuantity;
	}

	public void setReturnedQuantity(double returnedQuantity) {
		this.returnedQuantity = returnedQuantity;
	}

	public double getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(double pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	
	public boolean isPartialIssue() {
		return partialIssue;
	}

	public void setPartialIssue(boolean partialIssue) {
		this.partialIssue = partialIssue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReportTaskId() {
		return reportTaskId;
	}

	public void setReportTaskId(String reportTaskId) {
		this.reportTaskId = reportTaskId;
	}

	public double getTransactionCost() {
		return transactionCost;
	}

	public void setTransactionCost(double transactionCost) {
		this.transactionCost = transactionCost;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	
}
