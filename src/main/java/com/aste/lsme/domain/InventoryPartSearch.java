package com.aste.lsme.domain;


public class InventoryPartSearch 
{
	Long warehouse;
	String partName;
	String batchNumber;
	Double quantity;
	Long manufacturer;
	Long vendor;
	

	
	
	public Long getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Long warehouse) {
		this.warehouse = warehouse;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Long getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Long manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Long getVendor() {
		return vendor;
	}
	public void setVendor(Long vendor) {
		this.vendor = vendor;
	}
	
	
	

}
