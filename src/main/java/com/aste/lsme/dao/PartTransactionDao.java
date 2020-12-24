package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.PartTransaction;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.domain.PartTransactionSearch;

public interface PartTransactionDao {

	public void persist(PartTransaction partTransaction);
	public PartTransaction update(PartTransaction partTransaction);
	public PartTransaction get(long id);
	public void delete(long id);
	public List<PartTransaction> getReservedParts(String reportTaskId);
	public List<PartTransaction> getIssuedParts(String reportTaskId);
	public List<PartTransaction> getRecievedParts(String reportTaskId);
	public List<PartTransaction> getAllReservedParts(List<Warehouse> warehouses);
	public List<PartTransaction> getAllReturnedParts(List<Warehouse> warehouses);
	public List<PartTransaction> search(int from, PartTransactionSearch partConsumptionCriteria);	
	public long count(PartTransactionSearch partTransactionSearch);
	public List<PartTransaction> search(PartTransactionSearch partConsumptionCriteria);
}
