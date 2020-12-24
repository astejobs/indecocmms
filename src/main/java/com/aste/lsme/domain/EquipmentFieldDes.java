package com.aste.lsme.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="equipmentfielddes")
public class EquipmentFieldDes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	Long id;
	
	String labelName;
	
	String value;
	
	//@JoinColumn(name = "id")
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	DynamicFieldsOfEquiment dynamicFieldsOfEquipment;
	

	

	

	public DynamicFieldsOfEquiment getDynamicFieldsOfEquipment() {
		return dynamicFieldsOfEquipment;
	}

	public void setDynamicFieldsOfEquipment(DynamicFieldsOfEquiment dynamicFieldsOfEquipment) {
		this.dynamicFieldsOfEquipment = dynamicFieldsOfEquipment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	

}
