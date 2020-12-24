package com.aste.lsme.domain;

import java.util.Date;

public class StartScheduleDate {
	
private Date scheduleDate;

private Date actualScheduleDate;


public Date getScheduleDate() {
	return scheduleDate;
}


public void setScheduleDate(Date scheduleDate) {
	this.scheduleDate = scheduleDate;
}


public Date getActualScheduleDate() {
	return actualScheduleDate;
}


public void setActualScheduleDate(Date actualScheduleDate) {
	this.actualScheduleDate = actualScheduleDate;
}


@Override
public String toString() {
	// TODO Auto-generated method stub
	return "startDate="+scheduleDate+" scheduleDate="+actualScheduleDate;
}

}

