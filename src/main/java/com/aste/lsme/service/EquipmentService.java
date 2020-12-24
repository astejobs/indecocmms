package com.aste.lsme.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentDtoSearch;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.TaskDTO;


public interface EquipmentService 
{
	public List<TaskDTO> getAllTasks(String equipmentcode,String status);
	public EquipmentDTO getEquipmentByEquipCode(String equipmentcode);
	
	public List<Equipment> getAll();
	public String generateEquipmentCode(String electricalsubsystem);
	public Boolean saveImage(MultipartFile image, String name, String path);
	public Boolean removeImage(String imageName, String path);
	public String getContentType(String imageName);
	public List<AssetType> getEqpTypes(Workspace w);
	public long getEquipmentCount(EquipmentSearchCriteria equipmentSearch, Workspace w);
	public List<EquipmentDtoSearch> getEquipmentPaginated(int from, EquipmentSearchCriteria equipmentSearch, Workspace w);
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
