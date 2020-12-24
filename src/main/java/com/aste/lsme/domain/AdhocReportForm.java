package com.aste.lsme.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cmms_adhoc_report_form")
public class AdhocReportForm {

	@Id
	@Column(name = "report_form_id")
	Long id;

	@Column(name = "report_form_value")
	String formType;

	@OneToMany(mappedBy = "formType", fetch = FetchType.EAGER)
	private List<AdhocReportTable> tables;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

}
