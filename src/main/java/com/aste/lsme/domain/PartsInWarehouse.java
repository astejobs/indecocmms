package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PartsInWarehouse {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	
	@ManyToOne
	Part part;
	
	@Column(name="Total_Part_Quantity")
	double totalPartQuantity;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)	
	@OneToMany(mappedBy="partsInWarehouse",cascade=CascadeType.ALL)
	List<PartBatch> partBatchs;
	
	@ManyToOne
	Warehouse warehouse;

	public PartsInWarehouse() {
		super();
		this.partBatchs = new ArrayList<PartBatch>();
	}

	
	
	public PartsInWarehouse(long id, Part part, double totalPartQuantity, List<PartBatch> partBatchs,
			Warehouse warehouse) {
		super();
		this.id = id;
		this.part = part;
		this.totalPartQuantity = totalPartQuantity;
		this.partBatchs = partBatchs;
		this.warehouse = warehouse;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public double getTotalPartQuantity() {
		return totalPartQuantity;
	}

	public void setTotalPartQuantity(double totalPartQuantity) {
		this.totalPartQuantity = totalPartQuantity;
	}

	public List<PartBatch> getPartBatchs() {
		return partBatchs;
	}

	public void setPartBatchs(List<PartBatch> partBatchs) {
		this.partBatchs = partBatchs;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	
}
