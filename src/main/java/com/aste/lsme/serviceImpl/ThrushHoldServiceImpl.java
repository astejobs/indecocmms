package com.aste.lsme.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.aste.lsme.domain.Equipment;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.AssetSubtype;
import com.aste.lsme.domain.PredictiveCriteria;
import com.aste.lsme.domain.ThrushHoldValue;
import com.aste.lsme.domain.PredictiveMonitorPoint;
import com.aste.lsme.domain.PredictiveParameter;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.dao.ThrushHoldDaoInterface;
import com.aste.lsme.dao.PredictiveMonitorPointDaoInterface;
import com.aste.lsme.dao.PredictiveParameterDaoInterface;
import com.aste.lsme.service.ThrushHoldServiceInterface;


@Service
@Transactional
@Component
public class ThrushHoldServiceImpl implements ThrushHoldServiceInterface {

	@Autowired
	ThrushHoldDaoInterface predictiveMaintDao;
	
	@Autowired
	PredictiveMonitorPointDaoInterface predictiveMonitorPointDao;
	
	@Autowired
	PredictiveParameterDaoInterface predictiveParamDao;
	
	
//	@Autowired
//	PredictiveMaintenanceServiceInterface predictiveMainService;
	@Override
	public List<ThrushHoldValue> listAll() {
		return predictiveMaintDao.listAll();
	}

	
	@Override
	public boolean add(ThrushHoldValue pm) {
		 predictiveMaintDao.add(pm);
		 for (PredictiveMonitorPoint e : pm.getMonitorPointList()) {
			e.setPredictiveMaint(pm);
			predictiveMonitorPointDao.add(e);
		}
		 for (PredictiveParameter pp : pm.getParameterList()) {
			pp.setPredictiveMaint(pm);
			predictiveParamDao.add(pp);
		}
		return true ;
			
	}

	@Override
	public void remove(long id) throws Exception{
		try{
			predictiveMaintDao.remove(id);
		}catch(Exception e){
			
		}

	}

	@Override
	public ThrushHoldValue find(long id) {
		return predictiveMaintDao.find(id);

	}

	@Override
	public ThrushHoldValue update(ThrushHoldValue pm) throws Exception {
		 return predictiveMaintDao.update(pm);
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return predictiveMaintDao.count();
	}

	@Override
	public List<ThrushHoldValue> getPage(int from, int to) {
		// TODO Auto-generated method stub
		return predictiveMaintDao.getPage(from, to);
	}

	

	@Override
	public List<ThrushHoldValue> searchSpecificPredictiveMaintenance(int from,PredictiveCriteria p,Workspace w){
			// TODO Auto-generated method stub
		return predictiveMaintDao.searchSpecificPredictiveMaintenance(from,p,w);
	}

	@Override
	public long countSpecificPredictiveMaintenance(PredictiveCriteria p,Workspace w) {
		// TODO Auto-generated method stub
		return predictiveMaintDao.countSpecificPredictiveMaintenance( p,w);
	}


/*	@Override
	public List<Equipment1> equipSearch(EquipmentSearchCriteria eqSearch,Workspace w) {
		// TODO Auto-generated method stub
		return predictiveMaintDao.equipSearch(eqSearch,w);
	}*/


	@Override
	public List<AssetSubtype> getPredictEquipmentSubSystems(String eqType) {
		return predictiveMaintDao.getPredictEquipmentSubSystems(eqType);
	}


	@Override
	public boolean save(ThrushHoldValue equipmentData) {
		if(predictiveMaintDao.save(equipmentData))
				return true;
		else
			return false;
	}


	@Override
	public long countEqp(EquipmentSearchCriteria eqp, Workspace w) {
		// TODO Auto-generated method stub
		return predictiveMaintDao.countEqp(eqp,w) ;
	}


	@Override
	public List<Equipment> getSearchEqpList(int from, EquipmentSearchCriteria eqp, Workspace w) {
		// TODO Auto-generated method stub
		return predictiveMaintDao.getSearchEqpList(from,eqp,w);


	}
	
	
	@Override
	public String validatedContentType(MultipartFile image) throws Exception {
		
			System.out.println("image.getName()=>"+image.getOriginalFilename());
		System.out.println("in validated ContentType = "+image.getSize() +image.getContentType());
		if(image.getSize()>15000000){
			throw new RuntimeException("upto 1Mb images are accepted");
		}else if (image.getContentType().equals("image/jpeg") )
	    	return ".jpg";
	    else if(image.getContentType().equals("image/png") )
	    	return ".png";
	    else if(image.getContentType().equals("image/gif") )
	    	return ".gif";
	    else if(image.getContentType().equals("image/vnd.dwg") )
    	return ".dwg";
	    else if(image.getContentType().equals("application/pdf") )
	    	return ".pdf";	
	    else
	        throw new Exception("Only JPG png dwg gif pdf images are accepted   ==>  "+image.getContentType());
	    }
	    
	

	@Override
	public String saveImage(String filename, MultipartFile image,String path)
	        throws RuntimeException, IOException {
	    try {
	        File file = new File(path + "/"
	                + filename);
	        FileUtils.writeByteArrayToFile(file, image.getBytes());
	        System.out.println("File saved to the location:  " + file.toString()
	                + "  verify that the image has been stored. image==>"+ image.getSize());
	        return filename;
	    } catch (IOException e) {
	        throw e;
	    }
	}

}
