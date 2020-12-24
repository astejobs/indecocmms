package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.UnitOfMeasureDao;
import com.aste.lsme.domain.UnitOfMeasure;


@Repository
 class UnitOfMeasuredaoImpl implements UnitOfMeasureDao{
	
	
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean addUnitOfMeasure(UnitOfMeasure unitofmeasure) {
		Query q=em.createQuery("select l from UnitOfMeasure l where l.description= :description and l.unitOfMeasure= :unitOfMeasure");
		q.setParameter("description",unitofmeasure.getDescription());
		q.setParameter("unitOfMeasure",unitofmeasure.getUnitOfMeasure());
		if(q.getResultList().isEmpty())
		{
           em.persist(unitofmeasure);
		   return  true;
		}
		else
		{
			return false;
		}		
	}
	
	
	public  List<UnitOfMeasure> getUnitOfMeasure()
	{
		Query q=em.createQuery("Select l from UnitOfMeasure l");
		return q.getResultList();
		
	}


	@Override
	public Object get(Long id) {
		
		return em.find(UnitOfMeasure.class,id);
	}


	@Override
	public boolean update(UnitOfMeasure unitmeasure) {
		System.out.println("toupate");
		
		Query q=em.createQuery("select l from UnitOfMeasure l where l.description= :description and l.unitOfMeasure= :unitOfMeasure");
		q.setParameter("description",unitmeasure.getDescription());
		q.setParameter("unitOfMeasure",unitmeasure.getUnitOfMeasure());
		if(q.getResultList().isEmpty())
		{
			em.merge(unitmeasure);
			return true;
		}
		else
		
		return  false;
		
		
		
		
	}


	@Override
	public void delete(Long id) {
		em.remove(em.find(UnitOfMeasure.class, id));
	// em.remove(id);
		
	}


	@Override
	public long getUnitOfMeaasureCount() {
		Query q=em.createQuery("Select l from UnitOfMeasure l");
		return q.getResultList().size();
	}


	@Override
	public List<UnitOfMeasure> getUnitOfMeaasurePaginated(int from) {
		Query q=em.createQuery("Select l from UnitOfMeasure l");
		return 	q.setFirstResult(from).setMaxResults(10).getResultList();
		
	}

}
