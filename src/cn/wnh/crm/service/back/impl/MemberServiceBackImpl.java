package cn.mldn.crm.service.back.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.IMemberServiceBack;
import cn.mldn.crm.vo.Groups;
import cn.mldn.crm.vo.Logs;
import cn.mldn.crm.vo.Member;
import cn.mldn.util.dbc.DatabaseConnection;

public class MemberServiceBackImpl extends AbstractCRMServiceBack implements
		IMemberServiceBack {
	@Override
	public Map<String, Object> login(Member vo) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = false;
		try {
			// 1、进行用户名和密码的验证，如果登录成功将返回flag、rid两个字段的数据
			if (DAOFactory.getIMemberDAOInstance(this.dbc.getConnection())
					.findLogin(vo)) {
				// 2、更新member表中的最后一次登录日期时间
				if (DAOFactory.getIMemberDAOInstance(this.dbc.getConnection())
						.doUpdateLastdate(vo.getMid())) {
					result.put(
							"unread",
							DAOFactory.getIMemberNewsDAOInstance(
									this.dbc.getConnection())
									.getAllCountUnread(vo.getMid()));
					// 3、在登录日志表中进行一条数据的保存
					Logs logs = new Logs();
					logs.getMember().setMid(vo.getMid());
					if (DAOFactory
							.getILogsDAOInstance(this.dbc.getConnection())
							.doCreate(logs)) {
						// 4、根据用户的角色编号，查询出用户对应的权限组信息
						List<Groups> allGroups = DAOFactory
								.getIGroupsDAOInstance(this.dbc.getConnection())
								.findAllByRole(vo.getRole().getRid());
						Set<Integer> gids = new HashSet<Integer>();
						// 5、根据每一个权限组的编号，查询出对应的所有权限信息
						Iterator<Groups> iter = allGroups.iterator();
						// 6、根据每一个权限组的编号查询出所有的权限信息
						while (iter.hasNext()) {
							Groups gup = iter.next();
							gids.add(gup.getGid());
							gup.setAction(DAOFactory.getIActionDAOInstance(
									this.dbc.getConnection()).findAllByGroups(
									gup.getGid()));
						}
						// 7、将权限组的信息保存在角色里面
						vo.getRole().setGroups(allGroups);
						flag = true;
						result.put(
								"allActions",
								DAOFactory.getIActionDAOInstance(
										this.dbc.getConnection())
										.findAllByGroups(gids));
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		result.put("flag", flag);
		return result;
	}

	@Override
	public boolean editPassword(String mid, String newPass, String oldPass)
			throws Exception {
		try {
			Member vo = new Member();
			vo.setMid(mid);
			vo.setPassword(oldPass);
			if (DAOFactory.getIMemberDAOInstance(this.dbc.getConnection())
					.findLogin(vo)) {
				return DAOFactory.getIMemberDAOInstance(
						this.dbc.getConnection())
						.doUpdatePassword(mid, newPass);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> addPre(String mid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 15)) {
				map.put("allRoles",
						DAOFactory.getIRoleDAOInstance(
								super.dbc.getConnection()).findAll());
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> editPre(String mid, String umid)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 17)) {
				map.put("allRoles",
						DAOFactory.getIRoleDAOInstance(
								super.dbc.getConnection()).findAll());
				map.put("member",
						DAOFactory.getIMemberDAOInstance(
								super.dbc.getConnection()).findById(umid));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean edit(String mid, Member vo) throws Exception {
		try {
			if (super.isAction(mid, 17)) {
				return DAOFactory.getIMemberDAOInstance(
						super.dbc.getConnection()).doUpdate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean add(String mid, Member vo) throws Exception {
		try {
			if (super.isAction(mid, 15)) {
				if (DAOFactory.getIMemberDAOInstance(super.dbc.getConnection())
						.findById(vo.getMid()) == null) { // 此用户不存在
					vo.setLocked(0);// 新增用户不可能锁定
					vo.setFlag(0); // 都是普通用户
					return DAOFactory.getIMemberDAOInstance(
							super.dbc.getConnection()).doCreate(vo);
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(String mid, String column, String keyWord,
			int currentPage, int lineSize) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 16)) {
				map.put("allMembers",
						DAOFactory.getIMemberDAOInstance(
								super.dbc.getConnection()).findAllSplit(column,
								keyWord, currentPage, lineSize));
				map.put("memberCount",
						DAOFactory.getIMemberDAOInstance(
								this.dbc.getConnection()).getAllCount(column,
								keyWord));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean editPasswordByAdmin(String mid, Member vo) throws Exception {
		try {
			if (super.isAction(mid, 17)) {
				return DAOFactory.getIMemberDAOInstance(
						this.dbc.getConnection())
						.doUpdatePassword(vo.getMid(), vo.getPassword());
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean rm(String mid, Set<String> ids) throws Exception {
		try {
			if (ids.size() == 0) {
				return false ;
			}
			if (super.isAction(mid, 18)) {
				return DAOFactory.getIMemberDAOInstance(
						this.dbc.getConnection()).doRemove(ids);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
