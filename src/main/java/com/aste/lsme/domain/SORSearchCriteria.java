package com.aste.lsme.domain;

public class SORSearchCriteria {
	
	
	
	Long assetID;
	
	String equipmentType;


	String equipmentName;
	
    String itemCode;
	
	String unit;

	String rate;
	
	Workspace workspace;

	
	public Long getAssetID() {
		return assetID;
	}
	
	public void setAssetID(Long assetID) {
		this.assetID = assetID;
	}
    

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	@Override
	public String toString() {
		return "SORSearchCriteria [assetID=" + assetID + ", equipmentType=" + equipmentType + ", equipmentName="
				+ equipmentName + ", itemCode=" + itemCode + ", unit=" + unit + ", rate=" + rate + ", workspace="
				+ workspace + "]";
	}

	
	
	
	
	

}
