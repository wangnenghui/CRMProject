package cn.mldn.crm.service.back.impl;

import java.util.List;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.IGroupsServiceBack;
import cn.mldn.crm.vo.Groups;

public class GroupsServiceBackImpl extends AbstractCRMServiceBack implements
		IGroupsServiceBack {

	@Override
	public List<Groups> list(String mid) throws Exception {
		try {
			if (super.isAction(mid, 23)) {
				return DAOFactory.getIGroupsDAOInstance(
						this.dbc.getConnection()).findAll();
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Groups show(String mid, int gid) throws Exception {
		try {
			if (super.isAction(mid, 24)) {
				Groups gup = DAOFactory.getIGroupsDAOInstance(
						super.dbc.getConnection()).findById(gid);
				if (gup != null) { // 此权限组存在
					gup.setAction(DAOFactory.getIActionDAOInstance(
							super.dbc.getConnection()).findAllByGroups(gid));
				}
				return gup;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

}
