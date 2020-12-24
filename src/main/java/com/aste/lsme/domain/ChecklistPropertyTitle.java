package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "checklist_property_title")
public class ChecklistPropertyTitle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	ChecklistHeader checklistHeader;

	@Column(name = "title")
	String title;
	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "checklistPropertyTitle", cascade = CascadeType.ALL)
	List<ChecklistProperty> properties;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChecklistHeader getChecklistHeader() {
		return checklistHeader;
	}

	public void setChecklistHeader(ChecklistHeader checklistHeader) {
		this.checklistHeader = checklistHeader;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ChecklistProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<ChecklistProperty> properties) {
		this.properties = properties;
	}

	
}
