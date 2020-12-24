package com.aste.lsme.serviceImpl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aste.lsme.dao.ExceptionDateDaoService;
import com.aste.lsme.domain.DateUtility;
import com.aste.lsme.domain.ExceptionDate;
import com.aste.lsme.domain.Workspace;
import com.aste.lsme.service.ExceptionDateService;

@Component
@Transactional
@Service
public class ExceptionDateServiceImpl implements ExceptionDateService {

	@Autowired
	ExceptionDateDaoService dao;
	
	@Override
	public List<ExceptionDate> list(Workspace w) {
		
		return dao.list(w);
	}

	@Override
	public Date getAllowedDate(Date checkDate, List<ExceptionDate> datelist, Workspace w) {
		DateTime check=new DateTime(checkDate);			
		while(true){			
			if(isExceptionDate(checkDate, datelist)){
				check=check.plusDays(1);
				checkDate=check.toDate();
			}
			else
				return check.toDate();
		}
	}

	
	public boolean isExceptionDate(Date checkDate,List<ExceptionDate> exceptionDatelist){
		DateTime check= new DateTime(checkDate);
		for (ExceptionDate exceptionDate : exceptionDatelist) {
			if(DateUtility.compareWithoutYear(exceptionDate.getException_date(), check.toDate()) || (check.dayOfWeek().get()==DateTimeConstants.SUNDAY)){
				System.out.println("exeption.."+ check.toDate()+ check.dayOfWeek().get()+"   "+DateTimeConstants.SUNDAY+" "+( check.dayOfWeek().get()==DateTimeConstants.SUNDAY));
				return true;								
			}
			}
		
		return false;
	}
	
}
