package com.aste.lsme.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.EquipmentDynamicDaoInterface;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.DynamicFieldsOfEquiment;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.EquipmentPropertyDesInterface;


@Service
@Transactional
public class EquipmentPropertyDesServiceImp implements EquipmentPropertyDesInterface {

	@Autowired
	EquipmentDynamicDaoInterface<DynamicFieldsOfEquiment> equipmentDynamicDaoInterface;
	
	
	@Override
	public void save(DynamicFieldsOfEquiment equipment) {
		// TODO Auto-generated method stub
         equipmentDynamicDaoInterface.save(equipment);
	}

	@Override
	public DynamicFieldsOfEquiment find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DynamicFieldsOfEquiment getUnique(Long id, Workspace workspace) {
		// TODO Auto-generated method stub
		
	return	equipmentDynamicDaoInterface.getUnique(DynamicFieldsOfEquiment.class, id, workspace);
		
		
	}

	@Override
	public DynamicFieldsOfEquiment getEquipment(Long id) {
		// TODO Auto-generated method stub
		return equipmentDynamicDaoInterface.getEquipment(id);
	}

	@Override
	public void update(DynamicFieldsOfEquiment equipment) {
		// TODO Auto-generated method stub
		equipmentDynamicDaoInterface.update(equipment);
	}

	

}
