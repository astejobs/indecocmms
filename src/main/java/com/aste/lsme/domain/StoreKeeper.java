package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StoreKeeper {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="User_ID")
	UserDetail userDetail;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Warehouse_ID")
	@JsonIgnore
	Warehouse warehouse;

	@Column(name="Status")
	boolean status;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
