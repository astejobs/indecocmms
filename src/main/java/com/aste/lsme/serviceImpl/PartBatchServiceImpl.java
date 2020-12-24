package com.aste.lsme.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PartBatchDao;
import com.aste.lsme.domain.PartBatch;
import com.aste.lsme.service.PartBatchService;

@Service
@Transactional
public class PartBatchServiceImpl implements PartBatchService {

	@Autowired
	PartBatchDao partBatchDao;
	
	@Override
	public void persist(PartBatch partBatch) {
		partBatchDao.persist(partBatch);
	}

	@Override
	public PartBatch update(PartBatch partBatch) {
		return partBatchDao.update(partBatch);
	}

	@Override
	public PartBatch get(long id) {
		return partBatchDao.get(id);
	}

}
