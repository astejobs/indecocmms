package com.aste.lsme.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cmms_adhoc_report")
public class AdhocReport {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "adhoc_report_id")
	Integer id;

	@JoinColumn(name = "report_form_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	AdhocReportForm adhocReportForm;
	
	@NotEmpty
	@Column(name = "report_name")
	String reportName;

	@Pattern(regexp = "^$|[\\s\\S]*[a-z|A-Z|0-9]+[\\s\\S]*", message = "spaces_not_allowed")
	@Column(name = "description")
	String description;

	@NotEmpty
	@Column(name = "requestor_name")
	String requestorName;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	UserDetail user;

	@Column(name = "created_date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	Date createdDate;

	@Column(name = "modified_date")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@JsonIgnore
	Date modifiedDate;

	@JoinColumn(name = "project_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	Workspace workspace;

	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "adhocReport")
	@JsonIgnore
	List<AdhocReportColumnFilterGroup> criteria;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "adhocReport")
	@JsonIgnore
	List<AdhocReportColumnFilterCondition> columnList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "adhocReport")
	@JsonIgnore
	List<AdhocReportColumnSortOrder> sort;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdhocReportForm getAdhocReportForm() {
		return adhocReportForm;
	}

	public void setAdhocReportForm(AdhocReportForm adhocReportForm) {
		this.adhocReportForm = adhocReportForm;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public UserDetail getUser() {
		return user;
	}

	public void setUser(UserDetail user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public List<AdhocReportColumnFilterGroup> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<AdhocReportColumnFilterGroup> criteria) {
		this.criteria = criteria;
	}

	public List<AdhocReportColumnSortOrder> getSort() {
		return sort;
	}

	public void setSort(List<AdhocReportColumnSortOrder> sort) {
		this.sort = sort;
	}

	public List<AdhocReportColumnFilterCondition> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<AdhocReportColumnFilterCondition> columnList) {
		this.columnList = columnList;
	}

	
	
}
