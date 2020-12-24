package com.aste.lsme.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.aste.lsme.domain.ThrushHoldValue;
import com.aste.lsme.domain.PredictiveMonitorClass;




@Entity
@Table(name="pred_monitorpoints")
public class PredictiveMonitorPoint {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="id")
	long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pred_id")
	ThrushHoldValue predictiveMaint;
	
	
	@Column(name="monitor_type")
	String monitorType;
	
	@JoinColumn(name="monitor_class")
	@ManyToOne(fetch = FetchType.EAGER)	
	
	PredictiveMonitorClass predictiveMonClass;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@NotNull(message=" Reading Date can not be null")
	@Column(name="reading_date")
	Date date;
	
	
	
	@Column(name="unit")
	String unit;
	
	@Column(name="allow_low_value")
	double lowValue;
	
	@Column(name="allow_high_value")
	double highValue;
	
	@Column(name="actual_value")
	double actualValue;
	
	@Column(name="reading_delta")
	double readingDelta;

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

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public PredictiveMonitorClass getPredictiveMonClass() {
		return predictiveMonClass;
	}

	public void setPredictiveMonClass(PredictiveMonitorClass predictiveMonClass) {
		this.predictiveMonClass = predictiveMonClass;
	}

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getLowValue() {
		return lowValue;
	}

	public void setLowValue(double lowValue) {
		this.lowValue = lowValue;
	}

	public double getHighValue() {
		return highValue;
	}

	public void setHighValue(double highValue) {
		this.highValue = highValue;
	}

	public double getActualValue() {
		return actualValue;
	}

	public void setActualValue(double actualValue) {
		this.actualValue = actualValue;
	}

	public double getReadingDelta() {
		return readingDelta;
	}

	public void setReadingDelta(double readingDelta) {
		this.readingDelta = readingDelta;
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
		PredictiveMonitorPoint other = (PredictiveMonitorPoint) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

	
	
}