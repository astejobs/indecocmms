package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.aste.lsme.dao.LabourRateDao;
import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.LabourRateSearchCriteria;
import com.aste.lsme.domain.Workspace;

@Repository
public class LabourRateDaoImpl implements LabourRateDao{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean persist(LabourRate labourrate, Workspace w) {
		Query query = em.createQuery("Select l from LabourRate l where l.itemCode = :itemCode and l.workspace = :w ");
		query.setParameter("itemCode", labourrate.getItemCode());
	//	query.setParameter("desc", labourrate.getDescription());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  em.persist(labourrate);
		
		 	return true;
		}
		else{
			return false;
		}
	}
	
	
	public List<LabourRate> get(Long[] id)
	{
		Query q = em.createQuery("select l from LabourRate l where l.id in :id")
				.setParameter("id", Arrays.asList(id));
		return q.getResultList();
	}

	@Override
	public LabourRate get(Long id) {
		return em.find(LabourRate.class, id);
	}

	@Override
	public void delete(Long id) {
		 em.remove(em.find(LabourRate.class, id));
		
	}

	@Override
	public boolean update(LabourRate labourrate, Workspace w) {
		Query query = em.createQuery("Select l from LabourRate l where l.itemCode = :itemCode and l.workspace = :w and l.id != :id ");
		query.setParameter("itemCode", labourrate.getItemCode());
		query.setParameter("id", labourrate.getId());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  em.merge(labourrate);
		
		 	return true;
		}
		else{
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabourRate> getWorkspaceLabourrate(Workspace w) {
		Query q = em.createQuery("Select l from LabourRate l where workspace =:w").setParameter("w", w);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabourRate> getAll() {
		Query q = em.createQuery("Select l from LabourRate l");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabourRate> getLabourratePaginated(int from, Workspace w) {
		Query q = em.createQuery("Select l from LabourRate l where l.workspace = :w").setParameter("w", w);
		return q.getResultList();
	}

	

	@Override
	public Long getlabourrateCount(Workspace w) {
		Query q = em.createQuery("Select l from LabourRate l where l.workspace = :w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@Override
	public long count(LabourRateSearchCriteria labourrateSearch, Workspace w) {
		TypedQuery<LabourRate> labour=createSearchCriteria(labourrateSearch,w);
		return labour.getResultList().size();
	}

	@Override
	public List<LabourRate> getSearchList(int from, LabourRateSearchCriteria labourrateSearch, Workspace w) {
		TypedQuery<LabourRate> query=createSearchCriteria(labourrateSearch,w);
		
		return query.setFirstResult(from).setMaxResults(10).getResultList();
	}
	
	private TypedQuery<LabourRate> createSearchCriteria(LabourRateSearchCriteria labourrateSearch,Workspace w) {
		
	System.out.println("In criteria builder");
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<LabourRate> cq=cb.createQuery(LabourRate.class);
		Root<LabourRate> fromEE = cq.from(LabourRate.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		if (labourrateSearch.getItemCode() != null)
			if (!labourrateSearch.getItemCode().isEmpty())
				predicates.add(cb.equal(fromEE.get("itemCode"),
						labourrateSearch.getItemCode()));
		
		
		
		if (labourrateSearch.getDescription() != null)
			if (!labourrateSearch.getDescription().isEmpty())
				predicates.add(cb.equal(fromEE.get("description"),
						labourrateSearch.getDescription()));
		
		
		
		
		
		if (labourrateSearch.getUnit() != null)
			if (!labourrateSearch.getUnit().isEmpty())
				predicates.add(cb.equal(fromEE.get("unit"),
						labourrateSearch.getUnit()));
		
		
		
		if (labourrateSearch.getRate() != null)
			if (!labourrateSearch.getRate().isEmpty())
				predicates.add(cb.equal(fromEE.get("rate"),
						labourrateSearch.getRate()));
		
		
		
	
	
		predicates.add(cb.equal(fromEE.get("workspace"), w));
		
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
	//	cq.orderBy(cb.desc(fromEE.get("building")), cb.desc(fromEE.get("name")));
		//cq.orderBy(cb.desc(fromEE.get("itemCode")));
		return em.createQuery(cq);
	}

}
