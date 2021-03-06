package com.aste.lsme.service;

import java.text.ParseException;
import java.util.List;

import javax.persistence.TypedQuery;

import com.aste.lsme.domain.QuotEquipment;
import com.aste.lsme.domain.QuotLabourRate;
import com.aste.lsme.domain.QuotOtherVendorEquipment;
import com.aste.lsme.domain.QuotOtherVendorLabour;
import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.Workspace;

public interface QuotationServiceInterface {
	
	public Quotation addQuotation(Quotation q) throws Exception;

	public TypedQuery<Quotation> getPredicatesOnCriteria1(
			QuotationSearch esc) throws ParseException;
	public Long countSearchPage(QuotationSearch qsc); 
	public List<Quotation> getallquotations(Workspace w);
	public  Quotation findquotation(Long id);
	public QuotEquipment findQuotEquipment(long id);
	public QuotLabourRate findQuotLabourRate(long id);
	public QuotOtherVendorEquipment findOtherVendorEquipment(long id);
	public QuotOtherVendorLabour findOtherVendorLabour(long id);
	public List<QuotEquipment> getSORListForQuotaion(Long qId);
	public List<QuotLabourRate> getLabourRateListForQuotaion(Long qId);
	public List<QuotOtherVendorLabour> getVendourLabourListForQuotaion(Long qId);
	public List<QuotOtherVendorEquipment> getVendorEquipListForQuotaion(Long qId);
	public List<Quotation> getSearchPage(QuotationSearch qsc);
	public List<Quotation> getQuotationListonids(List<Long> id);
	public void remove(Quotation q) throws Exception;
}
