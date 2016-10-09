package cn.mldn.crm.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;

@SuppressWarnings("serial")
@WebServlet("/pages/back/DefaultServletBack/*")
public class DefaultServletBack extends AbstractCRMServlet {
	public String show() {
		try {
			Map<String, Object> map = ServiceFactory
					.getIDefaultServiceBackInstance().stat(super.getMid());
			super.request.setAttribute("allNewses", map.get("allNewses"));
			super.request.setAttribute("allClients", map.get("allClients"));
			super.request.setAttribute("allTasks", map.get("allTasks"));
			super.request.setAttribute("clientCount", map.get("clientCount"));
			super.request.setAttribute("unfinishCount",
					map.get("unfinishCount"));
			super.request.setAttribute("wfinishCount", map.get("wfinishCount"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "default.page";
	}

	@Override
	public String getMarkTitle() {
		// TODO Auto-generated method stub
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
