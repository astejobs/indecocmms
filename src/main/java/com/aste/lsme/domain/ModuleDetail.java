package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="module_dtl")
public class ModuleDetail {

	@Id
	@Column(name="MODULE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column(name="MODULE_NAME")
	String module_Name;
	
	@Column(name="module_parent_id")
	Long module_Parent_Id;
	
	@ManyToMany(mappedBy="moduleDetails")
	@JsonIgnore
	List<UserGroup> userGroups;

    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModule_Name() {
		return module_Name;
	}

	public void setModule_Name(String module_Name) {
		this.module_Name = module_Name;
	}

	public Long getModule_Parent_Id() {
		return module_Parent_Id;
	}

	public void setModule_Parent_Id(Long module_Parent_Id) {
		this.module_Parent_Id = module_Parent_Id;
	}

	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuleDetail other = (ModuleDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	
	
	
}
