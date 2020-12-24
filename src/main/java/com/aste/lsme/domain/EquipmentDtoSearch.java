package com.aste.lsme.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.aste.lsme.utils.Encryptor;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EquipmentDtoSearch {
	Long id;
	
	
	String name;
	AssetSubtype assetSubtype;
	
	AssetType assetType;
	
	Building building;
	
	Location location;
	
	String modelNo;
	
	String remarks;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date monthOfExpiry;
	
	String fcuModel;
	
	Double quantity;
	String equipmentCode;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date reCertificatedOn;
	String serialNo;
	
	
	
	
	

	
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return Encryptor.decrypt(name);
	}

	public void setName(String name) {	
		
		this.name =name;
	}
	
	public EquipmentDtoSearch(Long id,AssetType assetType,AssetSubtype assetSubtype,String name, Building building,
			Location location, String modelNo) {
		super();
		this.id=id;
		this.name = name;
		this.assetSubtype = assetSubtype;
		this.assetType = assetType;
		this.building = building;
		this.location = location;
		this.modelNo = modelNo;
	}
	
	public EquipmentDtoSearch(Long id,AssetType assetType,AssetSubtype assetSubtype,String name, Building building,
			Location location, String modelNo,String remarks,Date monthOfExpiry,String fcuModel, Double quantity,
			String equipmentCode, Date reCertificatedOn, String serialNo) {
		super();
		this.id=id;
		this.name = name;
		this.assetSubtype = assetSubtype;
		this.assetType = assetType;
		this.building = building;
		this.location = location;
		this.modelNo = modelNo;
		this.remarks = remarks;
		this.monthOfExpiry = monthOfExpiry;
		this.fcuModel = fcuModel;
		this.quantity = quantity;
		this.equipmentCode = equipmentCode;
		this.reCertificatedOn = reCertificatedOn;
		this.serialNo = serialNo;
	}
	

	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getMonthOfExpiry() {
		return monthOfExpiry;
	}
	public void setMonthOfExpiry(Date monthOfExpiry) {
		this.monthOfExpiry = monthOfExpiry;
	}
	public String getFcuModel() {
		return fcuModel;
	}
	public void setFcuModel(String fcuModel) {
		this.fcuModel = fcuModel;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public Date getReCertificatedOn() {
		return reCertificatedOn;
	}
	public void setReCertificatedOn(Date reCertificatedOn) {
		this.reCertificatedOn = reCertificatedOn;
	}
	public EquipmentDtoSearch() {
		super();
	}
	public AssetSubtype getAssetSubtype() {
		return assetSubtype;
	}
	public void setAssetSubtype(AssetSubtype assetSubtype) {
		this.assetSubtype = assetSubtype;
	}
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	
	
}