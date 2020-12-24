package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.PredictiveMonitorClass;
import com.aste.lsme.domain.Workspace;

public interface MonitorClassDao {

	boolean save(PredictiveMonitorClass monitorclass, Workspace w);

	long getClassCount(Workspace w);

	List<PredictiveMonitorClass> getPaginatedMonitor(int from, Workspace w);

	List<PredictiveMonitorClass> getMonitorClasses(Workspace w);

	void delete(Long valueOf);

	PredictiveMonitorClass get(Long id);

	boolean update(PredictiveMonitorClass pm, Workspace w);

}
