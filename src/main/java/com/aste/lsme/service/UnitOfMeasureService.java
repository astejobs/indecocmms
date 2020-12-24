package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.UnitOfMeasure;

public interface UnitOfMeasureService {
	boolean addMeasureUnit(UnitOfMeasure unitofmeasure);

	List<UnitOfMeasure>  getUnitOfMeasure();

	Object get(Long id);

	boolean update(UnitOfMeasure unitmeasure);

	void delete(Long id);

	long getUnitOfMeaasureCount();

	Object getUnitOfMeaasurePaginated(int from);
	

}

