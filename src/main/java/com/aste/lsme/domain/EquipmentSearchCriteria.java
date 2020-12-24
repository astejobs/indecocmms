package com.aste.lsme.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class EquipmentSearchCriteria {
	
	
	 String equipmentType;
	
	String equipmentCode;

	String name;
	
	String assetName;
	
	AssetSubtype assetSubtype;
	
	AssetType assetType;
	
	Building building;
	
	Location location;
	
	String available="all";
	
	String  servingArea;
	
	String modelNo;
	Long id;
	
	
    
    Workspace workspace;
    
  
	Double lifeSpan;

	Integer fromAge;
	Integer toAge;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date fromDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date toDate;
    
    boolean checkSchedule;;

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public AssetSubtype getAssetSubtype() {
		return assetSubtype;
	}

	public void setAssetSubtype(AssetSubtype assetSubtype) {
		this.assetSubtype = assetSubtype;
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

	public String getServingArea() {
		return servingArea;
	}

	public void setServingArea(String servingArea) {
		this.servingArea = servingArea;
	}



	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	
	
	
	
	public Double getLifeSpan() {
		return lifeSpan;
	}

	public void setLifeSpan(Double lifeSpan) {
		this.lifeSpan = lifeSpan;
	}



	public Integer getFromAge() {
		return fromAge;
	}

	public void setFromAge(Integer fromAge) {
		this.fromAge = fromAge;
	}

	public Integer getToAge() {
		return toAge;
	}

	public void setToAge(Integer toAge) {
		this.toAge = toAge;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	
	
	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}


	
	
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}


	
	
	
	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public boolean isCheckSchedule() {
		return checkSchedule;
	}
	
	

	public void setCheckSchedule(boolean checkSchedule) {
		this.checkSchedule = checkSchedule;
	}

	
	
	
	
	

	@Override
	public String toString() {
		return "EquipmentSearchCriteria [equipmentType=" + equipmentType + ", equipmentCode=" + equipmentCode
				+ ", name=" + name + ", assetName=" + assetName + ", assetSubtype=" + assetSubtype + ", assetType="
				+ assetType + ", building=" + building + ", location=" + location + ", available=" + available
				+ ", servingArea=" + servingArea + ", workspace=" + workspace + ", lifeSpan=" + lifeSpan + ", fromAge="
				+ fromAge + ", toAge=" + toAge + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}
	
	
	

	

	



	
    
    
    
    
	
}
