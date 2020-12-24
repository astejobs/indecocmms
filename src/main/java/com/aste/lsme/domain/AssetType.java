package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="Asset_Type")

public class AssetType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Asset_ID")
	Long id;
	
	@NotEmpty(message = "Enter Asset Type Name")
	@Column(name = "Asset_Type_Name")
	String assetTypeName;
	 	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

}