package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
//@DiscriminatorValue(value = Constants.MECHANICALSUBSYSTEM)
public class Mechanical {
	

	@Column(name = "Brand")
	private String brand; 
	

	@Column(name = "Serial_No")
	private String serialNo;
	

	@Column(name = "Quantity")
	private String quantity;
	
	

	@Column(name = "Drawing_name") 
	private String drawingName;


	public Mechanical() {
		// TODO Auto-generated constructor stub
	}

	
	/*public Mechanical(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	public String getBrand() {
		return brand;
	}
	*/


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getSerialNo() {
		return serialNo;
	}


	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}




	public String getDrawingName() {
		return drawingName;
	}


	public void setDrawingName(String drawingName) {
		this.drawingName = drawingName;
	}


	
	
	
	

}
