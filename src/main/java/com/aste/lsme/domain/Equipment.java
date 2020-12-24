package com.aste.lsme.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.aste.lsme.utils.Encryptor;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Equipment")
public class Equipment {
	
		
	    private boolean toStringMethod = false;
	    
		@Id@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "Id")
		Long id;
		
		
		
		@ManyToOne(cascade=CascadeType.ALL)
		@JsonIgnore
		private Schedule schedule;
		


		@JsonIgnore
		@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@NotNull(message = "Asset SubType can not be empty")
		@JoinColumn(name = "subtype")
		AssetSubtype assetSubtype;


		
		@LazyCollection(LazyCollectionOption.FALSE)
	    @ManyToMany(mappedBy = "equipment")
		@JsonIgnore
		private List<ChecklistHeader> checklists;
		
		@JsonIgnore
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "PROJECT_ID")
		Workspace workspace;
		
		@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@NotNull(message = "Location can not be empty")
		@JoinColumn(name = "location")
		Location location;
		
		@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@NotNull(message = "Building can not be empty")
		@JoinColumn(name = "building")
		Building building;
		
		
		@Column(name = "Name")
		@NotEmpty(message="Please enter Equipment name")
		String name;
		
		@Column(name = "Equipment_Code")
		String equipmentCode;

		


		@Column(name = "Remarks")
		String remarks;



		@DateTimeFormat(pattern = "dd-MM-yyyy")
		@Column(name = "Date_of_inspection")
		Date dateOfInspection;

		

		@Column(name = "Model_No")
		String modelNo;

		


		@Column(name = "unit_of_measurement")
		String unitOfMeasurement;

		@Column(name = "contractQty")
		String contractQty;

		@Column(name = "siteQty")
		Double siteQty;



		@JsonIgnore
		@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@NotNull(message = "Asset Type can not be empty")
		@JoinColumn(name = "asset_type")
		AssetType assetType;

		@Column(name = "Serial_No")
		String serialNo;
		
		
		@Column(name = "Capacity")
		String capacity;
		
		
		
		
		
		@Column(name="re_certificated_on")
		@DateTimeFormat(pattern = "dd-MM-yyyy")
		Date reCertificatedOn;
		
		@Column(name="no_of_extinguishers")
		Double noOfExtinguishers;
		
		@Column(name="month_of_expiry")
		@DateTimeFormat(pattern = "dd-MM-yyyy")
		Date monthOfExpiry;
		
		
		@Column(name = "quantity")
		Double quantity;
		
		@Column(name="description")
		String description;
		
		@Column(name="brand")
		String brand;
		
		@Column(name="fcu_model")
		String fcuModel;
		
		/*
		 * @Column(name="qty_fcu") String qtyFcu;
		 */
		
		@Column(name="cu_model")
		String cuModel;
		/*
		 * @Column(name="fcu_qty") String qtyCu;
		 */
		
		@Column(name="rating")
		String rating;
		

		@Column(name="type")
		String type;
		

		@ManyToOne
		GeoLocation geoLocation;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getRating() {
			return rating;
		}

		public void setRating(String rating) {
			this.rating = rating;
		}

		public Date getReCertificatedOn() {
			return reCertificatedOn;
		}

		public void setReCertificatedOn(Date reCertificatedOn) {
			this.reCertificatedOn = reCertificatedOn;
		}

		public Double getNoOfExtinguishers() {
			return noOfExtinguishers;
		}

		public void setNoOfExtinguishers(Double noOfExtinguishers) {
			this.noOfExtinguishers = noOfExtinguishers;
		}

		public Date getMonthOfExpiry() {
			return monthOfExpiry;
		}

		public void setMonthOfExpiry(Date monthOfExpiry) {
			this.monthOfExpiry = monthOfExpiry;
		}

		public Double getQuantity() {
			return quantity;
		}

		public void setQuantity(Double quantity) {
			this.quantity = quantity;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getFcuModel() {
			return fcuModel;
		}

		public void setFcuModel(String fcuModel) {
			this.fcuModel = fcuModel;
		}

		/*
		 * public String getQtyFcu() { return qtyFcu; }
		 * 
		 * public void setQtyFcu(String qtyFcu) { this.qtyFcu = qtyFcu; }
		 */

		public String getCuModel() {
			return cuModel;
		}

		public void setCuModel(String cuModel) {
			this.cuModel = cuModel;
		}

		/*
		 * public String getQtyCu() { return qtyCu; }
		 * 
		 * public void setQtyCu(String qtyCu) { this.qtyCu = qtyCu; }
		 */

		public Equipment(Long id) {
			super();
			this.id = id;
		}

		public Equipment() {
			super();
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
			this.name = Encryptor.encrypt(name);
		}

		

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		

		

		public String getModelNo() {
			return modelNo;
		}

		public void setModelNo(String modelNo) {
			this.modelNo = modelNo;
		}

		

		

		

		public String getEquipmentCode() {
			return equipmentCode;
		}

		public void setEquipmentCode(String equipmentCode) {
			this.equipmentCode = equipmentCode;
		}

		


		public Workspace getWorkspace() {
			return workspace;
		}

		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}

	   


		public List<ChecklistHeader> getChecklists() {
			return checklists;
		}

		public void setChecklists(List<ChecklistHeader> checklists) {
			this.checklists = checklists;
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

		
		
		

		public Schedule getSchedule() {
			return schedule;
		}

		public void setSchedule(Schedule schedule) {
			this.schedule = schedule;
		}
		
		public AssetSubtype getAssetSubtype() {
			return assetSubtype;
		}

		public void setAssetSubtype(AssetSubtype assetSubtype) {
			this.assetSubtype = assetSubtype;
		}

		



		public Date getDateOfInspection() {
			return dateOfInspection;
		}

		public void setDateOfInspection(Date dateOfInspection) {
			this.dateOfInspection = dateOfInspection;
		}

		public String getUnitOfMeasurement() {
			return unitOfMeasurement;
		}

		public void setUnitOfMeasurement(String unitOfMeasurement) {
			this.unitOfMeasurement = unitOfMeasurement;
		}

		
		public String getContractQty() {
			return contractQty;
		}

		public void setContractQty(String contractQty) {
			this.contractQty = contractQty;
		}

		public Double getSiteQty() {
			return siteQty;
		}

		public void setSiteQty(Double siteQty) {
			this.siteQty = siteQty;
		}

		public String getSerialNo() {
			return serialNo;
		}

		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}

		public String getCapacity() {
			return capacity;
		}

		public void setCapacity(String capacity) {
			this.capacity = capacity;
		}
		
		public AssetType getAssetType() {
			return assetType;
		}

		public void setAssetType(AssetType assetType) {
			this.assetType = assetType;
		}

		  public boolean isToStringMethod() { 
			  return toStringMethod; 
			  }
		  
		  public void setToStringMethod(boolean toStringMethod) {
			  this.toStringMethod =toStringMethod;
			  }

		

		public GeoLocation getGeoLocation() {
			return geoLocation;
		}

		public void setGeoLocation(GeoLocation geoLocation) {
			this.geoLocation = geoLocation;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			Equipment other = (Equipment) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		
		
		
	}


