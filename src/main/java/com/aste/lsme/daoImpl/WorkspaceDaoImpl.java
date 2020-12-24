package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.WorkspaceDao;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.WorkspaceDto;

@Repository
public class WorkspaceDaoImpl implements WorkspaceDao {
	
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<Workspace> getallworkspaces()
	{
		Query query = entityManager.createQuery("Select w from  Workspace w ");
		 return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Workspace> getUserRelatedWorkspaces(Long id)
	{
		Query query = entityManager.createQuery("Select v.workspace from UserGroup u JOIN u.accessSite v "
				+ "where u.id=:id").setParameter("id", id);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Workspace> getgroupprivilegeworkspace(List<Long> w)
	{

		Query query = entityManager.createQuery("Select w from Workspace w where w.id in :wids")
				.setParameter("wids",w);
		return query.getResultList();
	}
	
	public Workspace getworkspaceonid(String id)
	{
		Query query = entityManager.createQuery("select w from Workspace w where w.workspaceId=:id")
				.setParameter("id", id);
		return (Workspace) query.getSingleResult();
		
	} 

	@Override
	@Transactional
	public void persist(Workspace workspace) {
		
		entityManager.persist(workspace);
	}

	@Override
	public void update(Workspace workspace) {
		entityManager.merge(workspace);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Workspace> getWorkspacePaginated(int from) {
		Query query = entityManager.createQuery("Select w from Workspace w");
		return query.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getWorkspaceCount() {
		Query query = entityManager.createQuery("Select w from Workspace w");
		return (long) query.getResultList().size();
	}
	
	@Override
	public void delete(String id){
		entityManager.remove(entityManager.find(Workspace.class,id));
	}

	@Override
	public List<WorkspaceDto> getWorkspaces(Long grpId) {
		List<Long> grId=new ArrayList<Long>();

		grId.add(grpId);
		Query query = entityManager.
				createQuery("select new com.aste.lsme.webservicesDtos.WorkspaceDto(l.workspaceId,l.buildingDescription) from Workspace l JOIN l.userGroups u where u.id in :grp  Order By l.id").setParameter("grp", grId);

		 return query.getResultList();
	}
	
	

}
