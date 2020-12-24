package com.aste.lsme.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.EquipmentDao;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentDtoSearch;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Task;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.utils.Encryptor;
import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.FaultReportDto;
import com.aste.lsme.webservicesDtos.TaskDTO;



@Repository
public class EquipmentDaoImpl implements EquipmentDao {

	private EntityManager em;

	public EntityManager getEntitymanager() {
		return em;
	}

	@PersistenceContext
	public void setEntitymanager(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> getAll() {
		
		Query q = em.createQuery("select l from Equipment l"); 
		return (List<Equipment>) q.getResultList();
	}

	@Override
	public String generateEquipmentCode(String equipmentType) {
 TypedQuery<Long> d = (TypedQuery<Long>) 
		                  em.createQuery("Select Max(l.id) from Equipment l ", Long.class);
		                  return equipmentType + (d.getResultList().get(0) + 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetType> getEqpTypes(Workspace w) {
		
		 TypedQuery<AssetType> q = (TypedQuery<AssetType>) 
                 em.createQuery("Select  l from AssetType l ");
                 return q.getResultList();
	}
	
	
	@Override
	public long getEquipmentCount(EquipmentSearchCriteria equipmentSearch,Workspace w) {
		
		TypedQuery<EquipmentDtoSearch> query=createSearchCriteria(equipmentSearch);
		return	query.getResultList().size();
	}

	@Override
	public List<EquipmentDtoSearch> getEquipmentPaginated(int from,EquipmentSearchCriteria equipmentSearch,Workspace w) {
		TypedQuery<EquipmentDtoSearch> query=createSearchCriteria(equipmentSearch);
		return query.setFirstResult(from).setMaxResults(10).getResultList();
	}

	
	@Override
	public List<EquipmentDtoSearch> getAllEquipments(EquipmentSearchCriteria equipmentSearch) {
		TypedQuery<EquipmentDtoSearch> query=createSearchCriteria(equipmentSearch);
		return	query.getResultList();
	}
	
	private TypedQuery<EquipmentDtoSearch> createSearchCriteria(EquipmentSearchCriteria equipmentSearch) {
		
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<EquipmentDtoSearch> cq=cb.createQuery(EquipmentDtoSearch.class);
		Root<Equipment> equipment = cq.from(Equipment.class);
		List<Predicate> predicates=new ArrayList<Predicate>();
		
		predicates.add(cb.equal(equipment.get("workspace"),equipmentSearch.getWorkspace()));
		//System.out.println(equipmentSearch.getAssetType().getId()+"jjjjjjjjjjjjjjjj");
	if(equipmentSearch.getAssetType()!=null)
			predicates.add(cb.equal(equipment.get("assetType").get("id"),equipmentSearch.getAssetType().getId()));
		
		if (equipmentSearch.getAssetSubtype() != null)
			predicates.add(cb.equal(equipment.get("assetSubtype").get("id"),equipmentSearch.getAssetSubtype().getId()));
		if(equipmentSearch.getName() !=null)
			if(!equipmentSearch.getName().isEmpty())
			predicates.add(cb.equal(equipment.get("name"),Encryptor.encrypt(equipmentSearch.getName())));	

		if (equipmentSearch.getBuilding() != null)
			predicates.add(cb.equal(equipment.get("building").get("id"), equipmentSearch.getBuilding().getId()));

		if (equipmentSearch.getLocation() != null)
			predicates.add(cb.equal(equipment.get("location").get("id"), equipmentSearch.getLocation().getId()));

		/*if (equipmentSearch.getLifeSpan() != null)
			predicates.add(cb.equal(equipment.get("life"), equipmentSearch.getLifeSpan()));
		
		if(equipmentSearch.getFromDate() != null)
			predicates.add(cb.greaterThanOrEqualTo(equipment.<Date>get("installationDate"),equipmentSearch.getFromDate()));
		
		if(equipmentSearch.getToDate() != null)
			predicates.add(cb.lessThanOrEqualTo(equipment.<Date>get("installationDate"),equipmentSearch.getToDate()));
		*/
		if(equipmentSearch.isCheckSchedule())
			predicates.add(cb.isNull(equipment.get("schedule")));
		
		predicates.add(cb.equal(equipment.get("workspace"), equipmentSearch.getWorkspace()));
		
		  cq.where(cb.and(predicates.toArray(new Predicate[] {})));

		 cq.select(cb.construct(EquipmentDtoSearch.class,
                 equipment.get("id"), equipment.get("assetType"),equipment.get("assetSubtype"), equipment.get("name"), 
                 equipment.get("building"), 
                 equipment.get("location"),equipment.get("modelNo"),equipment.get("remarks"),equipment.get("monthOfExpiry"),
                 equipment.get("fcuModel"),equipment.get("quantity"),equipment.get("equipmentCode"),equipment.get("reCertificatedOn"),
                 equipment.get("serialNo")));

	
		
		
		
		//cq.select(equipment).where(predicates.toArray(new Predicate[] {}));
		//cq.orderBy(cb.desc(equipment.get("building")), cb.desc(equipment.get("name")));
		return em.createQuery(cq);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Equipment> getWorkspaceEquipment(Workspace w){
		
		Query query = em.createQuery("Select e from Equipment e where e.workspace = :w")
				.setParameter("w", w);
		return query.getResultList();
	}

	@Override
	public List<AssetType> getPMEquipTypes(Workspace w) {
		@SuppressWarnings("unchecked")
		TypedQuery<AssetType> tQ = (TypedQuery<AssetType>) em
				.createQuery(
						"Select distinct E from AssetType E");
		return tQ.getResultList();
	}


	@Override
	public List<Equipment> getPredictiveScheduledEquipments(EquipmentSearchCriteria equipmentSearch, int from, int to) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Equipment> cq = cb.createQuery(Equipment.class);
		Root<Equipment> equipRoot = cq.from(Equipment.class);
		List<Predicate> predicates = getPredicatesOnCriteria(equipmentSearch, cb, cq, equipRoot);
		cq.select(equipRoot).where(cb.and(predicates.toArray(new Predicate[0])));
		
		List<Equipment> tl = em.createQuery(cq).setFirstResult(from).setMaxResults(to).getResultList();
		
		return tl;
	}


	@Override
	public Boolean saveEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		em.persist(equipment);
		return true;
	}

	@Override
	public Equipment find(Long id) {
		// TODO Auto-generated method stub
		return em.find(Equipment.class, id);
	}

	@Override
	public Boolean update(Equipment equipment) {
		// TODO Auto-generated method stub
		
	try{
		em.merge(equipment);
		return true;
	}
	catch(Exception e)
	{
		return false;
	}

	}
	

	 public List<Equipment> getAll(Long id, Workspace w) {
	        Query query = this.em.createQuery("select e from Equipment e  where e.assetSubtype.id =:id AND e.workspace =:w");
	        query.setParameter("id", (Object)id);
	        query.setParameter("w", w);
	        return query.getResultList();
	    }
	

	public List<Equipment> getEquipmentBySubtype(AssetSubtype assetsubtype)
    {
	 Query query = em.createQuery("Select e from Equipment e where e.assetSubtype = :assetsubtype")
				.setParameter("assetsubtype", assetsubtype);
		return  query.getResultList();
	 
    }

	public List<Predicate> getPredicatesOnCriteria(EquipmentSearchCriteria equipmentSearch,
			CriteriaBuilder cb,CriteriaQuery cq, Root<Equipment> equipRoot)
	{
		List<Predicate> predicates = new ArrayList<Predicate>();
		System.out.println("w=" + equipRoot.get("workspace") + "  search-w="+ equipmentSearch.getWorkspace());
		
		predicates.add(cb.equal(equipRoot.get("workspace").get("id"),
				equipmentSearch.getWorkspace().getId()));
		
					
		if (equipmentSearch.getBuilding() != null )
			predicates.add(cb.equal(equipRoot.<Building> get("building"),
					equipmentSearch.getBuilding()));
		if (equipmentSearch.getAssetSubtype() != null
				&& equipmentSearch.getAssetSubtype().getId() != null)
			predicates.add(cb.equal(equipRoot.get("assetSubtype").get("id"),
					equipmentSearch.getAssetSubtype().getId()));
		
		
		if (equipmentSearch.getAssetType() != null
				&& equipmentSearch.getAssetType().getId()!= null)
			predicates.add(cb.equal(equipRoot.get("assetSubtype").get("assetType").get("id"),
					equipmentSearch.getAssetType().getId()));
			
		
		
		if (equipmentSearch.getLocation() != null)
			predicates.add(cb.equal(equipRoot.<Location> get("location"),
					equipmentSearch.getLocation()));
		
		
		if (!equipmentSearch.getName().equals(""))
			if (equipmentSearch.getName() != null)
				if (!equipmentSearch.getName().isEmpty())
					predicates
							.add(cb.like((Expression<String>) equipRoot
									.<String> get("name"), "%"
									+ Encryptor.encrypt(equipmentSearch.getName())
									+ "%"));

	
	
		return predicates;
	}

	@Override
	public Long countPredictiveScheduledEquipments(EquipmentSearchCriteria equipmentSearchCriteria) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
		Root<Equipment> eqRoot = criteriaQuery.from(Equipment.class);
		List<Predicate> predicateList = getPredicatesOnCriteria(equipmentSearchCriteria, cb, criteriaQuery, eqRoot);
		criteriaQuery.select(cb.count(eqRoot));
		criteriaQuery.where(cb.and(predicateList.toArray(new Predicate[0])));
		Long x = em.createQuery(criteriaQuery).getSingleResult();

		return x;
	}

	@Override
	public void delete(Long id) {
		Equipment eq=em.find(Equipment.class,id);
	    em.remove(eq);
		
	}


	@Override
	public List<TaskDTO> getAllTasks(String equipmentcode,String status) {
		List<String> list=Arrays.asList(Constants.OPEN,Constants.CLOSED,Constants.OVERDUE);
		
		
		/*
		 * Query query = em.
		 * createQuery("Select New  com.aste.lsme.webservicesDtos.TaskDTO(t.task_number,t.status,t.schedule,t.equipment,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.remarks) from Task t where t.equipment.equipmentCode= :equipmentcode and t.status in (:list)"
		 * );
		 */
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		Date todaysDate = null;//=new Date();
		try {
			todaysDate = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} System.out.println("statusss=="+status);
		Query query = null;
		
		if(status.equals(Constants.OPEN)){
			System.out.println("open=="+status);

		 query=em.
				createQuery("Select New com.aste.lsme.webservicesDtos.TaskDTO(t.id,t.task_number,t.status,t.schedule.scheduleNumber,t.equipment.equipmentCode,t.equipment.location.id,t.equipment.location.name,t.equipment.building.id,t.equipment.building.name,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.remarks) from Task t where t.equipment.equipmentCode= :equipmentcode and t.status= :status and t.actualScheduleDate= :todaysDate ");
		 query.setParameter("equipmentcode",equipmentcode); 
		    query.setParameter("status",status);
		    query.setParameter("todaysDate", todaysDate);
		}
		else if(status.equals(Constants.CLOSED)) {
			System.out.println("closed=="+status);
			 query=em.
				    createQuery("Select New com.aste.lsme.webservicesDtos.TaskDTO(t.id,t.task_number,t.status,t.schedule.scheduleNumber,t.equipment.equipmentCode,t.equipment.location.id,t.equipment.location.name,t.equipment.building.id,t.equipment.building.name,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.remarks) from Task t where t.equipment.equipmentCode= :equipmentcode and t.status= :status and t.completedDate= :todaysDate ");
			 query.setParameter("equipmentcode",equipmentcode); 
			    query.setParameter("status",status);
			    query.setParameter("todaysDate", todaysDate);
		}
		else if(status.equals(Constants.OVERDUE)) {
			System.out.println("overdue=="+status);

			 query=em.
					createQuery("Select New com.aste.lsme.webservicesDtos.TaskDTO(t.id,t.task_number,t.status,t.schedule.scheduleNumber,t.equipment.equipmentCode,t.equipment.location.id,t.equipment.location.name,t.equipment.building.id,t.equipment.building.name,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.remarks) from Task t where t.equipment.equipmentCode= :equipmentcode and t.status= :open and t.actualScheduleDate < :todaysDate ");
			 query.setParameter("equipmentcode",equipmentcode); 
			    query.setParameter("open",Constants.OPEN);
			    query.setParameter("todaysDate", todaysDate);
		}
		   
		   
			//  query.setParameter("todaysDate", formatter.format(todaysDate));
			  

				return query.getResultList();
			
	}

	@Override
	public EquipmentDTO getEquipmentByEquipCode(String equipmentcode) {
		
		Query query = em.
				  createQuery("Select New  com.aste.lsme.webservicesDtos.EquipmentDTO(e.equipmentCode,e.assetType.assetTypeName,e. name,e.location,e.building) from Equipment e where e.equipmentCode= :equipmentcode");
		 query.setParameter("equipmentcode", equipmentcode);
		
		return (EquipmentDTO) query.getSingleResult();

	}

	


	@Override
	public EquipmentDTO getEquipmentsByFrId(String frId) {
		Query query = em.
				  createQuery("Select New  com.aste.lsme.webservicesDtos.EquipmentDTO(e.equipment.name,e.equipment.id) from FaultReport e where e.frId= :frId");
		 query.setParameter("frId", frId);	
		 System.out.println(query.getResultList().size()+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		 if(query.getResultList().size()>0)
		return (EquipmentDTO) query.getSingleResult();
		 else
			 return null;
	
	}
	
	@Override
	public List<EquipmentDTO> getEquipmentsByBuildingIdAndLocationId(Long buildId,Long locId) {
		Query query = em.
				  createQuery("Select New  com.aste.lsme.webservicesDtos.EquipmentDTO(e.name,e.id) from Equipment e where e.building.id = :buildId and e.location.id = :locId")
				  				.setParameter("buildId", buildId).setParameter("locId", locId);
		
		return  query.getResultList();
	}

	@Override
	public List<TaskDTO> getTasksOnEquipmentCodeAndStatus(String equipmentcode, String status) {
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			Date todaysDate = null;//=new Date();
			try {
				todaysDate = sdf.parse(sdf.format(new Date()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} System.out.println("statusss=="+status);
			Query query = null;
			
			if(status.equals(Constants.OPEN)){
				System.out.println("open=="+status);

			 query=em.
					createQuery("Select New com.aste.lsme.webservicesDtos.TaskDTO(t.id,t.task_number,t.status,t.schedule.scheduleNumber,t.equipment.equipmentCode,t.equipment.location.id,t.equipment.location.name,t.equipment.building.id,t.equipment.building.name,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.remarks,t.beforeImage,t.afterImage) from Task t where t.equipment.equipmentCode= :equipmentcode and t.status= :status and t.scheduleDate= :todaysDate ");
			 query.setParameter("equipmentcode",equipmentcode); 
			    query.setParameter("status",status);
			    query.setParameter("todaysDate", todaysDate);
			}
			else if(status.equals(Constants.COMPLETED)) {
				System.out.println("completed=="+status);
				 query=em.
					    createQuery("Select New com.aste.lsme.webservicesDtos.TaskDTO(t.id,t.task_number,t.status,t.schedule.scheduleNumber,t.equipment.equipmentCode,t.equipment.location.id,t.equipment.location.name,t.equipment.building.id,t.equipment.building.name,t.scheduleDate,t.completedBy,t.completedDate,t.completedTime,t.remarks,t.beforeImage,t.afterImage) from Task t where t.equipment.equipmentCode= :equipmentcode and t.status= :status and t.scheduleDate= :todaysDate ");
				 query.setParameter("equipmentcode",equipmentcode); 
				    query.setParameter("status",status);
				    query.setParameter("todaysDate", todaysDate);
			}
		
			return query.getResultList();

	}

	@Override
	public List<EquipmentDTO> getEquipments(String w, String search) {
		 
		  CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<EquipmentDTO> cq = cb.createQuery(EquipmentDTO.class);
		  Root<Equipment> fromFr = cq.from(Equipment.class);
		  List<Predicate> predicateList = getPredicatesLike(cb, cq, fromFr,search);	
		  
		  
	       cq.where(cb.and(cb.or(predicateList.toArray(new Predicate[0])),cb.equal(fromFr.get("workspace").get("workspaceId"), w)));
	       cq.select(cb.construct(EquipmentDTO.class, fromFr.get("equipmentCode"),fromFr.get("assetType").<String>get("assetTypeName"),
	    		   fromFr.get("assetSubtype").<String>get("assetSubTypeName"),fromFr.get("name"),
	    		   fromFr.get("location").<String>get("name"),fromFr.get("building").<String>get("name"),fromFr.get("id") ));
			
			 
			  return em.createQuery(cq).getResultList();

	}

	 private List<Predicate> getPredicatesLike(CriteriaBuilder cb, CriteriaQuery cq,
				Root<Equipment> fromFr,String search){

		    	List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.like(fromFr.<String>get("name"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.get("assetType").<String>get("assetTypeName"),"%"+search+"%"));				
				predicates.add(cb.like(fromFr.get("assetSubtype").<String>get("assetSubTypeName"),"%"+search+"%"));
				predicates.add(cb.like(fromFr.get("building").<String>get("name"),"%"+ search+"%"));
				predicates.add(cb.like(fromFr.get("location").<String>get("name"),"%"+ search+"%"));

			
				return predicates;
		}
	
	
}

 

	
	

