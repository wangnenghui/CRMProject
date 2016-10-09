package cn.mldn.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate2 {
	public static void main(String[] args) throws Exception {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-10") ;
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-10") ;
		System.out.println(date1.getTime() <= date2.getTime());
	}
}
