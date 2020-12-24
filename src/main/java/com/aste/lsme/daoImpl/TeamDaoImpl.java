package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.TeamDao;
import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;



@Repository

public class TeamDaoImpl implements TeamDao {
 private EntityManager em;

public EntityManager getEm() {
	return em;
}


@PersistenceContext
public void setEm(EntityManager em) {
	this.em = em;
}


@Override
public boolean save(Team team) {
	try{
		TypedQuery<Team> q = (TypedQuery<Team>)em.createQuery("Select t from Team t where t.workspace=:w and t.name=:name");
		q.setParameter("w", team.getWorkspace());
		q.setParameter("name", team.getName());
		if(q.getResultList().size()>0){
	 return false;
 }else{
		team.setTechnician(team.getTechnician());
		em.persist(team);
		return true;
 }	
	}catch (PersistenceException e) {
		e.printStackTrace();
		System.out.println(e.getStackTrace());
		return false;
	}
}


@Override
public List<Team> getTeamList(Workspace w) {
	TypedQuery<Team> q = (TypedQuery<Team>)em.createQuery("Select t from Team t where t.workspace=:w");
 	q.setParameter("w",w);
    return q.getResultList();
}


@Override
public Team getTeam(Long id) {
	return em.find(Team.class, id);
}


@Override
public boolean update(Team team) {
	try{
		TypedQuery<Team> q = (TypedQuery<Team>)em.createQuery("Select t from Team t where t.workspace=:w and t.name=:name and t.id!=:id").setParameter("name", team.getName());
		q.setParameter("w", team.getWorkspace());
		q.setParameter("id", team.getId());
		if(q.getResultList().size()>0)
		{
			return false;
		}else
		{
			em.merge(team);
			return true;
		}
	
	}catch (Exception e) {
		return false;
	}
	
}


@Override
public boolean remove(Team team) {
	try{
	em.remove(team);
	return true;
	}catch (Exception e) {
		return false;
	}
}


@Override
public List<Team> getTeamPaginated(int from, Workspace w) {
	Query q = em.createQuery("Select t from Team t where t.workspace =:w").setParameter("w", w);
	return q.setFirstResult(from).setMaxResults(10).getResultList();
	
}
 
@Override
public List<Technician> getActiveTechnicianList(Workspace w) {
  TypedQuery<Technician> d = (TypedQuery<Technician>) em
      .createQuery("Select l from Technician l where l.status=:st and l.workspace=:w").setParameter("w", w);
  d.setParameter("st", "Active");
  return d.getResultList();
}


@Override
public boolean update(Team t, Workspace w) {
	
	  Query query = em.createQuery("Select t from Team t where t.name = :name and t.workspace = :w and t.id != :id ");
	    query.setParameter("name", t.getName());
	    query.setParameter("id", t.getId());
	    query.setParameter("w", w);
	    
	    if(query.getResultList().isEmpty()){
	       em.merge(t);
	       return true;
	    }
	    else{
	        return false;
	      }

	
}


@Override
public void delete(Long id) {
	 em.remove(em.find(Team.class, id));
	
}


@Override
public long getTeamCount(Workspace w) {
	   Query q = em.createQuery("Select b from Team b where b.workspace = :w").setParameter("w", w);
	   
	    
	    return (long) q.getResultList().size();

}


}
