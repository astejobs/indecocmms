package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.WorkspaceDao;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.WorkspaceService;
import com.aste.lsme.webservicesDtos.WorkspaceDto;

@Service
@Transactional
public class WorkspaceServiceImpl implements WorkspaceService {
	
	@Autowired
	WorkspaceDao workspacedao;
	
	public List<Workspace> getallworkspaces()
	{
		return workspacedao.getallworkspaces();
	}
	
	public List<Workspace> getUserRelatedWorkspaces(Long id)
	{
		return workspacedao.getUserRelatedWorkspaces(id);
	}
	public List<Workspace> getgroupprivilegeworkspace(List<Long> w)
	{
		return workspacedao.getgroupprivilegeworkspace(w);
	}
	public Workspace getworkspaceonid(String id)
	{
		return workspacedao.getworkspaceonid(id);
	}

	@Override
	public void persist(Workspace workspace) {

		workspacedao.persist(workspace);
	}

	@Override
	public void update(Workspace workspace) {
		workspacedao.update(workspace);
	}

	@Override
	public List<Workspace> getWorkspacePaginated(int from) {
		return workspacedao.getWorkspacePaginated(from);
	}

	@Override
	public Long getWorkspaceCount() {
		return workspacedao.getWorkspaceCount();
	}

	@Override
	public void delete(String id) {
		workspacedao.delete(id);
	}

	@Override
	public List<WorkspaceDto> getWorkspaces(Long grpId) {
		return workspacedao.getWorkspaces(grpId);
	}

	
	

}
