package com.aste.lsme.daoImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.FaultReportDao;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.FaultReportSearch;
import com.aste.lsme.domain.Schedule;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.FaultReportDto;


@Repository
public class FaultReportDaoImpl implements FaultReportDao {

	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public void persist(FaultReport faultReport) {
		em.persist(faultReport);
	}

	@Override
	public FaultReport get(String frId) {
		Query query = em.createQuery("Select f from FaultReport f where f.frId =:frId").setParameter("frId", frId);
		return (FaultReport) query.getSingleResult();
	}

	@Override
	public FaultReport update(FaultReport faultReport) {
		return em.merge(faultReport);
	}

	@Override
	public void delete(String frId) {
		em.remove(em.find(FaultReport.class, frId));
	}
	
	@Override
	public Long getMaxId(Workspace w){
		
		Query query = em.createQuery("Select count(f) from FaultReport f where f.workspace = :w")
									.setParameter("w", w);
		return (Long) query.getSingleResult();
		
	}
	
	@Override
	public List<FaultReport> getFaultReportPaginated(int from,FaultReportSearch frSearch) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FaultReport> cq = cb.createQuery(FaultReport.class);
		Root<FaultReport> fromFr = cq.from(FaultReport.class);
		List<Predicate> predicates = getPredicates(cb, cq, fromFr, frSearch);

		cq.select(fromFr).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromFr.get("activationTime")));
		if(frSearch.getGroupBy()!=null){
			if(frSearch.getGroupBy().equals("maintenanceGroup"))
				cq.groupBy(fromFr.get("maintGrp"));
			else if(frSearch.getGroupBy().equals("faultCategory"))
				cq.groupBy(fromFr.get("faultCategory"));
		}	
		TypedQuery<FaultReport> query = em.createQuery(cq);
		return query.setFirstResult(from).setMaxResults(10).getResultList();
		
	}
	
	@Override
	public List<FaultReport> getAllFaultReports(int from,FaultReportSearch frSearch) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FaultReport> cq = cb.createQuery(FaultReport.class);
		Root<FaultReport> fromFr = cq.from(FaultReport.class);
		List<Predicate> predicates = getPredicates(cb, cq, fromFr, frSearch);

		cq.select(fromFr).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(fromFr.get("activationTime")));
		if(frSearch.getGroupBy()!=null){
			if(frSearch.getGroupBy().equals("maintenanceGroup"))
				cq.groupBy(fromFr.get("maintGrp"));
			else if(frSearch.getGroupBy().equals("faultCategory"))
				cq.groupBy(fromFr.get("faultCategory"));
		}	
		TypedQuery<FaultReport> query = em.createQuery(cq);
		return query.getResultList();
		
	}
	
	@Override
	public Long getFaultReportCount(FaultReportSearch frSearch){
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<FaultReport> fromFr = criteriaQuery.from(FaultReport.class);
		List<Predicate> predicateList = getPredicates(cb, criteriaQuery,
				fromFr, frSearch);
		criteriaQuery.select(cb.count(fromFr));
		criteriaQuery.where(cb.and(predicateList.toArray(new Predicate[0])));
		System.err.println(em.createQuery(criteriaQuery).getSingleResult()+"--------------------------");
		return em.createQuery(criteriaQuery).getSingleResult();
		 
	}
	
	
	
	private List<Predicate> getPredicates(CriteriaBuilder cb,CriteriaQuery<?> cq,
			Root<FaultReport> fromFr, FaultReportSearch frSearch) {

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(fromFr.<Workspace> get("workspace"),frSearch.getWorkspace()));

		if (frSearch.getFrId() != null)
			if (!frSearch.getFrId().isEmpty())
				predicates.add(cb.equal(fromFr.get("frId"),frSearch.getFrId()));

		if (frSearch.getClientFrId() != null)
			if (!frSearch.getClientFrId().isEmpty())
				predicates.add(cb.equal(fromFr.get("clientFrId"),frSearch.getClientFrId()));

		if (frSearch.getFromDate() != null)
			predicates.add(cb.greaterThanOrEqualTo(fromFr.<LocalDateTime>get("activationTime"),frSearch.getFromDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDateTime()));	
	
		if(frSearch.getToDate()!= null)		
			predicates.add(cb.lessThanOrEqualTo(fromFr.<LocalDateTime>get("activationTime"),frSearch.getToDate().toInstant()
			      .atZone(ZoneId.systemDefault())
				      .toLocalDateTime().plusDays(1)));	

		if (frSearch.getFaultCategory() != null)
			predicates.add(cb.equal(fromFr.get("faultCategory"),frSearch.getFaultCategory()));

		if (frSearch.getBuilding() != null)
			predicates.add(cb.equal(fromFr.get("building"), frSearch.getBuilding()));

		if (frSearch.getLocation() != null)
			predicates.add(cb.equal(fromFr.get("location"), frSearch.getLocation()));

		if(frSearch.getEquipment()!=null)
			predicates.add(cb.equal(fromFr.get("equipment").get("id"), frSearch.getEquipment()));
		
		if(frSearch.getMaintGrp()!=null)
			predicates.add(cb.equal(fromFr.get("maintGrp").get("id"),frSearch.getMaintGrp()));
		
		if(frSearch.getRequestorName()!=null)
			if(!frSearch.getRequestorName().isEmpty())
				predicates.add(cb.equal(fromFr.get("requestorName"),frSearch.getRequestorName()));
		
		if (frSearch.getStatus() != null)
			predicates.add(cb.in(fromFr.get("status")).value(
					Arrays.asList(frSearch.getStatus())));
		return predicates;

	}
	
	 public List<FaultReportDto> search(String search,String w){

		  CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<FaultReportDto> criteriaQuery = cb.createQuery(FaultReportDto.class);
		  Root<FaultReport> fromFr = criteriaQuery.from(FaultReport.class);
		  if(!search.isEmpty()){
		  List<Predicate> predicateList = getPredicatesLike(cb, criteriaQuery,
		  fromFr, search);
		 
		  criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));
		  criteriaQuery.select(cb.construct(FaultReportDto.class, fromFr.get("frId"),fromFr.get("requestorName"),fromFr.get("faultCategory").<String>get("name"),
		  fromFr.get("status"),fromFr.get("activationTime"),fromFr.get("location").get("name"),fromFr.get("building").get("name"),fromFr.get("quotationStatus")));
		  return em.createQuery(criteriaQuery).getResultList();

		  }else 
			  return null;

	 }
	  private List<Predicate> getPredicatesLike(CriteriaBuilder cb, CriteriaQuery cq,
				Root<FaultReport> fromFr,String search){

		    	List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.like(fromFr.<String>get("frId"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.<String>get("clientFrId"),"%"+search+"%"));				
				predicates.add(cb.like(fromFr.get("faultCategory").<String>get("name"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.get("building").<String>get("name"),"%"+ search+"%"));
				//predicates.add(cb.like(fromFr.<String>get("status"),"%"+search+"%"));
			
				return predicates;
		}

	@Override
	public FaultReportDto getFaultDto(String frId) {
		
		Query query=em.

				createQuery("Select New com.aste.lsme.webservicesDtos.FaultReportDto(t.frId,t.requestorName,t.building,t.location,t.requestorContactNo,t.priority,t.faultCategory,t.maintGrp,t.actionTaken,t.observation,t.labourHrs,t.status,t.department,t.faultCategoryDesc,t.quotationStatus) from FaultReport t where t.frId= :frId").setParameter("frId", frId);

			return (FaultReportDto) query.getSingleResult();
	}
	
	@Override
	public List<String> getBeforeImages(String frId) {
	
		Query query=em.
				createQuery("Select distinct(i) from FaultReport f JOIN f.beforeImage i where f.frId= :frId").setParameter("frId", frId);
        
		
	      return   query.getResultList();
       
	}

	@Override
	public List<String> getAfterImages(String frId) {
	
		Query query=em. 
				createQuery("Select distinct(i) from FaultReport f JOIN f.afterImage i where f.frId= :frId").setParameter("frId", frId);

		
		return query.getResultList(); 
	}
	
	
	@Override
	public FaultReportDto getFaultDtoByEquipmentCode(String equipCode,List<String> statuses) {
		
		Query query=em.
						createQuery("Select New com.aste.lsme.webservicesDtos.FaultReportDto(t.frId,t.requestorName,t.building,t.location,t.requestorContactNo,t.priority,t.faultCategory,t.maintGrp,t.actionTaken,t.observation,t.labourHrs,t.status,t.division,t.department,t.equipment.id,t.equipment.name,t.activationTime,t.arrivalTime,t.restartTime,t.faultCategoryDesc,t.locationDesc,t.equipment.geoLocation,t.quotationStatus,t.locationScanned,t.responseTime,t.pauseTime,t.completionTime,t.acknowledgementTime,t.downTime) from FaultReport t where t.equipment.equipmentCode= :equipmentCode"
								+ " and t.status IN :statuses")
										.setParameter("equipmentCode", equipCode).setParameter("statuses",statuses);
		
		
			try {
				if(query.getResultList().size()>0)
					return (FaultReportDto) query.getResultList().get(0);
				else return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
	}
	 
	@Override
	public void updateArrivalAndResponseTime(LocalDateTime arrivalTime,String responseTime,String frId){
		Query query= em.createQuery("Update FaultReport f set f.arrivalTime = :arrivalTime,f.responseTime = :responseTime where f.frId = :frId")
									.setParameter("arrivalTime", arrivalTime).setParameter("responseTime", responseTime)
									.setParameter("frId", frId);
		query.executeUpdate();
		
	}
	
	@Override
	public void updateRestartTime(LocalDateTime restartTime,String frId){
		Query query= em.createQuery("Update FaultReport f set f.restartTime = :restartTime where f.frId = :frId")
									.setParameter("restartTime", restartTime)
									.setParameter("frId", frId);
		query.executeUpdate();
		
	}

	@Override
	public FaultReportDto getFaultDtoEdit(String frId) {
		Query query=em.
				createQuery("Select New com.aste.lsme.webservicesDtos.FaultReportDto(t.frId,t.requestorName,t.building,t.location,t.requestorContactNo,t.priority,t.faultCategory,t.maintGrp,t.actionTaken,t.observation,t.labourHrs,t.status,t.division,t.department,t.activationTime,t.arrivalTime,t.restartTime,t.faultCategoryDesc,t.locationDesc,t.quotationStatus,t.locationScanned,t.responseTime,t.pauseTime,t.completionTime,t.acknowledgementTime,t.downTime) from FaultReport t where t.frId= :frId").setParameter("frId", frId);

			return (FaultReportDto) query.getSingleResult();
	}

	@Override
	public List<FaultReportDto> getFaultsOnUser(String username, String role, String w, String search,String type) {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<FaultReportDto> criteriaQuery = cb.createQuery(FaultReportDto.class);
		  Root<FaultReport> fromFr = criteriaQuery.from(FaultReport.class);
		  Join<FaultReport, UserDetail> join = fromFr.join("attendedBy");

		  if(!search.isEmpty()){
		  List<Predicate> predicateList = getPredicatesLike(cb, criteriaQuery,
		  fromFr,search);
		  
		  if(role.equals(Constants.ROLE_TECHNICIAN)){
			  		if(type.equals(Constants.ACTIVE)){
			  			List<Predicate> statusPredicate =new ArrayList<>();
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.OPEN));
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.PAUSE));
			  			criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.or(statusPredicate.toArray(new Predicate[0])),cb.equal(join.get("username"),username),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));		
			  		}
			  		else{
			  			List<Predicate> statusPredicate =new ArrayList<>();
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.PAUSE_REQUESTED));
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.COMPLETED));
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.CLOSED));

			  			criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.or(statusPredicate.toArray(new Predicate[0])),cb.equal(join.get("username"),username),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));		  			
			  		}
		  }else if(role.equals(Constants.ROLE_MANAGING_AGENT)){
			  		if(type.equals(Constants.ACTIVE)){
			  			List<Predicate> statusPredicate =new ArrayList<>();
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.PAUSE_REQUESTED));
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.COMPLETED));
			  			criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.or(statusPredicate.toArray(new Predicate[0])),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));
			  		}
			  		else{
			  			List<Predicate> statusPredicate =new ArrayList<>();
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.CLOSED));
			  			statusPredicate.add(cb.equal(fromFr.get("status"),Constants.PAUSE));
			  			criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.or(statusPredicate.toArray(new Predicate[0])),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));
			  		}
					
			}
	 
		  criteriaQuery.select(cb.construct(FaultReportDto.class, fromFr.get("frId"),fromFr.get("requestorName"),fromFr.get("faultCategory").<String>get("name"),
		  fromFr.get("status"),fromFr.get("activationTime"),fromFr.get("location").get("name"),fromFr.get("building").get("name"),fromFr.get("quotationStatus")));
		  criteriaQuery.distinct(true);
		  return em.createQuery(criteriaQuery).getResultList();

		  
		  }else 
			  return null;
	}

	
		  
		  
	
	@Override
	public boolean updateStatus(String frId,String status) {
		try{
			if(status.equals(Constants.PAUSE)){
				Query query= em.createQuery("Update FaultReport f set f.status = :status,f.pauseTime=:pauseTime  where f.frId = :frId")
				.setParameter("status", status)
				.setParameter("frId", frId).setParameter("pauseTime", LocalDateTime.now());
		         query.executeUpdate();
			}else{
				Query query= em.createQuery("Update FaultReport f set f.status = :status where f.frId = :frId")
				.setParameter("status", status)
				.setParameter("frId", frId);
				query.executeUpdate();			 
			}
				return true;
		}catch (Exception e) {
				e.printStackTrace();
				return false;
		}
	}

	
	@Override
	public List<FaultReportDto> searchQuotationupload(String w, String search) {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<FaultReportDto> criteriaQuery = cb.createQuery(FaultReportDto.class);
		  Root<FaultReport> fromFr = criteriaQuery.from(FaultReport.class);
		  if(!search.isEmpty()){
		  List<Predicate> predicateList = getPredicatesLike(cb, criteriaQuery,
		  fromFr, search);
		  
		  List<Predicate> statusPredicate =new ArrayList<>();
			statusPredicate.add(cb.equal(fromFr.get("quotationStatus"),Constants.QUOTATION_REJECTED));
			statusPredicate.add(cb.isNull(fromFr.get("quotationStatus")));
		 
			criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.or(statusPredicate.toArray(new Predicate[0])),cb.equal(fromFr.get("status"),Constants.PAUSE),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));

		  
		  criteriaQuery.select(cb.construct(FaultReportDto.class, fromFr.get("frId"),fromFr.get("requestorName"),fromFr.get("faultCategory").<String>get("name"),
		  fromFr.get("status"),fromFr.get("activationTime"),fromFr.get("location").get("name"),fromFr.get("building").get("name"),fromFr.get("quotationStatus")));
		  return em.createQuery(criteriaQuery).getResultList();

		  }else 
			  return null;
	}

	@Override
	public List<FaultReportDto> searchpurchaseOrderupload(String w, String search) {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<FaultReportDto> criteriaQuery = cb.createQuery(FaultReportDto.class);
		  Root<FaultReport> fromFr = criteriaQuery.from(FaultReport.class);
		  if(!search.isEmpty()){
		  List<Predicate> predicateList = getPredicatesLike(cb, criteriaQuery,
		  fromFr, search);
		 
			criteriaQuery.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.equal(fromFr.get("status"),Constants.PAUSE_REQUESTED),cb.isNotNull(fromFr.get("quotation")),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));

		  
		  criteriaQuery.select(cb.construct(FaultReportDto.class, fromFr.get("frId"),fromFr.get("requestorName"),fromFr.get("faultCategory").<String>get("name"),
		  fromFr.get("status"),fromFr.get("activationTime"),fromFr.get("location").get("name"),fromFr.get("building").get("name"),fromFr.get("quotationStatus")));
		  return em.createQuery(criteriaQuery).getResultList();

		  }else 
			  return null;
	}

	@Override
	public void quotationStatus(String frId, String status) {
		Query query= em.createQuery("Update FaultReport f set f.quotationStatus = :status where f.frId = :frId")
				.setParameter("frId", frId).setParameter("status", status);
         query.executeUpdate();
	}

	@Override
	public String findLocationCodeOfFaultReport(String frId){
		Query query =em.createQuery("Select f.location.locationCode from FaultReport f where f.frId = :frId")
							.setParameter("frId", frId);
		if(query.getResultList().size()>0)
			query.getFirstResult();
		return null;
	}
	@Override
	public void updateFaultReportLocationScanned(String frId,boolean value){
		Query query= em.createQuery("Update FaultReport f set f.locationScanned = :value where f.frId = :frId")
				.setParameter("frId", frId).setParameter("value", value);
         query.executeUpdate();
	}

	@Override
	public List<String> getRemarks(String frId) {

		Query query =em.createQuery("Select distinct(r) from FaultReport f JOIN f.remarks r where f.frId = :frId")
				.setParameter("frId", frId);
if(query.getResultList().size()>0)
return query.getResultList();
else
return null;
	}
	
}
