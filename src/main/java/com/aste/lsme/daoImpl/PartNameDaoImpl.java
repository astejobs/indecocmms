package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PartNameDao;
import com.aste.lsme.domain.PartName;

@Repository
public class PartNameDaoImpl implements PartNameDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(PartName partName) {
		
		 Query query = em.createQuery("Select p from PartName p where p.partName = :p").setParameter("p",partName.getPartName());
		 if(query.getResultList().isEmpty()){
			em.persist(partName);
			return true;
	    }
		 else{
			 return false;
	    }
	}

	@Override
	public PartName get(long id) {
		return em.find(PartName.class,id);
	}

	@Override
	public void delete(long id) {
		em.remove(em.find(PartName.class, id));
	}

	@Override
	public boolean update(PartName partName) {
		Query query = em.createQuery("Select p from PartName p where p.id != :id and p.partName = :p ");
		query.setParameter("id", partName.getId());
		query.setParameter("p", partName.getPartName());
		if(query.getResultList().isEmpty()){
		     em.merge(partName);
		   return true;
		}
		else{
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartName> getall() {
		Query query = em.createQuery("Select p from PartName p");
		return query.getResultList();
	}

}
