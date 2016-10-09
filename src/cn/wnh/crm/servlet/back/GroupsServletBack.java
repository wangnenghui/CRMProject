package cn.mldn.crm.servlet.back;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;

@SuppressWarnings("serial")
@WebServlet("/pages/back/groups/GroupsServletBack/*")
public class GroupsServletBack extends AbstractCRMServlet {
	public String list() {
		try {
			super.request.setAttribute("allGroupses", ServiceFactory
					.getIGroupsServiceBackInstance().list(super.getMid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "groups.list.page";
	}

	public String show() {
		try {
			int gid = Integer.parseInt(super.getParameter("gid"));
			super.request.setAttribute("gup", ServiceFactory
					.getIGroupsServiceBackInstance().show(super.getMid(), gid));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "groups.show.page";
	}

	@Override
	public String getMarkTitle() {
		return null;
	}

	@Override
	public String getUploadDir() {
		return null;
	}

	@Override
	public String getDefaultColumn() {
		return null;
	}

	@Override
	public String getColumntData() {
		return null;
	}

}
