package cn.mldn.crm.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.IClientServiceBack;
import cn.mldn.crm.vo.Client;
import cn.mldn.crm.vo.Member;
import cn.mldn.util.dbc.DatabaseConnection;

public class ClientServiceBackImpl implements IClientServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public boolean add(Client vo) throws Exception {
		try {
			vo.setReg(new Date()); // 注册日期为当前系统的日期时间
			// 1、需要查询该用户当前是否可用（用户必须是活跃状态才可以使用）；
			Member member = DAOFactory.getIMemberDAOInstance(
					this.dbc.getConnection()).findById(vo.getMember().getMid());
			if (member.getFlag().equals(1)) { // 超级管理员不受到任何限制
				return DAOFactory.getIClientDAOInstance(
						this.dbc.getConnection()).doCreate(vo);
			} else { // 2、需要查询该用户是否具备有指定的权限
				if (member.getLocked().equals(1)) { // 该用户已经被锁定了
					return false;
				} else {
					if (DAOFactory.getIActionDAOInstance(
							this.dbc.getConnection()).findByRoleAndId(
							member.getRole().getRid(), 1) != null) {
						return DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).doCreate(vo);
					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listByMemberAndType(String mid, Integer type,
			String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		try {
			Member member = DAOFactory.getIMemberDAOInstance(
					this.dbc.getConnection()).findById(mid); // 查询指定mid对应的信息
			if (member.getFlag().equals(1)) { // 超级管理员不受到任何限制
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("allClients",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection())
								.findAllSplitByMemberAndType(mid, type, column,
										keyWord, currentPage, lineSize));
				map.put("clientCount",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection())
								.getAllCountByMemberAndType(mid, type, column,
										keyWord));
				return map;
			} else {
				if (member.getLocked().equals(1)) { // 该用户已经被锁定了
					return null;
				} else {
					if (DAOFactory.getIActionDAOInstance(
							this.dbc.getConnection()).findByRoleAndId(
							member.getRole().getRid(), 2) != null) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("allClients",
								DAOFactory.getIClientDAOInstance(
										this.dbc.getConnection())
										.findAllSplitByMemberAndType(mid, type,
												column, keyWord, currentPage,
												lineSize));
						map.put("clientCount",
								DAOFactory.getIClientDAOInstance(
										this.dbc.getConnection())
										.getAllCountByMemberAndType(mid, type,
												column, keyWord));
						return map;
					} else {
						return null;
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Client editPre(String mid, Integer id) throws Exception {
		try {
			Member member = DAOFactory.getIMemberDAOInstance(
					this.dbc.getConnection()).findById(mid); // 查询指定mid对应的信息
			if (member.getLocked().equals(1)) {
				return null;
			}
			if (member.getFlag().equals(1)
					|| DAOFactory.getIActionDAOInstance(
							this.dbc.getConnection()).findByRoleAndId(
							member.getRole().getRid(), 3) != null) {
				return DAOFactory.getIClientDAOInstance(
						this.dbc.getConnection()).findByMemberAndId(mid, id);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean edit(Client vo) throws Exception {
		try {
			Member member = DAOFactory.getIMemberDAOInstance(
					this.dbc.getConnection()).findById(vo.getMember().getMid()); // 查询指定mid对应的信息
			if (member.getLocked().equals(1)) {
				return false;
			}
			if (member.getFlag().equals(1)
					|| DAOFactory.getIActionDAOInstance(
							this.dbc.getConnection()).findByRoleAndId(
							member.getRole().getRid(), 3) != null) {
				return DAOFactory.getIClientDAOInstance(
						this.dbc.getConnection()).doUpdateByMember(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean rmByMember(String mid, Set<Integer> cids) throws Exception {
		try {
			if (cids.size() == 0) {
				return false;
			}
			Member member = DAOFactory.getIMemberDAOInstance(
					this.dbc.getConnection()).findById(mid); // 查询指定mid对应的信息
			if (member.getLocked().equals(1)) {
				return false;
			}
			if (member.getFlag().equals(1)
					|| DAOFactory.getIActionDAOInstance(
							this.dbc.getConnection()).findByRoleAndId(
							member.getRole().getRid(), 4) != null) {
				return DAOFactory.getIClientDAOInstance(
						this.dbc.getConnection()).doRemoveByMember(mid, cids);
			}
			return false ;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
