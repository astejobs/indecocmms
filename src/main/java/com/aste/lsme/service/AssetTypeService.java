package com.aste.lsme.service;

import java.util.List;


import com.aste.lsme.domain.AssetType;

public interface AssetTypeService {

	public boolean addAssettype(AssetType assetType);
	public List<AssetType> getAll();
	public boolean update(AssetType assetType);
	public AssetType get(Long id);
	public void delete(Long id);
	public Long getAssetTypeCount();
	public List<AssetType> getAssetPaginated(int from);
	public AssetType getAssetTypeOnSubtypeName(String name);
	public List<AssetType> getAssetType();
}
