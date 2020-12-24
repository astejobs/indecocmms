package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Electrical;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;

public interface ElectricalService {

	public boolean persist (Electrical electrical, Workspace w);
	
	public void delete(Long id) throws Exception;
	public boolean update(Electrical electrical, Workspace w);
	public List<Electrical> getWorkspaceElectrical(Workspace w);
	public List<Electrical> getAll();
	public Electrical find(long id);
	public long getElectricalCount(EquipmentSearchCriteria electricalSearch, Workspace w);
	public List<Electrical> getSearchList( int from, EquipmentSearchCriteria electricalSearch, Workspace w);
		
	
}