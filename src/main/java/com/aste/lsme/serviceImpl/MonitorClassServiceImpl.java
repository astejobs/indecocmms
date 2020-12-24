package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.MonitorClassDao;
import com.aste.lsme.domain.PredictiveMonitorClass;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.MonitorClassService;

@Transactional
@Service
public class MonitorClassServiceImpl  implements MonitorClassService{

	@Autowired
	MonitorClassDao monitorDao;
	
	@Override
	public boolean save(PredictiveMonitorClass monitorclass, Workspace w) {
		try{
			return monitorDao.save(monitorclass,w);
			
		}catch (Exception e) {
			return false;		}
	}

	@Override
	public long getClassCount(Workspace w) {
		return  monitorDao.getClassCount(w);

	}
	
	@Override
	public List<PredictiveMonitorClass> getPaginatedMonitor(int from, Workspace w) {
		return monitorDao.getPaginatedMonitor(from,w);
	}

	@Override
	public List<PredictiveMonitorClass> getMonitorClasses(Workspace w) {
		return monitorDao.getMonitorClasses(w);
	}

	@Override
	public void delete(Long valueOf) {
	    monitorDao.delete(valueOf);
		
	}

	@Override
	public PredictiveMonitorClass get(Long id) {
	 return monitorDao.get(id);
	}

	@Override
	public boolean update(PredictiveMonitorClass pm, Workspace w) {
		return monitorDao.update(pm,w);
	}


	
	

}
