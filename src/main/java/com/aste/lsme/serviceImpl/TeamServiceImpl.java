package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.TeamDao;
import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.TeamService;


@Service
@Transactional
@Component
public class TeamServiceImpl implements TeamService {
     
	@Autowired
	TeamDao teamDao;
	
	@Override
	public boolean save(Team team) {
		if(teamDao.save(team))
			return true;
		else
			return false;
   	}

	@Override
	public List<Team> getTeamList(Workspace w) {
		return teamDao.getTeamList(w);
	}

	@Override
	public Team getTeam(Long id) {
		return teamDao.getTeam(id);
	}

	@Override
	public boolean update(Team team) {
		return teamDao.update(team);
	}

	@Override
	public boolean remove(Team team) {
		return teamDao.remove(team);
	}

	@Override
	public List<Team> getTeamPaginated(int from, Workspace w) {
		return teamDao.getTeamPaginated(from,w);
	}

	@Override
	  public List<Technician> getActiveTechnicianList(Workspace w) {
	    // TODO Auto-generated method stub
	    return teamDao.getActiveTechnicianList(w);
	  }

	@Override
	public boolean update(Team t, Workspace w) {
		
		return teamDao.update(t, w);
	}

	@Override
	public void delete(Long id) {
		
		teamDao.delete(id);
	}

	@Override
	public long getTeamCount(Workspace w) {
	
		return teamDao.getTeamCount(w);
	}

}