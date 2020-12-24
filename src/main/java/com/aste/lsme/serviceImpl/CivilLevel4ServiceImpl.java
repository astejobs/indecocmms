package com.aste.lsme.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.CivilLevel4Dao;
import com.aste.lsme.domain.CivilLevel4;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.CivilLevel4Service;


@Service
@Transactional
public class CivilLevel4ServiceImpl implements CivilLevel4Service {
	
	@Autowired
	CivilLevel4Dao dao;
	
	
	public long count(Workspace w){
		return dao.count(w);
	}
	
	 public boolean add(CivilLevel4 c,Workspace w){
		 return  dao.add(c,w);
	 }
	 
	 public void remove(Long id){
		 dao.remove(id);
	 }
	 public CivilLevel4 find(Long id){
		 return dao.find(id);
	 }
	 public boolean update(CivilLevel4 b,Workspace w){
		 return dao.update(b,w);
	 }

	@Override
	public List<CivilLevel4> getCivilLevel4Paginated(int from, Workspace w) {
		return dao.getCivilLevel4Paginated(from,w);
	}

	@Override
	public List<CivilLevel4> getAll(long id) {
		
		return dao.getAll(id);
	}

}
