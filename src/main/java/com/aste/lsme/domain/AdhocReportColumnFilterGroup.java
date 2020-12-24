package com.aste.lsme.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "cmms_adhoc_report_column_filter_group")
public class AdhocReportColumnFilterGroup {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "column_in_filter_group_id")
	Integer id;

	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "adhoc_report_id")
	AdhocReport adhocReport;
	
	@JoinColumn(name = "column_id")
	@OneToOne
	AdhocReportTableColumn tableColumn;
	


	@Column(name="conditionn")
	String conditionn;

	@NotEmpty
	@Column(name="valuee")
	String valuee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdhocReport getAdhocReport() {
		return adhocReport;
	}

	public void setAdhocReport(AdhocReport adhocReport) {
		this.adhocReport = adhocReport;
	}

	public AdhocReportTableColumn getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(AdhocReportTableColumn tableColumn) {
		this.tableColumn = tableColumn;
	}

	

	public String getConditionn() {
		return conditionn;
	}

	public void setConditionn(String conditionn) {
		this.conditionn = conditionn;
	}

	public String getValuee() {
		return valuee;
	}

	public void setValuee(String valuee) {
		this.valuee = valuee;
	}

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((AdhocReportColumnFilterGroup)obj).getId().equals(id))
			return true;
		return false;

	}

	
	
	

}
