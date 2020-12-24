package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.PredictiveParameter;

public interface PredictiveParameterServiceInterface {
	public List<PredictiveParameter> listAll();

	public boolean add(PredictiveParameter u);

	public void remove(PredictiveParameter p)  throws Exception;

	public PredictiveParameter find(long cost_id);

	public PredictiveParameter update(PredictiveParameter u);
	public List<PredictiveParameter> getPage(int from, int to);
	public long count();

}
