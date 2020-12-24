package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.ManufacturerDao;
import com.aste.lsme.domain.Manufacturer;
import com.aste.lsme.service.ManufacturerService;


@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

	
	
	 @Autowired
	 ManufacturerDao dao;
	
	@Override
	public boolean save(Manufacturer m) {
		
		return dao.save(m);
	}

	@Override
	public long getCount() 
	{
		return dao.getCount();
	}

	@Override
	public List<Manufacturer> getPaginated(int from) 
	{
		return dao.getPaginated(from);
	}

	@Override
	public Manufacturer getManufacturer(long id) 
	{
		
		return dao.getManufacturer(id);
	}

	@Override
	public boolean update(Manufacturer m) 
	{
		return dao.update(m);
	}

	@Override
	public void delete(Long id) {
		
		dao.delete(id);
	}

	@Override
	public List<Manufacturer> getAll() 
	{
		return dao.getAll();
	}

}
