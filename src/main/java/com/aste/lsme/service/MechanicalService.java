package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Mechanical;
import com.aste.lsme.domain.Workspace;


public interface MechanicalService {

	boolean save(Mechanical mechanicalEquipment);

	boolean geteqpfile(String equipFile);

	boolean getdrawfile(String drawImage);

	Mechanical find(Long id);
	

	List<Mechanical> getMechEquips();

	boolean remove(Mechanical mechanical);
	List<Mechanical> getEqPaginated(int from);

	boolean update(Mechanical mechanical);

	List<Mechanical> getSearchList(int from, EquipmentSearchCriteria mechanicalSearch, Workspace w);
	
	Long count(EquipmentSearchCriteria Mechanical,Workspace w);
	

}
