package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PartDao;
import com.aste.lsme.domain.Part;


@Repository
public class PartDaoImpl implements PartDao {
	
	@PersistenceContext
	EntityManager em;
	
	
	
	
	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public boolean save(Part part) {
		
			Query q=em.createQuery("Select p from Part p where p.partType=:parttype and p.description=:description and p.manufacturer=:manufacturer and p.vendor=:vendor");
			q.setParameter("parttype", part.getPartType());
			q.setParameter("description", part.getDescription());
			q.setParameter("manufacturer", part.getManufacturer());
			q.setParameter("vendor", part.getVendor());
			if(q.getResultList().isEmpty()){
				
			
			em.persist(part);
			
			return true;
			}
		
			
			return false;

		
		
	}


	@Override
	public long getCount() {
		Query q=em.createQuery("Select p from Part p");
		return q.getResultList().size();
	}


	@Override
	public List<Part> getPartPaginated(int from) {
		Query q=em.createQuery("Select p from Part p");

		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}


	@Override
	public Part gePart(Long id) {
		return em.find(Part.class, id);
	}


	@Override
	public boolean update(Part part) {
		Query q=em.createQuery("Select p from Part p where p.partType=:parttype and p.description=:description and p.manufacturer=:manufacturer and p.vendor=:vendor and p.id!=:id");
		q.setParameter("parttype", part.getPartType());
		q.setParameter("description", part.getDescription());
		q.setParameter("manufacturer", part.getManufacturer());
		q.setParameter("vendor", part.getVendor());
		q.setParameter("id",part.getId());
		if(q.getResultList().isEmpty()){
		em.merge(part);
		return true;
		}
			return false;
		
	}


	@Override
	public boolean remove(long id) {
   try{
	   Part part=em.find(Part.class, id);
  em.remove(part);
  System.out.println("after deletion");
  return true;
   }catch (Exception e) {
	   e.printStackTrace();
	   return false;
}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getAll(){
		Query q=em.createQuery("Select p from Part p");
		return q.getResultList();
	}

}
