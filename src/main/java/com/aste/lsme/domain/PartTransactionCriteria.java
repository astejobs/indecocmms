package com.aste.lsme.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PartTransactionCriteria {

	
	String partName;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date from;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date to;
	
	Long source;
	
	Long destination;
	
	
	


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

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Long getDestination() {
		return destination;
	}

	public void setDestination(Long destination) {
		this.destination = destination;
	}

	

	
	
	
}
	
