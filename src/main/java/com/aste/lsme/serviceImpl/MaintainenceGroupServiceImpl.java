package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.MaintainenceGroupDao;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.MaintainenceGroupService;


@Service
@Transactional
public class MaintainenceGroupServiceImpl implements MaintainenceGroupService {
	
	@Autowired
	MaintainenceGroupDao mainDao;
	
	@Override
	public boolean persist( MaintainenceGroup mainGrp,Workspace w) {
		
		return mainDao.persist(mainGrp,w);
	}

	@Override
	public  MaintainenceGroup get(Long id) {
		return mainDao.get(id);
	}

	@Override
	public void delete(Long id) {
		mainDao.delete(id);
	}

	

	@Override
	public List< MaintainenceGroup> getAll() {
		return mainDao.getAll();
	}

	@Override
	public boolean update( MaintainenceGroup mainGrp,Workspace w) {
		return mainDao.update(mainGrp,w);
	}

	@Override
	public long getMainGrpCount(Workspace w) {
		return mainDao.getMainGrpCount(w);

	}

	@Override
	public List<MaintainenceGroup> getMainGrpPaginated(int from, Workspace w) {
		return mainDao.getMainGrpPaginated(from,w);
	}

	@Override
	public List<MaintainenceGroup> getWorkspaceMainGrp(Workspace w) {
		return mainDao.getWorkspaceMainGrp(w);
	}

	@Override
	public List<MaintainenceGroup> getmainGrpCategories(Long workspaceId) {
		return mainDao.getmainGrpCategories(workspaceId);
	}

}
