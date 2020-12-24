package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.TechnicianDTO;

public interface TechnicianDao {

	boolean addTechnician(Technician technician, Workspace w);

	List<Technician> getTechnician();

	Technician get(Long id);

	boolean update(Technician technician, Workspace w);

	void delete(Long id);

	List<Technician> getTechnicianPaginated(int from, Workspace w);

	long geTechnicianCount(Workspace w);

	public List<Technician> getWorkspaceTechnician(Workspace w);

	List<TechnicianDTO> findTechnician(String w);

}
