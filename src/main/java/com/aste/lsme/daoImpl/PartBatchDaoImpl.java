package com.aste.lsme.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PartBatchDao;
import com.aste.lsme.domain.PartBatch;
@Repository
public class PartBatchDaoImpl implements PartBatchDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void persist(PartBatch partBatch) {
		em.persist(partBatch);
	}

	@Override
	public PartBatch update(PartBatch partBatch) {
		return em.merge(partBatch);
	}

	@Override
	public PartBatch get(long id) {
		return em.find(PartBatch.class, id);
	}

}
