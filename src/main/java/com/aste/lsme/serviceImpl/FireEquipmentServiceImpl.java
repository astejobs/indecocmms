package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.FireEquipmentDao;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Fire;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.FireEquipmentService;

@Service
@Transactional

public class FireEquipmentServiceImpl implements FireEquipmentService{

	@Autowired
	FireEquipmentDao fireequipmentdao;
	
	@Override
	public boolean persist(Fire fireequipment) {
		return fireequipmentdao.persist(fireequipment);
	}

	@Override
	public Fire get(Long id) {
		return fireequipmentdao.get(id);
	}

	
	

	@Override
	public boolean update(Fire fireequipment) {
		return fireequipmentdao.update(fireequipment);
	}

	@Override
	public List<Fire> getWorkspaceFireEquipment(Workspace w) {
		return fireequipmentdao.getWorkspaceFireequipments(w);
	}

	@Override
	public List<Fire> getAll() {
		return fireequipmentdao.getAll();
	}

	@Override
	public List<Fire> getFireEquipmentsPaginated(int from) {
		return fireequipmentdao.getfireequipmentsPaginated(from);
	}

	@Override
	public Long getFireEquipmentCount() {
		return fireequipmentdao.getfireequipmentsCount();
	}

	@Override
	public boolean delete(Fire fire) {
		return fireequipmentdao.delete(fire);
	}

	@Override
	public List<Fire> getSearchList(int from,EquipmentSearchCriteria fireSearchcriteria,Workspace w) {
		return  fireequipmentdao.getSearchList(from,fireSearchcriteria,w);
	}

	@Override
	public long count(EquipmentSearchCriteria fireSearch,Workspace w) {
		return  fireequipmentdao.count(fireSearch,w);
	}

}
