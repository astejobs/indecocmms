package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.AcmvDaoInterface;
import com.aste.lsme.domain.ACMV;
import com.aste.lsme.domain.EquipmentSearchCriteria;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.AcmvServiceInterface;
@Transactional
@Service
public class AcmvServiceImpl implements AcmvServiceInterface {

	@Autowired
	AcmvDaoInterface acmvDaoInterface;
	@Override
	public ACMV save(ACMV acmv) {
		// TODO Auto-generated method stub
		
		
		return acmvDaoInterface.save(acmv);
	}

	@Override
	public Boolean update(ACMV acmv) {
		// TODO Auto-generated method stub
		return acmvDaoInterface.update(acmv);
	}

	@Override
	public ACMV delete(ACMV acmv) {
		// TODO Auto-generated method stub
		return acmvDaoInterface.delete(acmv);
	}

	@Override
	public ACMV find(Long id) {
		// TODO Auto-generated method stub
		return acmvDaoInterface.find(id);
	}

	@Override
	public ACMV find(Long id, Workspace workspace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateEquipmentCode(String type) {
		// TODO Auto-generated method stub
		return acmvDaoInterface.generateEquipmentCode(type);
	}

	@Override
	public List<ACMV> getSearchList(int from,EquipmentSearchCriteria acmvSearchCriteria) {
		// TODO Auto-generated method stub
		return  acmvDaoInterface.getSearchList(from,acmvSearchCriteria);
	}

	@Override
	public void updateEquimentBaseLine(ACMV acmv) {
		// TODO Auto-generated method stub
		acmvDaoInterface.updateEquimentBaseLine(acmv);
	}

	@Override
	public Long getAcmvCount(Workspace w) {
		// TODO Auto-generated method stub
		return acmvDaoInterface.getAcmvCount(w);
	}

}
