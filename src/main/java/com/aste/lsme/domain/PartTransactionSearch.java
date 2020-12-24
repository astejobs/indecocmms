package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PartTransactionSearch {
	
	long warehouseId;
	
	String partName;
	
	String [] status;
	
	String reportTaskId;
	
	public String getReportTaskId() {
		return reportTaskId;
	}

	public void setReportTaskId(String reportTaskId) {
		this.reportTaskId = reportTaskId;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}


	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date from;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date to;

	public long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	
	public void setTo(Date to) {
		this.to = to;
	}
	
	
	

}
