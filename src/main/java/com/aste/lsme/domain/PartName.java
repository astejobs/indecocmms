package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class PartName {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name="Part_Name",unique=true)
	@NotNull(message="Part Name is required")
	@NotEmpty(message="Part Name is required")
	String partName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	
	

	
}
