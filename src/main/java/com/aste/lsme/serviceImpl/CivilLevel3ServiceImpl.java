package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.CivilLevel3Dao;
import com.aste.lsme.domain.CivilLevel3;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.CivilLevel3Service;



@Service
@Transactional
public class CivilLevel3ServiceImpl implements CivilLevel3Service {

	@Autowired
	CivilLevel3Dao dao;

	
	@Override
	public List<CivilLevel3> getCivilLevel3Paginated(int from,Workspace w) {
		return dao.getCivilLevel3Paginated(from,w);
	}
	
	@Override
	public long count(Workspace w) {
		return dao.count(w);
	}


	@Override
	public boolean add(CivilLevel3 c,Workspace w) {
		return dao.add(c,w);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public CivilLevel3 find(Long id) {
		return dao.find(id);
	}

	@Override
	public List<CivilLevel3> getCivilLevel3(long string) {

		System.out.println(string+"=================");
		return dao.getCivillevel3(string);
		
	}
	@Override
	public boolean update(CivilLevel3 b,Workspace w) {
		// TODO Auto-generated method stub
		return dao.update(b,w);

	}

	@Override
	public List<CivilLevel3> getAll(Workspace w) {
		// TODO Auto-generated method stub
		return dao.getAll(w);
	}

	
}
