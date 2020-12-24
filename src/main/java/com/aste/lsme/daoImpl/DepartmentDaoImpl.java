package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.DepartmentDao;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Division;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean persist(Department department,Workspace w) {
		Query query = em.createQuery("Select d from Department d where d.name = :name and d.workspace = :w ");
		query.setParameter("name", department.getName());
		//query.setParameter("desc", department.getDescription());
		query.setParameter("w", w);
		if(query.getResultList().isEmpty()){
		  em.persist(department);
		 	return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Department get(Long id) {
		return  em.find(Department.class, id);
	}

	@Override
	public void delete(Long id) {
		 em.remove(em.find(Department.class, id));
		
	}

	@Override
	public boolean update(Department department,Workspace w) {
		
		Query query = em.createQuery("Select d from Department d where d.name = :name and d.workspace = :w and d.id != :id ");
		query.setParameter("name", department.getName());
		query.setParameter("id", department.getId());
		query.setParameter("w", w);
		
		if(query.getResultList().isEmpty()){
			 em.merge(department);
			 return true;
		}
		else{
				return false;
			}
		}


	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getWorkspaceDepartments(Workspace w) {
		Query q = em.createQuery("Select d from Department d where workspace.workspaceId =:w").setParameter("w", w.getWorkspaceId());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAll() {
		Query q = em.createQuery("Select d from Department d");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartmentPaginated(int from,Workspace w) {
		Query q = em.createQuery("Select d from Department d where d.workspace = :w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
	}

	@Override
	public Long getDepartmentCount(Workspace w) {
		Query q = em.createQuery("Select d from Department d where d.workspace = :w").setParameter("w", w);
		return (long) q.getResultList().size();
	}

	@Override
	public List<Department> getDepartments(Long workspace) {
		Query q = em.createQuery("Select d from Department d where d.workspace.id = :w").setParameter("w", workspace);;
		return q.getResultList();
	}
	

}
