package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.VendorServiceDao;
import com.aste.lsme.domain.Vendor;
import com.aste.lsme.service.VendorService;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorServiceDao dao;
	
	
	@Override
	public boolean saveVendor(Vendor v) 
	{
		return dao.saveVendor(v);
	}

	@Override
	public long getCount() {
		return dao.getCount();
	}

	@Override
	public List<Vendor> getPaginated(int from) 
	{
		return dao.getPaginated(from);
	}

	@Override
	public Vendor getVendor(long id) {
		return dao.getVendor(id);
	}

	@Override
	public boolean update(Vendor v) {
		return dao.update(v);
	}

	@Override
	public void delete(Long id) {
		
		dao.delete(id);
	}


	@Override
	public List<Vendor> getAll() {
		return dao.getAll();
	}


}
