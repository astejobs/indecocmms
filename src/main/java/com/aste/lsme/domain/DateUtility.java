package com.aste.lsme.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class DateUtility {

	public static int dateDifference(String endDate, String startDate) throws ParseException {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 
		 
		Date end = s.parse(endDate);
		Date start = s.parse(startDate);
        Long diff;
		try {
			//d1 = format.parse(startDate.toString());
			//d2 = format.parse(endDate.toString());
     	    diff = end.getTime() - start.getTime();
     	    System.out.println("in method Date Time difference is "+diff);
	}catch(Exception e){
		 System.out.println("Exception generated in date difference ");
		return -1;
	}
		if(diff>=0) 
		{
           return 1;
		}else
		{
			return -1;
		}
	
	}
	
		public static boolean compareWithoutYear(Date d1, Date d2){				
				DateTime dt1=new DateTime(d1);
				DateTime dt2=new DateTime(d2);				
				if(dt1.monthOfYear().get()==dt2.monthOfYear().get() && dt1.dayOfMonth().get()==dt2.dayOfMonth().get()){
					System.out.println("exceptionxxdate");
					return true;					
				}
				return false;
				 
			}
}

