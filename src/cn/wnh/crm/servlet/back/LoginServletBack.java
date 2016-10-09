package cn.mldn.crm.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Member;
import cn.mldn.util.MD5Code;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/LoginServletBack/*")
public class LoginServletBack extends DispatcherServlet {
	private Member member = new Member();

	public String login() {
		// 把输入的密码加密成功后设置给password属性
		this.member.setPassword(new MD5Code().getMD5ofStr(this.member.getPassword()));
		try {
			Map<String,Object> map = ServiceFactory.getIMemberServiceBackInstance().login(this.member) ;
			boolean loginFlag = (Boolean) map.get("flag") ;
			if (loginFlag) {
				super.getSession().setAttribute("mid", this.member.getMid());
				super.getSession().setAttribute("flag", this.member.getFlag());
				super.getSession().setAttribute("groups", this.member.getRole().getGroups());
				super.getSession().setAttribute("allActions", map.get("allActions"));
				super.getSession().setAttribute("unread", map.get("unread")); 
				// 处理权限问题；
				super.setMsgAndUrl("member.login.success", "index.page");
			} else {
				super.setMsgAndUrl("member.login.failure", "member.login.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page" ;
	}
	public String logout() {
		super.getSession().invalidate();
		super.setMsgAndUrl("member.logout.success", "member.login.page");
		return "forward.page" ;
	} 
	
	public Member getMember() {
		return member;
	}

	@Override
	public String getMarkTitle() {
		return null;
	}

	@Override
	public String getUploadDir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumntData() {
		// TODO Auto-generated method stub
		return null;
	}

}
