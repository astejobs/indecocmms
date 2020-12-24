package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_table")
public class AdhocReportTable {

	@Id
	@Column(name = "table_id")
	Integer id;

	@JoinColumn(name = "report_form_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReportForm formType;

	@Column(name = "table_name")
	String tableName;

	@OneToMany(mappedBy = "table")
	List<AdhocReportTableColumn> columns;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdhocReportForm getFormType() {
		return formType;
	}

	public void setFormType(AdhocReportForm formType) {
		this.formType = formType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
