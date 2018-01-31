package com.appstek.dc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class DateTimeUtil {
	final Logger logger = Logger.getLogger(getClass());
	
	public static Date toDateConversion(String date) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date formattedDob=null;

		try {
			formattedDob = formatter.parse(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formattedDob;
	}
	public static List<Date> getParticularWeekdaysWithSomeDateRange(Date fromDate , Date toDate , String weekDay) {
		List<Date> dates=new ArrayList<Date>();
		int dayValue = 0;
		try {
			 Calendar fcal=Calendar.getInstance();
			 fcal.setTime(fromDate);
			 fcal.add(Calendar.DATE, -1);
			 Calendar tcal=Calendar.getInstance();
			 tcal.setTime(toDate);
			 if(weekDay.equalsIgnoreCase("Monday")){
				 dayValue = Calendar.MONDAY;
			 }else if (weekDay.equalsIgnoreCase("Tuesday")) {
				 dayValue = Calendar.TUESDAY;
			}else if (weekDay.equalsIgnoreCase("Wednesday")) {
				 dayValue = Calendar.WEDNESDAY;
			}else if (weekDay.equalsIgnoreCase("Thursday")) {
				 dayValue = Calendar.THURSDAY;
			}else if (weekDay.equalsIgnoreCase("Friday")) {
				 dayValue = Calendar.FRIDAY;
			}else if (weekDay.equalsIgnoreCase("Saturday")) {
				 dayValue = Calendar.SATURDAY;
			}else if (weekDay.equalsIgnoreCase("Sunday")) {
				 dayValue = Calendar.SUNDAY;
			}else{
				
			}

			 while(!fcal.equals(tcal)){
			     fcal.add(Calendar.DATE, 1);
			     if(fcal.get(Calendar.DAY_OF_WEEK)==dayValue){
			    	 dates.add(fcal.getTime());
			     }
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}

	     return dates;
	}
	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(startdate);
	    calendar.add(Calendar.DATE, -1);

	    while (calendar.getTime().before(enddate)){
	    	calendar.add(Calendar.DATE, 1);
	        dates.add(calendar.getTime());
	    }
	    return dates;
	}
	public static List<Date> getWeekDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(startdate);
	    calendar.add(Calendar.DATE, -1);

	    while (calendar.getTime().before(enddate)){
	    	calendar.add(Calendar.DATE, 1);
	    	if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
	    	}else{
	    		dates.add(calendar.getTime());
	    	}
	    }
	    return dates;
	}
	public static Date getSettingTimeInToDate(Date existDate , Date replaceTimeDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(existDate);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(replaceTimeDate);
		cal.set(Calendar.HOUR, cal1.get(Calendar.HOUR));
		cal.set(Calendar.MINUTE, cal1.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal1.get(Calendar.SECOND));
		return cal.getTime();
	}
	public static Date getFormattedCurrentDateTime(){
		Date currentDate = new Date();
		return currentDate;
	}
	
	public static Date toDateConvertor(String date) {
		String pattern = "dd/MM/yyyy hh:mm";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date formatted=null;

		try {
			formatted = formatter.parse(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formatted;
	}
	
	
	public static Date toDateConvert(String date) {
		String pattern = "YYYY-mm-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date formatted=null;

		try {
			formatted = formatter.parse(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formatted;
	}
}
