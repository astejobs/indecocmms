package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.Building;
import com.aste.lsme.domain.CivilLevel3;
import com.aste.lsme.domain.Workspace;



public interface CivilLevel3Service {


	 public List<CivilLevel3> getCivilLevel3Paginated(int from,Workspace w);
	 public List<CivilLevel3> getCivilLevel3(long string);
	 public long count(Workspace w);
	 public boolean add(CivilLevel3 c,Workspace w);
	 public void remove(Long id);
	 public CivilLevel3 find(Long id);
	 public boolean update(CivilLevel3 b,Workspace w);
	 public List<CivilLevel3> getAll(Workspace w);
	 
}
