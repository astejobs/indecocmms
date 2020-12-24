package com.aste.lsme.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FaultReportSearch {

	String frId;
	
	String clientFrId;
	
	String requestorName;
	
	String [] status;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date fromDate;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date toDate;
	
	Long building;
	
	Long location;
	
	Long faultCategory;

	Long maintGrp;
	
	Long equipment;
	
	String groupBy;
	
	Workspace workspace;
	
	GeoLocation geoLocation;
	
	String equipmentCode;
	
	String locationCode;
	
	public FaultReportSearch() {
		
		
	}
	public String getFrId() {
		return frId;
	}

	public void setFrId(String frId) {
		this.frId = frId;
	}

	public String getClientFrId() {
		return clientFrId;
	}
	
	public void setClientFrId(String clientFrId) {
		this.clientFrId = clientFrId;
	}
	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		if(status.length == 0){
			status = new String[]{"open","closed","kiv","inprogress"};
		}
		this.status = status;
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

	public Long getBuilding() {
		return building;
	}

	public void setBuilding(Long building) {
		this.building = building;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public Long getFaultCategory() {
		return faultCategory;
	}

	public void setFaultCategory(Long faultCategory) {
		this.faultCategory = faultCategory;
	}
	
	public Workspace getWorkspace() {
		return workspace;
	}
	
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	public String getRequestorName() {
		return requestorName;
	}
	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}
	public Long getMaintGrp() {
		return maintGrp;
	}
	public void setMaintGrp(Long maintGrp) {
		this.maintGrp = maintGrp;
	}
	public Long getEquipment() {
		return equipment;
	}
	public void setEquipment(Long equipment) {
		this.equipment = equipment;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	
}
