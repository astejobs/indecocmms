package com.aste.lsme.daoImpl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.VendorServiceDao;
import com.aste.lsme.domain.Vendor;

@Repository
public class VendorServiceDaoImpl implements VendorServiceDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean saveVendor(Vendor v)
	{
		
		
		Query query = em.createQuery("Select v from Vendor v where v.vendorName = :name or v.phoneNumber= :pno or v.email= :email or v.faxNumber= :fax");
		query.setParameter("name", v.getVendorName());
		query.setParameter("pno", v.getPhoneNumber());
		query.setParameter("email", v.getEmail());
		query.setParameter("fax", v.getFaxNumber());
		if(query.getResultList().isEmpty())
		{
		  em.persist(v);
		 	return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public long getCount() {
		Query q = em.createQuery("Select v from Vendor v");
		return (long) q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getPaginated(int from) 
	{
		Query q = em.createQuery("Select v from Vendor v") ;
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Vendor getVendor(long id) {
		
		return em.find(Vendor.class, id);
	}

	@Override
	public boolean update(Vendor v) {
		
		Query query = em.createQuery("Select v from Vendor v where v.id != :id and (v.vendorName = :name or v.phoneNumber= :pno or v.email= :email or v.faxNumber= :fax)");
		query.setParameter("id", v.getId());
		query.setParameter("name", v.getVendorName());
		query.setParameter("pno", v.getPhoneNumber());
		query.setParameter("email", v.getEmail());
		query.setParameter("fax", v.getFaxNumber());
		if(query.getResultList().isEmpty())
		{
		  em.merge(v);
		 	return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void delete(Long id) {
		
		em.remove(em.find(Vendor.class, id));
	}


	@Override
	public List<Vendor> getAll() {
		Query q = em.createQuery("Select v from Vendor v") ;
		return q.getResultList();
	}


}
