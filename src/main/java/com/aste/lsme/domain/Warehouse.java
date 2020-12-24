package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Warehouse{

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Column(name = "Name")
	@NotEmpty(message="Warehouse Name is required")
	@NotNull(message="Warehouse Name is required")
	String name;
	
	@Column(name="Description")
	@NotEmpty(message="Warehouse Description is required")
	@NotNull(message="Warehouse Description is required")
	String description;
	
	@Column(name="Location")
	@NotEmpty(message="Location is required")
	@NotNull(message="Location is required")
	String location;

	@JsonIgnore
	@OneToMany(mappedBy="warehouse",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	List<StoreKeeper> storeKeepers;
	
	
	public Warehouse() {
		super();
		this.storeKeepers = new ArrayList<StoreKeeper>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<StoreKeeper> getStoreKeepers() {
		return storeKeepers;
	}

	public void setStoreKeepers(List<StoreKeeper> storeKeepers) {
		this.storeKeepers = storeKeepers;
	}
	
}
