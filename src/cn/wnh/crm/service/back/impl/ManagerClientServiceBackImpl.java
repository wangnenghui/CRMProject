package cn.mldn.crm.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.IManagerClientServiceBack;
import cn.mldn.crm.vo.Client;

public class ManagerClientServiceBackImpl extends AbstractCRMServiceBack
		implements IManagerClientServiceBack {

	@Override
	public Map<String, Object> listByType(String mid, int type, String column,
			String keyWord, int currentPage, int lineSize) throws Exception {
		try {
			if (super.isAction(mid, 26)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("allClients",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).findAllSplitByType(
								type, column, keyWord, currentPage, lineSize));
				map.put("clientCount",
						DAOFactory.getIClientDAOInstance(
								this.dbc.getConnection()).getAllCountByType(
								type, column, keyWord));
				return map;
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Client show(String mid, int cid) throws Exception {
		try {
			if (super.isAction(mid, 27)) {
				return DAOFactory.getIClientDAOInstance(
						super.dbc.getConnection()).findById(cid);
			}
			return null;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
