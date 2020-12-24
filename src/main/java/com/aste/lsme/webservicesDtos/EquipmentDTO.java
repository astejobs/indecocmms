package com.aste.lsme.webservicesDtos;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.GeoLocation;
import com.aste.lsme.domain.Location;
import com.aste.lsme.utils.Encryptor;

public class EquipmentDTO {

	String equipmentCode;
	String assetType;
	String assetSubType;
	String name;
	Location location;
	String locationName;
	String buildingName;
	Building building;
	String assetNo;
	Long id;
	GeoLocation geoLocation;

	
	
	public String getLocationName() {
		return Encryptor.decrypt(locationName);
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getBuildingName() {
		return Encryptor.decrypt(buildingName);
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetSubType() {
		return assetSubType;
	}

	public void setAssetSubType(String assetSubType) {
		this.assetSubType = assetSubType;
	}

	public String getName() {
		//return name;
		return Encryptor.decrypt(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public EquipmentDTO(String equipmentCode, String assetType, String name, Location location, Building building) 
	{
		super();
		this.equipmentCode = equipmentCode;
		this.assetType = assetType;
		this.name = name;
		this.location = location;
		this.building = building;
		
	}
	

	public EquipmentDTO(String equipmentCode, String assetType, String name, String locationName, String buildingName,
			Long id) {
		super();
		this.equipmentCode = equipmentCode;
		this.assetType = assetType;
		this.name = name;
		this.locationName = locationName;
		this.buildingName = buildingName;
		this.id = id;
	}

	public EquipmentDTO(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public EquipmentDTO() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}

	public EquipmentDTO(String equipmentCode, String assetType, String assetSubType, String name, Location location,
			String locationName, String buildingName, Building building, String assetNo, Long id) {
		super();
		this.equipmentCode = equipmentCode;
		this.assetType = assetType;
		this.assetSubType = assetSubType;
		this.name = name;
		this.location = location;
		this.locationName = locationName;
		this.buildingName = buildingName;
		this.building = building;
		this.assetNo = assetNo;
		this.id = id;
	}

	public EquipmentDTO(String equipmentCode, String assetType, String assetSubType, String name, String locationName,
			String buildingName, Long id) {
		super();
		this.equipmentCode = equipmentCode;
		this.assetType = assetType;
		this.assetSubType = assetSubType;
		this.name = name;
		this.locationName = locationName;
		this.buildingName = buildingName;
		this.id = id;
	}

	
}
