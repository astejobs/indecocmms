package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PartsInWarehouseDao;
import com.aste.lsme.domain.InventoryPartSearch;
import com.aste.lsme.domain.Part;
import com.aste.lsme.domain.PartBatch;
import com.aste.lsme.domain.PartsInWarehouse;
import com.aste.lsme.domain.Warehouse;

@Repository
public class PartsInWarehouseDaoImpl implements PartsInWarehouseDao {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public void persist(PartsInWarehouse partsInWarehouse) {
		em.persist(partsInWarehouse);
	}

	@Override
	public PartsInWarehouse get(long id) {
		return em.find(PartsInWarehouse.class, id);
	}

	@Override
	public void update(PartsInWarehouse partsInWarehouse) {
		em.merge(partsInWarehouse);
	}

	@Override
	public void delete(long id) {
		em.remove(em.find(PartsInWarehouse.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartsInWarehouse> getPartsInWarehouse(long id) {

		Query query = em.createQuery("Select p from PartsInWarehouse p where p.warehouse.id = :id ").setParameter("id", id);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Part> getParts(long id) {
	
		Query query = em.createQuery("Select p.part from PartsInWarehouse p where p.warehouse.id = :id ").setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public boolean checkBatchNo(PartBatch partBatch){
		Query query = em.createQuery("Select b from PartBatch b where b.batchNo = :batchNo and b.partsInWarehouse.id = :id ");
		query.setParameter("batchNo", partBatch.getBatchNo());
		query.setParameter("id", partBatch.getPartsInWarehouse().getId());
		
		if(!query.getResultList().isEmpty())
			return true;
		
		return false;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PartsInWarehouse> getAll(){
		Query query = em.createQuery("Select p from PartsInWarehouse p");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PartsInWarehouse> getPartsNotInWarehouse(long id){
		Query query = em.createQuery("Select p from PartsInWarehouse p where p.warehouse.id != :id ").setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	public PartsInWarehouse findPartInWarehouse(Part part,Warehouse warehouse){
		Query query = em.createQuery("Select p from PartsInWarehouse p where p.warehouse = :warehouse and p.part = :part ");
		query.setParameter("part", part);
		query.setParameter("warehouse", warehouse);
		try{
		  return (PartsInWarehouse) query.getSingleResult();
		}
	    catch (Exception e) {
	    	return null;
	    }
	 }

	@Override
	public List<PartsInWarehouse> searchPartsInWareHouse(InventoryPartSearch inPartSearch) {

		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PartsInWarehouse> cq = cb.createQuery(PartsInWarehouse.class);
		Root<PartsInWarehouse> fromPart = cq.from(PartsInWarehouse.class);
		
		Join<PartsInWarehouse, PartBatch> pwjoin = fromPart.join("partBatchs");
		
		List<Predicate> predicateList = getPredicate(inPartSearch,cb,pwjoin,fromPart);
		
		cq.where(cb.and(predicateList.toArray(new Predicate[0])));
		
		cq.distinct(true);
		return em.createQuery(cq).getResultList();
		
	}
	
	public List<Predicate>  getPredicate(InventoryPartSearch inPartSearch,CriteriaBuilder cb,Join<PartsInWarehouse, PartBatch> pwjoin,Root<PartsInWarehouse> fromPart )
	{
		
		List<Predicate> pList = new ArrayList<Predicate>();
		
		System.err.println(inPartSearch.getWarehouse()+"-------------");
		
		if(inPartSearch.getWarehouse() != null){
			pList.add(cb.equal(fromPart.get("warehouse").get("id"),(long)inPartSearch.getWarehouse()));
		}
		
		if(inPartSearch.getPartName() != null)
			if(!inPartSearch.getPartName().isEmpty())
				pList.add(cb.equal(fromPart.get("part").get("partType").get("partName"), inPartSearch.getPartName()));
		
		if(inPartSearch.getBatchNumber()!=null)
			if(!inPartSearch.getBatchNumber().isEmpty())
				pList.add(cb.equal(pwjoin.get("partBatchs").get("batchNo"),inPartSearch.getBatchNumber()));
		
		if(inPartSearch.getQuantity()!=null)
				cb.equal(pwjoin.get("partBatchs").get("quantity"), inPartSearch.getQuantity());
		
		if(inPartSearch.getManufacturer()!=null)
			pList.add(cb.equal(fromPart.get("part").get("manufacturer").get("name"), inPartSearch.getManufacturer()));
			
		if(inPartSearch.getVendor()!=null)
			pList.add(cb.equal(fromPart.get("part").get("vendor").get("vendorName"), inPartSearch.getVendor()));
		
		
		return pList;

		
	}
	
	
	
	}
