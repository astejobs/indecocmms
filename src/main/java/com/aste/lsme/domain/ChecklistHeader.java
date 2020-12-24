package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "checklist_header")
public class ChecklistHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@Column(name = "checklist_name")
	@NotEmpty(message="Please enter the checklist Name")
	String checklistName;

	@Column(name = "remarks")
	String prerequisite;

	@JsonIgnore
	@ManyToMany()
	@NotNull(message= "Please Select any Equipment")
	@JoinTable(name="CHECKLIST_EQUIPMENT",
	joinColumns=@JoinColumn(name="CHECKLIST_ID"),
	inverseJoinColumns=@JoinColumn(name="EQUIPMENT_ID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Equipment> equipment= new ArrayList<Equipment>();;

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "checklistHeader", cascade = CascadeType.ALL)
	List<ChecklistPropertyTitle> propertyTitles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChecklistName() {
		return checklistName;
	}

	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

 

	public List<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	public List<ChecklistPropertyTitle> getPropertyTitles() {
		return propertyTitles;
	}

	public void setPropertyTitles(List<ChecklistPropertyTitle> propertyTitles) {
		this.propertyTitles = propertyTitles;
	}

	
}
