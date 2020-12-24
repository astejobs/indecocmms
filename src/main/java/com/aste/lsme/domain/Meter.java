package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;




@Entity
@Table(name = "meter")
public class Meter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@NotEmpty(message = "Date Cannot Be Empty")
	@Column(name = "meter_no")
	String meterno;

	@NotNull(message = "Select Any Location")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location")
	Location location;
	
	@OneToMany(mappedBy = "meter")
	private List<UtilityReading> utilreadings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMeterno() {
		return meterno;
	}

	public void setMeterno(String meterno) {
		this.meterno = meterno;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<UtilityReading> getUtilreadings() {
		return utilreadings;
	}

	public void setUtilreadings(List<UtilityReading> utilreadings) {
		this.utilreadings = utilreadings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((meterno == null) ? 0 : meterno.hashCode());
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
		Meter other = (Meter) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (meterno == null) {
			if (other.meterno != null)
				return false;
		} else if (!meterno.equals(other.meterno))
			return false;
		return true;
	}



}

