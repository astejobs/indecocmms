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

import com.aste.lsme.dao.AcmvDaoInterface;
import com.aste.lsme.domain.ACMV;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;

@Repository
public class AcmvDaoImpl implements AcmvDaoInterface {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public ACMV save(ACMV acmv) {
		// TODO Auto-generated method stub
		entityManager.persist(acmv);
		return acmv;
		
	}

	@Override
	public Boolean update(ACMV acmv) {
		// TODO Auto-generated method stub
		entityManager.merge(acmv);
		return null;
	}

	@Override
	public ACMV delete(ACMV acmv) {
		// TODO Auto-generated method stub
		try
		{
		  entityManager.remove(entityManager.contains(acmv) ? acmv:entityManager.merge(acmv));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return acmv;
	}

	@Override
	public ACMV find(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(ACMV.class,id);
	}

	@Override
	public ACMV find(Long id, Workspace workspace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateEquipmentCode(String type) {
		// TODO Auto-generated method stub
		Query d=entityManager.createQuery("select max(l.id) from Equipment l",Long.class);
		     
		
		return type+(d.getResultList().get(0))+ 1;
	}

	@Override
	public List<ACMV> getSearchList(int from,EquipmentSearchCriteria acmvSearchCriteria) {
		// TODO Auto-generated method stub
		TypedQuery<ACMV> query=createSearchCriteria(acmvSearchCriteria);
		
		
		return  query.setFirstResult(from).setMaxResults(10).getResultList();
	}

	private TypedQuery<ACMV> createSearchCriteria(EquipmentSearchCriteria acmvSearchCriteria) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<ACMV> cq=cb.createQuery(ACMV.class);
		Root<ACMV> fromEE = cq.from(ACMV.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		if(acmvSearchCriteria.getWorkspace()!=null)
		predicates.add(cb.equal(fromEE.get("workspace").get("id"),acmvSearchCriteria.getWorkspace().getId()));
		
		
			if (!(acmvSearchCriteria.getServingArea()==null))
			predicates.add(cb.equal(fromEE.get("equipmentType"),acmvSearchCriteria.getEquipmentType()));
		
		
		if (acmvSearchCriteria.getBuilding() != null)
			predicates.add(cb.equal(fromEE.get("building").get("id"), acmvSearchCriteria
					.getBuilding().getId()));
		if (acmvSearchCriteria.getAssetSubtype() != null)
			predicates.add(cb.equal(fromEE.get("assetSubtype").get("id"),acmvSearchCriteria
					.getAssetSubtype().getId()));
		if (acmvSearchCriteria.getLocation() != null)
			predicates.add(cb.equal(fromEE.get("location").get("id"), acmvSearchCriteria
					.getLocation().getId()));
		
		if(acmvSearchCriteria.getEquipmentCode()!=null)
			if(!acmvSearchCriteria.getEquipmentCode().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentCode"),acmvSearchCriteria.getEquipmentCode()));
			
			
		

		if (!(acmvSearchCriteria.getServingArea()== null))
           	predicates.add(cb.equal(fromEE.get("servingArea"),
						acmvSearchCriteria.getServingArea()));
			
			
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromEE.get("building")), cb.desc(fromEE.get("name")));
		return entityManager.createQuery(cq);
	}

	@Override
	public void updateEquimentBaseLine(ACMV acmv) {
		// TODO Auto-generated method stub
		//Query query = entityManager.createNativeQuery("DELETE FROM Equipment.Baseline where equipment.id=:acmv.id").setParameter("acmv.id",acmv.getId());
		//query.executeUpdate();
		
		
	}

	@Override
	public Long getAcmvCount(Workspace w) {
		Query q = entityManager.createQuery("Select b from ACMV b where b.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

}
