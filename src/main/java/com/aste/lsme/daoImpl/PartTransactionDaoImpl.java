package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PartTransactionDao;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.PartTransaction;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.domain.PartTransactionSearch;

@Repository
public class PartTransactionDaoImpl implements PartTransactionDao {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public void persist(PartTransaction partTransaction) {
			em.persist(partTransaction);
	}

	@Override
	public PartTransaction update(PartTransaction partTransaction) {
		return em.merge(partTransaction);
	}
	

	@Override
	public PartTransaction get(long id) {
		return em.find(PartTransaction.class, id);
	}

	@Override
	public void delete(long id) {
		em.remove(em.find(PartTransaction.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransaction> getReservedParts(String reportTaskId) {
		Query query = em.createQuery("Select p from PartTransaction p where p.reportTaskId = :id and p.status = :status ");
		query.setParameter("id", reportTaskId);
		query.setParameter("status", Constants.RESERVED);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransaction> getIssuedParts(String reportTaskId) {
		Query query = em.createQuery("Select p from PartTransaction p where p.reportTaskId = :id and (p.status = :status or p.status = :pissue)");
		query.setParameter("id", reportTaskId);
		query.setParameter("status", Constants.ISSUED);
		query.setParameter("pissue", Constants.PARTIAL_ISSUE);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransaction> getRecievedParts(String reportTaskId) {
		Query query = em.createQuery("Select p from PartTransaction p where p.reportTaskId = :id and p.status = :status ");
		query.setParameter("id", reportTaskId);
		query.setParameter("status", Constants.RECEIVED);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransaction> getAllReservedParts(List<Warehouse> warehouses) {
		Query query = em.createQuery("Select p from PartTransaction p where (p.status = :status or p.status = :pissue or p.partialIssue = true) and p.partsInWarehouse.warehouse IN (:warehouses)");
		query.setParameter("status", Constants.RESERVED);
		query.setParameter("pissue", Constants.PARTIAL_ISSUE);
		query.setParameter("warehouses", warehouses);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransaction> getAllReturnedParts(List<Warehouse> warehouses) {
		Query query = em.createQuery("Select p from PartTransaction p where p.status = :status and p.partsInWarehouse.warehouse IN (:warehouses)");
		query.setParameter("status", Constants.RETURNED);
		query.setParameter("warehouses", warehouses);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransaction> search(int from,PartTransactionSearch partConsumptionCriteria) {
		TypedQuery<PartTransaction> partList= getList(partConsumptionCriteria);
		return partList.setFirstResult(from).setMaxResults(10).getResultList();
		
	}
	
	public TypedQuery<PartTransaction> getList(PartTransactionSearch warehouseCriteria){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery cq=cb.createQuery(PartTransaction.class);
		Root<PartTransaction> root=cq.from(PartTransaction.class);
		List<Predicate> predicate= new ArrayList<Predicate>();
		
		if (warehouseCriteria.getWarehouseId()!=-1)
			predicate.add(cb.equal(root.get("partsInWarehouse").get("warehouse").get("id"),
					warehouseCriteria.getWarehouseId()));
	
		if (warehouseCriteria.getPartName() != null)
			if (!warehouseCriteria.getPartName().isEmpty())
				predicate.add(cb.equal(root.get("partsInWarehouse").get("part").get("partType").get("partName"),
						warehouseCriteria.getPartName()));
		
		Expression<Date> dateColumnPath = root.<Date> get("issuedDate");
		Expression<Date> date2 = root.<Date> get("issuedDate");
		if ((warehouseCriteria.getFrom() != null)) {
			predicate.add(cb.greaterThanOrEqualTo(dateColumnPath,
					warehouseCriteria.getFrom()));
		}
		if ((warehouseCriteria.getTo() != null)) {
			predicate.add(cb.lessThanOrEqualTo(date2,
					warehouseCriteria.getTo()));
		}
		

		if (warehouseCriteria.getReportTaskId() != null)
			if (!warehouseCriteria.getReportTaskId().isEmpty())
				predicate.add(cb.equal(root.get("getReportTaskId()"),
						warehouseCriteria.getPartName()));
		if (warehouseCriteria.getStatus() != null)
			if(warehouseCriteria.getStatus().length>0)
				predicate.add(cb.in(root.get("status")).value(Arrays.asList(warehouseCriteria.getStatus())));
		cq.select(root).where(predicate.toArray(new Predicate[] {}));
		return em.createQuery(cq);
		
		
	}

	@Override
	public long count(PartTransactionSearch partTransactionSearch) {
		TypedQuery<PartTransaction> partList= getList(partTransactionSearch);
		return partList.getResultList().size();
	}

	@Override
	public List<PartTransaction> search(PartTransactionSearch partConsumptionCriteria) {
		TypedQuery<PartTransaction> partList= getList(partConsumptionCriteria);
		return partList.getResultList();
	}

	

}
