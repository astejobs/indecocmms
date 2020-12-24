package com.aste.lsme.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aste.lsme.dao.CivilDao;
import com.aste.lsme.domain.Civil;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.CivilService;

@Service
@Transactional
@Component
public class CivilServiceImpl implements
		CivilService {

	@Autowired
	CivilDao civilEquipmentDao;
	
	@Override
	public List<Civil> getAllCivilEquipments() {
		return civilEquipmentDao.getAllCivilEquipments();
	}

	@Override
	public boolean add(Civil civilEquipment,Workspace w) {
		
		try {
			int i = 0;
		/*	while (civilEquipment.getBaseline().get(i) != null)
				civilEquipment.getBaseline().get(i++).setEquipment(civilEquipment);
				*/
		} catch (Exception e) {
		}
		if (civilEquipmentDao.add(civilEquipment,w))
			return true;
		else
			return false;
	}

	@Override
	public Civil find(long id) {
		return civilEquipmentDao.find(id);
	}

	

	@Override
	public List<Civil> getSearchList(EquipmentSearchCriteria esc,int from,Workspace w) {
		return  civilEquipmentDao.getSearchList(esc,from,w);
	}

	@Override
	public Civil delete(Civil c) {
		return civilEquipmentDao.delete(c);
				
	}

	@Override
	public long getCount(Workspace w,EquipmentSearchCriteria esc) {
	
		return civilEquipmentDao.getCount(w,esc);
	}

	@Override
	public List<Civil> getPaginated(int from,Workspace w) {
		return civilEquipmentDao.getPaginated(from,w);
	}

	@Override
	public List<Civil> getWorkspaceRelatedCivilLevel3(Workspace w) {
		return civilEquipmentDao.getWorkspaceRelatedCivilLevel3(w);
	}

	@Override
	public boolean update(Civil equipment, Workspace w) {
		return civilEquipmentDao.update(equipment,w);
	}



}
