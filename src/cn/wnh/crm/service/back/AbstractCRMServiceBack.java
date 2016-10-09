package cn.mldn.crm.service.back;

import cn.mldn.crm.factory.DAOFactory;
import cn.mldn.crm.vo.Member;
import cn.mldn.util.dbc.DatabaseConnection;

public abstract class AbstractCRMServiceBack {
	protected DatabaseConnection dbc = new DatabaseConnection();
	public boolean isAction(String mid,int actid) throws Exception {
		Member member = DAOFactory.getIMemberDAOInstance(
				this.dbc.getConnection()).findById(mid); // 查询指定mid对应的信息
		if (member.getLocked().equals(1)) {
			return false;
		}
		if (member.getFlag().equals(1)
				|| DAOFactory.getIActionDAOInstance(
						this.dbc.getConnection()).findByRoleAndId(
						member.getRole().getRid(), actid) != null) {
			return true ;
		}
		return false ; 
	}
}
