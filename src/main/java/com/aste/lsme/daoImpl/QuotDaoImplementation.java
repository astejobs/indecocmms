package com.aste.lsme.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.QuotDaoInterface;
import com.aste.lsme.domain.Quotation;
import com.aste.lsme.domain.QuotationSearch;
import com.aste.lsme.domain.Quotations;
import com.aste.lsme.domain.Workspace;



@Repository
public class QuotDaoImplementation implements QuotDaoInterface {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void persist(Quotations quo) {
		
      em.persist(quo);
	}
	
	public Quotations findbyid(Long id)
	{
		return em.find(Quotations.class, id);
	}
    
	
	
	
}
