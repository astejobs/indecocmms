package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.WarehouseDao;
import com.aste.lsme.domain.StoreKeeper;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Warehouse;
@Repository
public class WarehouseDaoImpl implements WarehouseDao {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(Warehouse warehouse) {
		Query query = em.createQuery("Select w from Warehouse w where w.name = :name").setParameter("name", warehouse.getName());
		if(query.getResultList().isEmpty()){
			em.persist(warehouse);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Warehouse warehouse) {
		Query query = em.createQuery("Select w from Warehouse w where w.id != :id and w.name = :name ");
		query.setParameter("id", warehouse.getId());
		query.setParameter("name", warehouse.getName());
		if(query.getResultList().isEmpty()){
		  em.merge(warehouse);
		  return true;
		}
		return false;
	}

	@Override
	public Warehouse get(long id) {
		
		return em.find(Warehouse.class, id);
		
	}

	@Override
	public void delete(long id) {
		em.remove(em.find(Warehouse.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> getAll() {
		Query query = em.createQuery("Select w from Warehouse w");
		return query.getResultList();
	}
	
	@Override
	public StoreKeeper changeStatusStoreKeeper(StoreKeeper storeKeeper) {
		return em.merge(storeKeeper);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> getWarehousesOfStorekeeper(UserDetail userDetail) {
		Query query = em.createQuery("Select s.warehouse from StoreKeeper s where s.userDetail = :userDetail")
															.setParameter("userDetail",userDetail);
		return query.getResultList();
	}

	

	@Override
	public List<UserDetail> getUserDetail(long id) {
		Query q =em.createQuery("Select s.userDetail from StoreKeeper s where s.warehouse.id=:id").setParameter("id", id);
		
		return q.getResultList();
	}

	
	
}
