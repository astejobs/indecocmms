package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PredictiveParameterDaoInterface;
import com.aste.lsme.domain.PredictiveParameter;
import com.aste.lsme.domain.ThrushHoldValue;



@Repository
@Transactional
public class PredictiveParameterDaoImpl implements PredictiveParameterDaoInterface {
	private EntityManager entitymanager;

	public EntityManager getEntitymanager() {
		return entitymanager;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	public List<PredictiveParameter> listAll() {
		TypedQuery<PredictiveParameter> d = (TypedQuery<PredictiveParameter>) entitymanager
				.createQuery("Select l from PredictiveParameter l");
		return d.getResultList();
	}

	@Override
	public boolean add(PredictiveParameter b) {

			entitymanager.persist(b);
		
		return true;	

	}

	@Override
	public void remove(PredictiveParameter p)  throws Exception{
		try{
			ThrushHoldValue th=entitymanager.find(ThrushHoldValue.class, p.getPredictiveMaint().getId());
			th.getParameterList().remove(p);
			p.setPredictiveMaint(null);
			entitymanager.merge(p);
		}catch(Exception e){
			
			e.printStackTrace();
		}

	}

	@Override
	public PredictiveParameter find(long id) {
		return entitymanager.find(PredictiveParameter.class, id);

	}

	@Override
	public PredictiveParameter update(PredictiveParameter b) {
		return entitymanager.merge(b);

	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		TypedQuery d = (TypedQuery) entitymanager
				.createQuery("Select count(l) from PredictiveParameter l ");		
		return (Long) d.getResultList().get(0);

	}
	@Override
	public List<PredictiveParameter> getPage(int from, int to) {
		// TODO Auto-generated method stub
		TypedQuery<PredictiveParameter> d = (TypedQuery<PredictiveParameter>) entitymanager
				.createQuery("Select c from PredictiveParameter c ");		
		 return d.setFirstResult(from).setMaxResults(to).getResultList();
	}
	@Override
	public int removeForPredictiveMaint(long predictId) throws Exception {
		try{
			TypedQuery<PredictiveParameter> d = (TypedQuery<PredictiveParameter>) entitymanager
					.createQuery("Delete from PredictiveParameter c where c.predictiveMaint.id=:pred");	
			d.setParameter("pred", predictId);
			 return d.executeUpdate();
		}catch(Exception e){
			return -1;
		}

	}

}
