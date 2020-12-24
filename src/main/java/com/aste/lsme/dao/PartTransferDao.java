package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.PartTransferSearch;
import com.aste.lsme.domain.PartTransfer;
import com.aste.lsme.domain.Warehouse;

public interface PartTransferDao {
	
	public void persist(PartTransfer partTransfer);
	public PartTransfer update(PartTransfer partTransfer);
	public void delete(long id);
	public PartTransfer get(long id);
	public List<PartTransfer> getReservedParts(List<Warehouse> warehouses);
	public List<PartTransfer> getIssuingParts(List<Warehouse> warehouses);
	public List<PartTransfer> getRecievedParts(List<Warehouse> warehouses);
	public List<PartTransfer> search(int from,PartTransferSearch partTransferSearch);
	public long count(PartTransferSearch partTransactionSearch);
	public List<PartTransfer> getAll(PartTransferSearch partTransferSearch);

}

