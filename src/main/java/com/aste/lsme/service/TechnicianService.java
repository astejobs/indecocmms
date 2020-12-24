package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.TechnicianDTO;

public interface TechnicianService {

	boolean addTechnician(Technician technician, Workspace w);

	List<Technician> getTechnician();

	Technician get(Long id);

	boolean update(Technician technician, Workspace w);

	void delete(Long id);

    long geTechnicianCount(Workspace w);
    
   List<Technician>  getTechnicianPaginated(int from, Workspace w);
	public List<Technician> getWorkspaceTechnician(Workspace w);
	
	List<TechnicianDTO> findTechnician(String w);


}
