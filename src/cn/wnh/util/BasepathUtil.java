package cn.mldn.util;

import javax.servlet.http.HttpServletRequest;

public class BasepathUtil {
	public static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path ;
		return basePath ;
	}
}
