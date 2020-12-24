package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;


public interface TeamDao {

	boolean save(Team team);

	List<Team> getTeamList(Workspace w);

	Team getTeam(Long id);

	boolean update(Team team);
	
	void delete(Long id);

	boolean remove(Team team);

	List<Team> getTeamPaginated(int from, Workspace w);

	List<Technician> getActiveTechnicianList(Workspace w);
		
	public boolean update(Team t,Workspace w);
	
	long getTeamCount(Workspace w);
}
