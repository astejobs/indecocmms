package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.LabourRateDao;
import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.LabourRateSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.LabourRateService;


@Service
@Transactional
public class LabourRateServiceImpl implements LabourRateService{

	
	@Autowired
	LabourRateDao labourrateDao;
	
	
	@Override
	public boolean persist(LabourRate labourrate, Workspace w) {
		return labourrateDao.persist(labourrate,w);
	}

	@Override
	public LabourRate get(Long id) {
		return labourrateDao.get(id);
	}

	@Override
	public void delete(Long id) {
		labourrateDao.delete(id);

		
	}

	@Override
	public boolean update(LabourRate labourrate, Workspace w) {
		return labourrateDao.update(labourrate,w);
	}

	@Override
	public List<LabourRate> getWorkspaceLabourrate(Workspace w) {
		return labourrateDao.getWorkspaceLabourrate(w);
	}

	@Override
	public List<LabourRate> getAll() {
		return labourrateDao.getAll();
	}

	@Override
	public List<LabourRate> getLabourratePaginated(int from, Workspace w) {
		return labourrateDao.getLabourratePaginated(from, w);
	}

	@Override
	public Long getlabourrateCount(Workspace w) {
		return labourrateDao.getlabourrateCount(w);
	}

	@Override
	public long count(LabourRateSearchCriteria labourrateSearch, Workspace w) {
		return  labourrateDao.count(labourrateSearch,w);
	}

	@Override
	public List<LabourRate> getSearchList(int from, LabourRateSearchCriteria labourrateSearch, Workspace w) {
		return  labourrateDao.getSearchList(from,labourrateSearch,w);
	}
	
	public List<LabourRate> get(Long[] id)
	{
		return labourrateDao.get(id);
	}

}
