package com.aste.lsme.daoImpl;

import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.PredictiveCriteria;
import com.aste.lsme.domain.ThrushHoldValue;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.dao.ThrushHoldDaoInterface;
import com.aste.lsme.domain.Constants;

@Repository
@Transactional
public class ThrushHoldDaoImpl implements ThrushHoldDaoInterface {
	private EntityManager entitymanager;

	public EntityManager getEntitymanager() {
		return entitymanager;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	public List<ThrushHoldValue> listAll() {
		TypedQuery<ThrushHoldValue> d = (TypedQuery<ThrushHoldValue>) entitymanager
				.createQuery("Select l from ThrushHoldValue l");
		return d.getResultList();
	}

	@Override
	public boolean add(ThrushHoldValue b) {

		entitymanager.persist(b);

		return true;

	}

	@Override
	public void remove(long id) throws Exception {
		try {
			entitymanager.remove(entitymanager.find(ThrushHoldValue.class, id));
		} catch (Exception e) {

		}

	}

	@Override
	public ThrushHoldValue find(long id) {
		return entitymanager.find(ThrushHoldValue.class, id);

	}

	@Override
	public ThrushHoldValue update(ThrushHoldValue b) {

		Query query = entitymanager.createQuery("Select l from ThrushHoldValue l where l.id = :id");
		query.setParameter("id", b.getId());
		if ((query).getResultList().isEmpty()) {
			System.out.println("cant to merde");
			return b;
		}

		else {
			System.out.println("about to merde");
			return entitymanager.merge(b);
		}

	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		TypedQuery d = (TypedQuery) entitymanager.createQuery("Select count(l) from ThrushHoldValue l ");
		return (Long) d.getResultList().get(0);

	}

	@Override
	public List<ThrushHoldValue> getPage(int from, int to) {
		// TODO Auto-generated method stub
		TypedQuery<ThrushHoldValue> d = (TypedQuery<ThrushHoldValue>) entitymanager
				.createQuery("Select c from PredictiveMaintenance c ");
		return d.setFirstResult(from).setMaxResults(to).getResultList();
	}

	@Override
	public List<ThrushHoldValue> searchSpecificPredictiveMaintenance(int from, PredictiveCriteria p,
			Workspace w) {

		TypedQuery<ThrushHoldValue> PMList = PMSearch(p, w);
		return PMList.setFirstResult(from).setMaxResults(10).getResultList();
		

	}

	@Override
	public long countSpecificPredictiveMaintenance(PredictiveCriteria p, Workspace w) {
		
		TypedQuery<ThrushHoldValue> PMList = PMSearch(p, w);
		return (long) PMList.getResultList().size();

	}

	private List<Predicate> getPredictivePredicateList(PredictiveCriteria p,
			Root<ThrushHoldValue> predictiveMaintRoot, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		Expression<String> loccolumnPath = null;
		Expression<String> buildcolumnPath = null;
		Expression<String> subsyscolumnPath = null;
		if (p.getSystem() == "1") {
			predicateList.add(criteriaBuilder.isNull(predictiveMaintRoot.get("mechanicalEquip")));
			System.out.println("electrical system p.getSystem()==" + p.getSystem());
			loccolumnPath = predictiveMaintRoot.get("electricalEquip").get("location").get("id");
			buildcolumnPath = predictiveMaintRoot.get("electricalEquip").get("location").get("building").get("id");
			subsyscolumnPath = predictiveMaintRoot.get("electricalEquip").get("equipmentSubSystem").get("id");
		} else if (p.getSystem() == "2") {
			System.out.println("mechanical system p.getSystem() ==" + p.getSystem());
			predicateList.add(criteriaBuilder.isNull(predictiveMaintRoot.get("electricalEquip")));
			loccolumnPath = predictiveMaintRoot.get("mechanicalEquip").get("location").get("id");
			buildcolumnPath = predictiveMaintRoot.get("mechanicalEquip").get("location").get("building").get("id");
			subsyscolumnPath = predictiveMaintRoot.get("mechanicalEquip").get("equipmentSubSystem").get("id");
		}
		if (!p.getBuilding().equals("-1"))
			predicateList.add(criteriaBuilder.equal(buildcolumnPath, p.getBuilding()));
		if (p.getLoc() != -1)
			predicateList.add(criteriaBuilder.equal(loccolumnPath, p.getLoc()));
		if (p.getSubsystem() != -1)
			predicateList.add(criteriaBuilder.equal(subsyscolumnPath, p.getSubsystem()));

		return predicateList;
	}

	public TypedQuery<Equipment> equipSearch(EquipmentSearchCriteria eqSearch, Workspace w) {

		CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Equipment> cq = cb.createQuery(Equipment.class);
		Root<Equipment> fromEE = cq.from(Equipment.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(fromEE.get("workspace"), w));

		
		if (eqSearch.getEquipmentType() != "")
			//predicates.add(cb.equal(fromEE.get("type"), eqSearch.getEquipmentType()));

			predicates.add(cb.equal(fromEE.get("assetSubtype").get("assetType").get("assetTypeName"), eqSearch.getEquipmentType()));
		if (eqSearch.getAssetSubtype() != null)
			predicates.add(cb.equal(fromEE.get("assetSubtype"), eqSearch.getAssetSubtype()));

		if (eqSearch.getBuilding() != null)
			predicates.add(cb.equal(fromEE.get("building"), eqSearch.getBuilding()));

		if (eqSearch.getLocation() != null)
			predicates.add(cb.equal(fromEE.get("location"), eqSearch.getLocation()));
		cq.select(fromEE).where(predicates.toArray(new Predicate[] {}));
		return entitymanager.createQuery(cq);
	}

	@Override
	public List<AssetSubtype> getPredictEquipmentSubSystems(String eqType) {
		TypedQuery<AssetSubtype> d = (TypedQuery<AssetSubtype>) entitymanager
				.createQuery("Select d from AssetSubtype d where d.assetType.assetTypeName=:type");
		d.setParameter("type", eqType);
		return d.getResultList();

	}

	@Override
	public boolean save(ThrushHoldValue pm) {
		try {
			entitymanager.persist(pm);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public long countEqp(EquipmentSearchCriteria eqp, Workspace w) {
		TypedQuery<Equipment> eqpList = equipSearch(eqp, w);
		return (long) eqpList.getResultList().size();
	}

	@Override
	public List<Equipment> getSearchEqpList(int from, EquipmentSearchCriteria eqp, Workspace w) {
		TypedQuery<Equipment> eqpList = equipSearch(eqp, w);
		return eqpList.setFirstResult(from).setMaxResults(10).getResultList();
	}

	public TypedQuery<ThrushHoldValue> PMSearch(PredictiveCriteria PMSearch, Workspace w) {

		System.out.println(PMSearch.getSystem() + "====systempm");
		System.out.println(PMSearch.getSubsystem() + "=========subsystempm");
		System.out.println(PMSearch.getBuilding() + "==========buildingpm");
		System.out.println(PMSearch.getLoc() + "==========locationpm");
		System.out.println(w+"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwo");
		CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<ThrushHoldValue> cq = criteriaBuilder.createQuery(ThrushHoldValue.class);
		Root<ThrushHoldValue> eqRoot = cq.from(ThrushHoldValue.class);
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if(w!=null)
		predicateList
		.add(criteriaBuilder.equal(eqRoot.get("equipment").get("workspace"), w));

		
		if (PMSearch.getSystem() != "") {
			System.out.println("my name is Iram");
			if (!PMSearch.getSystem().isEmpty()) {
				System.out.println("i hv system");
				predicateList.add(criteriaBuilder.equal(eqRoot.get("equipment").get("assetSubtype").get("assetType").get("assetTypeName"), PMSearch.getSystem()));
			}
		}
		if (PMSearch.getSubsystem() != null) {
			System.out.println("i have subsystem");
			predicateList.add(
					criteriaBuilder.equal(eqRoot.get("equipment").get("assetSubtype").get("id"), PMSearch.getSubsystem()));
		}

		if (PMSearch.getBuilding() != "")
			if (!PMSearch.getBuilding().isEmpty()) {
				System.out.println("i have building");
				predicateList.add(criteriaBuilder.equal(eqRoot.get("equipment").get("building").get("id"),
						Long.parseLong(PMSearch.getBuilding())));
			}
		if (PMSearch.getLoc() != null) {
			System.out.println("i have location");
			predicateList
					.add(criteriaBuilder.equal(eqRoot.get("equipment").get("location").get("id"), PMSearch.getLoc()));
		}
		
		cq.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
		System.out.println(entitymanager.createQuery(cq).getResultList() + "lemmeeeeeee");
		return entitymanager.createQuery(cq);
	}

}