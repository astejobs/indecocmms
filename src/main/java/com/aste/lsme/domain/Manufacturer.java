package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="manufacturer")
public class Manufacturer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30,message="Size must be less than 30")
	@NotEmpty(message = "Enter Manufacturer Name")
	@Column(name = "NAME")
	private String name;
	 
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 200)
	@NotEmpty(message = "Enter Manufacturer Description")
	@Column(name="DESCRIPTION")
	private String description;

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
	
	
	
}
