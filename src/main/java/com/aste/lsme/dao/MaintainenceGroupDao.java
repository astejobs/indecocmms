package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.MaintainenceGroup;
import com.aste.lsme.domain.Workspace;


public interface MaintainenceGroupDao {
	
	
	public boolean persist (MaintainenceGroup mainGrp, Workspace w);
	public MaintainenceGroup get(Long id);
	public void delete(Long id);
	public boolean update(MaintainenceGroup mainGrp, Workspace w);
	public List<MaintainenceGroup> getAll();
	public long getMainGrpCount(Workspace w);
	public List<MaintainenceGroup> getMainGrpPaginated(int from, Workspace w);
	public List<MaintainenceGroup> getWorkspaceMainGrp(Workspace w);
	public List<MaintainenceGroup> getmainGrpCategories(Long workspaceId);	

}
