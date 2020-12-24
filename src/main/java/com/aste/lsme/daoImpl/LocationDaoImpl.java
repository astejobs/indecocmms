package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.LocationDao;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;

@Repository
public class LocationDaoImpl implements LocationDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(Location location,Workspace w) {
		
		Query query = em.createQuery("Select l from Location l where l.name = :name and l.building.id = :id and l.workspace = :w ");
		query.setParameter("name", location.getName());
		query.setParameter("id", location.getBuilding().getId());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
			em.persist(location);
			return true;
		}
		else{
			return false;
		}	
			
			
			
		
	}

	@Override
	public void delete(Long id) {

		em.remove(em.find(Location.class, id));
	}

	@Override
	public Location get(Long id) {
		
		return em.find(Location.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getWorkspaceLocations(Workspace w) {
		
		Query q = em.createQuery("Select l from Location l where workspace = :w").setParameter("w", w);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getBuildingLocations(Long id) {
		Query q = em.createQuery("Select l from Location l where building.id = :id ").setParameter("id", id);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAll() {
		Query q = em.createQuery("Select l from Location l");
		return q.getResultList();
	}

	@Override
	public boolean update(Location location,Workspace w) {
		Query query = em.createQuery("Select l from Location l where l.id != :id and l.name = :name and l.workspace.id =:w");
		query.setParameter("name", location.getName());
		query.setParameter("w", w.getId());
		query.setParameter("id", location.getId());
		if(query.getResultList().isEmpty()){
			em.merge(location);
			return true;
		}
		else{
			return false;
		}
	}


/*	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAllLocation(Building building) {
		Query query=em.createQuery("select l from Location l where l.building=:building").setParameter("building", building);
		List<Location> list=query.getResultList();
		return list;
	}
*/

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getLocationPaginated(int from,Workspace w) {
		Query q = em.createQuery("Select l from Location l where l.workspace = :w ");
		q.setParameter("w",w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}
	
	@Override
	public Long getLocationCount(Workspace w){
		Query q = em.createQuery("Select l from Location l where l.workspace = :w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> allLocations(Long buildid) {
		TypedQuery<Location> d = (TypedQuery<Location>) em
				.createQuery("Select l from Location l where building.id=:b");
		d.setParameter("b", buildid);
		return d.getResultList();
	}

}
