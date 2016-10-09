package cn.mldn.crm.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Client;
import cn.mldn.util.BasepathUtil;
import cn.mldn.util.SplitUtil;

@SuppressWarnings("serial")
@WebServlet("/pages/back/mclient/ManagerClientServletBack/*")
public class ManagerClientServletBack extends AbstractCRMServlet {
	private Client client = new Client();

	public String show() {
		try {
			super.request.setAttribute(
					"client",
					ServiceFactory.getIManagerClientServiceBackInstance().show(
							super.getMid(), this.client.getCid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mclient.show.page";
	}

	public String listSplit() {
		if (super.isAction(26)) {
			// 进行分页处理的时候已经把需要传递的内容传递过去
			SplitUtil su = super.handleSplitParam();
			try {
				Map<String, Object> map = ServiceFactory
						.getIManagerClientServiceBackInstance().listByType(
								super.getMid(), super.getType(),
								su.getColumn(), su.getKeyWord(),
								su.getCurrentPage(), su.getLineSize());
				super.request.setAttribute("type", super.getType());
				super.request.setAttribute("allClients", map.get("allClients"));
				super.request.setAttribute("allRecorders",
						map.get("clientCount"));
				super.request.setAttribute("paramName", "type");
				super.request.setAttribute("paramValue",
						String.valueOf(super.getType()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("url", BasepathUtil.getPath(super.request)
					+ super.getPage("mclient.list.servlet"));
			return "mclient.list.page";
		} else {
			return "error.page";
		}
	}

	public Client getClient() {
		return client;
	}

	@Override
	public String getMarkTitle() {
		return "客户";
	}

	@Override
	public String getUploadDir() {
		return null;
	}

	@Override
	public String getDefaultColumn() {
		return "name";
	}

	@Override
	public String getColumntData() {
		return "顾客姓名:name|联系邮箱:email|联系电话:tel|联系QQ:qq";
	}

}
