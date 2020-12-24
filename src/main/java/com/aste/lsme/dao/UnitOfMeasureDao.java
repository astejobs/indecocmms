package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.UnitOfMeasure;

public interface UnitOfMeasureDao {
	public boolean addUnitOfMeasure(UnitOfMeasure unitofmeasure); 
	public List<UnitOfMeasure> getUnitOfMeasure();
	public Object get(Long id);
	public boolean update(UnitOfMeasure unitmeasure);
	public void delete(Long id);
	public long getUnitOfMeaasureCount();
	public List<UnitOfMeasure> getUnitOfMeaasurePaginated(int from);
}
