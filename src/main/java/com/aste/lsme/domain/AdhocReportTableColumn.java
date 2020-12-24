package com.aste.lsme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_table_columns")
public class AdhocReportTableColumn {

	
	@Id
	@Column(name = "column_id")
	Integer id;

	
	@JoinColumn(name = "table_id")
	@ManyToOne(fetch = FetchType.EAGER)
	AdhocReportTable table;
	

	@Column(name="column_filter_input_source")
	String columnFilterInputSource;
	
	@Column(name="column_name")
	String columnName;
	
	
	@Column(name="column_description")
	String columnDesc;	
	

	@Column(name="column_filter_input_type")
	String columnFilterInputType;
	
	
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public AdhocReportTable getTable() {
		return table;
	}


	public void setTable(AdhocReportTable table) {
		this.table = table;
	}


	public String getColumnFilterInputSource() {
		return columnFilterInputSource;
	}


	public void setColumnFilterInputSource(String columnFilterInputSource) {
		this.columnFilterInputSource = columnFilterInputSource;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String getColumnDesc() {
		return columnDesc;
	}


	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}


	public String getColumnFilterInputType() {
		return columnFilterInputType;
	}


	public void setColumnFilterInputType(String columnFilterInputType) {
		this.columnFilterInputType = columnFilterInputType;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((AdhocReportTableColumn)obj).getId().equals(id))			
			return true;
		return false;
	}


	

}
