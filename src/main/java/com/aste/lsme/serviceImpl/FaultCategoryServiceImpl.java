package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.FaultCategoryDao;
import com.aste.lsme.domain.FaultCategory;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.FaultCategoryService;
@Service
@Transactional
public class FaultCategoryServiceImpl implements FaultCategoryService {
@Autowired
FaultCategoryDao dao;


	public boolean addFaultCategory(FaultCategory faultCategory,Workspace w) {
	
		return dao.addFaultCategory(faultCategory,w);
	}
	@Override
	public long getFaultCategoryCount(Workspace w) {
		
		return dao.getFaultCategoryCount(w);
	}
	@Override
	public List<FaultCategory> getfaultCategoryPaginated(int from,Workspace w) {
		
		return dao.getfaultCategoryPaginated(from,w);
	}
	@Override
	public FaultCategory get(Long id) {
		
	
	return  dao.get(id);
	}
	@Override
	public boolean update(FaultCategory faultCategory,Workspace w) {
		
		return dao.update(faultCategory,w);
	}
	@Override
	public void delete(Long id) {
		dao.delete(id);
		
	}
	@Override
	public List<FaultCategory> getWorkspaceFaultCategory(Workspace w) {
		return dao.getWorkspaceFaultCategory(w);
	}
	@Override
	public List<FaultCategory> getfaultCategories(Long workspaceId) {
		return dao.getfaultCategories(workspaceId);
	}


	

	
}
