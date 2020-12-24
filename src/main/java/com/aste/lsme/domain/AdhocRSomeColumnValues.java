package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_some_columns_values")
public class AdhocRSomeColumnValues {

	
	@Id
	@Column(name = "column_value_id")
	Integer id;

	
	@JoinColumn(name = "column_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReportTableColumn adhocReportTableColumn;


	@Column(name="possible_values")
	String possibleValues;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public AdhocReportTableColumn getAdhocReportTableColumn() {
		return adhocReportTableColumn;
	}


	public void setAdhocReportTableColumn(
			AdhocReportTableColumn adhocReportTableColumn) {
		this.adhocReportTableColumn = adhocReportTableColumn;
	}


	public String getPossibleValues() {
		return possibleValues;
	}


	public void setPossibleValues(String possibleValues) {
		this.possibleValues = possibleValues;
	}
	
}
