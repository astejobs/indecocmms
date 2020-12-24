package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.LabourRate;
import com.aste.lsme.domain.LabourRateSearchCriteria;
import com.aste.lsme.domain.Workspace;

public interface LabourRateDao {
	
	boolean persist(LabourRate labourrate, Workspace w);
	public LabourRate get(Long id);
	public void delete(Long id);
	boolean update(LabourRate labourrate, Workspace w);
	public List<LabourRate> getWorkspaceLabourrate(Workspace w);
	public List<LabourRate> getAll();	

	List<LabourRate> getLabourratePaginated(int from, Workspace w);
	public List<LabourRate> get(Long[] id);
	Long getlabourrateCount(Workspace w);
	long count(LabourRateSearchCriteria labourrateSearch, Workspace w);
	List<LabourRate> getSearchList(int from, LabourRateSearchCriteria labourrateSearch, Workspace w);

}
