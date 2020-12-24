package com.aste.lsme.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="dynamicfieldsofequipment")
public class DynamicFieldsOfEquiment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	
	String equipmentType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	Workspace workspace;
	
	@OneToOne
	AssetSubtype assetSubType;
	

	@OneToMany(mappedBy = "dynamicFieldsOfEquipment", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<EquipmentFieldDes> listFieldDes;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEquipmentType() {
		return equipmentType;
	}


	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}


	public Workspace getWorkspace() {
		return workspace;
	}


	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}


	public AssetSubtype getAssetSubType() {
		return assetSubType;
	}


	public void setAssetSubType(AssetSubtype assetSubType) {
		this.assetSubType = assetSubType;
	}


	public List<EquipmentFieldDes> getListFieldDes() {
		return listFieldDes;
	}


	public void setListFieldDes(List<EquipmentFieldDes> listFieldDes) {
		this.listFieldDes = listFieldDes;
	}






	
	
	

	
	

}
