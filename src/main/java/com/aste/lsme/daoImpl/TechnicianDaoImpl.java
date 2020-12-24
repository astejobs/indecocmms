package com.aste.lsme.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.TechnicianDao;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.aste.lsme.webservicesDtos.TechnicianDTO;
@Repository
public class TechnicianDaoImpl implements TechnicianDao{
	@PersistenceContext
     EntityManager em;
	@Override
	public boolean addTechnician(Technician technician,Workspace w) {
	Query q=em.createQuery("Select b from Technician b where b.technicianName = :name and b.workspace = :w");
	q.setParameter("name",technician.getTechnicianName());	
	q.setParameter("w", w);
	if(q.getResultList().isEmpty())
	{
		em.persist(technician);
		return true;
	}
	
	return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Technician> getTechnician() {
		Query q=em.createQuery("Select b from Technician b");
		
		return q.getResultList();
	}
	@Override
	public Technician get(Long id) {
	
		return em.find( Technician.class, id);
	}
	@Override
	public boolean update(Technician technician,Workspace w) {
		Query q=em.createQuery("Select b from Technician b where b.technicianName = :name and b.id != :id and b.workspace = :w");
		q.setParameter("name",technician.getTechnicianName());	
		q.setParameter("id", technician.getId());
		q.setParameter("w", w);
		if(q.getResultList().isEmpty())
		{
			em.merge(technician);
			return true;
		}
		
		return false;
		}
	@Override
	public void delete(Long id) {
	
		 em.remove(em.find(Technician.class, id));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Technician> getTechnicianPaginated(int from,Workspace w) {
		Query q =em.createQuery("Select b from Technician b where b.workspace = :w").setParameter("w", w);
		return q.setFirstResult(from).setMaxResults(10).getResultList();
		
	}
	@Override
	public long geTechnicianCount(Workspace w) {
		Query q = em.createQuery("Select b from  Technician b where b.workspace = :w").setParameter("w", w);
		return (long) q.getResultList().size();
	
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Technician> getWorkspaceTechnician(Workspace w) {
		Query q = em.createQuery("Select t from  Technician t where t.workspace = :w").setParameter("w", w);
		return q.getResultList();
	}
	@Override
	public List<TechnicianDTO> findTechnician(String w) {
		
		
		Query q=em.createQuery("Select New  com.aste.lsme.webservicesDtos.TechnicianDTO(t.id,t.technicianName)from Technician t where t.workspace.workspaceId= :id");
		q.setParameter("id", w);
		System.out.println("sizeeeeeeeeeee==="+q.getResultList().size());
		return q.getResultList();
	
		
	}
}
