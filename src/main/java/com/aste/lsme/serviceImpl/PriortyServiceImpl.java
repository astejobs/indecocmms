package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PriortyDao;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.PriortyService;

@Service
@Transactional
public class PriortyServiceImpl implements PriortyService {
	@Autowired
	PriortyDao priortyDao;
	
	@Override
	public boolean persist(Priorty priorty,Workspace w) {
		
		return priortyDao.persist(priorty,w);
	}
	
	@Override
	public Priorty get(Long id) {
		return priortyDao.get(id);
	}

	@Override
	public void delete(Long id) {
		priortyDao.delete(id);
	}

	

	@Override
	public List<Priorty> getAll() {
		return priortyDao.getAll();
	}

	@Override
	public boolean update(Priorty priorty,Workspace w) {
		return priortyDao.update(priorty,w);
	}


	@Override
	public List<Priorty> getPriortyPaginated(int from,Workspace w) {
		return priortyDao.getPriortyPaginated(from,w);
	}

	@Override
	public long getPriortyCount(Workspace w) {
		return priortyDao.getPriortyCount(w);
	}

	@Override
	public List<Priorty> getWorkspacePriorty(Workspace w) {
		return priortyDao.getWorkspacePriorty(w);
	}

	@Override
	public List<Priorty> getPriorties(Long workspaceId) {
	
		return priortyDao.getPriorties(workspaceId);
	}

}
