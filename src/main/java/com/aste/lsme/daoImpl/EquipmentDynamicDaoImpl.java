package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.EquipmentDynamicDaoInterface;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.DynamicFieldsOfEquiment;
import com.aste.lsme.domain.Workspace;

@Repository
public class EquipmentDynamicDaoImpl<T> implements EquipmentDynamicDaoInterface<T> {
	@PersistenceContext
    EntityManager em;
	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
	em.persist(t);	
	}
	
	@Override
	public T find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getUnique(Class clazz,Long id, Workspace workspace) {
		// TODO Auto-generated method stub
		
		Query query=em.createQuery("select n  from "+ clazz.getName() + "  n  where  n.assetSubType.id=:id and n.workspace=:workspace").setParameter("id",id).setParameter("workspace", workspace);
		  List<T> list=query.getResultList();
		  if (list == null || list.isEmpty()) {
		        return null;
		    }
		  System.err.println("size of list"+list.size());
		return list.get(0);
	}

	@Override
	public DynamicFieldsOfEquiment getEquipment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void update(DynamicFieldsOfEquiment equipment) {
		// TODO Auto-generated method stub
		em.merge(equipment);
	}

	
}
