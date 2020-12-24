package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.LabourRateSearchCriteria;
import com.aste.lsme.domain.Workspace;

public interface LabourRateService {
	
	
	public boolean persist (LabourRate labourrate, Workspace w);
	public LabourRate get(Long id);
	public void delete(Long id);
	public boolean update(LabourRate labourrate, Workspace w);
	public List<LabourRate> getWorkspaceLabourrate(Workspace w);
	public List<LabourRate> getAll();
	public List<LabourRate> getLabourratePaginated(int from, Workspace w);
	public Long getlabourrateCount(Workspace w);
	public long count(LabourRateSearchCriteria labourrateSearch, Workspace w);
	public List<LabourRate> getSearchList(int from, LabourRateSearchCriteria labourrateSearch, Workspace w);
	public List<LabourRate> get(Long[] id);

}
