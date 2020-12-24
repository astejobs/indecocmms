package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.BuildingDao;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Workspace;

@Repository
public class BuildingDaoImpl implements BuildingDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(Building building,Workspace w) {
		Query query = em.createQuery("Select b from Building b where b.name = :name and b.workspace = :w");
		query.setParameter("name", building.getName());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  em.persist(building);
		 	return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Building get(Long id) {
		return em.find(Building.class, id);
	}

	@Override
	public void delete(Long id) {

		 em.remove(em.find(Building.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Building> getWorkspaceBuildings(Workspace w) {
		
		Query q = em.createQuery("Select b from Building b where workspace.workspaceId =:w").setParameter("w", w.getWorkspaceId());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Building> getAll() {
		Query q = em.createQuery("Select b from Building b");
		return q.getResultList();
	}

	@Override
	public boolean update(Building building,Workspace w) {
		Query query = em.createQuery("Select b from Building b where b.id != :id and b.name = :name and b.workspace = :w ");
		query.setParameter("id", building.getId());
		query.setParameter("name", building.getName());
		query.setParameter("w",w);
		
		if(query.getResultList().isEmpty()){
			 em.merge(building);
			 return true;
		}
		else{
				return false;
			}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Building> getBuildingPaginated(int from,Workspace w){
		Query q = em.createQuery("Select b from Building b where b.workspace =:w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getBuildingCount(Workspace w) {
		Query q = em.createQuery("Select b from Building b where b.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@Override
	public List<Building> getBuildings(Long workspaceId) {
		Query q = em.createQuery("Select b from Building b where b.workspace.id =:w").setParameter("w", workspaceId);
		return q.getResultList();
	}

}
