package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;




//@Entity
//@DiscriminatorValue(value = Constants.FIRESUBSYSTEM)
public class Fire {
	
	@Column(name="brand")
	String brand;
	
	@Column(name="serial_num")
	String serialNumber;
	
	
	@Column(name="quantity")
	String quantity;

	
	@Column(name="capacity")
	String capacity;
	
	@Column(name="work_pressure")
	String workPressure;
	
	@Column(name="propellent_gas")
	String propellent_gas;
	
	
	public Fire() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	public Fire(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	*/
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getWorkPressure() {
		return workPressure;
	}

	public void setWorkPressure(String workPressure) {
		this.workPressure = workPressure;
	}

	public String getPropellent_gas() {
		return propellent_gas;
	}

	public void setPropellent_gas(String propellent_gas) {
		this.propellent_gas = propellent_gas;
	}

	
	

	

}
