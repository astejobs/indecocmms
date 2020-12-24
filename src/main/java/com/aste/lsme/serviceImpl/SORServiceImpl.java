package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.SORDao;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.SOR;
import com.aste.lsme.domain.SORSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.SORService;

@Service
@Transactional
public class SORServiceImpl implements SORService{

	@Autowired
	SORDao SORdao;
	
	
	@Override
	public List<Equipment> getEqpName(String assetId,Workspace w) {
		// TODO Auto-generated method stub
		return SORdao.getEqpName(assetId,w);
	}


	@Override
	public void save(SOR sor) throws Exception {
		SORdao.save(sor);
	}


	@Override
	public List<SOR> getSearchList(int from, SORSearchCriteria sorSearch, Workspace w) {
		return  SORdao.getSearchList(from,sorSearch,w);
	}
	

	@Override
	public long count(SORSearchCriteria sorSearch,Workspace w) {
		return SORdao.count(sorSearch,w);
	}


	@Override
	public SOR find(Long id) {
		// TODO Auto-generated method stub
		return SORdao.find(id);
	}
	
	public List<SOR> find(Long[] id)
	{
		return SORdao.find(id);
	}


	@Override
	public boolean remove(SOR sor) {
		
			if(SORdao.remove(sor))
			return true;
			else
			return false;
		
	}


	@Override
	public void update(SOR sor,Workspace w) throws Exception {
		  SORdao.update(sor,w);
			
	}


	@Override
	public List<SOR> getWorkspaceSor(Workspace w) {
		
		return SORdao.getWorkspaceSor(w);
	}


	@Override
	public List<AssetSubtype> getAssetSubtype(long assetId) {
		// TODO Auto-generated method stub
		return SORdao.getAssetSubtype(assetId);
	}


	@Override
	public List<AssetType> getAsset(Long id) {
		// TODO Auto-generated method stub
		return SORdao.getAsset(id);
	}


	
}
