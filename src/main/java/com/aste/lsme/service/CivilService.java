package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Civil;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;


public interface CivilService {

	public List<Civil> getAllCivilEquipments();
	public boolean add(Civil civilEquipment,Workspace w);
	public Civil find(long id);
	public boolean update(Civil equipment,Workspace w);
	public List<Civil> getSearchList(EquipmentSearchCriteria esc,int from,Workspace w);
	public Civil  delete(Civil c);
	public long getCount(Workspace w,EquipmentSearchCriteria esc);
	public List<Civil> getPaginated(int from,Workspace w);
	List<Civil> getWorkspaceRelatedCivilLevel3(Workspace w);
}
