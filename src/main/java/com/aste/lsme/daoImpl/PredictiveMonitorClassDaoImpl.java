package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PredictiveMonitorClassDaoInterface;
import com.aste.lsme.domain.PredictiveMonitorClass;



@Repository
@Transactional
public class PredictiveMonitorClassDaoImpl implements PredictiveMonitorClassDaoInterface {
	private EntityManager entitymanager;

	public EntityManager getEntitymanager() {
		return entitymanager;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	public List<PredictiveMonitorClass> listAll() {
		TypedQuery<PredictiveMonitorClass> d = (TypedQuery<PredictiveMonitorClass>) entitymanager
				.createQuery("Select l from PredictiveMonitorClass l");
		return d.getResultList();
	}

	@Override
	public boolean add(PredictiveMonitorClass b) {

			entitymanager.persist(b);
		
		return true;	

	}

	@Override
	public void remove(long id)  throws Exception{
		try{
		entitymanager.remove(entitymanager.find(PredictiveMonitorClass.class, id));
		}catch(Exception e){
			
		}

	}

	
	@Override
	public PredictiveMonitorClass find(long id) {
		return entitymanager.find(PredictiveMonitorClass.class, id);

	}

	@Override
	public PredictiveMonitorClass update(PredictiveMonitorClass b) {
		return entitymanager.merge(b);

	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		TypedQuery d = (TypedQuery) entitymanager
				.createQuery("Select count(l) from PredictiveMonitorClass l ");		
		return (Long) d.getResultList().get(0);

	}
	@Override
	public List<PredictiveMonitorClass> getPage(int from, int to) {
		// TODO Auto-generated method stub
		TypedQuery<PredictiveMonitorClass> d = (TypedQuery<PredictiveMonitorClass>) entitymanager
				.createQuery("Select c from PredictiveMonitorClass c ");		
		 return d.setFirstResult(from).setMaxResults(to).getResultList();
	}


	

}
