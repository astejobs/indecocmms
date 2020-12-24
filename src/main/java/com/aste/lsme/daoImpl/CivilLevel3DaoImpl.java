package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.CivilLevel3Dao;
import com.aste.lsme.domain.CivilLevel3;
import com.aste.lsme.domain.Workspace;



@Repository
public class CivilLevel3DaoImpl implements CivilLevel3Dao {
    
	private EntityManager em;

	public EntityManager getEntitymanager() {
		return em;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager em) {
		this.em = em;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<CivilLevel3> getCivilLevel3Paginated(int from,Workspace w){
		Query q = em.createQuery("Select b from CivilLevel3 b where b.workspace =:w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}
	
	
	@Override
	public long count(Workspace w) {
		Query q = em.createQuery("Select c from CivilLevel3 c where c.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@Override
	public boolean add(CivilLevel3 c,Workspace w) 
	{
		
		  Query d =  em.createQuery("Select l from  CivilLevel3 l where l.civilLevel3Name = :name and l.workspace= :w ");
		  d.setParameter("name", c.getCivilLevel3Name());
		  d.setParameter("w",w);
		  if(d.getResultList().isEmpty())
		  {
			  em.persist(c);
			  return true;
		  }
		  else
			    return false;
		  	  		
	}

	@Override
	public void remove(Long id) {
		 em.remove(em.find(CivilLevel3.class, id));
		
	}

	@Override
	public CivilLevel3 find(Long id) {
		 return em.find(CivilLevel3.class, id);
	}

	@Override
	public boolean update(CivilLevel3 c,Workspace w) 
	{
		@SuppressWarnings("unchecked")
		TypedQuery<CivilLevel3> query=(TypedQuery<CivilLevel3>) 	
		em.createQuery("Select c from CivilLevel3 c where c.civilLevel3Name = :name and c.id != :id and c.workspace = :w ");		
		query.setParameter("name", c.getCivilLevel3Name());
		query.setParameter("w",w);
		
		 query.setParameter("id", c.getId());
		    
		    if(query.getResultList().isEmpty())
			{
				em.merge(c);
				return true;
			}
			
			return false;
		
			}


	@Override
	public List<CivilLevel3> getAll(Workspace w) {
		@SuppressWarnings("unchecked")
		TypedQuery<CivilLevel3> d = (TypedQuery<CivilLevel3>) em
				.createQuery("Select d from CivilLevel3 d where d.workspace= :w").setParameter("w", w);
		return d.getResultList();
	}

	@Override
	public List<CivilLevel3> getCivillevel3(long type) {
		@SuppressWarnings("unchecked")
		TypedQuery<CivilLevel3> d = (TypedQuery<CivilLevel3>) em
				.createQuery("Select d from CivilLevel3 d where d.assetSubType.id=:type");
		d.setParameter("type", type);
		System.out.println(d.getResultList().size()+"0000000000000000");
		return d.getResultList();
	}
	
}
