package cn.mldn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCompare {
	/**
	 * 将传递进来的日期与当前的日期进行比较，在进行比较处理的过程之中，是以当前日期为主，不包含时间
	 * 
	 * @param date
	 * @return
	 */
	public static boolean compare(Date date) {
		try {
			return getCurrentDate().getTime() <= date.getTime();
		} catch (Exception e) {
			return false;
		}
	}

	public static Date getCurrentDate() {
		try {
			String currDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()) + " 00:00:00.000";
			Date cDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
					.parse(currDate);
			return cDate;
		} catch (Exception e) {
			return null ;
		}
	}
}
