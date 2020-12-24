package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PartDao;
import com.aste.lsme.domain.Part;
import com.aste.lsme.service.PartService;



@Service
@Transactional
public class PartServiceImpl implements PartService{

	@Autowired
	PartDao dao;
	
	@Override
	public boolean save(Part part) {
		return dao.save(part);
	}

	@Override
	public long getCount() {
		return dao.getCount();
	}

	@Override
	public List<Part> getPartPaginated(int from) {
		return dao.getPartPaginated(from);
	}

	@Override
	public Part getPart(Long id) {
		return dao.gePart(id);
	}

	@Override
	public boolean update(Part part) {
		return dao.update(part);
	}

	@Override
	public boolean remove(long id) {
		return dao.remove(id);
	}

	@Override
	public List<Part> getAll() {
		return dao.getAll();
	}

}
