package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class PartBatch {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@NotNull(message="Batch No is required")
	@NotEmpty(message="Batch No is required")
	@Column(name="Batch_Name")
	String batchNo;
	
	@Column(name="Location")
	String location;
	
	@NotNull(message="Quantity is required")
	@Column(name="Quantity")
	Double quantity;
	
	@NotNull(message="Unit Cost is required")
	@Column(name="Unit_Cost")
	Double unitCost;
	
	@ManyToOne
	PartsInWarehouse partsInWarehouse;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public PartsInWarehouse getPartsInWarehouse() {
		return partsInWarehouse;
	}

	public void setPartsInWarehouse(PartsInWarehouse partsInWarehouse) {
		this.partsInWarehouse = partsInWarehouse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		PartBatch other = (PartBatch) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
