package com.aste.lsme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aste.lsme.domain.PredictiveMonitorClass;
import com.aste.lsme.domain.Workspace;

public interface MonitorClassService {

	
	boolean save(PredictiveMonitorClass monitorclass, Workspace w);

	long getClassCount(Workspace w);

	List<PredictiveMonitorClass> getPaginatedMonitor(int from, Workspace w);

	List<PredictiveMonitorClass> getMonitorClasses(Workspace w);

	void delete(Long valueOf);

	PredictiveMonitorClass get(Long id);

	boolean update(PredictiveMonitorClass pm, Workspace w);

	
}
