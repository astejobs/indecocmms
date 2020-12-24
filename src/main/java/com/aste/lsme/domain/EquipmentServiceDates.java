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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Equipment_Service_Dates")
public class EquipmentServiceDates {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	private Long id;

	@Column(name = "Date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;

	@JoinColumn(name = "Equipment")
	@ManyToOne(fetch = FetchType.EAGER)
	private Equipment equipment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		return date.toString();
	}

    

}
