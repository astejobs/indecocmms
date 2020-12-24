package com.aste.lsme.dao;


import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.DynamicFieldsOfEquiment;



public interface EquipmentDynamicDaoInterface<T> {
	public void save(T t);
	public T find(Long id);
	
	public T getUnique(Class clazz, Long id, com.aste.lsme.domain.Workspace workspace);
	
	public DynamicFieldsOfEquiment getEquipment(Long id);
	public void update(DynamicFieldsOfEquiment equipment);
}
