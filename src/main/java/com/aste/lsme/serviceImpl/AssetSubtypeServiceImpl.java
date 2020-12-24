package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.AssetSubtypeDao;
import com.aste.lsme.domain.AssetSubtype;

import com.aste.lsme.service.AssetSubtypeService;


@Service
@Transactional
public class AssetSubtypeServiceImpl implements AssetSubtypeService {
		
		@Autowired
		AssetSubtypeDao dao;
		
		@Override
		public boolean addAssetSubtype(AssetSubtype assetSubtype) {
			return dao.addAssetSubtype(assetSubtype);
			
		}
		
		@Override
		public List<AssetSubtype> getAssetSubtypeList() {
			return dao. getAssetSubtypeList();
			
		}
		
		@Override
		public AssetSubtype get(Long id) {
			
			return dao.get(id);
		}
		
		@Override
		public void delete(Long id) {
		
			dao.delete(id);
		}
		
		@Override
		public boolean update(AssetSubtype assetSubtype) {
			return dao.update(assetSubtype);
			
		}
		
		@Override
		public List<AssetSubtype> getEquipmentSubTypeList(String type) {
			return dao.getEquipmentSubTypeList(type);
		}
		
		@Override
		public List<AssetSubtype> getAssetSubTypePaginated(int from) {
			return dao.getAssetSubTypePaginated(from);
		}
		
		@Override
		public Long getAssetSubTypeCount() {
			return dao.getAssetSubTypeCount();
		}

		@Override
		public List<AssetSubtype> getEquipmentSubSystems(String eqType) {
			return dao.getEquipmentSubSystems(eqType);
		}

		@Override
		public List<AssetSubtype> getAssetSubtype(Long id) {
			// TODO Auto-generated method stub
			return dao.getAssetSubtype(id);
		}
		public  List<AssetSubtype> getEquipmentSubSystemOnName(String name) 
		{
			return dao.getEquipmentSubSystemOnName(name);
		}

		@Override
		public List<AssetSubtype> getSubSystems(Long id) {
			return dao.getSubSystems(id);
		}
}