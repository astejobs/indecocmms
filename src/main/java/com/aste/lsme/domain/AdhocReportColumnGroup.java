package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_column_group")
public class AdhocReportColumnGroup {

	@Id
	@Column(name = "column_in_group_id")
	Integer id;

	@JoinColumn(name = "column_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReportTableColumn adhocReportTableColumn;

	@JoinColumn(name = "adhoc_report_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReport adhocReport;

	

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

	public AdhocReport getAdhocReport() {
		return adhocReport;
	}

	public void setAdhocReport(AdhocReport adhocReport) {
		this.adhocReport = adhocReport;
	}

	
	
}
