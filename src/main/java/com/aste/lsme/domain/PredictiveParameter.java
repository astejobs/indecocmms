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
@Table(name="pred_parameters")
public class PredictiveParameter {


	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="id")
	long id;
	
	
	@JoinColumn(name="pred_id")
	@ManyToOne(fetch = FetchType.EAGER)	
	ThrushHoldValue predictiveMaint;
	
	
	@Column(name="parameter")
	String parameter;
	
	@Column(name="value")
	String fieldValue;
	
	@Column(name="unit")
	String fieldUnit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ThrushHoldValue getPredictiveMaint() {
		return predictiveMaint;
	}

	public void setPredictiveMaint(ThrushHoldValue predictiveMaint) {
		this.predictiveMaint = predictiveMaint;
	}


	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getFieldUnit() {
		return fieldUnit;
	}

	public void setFieldUnit(String fieldUnit) {
		this.fieldUnit = fieldUnit;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		PredictiveParameter other = (PredictiveParameter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
