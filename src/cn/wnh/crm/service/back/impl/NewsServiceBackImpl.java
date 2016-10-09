package cn.mldn.crm.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.INewsServiceBack;
import cn.mldn.crm.vo.MemberNews;
import cn.mldn.crm.vo.News;

public class NewsServiceBackImpl extends AbstractCRMServiceBack implements
		INewsServiceBack {

	@Override
	public boolean add(News vo) throws Exception {
		try {
			if (super.isAction(vo.getMember().getMid(), 12)) {
				vo.setPubdate(new Date()); // 发布日期设置为当前所在日期时间
				return DAOFactory
						.getINewsDAOInstance(super.dbc.getConnection())
						.doCreate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listSplit(String mid, String column,
			String keyWord, int currentPage, int lineSize) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 11)) {
				List<News> all = DAOFactory.getINewsDAOInstance(
						this.dbc.getConnection()).findAllSplit(column, keyWord,
						currentPage, lineSize);
				if (all.size() > 0) {
					Set<Integer> set = new HashSet<Integer>();
					Iterator<News> iter = all.iterator();
					while (iter.hasNext()) {
						set.add(iter.next().getNid());
					}
					map.put("unreadMap",
							DAOFactory.getIMemberNewsDAOInstance(
									super.dbc.getConnection()).findAllNotId(
									mid, set)); 
				}
				map.put("allNewses", all);
				map.put("newsCount",
						DAOFactory
								.getINewsDAOInstance(this.dbc.getConnection())
								.getAllCount(column, keyWord));

			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public News show(String mid, int nid) throws Exception {
		try {
			if (super.isAction(mid, 33)) {
				if (DAOFactory.getIMemberNewsDAOInstance(
						super.dbc.getConnection()).getAllCountByMemberAndNews(
						mid, nid) == 0) { // 没有读取过
					MemberNews vo = new MemberNews();
					vo.getMember().setMid(mid);
					vo.getNews().setNid(nid);
					vo.setRdate(new Date());
					DAOFactory.getIMemberNewsDAOInstance(
							super.dbc.getConnection()).doCreate(vo);
				}

				return DAOFactory
						.getINewsDAOInstance(super.dbc.getConnection())
						.findById(nid);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public News editPre(String mid, int nid) throws Exception {
		try {
			if (super.isAction(mid, 13)) {
				return DAOFactory
						.getINewsDAOInstance(super.dbc.getConnection())
						.findById(nid);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean edit(News vo) throws Exception {
		try {
			if (super.isAction(vo.getMember().getMid(), 13)) {
				return DAOFactory
						.getINewsDAOInstance(super.dbc.getConnection())
						.doUpdate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean rm(String mid, Set<Integer> ids) throws Exception {
		try {
			if (ids.size() == 0) {
				return false;
			}
			if (super.isAction(mid, 14)) {
				return DAOFactory
						.getINewsDAOInstance(super.dbc.getConnection())
						.doRemove(ids);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
