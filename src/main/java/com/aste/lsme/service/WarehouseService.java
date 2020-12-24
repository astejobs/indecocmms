package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.StoreKeeper;
import com.aste.lsme.domain.UserDetail;
import com.aste.lsme.domain.Warehouse;

public interface WarehouseService {

	public boolean persist(Warehouse warehouse);
	public boolean update(Warehouse warehouse);
	public Warehouse get(long id);
	public void delete(long id);
	public List<Warehouse> getAll();
	public StoreKeeper changeStatusStoreKeeper(StoreKeeper storeKeeper);
	
	public List<Warehouse> getWarehousesOfStorekeeper(UserDetail userDetail);
	
	public List<UserDetail> getUserDetail(long id);

}
