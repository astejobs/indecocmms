package com.aste.lsme.service;

import java.util.List;
import com.aste.lsme.domain.CivilLevel4;
import com.aste.lsme.domain.Workspace;



public interface CivilLevel4Service {
	

	 public long count(Workspace w);
	 public boolean add(CivilLevel4 c,Workspace w);
	 public void remove(Long id);
	 public CivilLevel4 find(Long id);
	 public boolean update(CivilLevel4 b,Workspace w);
	 public List<CivilLevel4> getCivilLevel4Paginated(int from,Workspace w);
	 public List<CivilLevel4> getAll(long id);
}
