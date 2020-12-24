package com.aste.lsme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;


public interface TeamService {

	@Autowired
	boolean save(Team team);

	List<Team> getTeamList(Workspace w);

	Team getTeam(Long id);

	boolean update(Team team);

	boolean remove(Team team);
	
	void delete(Long id);
	
	long getTeamCount(Workspace w);

	List<Team> getTeamPaginated(int from, Workspace w);

	public List<Technician> getActiveTechnicianList(Workspace w);

	public boolean update(Team t,Workspace w);
}
