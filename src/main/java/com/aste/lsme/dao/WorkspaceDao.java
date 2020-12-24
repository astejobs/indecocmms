package com.aste.lsme.dao;

import java.util.List;

import com.aste.lsme.domain.Workspace;
import com.aste.lsme.webservicesDtos.WorkspaceDto;

public interface WorkspaceDao {
	
	public List<Workspace> getallworkspaces();
	public List<Workspace> getUserRelatedWorkspaces(Long id);
	public List<Workspace> getgroupprivilegeworkspace(List<Long> w);
	public Workspace getworkspaceonid(String id);
	public void persist(Workspace workspace);
	public void update(Workspace workspace);
	public List<Workspace> getWorkspacePaginated(int from);
	public Long getWorkspaceCount();
	public void delete(String id);
	public List<WorkspaceDto> getWorkspaces(Long grpId);
	
}
