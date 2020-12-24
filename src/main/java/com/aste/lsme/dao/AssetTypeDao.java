package com.aste.lsme.dao;

import java.util.List;


import com.aste.lsme.domain.AssetType;



public interface AssetTypeDao {

	public boolean addAssetType(AssetType assetType);
	public boolean update(AssetType assetType);
	public AssetType get(Long id);
	public void delete(Long id);
	public Long getAssetTypeCount();
	public List<AssetType> getAssetPaginated(int from);
	public List<AssetType> getAll();
	public AssetType getAssetTypeOnSubtypeName(String name);
	public List<AssetType> getAssetType();
 
}
