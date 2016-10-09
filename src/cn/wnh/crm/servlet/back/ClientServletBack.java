package cn.mldn.crm.servlet.back;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.Client;
import cn.mldn.util.BasepathUtil;
import cn.mldn.util.SplitUtil;

@SuppressWarnings("serial")
@WebServlet("/pages/back/client/ClientServletBack/*")
public class ClientServletBack extends AbstractCRMServlet {
	private Client client = new Client();
	public String rm() {
		String cid = super.getParameter("ids") ;
		String result [] = cid.split("\\|") ;
		Set<Integer> set = new HashSet<Integer>() ;
		for (int x = 0 ; x < result.length ; x ++) {
			set.add(Integer.parseInt(result[x])) ;
		}
		try {
			if (ServiceFactory.getIClientServiceBackInstance().rmByMember(super.getMid(), set)) {
				super.setMsgAndUrl("vo.rm.success", "client.list.servlet");
			} else {
				super.setMsgAndUrl("vo.rm.failure", "client.list.servlet");
			}
		} catch (Exception e) {
			super.setMsgAndUrl("vo.rm.failure", "client.list.servlet");
		}
		return "forward.page" ;
	}
	public String editPre() {
		try {
			super.request.setAttribute(
					"client",
					ServiceFactory.getIClientServiceBackInstance().editPre(
							super.getMid(), this.client.getCid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "client.edit.page" ;
	}
	public String edit() {
		// 在进行数据更新的时候一定要将当前的用户的id传给Client类对象
		this.client.getMember().setMid(super.getMid());
		try {
			if (ServiceFactory.getIClientServiceBackInstance().edit(this.client)) {
				super.setMsgAndUrl("vo.edit.success", "client.list.servlet");
			} else {
				super.setMsgAndUrl("vo.edit.failure", "client.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgAndUrl("vo.edit.failure", "client.list.servlet");
		}
		return "forward.page" ;
	}
	
	public String listSplit() {
		if (super.isAction(2)) {
			// 进行分页处理的时候已经把需要传递的内容传递过去
			SplitUtil su = super.handleSplitParam();
			try {
				Map<String, Object> map = ServiceFactory
						.getIClientServiceBackInstance().listByMemberAndType(
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
					+ super.getPage("client.list.servlet"));
			return "client.list.page";
		} else {
			return "errors.page";
		}
	}

	public String addPre() {
		if (super.isAction(1)) { // 如果是1号权限
			return "client.add.page";
		} else {
			return "error.page";
		}
	}

	public String add() {
		// 需要通过session取得mid数据
		if (super.isAction(1)) {
			String mid = super.getSession().getAttribute("mid").toString();
			this.client.getMember().setMid(mid);
			try {
				if (ServiceFactory.getIClientServiceBackInstance().add(
						this.client)) {
					super.setMsgAndUrl("vo.add.success", "client.add.servlet");
				} else {
					super.setMsgAndUrl("vo.add.failure", "client.add.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "client.add.servlet");
			}
			return "forward.page";
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
