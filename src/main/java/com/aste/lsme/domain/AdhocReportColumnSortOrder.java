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
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_column_sort_order")
public class AdhocReportColumnSortOrder {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "sort_order_id")
	Integer id;

	@JoinColumn(name = "column_id")
	@OneToOne
	AdhocReportTableColumn column;

	@Column(name = "sort_order")
	String sortOrder;

	@Column(name = "groupBy")
	String groupBy;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "adhoc_report")
	AdhocReport adhocReport;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public AdhocReportTableColumn getColumn() {
		return column;
	}

	public void setColumn(AdhocReportTableColumn column) {
		this.column = column;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public AdhocReport getAdhocReport() {
		return adhocReport;
	}

	public void setAdhocReport(AdhocReport adhocReport) {
		this.adhocReport = adhocReport;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	 @PrePersist
	    protected void falseGroupBy() {
	      if (groupBy == null) {
	    	  groupBy = "false";
	      }
	 }

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(((AdhocReportColumnSortOrder)obj).getId().equals(id))
			return true;
		return false;
	}
	 
}
