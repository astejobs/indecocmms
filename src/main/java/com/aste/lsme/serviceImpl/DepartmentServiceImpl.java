package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.DepartmentDao;
import com.aste.lsme.domain.Department;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentDao departmentDao;
	
	@Override
	public boolean persist(Department department,Workspace w) {
		return departmentDao.persist(department,w);
	}

	@Override
	public Department get(Long id) {
		return departmentDao.get(id);

	}

	@Override
	public void delete(Long id) {
		departmentDao.delete(id);
		
	}

	@Override
	public boolean update(Department department,Workspace w) {
		return departmentDao.update(department,w);
	}

	@Override
	public List<Department> getWorkspaceDepartments(Workspace w) {
		return departmentDao.getWorkspaceDepartments(w);
	}

	@Override
	public List<Department> getAll() {
		return departmentDao.getAll();
	}

	@Override
	public long getDepartmentCount(Workspace w) {
		return departmentDao.getDepartmentCount(w);
	}

	@Override
	public List<Department> getDepartmentPaginated(int from,Workspace w) {
		return departmentDao.getDepartmentPaginated(from,w);
	}

	@Override
	public List<Department> getDepartments(Long workspace) {
		return departmentDao.getDepartments(workspace);
	}

}
