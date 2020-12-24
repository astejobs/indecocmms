package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PredictiveMonitorPointDaoInterface;
import com.aste.lsme.domain.PredictiveMonitorPoint;
import com.aste.lsme.service.PredictiveMonitorPointServiceInterface;


@Service
@Transactional
@Component
public class PredictiveMonitorPointServiceImpl implements PredictiveMonitorPointServiceInterface {

	@Autowired
	PredictiveMonitorPointDaoInterface predictiveMonitorPtsDao;

	@Override
	public List<PredictiveMonitorPoint> listAll() {
		return predictiveMonitorPtsDao.listAll();

	}

	@Override
	public boolean add(PredictiveMonitorPoint u) {
		return predictiveMonitorPtsDao.add(u);
			
	}

	@Override
	public void remove(PredictiveMonitorPoint mp) throws Exception{
		try{
			predictiveMonitorPtsDao.remove(mp);
		}catch(Exception e){
			
		}

	}

	@Override
	public PredictiveMonitorPoint find(long id) {
		return predictiveMonitorPtsDao.find(id);

	}

	@Override
	public PredictiveMonitorPoint update(PredictiveMonitorPoint u) {
		return predictiveMonitorPtsDao.update(u);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return predictiveMonitorPtsDao.count();
	}

	@Override
	public List<PredictiveMonitorPoint> getPage(int from, int to) {
		// TODO Auto-generated method stub
		return predictiveMonitorPtsDao.getPage(from, to);
	}

	
}
