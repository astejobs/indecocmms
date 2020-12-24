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

import com.aste.lsme.dao.PartTransferDao;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.PartTransfer;
import com.aste.lsme.domain.PartTransferSearch;
import com.aste.lsme.domain.Warehouse;
@Repository
public class PartTransferDaoImpl implements PartTransferDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void persist(PartTransfer partTransfer) {
		em.persist(partTransfer);
	}

	@Override
	public PartTransfer update(PartTransfer partTransfer) {
		return em.merge(partTransfer);
	}

	@Override
	public void delete(long id) {
		em.remove(em.find(PartTransfer.class, id));
	}

	@Override
	public PartTransfer get(long id) {
		return em.find(PartTransfer.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransfer> getReservedParts(List<Warehouse> warehouses) {
		Query query = em.createQuery("Select p from PartTransfer p where p.requestor IN(:warehouses) and p.status = :reserved ");
		query.setParameter("warehouses", warehouses);
		query.setParameter("reserved", Constants.RESERVED);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransfer> getIssuingParts(List<Warehouse> warehouses) {
		Query query = em.createQuery("Select p from PartTransfer p where p.issuer IN(:warehouses) and p.status = :reserved ");
		query.setParameter("warehouses", warehouses);
		query.setParameter("reserved", Constants.RESERVED);
		return query.getResultList();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartTransfer> getRecievedParts(List<Warehouse> warehouses) {
		Query query = em.createQuery("Select p from PartTransfer p where p.requestor IN(:warehouses) and p.status = :issued ");
		query.setParameter("warehouses", warehouses);
		query.setParameter("issued", Constants.ISSUED);
		return query.getResultList();
	}


	
	
	
	public TypedQuery<PartTransfer> getList(PartTransferSearch partTransferSearch){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<PartTransfer> cq=cb.createQuery(PartTransfer.class);
		Root<PartTransfer> root=cq.from(PartTransfer.class);
		List<Predicate> predicate= new ArrayList<Predicate>();
		
		if (partTransferSearch.getSource()!=-1)
			predicate.add(cb.equal(root.get("issuer").get("id"),
					partTransferSearch	.getSource()));
		
		if(partTransferSearch.getDestination()!=-1)
			predicate.add(cb.equal(root.get("requestor").get("id"),
					partTransferSearch.getDestination()));
		
		if (partTransferSearch.getPartName() != null)
			if (!partTransferSearch.getPartName().isEmpty())
				predicate.add(cb.equal(root.get("partsInWarehouse").get("part").get("partType").get("partName"),
						partTransferSearch.getPartName()));
		
		Expression<Date> dateColumnPath = root.<Date> get("reservedDate");
		Expression<Date> date2 = root.<Date> get("recievedDate");
		if ((partTransferSearch.getFrom() != null)) {
			predicate.add(cb.greaterThanOrEqualTo(dateColumnPath,
					partTransferSearch.getFrom()));
		}
		if ((partTransferSearch.getTo() != null)) {
			predicate.add(cb.lessThanOrEqualTo(date2,
					partTransferSearch.getTo()));
		}
		if(partTransferSearch.getStatus() != null)
			if(partTransferSearch.getStatus().length > 0)
			 predicate.add(cb.in(root.get("status")).value(Arrays.asList(partTransferSearch.getStatus())));
		
		cq.select(root).where(predicate.toArray(new Predicate[] {}));
		return em.createQuery(cq);
		
		
	}

	
	@Override
	public List<PartTransfer> search(int from, PartTransferSearch partTransferSearch) {
		return getList(partTransferSearch).setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public long count(PartTransferSearch partTransactionSearch) {
		return getList(partTransactionSearch).getResultList().size();

	}
	@Override
	public List<PartTransfer> getAll(PartTransferSearch partTransferSearch) {
		return getList(partTransferSearch).getResultList();
	}
	
}
