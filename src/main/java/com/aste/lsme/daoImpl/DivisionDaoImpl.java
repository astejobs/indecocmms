package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.DivisionDao;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;

@Repository
public class DivisionDaoImpl implements DivisionDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(Division division,Workspace w) {
	
		Query query = em.createQuery("Select d from Division d where d.name = :name and d.workspace = :w ");
		query.setParameter("name", division.getName());
	//	query.setParameter("desc", division.getDescription());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  em.persist(division);
		
		 	return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Division get(Long id) {
		return  em.find(Division.class, id);
	}

	@Override
	public void delete(Long id) {
		 em.remove(em.find(Division.class, id));
		
	}

	@Override
	public boolean update(Division division,Workspace w) {
	
		long id =division.getId();
		Query query = em.createQuery("Select d from Division d where d.name = :name and d.workspace = :w and d.id != :id");
		query.setParameter("name", division.getName());
		query.setParameter("w", w);
		query.setParameter("id", id);
		if(query.getResultList().isEmpty()){
			
		  em.merge(division);
		
		 	return true;
		}
		else{
			return false;
		}
	
  
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Division> getWorkspaceDivisions(Workspace w) {
		Query q = em.createQuery("Select d from Division d where d.workspace.workspaceId = :w").setParameter("w", w.getWorkspaceId());
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Division> getAll() {
		Query q = em.createQuery("Select d from Division d");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Division> getDivisionPaginated(int from,Workspace w) {
		Query q = em.createQuery("Select d from Division d where d.workspace = :w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getDivisionCount(Workspace w) {
		Query q = em.createQuery("Select d from Division d where d.workspace = :w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@Override
	public List<Division> getDivisions(Long workspaceId) {
		Query q = em.createQuery("Select d from Division d where d.workspace.id = :w").setParameter("w", workspaceId);
		return q.getResultList();
	}

}
