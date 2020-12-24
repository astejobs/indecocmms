package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.ElectricalDao;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Electrical;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;


@Repository

public class ElectricalDaoImpl implements ElectricalDao {

	
	@PersistenceContext
	private	EntityManager em;
	
	@Override
	public boolean persist(Electrical electrical,Workspace w) {
  
		Query query = em.createQuery("Select f from Equipment f where (f.name = :name)"
				+ " and f.assetSubtype=:subtype and f.workspace = :w");
		
		   // query.setParameter("name",  electrical.getName());
		   // query.setParameter("assetNo",electrical.getAssetNo());
		   // query.setParameter("subtype",  electrical.getAssetSubtype());
		    query.setParameter("w",  w);
		    
		      
		  if(query.getResultList().isEmpty()){
		    	 em.persist(electrical);
		        return true;
		    }
		    else{
		    	return false;
		      }
		  }
		
    @Override
	public void delete(Long id) {
		em.remove(em.find(Electrical.class, id));
	}

	@Override
	public boolean update(Electrical electrical,Workspace w) {
		
		Query query = em.createQuery("Select f from Equipment f where (f.name = :name) and f.assetSubtype=:subtype and f.workspace = :w and f.id != :id");
	  //  query.setParameter("id", electrical.getId());
	  //  query.setParameter("name", electrical.getName());
	  //  query.setParameter("assetNo",electrical.getAssetNo());
	  //  query.setParameter("subtype", electrical.getAssetSubtype());
	    query.setParameter("w", w);
	    
	    
	    if(query.getResultList().isEmpty()){
	    	em.merge(electrical);
	        return true;
	    }
	    else{
	      return false;
	      }
	  }
	

	
	@Override
	public List<Electrical> getAll() {
		Query q = em.createQuery("Select b from Electrical b");
		return q.getResultList();
	}


	
	@Override
	public Electrical find(long id) {
		
		return  em.find(Electrical.class,id);
	}


	@Override
	public long getElectricalCount(EquipmentSearchCriteria electricalSearch,Workspace w) {
		TypedQuery<Electrical> query=createSearchCriteria(electricalSearch,w);
		return	query.getResultList().size();
	}


    @Override
	public List<Electrical> getSearchList(int from,EquipmentSearchCriteria electricalSearch,Workspace w) {
		TypedQuery<Electrical> query=createSearchCriteria(electricalSearch,w);
		
		return query.setFirstResult(from).setMaxResults(10).getResultList();
	}


	private TypedQuery<Electrical> createSearchCriteria(EquipmentSearchCriteria electricalSearch,Workspace w) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Electrical> cq=cb.createQuery(Electrical.class);
		Root<Electrical> fromEE = cq.from(Electrical.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		if (electricalSearch.getServingArea() != null)
			if (!electricalSearch.getServingArea().isEmpty())
				predicates.add(cb.equal(fromEE.get("servingArea"),
						electricalSearch.getServingArea()));
		if (electricalSearch.getName() != null)
			if (!electricalSearch.getName().isEmpty())
				predicates.add(cb.equal(fromEE.get("name"),
						electricalSearch.getName()));
		
		
		if(electricalSearch.getEquipmentType()!=null)
			if (!electricalSearch.getServingArea().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentType"),electricalSearch.getEquipmentType()));
		
		
		if (electricalSearch.getBuilding() != null)
			predicates.add(cb.equal(fromEE.get("building").get("id"), electricalSearch
					.getBuilding().getId()));
		if (electricalSearch.getAssetSubtype() != null)
			predicates.add(cb.equal(fromEE.get("assetSubtype").get("id"),electricalSearch
					.getAssetSubtype().getId()));
		if (electricalSearch.getLocation() != null)
			predicates.add(cb.equal(fromEE.get("location").get("id"), electricalSearch
					.getLocation().getId()));
		
		if(electricalSearch.getEquipmentCode()!=null)
			if(!electricalSearch.getEquipmentCode().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentCode"),electricalSearch.getEquipmentCode()));
			
		predicates.add(cb.equal(fromEE.get("workspace"), w));
			
			
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromEE.get("building")), cb.desc(fromEE.get("name")));
		return em.createQuery(cq);
	}

	
	
}

	
	



