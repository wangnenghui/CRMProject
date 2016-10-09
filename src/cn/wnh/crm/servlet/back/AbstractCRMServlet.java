package cn.mldn.crm.servlet.back;

import java.util.Map;

import cn.mldn.crm.vo.Action;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
public abstract class AbstractCRMServlet extends DispatcherServlet {
	/**
	 * 通过session取出全部的权限，而后判断此权限是否存在
	 * @param actid 要判断的权限ID
	 * @return 如果指定权限存在，那么将返回true，如果指定权限不存在，将返回false
	 */
	@SuppressWarnings("unchecked") 
	public boolean isAction(int actid) {
		Map<String,Action> map = (Map<String,Action>) super.getSession().getAttribute("allActions") ;
		return map.containsKey(String.valueOf(actid)) ;
	}
	/**
	 * 通过session取出登录后的mid属性
	 * @return
	 */
	public String getMid()  {
		return super.getSession().getAttribute("mid").toString() ;
	}
	/**
	 * type的类型为int，如果没有传递type，返回的就是-1
	 * @return
	 */
	public int getType() {
		// 判断当前是否有type这个参数，如果没有这个参数就表示查询全部客户信息，设置为-1
		if (super.getParameter("type") == null || "".equals(super.getParameter("type"))) {
			return -1 ;
		} else {
			return Integer.parseInt(super.getParameter("type")) ;
		}
	}
	public int getVisit() {
		// 判断当前是否有type这个参数，如果没有这个参数就表示查询全部客户信息，设置为-1
		if (super.getParameter("visit") == null || "".equals(super.getParameter("visit"))) {
			return -1 ;
		} else {
			return Integer.parseInt(super.getParameter("visit")) ;
		}
	}
}
