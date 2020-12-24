package com.aste.lsme.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.QuotDaoInterface;
import com.aste.lsme.domain.Quotations;
import com.aste.lsme.service.QuotServiceInterface;


@Transactional
@Service
public class QuotServiceImplementation implements QuotServiceInterface {
	
	@Autowired
	private QuotDaoInterface quotservice;
	
	@Override
	public void persist(Quotations quo) {
		
       quotservice.persist(quo);
	}
	
	public Quotations findbyid(Long id)
	{
		return quotservice.findbyid(id);
	}

}
