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
@Table(name = "equipment_task_reading")
public class EquipmentTaskReading {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	
	@Column(name = "reading")
	Double reading;

	@JoinColumn(name = "pmtask_id")
	@ManyToOne(fetch = FetchType.EAGER)
	Task task;

	@JoinColumn(name = "equipment_baseline_id")
	@ManyToOne(fetch = FetchType.EAGER)
	EquipmentBaseline equipmentBaseline;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getReading() {
		return reading;
	}

	public void setReading(Double reading) {
		this.reading = reading;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public EquipmentBaseline getEquipmentBaseline() {
		return equipmentBaseline;
	}

	public void setEquipmentBaseline(EquipmentBaseline equipmentBaseline) {
		this.equipmentBaseline = equipmentBaseline;
	}

}

