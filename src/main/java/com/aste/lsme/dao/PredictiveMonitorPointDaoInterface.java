package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.PredictiveMonitorPoint;


public interface PredictiveMonitorPointDaoInterface {

	public List<PredictiveMonitorPoint> listAll();

	public boolean add(PredictiveMonitorPoint u);

	public void remove(PredictiveMonitorPoint mp)  throws Exception;

	public PredictiveMonitorPoint find(long cost_id);

	public PredictiveMonitorPoint update(PredictiveMonitorPoint u);
	public List<PredictiveMonitorPoint> getPage(int from, int to);
	public long count();

	int removeForPredictiveMaint(long predictId) throws Exception;

}
