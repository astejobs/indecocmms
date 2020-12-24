package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentDtoSearch;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;

import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.TaskDTO;




public interface EquipmentDao {

	public List<TaskDTO> getAllTasks(String equipmentcode,String status);
	public EquipmentDTO getEquipmentByEquipCode(String equipmentcode);

	
	public List<Equipment> getAll();
	public String generateEquipmentCode(String electricalsubsystem);
	public List<AssetType> getEqpTypes(Workspace w);
	public long getEquipmentCount(EquipmentSearchCriteria electricalSearch, Workspace w);
	public List<EquipmentDtoSearch> getEquipmentPaginated(int from, EquipmentSearchCriteria electricalSearch,Workspace w);
	public List<EquipmentDtoSearch> getAllEquipments(EquipmentSearchCriteria equipmentSearch);
	public List<Equipment> getWorkspaceEquipment(Workspace w);
	public List<AssetType> getPMEquipTypes(Workspace w);

	public List<Equipment> getPredictiveScheduledEquipments(EquipmentSearchCriteria equipmentSearch, int from, int to);
	public Long countPredictiveScheduledEquipments(EquipmentSearchCriteria equipmentSearchCriteria);

	public  Boolean saveEquipment(Equipment equipment);
	public Equipment find(Long id);
	public Boolean update(Equipment equipment);
	public List<Equipment> getEquipmentBySubtype(AssetSubtype assetsubtype);
	public void delete(Long id);
	 public List<Equipment> getAll(Long id, Workspace w);
	public EquipmentDTO getEquipmentsByFrId(String frId);
	List<EquipmentDTO> getEquipmentsByBuildingIdAndLocationId(Long buildId, Long locId);

	public List<TaskDTO> getTasksOnEquipmentCodeAndStatus(String equipmentcode,String status );
	public List<EquipmentDTO> getEquipments( String w, String search);
}
