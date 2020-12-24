package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aste.lsme.dao.UnitOfMeasureDao;
import com.aste.lsme.domain.UnitOfMeasure;
import com.aste.lsme.service.UnitOfMeasureService;
@Service
@Transactional
public class UnitOfMeasureServiceImpl  implements  UnitOfMeasureService{
	@Autowired
	UnitOfMeasureDao dao;
	

	@Override
	public boolean addMeasureUnit(UnitOfMeasure unitofmeasure) {
		
		return dao.addUnitOfMeasure(unitofmeasure);
	}

	@Override
	public List<UnitOfMeasure> getUnitOfMeasure() {
		
		return dao.getUnitOfMeasure();
	}

	@Override
	public Object get(Long id) {
		
		return dao.get(id);
	}

	@Override
	public boolean update(UnitOfMeasure unitmeasure) {
		
		return dao.update(unitmeasure);
	}

	@Override
	public void delete(Long id) {
		
		dao.delete(id);
		
	}
	

	

	@Override
	public long getUnitOfMeaasureCount() {
	
		return dao.getUnitOfMeaasureCount();
	}

	

	@Override
	public List<UnitOfMeasure> getUnitOfMeaasurePaginated(int from) {
		
		return dao.getUnitOfMeaasurePaginated(from);
	}


}
