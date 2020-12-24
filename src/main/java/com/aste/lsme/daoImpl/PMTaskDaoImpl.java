package com.aste.lsme.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.PMTaskDao;
import com.aste.lsme.domain.ChecklistProperty;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Content;
import com.aste.lsme.domain.EmailTaskDTO;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.ScheduledEquipment;
import com.aste.lsme.domain.SearchPMTask;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.TaskChecklist;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.aste.lsme.webservicesDtos.TaskDTO;



@Repository
public class PMTaskDaoImpl implements PMTaskDao{

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public boolean addList(List<Task> tasks) 
	{
		for (Task preventiveMaintenanceTask : tasks) 
		{
			em.persist(preventiveMaintenanceTask);
		}
		return true;
	}	
	
	@Override
	public List<Task> getSearchResult(SearchPMTask searchCriteria, int from) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Task> criteriaQuery = cb.createQuery(Task.class);
		Root<Task> pmtRoot = criteriaQuery.from(Task.class);
		List<Predicate> predicateList = getPredicatesOnCriteria(searchCriteria,
				cb, pmtRoot);
		criteriaQuery.where(cb.and(predicateList.toArray(new Predicate[0])));
		criteriaQuery.distinct(true);
		return em.createQuery(criteriaQuery).setFirstResult(from).setMaxResults(10).getResultList();
	}


		private List<Predicate> getPredicatesOnCriteria(SearchPMTask searchCriteria, CriteriaBuilder cb,
				Root<Task> pmtRoot) {
			
			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Join<Task,Schedule> join = pmtRoot.join("schedule");

			Join<Schedule, Equipment> join1 = join.join("equipment");
			if (searchCriteria.getWorkspace() != null)
				predicateList.add(cb.equal(join1.get("workspace"), searchCriteria.getWorkspace()));
			if (searchCriteria.getSchedule() != null)
				predicateList.add(cb.equal(pmtRoot.<Schedule> get("schedule"),
						searchCriteria.getSchedule()));
			if (searchCriteria.getTask_number() != "")
				predicateList.add(cb.like(
						(Expression<String>) pmtRoot.<String> get("task_number"),
						"%" + searchCriteria.getTask_number() + "%"));
			if (searchCriteria.getStatus() != null
					&& searchCriteria.getStatus().length > 0) {
				List<String> statusList = Arrays.asList(searchCriteria.getStatus());
				predicateList.add(cb.in(pmtRoot.get("status")).value(statusList));
			}
			if (searchCriteria.getEquipment() != null)
				predicateList.add(cb.equal(pmtRoot.<Equipment> get("equipment")
						, searchCriteria.getEquipment()));
			
			Expression<Date> dateColumnPath = pmtRoot.<Date> get("actualScheduleDate");
			if (searchCriteria.getStartDueDate() != null){
				
				predicateList.add(cb.greaterThanOrEqualTo(dateColumnPath,
						searchCriteria.getStartDueDate()));
			}
		
			if (searchCriteria.getEndDueDate() != null){
				
				predicateList.add(cb.lessThanOrEqualTo(pmtRoot.<Date> get("actualScheduleDate"),
						searchCriteria.getEndDueDate()));
			}
			
			
			if(!((searchCriteria.getValue().isEmpty() || searchCriteria.getSlot().isEmpty() || searchCriteria.getElapse().isEmpty())))
					{
				
					if(searchCriteria.getElapse().equals("Within"))
					{
						
				if(searchCriteria.getSlot().equals("Mins"))
				      {
					javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					System.out.println(Integer.parseInt(searchCriteria.getValue())*60 +"nahida!!!!!!!");
					 predicateList.add(cb.le(timeToSec,Integer.parseInt(searchCriteria.getValue())*60));
					
				       }
				else if(searchCriteria.getSlot().equals("Hours"))
			          {
			    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					 predicateList.add(cb.le(timeToSec,Integer.parseInt(searchCriteria.getValue())*60*60));
					
			          }
				else if(searchCriteria.getSlot().equals("Days"))
			          {
			    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					 predicateList.add(cb.le(timeToSec,Integer.parseInt(searchCriteria.getValue())*24*60*60));
			    	
			          }
				else  
		             {
			    	
			    	
			    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					
					 predicateList.add(cb.le(timeToSec,Integer.parseInt(searchCriteria.getValue())*30*24*60*60));
		    	
		             }
				}
					else if(searchCriteria.getElapse().equals("GreaterThan"))
					{
						if(searchCriteria.getSlot().equals("Mins"))
					      {
						javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						 predicateList.add(cb.greaterThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*60));
						
					       }
					else if(searchCriteria.getSlot().equals("Hours"))
				          {
				    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						 predicateList.add(cb.greaterThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*60*60));
						
				          }
					else if(searchCriteria.getSlot().equals("Days"))
				          {
				    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						 predicateList.add(cb.greaterThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*24*60*60));
				    	
				          }
					else  
			             {
				    	
				    	
				    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						
						 predicateList.add(cb.greaterThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*30*24*60*60));
			    	
			             }
					}
					
					if(searchCriteria.getElapse().equals("EqualTo"))
					{
				if(searchCriteria.getSlot().equals("Mins"))
				      {
					javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					 predicateList.add(cb.equal(timeToSec,Integer.parseInt(searchCriteria.getValue())*60));
					
				       }
				else if(searchCriteria.getSlot().equals("Hours"))
			          {
			    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					 predicateList.add(cb.equal(timeToSec,Integer.parseInt(searchCriteria.getValue())*60*60));
					
			          }
				else if(searchCriteria.getSlot().equals("Days"))
			          {
			    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					 predicateList.add(cb.equal(timeToSec,Integer.parseInt(searchCriteria.getValue())*24*60*60));
			    	
			          }
				else  
		             {
			    
			    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
				            "TIMEDIFF",
				            java.sql.Time.class,
				            pmtRoot.<Date>get( "completedDate" ),
				            pmtRoot.<Date>get( "actualScheduleDate" ) );
					
					javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
				            "TIME_TO_SEC",
				            Integer.class,
				            timeDiff );
					
					
					 predicateList.add(cb.equal(timeToSec,Integer.parseInt(searchCriteria.getValue())*30*24*60*60));
		    	
		             }
				}
					else
					{
						if(searchCriteria.getSlot().equals("Mins"))
					      {
						javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						 predicateList.add(cb.lessThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*60));
						
					       }
					else if(searchCriteria.getSlot().equals("Hours"))
				          {
				    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						 predicateList.add(cb.lessThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*60*60));
						
				          }
					else if(searchCriteria.getSlot().equals("Days"))
				          {
				    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						 predicateList.add(cb.lessThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*24*60*60));
				    	
				          }
					else  
			             {
				    	
				    	
				    	javax.persistence.criteria.Expression<java.sql.Time> timeDiff = cb.function(
					            "TIMEDIFF",
					            java.sql.Time.class,
					            pmtRoot.<Date>get( "completedDate" ),
					            pmtRoot.<Date>get( "actualScheduleDate" ) );
						
						javax.persistence.criteria.Expression<Integer> timeToSec = cb.function(
					            "TIME_TO_SEC",
					            Integer.class,
					            timeDiff );
						
						
						 predicateList.add(cb.lessThan(timeToSec,Integer.parseInt(searchCriteria.getValue())*30*24*60*60));
			    	
			             }
					}
						
			}
			
			    

			return predicateList;
		}

	@Override
	public Task find(Long taskid) {
		return em.find(Task.class, taskid);
	}

	@Override
	public boolean update(Task pmTask) {
		
		try {
			em.merge(pmTask);
			} 
			catch (Exception ex) 
				{
				ex.printStackTrace();
				return false;
				}
		
		return true;
	
	}

	@Override
	public long getSearchCount(SearchPMTask searchCriteria) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<Task> pmtRoot = criteriaQuery.from(Task.class);
		
		List<Predicate> predicateList = getPredicatesOnCriteria(searchCriteria,
				cb, pmtRoot);
		criteriaQuery.select(cb.countDistinct(pmtRoot));
		criteriaQuery.where(cb.and(predicateList.toArray(new Predicate[0])));
		
		Long size= em.createQuery(criteriaQuery).getSingleResult();
		
		return size;
	}

	@Override
	  public List<TaskChecklist> listallChecklists(Task pmtask) {
	    TypedQuery<TaskChecklist> d = (TypedQuery<TaskChecklist>) em
	        .createQuery("Select l from TaskChecklist l where l.task.id=:id");
	    d.setParameter("id", pmtask.getId());
	   
	    return d.getResultList();
	  }

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailTaskDTO> getEmailTasks(String pmsNo, Workspace w) throws ParseException {
		

			  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		      Calendar c = Calendar.getInstance();
		      Date fromDate = sdf.parse("01-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
		      Date toDate = sdf.parse("31-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
		      Query query = em.createQuery("Select New com.aste.lsme.domain.EmailTaskDTO(t.task_number,t.equipment.equipmentCode,t.equipment.name,t.actualScheduleDate) from Task t where (t.actualScheduleDate BETWEEN :fromDate and :toDate) and t.status = :open and t.schedule.scheduleNumber = :pmsNo ");
		      query.setParameter("fromDate", fromDate);
		      query.setParameter("toDate", toDate);
		      query.setParameter("open", Constants.OPEN);
		      query.setParameter("pmsNo", pmsNo);
		      return query.getResultList();
		
	}



	@Override
	public Task findByTaskId(String taskId) 
	{
		@SuppressWarnings("unchecked")
		TypedQuery<Task> d = (TypedQuery<Task>) em.createQuery("Select l from Task l where l.task_number=:id");
		d.setParameter("id", taskId);
		if (!d.getResultList().isEmpty()) 
		{
			System.out.println("size is not  null");
			return d.getResultList().get(0);

		}
		
		return null;
	}

	@Override
	  public List<TaskChecklist> gettaskchecklist(Long id) {
	    Query q= em.createQuery("select t from TaskChecklist t where t.task.id=:id")
	         .setParameter("id", id);
	    return q.getResultList();
	  }

	@Override
	public  List<ChecklistProperty> getchecklistpropertyontask(Long id){
	     Query q= em.createQuery("select t.checklistProperty from TaskChecklist t where t.id=:id")
	         .setParameter("id", id);
	     return q.getResultList();
	  }

	@Override
	public List<Schedule> getAll(Workspace w) {
		TypedQuery<Schedule> d = (TypedQuery<Schedule>) em
				.createQuery("Select distinct c from Schedule c  JOIN c.equipment e where e.workspace = :w");
		d.setParameter("w", w);
	
		return d.getResultList();
	}
	
	
	public List<Task> getSearchResult(SearchPMTask searchCriteria, int from,
			int to) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Task> criteriaQuery = cb.createQuery(Task.class);
		Root<Task> pmtRoot = criteriaQuery.from(Task.class);
		List<Predicate> predicateList = getPredicatesOnCriteria(searchCriteria,
				cb, pmtRoot);
		criteriaQuery.where(cb.and(predicateList.toArray(new Predicate[0])));
		criteriaQuery.distinct(true);
		return em.createQuery(criteriaQuery).setMaxResults(to)
				.setFirstResult(from).getResultList();
	}
	 @Override
		public Long count(ScheduledEquipment searchPMSchedule) throws ParseException {
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        CriteriaBuilder cb = this.em.getCriteriaBuilder();
		        CriteriaQuery criteriaQuery = cb.createQuery();
		        Root task = criteriaQuery.from((Class)Task.class);
		        List<Predicate> predicateList = this.getpredicates(cb, criteriaQuery, task, searchPMSchedule );
		        criteriaQuery.select((Selection)task);
		        criteriaQuery.where((Expression)cb.and(predicateList.toArray(new Predicate[0])));
		        
		        System.out.println(this.em.createQuery(criteriaQuery).getResultList());
		        Long xz=(long) em.createQuery(criteriaQuery).getResultList().size();
		     
		        return xz;
		      

		}

	
	 @Override
		public List<Task> getAll(ScheduledEquipment searchPMSchedule, Integer from, Integer rows) throws ParseException {
		//	TypedQuery<Task> query = createSearchCriteriaQuery2(searchPMSchedule);
			//return query.setFirstResult(from).setMaxResults(rows).getResultList();

			System.out.println("i m in getall");
		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        CriteriaBuilder cb = this.em.getCriteriaBuilder();
		        CriteriaQuery criteriaQuery = cb.createQuery();
		        Root task = criteriaQuery.from((Class)Task.class);
		        List<Predicate> predicateList = this.getpredicates(cb, criteriaQuery, task, searchPMSchedule);
		        criteriaQuery.select((Selection)task);
		        criteriaQuery.where((Expression)cb.and(predicateList.toArray(new Predicate[0])));

				System.out.println("i m in after getall");
		        System.out.println(this.em.createQuery(criteriaQuery).getResultList());
		        return this.em.createQuery(criteriaQuery).setFirstResult(from).setMaxResults(rows).getResultList();
		}
	 
	 public List<Task> getScheduledEquipment(ScheduledEquipment seq) throws ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        CriteriaBuilder cb = this.em.getCriteriaBuilder();
	        CriteriaQuery criteriaQuery = cb.createQuery();
	        Root task = criteriaQuery.from((Class)Task.class);
	        List<Predicate> predicateList = this.getpredicates(cb, criteriaQuery, task, seq);
	        criteriaQuery.select((Selection)task);
	        criteriaQuery.where((Expression)cb.and(predicateList.toArray(new Predicate[0])));
	        System.out.println(this.em.createQuery(criteriaQuery).getResultList());
	        return this.em.createQuery(criteriaQuery).getResultList();
	    }
	 
	 
	 public List<Predicate> getpredicates(CriteriaBuilder cb, CriteriaQuery cq, Root<Task> task, ScheduledEquipment sch) throws ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        ArrayList<Predicate> predicates = new ArrayList<Predicate>();
	        System.out.println(String.valueOf(dateFormat.format(sch.getFrom())) + "-----------" + dateFormat.format(sch.getTo()));
	        predicates.add(cb.equal((Expression)task.get("equipment").get("workspace").get("id"), (Object)sch.getWorkspace().getId()));
	        predicates.add(cb.between((Expression)task.get("actualScheduleDate"), (Comparable)dateFormat.parse(dateFormat.format(sch.getFrom())), (Comparable)dateFormat.parse(dateFormat.format(sch.getTo()))));
	        if (sch.getEquipmentTypeid() != null) {
	            predicates.add(cb.equal((Expression)task.get("equipment").get("assetSubtype").get("assetType").get("id"), (Object)sch.getEquipmentTypeid()));
	        }
	        if (sch.getEquipmentSubtypeid() != null) {
	            predicates.add(cb.equal((Expression)task.get("equipment").get("assetSubtype").get("id"), (Object)sch.getEquipmentSubtypeid()));
	            System.out.println("--------in sch.getEquipmentSubTypeid()");
	        }
	        if (sch.getEquipmentid() != null) {
	            predicates.add(cb.equal((Expression)task.get("equipment").get("id"), (Object)sch.getEquipmentid()));
	            System.out.println("--------in sch.getEquipmentid()");
	        }
	        if (sch.getBuildingid() != null) {
	            predicates.add(cb.equal((Expression)task.get("equipment").get("building").get("id"), (Object)sch.getBuildingid()));
	            System.out.println("--------in sch.getBuildingid()");
	        }
	        if (sch.getLocationid() != null) {
	            predicates.add(cb.equal((Expression)task.get("equipment").get("location").get("id"), (Object)sch.getLocationid()));
	            System.out.println("--------in sch.getLocationid()");
	        }
	        if (!sch.getTaskToBePerformed().isEmpty()) {
	            predicates.add(cb.equal((Expression)task.get("schedule").get("taskPerformed"), (Object)sch.getTaskToBePerformed()));
	            System.out.println("--------in sch.getTaskToBePerformed()" + sch.getTaskToBePerformed());
	        }
	        if (sch.getFrequencyOfService() != null) {
	            predicates.add(cb.equal((Expression)task.get("schedule").get("frequency"), (Object)sch.getFrequencyOfService()));
	            System.out.println("--------in sch.getFrequencyOfService()");
	        }
	        if (sch.getStatus().length != 0) {
	            predicates.add((Predicate)cb.in((Expression)task.get("status")).value(Arrays.asList(sch.getStatus())));
	            System.out.println("--------in sch.getStatus()");
	        }
	        return predicates;
	    }

	@Override
	public TaskChecklist findById(Long checklistid) {
		
		return em.find(TaskChecklist.class,checklistid);
		
	}

	@Override
	public void update(TaskChecklist checklist) {

		em.merge(checklist);
	}

	@Override
	public TaskDTO findTask(Long id) {
		
		
		Query query = em.
	
		  createQuery("Select New  com.aste.lsme.webservicesDtos.TaskDTO(t.id,t.task_number,t.status,t.remarks,t.schedule.scheduleNumber,t.schedule.briefDescription,t.equipment.equipmentCode,t.equipment.location.id,t.equipment.location.name,t.equipment.building.id,t.equipment.building.name,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.dueDate,t.endDate) from Task t where t.id= :id"
		  );
		 		 query.setParameter("id", id);
		
		return (TaskDTO) query.getSingleResult();
	
	}

	@Override
	public void updateImage(Content con,boolean beforeImage)  {
		
		
		long num = Long.parseLong(con.getId());
		System.out.println("IDDDDDDD=="+num);
		
		if(beforeImage) {
			
			System.out.println("true===="+beforeImage);
			Query query= em.createQuery("Update Task t set t.beforeImage = :beforeImage where t.id = :id")
				.setParameter("id",num )
				.setParameter("beforeImage", con.getData());
		 query.executeUpdate();
		}
		else
		{
			System.out.println("false===="+beforeImage);
			Query query=em.createQuery("Update Task t set t.afterImage = :afterImage where t.id = :id")
					.setParameter("id",num )
					.setParameter("afterImage", con.getData());
			 query.executeUpdate();
		}
        
		
	}

	@Override
	public List<TaskDTO> getTasks(String username, String role, String w, String search) {
		
		
		  CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<TaskDTO> criteriaQuery = cb.createQuery(TaskDTO.class);
		  Root<Task> fromFr = criteriaQuery.from(Task.class);
		  if(!search.isEmpty())
		  {
			  List<Predicate> predicateList = getPredicatesLike(cb, criteriaQuery, fromFr,search);
			  if(role.equals(Constants.ROLE_TECHNICIAN))
			  {
			       criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.equal(fromFr.get("equipment").get("workspace").get("workspaceId"), w)));

				}
			  else if(role.equals(Constants.ROLE_MANAGING_AGENT))
			  {
					  criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.notEqual(fromFr.get("status"),Constants.OPEN),cb.equal(fromFr.get("equipment").get("workspace").get("workspaceId"), w)));
				
			  }
			 
			        criteriaQuery.select(cb.construct(TaskDTO.class,fromFr.get("id"), fromFr.get("task_number"), fromFr.get("status"),	fromFr.get("equipment").get("name"),
					fromFr.get("equipment").get("location").get("name"), fromFr.get("equipment").get("building").get("name"),
					fromFr.get("scheduleDate"),fromFr.get("beforeImage"),fromFr.get("afterImage") ));
			 
			  return em.createQuery(criteriaQuery).getResultList();

			  
		 }
		  else 

		          return null;
	}		
	  
	 private List<Predicate> getPredicatesLike(CriteriaBuilder cb, CriteriaQuery cq,
				Root<Task> fromFr,String search){

		    	List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.like(fromFr.<String>get("task_number"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.<String>get("status"),"%"+search+"%"));				
				predicates.add(cb.like(fromFr.get("equipment").get("location").<String>get("name"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.get("equipment").get("building").<String>get("name"),"%"+ search+"%"));
			
				return predicates;
		}

}

