package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.FaultCategoryDao;

import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.Workspace;

@Repository
public class FaultCategoryDaoImpl implements FaultCategoryDao {
@PersistenceContext
EntityManager em;

	@Override
	public boolean addFaultCategory(FaultCategory faultCategory,Workspace w) {
	
			Query q=em.createQuery("Select b from FaultCategory b where b.name = :name  and b.workspace = :w");
			q.setParameter("name",faultCategory.getName());	
			q.setParameter("w", w);
			//q.setParameter("description",faultCategory.getDescription());	
			
			if(q.getResultList().isEmpty())
			{
				em.persist(faultCategory);
				return true;
			}
			
			return false;
			}

	@Override
	public long getFaultCategoryCount(Workspace w) {
		Query q=em.createQuery("Select b from FaultCategory b where  b.workspace =:w").setParameter("w", w);
		return (long)	q.getResultList().size();
		
	}

	@Override
	public List<FaultCategory> getfaultCategoryPaginated(int from,Workspace w) {
		Query q=em.createQuery("Select b from FaultCategory b where  b.workspace =:w").setParameter("w", w);
		
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public FaultCategory get(Long id) {
		
		return em.find(FaultCategory.class, id);
	}
	@Override
	public boolean update(FaultCategory faultCategory,Workspace w) 
	{
		Query qu=em.createQuery("select b from FaultCategory b  where b.id != :id  and b.name = :name and b.workspace = :w ");
         qu.setParameter("id", faultCategory.getId());
         qu.setParameter("name",faultCategory.getName());
         qu.setParameter("w",w );
         if(qu.getResultList().isEmpty())
         {
        	 em.merge(faultCategory);
        	     return true;
         }
		return false;
	}		


	@Override
	public void delete(Long id) {
		em.remove(em.find(FaultCategory.class, id));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FaultCategory> getWorkspaceFaultCategory(Workspace w) {
		
		Query query = em.createQuery("Select f from FaultCategory f where f.workspace.workspaceId = :w ")
									.setParameter("w", w.getWorkspaceId());
		return query.getResultList();
	}

	@Override
	public List<FaultCategory> getfaultCategories(Long workspaceId) {
		Query query = em.createQuery("Select f from FaultCategory f where f.workspace.id = :w ")
				.setParameter("w", workspaceId);
		return query.getResultList();
	}

}
