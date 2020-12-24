package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Manufacturer;

public interface ManufacturerDao
{
	
	
	boolean save(Manufacturer m);
	long getCount();
	List<Manufacturer> getPaginated(int from);
	Manufacturer getManufacturer(long id);
    public boolean update(Manufacturer m);
    public void delete(Long id);
	List<Manufacturer> getAll();
	
	
}
