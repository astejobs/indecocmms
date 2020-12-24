package com.aste.lsme.daoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.SelfHelpdao;
import com.aste.lsme.domain.AdhocReport;
import com.aste.lsme.domain.AdhocReportColumnFilterCondition;
import com.aste.lsme.domain.AdhocReportColumnFilterGroup;
import com.aste.lsme.domain.AdhocReportColumnSortOrder;
import com.aste.lsme.domain.AdhocReportForm;
import com.aste.lsme.domain.AdhocReportTableColumn;
import com.aste.lsme.domain.Constants;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Workspace;

@Repository
public class SelfHelpReportDaoImpl implements SelfHelpdao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addAdhocReport(AdhocReport adhocReport) throws Exception{
		
		em.persist(adhocReport);
		
	}
	
	
	public AdhocReport findAdhocByid(Integer id){
		return em.find(AdhocReport.class, id);
	}
	
   @SuppressWarnings("unchecked")
    public List<AdhocReportForm> getReportTypes(){
	   Query query = em.createQuery("Select r from  AdhocReportForm r");
	   return query.getResultList();
		
		 
		
	}
   
   public void updateAdhocReport(AdhocReport adhocReport) throws Exception{
		em.merge(adhocReport);
	}
   
   
   @SuppressWarnings("unchecked")
public List<AdhocReportTableColumn> getColumns(){
		List<Integer> statusList=Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,16,18,19,20,22,23,24,33,44,45,46,50,58,61);
        Query query = em.createQuery("Select  c from  AdhocReportTableColumn c where c.id in (:statusList)")
        		.setParameter("statusList", statusList);
		 return query.getResultList();
	}

   public void persist(AdhocReportColumnFilterCondition adhocReportColumnFilterCondition){
	  
	   em.persist(adhocReportColumnFilterCondition);
	  
   }
   
   public void persist(AdhocReportColumnSortOrder adhovreportcolumnsortorder){
	   em.persist(adhovreportcolumnsortorder);
	   
   }
   
   public void persist(AdhocReportColumnFilterGroup adhocReportColumnFilterGroup){
	   em.persist(adhocReportColumnFilterGroup);
   }
   
   @SuppressWarnings("unchecked")
   public List<AdhocReport> getWorkspacebasedAdhocReports(Workspace w){
	   Query query = em.createQuery("Select a from AdhocReport a where a.workspace=:w")
			   .setParameter("w", w);
	   return query.getResultList();
	   
   }
   
   public void deleteadhoccolumn(Integer id){
	   
		Query query = em.createQuery("Delete  from AdhocReportColumnFilterCondition c where c.adhocReport.id=:id")
				.setParameter("id", id);
		query.executeUpdate();
	   
	  
   }
   
   public void deleteadhocsortorder(Integer id){
	   Query query = em.createQuery("Delete  from AdhocReportColumnSortOrder c where c.adhocReport.id=:id")
			   .setParameter("id", id);
	   query.executeUpdate();
	   
   }
   
   public void deleteadhocfiltergroup(Integer id){
	   Query query = em.createQuery("Delete  from AdhocReportColumnFilterGroup c where c.adhocReport.id=:id")
			   .setParameter("id", id);
	   query.executeUpdate();
   }
	
   public List<Object[]> search(AdhocReport adhocReport,Date d1,Date d2,String flag){
	  CriteriaBuilder cb = em.getCriteriaBuilder(); 
	  CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
	  Root<FaultReport> f = q.from(FaultReport.class);
	  q.multiselect(getSelectionList( cb, adhocReport, f,flag)).where(cb.and(getpredicates(cb,adhocReport, f,d1,d2).toArray(new Predicate[0])));
	  q.orderBy(getOrderBy( cb, adhocReport, f));
	  for (AdhocReportColumnSortOrder sortcols :  adhocReport.getSort()) {
		  if(sortcols.getGroupBy().equals("true"))
		      q.groupBy(f.get( sortcols.getColumn().getColumnName()));
		       
		  }
	  return em.createQuery(q).getResultList();
	  
	  
	   
	   
   }
   
  
   private   List<Selection<?>>  getSelectionList(CriteriaBuilder cb,AdhocReport adhocReport, Root<FaultReport> f,String flag){
	   
	   List<Selection<?>> s = new LinkedList<Selection<?>>();
	   if(flag != null){
	      s.add(f.get("id"));
	   }
	   for (AdhocReportColumnFilterCondition selectcols :  adhocReport.getColumnList()) {
		     if(selectcols.getTableColumn().getColumnFilterInputSource().equals("Reference Table")){
                 if(selectcols.getTableColumn().getColumnName().equals("locId"))
		              s.add(f.get("location").get("name")); 
                 else if(selectcols.getTableColumn().getColumnName().equals("deptId"))
		              s.add(f.get("department").get("name")); 
                 else if(selectcols.getTableColumn().getColumnName().equals("bldgId"))
		    		 s.add(f.get("building").get("name"));
                 else if(selectcols.getTableColumn().getColumnName().equals("faultCodeId"))
		    		 s.add(f.get("faultCategory").get("name"));
                 else if(selectcols.getTableColumn().getColumnName().equals("priorityId"))
		    		 s.add(f.get("priority").get("name"));
                 else if(selectcols.getTableColumn().getColumnName().equals("costCenter"))
		    		 s.add(f.get("costCenter").get("costCenterName"));
                 else if(selectcols.getTableColumn().getColumnName().equals("equipment"))
		    		 s.add(f.get("equipment").get("name"));
		     } else
		    	 s.add(f.get(selectcols.getTableColumn().getColumnName()));
		 }
	   
	   
	   
	   return s;
   }
   
   private List<Predicate> getpredicates(CriteriaBuilder cb,AdhocReport adhocReport, Root<FaultReport> f,Date d1, Date d2){
	   List<Predicate> predicatelist = new ArrayList<Predicate>();
	   
	   
	   for (AdhocReportColumnFilterGroup  adhocReportColumnFilterGroup :  adhocReport.getCriteria()) {
		    if(adhocReportColumnFilterGroup.getConditionn().equals("Equal")){
		    	if(adhocReportColumnFilterGroup.getTableColumn().getColumnFilterInputSource().equals("Reference Table")){
		    		if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("locId"))
		    			 predicatelist.add(cb.equal(f.get("location").get("id"),
		 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("deptId"))
		    			 predicatelist.add(cb.equal(f.get("department").get("id"),
		 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("bldgId"))
			    		 predicatelist.add(cb.equal(f.get("building").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("faultCodeId"))
			    		 predicatelist.add(cb.equal(f.get("faultCategory").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("priorityId"))
			    		 predicatelist.add(cb.equal(f.get("priority").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("costCenter"))
			    		 predicatelist.add(cb.equal(f.get("costCenter").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("equipment"))
			    		 predicatelist.add(cb.equal(f.get("equipment").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    	
		    	}else
		    		predicatelist.add(cb.equal(f.get(adhocReportColumnFilterGroup.getTableColumn().getColumnName()),
			    			adhocReportColumnFilterGroup.getValuee()));
		    }
		    else if(adhocReportColumnFilterGroup.getConditionn().equals("Not")){
		    	if(adhocReportColumnFilterGroup.getTableColumn().getColumnFilterInputSource().equals("Reference Table")){
		    		if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("locId"))
		    			 predicatelist.add(cb.notEqual(f.get("location").get("id"),
		 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("deptId"))
		    			 predicatelist.add(cb.notEqual(f.get("department").get("id"),
		 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("bldgId"))
			    		 predicatelist.add(cb.notEqual(f.get("building").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("faultCodeId"))
			    		 predicatelist.add(cb.notEqual(f.get("faultCategory").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("priorityId"))
			    		 predicatelist.add(cb.notEqual(f.get("priority").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("costCenter"))
			    		 predicatelist.add(cb.notEqual(f.get("costCenter").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("equipment"))
			    		 predicatelist.add(cb.notEqual(f.get("equipment").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    	
		    	}else
		    		
		    	predicatelist.add(cb.notEqual(f.get(adhocReportColumnFilterGroup.getTableColumn().getColumnName()),
		    			adhocReportColumnFilterGroup.getValuee()));
		    }
		    else if(adhocReportColumnFilterGroup.getConditionn().equals("Like")){
		    	if(adhocReportColumnFilterGroup.getTableColumn().getColumnFilterInputSource().equals("Reference Table")){
		    		if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("locId"))
		    			 predicatelist.add(cb.equal(f.get("location").get("id"),
		 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("deptId"))
		    			 predicatelist.add(cb.equal(f.get("department").get("id"),
		 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("bldgId"))
			    		 predicatelist.add(cb.equal(f.get("building").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("faultCodeId"))
			    		 predicatelist.add(cb.equal(f.get("faultCategory").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("priorityId"))
			    		 predicatelist.add(cb.equal(f.get("priority").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("costCenter"))
			    		 predicatelist.add(cb.equal(f.get("costCenter").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    		else if(adhocReportColumnFilterGroup.getTableColumn().getColumnName().equals("equipment"))
			    		 predicatelist.add(cb.equal(f.get("equipment").get("id"),
			 		    			adhocReportColumnFilterGroup.getValuee()));
		    	
		    	}else
		    	 predicatelist.add(cb.like(f.<String>get(adhocReportColumnFilterGroup.getTableColumn().getColumnName()),
		    			"%"+adhocReportColumnFilterGroup.getValuee()+"%"));
		    }
		     }
	      predicatelist.add(cb.between(f.<Date>get("reportedDate"),d1, d2));
	     return predicatelist;
	}
   
   private List<Order> getOrderBy(CriteriaBuilder cb,AdhocReport adhocReport, Root<FaultReport> f){
	   List<Order> order = new ArrayList<Order>();
	   
	   for(AdhocReportColumnSortOrder  sortorder : adhocReport.getSort()){
		   
		   if(sortorder.getSortOrder().equals("ASC")){
			   if(sortorder.getColumn().getColumnFilterInputSource().equals("Reference Table")){
				    if(sortorder.getColumn().getColumnName().equals("locId"))
			    		 order.add(cb.asc(f.get("location").get("name")));
				    else if(sortorder.getColumn().getColumnName().equals("deptId"))
			    		 order.add(cb.asc(f.get("department").get("name")));
				    else if(sortorder.getColumn().getColumnName().equals("bldgId"))
			        	  order.add(cb.asc(f.get("building").get("name")));
				    else if(sortorder.getColumn().getColumnName().equals("faultCodeId"))
			    		 order.add(cb.asc(f.get("faultCategory").get("name")));
				    else if(sortorder.getColumn().getColumnName().equals("priorityId"))
			    		 order.add(cb.asc(f.get("priority").get("name")));
				    else if(sortorder.getColumn().getColumnName().equals("costCenter"))
			    		 order.add(cb.asc(f.get("costCenter").get("costCenterName")));
				    else if(sortorder.getColumn().getColumnName().equals("equipment"))
			    		 order.add(cb.asc(f.get("equipment").get("name")));
			    	} else
			    	order.add(cb.asc(f.get(sortorder.getColumn().getColumnName())));
		      }
		       else if(sortorder.getSortOrder().equals("DESC")){
		    	   if(sortorder.getColumn().getColumnFilterInputSource().equals("Reference Table")){
		    	   if(sortorder.getColumn().getColumnName().equals("locId"))
			    		 order.add(cb.desc(f.get("location").get("name")));
		    	   else if(sortorder.getColumn().getColumnName().equals("deptId"))
			    		 order.add(cb.desc(f.get("department").get("name")));
		    	   else if(sortorder.getColumn().getColumnName().equals("bldgId"))
			        	  order.add(cb.desc(f.get("building").get("name")));
		    	   else if(sortorder.getColumn().getColumnName().equals("faultCodeId"))
			    		 order.add(cb.desc(f.get("faultCategory").get("name")));
		    	   else if(sortorder.getColumn().getColumnName().equals("priorityId"))
			    		 order.add(cb.desc(f.get("priority").get("name")));
		    	   else if(sortorder.getColumn().getColumnName().equals("costCenter"))
			    		 order.add(cb.desc(f.get("costCenter").get("costCenterName")));
		    	   else if(sortorder.getColumn().getColumnName().equals("equipment"))
			    		 order.add(cb.desc(f.get("equipment").get("name")));
			    	} else
			        order.add(cb.desc(f.get(sortorder.getColumn().getColumnName())));
		   }
	   }
	   
	   return order;
   }
   
   public List<AdhocReport> getAdhocOnType(Long id,Workspace w){
	   Query query = em.createQuery("Select a from  AdhocReport a where a.adhocReportForm.id=:id and a.workspace.id=:w")
			   .setParameter("id", id).setParameter("w", w.getId());
	   return query.getResultList();
   }
   
   public void deleteAdhocReport(Integer id){
	   AdhocReport adhocReport = em.find(AdhocReport.class, id);
	   em.remove(adhocReport);
   }
   
   public List<FaultReport> getFaultReportsOnId(List<Long> ids){
	   Query query = em.createQuery("Select f from FaultReport f where f.id in (:ids)")
			   .setParameter("ids", ids);
	    return  query.getResultList();
   }
  

}
