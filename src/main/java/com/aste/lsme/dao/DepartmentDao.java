package com.aste.lsme.dao;

import java.util.List;


import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Location;
import com.aste.lsme.domain.Workspace;

public interface DepartmentDao {

	
	public boolean persist (Department department, Workspace w);
	public Department get(Long id);
	public void delete(Long id);
	public boolean update(Department department, Workspace w);
	public List<Department> getWorkspaceDepartments(Workspace w);
	public List<Department> getAll();	
	List<Department> getDepartmentPaginated(int from, Workspace w);
	Long getDepartmentCount(Workspace w);
	public List<Department> getDepartments(Long workspace);
}
