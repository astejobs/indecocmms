package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.aste.lsme.domain.Constants;

//@Entity
//@DiscriminatorValue(value=Constants.ELECTRICALSUBSYSTEM)
public class Electrical  {

	@Column(name = "Model")
	String model;
	
	@Column(name = "Serial_No")
	String serialNo;
	
	@Column(name = "Capacity")
	String capacity;
	
	@Column(name = "Quantity")
	String quantity;

/*
	public Electrical() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Electrical(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
*/
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	
	
}
