package cn.mldn.crm.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Task;
import cn.mldn.util.BasepathUtil;
import cn.mldn.util.SplitUtil;

@SuppressWarnings("serial")
@WebServlet("/pages/back/mtask/ManagerTaskServletBack/*")
public class ManagerTaskServletBack extends AbstractCRMServlet {

	private Task task = new Task();

	public String listSplit() {
		if (super.isAction(28)) {
			try {
				// 进行分页处理的时候已经把需要传递的内容传递过去
				SplitUtil su = super.handleSplitParam();
				Map<String, Object> map = ServiceFactory
						.getITaskServiceBackInstance().list(
								super.getMid(), super.getVisit(), super.getType(), "name",
								su.getKeyWord(), su.getCurrentPage(),
								su.getLineSize());
				super.request.setAttribute("allTasks", map.get("allTasks"));
				super.request.setAttribute("allRecorders", map.get("taskCount"));
				super.request.setAttribute("allClients", map.get("allClients"));
				super.request.setAttribute("type", super.getType());
				super.request.setAttribute("visit", super.getVisit());
				super.request.setAttribute("paramName", "type");
				super.request.setAttribute("paramValue",
						String.valueOf(super.getType()));
				super.request.setAttribute("paramNameB", "visit");
				super.request.setAttribute("paramValueB",
						String.valueOf(super.getVisit()));
				request.setAttribute("url", BasepathUtil.getPath(super.request)
						+ super.getPage("mtask.list.servlet"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "mtask.list.page";
		} else {
			return "error.page";
		}
	}

	public String show() {
		if (super.isAction(32)) {
			try {
				int tid = Integer.parseInt(super.getParameter("tid"));
				request.setAttribute(
						"task",
						ServiceFactory.getITaskServiceBackInstance().show(
								super.getMid(), tid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "mtask.show.page";
		} else {
			return "error.page";
		}
	}

	public String listByClient() {
		if (super.isAction(29)) {
			try {
				int cid = Integer.parseInt(super.getParameter("cid"));
				Map<String, Object> map = ServiceFactory
						.getITaskServiceBackInstance().listByClient(
								super.getMid(), cid);
				request.setAttribute("allTasks", map.get("allTasks"));
				request.setAttribute("client", map.get("client"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "mtask.client.list.page";
		} else {
			return "error.page";
		}
	}
	
	public Task getTask() {
		return task;
	}

	@Override
	public String getMarkTitle() {
		return "任务";
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
