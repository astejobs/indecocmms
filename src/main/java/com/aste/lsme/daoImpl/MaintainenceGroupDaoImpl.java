package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.MaintainenceGroupDao;
import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Workspace;


@Repository
public class MaintainenceGroupDaoImpl implements MaintainenceGroupDao {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public boolean persist(MaintainenceGroup mainGrp,Workspace w) {
		Query query = em.createQuery("Select b from MaintainenceGroup b where b.name = :name  and b.workspace = :w");
		query.setParameter("name", mainGrp.getName());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  em.persist(mainGrp);
		 	return true;
		}
		else{
			return false;
		}
	}

	@Override
	public MaintainenceGroup get(Long id) {
		return  em.find(MaintainenceGroup.class, id);
	}

	@Override
	public void delete(Long id) {

		 em.remove(em.find(MaintainenceGroup.class, id));
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<MaintainenceGroup> getAll() {
		Query q = em.createQuery("Select b from MaintainenceGroup b");
		return q.getResultList();
	}

	@Override
	public boolean update(MaintainenceGroup mainGrp,Workspace w) {
		Query query = em.createQuery("Select b from MaintainenceGroup b where b.name = :name and b.status=:status and b.workspace = :w and b.id!=:id");
		query.setParameter("name", mainGrp.getName());
		query.setParameter("status", mainGrp.getStatus());
		query.setParameter("w",w);
		query.setParameter("id", mainGrp.getId());
		if(query.getResultList().isEmpty()){
			 em.merge(mainGrp);
			 return true;
		}
		else{
				return false;
			}

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaintainenceGroup> getMainGrpPaginated(int from,Workspace w){
		Query q = em.createQuery("Select b from MaintainenceGroup b where b.workspace =:w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public long getMainGrpCount(Workspace w) {
		Query q = em.createQuery("Select b from MaintainenceGroup b where b.workspace =:w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaintainenceGroup> getWorkspaceMainGrp(Workspace w) {
		Query q = em.createQuery("Select b from MaintainenceGroup b where b.workspace.workspaceId =:w").setParameter("w", w.getWorkspaceId());
		return q.getResultList();
	}

	@Override
	public List<MaintainenceGroup> getmainGrpCategories(Long workspaceId) {
		Query q = em.createQuery("Select b from MaintainenceGroup b where b.workspace.id =:w").setParameter("w", workspaceId);
		return q.getResultList();
	}


}
 