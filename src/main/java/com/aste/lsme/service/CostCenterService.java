package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.TechnicianDTO;



public interface CostCenterService {

	boolean addCostCenter(CostCenter costCenter, Workspace w);

	List<CostCenter> getcostCenter();

	Object get(Long id);

	boolean update(CostCenter costCenter, Workspace w);

	void delete(Long id);

	List<CostCenter> getWorkspaceCostCenter(Workspace w);


	long getCostCenterCount(Workspace w);
	List<CostCenter> getCostCenterPaginated(int from, Workspace w);

	List<CostCenter> findCostCenter(String w) ;

}
