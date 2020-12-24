package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.ChecklistDao;
import com.aste.lsme.domain.ChecklistHeader;
import com.aste.lsme.domain.ChecklistPropertyTitle;
import com.aste.lsme.domain.ChecklistSearch;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.Workspace;

@Repository
public class ChecklistDaoImpl implements ChecklistDao {
	
	@PersistenceContext
	EntityManager em;
	
	public void addChecklist(ChecklistHeader checklist) throws Exception
	{
		em.persist(checklist);
	}
	
	
	public ChecklistHeader find(Long id)
	{
		 return em.find(ChecklistHeader.class, id);
	}
	
	@Override
	public Long countSearchPage(ChecklistSearch qsc) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ChecklistHeader> qRoot = cq.from(ChecklistHeader.class);
		List<Predicate> pList = getPredicatesOnCriteria(qsc, cb, qRoot);
		cq.select(cb.count(qRoot)).where(
				cb.and(pList.toArray(new Predicate[0])));
		cq.distinct(true);
		return em.createQuery(cq).getSingleResult();
	}
	
	
	public List<ChecklistHeader> getSearchPage(ChecklistSearch qsc) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ChecklistHeader> cq = cb
				.createQuery(ChecklistHeader.class);
		Root<ChecklistHeader> qRoot = cq.from(ChecklistHeader.class);
		List<Predicate> pList = getPredicatesOnCriteria(qsc, cb, qRoot);
		cq.select(qRoot).where(cb.and(pList.toArray(new Predicate[0])));
		cq.distinct(true);
		return em.createQuery(cq).setFirstResult(qsc.getFromPage())
				.setMaxResults(Constants.ROWS).getResultList();
	}
	
	public List<Predicate> getPredicatesOnCriteria(ChecklistSearch qsc,
			CriteriaBuilder cb, Root<ChecklistHeader> qRoot) {
		
		Join<ChecklistHeader, Equipment> checklequipjoin = qRoot.join("equipment");
		List<Predicate> predicateList = new ArrayList<Predicate>();
		predicateList.add(cb.equal(checklequipjoin.get("workspace"),
				qsc.getWorkspace()));
		
		if(qsc.getEquipment()!= null)
		    predicateList.add(cb.equal(checklequipjoin.get("id"),
					qsc.getEquipment().getId()));
		
		if((qsc.getChecklistname()!=null) && (!qsc.getChecklistname().isEmpty()))
		    predicateList.add(cb.equal(qRoot.get("checklistName"),
					qsc.getChecklistname()));
		
		return predicateList;
		
	}
	
	public void update(ChecklistHeader checklist) throws Exception
	{
		
		em.merge(checklist);
	}
	
	public void removeChecklistTitle(Long id)
	{
		Query query1 = em.createQuery("Select c from ChecklistPropertyTitle c  WHERE c.checklistHeader.id=:qid")
				.setParameter("qid", id);
		List<ChecklistPropertyTitle> checklistpropertytitles = query1.getResultList();
		for (ChecklistPropertyTitle checklistPropertyTitle : checklistpropertytitles) {
			checklistPropertyTitle.setChecklistHeader(null);
			em.merge(checklistPropertyTitle);
			
		}
		try{
			Query query = em.createQuery(
				      "DELETE FROM ChecklistPropertyTitle c WHERE c.checklistHeader.id=:qid");
				 query.setParameter("qid", id).executeUpdate();
			}catch(Exception e){e.printStackTrace();}
			
		
	}
	
	public void removeChecklistproperties(Long id)
	{
		try{
			Query query = em.createQuery(
				      "DELETE  FROM ChecklistProperty c WHERE c.checklistPropertyTitle.id=:qid");
				   query.setParameter("qid", id).executeUpdate();
			}catch(Exception e){e.printStackTrace();}
		
	}


	@Override
	public List<ChecklistHeader> getAllWorkspaceBased(Workspace w) {
		
	Query query=em.createQuery("select DISTINCT(c.checklists) from Equipment c where c.workspace=:w").setParameter("w", w);
		
		List<ChecklistHeader> list=query.getResultList();
		return list;
		
	}
	
	
	@Override
	public List<ChecklistHeader> listAll(Long id) {
		TypedQuery<ChecklistHeader> d = (TypedQuery<ChecklistHeader>) em
				.createQuery("Select c from ChecklistHeader c JOIN c.equipment e where e.id=:id");
		d.setParameter("id", id);
		return d.getResultList();
	}


	@Override
	public List<ChecklistPropertyTitle> findPropertyTitles(ChecklistHeader chklistHeader) {
		
		TypedQuery<ChecklistPropertyTitle> d = (TypedQuery<ChecklistPropertyTitle>) em.createQuery("Select l from ChecklistPropertyTitle l where l.checklistHeader.id=:id");
		d.setParameter("id", chklistHeader.getId());
		return d.getResultList();
	}


	
}
