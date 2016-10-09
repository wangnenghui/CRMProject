package cn.mldn.crm.service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.IRoleServiceBack;
import cn.mldn.crm.vo.Role;

public class RoleServiceBackImpl extends AbstractCRMServiceBack implements
		IRoleServiceBack {

	@Override
	public boolean add(String mid, Role vo) throws Exception {
		try {
			// 角色必须要有对应的权限组信息
			if (vo.getGroups() == null || vo.getGroups().size() == 0) {
				return false;
			}
			if (super.isAction(mid, 19)) {
				// 1、向role表中保存新的数据，但是此时的vo里面没有rid内容
				if (DAOFactory.getIRoleDAOInstance(super.dbc.getConnection())
						.doCreate(vo)) {
					// 2、为了可以向role_groups表中保存数据，所以需要取出之前保存的role数据的rid
					vo.setRid(DAOFactory.getIRoleDAOInstance(
							super.dbc.getConnection()).findLastId());
					return DAOFactory.getIRoleDAOInstance(
							super.dbc.getConnection())
							.doCreateRoleAndGropus(vo);
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

	@Override
	public Map<String, Object> addPre(String mid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 19)) {
				map.put("allGroupses",
						DAOFactory.getIGroupsDAOInstance(
								super.dbc.getConnection()).findAllByType());
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

	@Override
	public List<Role> list(String mid) throws Exception {
		try {
			if (super.isAction(mid, 20)) {
				return DAOFactory
						.getIRoleDAOInstance(super.dbc.getConnection())
						.findAll();
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

	@Override
	public Role show(String mid, int rid) throws Exception {
		try {
			if (super.isAction(mid, 34)) {
				Role vo = DAOFactory.getIRoleDAOInstance(
						super.dbc.getConnection()).findById(rid);
				if (vo != null) {
					vo.setGroups(DAOFactory.getIGroupsDAOInstance(
							super.dbc.getConnection()).findAllByRole(rid));
				}
				return vo;
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

	@Override
	public Map<String, Object> editPre(String mid, int rid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (super.isAction(mid, 21)) {
				map.put("role",
						DAOFactory.getIRoleDAOInstance(
								super.dbc.getConnection()).findById(rid));
				map.put("allGroupses",
						DAOFactory.getIGroupsDAOInstance(
								super.dbc.getConnection()).findAllByType());
				map.put("roleGroups",
						DAOFactory.getIRoleDAOInstance(
								super.dbc.getConnection())
								.findGroupsByRole(rid));
			}
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

	@Override
	public boolean edit(String mid, Role vo) throws Exception {
		try {
			if (vo.getGroups() == null || vo.getGroups().size() == 0) {
				return false;
			}
			if (super.isAction(mid, 21)) {
				// 1、要求先将角色进行更新
				if (DAOFactory.getIRoleDAOInstance(super.dbc.getConnection())
						.doUpdate(vo)) {
					// 2、删除掉原本的角色与权限组的对应关系
					if (DAOFactory.getIRoleDAOInstance(
							super.dbc.getConnection()).doRemoveRoleAndGroups(
							vo.getRid())) {
						return DAOFactory.getIRoleDAOInstance(
								super.dbc.getConnection())
								.doCreateRoleAndGropus(vo);
					}
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

	@Override
	public boolean rm(String mid, Set<Integer> rid) throws Exception {
		try {
			if (rid.size() == 0) {
				return false;
			}
			if (super.isAction(mid, 22)) {
				return DAOFactory
						.getIRoleDAOInstance(super.dbc.getConnection())
						.doRemove(rid);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

}
