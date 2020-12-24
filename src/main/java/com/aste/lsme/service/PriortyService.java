package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;

public interface PriortyService {
	public boolean persist (Priorty priorty, Workspace w);
	public Priorty get(Long id);
	public void delete(Long id);
	public boolean update(Priorty priorty, Workspace w);
	public List<Priorty> getAll();
	public long getPriortyCount(Workspace w);
	public List<Priorty> getPriortyPaginated(int from, Workspace w);
	public List<Priorty> getWorkspacePriorty(Workspace w);
	public List<Priorty> getPriorties(Long workspaceId);	

}
