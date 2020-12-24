package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;

public interface FaultCategoryService {

	boolean addFaultCategory(FaultCategory faultCategory, Workspace w);

	long getFaultCategoryCount(Workspace w);

List<FaultCategory> getfaultCategoryPaginated(int from, Workspace w);

FaultCategory get(Long id);

boolean update(FaultCategory faultCategory,Workspace w);
void delete(Long id);
public List<FaultCategory> getWorkspaceFaultCategory(Workspace w);

List<FaultCategory> getfaultCategories(Long workspaceId);







}
