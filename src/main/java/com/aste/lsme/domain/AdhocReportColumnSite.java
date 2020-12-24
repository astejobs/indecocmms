package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_columns_site")
public class AdhocReportColumnSite {

	@Id
	@Column(name = "column_site_id")
	Integer id;

	@JoinColumn(name = "column_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReportTableColumn adhocReportTableColumn;

	@JoinColumn(name = "worksite_group_id")
	@ManyToOne(fetch = FetchType.EAGER)
	Workspace workSpaceGroup;

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

	public Workspace getWorkSpaceGroup() {
		return workSpaceGroup;
	}

	public void setWorkSpaceGroup(Workspace workSpaceGroup) {
		this.workSpaceGroup = workSpaceGroup;
	}

	

}
