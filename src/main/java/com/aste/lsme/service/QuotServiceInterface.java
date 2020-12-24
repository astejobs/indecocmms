package com.aste.lsme.service;

import com.aste.lsme.domain.Quotations;

public interface QuotServiceInterface {

	public void persist (Quotations quo);
	public Quotations findbyid(Long id);
}
