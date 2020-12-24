package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Workspace;


public interface CostCenterDao {

	boolean addCostCenter(CostCenter costCenter, Workspace w);

	List<CostCenter> getcostCenter();

	CostCenter get(Long id);

	boolean updatecostCenter(CostCenter costCenter, Workspace w);

	void delete(Long id);

	long getCostCenterCount(Workspace w);

	List<CostCenter> getCostCenterPaginated(int from, Workspace w);

	List<CostCenter> getWorkspaceCostCenter(Workspace w);

	 List<CostCenter> findCostCenter(String w);
}
