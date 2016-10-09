package cn.mldn.crm.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.service.back.AbstractCRMServiceBack;
import cn.mldn.crm.service.back.IDefaultServiceBack;
import cn.mldn.util.DateCompare;

public class DefaultServiceBackImpl extends AbstractCRMServiceBack implements
		IDefaultServiceBack {

	@Override
	public Map<String, Object> stat(String mid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allClients", DAOFactory.getIClientDAOInstance(this.dbc.getConnection()).findAllSplitByMemberAndType(mid, -1, "name", "", 1, 6)) ;
			map.put("allTasks", DAOFactory.getITaskDAOInstance(this.dbc.getConnection()).findAllSplitByMember(mid, -1, -1, "title", "", 1, 7)) ;
			map.put("clientCount", DAOFactory.getIClientDAOInstance(this.dbc.getConnection()).getAllCountByMemberAndType(mid, -1, "name", "")) ;
			map.put("allNewses", DAOFactory.getINewsDAOInstance(this.dbc.getConnection()).findAllSplit("title", "", 1, 9)) ;
			
			map.put("unfinishCount", DAOFactory.getITaskDAOInstance(this.dbc.getConnection()).getAllCountByBeforeUnFinish(mid, DateCompare.getCurrentDate())) ;
			map.put("wfinishCount", DAOFactory.getITaskDAOInstance(this.dbc.getConnection()).getAllCountByAfterFinish(mid, DateCompare.getCurrentDate())) ;
			// map.put("", value) ;
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
