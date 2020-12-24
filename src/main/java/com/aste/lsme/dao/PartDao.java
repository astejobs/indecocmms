package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Part;

public interface PartDao {

	boolean save(Part part);

	long getCount();

	List<Part> getPartPaginated(int from);

	Part gePart(Long id);

	boolean update(Part part);

   boolean remove(long id);

   public List<Part> getAll();

}
