package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.MonitorClassDao;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.PredictiveMonitorClass;
import com.aste.lsme.domain.Workspace;

@Repository
public class MonitorClassDaoImpl implements MonitorClassDao{


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public boolean save(PredictiveMonitorClass monitorclass, Workspace w) {
		Query query = entityManager.createQuery("Select b from PredictiveMonitorClass b where b.className = :name and b.workspace = :w");
		query.setParameter("name", monitorclass.getClassName());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  entityManager.persist(monitorclass);
		 	return true;
		}
		else{
			return false;
		}
	}

	@Override
	public long getClassCount(Workspace w) {
		Query q = entityManager.createQuery("Select b from PredictiveMonitorClass b where b.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PredictiveMonitorClass> getPaginatedMonitor(int from, Workspace w) {
		Query q = entityManager.createQuery("Select b from PredictiveMonitorClass b where b.workspace =:w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public List<PredictiveMonitorClass> getMonitorClasses(Workspace w) {
		Query q = entityManager.createQuery("Select b from PredictiveMonitorClass b where b.workspace =:w").setParameter("w", w);
		return q.getResultList();

	}

	@Override
	public void delete(Long valueOf) {
		entityManager.remove(entityManager.find(PredictiveMonitorClass.class, valueOf));
		
	}

	@Override
	public PredictiveMonitorClass get(Long id) {
		return entityManager.find(PredictiveMonitorClass.class, id);
	}

	@Override
	public boolean update(PredictiveMonitorClass pm, Workspace w) {
		Query query = entityManager.createQuery("Select b from PredictiveMonitorClass b where b.id != :id and b.className = :name and b.workspace = :w ");
		query.setParameter("id", pm.getId());
		query.setParameter("name", pm.getClassName());
		query.setParameter("w",w);
		
		if(query.getResultList().isEmpty()){
			entityManager.merge(pm);
			 return true;
		}
		else{
				return false;
			}
	}
	
}
