package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.MechanicalDao;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Mechanical;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.MechanicalService;

@Service
@Transactional
public class MechanicalServiceImpl implements MechanicalService {

	@Autowired
	MechanicalDao mechDao;

	@Override
	public boolean save(Mechanical mechanicalEquipment) {
		if(mechDao.save(mechanicalEquipment))
		return true;
		else
		return false;
	}

	
	@Override
	public boolean geteqpfile(String equipFile) {
		
		return mechDao.geteqpfile(equipFile);
	}

	@Override
	public boolean getdrawfile(String drawImage) {
		return mechDao.getdrawfile(drawImage);
	}

	@Override
	public Mechanical find(Long id) {
		return mechDao.find(id);
	}

	@Override
	public List<Mechanical> getMechEquips() {
		return mechDao.getMechEquips();
	}

	@Override
	public boolean remove(Mechanical mechanical) {
		
	if( mechDao.remove(mechanical))
		return true;
	else
		return false;
		
	}

	@Override
	public List<Mechanical> getEqPaginated(int from) {
		return mechDao.etEqPaginated(from);
	}

	@Override
	public boolean update(Mechanical mechanical) {
		return mechDao.update(mechanical);
	}

	@Override
	public List<Mechanical> getSearchList(int from,EquipmentSearchCriteria mechanicalSearch,Workspace w) {
		return  mechDao.getSearchList(from,mechanicalSearch,w);
	}

	@Override
	public Long count(EquipmentSearchCriteria Mechanical,Workspace w) {
		return mechDao.count(Mechanical,w);
	}
}
