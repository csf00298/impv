package com.demo.tlist;

import java.io.Serializable;
import java.util.Calendar;


@SuppressWarnings("all")
public class TestCalendar implements Serializable{
	/** 
	* @Fields serialVersionUID 
	*/ 
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2016,7,16,16,31);
		long timeInMillis = cal.getTimeInMillis();
		timeInMillis += 1000*60*60;
		cal.setTimeInMillis(timeInMillis);
		cal.add(cal.DATE, 1);
		System.out.println(cal.getTime());
//		Color.black;
		TestCalendar tc = new TestCalendar();//内部类
		TestCalendar.Inner inner = tc.new Inner();
		System.out.println(inner.a);
	}
	
	class Inner{
		int a =10;
	}
}
