package com.aste.lsme.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "quotation")
public class Quotation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@NotEmpty(message="Enter a Reference Number")
	@Column(name="refrenceno",unique=true)
	String refrenceno;
	
	@ManyToMany(mappedBy="quotationheader")
    private List<RequestForApproval> rfa;
	
	@Column(name = "qdate")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull(message="Please enter the Installation Date")
	Date quotationDate;
	
	@Column(name = "validity")
	@NotEmpty(message="Enter the validity of the quotation")
	String validity;
	
	@Column(name = "quotationFor")
	@NotEmpty(message="Enter a Client Name")
	String quotationFor;
	
	@Column(name="qdesc",length=2000)
	String quotationDescription;
	
	
	@Column(name="gst")
	Float gst;
	
	@Column(name="remarks")
	String remarks;
	
	@Column(name="totalAmount")
	Float totalAmount;
	
	@Column(name="actualamount")
	Float actualamount;
	
	@Column(unique=true,nullable=false,name="quotaionCode")
	String quotationCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROJECT_ID")
	Workspace workspace;
	
	@Column(name="EmergencyorDiscount")
	String value;
	
	@Column(name="Type")
	String type;
	
	@Column(name="EmergencyorDiscountpercent")
	Float emergencyOrDiscountPercent;
	
	@Column(name="ExtraCharges")
	Float extraCharges;
	
	@Column(name="AmountAfter")
	Float amountafter;
	
    @OneToMany(mappedBy = "quotation", cascade= CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("id")
	List<QuotEquipment> equipmentList; 
	
	@OneToMany(mappedBy = "quotation", cascade= CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OrderBy("id")
	List<QuotLabourRate> labourRateList;
	
	@OneToMany(mappedBy = "quotation", cascade= CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OrderBy("id")
	List<QuotOtherVendorEquipment> otherVendorEquipmentList;
	
	@OneToMany(mappedBy = "quotation", cascade= CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OrderBy("id")
	List<QuotOtherVendorLabour> otherVendorLabourList;

	

	public String getRefrenceno() {
		return refrenceno;
	}

	public void setRefrenceno(String refrenceno) {
		this.refrenceno = refrenceno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getQuotationDate() {
		return quotationDate;
	}

	public void setQuotationDate(Date quotationDate) {
		this.quotationDate = quotationDate;
	}


	public String getQuotationDescription() {
		return quotationDescription;
	}

	public void setQuotationDescription(String quotationDescription) {
		this.quotationDescription = quotationDescription;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getQuotationFor() {
		return quotationFor;
	}

	public void setQuotationFor(String quotationFor) {
		this.quotationFor = quotationFor;
	}


	public Float getGst() {
		return gst;
	}

	public void setGst(Float gst) {
		this.gst = gst;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getActualamount() {
		return actualamount;
	}

	public void setActualamount(Float actualamount) {
		this.actualamount = actualamount;
	}

	

	public String getQuotationCode() {
		return quotationCode;
	}

	public void setQuotationCode(String quotationCode) {
		this.quotationCode = quotationCode;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public List<QuotEquipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<QuotEquipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	

	public List<QuotLabourRate> getLabourRateList() {
		return labourRateList;
	}

	public void setLabourRateList(List<QuotLabourRate> labourRateList) {
		this.labourRateList = labourRateList;
	}

	public List<QuotOtherVendorEquipment> getOtherVendorEquipmentList() {
		return otherVendorEquipmentList;
	}



	public void setOtherVendorEquipmentList(
			List<QuotOtherVendorEquipment> otherVendorEquipmentList) {
		this.otherVendorEquipmentList = otherVendorEquipmentList;
	}

	public List<QuotOtherVendorLabour> getOtherVendorLabourList() {
		return otherVendorLabourList;
	}

	public void setOtherVendorLabourList(
			List<QuotOtherVendorLabour> otherVendorLabourList) {
		this.otherVendorLabourList = otherVendorLabourList;
	}

    
	public List<RequestForApproval> getRfa() {
		return rfa;
	}

	public void setRfa(List<RequestForApproval> rfa) {
		this.rfa = rfa;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Float getEmergencyOrDiscountPercent() {
		return emergencyOrDiscountPercent;
	}

	public void setEmergencyOrDiscountPercent(Float emergencyOrDiscountPercent) {
		this.emergencyOrDiscountPercent = emergencyOrDiscountPercent;
	}

	public Float getAmountafter() {
		return amountafter;
	}

	public void setAmountafter(Float amountafter) {
		this.amountafter = amountafter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getExtraCharges() {
		return extraCharges;
	}

	public void setExtraCharges(Float extraCharges) {
		this.extraCharges = extraCharges;
	}

    
    
	

	

	

	
	
	
	
}
