package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.AssetTypeDao;
import com.aste.lsme.domain.AssetType;

@Repository
public class AssetTypeDaoImpl implements AssetTypeDao{
	
	@PersistenceContext
	EntityManager em ;

	@Override 
	public boolean addAssetType(AssetType assetType) {
		Query q=em.createQuery("Select b from AssetType b where b.assetTypeName = :name");
		q.setParameter("name", assetType.getAssetTypeName());

		if(q.getResultList().isEmpty())
		{
			System.out.println("ggg");
			em.persist(assetType);
			System.out.println("gggpppp");
			return true;
		}
		else
		{
		  return false;
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> getAll() {
		Query q = em.createQuery("Select a from AssetType a");
		return q.getResultList();
	}

	@Override
	public AssetType get(Long id) {
		
		return em.find(AssetType.class, id);
	}

	@Override
	public boolean update(AssetType assetType) {
		Query q=em.createQuery("Select b from AssetType b where b.assetTypeName = :name and b.id != :id");
		q.setParameter("name", assetType.getAssetTypeName());
		q.setParameter("id",assetType.getId());

		if(q.getResultList().isEmpty())
		{
			em.merge(assetType);
			return true;
		}
		else
		{
		  return false;
		}
		
	}

	@Override
	public void delete(Long id) {
		em.remove(em.find(AssetType.class, id));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> getAssetPaginated(int from){
		Query q = em.createQuery("Select a from AssetType a");
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getAssetTypeCount() {
		Query q = em.createQuery("Select a from AssetType a");
		return (long) q.getResultList().size();
	}
	
	public AssetType getAssetTypeOnSubtypeName(String name)
	{
		Query q = em.createQuery("Select a.assetType from AssetSubtype a where a.assetSubTypeName=:name")
				.setParameter("name", name);
		return (AssetType) q.getSingleResult();
		
	}

	public List<AssetType> getAssetType() {
		// TODO Auto-generated method stub
		TypedQuery<AssetType> d = (TypedQuery<AssetType>)em
				.createQuery("Select l from AssetType l  ORDER BY l.assetTypeName ASC ");
	
		return d.getResultList();
		
	}

}
