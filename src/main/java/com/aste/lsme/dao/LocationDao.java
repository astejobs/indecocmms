package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;

public interface LocationDao {

	public boolean persist(Location location,Workspace w);
	public void delete(Long id);
	public Location get(Long id);
	public boolean update(Location location,Workspace w);
	public List<Location> getWorkspaceLocations(Workspace w);
	public List<Location> getBuildingLocations(Long id);
	public List<Location> getAll();
	public List<Location> getLocationPaginated(int from,Workspace w);
	public Long getLocationCount(Workspace w);
	public List<Location> allLocations(Long buildid);
	
}
