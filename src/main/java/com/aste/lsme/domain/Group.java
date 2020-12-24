package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "cmms_maint_grp")
public class Group {
	@Id
	@Column(name = "id")
	long id;	
	
	
	
	@Pattern(regexp = "^([a-zA-Z0-9-]+[\\s\\S]*)", message = "other_symbols_not_allowed")
	@Size(max=50)
	@NotEmpty(message="Select any Group")
	@Column(name = "MAINT_GRP_ID")
	String maint_id;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "proj_id")
	Workspace workspace;
	
	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Size(max=200)
	@Column(name = "MAINT_GRP_DESC")
	String mGrpDesc;
	
	@Column(name = "ACTIVE_FLAG")
	String activeFlag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaint_id() {
		return maint_id;
	}

	public void setMaint_id(String maint_id) {
		this.maint_id = maint_id;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public String getmGrpDesc() {
		return mGrpDesc;
	}

	public void setmGrpDesc(String mGrpDesc) {
		this.mGrpDesc = mGrpDesc;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	
	@Override
	public String toString(){
		return getmGrpDesc();
	}

}
