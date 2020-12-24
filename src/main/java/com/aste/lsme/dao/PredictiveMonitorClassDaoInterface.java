package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.PredictiveMonitorClass;

public interface PredictiveMonitorClassDaoInterface {

	public List<PredictiveMonitorClass> listAll();

	public boolean add(PredictiveMonitorClass u);

	public void remove(long id)  throws Exception;

	public PredictiveMonitorClass find(long cost_id);

	public PredictiveMonitorClass update(PredictiveMonitorClass u);
	public List<PredictiveMonitorClass> getPage(int from, int to);
	public long count();

}
