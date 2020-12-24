package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.CivilLevel4Dao;
import com.aste.lsme.domain.CivilLevel4;
import com.aste.lsme.domain.Workspace;



@Repository
public class CivilLevel4DaoImpl implements CivilLevel4Dao {
	
	@PersistenceContext
	EntityManager em;

	
   public long count(Workspace w) {
		
		Query q = em.createQuery("Select c from CivilLevel4 c where c.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();

	}
   
   @Override
   public boolean add(CivilLevel4 c,Workspace w)
   {
	   Query d =  em.createQuery("Select l from  CivilLevel4 l where l.civilLevel4Name = :name and l.workspace= :w ");
		  d.setParameter("name", c.getCivilLevel4Name());
		  d.setParameter("w",w);
		  if(d.getResultList().isEmpty()){
			  em.persist(c);
			  return true;
		  }
		  else{
			  
			  return false;
		  }	
		  
   }
   
   public void remove(Long id)
   {
	 
	   em.remove(em.find(CivilLevel4.class, id));
   }
   
   
   public CivilLevel4 find(Long id)
   {
	   return em.find(CivilLevel4.class, id);
   }
   
   
   public boolean update(CivilLevel4 c,Workspace w)
   {
		
	    @SuppressWarnings("unchecked")
		TypedQuery<CivilLevel4> query = (TypedQuery<CivilLevel4>)em.createQuery("Select c from CivilLevel4 c where c.id != :id and c.civilLevel4Name = :name and c.workspace = :w ");
		query.setParameter("name", c.getCivilLevel4Name());
		query.setParameter("w",w);
	    query.setParameter("id", c.getId());
	    
	    if(query.getResultList().isEmpty())
		{
			em.merge(c);
			return true;
		}
		
		return false;
		}
			
   
   
   
   @SuppressWarnings("unchecked")
	@Override
	public List<CivilLevel4> getCivilLevel4Paginated(int from,Workspace w){
		Query q = em.createQuery("Select c from CivilLevel4 c where c.workspace =:w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}
   
   
   public List<CivilLevel4> getAll(Workspace w)
   {
	   
		@SuppressWarnings("unchecked")
		TypedQuery<CivilLevel4> d = (TypedQuery<CivilLevel4>) em
				.createQuery("Select d from CivilLevel4 d where d.workspace= :w").setParameter("w", w);
		return d.getResultList();
   }
   
  @SuppressWarnings("unchecked")
  @Override
  public List<CivilLevel4> getAll(long id){
	   
	   TypedQuery<CivilLevel4> d = (TypedQuery<CivilLevel4>) em
				.createQuery("Select d from CivilLevel4 d where d.civilLevel3.id=:type");
		d.setParameter("type", id);
		return d.getResultList();
   }
   
}
