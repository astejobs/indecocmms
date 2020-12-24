package com.aste.lsme.dao;



import java.text.ParseException;

import javax.persistence.TypedQuery;

import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.Quotations;

public interface QuotDaoInterface {

	public void persist(Quotations quo);
	public Quotations findbyid(Long id);
	
}
