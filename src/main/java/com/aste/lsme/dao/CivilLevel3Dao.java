package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.CivilLevel3;
import com.aste.lsme.domain.Workspace;




public interface CivilLevel3Dao {
	
	public List<CivilLevel3> getCivilLevel3Paginated(int from,Workspace w);
	public List<CivilLevel3> getCivillevel3(long id);
	 public long count(Workspace w);
	 public boolean add(CivilLevel3 c,Workspace w);
	 public void remove(Long id);
	 public CivilLevel3 find(Long id);
	 public boolean update(CivilLevel3 b,Workspace w);
	 public List<CivilLevel3> getAll(Workspace w);
}
