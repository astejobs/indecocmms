package com.aste.lsme.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.GroupDetailsDaoInterface;
import com.aste.lsme.domain.GroupPriviledges;
import com.aste.lsme.domain.ModuleDetail;
import com.aste.lsme.domain.UserGroup;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.GroupServiceInterface;

@Service
@Transactional
public class GroupServiceImpl implements GroupServiceInterface {
	
	@Autowired
	GroupDetailsDaoInterface groupdao;

	@Override
	public UserGroup saveGroup(UserGroup group) throws Exception {
		
		return groupdao.saveGroup(group) ;
	}

	@Override
	public List<UserGroup> getallgroups() {
		
		return groupdao.getallgroups();
	}

	@Override
	public UserGroup findGroup(Long id) {
		
		return groupdao.findGroup(id);
	}

	@Override
	public String removeGroup(Long id) throws Exception {
		
		return groupdao.removeGroup(id);
	}

	@Override
	public UserGroup updateGroup(UserGroup group) {
		
		return  groupdao.updateGroup(group);
	}
	
	public List<ModuleDetail> getallmodules()
	{
		return groupdao.getallmodules();
	}
	
	public List<ModuleDetail> getmodulesbasedongroup(GroupPriviledges group)
	{
		return groupdao.getmodulesbasedongroup(group);
	}
	public List<ModuleDetail> getgrouprelatedmodule (Long id)
	{
		 return  groupdao.getgrouprelatedmodule(id);
	}
	 
	public List<Workspace> getgrouprelatedsite(Long id)
	{
		 return  groupdao.getgrouprelatedsite(id);
		 
	}
	 public ModuleDetail getmodulebyName(String name)
	 {
		 return  groupdao.getmodulebyName(name);
	 }
	 
	 public UserGroup findGroupbyName(String name)
	 {
		 return groupdao.findGroupbyName(name);
	 }

}
