package com.aste.lsme.dao;

import com.aste.lsme.domain.PartBatch;

public interface PartBatchDao {

	public void persist(PartBatch partBatch);
	public PartBatch update(PartBatch partBatch);
	public PartBatch get(long id);
	
	
}
