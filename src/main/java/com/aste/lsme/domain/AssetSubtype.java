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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity  
@Table(name="Asset_SubType")

public class AssetSubtype {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Asset_Subtype_ID")
	Long id;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max = 30,message="Size must be less than 30")
	@NotEmpty(message = "Enter Asset Subtype ID")
	@Column(name="Asset_SubType_Name")
	String assetSubTypeName;
	
	@JsonIgnore
	@NotNull(message = "Please Select Asset Type")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Asset_ID")
	AssetType assetType ; 
	


	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssetSubTypeName() {
		return assetSubTypeName;
	}

	public void setAssetSubTypeName(String assetSubTypeName) {
		this.assetSubTypeName = assetSubTypeName;
	}

	

	
}
