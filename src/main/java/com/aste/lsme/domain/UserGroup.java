package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_group")
public class UserGroup {

	@Id
	@Column(name = "USER_GRPID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "USER_GRPNM",unique=true)
	@NotEmpty(message="Group name can't be Empty")
	String userGroupName;

	@Column(name = "Group_Desc")
	@NotEmpty(message="Group Description can't be Empty")
	String groupDesc;
	
	@OneToMany(mappedBy = "usergroup")
	@LazyCollection(LazyCollectionOption.FALSE)
	List<UserDetail> userdetails;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.MERGE)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="GROUP_MODULE",
	joinColumns=@JoinColumn(name="GROUP_ID"),
	inverseJoinColumns=@JoinColumn(name="MODULE_ID"))
	List<ModuleDetail> moduleDetails;
	

	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="GROUP_WORKSPACE",
	joinColumns=@JoinColumn(name="GROUP_ID"),
	inverseJoinColumns=@JoinColumn(name="WORKSPACE_ID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Workspace> workspaces; 
	
	@Column(name="Read_Only")
	boolean readOnly=false;
	
	@ManyToOne(fetch=FetchType.EAGER)
	Role role;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

    
   

	public List<UserDetail> getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(List<UserDetail> userdetails) {
		this.userdetails = userdetails;
	}

	public List<ModuleDetail> getModuleDetails() {
		return moduleDetails;
	}

	public void setModuleDetails(List<ModuleDetail> moduleDetails) {
		this.moduleDetails = moduleDetails;
	}

	public List<Workspace> getWorkspaces() {
		return workspaces;
	}

	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	


}
