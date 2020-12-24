package com.aste.lsme.service;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.PredictiveCriteria;
import com.aste.lsme.domain.ThrushHoldValue;
import com.aste.lsme.domain.Workspace;



public interface ThrushHoldServiceInterface {
	public List<ThrushHoldValue> listAll();

	public boolean add(ThrushHoldValue u);

	public void remove(long id)  throws Exception;

	public ThrushHoldValue find(long cost_id);

	public ThrushHoldValue update(ThrushHoldValue u) throws Exception;
	public List<ThrushHoldValue> getPage(int from, int to);
	public long count();
	//boolean saveImage(PredictiveMaintenance pm, MultipartFile equip_image,BindingResult result);
	List<ThrushHoldValue> searchSpecificPredictiveMaintenance(int from, PredictiveCriteria p, Workspace w);

	long countSpecificPredictiveMaintenance(PredictiveCriteria p, Workspace w);

	//public List<Equipment1> equipSearch(EquipmentSearchCriteria eqSearch,Workspace w);

	public List<AssetSubtype> getPredictEquipmentSubSystems(String eqType);

	public boolean save(ThrushHoldValue equipmentData);

	public long countEqp(EquipmentSearchCriteria eqp, Workspace w);

	public List< Equipment> getSearchEqpList(int from, EquipmentSearchCriteria eqp, Workspace w);

	public String validatedContentType(MultipartFile image) throws Exception;
	public String saveImage(String filename, MultipartFile image ,String path)throws Exception;

}
