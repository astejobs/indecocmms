package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.CostCenterDao;
import com.aste.lsme.domain.CostCenter;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.CostCenterService;

@Service
@Transactional
public class CostCenterServiceImpl  implements CostCenterService{
		@Autowired 
		CostCenterDao  dao;
		
	@Override
	public boolean addCostCenter(CostCenter costCenter,Workspace w) {
		
		return dao.addCostCenter(costCenter,w);
	}
	
	@Override
	public List<CostCenter> getcostCenter() {
		
		return dao.getcostCenter();
	}
	
	@Override
	public CostCenter get(Long id) {
		
		return  dao.get(id);
	}
		
		@Override
		public boolean update(CostCenter costCenter,Workspace w) 
		{
			
			return dao.updatecostCenter(costCenter,w);
		}
		
		@Override
		public void delete(Long id) {
		dao.delete(id);
			
		}
		
		@Override
		public long getCostCenterCount(Workspace w) {
			
			return dao.getCostCenterCount(w);
		}
		
		@Override
		public List<CostCenter> getCostCenterPaginated(int from,Workspace w) {
			
			return dao.getCostCenterPaginated(from,w);
		}

		@Override
		public List<CostCenter> getWorkspaceCostCenter(Workspace w) {
			return dao.getWorkspaceCostCenter(w);
		}

		@Override
		public List<CostCenter> findCostCenter(String w) {
  
			return dao.findCostCenter(w);
		}


}
