package cn.mldn.test;

import java.util.Date;

public class TestDate {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		Thread.sleep(3000);
		long end = System.currentTimeMillis();
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		
		Date sdate = new Date(start) ;
		Date edate = new Date(end) ;
		
		System.out.println(sdate.before(edate));
		
		
		if (end > start) {
			System.out.println("合理的操作。");
		}
	}
}
