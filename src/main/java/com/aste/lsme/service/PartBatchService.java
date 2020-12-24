package com.aste.lsme.service;

import com.aste.lsme.domain.PartBatch;

public interface PartBatchService {

	public void persist(PartBatch partBatch);
	public PartBatch update(PartBatch partBatch);
	public PartBatch get(long id);
}
