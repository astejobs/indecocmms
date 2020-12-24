package com.aste.lsme.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PMDaoInterface;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.SearchPMSchedule;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.Team;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;

@Repository
public class PMDaoImpl implements PMDaoInterface {

	@PersistenceContext
	EntityManager em;

	@Override
	public void persist(Schedule schedule) 
	{
		
		em.merge(schedule);
	}

	@Override
	public List<Schedule> getscheduledtoreview() 
	{
		 Query query = em.createQuery("Select s from Schedule s where s.reviewstatus='pending'");
	      return query.getResultList();
		
	}

	@Override
	public Schedule find(Long id) {
		
		return em.find(Schedule.class, id);
	}

	@Override
	public String getCheckListNameOfSchedule(Long id) {
		
		
		TypedQuery<String> d = (TypedQuery<String>) em
				.createQuery("Select s.checklistHeader.checklistName from Schedule s where s.id=:id");
		d.setParameter("id", id);
		
		try
		{  
		return d.getSingleResult(); 
		}	
			catch(Exception e)
			{
				return null;
			}
		
	}

	@Override
	public void updatescheduleforreviewer(Schedule schedule) {
		
	    TypedQuery<Schedule> d = (TypedQuery<Schedule>) em.createQuery("update Schedule t set t.scheduleDate =:ssd,t.startDate=:sd, t.endDate=:ed, t.briefDescription=:bd , t.detailedDescription=:dd , t.userRefNumber=:ref ,t.frequency=:fre, t.taskPerformed=:task,t.reviewstatus=:status  where t.id =:id");
		    d.setParameter("ssd", schedule.getScheduleDate());
		    d.setParameter("sd", schedule.getStartDate());
		    System.out.println("end date: " + schedule.getEndDate());
		    d.setParameter("ed", schedule.getEndDate());
		    d.setParameter("bd", schedule.getBriefDescription());
		    d.setParameter("dd", schedule.getDetailedDescription());
		    d.setParameter("ref", schedule.getUserRefNumber());
		    d.setParameter("task", schedule.getTaskPerformed());
		    d.setParameter("fre", schedule.getFrequency());
		    d.setParameter("status", schedule.getReviewstatus());
		    d.setParameter("id", schedule.getId());
		  		 
		    d.executeUpdate();
		
	}

	@Override
	public List<Schedule> getscheduledforadmin() {
		Query query = em.createQuery("Select s from Schedule s where s.reviewstatus='accepted' AND s.status  is NULL");
	      return query.getResultList();
	}

	@Override
	public void updatescheduleforadmin(Schedule schedule, HttpSession session)
	{
		   TypedQuery<Schedule> d = (TypedQuery<Schedule>) em
			          .createQuery("update Schedule t set t.scheduleDate =:ssd, t.startDate=:sd, t.endDate=:ed, t.briefDescription=:bd , t.detailedDescription=:dd , t.userRefNumber=:ref ,t.frequency=:fre, t.taskPerformed=:task,t.status=:status  where t.id =:id");
			      d.setParameter("ssd", schedule.getScheduleDate());
			      d.setParameter("sd", schedule.getStartDate());
			      System.out.println("end date: " + schedule.getEndDate());
			      d.setParameter("ed", schedule.getEndDate());
			      d.setParameter("bd", schedule.getBriefDescription());
			      d.setParameter("dd", schedule.getDetailedDescription());
			      d.setParameter("ref", schedule.getUserRefNumber());
			      d.setParameter("task", schedule.getTaskPerformed());
			      d.setParameter("fre", schedule.getFrequency());
			      d.setParameter("status", schedule.getStatus());
			      d.setParameter("id", schedule.getId());
			      d.executeUpdate();
	}

	@Override
	public Long getSearchCount(SearchPMSchedule searchPMSchedule) {
	
		TypedQuery<Schedule> query = createSearchCriteriaQuery(searchPMSchedule);
		return (long) query.getResultList().size();
	}
	
	private TypedQuery<Schedule> createSearchCriteriaQuery(
			SearchPMSchedule searchPMSchedule) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Schedule> cq = cb.createQuery(Schedule.class);
		Root<Schedule> fromFr = cq.from(Schedule.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Join<Schedule, Equipment> join1 = fromFr.join("equipment");
		predicates.add(cb.equal(
				join1.<Workspace>get("workspace"),
				searchPMSchedule.getWorkspace()));
		predicates.add(cb.equal(fromFr.get("status"), "approved"));

		if (searchPMSchedule.getSchedule_number() != null)
			if (!searchPMSchedule.getSchedule_number().isEmpty())
				predicates.add(cb.equal(fromFr.get("scheduleNumber"),
						searchPMSchedule.getSchedule_number()));

		if (searchPMSchedule.getUserReferenceNo() != null)
			if (!searchPMSchedule.getUserReferenceNo().isEmpty())
				predicates.add(cb.equal(fromFr.get("userRefNumber"),
						searchPMSchedule.getUserReferenceNo()));

		Expression<Date> dateColumnPath = fromFr.<Date> get("startDate");
		Expression<Date> date2 = fromFr.<Date> get("endDate");
		if ((searchPMSchedule.getStartDate() != null)) {
			predicates.add(cb.greaterThanOrEqualTo(dateColumnPath,
					searchPMSchedule.getStartDate()));
		}
		if ((searchPMSchedule.getEndDate() != null)) {
			predicates.add(cb.lessThanOrEqualTo(date2,
					searchPMSchedule.getEndDate()));
		}

		if (searchPMSchedule.getEquipment() != null)
			predicates.add(cb.equal(join1.get("id"),
					searchPMSchedule.getEquipment().getId()));

		cq.select(fromFr).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromFr.get("startDate")),
				cb.desc(fromFr.get("scheduleNumber")));
		cq.distinct(true);
		
		 TypedQuery<Schedule> query = em.createQuery(cq);
		 System.out.println(query.unwrap(org.hibernate.Query.class).getQueryString());
		 return query;
	}



	@Override
	public List<Schedule> getAll(SearchPMSchedule searchPMSchedule, Integer from, Integer rows) {
		TypedQuery<Schedule> query = createSearchCriteriaQuery(searchPMSchedule);
		return query.setFirstResult(from).setMaxResults(rows).getResultList();
	}

	@Override
	public List<Task> listTasks(Schedule schedule)
	{
		
		TypedQuery<Task> d = (TypedQuery<Task>) em.createQuery("Select c from Task c where c.schedule.id=:id", Task.class);
		d.setParameter("id", schedule.getId());
		return d.getResultList();
	}

	@Override
	public void remove(Task t) 
	{
        Task t1 = em.find(Task.class, t.getId());
        
		em.remove(t1);
				
	}

	@Override
	public Boolean update(Schedule schedule) 
	{
		TypedQuery<Schedule> d = (TypedQuery<Schedule>) em
				.createQuery("update Schedule t set t.scheduleDate =:ssd, t.startDate=:sd, t.endDate=:ed, t.briefDescription=:bd , t.detailedDescription=:dd ,t.frequency=:f, t.userRefNumber=:ref , t.taskPerformed=:task where t.id =:id");
		d.setParameter("ssd", schedule.getScheduleDate());
		d.setParameter("sd", schedule.getStartDate());
		System.out.println("end date: " + schedule.getEndDate());
		d.setParameter("ed", schedule.getEndDate());
		d.setParameter("bd", schedule.getBriefDescription());
		d.setParameter("dd", schedule.getDetailedDescription());
		d.setParameter("ref", schedule.getUserRefNumber());
		d.setParameter("f", schedule.getFrequency());
		d.setParameter("task", schedule.getTaskPerformed());
		d.setParameter("id", schedule.getId());
		d.executeUpdate();
		return true;
	}

	@Override
	public List<String> getScheduleTeamEmails(String pmsNo)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Technician> root = cq.from(Technician.class);
		Join<Technician,Team> schteam = root.join("team");
		Join<Team,Schedule> join = schteam.join("schedule");
		
		cq.multiselect(root.get("email"));
		cq.where(cb.equal(join.get("scheduleNumber"),pmsNo));
		
		return em.createQuery(cq).getResultList();
	}

	@Override
	public List<Schedule> getAllPMSchedules(Workspace w) {
		TypedQuery<Schedule> d = (TypedQuery<Schedule>)
				em.createQuery("Select distinct c from Schedule c  JOIN c.equipment e where e.workspace = :w");
		d.setParameter("w", w);
		
		return d.getResultList();
	}

	@Override
	public boolean delete(Long id) {
	try {
			
			@SuppressWarnings("unchecked")
			TypedQuery<Equipment> d = (TypedQuery<Equipment>) 
					em.createQuery("update Equipment t set t.schedule.id =null where t.schedule.id =:id");
			 
			d.setParameter("id", id); 
			d.executeUpdate();
			
			
			em.remove(em.find(Schedule.class, id));
		
			
			return true;
			}
				catch (Exception e) 
				{
					e.printStackTrace();
					return false;
				}
	}
	
	
	@Override
	public long getMaxScheduleId(){
		try{
		Query query=em.createQuery("Select Max(s.id) from Schedule s");
			return (long) query.getSingleResult();
		}catch (Exception e) {
return 0;		}
		
	}
}
