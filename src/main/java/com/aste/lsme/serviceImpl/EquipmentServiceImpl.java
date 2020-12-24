package com.aste.lsme.serviceImpl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aste.lsme.dao.EquipmentDao;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.AssetType;
import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentDtoSearch;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.EquipmentService;
import com.aste.lsme.webservicesDtos.EquipmentDTO;
import com.aste.lsme.webservicesDtos.TaskDTO;

@Service
@Transactional
@Component
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	EquipmentDao dao;
	
	@Override
	public List<Equipment> getAll() {
		
		return dao.getAll();
	}

	@Override
	public String generateEquipmentCode(String equipmentType) {
		
		return dao.generateEquipmentCode(equipmentType);
	}

	@Override
	public Boolean saveImage(MultipartFile image, String name, String path) {
		try {
			if (!destinationExists(path))
				createFolderStructure(path);
			FileUtils.writeByteArrayToFile(new File(path + name),
					image.getBytes());

		} catch (Exception e) {

			return false;
		}
		return true;
	}
	
	private Boolean destinationExists(String path) {
		File folder = new File(path);
		return folder.exists();

	}

	private void createFolderStructure(String path) {

		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
	}

	@Override
	public Boolean removeImage(String imageName, String path) {
		File f = new File(path + imageName);
		if (f.exists()) {
			f.delete();
			return true;
		}
		return false;
	}

	@Override
	public String getContentType(String imageName) {
		if (imageName.contains(".pdf"))
			return "application/pdf";
		else if (imageName.contains(".dwg"))
			return "imsage/vnd.dwg";
		else if (imageName.contains(".gif"))
			return "image/gif";
		else
			return "image/jpeg";
	}

	@Override
	public List<AssetType> getEqpTypes(Workspace w) {
		return dao.getEqpTypes(w);
		
	}

	@Override
	public long getEquipmentCount(EquipmentSearchCriteria equipmentSearch,Workspace w) {
		return dao.getEquipmentCount(equipmentSearch,w);
	}

	@Override
	public List<EquipmentDtoSearch> getEquipmentPaginated(int from, EquipmentSearchCriteria equipmentSearch,Workspace w) {
		return dao.getEquipmentPaginated(from, equipmentSearch,w);
	}

	@Override
	public List<Equipment> getWorkspaceEquipment(Workspace w) {
		return dao.getWorkspaceEquipment(w);
	}

	@Override
	public List<EquipmentDtoSearch> getAllEquipments(EquipmentSearchCriteria equipmentSearch) {
		
		return dao.getAllEquipments(equipmentSearch);
	}

	@Override
	public List<AssetType> getPMEquipTypes(Workspace w) {
		return dao.getPMEquipTypes(w);

	}


	@Override
	public List<Equipment> getPredictiveScheduledEquipments(EquipmentSearchCriteria equipmentSearch, int from, int to) {
		
		return dao.getPredictiveScheduledEquipments(equipmentSearch, from, to);
	}

	@Override
	public Long countPredictiveScheduledEquipments(EquipmentSearchCriteria equipmentSearchCriteria) {
	
		return dao.countPredictiveScheduledEquipments(equipmentSearchCriteria);
	}

	

	@Override
	public Boolean saveEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		return dao.saveEquipment(equipment);
	}


	@Override
	public Equipment find(Long id) {
		// TODO Auto-generated method stub
		return dao.find(id);
	}

	@Override
	public Boolean update(Equipment equipment) {
		// TODO Auto-generated method stub
		return dao.update(equipment);
	}

	public List<Equipment> getEquipmentBySubtype(AssetSubtype assetsubtype)

	{
		return dao.getEquipmentBySubtype(assetsubtype);
	}
	
	@Override
	  public void delete(Long id) {
	    // TODO Auto-generated method stub
	    dao.delete(id); 
	  }

	 public List<Equipment> getAll(Long id, Workspace w){
		return dao.getAll(id, w);

	 }

	@Override
	public List<TaskDTO> getAllTasks(String equipmentcode,String status) {
		
			 return dao.getAllTasks(equipmentcode,status);
            
	
		}

	@Override
	public EquipmentDTO getEquipmentByEquipCode(String equipmentcode) {
		// TODO Auto-generated method stub
		return dao.getEquipmentByEquipCode(equipmentcode);
	}

	@Override
	public EquipmentDTO getEquipmentsByFrId(String frId) {
		return dao.getEquipmentsByFrId(frId);
	}

	@Override
	public List<EquipmentDTO> getEquipmentsByBuildingIdAndLocationId(Long buildId, Long locId) {
		// TODO Auto-generated method stub
		return dao.getEquipmentsByBuildingIdAndLocationId(buildId, locId);
	}

	@Override
	public List<TaskDTO> getTasksOnEquipmentCodeAndStatus(String equipmentcode, String status) {
		// TODO Auto-generated method stub
		return dao.getTasksOnEquipmentCodeAndStatus(equipmentcode, status);
	}

	@Override
	public List<EquipmentDTO> getEquipments(String w, String search) {
		// TODO Auto-generated method stub
		return dao.getEquipments(w, search);
	}

	

	
}
