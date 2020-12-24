package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Fire;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;


public interface FireEquipmentDao {

	public boolean persist(Fire fireequipment);
	public boolean delete(Fire fire);
	public Fire get(Long id);
	public boolean update(Fire fireequipment);
	public List<Fire> getWorkspaceFireequipments(Workspace w);
	public List<Fire> getAll();
	public List<Fire> getfireequipmentsPaginated(int from);
	public Long getfireequipmentsCount();
	
	public long count(EquipmentSearchCriteria fireSearch,Workspace w);
	public List<Fire> getSearchList(int from, EquipmentSearchCriteria fireSearchcriteria, Workspace w);
}
