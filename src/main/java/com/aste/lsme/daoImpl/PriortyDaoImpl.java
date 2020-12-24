package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PriortyDao;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Priorty;
import com.aste.lsme.domain.Workspace;

@Repository
public class PriortyDaoImpl  implements PriortyDao{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(Priorty priorty,Workspace w) {
		Query query = em.createQuery("Select b from Priorty b where b.name = :name and b.workspace=:w").setParameter("w",w);
		query.setParameter("name", priorty.getName());
		if(query.getResultList().isEmpty()){
		  em.persist(priorty);
		 	return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Priorty get(Long id) {
		return  em.find(Priorty.class, id);
	}

	@Override
	public void delete(Long id) {

		 em.remove(em.find(Priorty.class, id));
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Priorty> getAll() {
		Query q = em.createQuery("Select b from Priorty b");
		return q.getResultList();
	}

	@Override
	public boolean update(Priorty priorty,Workspace w) {
	
		Query query = em.createQuery("Select b from Priorty b where b.name = :name and b.workspace = :w and b.id!=:id");
		query.setParameter("name", priorty.getName());
		query.setParameter("w",w);
		query.setParameter("id", priorty.getId());
		if(query.getResultList().isEmpty()){
			
			 em.merge(priorty);
			 return true;
		}
		else{
				return false;
			}

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Priorty> getPriortyPaginated(int from,Workspace w){
		
		Query q = em.createQuery("Select b from Priorty b where b.workspace =:w").setParameter("w", w);
		System.out.println(q.getResultList().size());
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getPriortyCount(Workspace w) {
		Query q = em.createQuery("Select b from Priorty b where b.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Priorty> getWorkspacePriorty(Workspace w) {
		Query q = em.createQuery("Select b from Priorty b where b.workspace.workspaceId =:w").setParameter("w", w.getWorkspaceId());
		return  q.getResultList();
	}

	@Override
	public List<Priorty> getPriorties(Long workspaceId) {
		Query q = em.createQuery("Select b from Priorty b where b.workspace.id =:w").setParameter("w", workspaceId);
		return  q.getResultList();
	}

}
