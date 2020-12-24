package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;


public interface AssetSubtypeService {

	public boolean addAssetSubtype(AssetSubtype assetSubtype);
	public List<AssetSubtype> getAssetSubtypeList();
	public boolean update(AssetSubtype assetSubtype);
	public void delete(Long id);
	public AssetSubtype get(Long id);
	public List<AssetSubtype>getEquipmentSubTypeList(String type);
	public List<AssetSubtype> getAssetSubTypePaginated(int from);
	public Long getAssetSubTypeCount();
	public List<AssetSubtype> getEquipmentSubSystems(String eqType);
	public List<AssetSubtype> getAssetSubtype(Long id);
	public List<AssetSubtype> getEquipmentSubSystemOnName(String name);
	public  List<AssetSubtype> getSubSystems(Long id);


}
