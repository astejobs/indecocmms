package com.aste.lsme.daoImpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.aste.lsme.dao.ExceptionDateDaoService;
import com.aste.lsme.domain.ExceptionDate;
import com.aste.lsme.domain.Workspace;

@Repository
public class ExceptionDaoImpl implements ExceptionDateDaoService {

	@PersistenceContext
	EntityManager em;
	

	
	@Override
	public List<ExceptionDate> list(Workspace w) {
		
		Query q = em
				.createQuery("select l from ExceptionDate l where l.workspace=:workspace");
		q.setParameter("workspace", w);

		return q.getResultList();
	}



	@Override
	public Date getAllowedDate(Date checkDate, List<ExceptionDate> datelist, Workspace w) {
		// TODO Auto-generated method stub
		return null;
	}
}
