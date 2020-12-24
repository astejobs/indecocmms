package com.aste.lsme.service;

import java.util.Date;
import java.util.List;

import com.aste.lsme.domain.AdhocReport;
import com.aste.lsme.domain.AdhocReportForm;
import com.aste.lsme.domain.AdhocReportTableColumn;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.Workspace;

public interface SelfHelpReportService {
	
	public void addAdhocReport(AdhocReport adhocReport) throws Exception;
	public List<AdhocReportForm> getReportTypes();
	public void updateAdhocReport(AdhocReport adhocReport) throws Exception;
	public List<AdhocReportTableColumn> getColumns();
	public List<AdhocReport> getWorkspacebasedAdhocReports(Workspace w);
	public AdhocReport findAdhocByid(Integer id);
	public void editSelfhelpReport(AdhocReport adhocReport) throws Exception;
	public List<Object[]> search(AdhocReport adhocReport,Date d1,Date d2,String flag);
	public List<AdhocReport> getAdhocOnType(Long id,Workspace w);
	public void deleteAdhocReport(Integer id);
	 public List<FaultReport> getFaultReportsOnId(List<Long> ids);
}
