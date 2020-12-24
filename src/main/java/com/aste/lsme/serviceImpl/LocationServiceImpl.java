package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.LocationDao;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDao locationDao;
	
	
	
	@Override
	public boolean persist(Location location,Workspace w) {
		
		return locationDao.persist(location,w);
	}

	@Override
	public void delete(Long id) {
		
		locationDao.delete(id);
	}

	@Override
	public Location get(Long id) {
		return locationDao.get(id);
	}

	@Override
	public List<Location> getWorkspaceLocations(Workspace w) {
		return locationDao.getWorkspaceLocations(w);
	}

	@Override
	public List<Location> getBuildingLocations(Long id) {
		return locationDao.getBuildingLocations(id);
	}

	@Override
	public List<Location> getAll() {
		return locationDao.getAll();
	}

	@Override
	public boolean update(Location location,Workspace w) {
		return locationDao.update(location,w);
	}

	@Override
	public List<Location> getLocationPaginated(int from,Workspace w) {
		return locationDao.getLocationPaginated(from,w);
	}

	@Override
	public Long getLocationCount(Workspace w) {
		return locationDao.getLocationCount(w);
	}

	@Override
	public List<Location> getLocationList(Long buildid) {
		return locationDao.allLocations(buildid);
	}


}
