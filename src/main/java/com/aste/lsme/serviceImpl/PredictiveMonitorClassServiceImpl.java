package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PredictiveMonitorClassDaoInterface;
import com.aste.lsme.domain.PredictiveMonitorClass;
import com.aste.lsme.service.PredictiveMonitorClassServiceInterface;


@Service
@Transactional
@Component
public class PredictiveMonitorClassServiceImpl implements PredictiveMonitorClassServiceInterface {

	@Autowired
	PredictiveMonitorClassDaoInterface predictiveMonitorClass;

	@Override
	public List<PredictiveMonitorClass> listAll() {
		return predictiveMonitorClass.listAll();

	}

	@Override
	public boolean add(PredictiveMonitorClass u) {
		return predictiveMonitorClass.add(u);
			
	}

	@Override
	public void remove(long id) throws Exception{
		try{
			predictiveMonitorClass.remove(id);
		}catch(Exception e){
			
		}

	}

	@Override
	public PredictiveMonitorClass find(long id) {
		return predictiveMonitorClass.find(id);

	}

	@Override
	public PredictiveMonitorClass update(PredictiveMonitorClass u) {
		return predictiveMonitorClass.update(u);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return predictiveMonitorClass.count();
	}

	@Override
	public List<PredictiveMonitorClass> getPage(int from, int to) {
		// TODO Auto-generated method stub
		return predictiveMonitorClass.getPage(from, to);
	}

	
}
