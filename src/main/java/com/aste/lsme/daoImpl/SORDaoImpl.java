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

import com.aste.lsme.dao.SORDao;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.SOR;
import com.aste.lsme.domain.SORSearchCriteria;
import com.aste.lsme.domain.Workspace;

@Repository
public class SORDaoImpl implements SORDao{

	@PersistenceContext
	EntityManager em ;

	@Override
	public List<Equipment> getEqpName(String assetId,Workspace w) {
		 @SuppressWarnings("unchecked")
		 TypedQuery<Equipment> q = (TypedQuery<Equipment>) 
                 em.createQuery("Select  l from Equipment l where l.assetSubtype.assetType.assetTypeName=:assetId and l.workspace=:w");
		 q.setParameter("w",w);
		 q.setParameter("assetId", assetId);
		 return q.getResultList();
	}
	@Override

	public void save(SOR sor) throws Exception {	
		
			 em.persist(sor);
				

			
	}
	@Override
	public List<SOR> getSearchList(int from,SORSearchCriteria SORSearch,Workspace w) {	
		TypedQuery<SOR> SORList=getList(SORSearch,w);
		return SORList.setFirstResult(from).setMaxResults(10).getResultList();		
	}
	@Override
	public long count(SORSearchCriteria SORSearch,Workspace w) {	
		TypedQuery<SOR> SORList=getList(SORSearch,w);
		return (long) SORList.getResultList().size();
	}
	public TypedQuery<SOR> getList(SORSearchCriteria SORSearch,Workspace w) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<SOR> cq=cb.createQuery(SOR.class);
		Root<SOR> fromEE = cq.from(SOR.class);
		List<Predicate> predicates=new ArrayList<Predicate>();	
			
		
			if(!SORSearch.getItemCode().isEmpty())
			predicates.add(cb.equal(fromEE.get("itemCode"), SORSearch
					.getItemCode()));
		
			if(!SORSearch.getRate().isEmpty())
			predicates.add(cb.equal(fromEE.get("rate"),SORSearch
					.getRate()));
		
			if(!SORSearch.getUnit().isEmpty())
			predicates.add(cb.equal(fromEE.get("unit"), SORSearch
					.getUnit()));
		if (!SORSearch.getEquipmentType().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentType"),
								SORSearch.getEquipmentType()));		
		if(!SORSearch.getEquipmentName().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentName"),SORSearch.getEquipmentName()));
			predicates.add(cb.equal(fromEE.get("workspace"), w));
		    cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		return em.createQuery(cq);	
	}
	@Override
	public List<SOR> find(Long[] id) {	
		Query q = em.createQuery("select s from SOR s where s.id in :id")
				.setParameter("id", Arrays.asList(id));
		return q.getResultList();
	}
	@Override
	public boolean remove(SOR sor) {
		try{
			em.remove(em.contains(sor) ? sor : em.merge(sor));	 
			return true;
		} catch (Exception e) {
			return false;
			}
	}
	@Override
	public void update(SOR sor,Workspace w) throws Exception {
                 em.merge(sor);
			
           
	}
	@Override
	public List<SOR> getWorkspaceSor(Workspace w){	
	 @SuppressWarnings("unchecked")
	 	TypedQuery<SOR> q = (TypedQuery<SOR>)em.createQuery("Select s from SOR s where s.workspace=:w");
	 	q.setParameter("w",w);
		return q.getResultList();
	}
	@Override
	public SOR find(Long id) {
		return em.find(SOR.class, id);
	}
	@Override
	public List<AssetSubtype> getAssetSubtype(long assetId) {		
		TypedQuery<AssetSubtype> q = (TypedQuery<AssetSubtype>) 
                 em.createQuery("Select  l from AssetSubtype l where l.assetType.id=:assetId");
		q.setParameter("assetId", assetId);
		return q.getResultList();
	}
	@Override
	public List<AssetType> getAsset(Long id) {
		TypedQuery<AssetType> q = (TypedQuery<AssetType>) 
                em.createQuery("Select  l from AssetType l where l.id!=:id");
		q.setParameter("id", id);
		return q.getResultList();
	}	
}
