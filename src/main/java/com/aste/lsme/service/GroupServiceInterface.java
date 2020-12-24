package com.aste.lsme.service;

import java.util.List;

import com.aste.lsme.domain.GroupPriviledges;
import com.aste.lsme.domain.ModuleDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;

public interface GroupServiceInterface {
	public UserGroup saveGroup(UserGroup group) throws Exception;
	public List<UserGroup> getallgroups();
	public UserGroup findGroup(Long id);
	public String removeGroup(Long id) throws Exception;
	public UserGroup updateGroup(UserGroup group);
	public List<ModuleDetail> getallmodules();
	public List<ModuleDetail> getmodulesbasedongroup(GroupPriviledges group);
	public List<ModuleDetail> getgrouprelatedmodule (Long id);
	public List<Workspace> getgrouprelatedsite(Long id);
	public ModuleDetail getmodulebyName(String name);
    public UserGroup findGroupbyName(String name);
	
}
