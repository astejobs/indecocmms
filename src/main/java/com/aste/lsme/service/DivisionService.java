package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;

public interface DivisionService {

	
	public boolean persist (Division division, Workspace w);
	public Division get(Long id);
	public void delete(Long id);
	public boolean update(Division division, Workspace w);
	public List<Division> getWorkspaceDivisions(Workspace w);
	public List<Division> getAll();
	public List<Division> getDivisionPaginated(int from, Workspace w);
	public Long getDivisionCount(Workspace w);
	public List<Division> getDivisions(Long workspaceId);
	
	
	
}
