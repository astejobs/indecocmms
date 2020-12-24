package com.aste.lsme.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.SelfHelpdao;
import com.aste.lsme.domain.AdhocReport;
import com.aste.lsme.domain.AdhocReportColumnFilterCondition;
import com.aste.lsme.domain.AdhocReportColumnFilterGroup;
import com.aste.lsme.domain.AdhocReportColumnSortOrder;
import com.aste.lsme.domain.AdhocReportForm;
import com.aste.lsme.domain.AdhocReportTableColumn;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.SelfHelpReportService;

@Service
@Transactional
public class SelfHelpReportServiceImpl implements SelfHelpReportService {
	
	@Autowired
	SelfHelpdao selfhelpdao;
	
	public void addAdhocReport(AdhocReport adhocReport) throws Exception
	{
		selfhelpdao.addAdhocReport(adhocReport);
		
	}
	public List<AdhocReportForm> getReportTypes()
	 {
		return selfhelpdao.getReportTypes();
		   
	 }
	
	public void updateAdhocReport(AdhocReport adhocReport) throws Exception{
		
		 if(adhocReport.getColumnList() != null) {
		 for(AdhocReportColumnFilterCondition adhoccolumns : adhocReport.getColumnList()){
			 adhoccolumns.setAdhocReport(selfhelpdao.findAdhocByid(adhocReport.getId()));
			 selfhelpdao.persist(adhoccolumns);
			 
		  }
		 }
		 
		 if(adhocReport.getSort() != null) {
         for(AdhocReportColumnSortOrder adhoccolumnsortorder : adhocReport.getSort()){
        	 adhoccolumnsortorder.setAdhocReport(selfhelpdao.findAdhocByid(adhocReport.getId()));
        	 selfhelpdao.persist(adhoccolumnsortorder);
			 
		  }
		 }
         
		 if(adhocReport.getCriteria() != null) {
         for(AdhocReportColumnFilterGroup adhocreportfiltergroup : adhocReport.getCriteria()){
        	 adhocreportfiltergroup.setAdhocReport(selfhelpdao.findAdhocByid(adhocReport.getId()));
			 selfhelpdao.persist(adhocreportfiltergroup);
		  }
		 }
		 selfhelpdao.updateAdhocReport(adhocReport);
	}
	
		public List<AdhocReportTableColumn> getColumns(){
			return selfhelpdao.getColumns();
		}
		
		public List<AdhocReport> getWorkspacebasedAdhocReports(Workspace w){
			return selfhelpdao.getWorkspacebasedAdhocReports(w);
		}
		
		public void editSelfhelpReport(AdhocReport adhocReport) throws Exception{
			selfhelpdao.deleteadhoccolumn(adhocReport.getId());
			selfhelpdao.deleteadhocfiltergroup(adhocReport.getId());
			selfhelpdao.deleteadhocsortorder(adhocReport.getId());
			if(adhocReport.getColumnList() != null){
			for(AdhocReportColumnFilterCondition adhoccolumns : adhocReport.getColumnList()){
				 adhoccolumns.setAdhocReport(selfhelpdao.findAdhocByid(adhocReport.getId()));
				 selfhelpdao.persist(adhoccolumns);
				 
			 }
			}
			 
			if(adhocReport.getSort() != null){
	         for(AdhocReportColumnSortOrder adhoccolumnsortorder : adhocReport.getSort()){
	        	 System.out.println(adhoccolumnsortorder.getGroupBy()+"0-0-00--0-0-0-0-0-0-0-0-0-0-0-0-0");
	        	 adhoccolumnsortorder.setAdhocReport(selfhelpdao.findAdhocByid(adhocReport.getId()));
	        	 selfhelpdao.persist(adhoccolumnsortorder);
				 
			 }
			}
	        if(adhocReport.getCriteria() != null) {
	         for(AdhocReportColumnFilterGroup adhocreportfiltergroup : adhocReport.getCriteria()){
	        	 adhocreportfiltergroup.setAdhocReport(selfhelpdao.findAdhocByid(adhocReport.getId()));
				 selfhelpdao.persist(adhocreportfiltergroup);
			 }
	        }
			 selfhelpdao.updateAdhocReport(adhocReport);
			
			
		}
		@Override
		public AdhocReport findAdhocByid(Integer id) {
			return selfhelpdao.findAdhocByid(id);
		}
       
		public List<Object[]> search(AdhocReport adhocReport,Date d1,Date d2,String flag){
			 return selfhelpdao.search(adhocReport, d1, d2,flag);
		 }
		 
	    public List<AdhocReport> getAdhocOnType(Long id,Workspace w){
			 return selfhelpdao.getAdhocOnType(id,w);
		 }
	    
	    public void deleteAdhocReport(Integer id){
	    	selfhelpdao.deleteadhoccolumn(id);
			selfhelpdao.deleteadhocfiltergroup(id);
			selfhelpdao.deleteadhocsortorder(id);
			
			selfhelpdao.deleteAdhocReport(id);
	    	
	    }
	    
	    public List<FaultReport> getFaultReportsOnId(List<Long> ids){
	    	return selfhelpdao.getFaultReportsOnId(ids);
	    }
		 

}
