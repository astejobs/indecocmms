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

import com.aste.lsme.dao.MechanicalDao;
import com.aste.lsme.domain.ACMV;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Mechanical;
import com.aste.lsme.domain.Workspace;

import javassist.compiler.ast.Symbol;

@Repository
public class MechanicalDaoImpl implements MechanicalDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public boolean save(Mechanical mechanicalEquipment) {
		TypedQuery<Equipment> d = (TypedQuery<Equipment>) em
				.createQuery("Select l from Equipment l where (l.name=:name) and l.assetSubtype=:assetSubType and l.workspace=:w");
		//d.setParameter("assetNo", mechanicalEquipment.getAssetNo());
		//d.setParameter("name", mechanicalEquipment.getName());
		//d.setParameter("assetSubType", mechanicalEquipment.getAssetSubtype());
		//d.setParameter("w", mechanicalEquipment.getWorkspace());
		if(d.getResultList().size()>0){
			return false;
		}
		else
		{
		em.persist(mechanicalEquipment);
			return true;
		}
		
	}
	

	@Override
	public boolean geteqpfile(String equipFile) {
		TypedQuery<Equipment> d = (TypedQuery<Equipment>) em
				.createQuery("Select l from Equipment l where l.image=:equipFile");
		d.setParameter("equipFile", equipFile);
			if(d.getResultList().size()>0)
			{
				return false;
			}		
			else
			{
				return true;
			}
	}

	@Override
	public boolean getdrawfile(String drawImage) {
		TypedQuery<Equipment> d = (TypedQuery<Equipment>) em
				.createQuery("Select l from Equipment l where l.drawingImage=:drawImage");
		d.setParameter("drawImage", drawImage);
			if(d.getResultList().size()>0)
			{
				return false;
			}		
			else
			{
				return true;
			}
	}

	@Override
	public Mechanical find(Long id) {
		return em.find(Mechanical.class, id);
	}

	@Override
	public List<Mechanical> getMechEquips() {
		TypedQuery<Mechanical> d = (TypedQuery<Mechanical>) em
				.createQuery("Select l from Equipment l where l.equipmentType =:type");
		d.setParameter("type", "mechanical");
		return d.getResultList();		
	}

	@Override
	public boolean remove(Mechanical mechanical) {
		 try{
			 em.remove(em.contains(mechanical) ? mechanical : em.merge(mechanical));	 
			 return true;
		 }catch (Exception e) {
			 return false;
		  }

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mechanical> etEqPaginated(int from) {
			Query q = em.createQuery("Select l from Equipment l where l.equipmentType =:type");
			q.setParameter("type", Constants.MECHANICALSUBSYSTEM);
			return q.setFirstResult(from).setMaxResults(10).getResultList();
		}

	@Override
	public boolean update(Mechanical mechanical) {	
	    Query query = em.createQuery("Select f from Equipment f where (f.name = :name) and f.assetSubtype=:subtype and f.workspace = :w and f.id != :id");
	   // query.setParameter("id", mechanical.getId());
	   // query.setParameter("name", mechanical.getName());
	   // query.setParameter("assetNo", mechanical.getAssetNo());
	   // query.setParameter("subtype", mechanical.getAssetSubtype());
	   // query.setParameter("w", mechanical.getWorkspace()); 
	      if(query.getResultList().isEmpty())
	      {
		        em.merge(mechanical);
		        return true; 	  
	      }
	      else{
	    	  System.err.println(query.getResultList().get(0).toString());
		        return false;
	      }
	}

	@Override
	public List<Mechanical> getSearchList(int from,EquipmentSearchCriteria mechanicalSearch,Workspace w) {	
		TypedQuery<Mechanical> mechList=getList(mechanicalSearch,w);
		return mechList.setFirstResult(from).setMaxResults(10).getResultList();		
	}
	
	@Override
	public Long count(EquipmentSearchCriteria mechanicalSearch,Workspace w) {	
		TypedQuery<Mechanical> mechList=getList(mechanicalSearch,w);
	    return (long) mechList.getResultList().size();
	}
	
	public TypedQuery<Mechanical> getList(EquipmentSearchCriteria mechanicalSearch,Workspace w) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Mechanical> cq=cb.createQuery(Mechanical.class);
		Root<Mechanical> fromEE = cq.from(Mechanical.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		if (mechanicalSearch.getServingArea() != null)
			if (!mechanicalSearch.getServingArea().isEmpty())
				predicates.add(cb.equal(fromEE.get("servingArea"),
						mechanicalSearch.getServingArea()));
		
		if (mechanicalSearch.getName() != null)
			if (!mechanicalSearch.getName().isEmpty())
				predicates.add(cb.equal(fromEE.get("name"),
						mechanicalSearch.getName()));
		
		if(mechanicalSearch.getEquipmentType()!=null)
			if (!mechanicalSearch.getServingArea().isEmpty())
			predicates.add(cb.equal(fromEE.get("equipmentType"),mechanicalSearch.getEquipmentType()));
		if (mechanicalSearch.getBuilding() != null)
			predicates.add(cb.equal(fromEE.get("building").get("id"), mechanicalSearch
					.getBuilding().getId()));
		if (mechanicalSearch.getAssetSubtype() != null)
			predicates.add(cb.equal(fromEE.get("assetSubtype").get("id"),mechanicalSearch
					.getAssetSubtype().getId()));
		if (mechanicalSearch.getLocation() != null)
			predicates.add(cb.equal(fromEE.get("location").get("id"), mechanicalSearch
					.getLocation().getId()));
		if(mechanicalSearch.getEquipmentCode()!=null)
		if(!mechanicalSearch.getEquipmentCode().isEmpty())
		predicates.add(cb.equal(fromEE.get("equipmentCode"),mechanicalSearch.getEquipmentCode()));	
		predicates.add(cb.equal(fromEE.get("workspace"), w));
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromEE.get("building")), cb.desc(fromEE.get("name")));
		return em.createQuery(cq);	
	}	
}