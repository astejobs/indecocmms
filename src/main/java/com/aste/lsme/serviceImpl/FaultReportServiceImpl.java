package com.aste.lsme.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.FaultReportDao;
import com.aste.lsme.domain.FaultReport;
import com.aste.lsme.domain.FaultReportSearch;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.FaultReportService;
import com.aste.lsme.webservicesDtos.FaultReportDto;

@Service
@Transactional
public class FaultReportServiceImpl implements FaultReportService{

	@Autowired 
	FaultReportDao faultReportDao;
	
	@Override
	public void persist(FaultReport faultReport) {
		
		faultReportDao.persist(faultReport);
	}

	@Override
	public FaultReport get(String frId) {
		return faultReportDao.get(frId);
	}

	@Override
	public FaultReport update(FaultReport faultReport) {
		return faultReportDao.update(faultReport);
	}

	@Override
	public void delete(String frId) {
		faultReportDao.delete(frId);
	}

	@Override
	public Long getMaxId(Workspace w) {
		return faultReportDao.getMaxId(w);
	}

	@Override
	public Long getFaultReportCount(FaultReportSearch frSearch) {
		return faultReportDao.getFaultReportCount(frSearch);
	}

	@Override
	public List<FaultReport> getFaultReportPaginated(int from, FaultReportSearch frSearch) {
		return faultReportDao.getFaultReportPaginated(from, frSearch);
	}

	@Override
	public List<FaultReport> getAllFaultReports(int from, FaultReportSearch frSearch) {
		return faultReportDao.getAllFaultReports(from, frSearch);
	}

	@Override
	public List<FaultReportDto> search(String search, String w) {
		return faultReportDao.search(search,w);
	}

	@Override
	public FaultReportDto getFaultDto(String frId) {
		return faultReportDao.getFaultDto(frId);
	}

	@Override
	public List<String> getBeforeImages(String frId) {
		return faultReportDao.getBeforeImages(frId);
	}

	@Override
	public List<String> getAfterImages(String frId) {
		return faultReportDao.getAfterImages(frId);
	}

	@Override
	public FaultReportDto getFaultDtoByEquipmentCode(String equipCode,List<String> statuses) {
		return faultReportDao.getFaultDtoByEquipmentCode(equipCode,statuses);
	}

	@Override
	public void updateArrivalAndResponseTime(LocalDateTime arrivalTime, String responseTime, String frId) {
		faultReportDao.updateArrivalAndResponseTime(arrivalTime, responseTime, frId);
	}

	@Override
	public void updateRestartTime(LocalDateTime restartTime, String frId) {
		faultReportDao.updateRestartTime(restartTime, frId);
	}

	@Override
	public FaultReportDto getFaultDtoEdit(String frId) {
		return faultReportDao.getFaultDtoEdit(frId);
	}

	@Override
	public List<FaultReportDto> getFaultsOnUser(String username, String role,String workspace,String search,String type) {
		return faultReportDao.getFaultsOnUser(username,role,workspace,search,type);
	}

	@Override
	public boolean updateStatus(String frId,String status) {
		return faultReportDao.updateStatus(frId,status);
	}

	@Override
	public List<FaultReportDto> searchQuotationupload(String w, String search) {
	return faultReportDao.searchQuotationupload(w,search);
	}

	@Override
	public List<FaultReportDto> searchpurchaseOrderupload(String w, String search) {
		return faultReportDao.searchpurchaseOrderupload(w,search);

	}

	@Override
	public void quotationStatus(String frId, String status) {
		 faultReportDao.quotationStatus(frId,status);
	}

	@Override
	public String findLocationCodeOfFaultReport(String frId) {
		return faultReportDao.findLocationCodeOfFaultReport(frId);
	}

	@Override
	public void updateFaultReportLocationScanned(String frId, boolean value) {
		faultReportDao.updateFaultReportLocationScanned(frId, value);
	}

	@Override
	public List<String> getRemarks(String frId) {
		return faultReportDao.getRemarks(frId);

	}

	
	
 
}
