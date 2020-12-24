package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OtherRate {

	@Id
	@Column(name="Other_Rate_Id")
	long id;
	
	@Column(name="Unit")
	Integer unit;
	
	@Column(name="Quantity")
	Integer quantity;
	
	@Column(name="UnitPrice")
	Double unitPrice;
	
	@Column(name="Description")
	String description;
	
	@Column(name="TotalCost")
    Double totalCost;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Quotation")
	Quotation quotation;
	
	
	
	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
}
