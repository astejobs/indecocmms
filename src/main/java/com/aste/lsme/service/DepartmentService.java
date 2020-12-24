package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.WorkspaceDto;

public interface DepartmentService {

	
	public boolean persist (Department department, Workspace w);
	public Department get(Long id);
	public void delete(Long id);
	public boolean update(Department department, Workspace w);
	public List<Department> getWorkspaceDepartments(Workspace w);
	public List<Department> getAll();
	public long getDepartmentCount(Workspace w);
	public List<Department> getDepartmentPaginated(int from, Workspace w);
	public List<Department> getDepartments(Long workspaceId);
}
