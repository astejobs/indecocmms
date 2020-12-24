package com.aste.lsme.service;

import java.util.Date;
import java.util.List;

import com.aste.lsme.domain.ExceptionDate;
import com.aste.lsme.domain.Workspace;



public interface ExceptionDateService
{
	List<ExceptionDate> list(Workspace w);
	Date getAllowedDate(Date checkDate, List<ExceptionDate> datelist,Workspace w);

}
