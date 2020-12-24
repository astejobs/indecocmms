package com.aste.lsme.webservicesDtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.GeoLocation;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.utils.Encryptor;

public class FaultReportDto implements Serializable {
	/**	 * 
	 */
	private static final long serialVersionUID = -6124092871417575487L;
	
	private String frId;
	private String clientFrId;
	private String customerRefId;
	private String requestorName;
	private String requestorContactNo;

	private Location location;

	private Building building;
	
	private Division division;

	private String locationDesc;

	private FaultCategory faultCategory;
	
	private Priorty priority;
	
	private Department department;

	private MaintainenceGroup maintGrp;
	
	private List<String> remarks;

	private String status;

    private String faultCategoryDesc;
    
    String locationName;
    String buildingName;
    
    String cost;
    
    boolean editable;	
    String quotationStatus;
	
		
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

	public String getFaultCategoryDesc() {
		return faultCategoryDesc;
	}

	public void setFaultCategoryDesc(String faultCategoryDesc) {
		this.faultCategoryDesc = faultCategoryDesc;
	}

	private EquipmentDTO equipment;
	
	private String observation;
	
	private String actionTaken;

	private CostCenter costCenter;
	
	private String labourHrs;
	
	private List<UserDTO> attendedBy=new ArrayList<UserDTO>();
	
	private List<String> images; 	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime activationTime;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime arrivalTime;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime restartTime;

	private boolean locationScanned;
	
	private String responseTime;
	private String downTime;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime pauseTime;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime completionTime;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime acknowledgementTime;
	
	
	public FaultReportDto() {
		super();
	}
	
	public FaultReportDto( List<String> images) {
		super();
		this.images=images;
		
	}
	
	public FaultReportDto(String frId) {
		super();
		this.frId = frId;
	}
	/*
	 * {"building":{"id":2},"department":{"id":3},"division":{"id":6},
	 * "equipment":{"id":1},"faultCategory":{"id":6},"faultCategoryName":"Vic",
	 * "location":{"id":3},"locationDesc":"McCoy",
	 * "maintGrp":{"id":6},"priority":{"id":6},"remarks":["vision"],
	 * "requestorContactNo":"868985686-","requestorName":"chai"}
	 */
	
	public FaultReportDto(String frId, String requestorName,Building building,Location location, String requestorContactNo, 
			Priorty priority,FaultCategory faultCategory,
			 MaintainenceGroup maintGrp,String actionTaken,String observation,String labourHrs,
			  String status,Department department, String faultCategoryDesc,String quotationStatus
			 ) {
		super();
		this.frId = frId;
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		this.priority = priority;
		this.maintGrp = maintGrp;
		this.status = status;
		this.faultCategory = faultCategory;
		this.observation = observation;
		this.actionTaken = actionTaken;
		this.department = department;
		this.labourHrs = labourHrs;
		this.faultCategoryDesc = faultCategoryDesc;
		this.quotationStatus=quotationStatus;

	}
	
	
	public FaultReportDto(String frId, String requestorName,Building building,Location location, String requestorContactNo, 
			Priorty priority,FaultCategory faultCategory,
			 MaintainenceGroup maintGrp,String actionTaken,String observation,String labourHrs,
			  String status,Department department, String faultCategoryDesc,String locationDesc,String quotationStatus
			  ) {
		super();
		this.frId = frId;
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		//this.division = division;	
		this.priority = priority;
		this.maintGrp = maintGrp;
		this.status = status;
		this.faultCategory = faultCategory;
		//this.equipment = equipment;
		this.observation = observation;
		this.actionTaken = actionTaken;
		//5this.remarks = remarks;
		this.department = department;
		this.labourHrs = labourHrs;
		this.faultCategoryDesc = faultCategoryDesc;
		this.locationDesc=locationDesc;
		this.quotationStatus=quotationStatus;

	}
	

	
	public FaultReportDto(String frId, String requestorName,Building building,Location location, String requestorContactNo, 
			Priorty priority,FaultCategory faultCategory,
			 MaintainenceGroup maintGrp,String actionTaken,String observation,String labourHrs,
			  String status,Department department, String faultCategoryDesc,Division division,String quotationStatus
			  ) {
		super();
		this.frId = frId;
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		this.division = division;	
		this.priority = priority;
		this.maintGrp = maintGrp;
		this.status = status;
		this.faultCategory = faultCategory;
		this.observation = observation;
		this.actionTaken = actionTaken;
		//5this.remarks = remarks;
		this.department = department;
		this.labourHrs = labourHrs;
		this.faultCategoryDesc = faultCategoryDesc;
		this.quotationStatus=quotationStatus;

	}


	public FaultReportDto(String frId, String requestorName, String requestorContactNo, Location location,
			Building building, Division division, String locationDesc, FaultCategory faultCategory,
			String faultCategoryName, Priorty priority, Department department, MaintainenceGroup maintGrp,
			List<String> remarks, String status, EquipmentDTO equipment, LocalDateTime activationTime,String quotationStatus) {
		super();
		this.frId = frId;
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		this.division = division;
		this.locationDesc = locationDesc;
		this.faultCategory = faultCategory;
		//this.faultCategoryName = faultCategoryName;
		this.priority = priority;
		this.department = department;
		this.maintGrp = maintGrp;
		this.remarks = remarks;
		this.status = status;
		this.equipment = equipment;
		this.activationTime = activationTime;
		this.quotationStatus=quotationStatus;
	}
		
	/*For Edit/View FaultReport*------------*/
	//getFaultDtoEdit();

	public FaultReportDto(String frId, String requestorName,Building building,Location location, String requestorContactNo, 
			Priorty priority,FaultCategory faultCategory,
			 MaintainenceGroup maintGrp,String actionTaken,String observation,String labourHrs
			 ,String status,Division division,Department department,LocalDateTime activationTime,LocalDateTime arrivalTime
			 	,LocalDateTime restartTime,String faultCategoryDesc,String locationDesc,String quotationStatus,boolean locationScanned,String responseTime,LocalDateTime pauseTime,LocalDateTime completionTime,LocalDateTime acknowledgementTime,String downTime) {
		super();
		this.frId = frId;
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		this.priority = priority;
		this.maintGrp = maintGrp;
		this.status = status;
		this.faultCategory = faultCategory;
		this.observation = observation;
		this.actionTaken = actionTaken;
		this.department = department;
		this.labourHrs = labourHrs;
		this.activationTime=activationTime;
		this.arrivalTime=arrivalTime;
		this.restartTime=restartTime;
		this.division=division;
		this.faultCategoryDesc=faultCategoryDesc;
		this.locationDesc=locationDesc;
		this.quotationStatus=quotationStatus;
		this.locationScanned = locationScanned;
		
		this.responseTime=responseTime;
		this.pauseTime=pauseTime;
		this.completionTime=completionTime;
		this.acknowledgementTime=acknowledgementTime;
		this.downTime=downTime;
	}
	
	
	/*For Scan Equipment FaultReport*------------*/
//getFaultDtoByEquipmentCode
	//
	public FaultReportDto(String frId, String requestorName,Building building,Location location, String requestorContactNo, 
			Priorty priority,FaultCategory faultCategory,
			 MaintainenceGroup maintGrp,String actionTaken,String observation,String labourHrs
			 ,String status,Division division,Department department,Long equipmentId,String equipmentName,LocalDateTime activationTime,LocalDateTime arrivalTime
			 	,LocalDateTime restartTime,String faultCategoryDesc,String locationDesc,GeoLocation geoLocation,String quotationStatus,boolean locationScanned,String responseTime,LocalDateTime pauseTime,LocalDateTime completionTime,LocalDateTime acknowledgementTime,String downTime) {
		super();
		this.frId = frId;
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		this.priority = priority;
		this.maintGrp = maintGrp;
		this.status = status;
		this.faultCategory = faultCategory;
		this.equipment = new EquipmentDTO(equipmentName, equipmentId);
		this.equipment.setGeoLocation(geoLocation);
		this.observation = observation;
		this.actionTaken = actionTaken;
		this.department = department;
		this.labourHrs = labourHrs;
		this.activationTime=activationTime;
		this.arrivalTime=arrivalTime;
		this.restartTime=restartTime;
		this.division=division;
		this.faultCategoryDesc=faultCategoryDesc;
		this.locationDesc=locationDesc;
		this.quotationStatus=quotationStatus;
		this.locationScanned=locationScanned;
		
		this.responseTime=responseTime;
		this.pauseTime=pauseTime;
		this.completionTime=completionTime;
		this.acknowledgementTime=acknowledgementTime;
		this.downTime=downTime;
	}

	

	public FaultReportDto(String frId, String requestorName, String requestorContactNo,
			Location location, Building building, Division division, Priorty priority,
			FaultCategory faultCategory, MaintainenceGroup maintGrp,String quotationStatus) {
		super();
		this.frId = frId; 
		this.requestorName = requestorName;
		this.requestorContactNo = requestorContactNo;
		this.location = location;
		this.building = building;
		this.division = division;
		this.priority = priority;
		this.faultCategory = faultCategory;
		this.maintGrp = maintGrp;	
		this.quotationStatus=quotationStatus;
	} 


	   /*----for Search----------*/
	public FaultReportDto(String frId, String requestorName, String faultCategoryName, String status,LocalDateTime activationTime,String locationName,String buildingName,String quotationStatus) {
		super();
		this.frId = frId;
		this.activationTime=activationTime;
		this.requestorName = requestorName;
		this.buildingName=buildingName;
		this.locationName=locationName;
		//this.faultCategoryName = faultCategoryName;
		this.status = status;
		this.quotationStatus=quotationStatus;
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

	public String getCustomerRefId() {
		return customerRefId;
	}

	public void setCustomerRefId(String customerRefId) {
		this.customerRefId = customerRefId;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}
	
	public String getRequestorContactNo() {
		return requestorContactNo;
	}

	public void setRequestorContactNo(String requestorContactNo) {
		this.requestorContactNo = requestorContactNo;
	}

	public String getObservation() {
		return observation;
	}


	public void setObservation(String observation) {
		this.observation = observation;
	}


	public String getActionTaken() {
		return actionTaken;
	}


	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public List<String> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}


	public String getLabourHrs() {
		return labourHrs;
	}

	public void setLabourHrs(String labourHrs) {
		this.labourHrs = labourHrs;
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

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}


	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public FaultCategory getFaultCategory() {
		return faultCategory;
	}

	public void setFaultCategory(FaultCategory faultCategory) {
		this.faultCategory = faultCategory;
	}


	public Priorty getPriority() {
		return priority;
	}

	public void setPriority(Priorty priority) {
		this.priority = priority;
	}

	public LocalDateTime getRestartTime() {
		return restartTime;
	}

	public void setRestartTime(LocalDateTime restartTime) {
		this.restartTime = restartTime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public MaintainenceGroup getMaintGrp() {
		return maintGrp;
	}

	public void setMaintGrp(MaintainenceGroup maintGrp) {
		this.maintGrp = maintGrp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EquipmentDTO getEquipment() {
		return equipment;
	}

	public void setEquipment(EquipmentDTO equipment) {
		this.equipment = equipment;
	}

	public CostCenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}


	public List<UserDTO> getAttendedBy() {
		return attendedBy;
	}

	public void setAttendedBy(List<UserDTO> attendedBy) {
		this.attendedBy = attendedBy;
	}

	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}
	
	public LocalDateTime getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(LocalDateTime activationTime) {
		this.activationTime = activationTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
	

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
	

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	
	
	public String getQuotationStatus() {
		return quotationStatus;
	}

	public void setQuotationStatus(String quotationStatus) {
		this.quotationStatus = quotationStatus;
	}
	
	public boolean getLocationScanned() {
		return locationScanned;
	}

	public void setLocationScanned(boolean locationScanned) {
		this.locationScanned = locationScanned;
	}
	
	
	

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public LocalDateTime getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(LocalDateTime pauseTime) {
		this.pauseTime = pauseTime;
	}

	public LocalDateTime getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(LocalDateTime completionTime) {
		this.completionTime = completionTime;
	}

	public LocalDateTime getAcknowledgementTime() {
		return acknowledgementTime;
	}

	public void setAcknowledgementTime(LocalDateTime acknowledgementTime) {
		this.acknowledgementTime = acknowledgementTime;
	}

	@Override
	public String toString() {
		return "FaultReportDto [frId=" + frId + ", clientFrId=" + clientFrId + ", customerRefId=" + customerRefId
				+ ", requestorName=" + requestorName + ", requestorContactNo=" + requestorContactNo + ", location="
				+ location + ", building=" + building + ", division=" + division + ", locationDesc=" + locationDesc
				+ ", faultCategory=" + faultCategory + ", priority=" + priority + ", department=" + department
				+ ", maintGrp=" + maintGrp + ", remarks=" + remarks + ", status=" + status + ", faultCategoryDesc="
				+ faultCategoryDesc + ", equipment=" + equipment + ", observation=" + observation + ", actionTaken="
				+ actionTaken + ", costCenter=" + costCenter + ", labourHrs=" + labourHrs + ", attendedBy=" + attendedBy
				+ ", images=" + images + ", activationTime=" + activationTime + ", arrivalTime=" + arrivalTime
				+ ", restartTime=" + restartTime + "]";
	}

	public static FaultReportDto getFaultDtoEdit(String frId2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
