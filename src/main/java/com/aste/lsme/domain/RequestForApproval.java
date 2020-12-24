package com.aste.lsme.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Requestor")
public class RequestForApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@ManyToOne
	private UserDetail requestor;
	
	@OneToOne
	private UserDetail costcenter;
	
	@OneToOne
	private UserDetail approver;
	
	@OneToOne
	private UserDetail  finaceapprover;
	
	@OneToOne
	private UserDetail  siteapprover;
	
	
	@NotEmpty(message="please fill the field")
	@Column(name="REFERENCE_NO",unique = true)
	private String refnumber;
	
	@Column(name="JOB_SITE")
	@NotEmpty(message="please fill the field")
	private String jobSite;
	
	@Column(name="JOB_CHARGABLE")
	@NotEmpty(message="please fill the field")
	private String jobChargable;
	
	@Column(name="PO_NUMBER")
	private String poNumber;
	
	@Column(name="PO_DOCUMENT")
	private String poDocument;
	
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="WORK_DATE")
	private Date workDate;
	
	@Column(name="PARTIAL_DELIVERY")
	@NotEmpty(message="please fill the field")
	private String partialDelivery;
	
	@Column(name="PARTIAL_PAYMENT_REQUIRED")
	@NotEmpty(message="please fill the field")
	private String partialPaymentRequired;
	
	@Column(name="PAYMENT_TERM")
	@NotEmpty(message="please fill the field")
	private String paymentTerm;
	
	@Column(name="LEAD_TIME")
	private String leadTime;
	
	
	@Column(name="REASON_FOR_REQUISITION")
	@NotEmpty(message="please fill the field")
	private String reasonForRequistion;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> purposeOfMaterialService = new ArrayList<String>();
	
	
	@ManyToMany(cascade= CascadeType.MERGE)
	@NotNull(message="Please select the quotation")
	@JoinTable(name="RFA_QUOTATION",
	joinColumns=@JoinColumn(name="RFA_ID"),
	inverseJoinColumns=@JoinColumn(name="GROUP_ID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Quotation> quotationheader;
	
	@OneToMany(mappedBy="requestor",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Quotations> quotation;
	
	
	
	@Column(name="SELLING_PRICE")
	@NotNull(message = "please enter the amount")
	private Double sellingPrice;
	
	@Column(name="COST_PRICE")
	@NotNull(message = "please enter the amount")
	private Double costPrice;
	
	@Column(name="APPLICABLE")
	private String applicable;
	

	@Column(name="GUIDELINES")
	private String guidelinesAreMet;
	

	@Column(name="RECURRENT_ITEMS")
	private String purchaseOfrecurrentItems;
	

	@Column(name="RFA_NO")
	private String rfaNumber;
	

	@Column(name="CONTRACTUAL_SUPPLY")
	private String contractualSupplyOfItems;
	

	@Column(name="NOT_APPLICABLE")
	private String notApplicable;
	

	@Column(name="SOLE_AGENT")
	private String soleAgentOEM;
	

	@Column(name="TIME_CRITICAL_PURCHASE")
	private String timeCriticalPurchase;
	

	@Column(name="REMARKS")
	@NotEmpty(message="please enter the remarks")
	private String remarks;
	
	@Column(name="APPROVER_STATUS")
	private String appStatus;
	
	@Column(name="COSTCENTER_STATUS")
	private String costCenterStatus;
	
	@Column(name="SITEAPPROVER_STATUS")
	private String siteApproverStatus;

	@Column(name="FINANCEOFF_STATUS")
	private String finStatus;
	

	@Column(name="ADMIN_STATUS")
	private String adStatus;
	

	@Column(name="REQUESTOR_STATUS")
	private String reqStatus;
	
	@Column(name="REQSUB_DATE")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date submittionDate;
	
	@Column(name="APPSUB_DATE")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date approverDate;
	
	@Column(name="SITEAPPROVER_DATE")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date siteApproverDate;
	
	@Column(name="FINANCE_DATE")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date financerDate;
	
	@Column(name="ADMIN_DATE")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date adminDate;
	
	@Column(name="COSTCENTER_DATE")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date costCenterdate;
	
	@Column(name="ADMIN_COMMENTS")
	private String adminComments;
	
	@Column(name="SIGNITURE")
	private String signiture;
	
	@Column(name="FINANCER_NAME")
	private String financername;
	
	@Column(name="FINANCE_COMMENTS")
	private String comments;
	
	@Column(name="APP_SIGNITURE")
	private String approverSig;
	
	@Column(name="FINANCE_SIGNITURE")
	private String financerSig;
	
	@Column(name="COSTCENTER_SIGNITURE")
	private String costCenterSig;
	
	@Column(name="SITEAPPROVER_SIGNITURE")
	private String siteApproverSig;
	
	@Column(name="ADMIN_SIGNITURE")
	private String adminSig;
	
	@Column(name="TOTAL_PRICE")
	private Double totalPriceQuoted;

	
	@ManyToOne
	private Workspace workspace;
	
	
	@Column(name="OTHER_MATERIAL")
	private String othermaterial;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getJobSite() {
		return jobSite;
	}

	public void setJobSite(String jobSite) {
		this.jobSite = jobSite;
	}

	public String getRefnumber() {
		return refnumber;
	}

	public void setRefnumber(String refnumber) {
		this.refnumber = refnumber;
	}

	public String getJobChargable() {
		return jobChargable;
	}

	public void setJobChargable(String jobChargable) {
		this.jobChargable = jobChargable;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getPartialDelivery() {
		return partialDelivery;
	}

	public void setPartialDelivery(String partialDelivery) {
		this.partialDelivery = partialDelivery;
	}

	public String getPartialPaymentRequired() {
		return partialPaymentRequired;
	}

	public void setPartialPaymentRequired(String partialPaymentRequired) {
		this.partialPaymentRequired = partialPaymentRequired;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getReasonForRequistion() {
		return reasonForRequistion;
	}

	public void setReasonForRequistion(String reasonForRequistion) {
		this.reasonForRequistion = reasonForRequistion;
	}

	public List<String> getPurposeOfMaterialService() {
		return purposeOfMaterialService;
	}

	public void setPurposeOfMaterialService(List<String> purposeOfMaterialService) {
		this.purposeOfMaterialService = purposeOfMaterialService;
	}

	public List<Quotations> getQuotation() {
		return quotation;
	}

	public void setQuotation(List<Quotations> quotation) {
		this.quotation = quotation;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getApplicable() {
		return applicable;
	}

	public void setApplicable(String applicable) {
		this.applicable = applicable;
	}

	
	
	

	public String getRfaNumber() {
		return rfaNumber;
	}

	public void setRfaNumber(String rfaNumber) {
		this.rfaNumber = rfaNumber;
	}



	public String getNotApplicable() {
		return notApplicable;
	}

	public void setNotApplicable(String notApplicable) {
		this.notApplicable = notApplicable;
	}


	public String getTimeCriticalPurchase() {
		return timeCriticalPurchase;
	}

	public void setTimeCriticalPurchase(String timeCriticalPurchase) {
		this.timeCriticalPurchase = timeCriticalPurchase;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getFinStatus() {
		return finStatus;
	}

	public void setFinStatus(String finStatus) {
		this.finStatus = finStatus;
	}

	public String getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

	public String getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}

	public Date getSubmittionDate() {
		return submittionDate;
	}

	public void setSubmittionDate(Date submittionDate) {
		this.submittionDate = submittionDate;
	}

	public Date getApproverDate() {
		return approverDate;
	}

	public void setApproverDate(Date approverDate) {
		this.approverDate = approverDate;
	}

	public String getSigniture() {
		return signiture;
	}

	public void setSigniture(String signiture) {
		this.signiture = signiture;
	}

	public Date getFinancerDate() {
		return financerDate;
	}

	public void setFinancerDate(Date financerDate) {
		this.financerDate = financerDate;
	}

	public Date getAdminDate() {
		return adminDate;
	}

	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}

	public String getAdminComments() {
		return adminComments;
	}

	public void setAdminComments(String adminComments) {
		this.adminComments = adminComments;
	}

	public String getFinancername() {
		return financername;
	}

	public void setFinancername(String financername) {
		this.financername = financername;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getApproverSig() {
		return approverSig;
	}

	public void setApproverSig(String approverSig) {
		this.approverSig = approverSig;
	}

	public String getFinancerSig() {
		return financerSig;
	}

	public void setFinancerSig(String financerSig) {
		this.financerSig = financerSig;
	}

	public Double getTotalPriceQuoted() {
		return totalPriceQuoted;
	}

	public void setTotalPriceQuoted(Double totalPriceQuoted) {
		this.totalPriceQuoted = totalPriceQuoted;
	}

	public UserDetail getRequestor() {
		return requestor;
	}

	public void setRequestor(UserDetail requestor) {
		this.requestor = requestor;
	}

	public UserDetail getApprover() {
		return approver;
	}

	public void setApprover(UserDetail approver) {
		this.approver = approver;
	}

	public UserDetail getFinaceapprover() {
		return finaceapprover;
	}

	public void setFinaceapprover(UserDetail finaceapprover) {
		this.finaceapprover = finaceapprover;
	}

	

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getGuidelinesAreMet() {
		return guidelinesAreMet;
	}

	public void setGuidelinesAreMet(String guidelinesAreMet) {
		this.guidelinesAreMet = guidelinesAreMet;
	}

	public String getPurchaseOfrecurrentItems() {
		return purchaseOfrecurrentItems;
	}

	public void setPurchaseOfrecurrentItems(String purchaseOfrecurrentItems) {
		this.purchaseOfrecurrentItems = purchaseOfrecurrentItems;
	}

	public String getContractualSupplyOfItems() {
		return contractualSupplyOfItems;
	}

	public void setContractualSupplyOfItems(String contractualSupplyOfItems) {
		this.contractualSupplyOfItems = contractualSupplyOfItems;
	}

	public String getSoleAgentOEM() {
		return soleAgentOEM;
	}

	public void setSoleAgentOEM(String soleAgentOEM) {
		this.soleAgentOEM = soleAgentOEM;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCostCenterStatus() {
		return costCenterStatus;
	}

	public void setCostCenterStatus(String costCenterStatus) {
		this.costCenterStatus = costCenterStatus;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public UserDetail getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(UserDetail costcenter) {
		this.costcenter = costcenter;
	}

	public Date getCostCenterdate() {
		return costCenterdate;
	}

	public void setCostCenterdate(Date costCenterdate) {
		this.costCenterdate = costCenterdate;
	}

	public String getCostCenterSig() {
		return costCenterSig;
	}

	public void setCostCenterSig(String costCenterSig) {
		this.costCenterSig = costCenterSig;
	}

	public List<Quotation> getQuotationheader() {
		return quotationheader;
	}

	public void setQuotationheader(List<Quotation> quotationheader) {
		this.quotationheader = quotationheader;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoDocument() {
		return poDocument;
	}

	public void setPoDocument(String poDocument) {
		this.poDocument = poDocument;
	}

	public String getAdminSig() {
		return adminSig;
	}

	public void setAdminSig(String adminSig) {
		this.adminSig = adminSig;
	}

	public UserDetail getSiteapprover() {
		return siteapprover;
	}

	public void setSiteapprover(UserDetail siteapprover) {
		this.siteapprover = siteapprover;
	}

	public String getSiteApproverStatus() {
		return siteApproverStatus;
	}

	public void setSiteApproverStatus(String siteApproverStatus) {
		this.siteApproverStatus = siteApproverStatus;
	}

	public Date getSiteApproverDate() {
		return siteApproverDate;
	}

	public void setSiteApproverDate(Date siteApproverDate) {
		this.siteApproverDate = siteApproverDate;
	}

	public String getSiteApproverSig() {
		return siteApproverSig;
	}

	public void setSiteApproverSig(String siteApproverSig) {
		this.siteApproverSig = siteApproverSig;
	}

	public String getOthermaterial() {
		return othermaterial;
	}

	public void setOthermaterial(String othermaterial) {
		this.othermaterial = othermaterial;
	}

	
	
	
	
	
}
