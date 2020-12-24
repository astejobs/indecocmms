package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PartNameDao;
import com.aste.lsme.domain.PartName;
import com.aste.lsme.service.PartNameService;

@Service
@Transactional
public class PartNameServiceImpl implements PartNameService {

	@Autowired
	PartNameDao partNameDao;
	
	@Override
	public boolean persist(PartName partName) {
		return partNameDao.persist(partName);
	}

	@Override
	public PartName get(long id) {
		return partNameDao.get(id);
	}

	@Override
	public void delete(long id) {
		partNameDao.delete(id);
	}

	@Override
	public boolean update(PartName partName) {
		return partNameDao.update(partName);
	}

	@Override
	public List<PartName> getall() {
		return partNameDao.getall();
	}

}
