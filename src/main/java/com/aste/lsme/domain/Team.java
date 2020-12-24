package com.aste.lsme.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "team")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	 Long id;

	
	
	@NotEmpty(message="Enter Team name")
	@Pattern(regexp = "^([a-zA-Z0-9-]+[\\s\\S]*)", message = "other_symbols_not_allowed")
	@Size(max = 30, message="please enter smaller name")
	@Column(name = "name")
	String name;
	
	
	
	@NotNull
	@ManyToMany
	@JoinTable(name="team_technician",
	joinColumns=@JoinColumn(name="Team_ID"),
	inverseJoinColumns=@JoinColumn(name="technician_ID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Technician> technician;
	
	
	
	@ManyToMany(cascade=CascadeType.MERGE,mappedBy="teams")
	List<Schedule> schedule;



	@ManyToOne
	private Workspace workspace;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public List<Technician> getTechnician() {
		return technician;
	}



	public void setTechnician(List<Technician> technician) {
		this.technician = technician;
	}



	public Workspace getWorkspace() {
		return workspace;
	}



	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}



	public List<Schedule> getSchedule() {
		return schedule;
	}



	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
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
		Team other = (Team) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
