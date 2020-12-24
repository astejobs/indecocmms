package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.TechnicianDao;
import com.aste.lsme.domain.Technician;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.TechnicianService;
import com.aste.lsme.webservicesDtos.TechnicianDTO;

@Service
@Transactional
public class TechnicianServiceImpl implements TechnicianService{
 @Autowired
 TechnicianDao  dao;
 
	
	@Override
	public boolean addTechnician(Technician technician,Workspace w) {
		
		return dao.addTechnician(technician,w);
	}


	@Override
	public List<Technician> getTechnician() {
	
		return  dao.getTechnician();
	}


	@Override
	public Technician get(Long id) {
	
		return dao.get(id);
	}


	@Override
	public boolean update(Technician technician,Workspace w) {
	
		return dao.update(technician,w) ;
	}

   @Override
	public void delete(Long id) {
	dao.delete(id);
		
	}

   @Override
	public long geTechnicianCount(Workspace w) {
		
		return dao.geTechnicianCount(w);
	}









	@Override
	public List<Technician> getTechnicianPaginated(int from,Workspace w) {
	
		return dao.getTechnicianPaginated(from,w);
	}


	@Override
	public List<Technician> getWorkspaceTechnician(Workspace w) {
		return dao.getWorkspaceTechnician(w);
	}


	@Override
	public List<TechnicianDTO> findTechnician(String w) {
		// TODO Auto-generated method stub
		return dao.findTechnician(w);
	}


	




	




	



}
