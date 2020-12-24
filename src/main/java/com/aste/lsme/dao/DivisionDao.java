package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;

public interface DivisionDao {
	
	boolean persist(Division division, Workspace w);
	public Division get(Long id);
	public void delete(Long id);
	boolean update(Division division, Workspace w);
	public List<Division> getWorkspaceDivisions(Workspace w);
	public List<Division> getAll();	

	List<Division> getDivisionPaginated(int from, Workspace w);
	Long getDivisionCount(Workspace w);
	List<Division> getDivisions(Long workspaceId);
	
	
	
}
