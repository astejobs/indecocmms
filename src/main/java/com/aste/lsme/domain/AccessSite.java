package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="site_access")
public class AccessSite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	Long id;

	@JsonIgnore
	@JoinColumn(name="group_id")
	@ManyToOne(fetch=FetchType.EAGER)
	UserGroup userGroup;
	
	@JoinColumn(name="project_id")
	@ManyToOne(fetch=FetchType.EAGER)
	Workspace workspace;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	
	
}
