package com.aste.lsme.serviceImpl;

import java.util.List;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.ElectricalDao;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Electrical;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.ElectricalService;

@Service
@Transactional
public class ElectricalServiceImpl implements ElectricalService {

	@Autowired
	 ElectricalDao electricalDao;
	
	@Override
	public boolean persist(Electrical electrical,Workspace w) {
		
		return electricalDao.persist(electrical,w);
	}

	@Override
	public void delete(Long id)  throws Exception{
		electricalDao.delete(id);
		
	}

	@Override
	public boolean update(Electrical electrical,Workspace w) {
		
		return electricalDao.update(electrical,w);
	}

	@Override
	public List<Electrical> getWorkspaceElectrical(Workspace w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Electrical> getAll() {
		// TODO Auto-generated method stub
		return  electricalDao.getAll();
	}



	@Override
	public Electrical find(long id) {
		
		return electricalDao.find(id);
	}



	@Override
	public long getElectricalCount( EquipmentSearchCriteria electricalSearch,Workspace w) {
		// TODO Auto-generated method stub
		 return electricalDao.getElectricalCount(electricalSearch,w);
	}
	
   @Override
	public List<Electrical> getSearchList(int from, EquipmentSearchCriteria electricalSearch,Workspace w) {
		// TODO Auto-generated method stub
		return electricalDao.getSearchList(from,electricalSearch,w);
	}

	
	}