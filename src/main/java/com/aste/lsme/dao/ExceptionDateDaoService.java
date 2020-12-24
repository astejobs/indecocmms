package com.aste.lsme.dao;

import java.util.Date;
import java.util.List;

import com.aste.lsme.domain.ExceptionDate;
import com.aste.lsme.domain.Workspace;


public interface ExceptionDateDaoService {
	
	List<ExceptionDate> list(Workspace w);
	Date getAllowedDate(Date checkDate, List<ExceptionDate> datelist,Workspace w);
}
