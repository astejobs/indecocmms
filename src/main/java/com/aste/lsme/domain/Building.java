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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.aste.lsme.utils.Encryptor;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Building")
public class Building {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Building_Id")
	Long id;

	@NotEmpty(message = "Enter Building ID")
	@Column(name = "Building_Name")
    String name; 

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	Workspace workspace;

	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 200)
	@NotEmpty(message = "Enter Building Description")
	@Column(name = "Building_Description")
	String description; 
	
	@ManyToOne
	GeoLocation geoLocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public String getName() {
		return Encryptor.decrypt(name);
	}

	public void setName(String name) {	
		this.name = Encryptor.encrypt(name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

}
