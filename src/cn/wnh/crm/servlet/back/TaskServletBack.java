package cn.mldn.crm.servlet.back;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Task;
import cn.mldn.util.BasepathUtil;
import cn.mldn.util.SplitUtil;

@SuppressWarnings("serial")
@WebServlet("/pages/back/task/TaskServletBack/*")
public class TaskServletBack extends AbstractCRMServlet {

	private Task task = new Task();

	public String over() {
		if (super.isAction(9)) {
			try {
				int tid = Integer.parseInt(super.getParameter("tid"));
				if (ServiceFactory.getITaskServiceBackInstance().editOver(super.getMid(), tid)) {
					super.setMsgAndUrl("task.over.success", "task.list.servlet");
				} else {
					super.setMsgAndUrl("task.over.failure", "task.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("task.over.failure", "task.list.servlet");
			}
		} else {
			return "error.page";
		}
		return "forward.page";
	}
	
	public String finish() {
		if (super.isAction(8)) {
			try {
				int tid = Integer.parseInt(super.getParameter("tid"));
				if (ServiceFactory.getITaskServiceBackInstance().editFinish(super.getMid(), tid)) {
					super.setMsgAndUrl("task.finish.success", "task.list.servlet");
				} else {
					super.setMsgAndUrl("task.finish.failure", "task.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("task.finish.failure", "task.list.servlet");
			}
			return "forward.page" ;
		} else {
			return "error.page";
		}
	}

	public String rm() {
		if (super.isAction(10)) {
			try {
				String ids = super.getParameter("ids") ;
				Set<Integer> tids = new HashSet<Integer>() ;
				String result [] = ids.split("\\|") ;
				for (int x = 0; x < result.length; x++) {
					tids.add(Integer.parseInt(result[x])) ;
				}
				
				if (ServiceFactory.getITaskServiceBackInstance().rmByMember(super.getMid(), tids)) {
					super.setMsgAndUrl("vo.rm.success", "task.list.servlet");
				} else {
					super.setMsgAndUrl("vo.rm.failure", "task.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.rm.failure", "task.list.servlet");
			}
		} else {
			return "error.page";
		}
		return "forward.page";
	}

	public String editPre() {
		if (super.isAction(7)) {
			try {
				int tid = Integer.parseInt(super.getParameter("tid"));
				request.setAttribute(
						"task",
						ServiceFactory.getITaskServiceBackInstance().editPre(
								super.getMid(), tid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "task.edit.page";
		} else {
			return "error.page";
		}
	}

	public String edit() {
		if (super.isAction(7)) {
			this.task.getMember().setMid(super.getMid());
			try {
				if (ServiceFactory.getITaskServiceBackInstance()
						.edit(this.task)) {
					super.setMsgAndUrl("vo.edit.success", "task.list.servlet");
				} else {
					super.setMsgAndUrl("vo.edit.failure", "task.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "task.list.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	public String listSplit() {
		if (super.isAction(6)) {
			try {
				// 进行分页处理的时候已经把需要传递的内容传递过去
				SplitUtil su = super.handleSplitParam();
				Map<String, Object> map = ServiceFactory
						.getITaskServiceBackInstance().listByMember(
								super.getMid(), super.getVisit(),
								super.getType(), "name", su.getKeyWord(),
								su.getCurrentPage(), su.getLineSize());
				super.request.setAttribute("allTasks", map.get("allTasks"));
				super.request
						.setAttribute("allRecorders", map.get("taskCount"));
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
						+ super.getPage("task.list.servlet"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "task.list.page";
		} else {
			return "error.page";
		}
	}

	public String show() {
		if (super.isAction(31)) {
			try {
				int cid = Integer.parseInt(super.getParameter("cid"));
				int tid = Integer.parseInt(super.getParameter("tid"));
				request.setAttribute(
						"task",
						ServiceFactory.getITaskServiceBackInstance().show(
								super.getMid(), cid, tid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "task.show.page";
		} else {
			return "error.page";
		}
	}

	public String listByClient() {
		if (super.isAction(30)) {
			try {
				int cid = Integer.parseInt(super.getParameter("cid"));
				Map<String, Object> map = ServiceFactory
						.getITaskServiceBackInstance().listByMemberAndClient(
								super.getMid(), cid);
				request.setAttribute("allTasks", map.get("allTasks"));
				request.setAttribute("client", map.get("client"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "task.client.list.page";
		} else {
			return "error.page";
		}
	}

	public String addPre() {
		if (super.isAction(5)) {
			return "task.add.page";
		} else {
			return "error.page";
		}
	}

	public String add() {
		if (super.isAction(5)) {
			this.task.getMember().setMid(super.getMid());
			try {
				if (ServiceFactory.getITaskServiceBackInstance().add(this.task)) {
					super.setMsgAndUrl("vo.add.success", "task.add.servlet");
				} else {
					super.setMsgAndUrl("vo.add.failure", "task.add.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "task.add.servlet");
			}
			return "forward.page";
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
