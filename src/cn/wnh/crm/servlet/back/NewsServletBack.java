package cn.mldn.crm.servlet.back;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.crm.factory.ServiceFactory;
import cn.mldn.crm.vo.News;
import cn.mldn.util.BasepathUtil;
import cn.mldn.util.SplitUtil;

@SuppressWarnings("serial")
@WebServlet("/pages/back/news/NewsServletBack/*")
public class NewsServletBack extends AbstractCRMServlet {
	private News news = new News();

	public String editPre() {
		if (super.isAction(13)) {
			try {
				super.request.setAttribute(
						"news",
						ServiceFactory.getINewsServiceBackInstance().editPre(
								super.getMid(), this.news.getNid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "news.edit.page";
		} else {
			return "error.page";
		}
	}

	public String rm() {
		if (super.isAction(14)) {
			String ids = super.getParameter("ids");
			Set<Integer> set = new HashSet<Integer>();
			String result[] = ids.split("\\|");
			for (int x = 0; x < result.length; x++) {
				set.add(Integer.parseInt(result[x]));
			}
			try {
				if (ServiceFactory.getINewsServiceBackInstance().rm(
						super.getMid(), set)) {
					super.setMsgAndUrl("vo.rm.success", "news.list.servlet");
				} else {
					super.setMsgAndUrl("vo.rm.failure", "news.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.rm.failure", "news.list.servlet");
			}
			return "forward.page";
		} else {
			return "error.page";
		}
	}

	public String edit() {
		if (super.isAction(13)) {
			this.news.getMember().setMid(super.getMid());
			try {
				if (ServiceFactory.getINewsServiceBackInstance()
						.edit(this.news)) {
					super.setMsgAndUrl("vo.edit.success", "news.list.servlet");
				} else {
					super.setMsgAndUrl("vo.edit.failure", "news.list.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.edit.failure", "news.list.servlet");
			}
		} else {
			return "error.page";
		}
		return "forward.page";
	}

	public String addPre() {
		if (super.isAction(12)) {
			return "news.add.page";
		} else {
			return "error.page";
		}
	}

	public String list() {
		if (super.isAction(11)) {
			SplitUtil su = super.handleSplitParam();
			try {
				Map<String, Object> map = ServiceFactory
						.getINewsServiceBackInstance().listSplit(
								super.getMid(), su.getColumn(),
								su.getKeyWord(), su.getCurrentPage(),
								su.getLineSize());
				super.getSession().setAttribute("unread", 0);
				super.request.setAttribute("allNewses", map.get("allNewses"));
				super.request.setAttribute("unreadMap", map.get("unreadMap"));
				super.request
						.setAttribute("allRecorders", map.get("newsCount"));
				request.setAttribute("url", BasepathUtil.getPath(super.request)
						+ super.getPage("news.list.servlet"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "news.list.page";
		} else {
			return "error.page";
		}
	}

	public String show() {
		if (super.isAction(33)) {
			try {
				super.request.setAttribute(
						"news",
						ServiceFactory.getINewsServiceBackInstance().show(
								super.getMid(), this.news.getNid()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "news.show.page";
		} else {
			return "error.page";
		}
	}

	public String add() {
		if (super.isAction(12)) {
			this.news.getMember().setMid(super.getMid());
			try {
				if (ServiceFactory.getINewsServiceBackInstance().add(this.news)) {
					super.setMsgAndUrl("vo.add.success", "news.add.servlet");
				} else {
					super.setMsgAndUrl("vo.add.failure", "news.add.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
				super.setMsgAndUrl("vo.add.failure", "news.add.servlet");
			}
		} else {
			return "error.page";
		}
		return "forward.page";
	}

	public News getNews() {
		return news;
	}

	@Override
	public String getMarkTitle() {
		return "公告信息";
	}

	@Override
	public String getUploadDir() {
		return null;
	}

	@Override
	public String getDefaultColumn() {
		return "title";
	}

	@Override
	public String getColumntData() {
		return "公告标题:title|发布者:mid";
	}

}
