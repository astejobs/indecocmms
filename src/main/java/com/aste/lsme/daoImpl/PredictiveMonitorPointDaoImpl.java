package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PredictiveMonitorPointDaoInterface;
import com.aste.lsme.domain.PredictiveMonitorPoint;
import com.aste.lsme.domain.ThrushHoldValue;



@Repository
@Transactional
public class PredictiveMonitorPointDaoImpl implements PredictiveMonitorPointDaoInterface {
	private EntityManager entitymanager;

	public EntityManager getEntitymanager() {
		return entitymanager;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	public List<PredictiveMonitorPoint> listAll() {
		TypedQuery<PredictiveMonitorPoint> d = (TypedQuery<PredictiveMonitorPoint>) entitymanager
				.createQuery("Select l from PredictiveMonitorPoints l");
		return d.getResultList();
	}

	@Override
	public boolean add(PredictiveMonitorPoint b) {

			entitymanager.persist(b);
		
		return true;	

	}

	@Override
	public void remove(PredictiveMonitorPoint mp)  throws Exception{
		try{
			ThrushHoldValue th= entitymanager.find(ThrushHoldValue.class, mp.getPredictiveMaint().getId());
			th.getMonitorPointList().remove(mp);
			mp.setPredictiveMaint(null);
			entitymanager.merge(mp);
		}catch(Exception e){
			
		}

	}

	@Override
	public PredictiveMonitorPoint find(long id) {
		return entitymanager.find(PredictiveMonitorPoint.class, id);

	}

	@Override
	public PredictiveMonitorPoint update(PredictiveMonitorPoint b) {
		return entitymanager.merge(b);

	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		TypedQuery d = (TypedQuery) entitymanager
				.createQuery("Select count(l) from PredictiveMonitorPoints l ");		
		return (Long) d.getResultList().get(0);

	}
	
	@Override
	public List<PredictiveMonitorPoint> getPage(int from, int to) {
		// TODO Auto-generated method stub
		TypedQuery<PredictiveMonitorPoint> d = (TypedQuery<PredictiveMonitorPoint>) entitymanager
				.createQuery("Select c from PredictiveMonitorPoints c ");		
		 return d.setFirstResult(from).setMaxResults(to).getResultList();
	}

	@Override
	public int removeForPredictiveMaint(long predictId) throws Exception {
		try{
			System.out.println("In delete for predictmm mpont");
			TypedQuery<PredictiveMonitorPoint> d = (TypedQuery<PredictiveMonitorPoint>) entitymanager
					.createQuery("Delete from PredictiveMonitorPoint c where c.predictiveMaint.id=:pred");	
			d.setParameter("pred", predictId);
			 return d.executeUpdate();
		}catch(Exception e){
			return -1;
		}

	}

	

}
