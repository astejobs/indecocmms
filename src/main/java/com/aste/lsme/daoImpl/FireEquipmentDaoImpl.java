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

import com.aste.lsme.dao.FireEquipmentDao;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Fire;
import com.aste.lsme.domain.Workspace;

@Repository
public class FireEquipmentDaoImpl implements FireEquipmentDao{

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public boolean persist(Fire fireequipment) {
		
		//Workspace w=fireequipment.getWorkspace();
	
		Query query = em.createQuery("Select f from Equipment f where (f.name = :name) and f.assetSubtype=:subtype and f.workspace = :w ");
		//query.setParameter("name", fireequipment.getName());
		//query.setParameter("assetNo", fireequipment.getAssetNo());
		//query.setParameter("subtype", fireequipment.getAssetSubtype());
		//query.setParameter("w", w);
			
			if(query.getResultList().isEmpty()){
			  em.persist(fireequipment);
			
			 	return true;
			}
			else{
				return false;
			}
			

	}


	@Override
	public Fire get(Long id) {
		 return em.find(Fire.class, id);
	}

	@Override
	public boolean update(Fire fireequipment) {
		
		//Workspace w=fireequipment.getWorkspace();
		Query query = em.createQuery("Select f from Equipment f where (f.name = :name) and f.id != :id  and f.assetSubtype=:subtype and f.workspace = :w");
		//query.setParameter("id", fireequipment.getId());
		//query.setParameter("name", fireequipment.getName());
		//query.setParameter("assetNo", fireequipment.getAssetNo());
		//query.setParameter("subtype", fireequipment.getAssetSubtype());
		//query.setParameter("w", w);
	
			if(query.getResultList().isEmpty())
			{
				em.merge(fireequipment);
			 	return true;
			}
			else
			{
				return false;
			}

	}

	@Override
	public List<Fire> getWorkspaceFireequipments(Workspace w) {
		Query q = em.createQuery("Select f from Equipment f where workspace =:w").setParameter("w", w);
		return q.getResultList();
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Fire> getAll() {
		Query q = em.createQuery("Select f from Equipment f where f.equipmentType=:type");
		q.setParameter("type", Constants.FIRESUBSYSTEM);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fire> getfireequipmentsPaginated(int from) {
		Query q = em.createQuery("Select f from Equipment f where f.equipmentType=:type");
		q.setParameter("type", Constants.FIRESUBSYSTEM);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getfireequipmentsCount() {
		Query q = em.createQuery("Select f from Equipment f where f.equipmentType=:type");
		q.setParameter("type", Constants.FIRESUBSYSTEM);
		return (long) q.getResultList().size();
	}

	@Override
	public boolean delete(Fire fire) {
		
		em.remove(em.contains(fire)?fire:em.merge(fire));
		
		return true;
	}


	@Override
	public List<Fire> getSearchList(int from,EquipmentSearchCriteria fireSearchcriteria,Workspace w) {
		
        TypedQuery<Fire> query=createSearchCriteria(fireSearchcriteria,w);
		return query.setFirstResult(from).setMaxResults(10).getResultList();
	}


	private TypedQuery<Fire> createSearchCriteria(EquipmentSearchCriteria fireSearchcriteria,Workspace w) {
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Fire> cq=cb.createQuery(Fire.class);
		Root<Fire> fromEE = cq.from(Fire.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		if (fireSearchcriteria.getServingArea() != null)
			if (!fireSearchcriteria.getServingArea().isEmpty())
				predicates.add(cb.equal(fromEE.get("servingArea"),
						fireSearchcriteria.getServingArea()));
		
		if (fireSearchcriteria.getName()!= null)
			if (!fireSearchcriteria.getName().isEmpty())
				predicates.add(cb.equal(fromEE.get("name"),
						fireSearchcriteria.getName()));
		
		if(fireSearchcriteria.getEquipmentType()!=null)
			if (!fireSearchcriteria.getServingArea().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentType"),fireSearchcriteria.getEquipmentType()));
		
		if (fireSearchcriteria.getBuilding() != null)
			predicates.add(cb.equal(fromEE.get("building").get("id"), fireSearchcriteria
					.getBuilding().getId()));
		if (fireSearchcriteria.getAssetSubtype() != null)
			predicates.add(cb.equal(fromEE.get("assetSubtype").get("id"),fireSearchcriteria
					.getAssetSubtype().getId()));
		if (fireSearchcriteria.getLocation() != null)
			predicates.add(cb.equal(fromEE.get("location").get("id"), fireSearchcriteria
					.getLocation().getId()));
		
		
		
		if(fireSearchcriteria.getEquipmentCode()!=null)
			if(!fireSearchcriteria.getEquipmentCode().isEmpty()){
				
				predicates.add(cb.equal(fromEE.get("equipmentCode"),fireSearchcriteria.getEquipmentCode()));
			}
			
			

		
	
	
		predicates.add(cb.equal(fromEE.get("workspace"), w));
		
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromEE.get("building")), cb.desc(fromEE.get("name")));
		return em.createQuery(cq);
	}
	
	
	public long count(EquipmentSearchCriteria fireSearch,Workspace w){
		TypedQuery<Fire> fire=createSearchCriteria(fireSearch,w);
		return fire.getResultList().size();
		
	}


	

}
