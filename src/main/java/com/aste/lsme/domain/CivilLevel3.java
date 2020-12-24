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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;



//@Entity
//@Table(name="Civil_Level3")
public class CivilLevel3 {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="CivilLevel3_id")
	Long id;
	
	
	@Column(name="Civil_Level3_Name")
	@NotEmpty(message="Civil Level 3 Name cannot be empty")
	
	String civilLevel3Name;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="AssetSubType_Id")
	@NotNull(message="Please Select Asset SubType")
	AssetSubtype assetSubtype;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WORKSPACE")
	Workspace workspace;

	
	
	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCivilLevel3Name() {
		return civilLevel3Name;
	}

	public void setCivilLevel3Name(String civilLevel3Name) {
		this.civilLevel3Name = civilLevel3Name;
	}

	public AssetSubtype getAssetSubType() {
		return assetSubtype;
	}
	
	public void setAssetSubType(AssetSubtype assetSubtype) {
		this.assetSubtype = assetSubtype;	
	}
}
