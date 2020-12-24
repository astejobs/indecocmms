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
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@Entity
@Table(name = "utility_reading")
public class UtilityReading {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Long id;

	@NotNull(message = "Select Any Meter")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meter")
	Meter meter;

	@NotNull(message = "Date Cannot Be Empty")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "date")
	private Date date;

	@NumberFormat(style=Style.NUMBER)
	@Column(name = "current_reading")
	Double curReading;

	@NumberFormat(style=Style.NUMBER)
	@Column(name = "previous_reading")
	Double preReading;

	@Column(name = "remarks")
	String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Meter getMeter() {
		return meter;
	}

	public void setMeter(Meter meter) {
		this.meter = meter;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getCurReading() {
		return curReading;
	}

	public void setCurReading(Double curReading) {
		this.curReading = curReading;
	}

	public Double getPreReading() {
		return preReading;
	}

	public void setPreReading(Double preReading) {
		this.preReading = preReading;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}

