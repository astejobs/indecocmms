package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PartTransactionDao;
import com.aste.lsme.domain.PartTransaction;
import com.aste.lsme.domain.PartTransferSearch;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.domain.PartTransactionSearch;
import com.aste.lsme.service.PartTransactionService;
@Service
@Transactional
public class PartTransactionServiceImpl implements PartTransactionService {

	@Autowired
	PartTransactionDao partTransactionDao;
	
	@Override
	public void persist(PartTransaction partTransaction) {
		partTransactionDao.persist(partTransaction);
	}

	@Override
	public PartTransaction update(PartTransaction partTransaction) {
		return partTransactionDao.update(partTransaction);
	}

	@Override
	public PartTransaction get(long id) {
		return partTransactionDao.get(id);
	}

	@Override
	public void delete(long id) {
		partTransactionDao.delete(id);
	}

	@Override
	public List<PartTransaction> getReservedParts(String reportTaskId) {
		return partTransactionDao.getReservedParts(reportTaskId);
	}

	@Override
	public List<PartTransaction> getIssuedParts(String reportTaskId) {
		return partTransactionDao.getIssuedParts(reportTaskId);
	}

	@Override
	public List<PartTransaction> getRecievedParts(String reportTaskId) {
		return partTransactionDao.getRecievedParts(reportTaskId);
	}

	@Override
	public List<PartTransaction> getAllReservedParts(List<Warehouse> warehouses) {
		return partTransactionDao.getAllReservedParts(warehouses);
	}

	@Override
	public List<PartTransaction> getAllReturnedParts(List<Warehouse> warehouses) {
		return partTransactionDao.getAllReturnedParts(warehouses);
	}

	@Override
	public List<PartTransaction> search(int from,PartTransactionSearch partConsumptionCriteria) {
		
		return partTransactionDao.search(from,partConsumptionCriteria);
	}

	@Override
	public long count(PartTransactionSearch partTransactionSearch) {
		return partTransactionDao.count(partTransactionSearch);
	}

	@Override
	public List<PartTransaction> search(PartTransactionSearch partConsumptionCriteria) {
		return partTransactionDao.search(partConsumptionCriteria);
	}

	

}
