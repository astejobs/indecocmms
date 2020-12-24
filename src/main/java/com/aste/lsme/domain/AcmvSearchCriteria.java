package com.aste.lsme.domain;



public class AcmvSearchCriteria {
	
	
	String equipmentType;
	
	String equipmentCode;

	String name;
	
	AssetSubtype assetSubtype;
	
	Building building;
	
	Location location;
	
	
	String  servingArea;
	
    
    Workspace workspace;
    
    

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
    
    
    
    
	
}
