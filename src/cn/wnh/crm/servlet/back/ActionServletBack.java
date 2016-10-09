package cn.mldn.crm.servlet.back;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;

@SuppressWarnings("serial")
@WebServlet("/pages/back/action/ActionServletBack/*")
public class ActionServletBack extends AbstractCRMServlet {
	public String list() {
		try {
			super.request.setAttribute("allActions", ServiceFactory
					.getIActionServiceBackInstance().list(super.getMid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "action.list.page";
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
