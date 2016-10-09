package cn.mldn.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/pages/back/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 需要通过HttpServletRequest接口取得Session对象
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession ses = request.getSession();
		if (ses.getAttribute("mid") != null) { // 表示登录成功
			chain.doFilter(req, resp);
		} else { // 应该将请求交给forward.jsp进行错误信息提示以及跳转
			req.setAttribute("msg", "您还未登录，请先登录！");
			req.setAttribute("url", "/login.jsp");
			req.getRequestDispatcher("/pages/forward.jsp").forward(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
