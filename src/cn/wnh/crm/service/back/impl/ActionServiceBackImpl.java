package cn.mldn.crm.service.back.impl;

import java.util.List;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.IActionServiceBack;
import cn.mldn.crm.vo.Action;

public class ActionServiceBackImpl extends AbstractCRMServiceBack implements
		IActionServiceBack {

	@Override
	public List<Action> list(String mid) throws Exception {
		try {
			if (super.isAction(mid, 25)) {
				return DAOFactory.getIActionDAOInstance(
						super.dbc.getConnection()).findAll();
			}
			return null ;
		} catch (Exception e) {
			throw e;
		} finally {
			super.dbc.close();
		}
	}

}
