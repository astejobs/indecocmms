package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Electrical;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;

public interface  ElectricalDao {
	public boolean persist (Electrical electrical, Workspace w);
	public void delete(Long id) throws Exception;
	public boolean update(Electrical electrical, Workspace w);
    public List<Electrical> getAll();
	public Electrical find(long id);
	public long getElectricalCount(EquipmentSearchCriteria electricalSearch, Workspace w);
	public List<Electrical> getSearchList(int from, EquipmentSearchCriteria electricalSearch, Workspace w);	
}
