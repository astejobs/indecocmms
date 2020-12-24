package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Workspace;

public interface BuildingService {

	public boolean persist (Building building,Workspace w);
	public Building get(Long id);
	public void delete(Long id);
	public boolean update(Building building,Workspace w);
	public List<Building> getWorkspaceBuildings(Workspace w);
	public List<Building> getAll();	
	public List<Building> getBuildingPaginated(int from,Workspace w);
	public Long getBuildingCount(Workspace w);
	public List<Building> getBuildings(Long workspaceId);

}
