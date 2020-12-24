package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.PredictiveMonitorPoint;


public interface PredictiveMonitorPointServiceInterface {
	public List<PredictiveMonitorPoint> listAll();

	public boolean add(PredictiveMonitorPoint u);

	public void remove(PredictiveMonitorPoint mp)  throws Exception;

	public PredictiveMonitorPoint find(long id);

	public PredictiveMonitorPoint update(PredictiveMonitorPoint u);
	public List<PredictiveMonitorPoint> getPage(int from, int to);
	public long count();

}
