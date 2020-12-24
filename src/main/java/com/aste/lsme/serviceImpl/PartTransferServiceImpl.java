package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.PartTransferDao;
import com.aste.lsme.domain.PartTransferSearch;
import com.aste.lsme.domain.PartTransfer;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.service.PartTransferService;
@Service
@Transactional
public class PartTransferServiceImpl implements PartTransferService {

	@Autowired
	PartTransferDao partTransferDao;
	
	@Override
	public void persist(PartTransfer partTransfer) {
		partTransferDao.persist(partTransfer);
	}

	@Override
	public PartTransfer update(PartTransfer partTransfer) {
		return partTransferDao.update(partTransfer);
	}

	@Override
	public void delete(long id) {
		partTransferDao.delete(id);
	}

	@Override
	public PartTransfer get(long id) {
		return partTransferDao.get(id);
	}

	@Override
	public List<PartTransfer> getReservedParts(List<Warehouse> warehouses) {
		return partTransferDao.getReservedParts(warehouses);
	}

	@Override
	public List<PartTransfer> getIssuingParts(List<Warehouse> warehouses) {
		return partTransferDao.getIssuingParts(warehouses);
	}

	@Override
	public List<PartTransfer> getRecievedParts(List<Warehouse> warehouses) {
		return partTransferDao.getRecievedParts(warehouses);
	}

	@Override
	public List<PartTransfer> search(int from,PartTransferSearch partTransferSearch) {
		return partTransferDao.search(from,partTransferSearch);
	}

	@Override
	public long count(PartTransferSearch partTransactionSearch) {
		return partTransferDao.count(partTransactionSearch);
	}

	@Override
	public List<PartTransfer> getAll(PartTransferSearch partTransferSearch) {
		return partTransferDao.getAll(partTransferSearch);
	}

}
