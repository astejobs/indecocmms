package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Part;

public interface PartService {

	boolean save(Part part);

	long getCount();

	List<Part> getPartPaginated(int from);

	Part getPart(Long id);

	boolean update(Part part);

	boolean remove(long id);
	
	public List<Part> getAll();

}
