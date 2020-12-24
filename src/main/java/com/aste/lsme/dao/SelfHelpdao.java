package com.aste.lsme.dao;

import java.util.Date;
import java.util.List;

import com.aste.lsme.domain.AdhocReport;
import com.aste.lsme.domain.AdhocReportColumnFilterCondition;
import com.aste.lsme.domain.AdhocReportColumnFilterGroup;
import com.aste.lsme.domain.AdhocReportColumnSortOrder;
import com.aste.lsme.domain.AdhocReportForm;
import com.aste.lsme.domain.AdhocReportTableColumn;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Workspace;

public interface SelfHelpdao {
	
	public void addAdhocReport(AdhocReport adhocReport) throws Exception;
	public List<AdhocReportForm> getReportTypes();
	public void updateAdhocReport(AdhocReport adhocReport) throws Exception;
	public List<AdhocReportTableColumn> getColumns();
	public void persist(AdhocReportColumnFilterCondition adhocReportColumnFilterCondition);
	public void persist(AdhocReportColumnSortOrder adhovreportcolumnsortorder);
	public void persist(AdhocReportColumnFilterGroup adhocReportColumnFilterGroup);
	public AdhocReport findAdhocByid(Integer id);
	public List<AdhocReport> getWorkspacebasedAdhocReports(Workspace w);
	public void deleteadhoccolumn(Integer id);
	public void deleteadhocsortorder(Integer id);
	public void deleteadhocfiltergroup(Integer id);
	public List<Object[]> search(AdhocReport adhocReport,Date d1,Date d2,String flag);
	public List<AdhocReport> getAdhocOnType(Long id,Workspace w);
	public void deleteAdhocReport(Integer id);
	 public List<FaultReport> getFaultReportsOnId(List<Long> ids);

}
