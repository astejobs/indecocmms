package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.PredictiveMonitorClass;


public interface PredictiveMonitorClassServiceInterface {
	public List<PredictiveMonitorClass> listAll();

	public boolean add(PredictiveMonitorClass u);

	public void remove(long id)  throws Exception;

	public PredictiveMonitorClass find(long id);

	public PredictiveMonitorClass update(PredictiveMonitorClass u);
	public List<PredictiveMonitorClass> getPage(int from, int to);
	public long count();

}
