package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Vendor;

public interface VendorService {
	
	
	boolean saveVendor(Vendor v);
	long getCount();
	List<Vendor> getPaginated(int from);
	Vendor getVendor(long id);
    public boolean update(Vendor v);
    public void delete(Long id);
	List<Vendor> getAll();

}
