package cn.mldn.util;

public class ValidateUtil {
	/**
	 * 验证是否为空
	 * @param str 要验证的内容
	 * @return 如果为空返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) ;
	}
	/**
	 * 验证是否是整数
	 * @param str
	 * @return 如果是整数则返回true，否则返回false
	 */
	public static boolean isInteger(String str) {
		if (!isEmpty(str)) {	// 现在内容不是空
			return str.matches("\\d+") ;
		}
		return false ;
	}
	/**
	 * 验证是否是小数
	 * @param str
	 * @return 如果是小数返回true，否则返回false
	 */
	public static boolean isDouble(String str) {
		if (!isEmpty(str)) {	// 现在内容不是空
			return str.matches("\\d+(\\.\\d+)?") ;
		}
		return false ;
	}
	/**
	 * 验证是否是日期或日期时间
	 * @param str
	 * @return 如果是日期或日期时间返回true，否则返回false 
	 */
	public static boolean isDate(String str) {
		if (!isEmpty(str)) {	// 现在内容不是空
			if (str.matches("\\d{4}-\\d{2}-\\d{2}")) {
				return true ;
			} else {
				return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
			}
		} 
		return false ;
	}
}
