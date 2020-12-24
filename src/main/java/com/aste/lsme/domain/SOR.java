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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="SOR")
public class SOR {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;	

	@Column(name = "item_code")
	@NotEmpty(message = "itemcode can not be empty")
	String itemCode;

	
	@Column(name = "description")
	String description;
	
	@Column(name = "unit")
	@NotEmpty(message = "Unit can not be empty")
	String unit;

	@Column(name = "rate")
	@NotEmpty(message = "Rate can not be empty")
	String rate;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Quotation")
	Quotation quotation;
	
	@Column(name = "EquipmentName")
	@NotEmpty(message = "Equipment can not be empty")
	String equipmentName;
	

	@Column(name = "EquipmentType")
	@NotEmpty(message = "Equipmen Type can not be empty")
	String equipmentType;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "workspaceId")
	Workspace workspace;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public Quotation getQuotation() {
		return quotation;
	}

	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}


	
	
	

}
