package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.PartName;

public interface PartNameDao {

	public boolean persist(PartName partName);
	public PartName get(long id);
	public void delete(long id);
	public boolean update(PartName partName);
	public List<PartName> getall();
}
