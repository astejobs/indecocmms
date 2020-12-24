package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.WarehouseDao;
import com.aste.lsme.domain.StoreKeeper;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Warehouse;
import com.aste.lsme.service.WarehouseService;
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
	WarehouseDao warehouseDao;
	
	@Override
	public boolean persist(Warehouse warehouse) {
		return warehouseDao.persist(warehouse);
	}

	@Override
	public boolean update(Warehouse warehouse) {
		return warehouseDao.update(warehouse);
	}

	@Override
	public Warehouse get(long id) {
		return warehouseDao.get(id);
	}

	@Override
	public void delete(long id) {
		warehouseDao.delete(id);
	}

	@Override
	public List<Warehouse> getAll() {
		return warehouseDao.getAll();
	}

	@Override
	public StoreKeeper changeStatusStoreKeeper(StoreKeeper storeKeeper) {
		return warehouseDao.changeStatusStoreKeeper(storeKeeper);
	}

	@Override
	public List<Warehouse> getWarehousesOfStorekeeper(UserDetail userDetail) {
		return warehouseDao.getWarehousesOfStorekeeper(userDetail);
	}

	

	@Override
	public List<UserDetail> getUserDetail(long id) {
		
		return warehouseDao.getUserDetail(id);
	}

}
