package com.aste.lsme.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Content {

	
	private String id;
	
	private String data;
	
	
	

    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm")
	private LocalDateTime deliveryDate;
	
	
	
	public Content() {
		super();
	}	

	public  Content(String id, String data) {
		super();
		this.id = id;
		this.data = data;
	}


	public  Content(String id, String data,LocalDateTime deliveryDate) {
		super();
		this.id = id;
		this.data = data;
		this.deliveryDate=deliveryDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getData() {
		return data;
	}



	public void setData(String data) {
		this.data = data;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	
	
	
}
