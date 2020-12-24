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
import javax.persistence.Table;

@Entity
@Table(name = " cmms_adhoc_report_column_filter_condition")
public class AdhocReportColumnFilterCondition {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "column_filter_condition_id")
	Integer id;

	@JoinColumn(name = "column_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReportTableColumn tableColumn;


	@JoinColumn(name = "adhoc_report_id")
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	AdhocReport adhocReport;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdhocReportTableColumn getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(AdhocReportTableColumn tableColumn) {
		this.tableColumn = tableColumn;
	}

	public AdhocReport getAdhocReport() {
		return adhocReport;
	}

	public void setAdhocReport(AdhocReport adhocReport) {
		this.adhocReport = adhocReport;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((AdhocReportColumnFilterCondition)obj).getId().equals(id))
			return true;
		return false;

	}

}
