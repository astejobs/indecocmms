package com.aste.lsme.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="equipmentfielddata")
public class EquipmentFieldData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String labelName;
	
	String  value;
	
	
	//@JoinColumn(name = "id",insert="false" update="false")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Equipment equipment;
	
	
	



	

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public EquipmentFieldData() {
		super();
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
