package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.AssetSubtypeDao;
import com.aste.lsme.domain.AssetSubtype;

@Repository
public class AssetSubtypeDaoImpl implements AssetSubtypeDao{
	
@PersistenceContext
EntityManager em;
	@Override
	public boolean addAssetSubtype(AssetSubtype assetSubtype) {
		Query query = em.createQuery("Select a from AssetSubtype a where a.assetSubTypeName = :name and a.assetType.id = :id ");
		query.setParameter("name", assetSubtype.getAssetSubTypeName());
		query.setParameter("id", assetSubtype.getAssetType().getId());
		if(query.getResultList().isEmpty()){
		  em.persist(assetSubtype);
		 	return true;
		}
		else{
			return false;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetSubtype> getAssetSubtypeList() {
        Query q=em.createQuery("select l from AssetSubtype l");
		return q.getResultList();
	}
	
	@Override
	public AssetSubtype get(Long id) {
		return em.find(AssetSubtype.class,id);
	}
	
	@Override
	public void delete(Long id) {
		em.remove(em.find(AssetSubtype.class, id));
		
	}
	@Override
	public boolean update(AssetSubtype assetSubtype) {
		Query query = em.createQuery("Select a from AssetSubtype a where a.assetSubTypeName = :name and a.assetType.id != :id ");
		query.setParameter("name", assetSubtype.getAssetSubTypeName());
		query.setParameter("id", assetSubtype.getAssetType().getId());
		if(query.getResultList().isEmpty()){
		  em.merge(assetSubtype);
		 	return true;
		}
		else{
			return false;
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetSubtype> getEquipmentSubTypeList(String type) {
		Query  qr=em.createQuery("select l from AssetSubtype l where l.assetType.assetTypeName=:type").setParameter("type", type);
		List<AssetSubtype> list=qr.getResultList();
		return  list ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetSubtype> getAssetSubTypePaginated(int from){
		Query q = em.createQuery("Select a from AssetSubtype a");
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getAssetSubTypeCount() {
		Query q = em.createQuery("Select a from AssetSubtype a");
		return (long) q.getResultList().size();
	}


	@Override
	public List<AssetSubtype> getEquipmentSubSystems(String eqType) {
		TypedQuery<AssetSubtype> d = (TypedQuery<AssetSubtype>) em
				.createQuery("Select d from AssetSubtype d where d.assetType.assetTypeName=:type");
		d.setParameter("type", eqType);
		return d.getResultList();
	}


	@Override
	public List<AssetSubtype> getAssetSubtype(Long id) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("Select d from  AssetSubtype d where d.assetType.id=:id");
        q.setParameter("id",id);
        return q.getResultList();
	}
	
	@Override
	public  List<AssetSubtype> getEquipmentSubSystemOnName(String name) {
		TypedQuery<AssetSubtype> d = (TypedQuery<AssetSubtype>) em
				.createQuery("Select d from AssetSubtype d where d.assetSubTypeName=:name");
		d.setParameter("name",name);
		return d.getResultList();
	}


	@Override
	public List<AssetSubtype> getSubSystems(Long id) {
		Query  qr=em.createQuery("select l from AssetSubtype l where l.assetType.id=:id").setParameter("id", id);
		List<AssetSubtype> list=qr.getResultList();
		return  list ;
	}

}
