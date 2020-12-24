package com.aste.lsme.service;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.DynamicFieldsOfEquiment;
import com.aste.lsme.domain.Workspace;

public interface EquipmentPropertyDesInterface {
	public void save(DynamicFieldsOfEquiment equipment);
	public DynamicFieldsOfEquiment find(Long id);
	public DynamicFieldsOfEquiment getUnique(Long id,Workspace workspace);
	public DynamicFieldsOfEquiment getEquipment(Long id);
	public void update(DynamicFieldsOfEquiment equipment);
}
