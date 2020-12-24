package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Fire;
import com.aste.lsme.domain.Workspace;

public interface FireEquipmentService {

	public boolean persist (Fire division);
	public Fire get(Long id);
	public boolean delete(Fire fire);
	public boolean update(Fire division);
	public List<Fire> getWorkspaceFireEquipment(Workspace w);
	public List<Fire> getAll();
	public List<Fire> getFireEquipmentsPaginated(int from);
	public Long getFireEquipmentCount();
	public List<Fire> getSearchList(int from, EquipmentSearchCriteria fireSearch, Workspace w);
	public long count(EquipmentSearchCriteria fireSearch,Workspace w);
	
	
	
	
	
	
}
