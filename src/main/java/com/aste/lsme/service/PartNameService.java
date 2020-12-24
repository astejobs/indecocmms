package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.PartName;

public interface PartNameService {

	public boolean persist(PartName partName);
	public PartName get(long id);
	public void delete(long id);
	public boolean update(PartName partName);
	public List<PartName> getall();
	
}
