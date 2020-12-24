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


@Entity
@Table(name="access_detail")
public class AccessDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name=" 	ACCESS_DETAIL_ID")
	Long Access_Detail_Id;

	@JoinColumn(name="group_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	UserGroup userGroup;
	
	@JoinColumn(name="MODULE_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	ModuleDetail moduleDetail;

	public Long getAccess_Detail_Id() {
		return Access_Detail_Id;
	}

	public void setAccess_Detail_Id(Long access_Detail_Id) {
		Access_Detail_Id = access_Detail_Id;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public ModuleDetail getModuleDetail() {
		return moduleDetail;
	}

	public void setModuleDetail(ModuleDetail moduleDetail) {
		this.moduleDetail = moduleDetail;
	}
	
	
}
