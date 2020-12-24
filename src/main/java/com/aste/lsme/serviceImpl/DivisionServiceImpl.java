
package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.DivisionDao;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.DivisionService;


@Service
@Transactional
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	DivisionDao divisionDao;
	
	@Override
	public boolean persist(Division division,Workspace w) {
		return divisionDao.persist(division,w);
	}

	@Override
	public Division get(Long id) {
		return divisionDao.get(id);
	}

	@Override
	public void delete(Long id) {
		divisionDao.delete(id);
		
	}

	@Override
	public boolean update(Division division,Workspace w) {
		return divisionDao.update(division,w);
	}

	@Override
	public List<Division> getWorkspaceDivisions(Workspace w) {
		return divisionDao.getWorkspaceDivisions(w);
	}

	@Override
	public List<Division> getAll() {
		return divisionDao.getAll();
	}

	@Override
	public List<Division> getDivisionPaginated(int from,Workspace w) {
		return divisionDao.getDivisionPaginated(from,w);
	}

	@Override
	public Long getDivisionCount(Workspace w) {
		return divisionDao.getDivisionCount(w);
	}

	@Override
	public List<Division> getDivisions(Long workspaceId) {
		return divisionDao.getDivisions(workspaceId);
	}

}
