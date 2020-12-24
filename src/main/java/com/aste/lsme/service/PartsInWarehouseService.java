package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.InventoryPartSearch;
import com.aste.lsme.domain.Part;
import com.aste.lsme.domain.PartBatch;
import com.aste.lsme.domain.PartsInWarehouse;
import com.aste.lsme.domain.Warehouse;

public interface PartsInWarehouseService {

	public void  persist(PartsInWarehouse partsInWarehouse);
	public PartsInWarehouse get(long id);
	public void update(PartsInWarehouse partsInWarehouse);
	public void delete(long id);
	public List<PartsInWarehouse> getPartsInWarehouse(long id);
	public List<Part> getParts(long id);
	public boolean checkBatchNo(PartBatch partBatch);
	public List<PartsInWarehouse> getAll();
	public List<PartsInWarehouse> getPartsNotInWarehouse(long id);
	public PartsInWarehouse findPartInWarehouse(Part part, Warehouse warehouse);
	public List<PartsInWarehouse> searchPartsInWareHouse(InventoryPartSearch inPartSearch);

}
