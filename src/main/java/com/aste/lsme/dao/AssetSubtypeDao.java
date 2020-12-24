package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.AssetSubtype;

public interface AssetSubtypeDao {

	boolean addAssetSubtype(AssetSubtype assetSubtype);
	List<AssetSubtype> getAssetSubtypeList();
	boolean update(AssetSubtype assetSubtype);
	void delete(Long id);
	AssetSubtype get(Long id);
	public List<AssetSubtype>getEquipmentSubTypeList(String type);
	List<AssetSubtype> getAssetSubTypePaginated(int from);
	Long getAssetSubTypeCount();
	List<AssetSubtype> getEquipmentSubSystems(String eqType);
	public List<AssetSubtype> getAssetSubtype(Long id);
	public  List<AssetSubtype> getEquipmentSubSystemOnName(String name) ;
	List<AssetSubtype> getSubSystems(Long id);

}
