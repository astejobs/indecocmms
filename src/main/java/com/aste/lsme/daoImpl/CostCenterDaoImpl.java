package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.CostCenterDao;
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.TechnicianDTO;
@Repository
public class CostCenterDaoImpl implements CostCenterDao{
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean addCostCenter(CostCenter costCenter,Workspace w) {
		Query q=em.createQuery("Select b from CostCenter b where b.costCenterName= :name  and b.workspace = :w");
	
		q.setParameter("name", costCenter.getCostCenterName());
		q.setParameter("w", w);

		if(q.getResultList().isEmpty())
		{
			em.persist(costCenter);
			return true;
		}
		else
		{
		  return false;
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CostCenter> getcostCenter() {
		Query q = em.createQuery("Select l from CostCenter l");
		return q.getResultList();
	
	}

	@Override
	public CostCenter get(Long id) {
	
		return em.find(CostCenter.class, id);
	}

	@Override
	public boolean updatecostCenter(CostCenter costCenter,Workspace w) {
		Query q=em.createQuery("Select b from CostCenter b where  b.id !=:id and b.costCenterName= :name and b.workspace = :w ");
	
		q.setParameter("name", costCenter.getCostCenterName());
	    q.setParameter("id", costCenter.getId());
		q.setParameter("w", w);
		

		if(q.getResultList().isEmpty())
		{
			em.merge(costCenter);
			return true;
		}
		else
		{
		  return false;
		}

	}

	@Override
	public void delete(Long id) {
		em.remove(em.find(CostCenter.class, id));
		
	}

	@Override
	public long getCostCenterCount(Workspace w) {
		Query q = em.createQuery("Select b from CostCenter b where b.workspace = :w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CostCenter> getCostCenterPaginated(int from,Workspace w) {
		Query q = em.createQuery("Select b from CostCenter b where b.workspace = :w").setParameter("w", w) ;
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CostCenter> getWorkspaceCostCenter(Workspace w) {
		Query q = em.createQuery("Select b from CostCenter b where b.workspace = :w").setParameter("w", w) ;
		return q.getResultList();
	}

	@Override
	public List<CostCenter> findCostCenter(String w) {
	   Query q=em.createQuery("Select c from CostCenter c where c.workspace.workspaceId= :w").setParameter("w", w);
		return q.getResultList();
	}

}
