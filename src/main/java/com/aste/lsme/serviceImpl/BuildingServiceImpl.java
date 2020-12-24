package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.BuildingDao;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.BuildingService;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	BuildingDao buildingDao;
	
	@Override
	public boolean persist(Building building,Workspace w) {
		
		return buildingDao.persist(building,w);
	}

	@Override
	public Building get(Long id) {
		return buildingDao.get(id);
	}

	@Override
	public void delete(Long id) {
		buildingDao.delete(id);
	}

	@Override
	public List<Building> getWorkspaceBuildings(Workspace w) {
		return buildingDao.getWorkspaceBuildings(w);
	}

	@Override
	public List<Building> getAll() {
		return buildingDao.getAll();
	}

	@Override
	public boolean update(Building building,Workspace w) {
		return buildingDao.update(building,w);
	}

	@Override
	public List<Building> getBuildingPaginated(int from,Workspace w) {
		return buildingDao.getBuildingPaginated(from,w);
	}

	@Override
	public Long getBuildingCount(Workspace w) {
		return buildingDao.getBuildingCount(w);
	}

	@Override
	public List<Building> getBuildings(Long workspaceId) {
		return buildingDao.getBuildings(workspaceId);
	}

}
