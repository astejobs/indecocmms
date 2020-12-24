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
import com.aste.lsme.dao.CivilDao;
import com.aste.lsme.domain.Civil;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;


@Repository
public class CivilDaoImpl implements CivilDao {

	private EntityManager em;

	public EntityManager getEntitymanager() {
		return em;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<Civil> getAllCivilEquipments() {
		
		TypedQuery<Civil> d = (TypedQuery<Civil>) em.createQuery("Select l from Civil l",Civil.class);
		return d.getResultList();	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Civil> getWorkspaceRelatedCivilLevel3(Workspace w) 
	{
		
		Query q = em.createQuery("Select c from Civil c where workspace =:w").setParameter("w", w);
		return q.getResultList();
	}
	
	@Override
	public boolean add(Civil civilEquipment,Workspace w) {
		Query query = em.createQuery("Select f from Equipment f where (f.name = :name)"
				+ " and f.assetSubtype=:subtype and f.workspace = :w");
		 
		 //  query.setParameter("name",  civilEquipment.getName());
		 //  query.setParameter("assetNo",  civilEquipment.getAssetNo());
		 //  query.setParameter("subtype",  civilEquipment.getAssetSubtype());
		   query.setParameter("w", w);
		   
		   
		   if(query.getResultList().isEmpty()){
		   	em.persist(civilEquipment);
		       return true;
		   }
		   else{
		   	return false;
		     }
	}

	@Override
	public Civil find(long id) {
		return em.find(Civil.class, id);
	}


	

	
	@Override
	public List<Civil> getSearchList(EquipmentSearchCriteria esc,int from,Workspace w) {
          
		TypedQuery<Civil> query=createSearchCriteria(esc,w);
		
		return query.setFirstResult(from).setMaxResults(10).getResultList();
	}
	
	private TypedQuery<Civil> createSearchCriteria(EquipmentSearchCriteria esc,Workspace w) {
	
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Civil> cq=cb.createQuery(Civil.class);
		Root<Civil> fromEE = cq.from(Civil.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		if (esc.getServingArea() != null)
			if (!esc.getServingArea().isEmpty())
				predicates.add(cb.equal(fromEE.get("servingArea"),esc.getServingArea()));
		
		
		if(esc.getEquipmentType()!=null)
			if (!esc.getServingArea().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentType"),"civil"));
		
		
		if (esc.getBuilding() != null)
			predicates.add(cb.equal(fromEE.get("building").get("id"), esc
					.getBuilding().getId()));
		if (esc.getAssetSubtype() != null)
			predicates.add(cb.equal(fromEE.get("assetSubtype").get("id"),esc
					.getAssetSubtype().getId()));
		if (esc.getLocation() != null)
			predicates.add(cb.equal(fromEE.get("location").get("id"), esc
					.getLocation().getId()));
		
		predicates.add(cb.equal(fromEE.get("workspace"),w));
		
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromEE.get("building")), cb.desc(fromEE.get("name")));
		return em.createQuery(cq);
	}

	@Override
	public Civil delete(Civil c) {
		
		try
		{
		em.remove(em.contains(c) ? c:em.merge(c));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return c;
		
	}

	@Override
	public long getCount(Workspace w,EquipmentSearchCriteria esc) {
		TypedQuery<Civil> query=createSearchCriteria(esc,w);
		return query.getResultList().size();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Civil> getPaginated(int from,Workspace w) {
		Query q = em.createQuery("Select b from Civil b where b.workspace =:w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public boolean update(Civil equipment, Workspace w) {
		Query query = em.createQuery("Select f from Equipment f where f.id != :id and (f.name = :name) and f.assetSubtype=:subtype and f.workspace = :w");
		 //  query.setParameter("id", equipment.getId());
		//   query.setParameter("name", equipment.getName());
		//   query.setParameter("assetNo",equipment.getAssetNo());
		 //  query.setParameter("subtype", equipment.getAssetSubtype());
		   query.setParameter("w", w);
		   
		   
		   if(query.getResultList().isEmpty()){
		   	em.merge(equipment);
		       return true;
		   }
		   else{     
		     return false;
		     }
	}


}
