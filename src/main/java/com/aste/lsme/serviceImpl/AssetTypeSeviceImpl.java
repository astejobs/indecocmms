 package com.aste.lsme.serviceImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.AssetTypeDao;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.service.AssetTypeService;


@Service
@Transactional
public class AssetTypeSeviceImpl implements AssetTypeService {
	
	@Autowired
	AssetTypeDao dao;

	@Override
	public boolean addAssettype(AssetType assetType) {
		return dao.addAssetType(assetType);
		
	}

	@Override
	public AssetType get(Long id) {
		
		return dao.get(id);
	}

	@Override
	public boolean update(AssetType assetType) {
		return dao.update(assetType);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public Long getAssetTypeCount() {
		return dao.getAssetTypeCount();
	}

	@Override
	public List<AssetType> getAssetPaginated(int from) {
		return dao.getAssetPaginated(from);
	}

	@Override
	public List<AssetType> getAll() {
		return dao.getAll();
	}
	
	public AssetType getAssetTypeOnSubtypeName(String name)
	{
		return dao.getAssetTypeOnSubtypeName(name);
	}
	
	public List<AssetType> getAssetType() {
		 return dao.getAssetType();
		
	}
	

}
