package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PredictiveParameterDaoInterface;
import com.aste.lsme.domain.PredictiveParameter;
import com.aste.lsme.service.PredictiveParameterServiceInterface;


@Service
@Transactional
@Component
public class PredictiveParameterServiceImpl implements PredictiveParameterServiceInterface {

	@Autowired
	PredictiveParameterDaoInterface predictiveParamDao;

	@Override
	public List<PredictiveParameter> listAll() {
		return predictiveParamDao.listAll();

	}

	@Override
	public boolean add(PredictiveParameter u) {
		return predictiveParamDao.add(u);
			
	}

	@Override
	public void remove(PredictiveParameter p) throws Exception{
		try{
			predictiveParamDao.remove(p);
		}catch(Exception e){
			
		}

	}

	@Override
	public PredictiveParameter find(long id) {
		return predictiveParamDao.find(id);

	}

	@Override
	public PredictiveParameter update(PredictiveParameter u) {
		return predictiveParamDao.update(u);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return predictiveParamDao.count();
	}

	@Override
	public List<PredictiveParameter> getPage(int from, int to) {
		// TODO Auto-generated method stub
		return predictiveParamDao.getPage(from, to);
	}

	
}
