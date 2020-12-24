package com.aste.lsme.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Fault_Report")
public class FaultReport {

		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name = "FR_ID",unique=true)
	private String frId;

	@Pattern(regexp = "^|[\\s\\S]*$", message = "Enter valid client id")
	@Column(name = "Client_Fr_ID")
	private String clientFrId;

	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "Enter valid client id")
	@Column(name = "Customer_Ref_ID")
	private String customerRefId;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "WORKSPACE")
	Workspace workspace;

	@Length(max = 50, message = "Name should be less than 50 chars")
	@NotEmpty(message="Enter Requestor Name")
	@Column(name = "Requestor_Name", length = 50)
	private String requestorName;

	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select any Department")
	@ManyToOne
	@JoinColumn(name = "Department_ID")
	private Department department;

	@Pattern(regexp = "^\\d{8,12}$", message = "Please Enter the 8 to 10 digits correctly")
	@NotEmpty(message="Enter Contact Number")
	@Column(name = "Requestor_Contact_NO")
	private String requestorContactNo;

	

	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select any Location")
	@ManyToOne
	@JoinColumn(name = "Location_ID")
	private Location location;

	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select any Building")
	@ManyToOne
	@JoinColumn(name = "Building_ID")
	private Building building;

	@Column(name = "Location_Description", length = 100)
	private String locationDesc;

	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select any Fault Category")
	@ManyToOne
	@JoinColumn(name = "FAULT_CATEGORY_ID")
	private FaultCategory faultCategory;

	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select any Priority Id")
	@ManyToOne
	@JoinColumn(name = "Priority_ID")
	private Priorty priority;

	@Length(max = 500, message = "Fault Category Description should be not more than 250 chars")
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "Enter valid description")
	@Column(name = "Fault_Category_Description", length = 250)
	@NotEmpty(message="Enter Fault Description")
	private String faultCategoryDesc;

	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select any Maintenance Group")
	@ManyToOne
	@JoinColumn(name = "MaintenanceGroup_ID")
	private MaintainenceGroup maintGrp;

	@Column(name = "Created_By", length = 500)
	private String createdBy;

	@Column(name = "Status", length = 20)
	private String status;

	@Column(name = "Total_Part_Cost")
	private double totalPartCost;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "Equipment_ID")
	private Equipment equipment;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "Cost_Center_ID")
	CostCenter costCenter;

	@Length(max = 200, message = "Observation should be not more than 200 chars")
	@Column(name = "Observation")
	private String observation;

	@Column(name = "Diagnosis", length = 100)
	private String diagnosis;

	@Length(max = 500, message = "Action Taken should be not more than 250 chars")
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "Enter valid action")
	@Column(name = "Action_Taken", length = 250)
	private String actionTaken;

	@Length(max = 500, message = "Fault Detail should not be more than 500 chars")
	@Column(name = "Fault_Detail", length = 500)
	private String faultDetail;
	
	@Column(name = "Labour_Hours", length = 100)
	private String labourHrs;

	@Column(name = "Other_Cost", length = 10)
	private String otherCost;

	@Column(name = "InProgress_By", length = 50)
	private String inProgressBy;



	@Column(name = "Reason_For_Outstanding")
	private String reasonForOutstanding;

	
	@JsonIgnore
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@NotEmpty(message="Select Technician(s) ")
	@JoinTable(name="FaultReport_Technician",
	joinColumns=@JoinColumn(name="FaultReport_ID"),
	inverseJoinColumns=@JoinColumn(name="Technician_ID"))
	private List<UserDetail> attendedBy;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	@CollectionTable(name="FaultReport_Remarks")
	private List<String> remarks;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	@CollectionTable(name="FaultReport_BeforeImages")
	private List<String> beforeImage; 

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	@CollectionTable(name="FaultReport_AfterImages")
	private List<String> afterImage; 

	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = "Select any Division ")
	@JoinColumn(name = "Division")
	Division division;
	
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(name="activationTime")
	LocalDateTime activationTime;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(name="arrivalTime")
	LocalDateTime arrivalTime;
	
	@Column(name="Response_Time")
	String responseTime;
	
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @Column(name="pauseTime")
	LocalDateTime pauseTime;
    
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @Column(name="restartTime")
	LocalDateTime restartTime;
   
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @Column(name="completionTime")
	LocalDateTime completionTime;
    
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @Column(name="acknowledgementTime")
	LocalDateTime acknowledgementTime;
    
    @Column(name="Down_Time")
	String downTime;
	
    @Column(name="Quotation")
	String quotation;
    
    @Column(name="Purchase_Order")
    String purchaseOrder;
    
    
    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
    @Column(name="delivery_Date")
    LocalDateTime deliveryDate;
    
    
    @Column(name="quotation_status")
    String quotationStatus;
    
    @Column(name="Location_Scanned")
    boolean locationScanned;
	
	public FaultReport() {
		
		this.beforeImage = new ArrayList<String>();
		this.afterImage = new ArrayList<String>();
		this.remarks = new ArrayList<String>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getRequestorContactNo() {
		return requestorContactNo;
	}

	public void setRequestorContactNo(String requestorContactNo) {
		this.requestorContactNo = requestorContactNo;
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

	public String getLocationDesc() {
		return locationDesc;
	}
	
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	
	public Priorty getPriority() {
		return priority;
	}

	public void setPriority(Priorty priority) {
		this.priority = priority;
	}

	public String getFaultCategoryDesc() {
		return faultCategoryDesc;
	}
	
	public void setFaultCategoryDesc(String faultCategoryDesc) {
		this.faultCategoryDesc = faultCategoryDesc;
	}
	
	public MaintainenceGroup getMaintGrp() {
		return maintGrp;
	}

	public void setMaintGrp(MaintainenceGroup maintGrp) {
		this.maintGrp = maintGrp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	 public double getTotalPartCost() {
		return totalPartCost;
	}
	 
	public void setTotalPartCost(double totalPartCost) {
		this.totalPartCost = totalPartCost;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public CostCenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}
	
	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getFaultDetail() {
		return faultDetail;
	}

	public void setFaultDetail(String faultDetail) {
		this.faultDetail = faultDetail;
	}

	


	public String getLabourHrs() {
		return labourHrs;
	}

	public void setLabourHrs(String labourHrs) {
		this.labourHrs = labourHrs;
	}

	public String getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(String otherCost) {
		this.otherCost = otherCost;
	}

	public String getInProgressBy() {
		return inProgressBy;
	}

	public void setInProgressBy(String inProgressBy) {
		this.inProgressBy = inProgressBy;
	}

	
	public String getReasonForOutstanding() {
		return reasonForOutstanding;
	}

	public void setReasonForOutstanding(String reasonForOutstanding) {
		this.reasonForOutstanding = reasonForOutstanding;
	}

	public List<UserDetail> getAttendedBy() {
		return attendedBy;
	}

	public void setAttendedBy(List<UserDetail> attendedBy) {
		this.attendedBy = attendedBy;
	}

	public List<String> getBeforeImage() {
		return beforeImage;
	}

	public void setBeforeImage(List<String> beforeImage) {
		this.beforeImage = beforeImage;
	}

	public List<String> getAfterImage() {
		return afterImage;
	}

	public void setAfterImage(List<String> afterImage) {
		this.afterImage = afterImage;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
	
	 public FaultCategory getFaultCategory() {
		return faultCategory;
	}
	 public void setFaultCategory(FaultCategory faultCategory) {
		this.faultCategory = faultCategory;
	}
	 
	 public List<String> getRemarks() {
		return remarks;
	}
	 
	 public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
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



	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public LocalDateTime getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(LocalDateTime pauseTime) {
		this.pauseTime = pauseTime;
	}

	public LocalDateTime getRestartTime() {
		return restartTime;
	}

	public void setRestartTime(LocalDateTime restartTime) {
		this.restartTime = restartTime;
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

	
	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}
	
    String getQuotation() {
		return quotation;
	}

	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}
	
	
	

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	
	
	
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
	
	

	public String getQuotationStatus() {
		return quotationStatus;
	}

	public void setQuotationStatus(String quotationStatus) {
		this.quotationStatus = quotationStatus;
	}

	public boolean isLocationScanned() {
		return locationScanned;
	}

	public void setLocationScanned(boolean locationScanned) {
		this.locationScanned = locationScanned;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acknowledgementTime == null) ? 0 : acknowledgementTime.hashCode());
		result = prime * result + ((actionTaken == null) ? 0 : actionTaken.hashCode());
		result = prime * result + ((activationTime == null) ? 0 : activationTime.hashCode());
		result = prime * result + ((afterImage == null) ? 0 : afterImage.hashCode());
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((attendedBy == null) ? 0 : attendedBy.hashCode());
		result = prime * result + ((beforeImage == null) ? 0 : beforeImage.hashCode());
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + ((clientFrId == null) ? 0 : clientFrId.hashCode());
		result = prime * result + ((completionTime == null) ? 0 : completionTime.hashCode());
		result = prime * result + ((costCenter == null) ? 0 : costCenter.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((customerRefId == null) ? 0 : customerRefId.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((diagnosis == null) ? 0 : diagnosis.hashCode());
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((downTime == null) ? 0 : downTime.hashCode());
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + ((faultCategory == null) ? 0 : faultCategory.hashCode());
		result = prime * result + ((faultCategoryDesc == null) ? 0 : faultCategoryDesc.hashCode());
		result = prime * result + ((faultDetail == null) ? 0 : faultDetail.hashCode());
		result = prime * result + ((frId == null) ? 0 : frId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inProgressBy == null) ? 0 : inProgressBy.hashCode());
		result = prime * result + ((labourHrs == null) ? 0 : labourHrs.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((locationDesc == null) ? 0 : locationDesc.hashCode());
		result = prime * result + ((maintGrp == null) ? 0 : maintGrp.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
		result = prime * result + ((otherCost == null) ? 0 : otherCost.hashCode());
		result = prime * result + ((pauseTime == null) ? 0 : pauseTime.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((reasonForOutstanding == null) ? 0 : reasonForOutstanding.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((requestorContactNo == null) ? 0 : requestorContactNo.hashCode());
		result = prime * result + ((requestorName == null) ? 0 : requestorName.hashCode());
		result = prime * result + ((responseTime == null) ? 0 : responseTime.hashCode());
		result = prime * result + ((restartTime == null) ? 0 : restartTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPartCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((workspace == null) ? 0 : workspace.hashCode());
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
		FaultReport other = (FaultReport) obj;
		if (acknowledgementTime == null) {
			if (other.acknowledgementTime != null)
				return false;
		} else if (!acknowledgementTime.equals(other.acknowledgementTime))
			return false;
		if (actionTaken == null) {
			if (other.actionTaken != null)
				return false;
		} else if (!actionTaken.equals(other.actionTaken))
			return false;
		if (activationTime == null) {
			if (other.activationTime != null)
				return false;
		} else if (!activationTime.equals(other.activationTime))
			return false;
		if (afterImage == null) {
			if (other.afterImage != null)
				return false;
		} else if (!afterImage.equals(other.afterImage))
			return false;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (attendedBy == null) {
			if (other.attendedBy != null)
				return false;
		} else if (!attendedBy.equals(other.attendedBy))
			return false;
		if (beforeImage == null) {
			if (other.beforeImage != null)
				return false;
		} else if (!beforeImage.equals(other.beforeImage))
			return false;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (clientFrId == null) {
			if (other.clientFrId != null)
				return false;
		} else if (!clientFrId.equals(other.clientFrId))
			return false;
		if (completionTime == null) {
			if (other.completionTime != null)
				return false;
		} else if (!completionTime.equals(other.completionTime))
			return false;
		if (costCenter == null) {
			if (other.costCenter != null)
				return false;
		} else if (!costCenter.equals(other.costCenter))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (customerRefId == null) {
			if (other.customerRefId != null)
				return false;
		} else if (!customerRefId.equals(other.customerRefId))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (diagnosis == null) {
			if (other.diagnosis != null)
				return false;
		} else if (!diagnosis.equals(other.diagnosis))
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (downTime == null) {
			if (other.downTime != null)
				return false;
		} else if (!downTime.equals(other.downTime))
			return false;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (faultCategory == null) {
			if (other.faultCategory != null)
				return false;
		} else if (!faultCategory.equals(other.faultCategory))
			return false;
		if (faultCategoryDesc == null) {
			if (other.faultCategoryDesc != null)
				return false;
		} else if (!faultCategoryDesc.equals(other.faultCategoryDesc))
			return false;
		if (faultDetail == null) {
			if (other.faultDetail != null)
				return false;
		} else if (!faultDetail.equals(other.faultDetail))
			return false;
		if (frId == null) {
			if (other.frId != null)
				return false;
		} else if (!frId.equals(other.frId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inProgressBy == null) {
			if (other.inProgressBy != null)
				return false;
		} else if (!inProgressBy.equals(other.inProgressBy))
			return false;
		if (labourHrs == null) {
			if (other.labourHrs != null)
				return false;
		} else if (!labourHrs.equals(other.labourHrs))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (locationDesc == null) {
			if (other.locationDesc != null)
				return false;
		} else if (!locationDesc.equals(other.locationDesc))
			return false;
		if (maintGrp == null) {
			if (other.maintGrp != null)
				return false;
		} else if (!maintGrp.equals(other.maintGrp))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		if (otherCost == null) {
			if (other.otherCost != null)
				return false;
		} else if (!otherCost.equals(other.otherCost))
			return false;
		if (pauseTime == null) {
			if (other.pauseTime != null)
				return false;
		} else if (!pauseTime.equals(other.pauseTime))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (reasonForOutstanding == null) {
			if (other.reasonForOutstanding != null)
				return false;
		} else if (!reasonForOutstanding.equals(other.reasonForOutstanding))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (requestorContactNo == null) {
			if (other.requestorContactNo != null)
				return false;
		} else if (!requestorContactNo.equals(other.requestorContactNo))
			return false;
		if (requestorName == null) {
			if (other.requestorName != null)
				return false;
		} else if (!requestorName.equals(other.requestorName))
			return false;
		if (responseTime == null) {
			if (other.responseTime != null)
				return false;
		} else if (!responseTime.equals(other.responseTime))
			return false;
		if (restartTime == null) {
			if (other.restartTime != null)
				return false;
		} else if (!restartTime.equals(other.restartTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(totalPartCost) != Double.doubleToLongBits(other.totalPartCost))
			return false;
		if (workspace == null) {
			if (other.workspace != null)
				return false;
		} else if (!workspace.equals(other.workspace))
			return false;
		return true;
	}

	

	
	 
}