package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Part")
public class Part {

	
	@Id
	@Column(name="PART_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@NotNull(message = "Select Part_Type")
	@JoinColumn(name = "Part_Name")
	@ManyToOne(fetch=FetchType.EAGER)
	private PartName partType;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 200)
	@NotEmpty(message = "Enter Part Description")
	@Column(name = "Part_Description")
	private String description;
	
	
	@NotNull(message = "Select Manufacturer")
	@JoinColumn(name = "Manufacturer")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	private Manufacturer manufacturer;
	
	@NotNull(message = "Select Vendor")
	@JoinColumn(name = "vendor")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	private Vendor vendor;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@NotNull(message = "Select Unit Of Measure")
	@ManyToOne
	private UnitOfMeasure unitOfMeasure;
	
	@Column(name = "Reorder_Level")
	private double reorderLevel;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public PartName getPartType() {
		return partType;
	}


	public void setPartType(PartName partType) {
		this.partType = partType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Manufacturer getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}


	public Vendor getVendor() {
		return vendor;
	}


	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}


	

	
	
	
	public double getReorderLevel() {
		return reorderLevel;
	}


	public void setReorderLevel(double reorderLevel) {
		this.reorderLevel = reorderLevel;
	}


	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}


	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
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
		Part other = (Part) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	
	
	
	
}
