package com.aste.lsme.daoImpl;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.GroupDetailsDaoInterface;
import com.aste.lsme.domain.GroupPriviledges;
import com.aste.lsme.domain.ModuleDetail;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;

@Repository
public class GroupDetailsDaoImpl implements GroupDetailsDaoInterface {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public UserGroup saveGroup(UserGroup group) throws Exception
	{
		   entityManager.persist(group);
			return group;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroup> getallgroups()
	{
		Query query = entityManager.createQuery("Select u from UserGroup u ");
	    return query.getResultList();
	}
	
	public UserGroup findGroup(Long id)
	{
		 return entityManager.find(UserGroup.class, id);
	}
	
	public UserGroup findGroupbyName(String name)
	{
		 Query query = entityManager.createQuery("select g from UserGroup g where g.userGroupName=:name ")
				 .setParameter("name", name);
		 try
		 {
			 return (UserGroup) query.getSingleResult();
		 }
		 catch(Exception ex)
		 {
			 return null;
		 }
	}
	
	public String removeGroup(Long id) throws Exception
	{
		
		entityManager.remove(entityManager.find(UserGroup.class, id));
		return "success";
		
	}
		
	public UserGroup updateGroup(UserGroup group)
	{
		try
		{
			
			return entityManager.merge(group);
		}
		catch(Exception ex)
		{
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ModuleDetail> getallmodules()
	{
		Query query = entityManager.createQuery("Select m from ModuleDetail m ");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ModuleDetail> getmodulesbasedongroup(GroupPriviledges group)
	{
		Query query = entityManager.createQuery("Select m from ModuleDetail m where m.id in :ids")
				.setParameter("ids", Arrays.asList(group.getModuleDetail()));
		 return query.getResultList();
		
	}
	
   @SuppressWarnings("unchecked")
   public List<ModuleDetail> getgrouprelatedmodule (Long id)
   {
	   
	   Query query = entityManager.createQuery("Select m from ModuleDetail m JOIN m.userGroups u where u.id=:id")
				.setParameter("id", id);
		 return query.getResultList();
		
	   
   }
   
   @SuppressWarnings("unchecked")
   public List<Workspace> getgrouprelatedsite(Long id)
   {
	   
	   Query query = entityManager.createQuery("Select w from Workspace w JOIN w.userGroups u where u.id=:id")
				.setParameter("id", id);
		 return query.getResultList();
		
	   
   }
   
   public ModuleDetail getmodulebyName(String name)
   {
	   Query q = entityManager.createQuery("Select  m from ModuleDetail m where m.module_Name =:name")
			   .setParameter("name", name);
	   return (ModuleDetail) q.getSingleResult();
   }

    
}
