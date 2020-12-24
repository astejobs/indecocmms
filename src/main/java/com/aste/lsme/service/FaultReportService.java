package com.aste.lsme.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.FaultReportSearch;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.FaultReportDto;

public interface FaultReportService {

	public void persist(FaultReport faultReport);
	public FaultReport get(String frId);
	public FaultReport update(FaultReport faultReport);
	public void delete(String frId); 
	public Long getMaxId(Workspace w);	
	public Long getFaultReportCount(FaultReportSearch frSearch);
	public List<FaultReport> getFaultReportPaginated(int from, FaultReportSearch frSearch);	
	public List<FaultReport> getAllFaultReports(int from,FaultReportSearch frSearch);
	public List<FaultReportDto> search(String search, String w);
	public FaultReportDto getFaultDto(String frId);
	public List<String> getBeforeImages(String frId);
	public List<String> getAfterImages(String frId);
	FaultReportDto getFaultDtoByEquipmentCode(String equipCode, List<String> statuses);
	void updateArrivalAndResponseTime(LocalDateTime arrivalTime, String responseTime, String frId);
	void updateRestartTime(LocalDateTime restartTime, String frId);
	public FaultReportDto getFaultDtoEdit(String frId);
	public List<FaultReportDto> getFaultsOnUser(String username, String role, String w, String search,String type);
	public boolean updateStatus(String frId,String status);
	public List<FaultReportDto> searchQuotationupload(String w, String search);
	public List<FaultReportDto> searchpurchaseOrderupload(String w, String search);
	public void quotationStatus(String frId, String status);
	String findLocationCodeOfFaultReport(String frId);
	void updateFaultReportLocationScanned(String frId, boolean value);
	public List<String> getRemarks(String frId);
}
