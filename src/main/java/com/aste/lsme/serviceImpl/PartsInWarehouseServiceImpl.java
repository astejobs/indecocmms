package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aste.lsme.dao.PartsInWarehouseDao;
import com.aste.lsme.domain.InventoryPartSearch;
import com.aste.lsme.domain.Part;
import com.aste.lsme.domain.PartBatch;
import com.aste.lsme.domain.PartsInWarehouse;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.service.PartsInWarehouseService;

@Service
@Transactional
public class PartsInWarehouseServiceImpl implements PartsInWarehouseService {

	@Autowired
	PartsInWarehouseDao partsInWarehouseDao;
	
	
	@Override
	public void persist(PartsInWarehouse partsInWarehouse) {
		partsInWarehouseDao.persist(partsInWarehouse);
	}

	@Override
	public PartsInWarehouse get(long id) {
		return partsInWarehouseDao.get(id);
	}

	@Override
	public void update(PartsInWarehouse partsInWarehouse) {
		partsInWarehouseDao.update(partsInWarehouse);
	}

	@Override
	public void delete(long id) {
		partsInWarehouseDao.delete(id);
	}

	@Override
	public List<PartsInWarehouse> getPartsInWarehouse(long id) {
		return partsInWarehouseDao.getPartsInWarehouse(id);
	}

	@Override
	public List<Part> getParts(long id) {
		return partsInWarehouseDao.getParts(id);
	}

	@Override
	public boolean checkBatchNo(PartBatch partBatch) {
		return partsInWarehouseDao.checkBatchNo(partBatch);
	}

	@Override
	public List<PartsInWarehouse> getAll() {
		return partsInWarehouseDao.getAll();
	}

	@Override
	public List<PartsInWarehouse> getPartsNotInWarehouse(long id) {
		return partsInWarehouseDao.getPartsNotInWarehouse(id);
	}

	@Override
	public PartsInWarehouse findPartInWarehouse(Part part, Warehouse warehouse) {
		return partsInWarehouseDao.findPartInWarehouse(part, warehouse);
	}

	@Override
	public List<PartsInWarehouse> searchPartsInWareHouse(InventoryPartSearch inPartSearch) {
		
		return partsInWarehouseDao.searchPartsInWareHouse(inPartSearch);
	}

}
