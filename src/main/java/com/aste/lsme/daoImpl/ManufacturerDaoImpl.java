package com.aste.lsme.daoImpl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.ManufacturerDao;
import com.aste.lsme.domain.Manufacturer;

@Repository
public class ManufacturerDaoImpl implements ManufacturerDao {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean save(Manufacturer m) {
		
		Query query = em.createQuery("Select m from Manufacturer m where m.name= :name and m.description= :desc");
		query.setParameter("name", m.getName());
		query.setParameter("desc", m.getDescription());

		if(query.getResultList().isEmpty())
		{
		  em.persist(m);
		 	return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public long getCount() {
		
		Query q= em.createQuery("select m from Manufacturer m");
		return q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getPaginated(int from) 
	{		
		Query q= em.createQuery("select m from Manufacturer m");
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Manufacturer getManufacturer(long id) {
		
		return em.find(Manufacturer.class, id);
	}

	@Override
	public boolean update(Manufacturer m) {
		Query query = em.createQuery("Select m from Manufacturer m where m.id != :id and m.name = :name");
		query.setParameter("id", m.getId());
		query.setParameter("name", m.getName());
		
		if(query.getResultList().isEmpty())
		{
		  em.merge(m);
		 	return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void delete(Long id) {
		
		em.remove(em.find(Manufacturer.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacturer> getAll() {
		Query q = em.createQuery("Select m from Manufacturer m") ;
		return q.getResultList();
	}

	 

	
}
