package cn.mldn.crm.factory;

import java.sql.Connection;

import cn.mldn.crm.dao.IActionDAO;
import cn.mldn.crm.dao.IClientDAO;
import cn.mldn.crm.dao.IGroupsDAO;
import cn.mldn.crm.dao.ILogsDAO;
import cn.mldn.crm.dao.IMemberDAO;
import cn.mldn.crm.dao.IMemberNewsDAO;
import cn.mldn.crm.dao.INewsDAO;
import cn.mldn.crm.dao.IRoleDAO;
import cn.mldn.crm.dao.ITaskDAO;
import cn.mldn.crm.dao.impl.ActionDAOImpl;
import cn.mldn.crm.dao.impl.ClientDAOImpl;
import cn.mldn.crm.dao.impl.GroupsDAOImpl;
import cn.mldn.crm.dao.impl.LogsDAOImpl;
import cn.mldn.crm.dao.impl.MemberDAOImpl;
import cn.mldn.crm.dao.impl.MemberNewsDAOImpl;
import cn.mldn.crm.dao.impl.NewsDAOImpl;
import cn.mldn.crm.dao.impl.RoleDAOImpl;
import cn.mldn.crm.dao.impl.TaskDAOImpl;

public class DAOFactory {
	public static IMemberDAO getIMemberDAOInstance(Connection conn) {
		return new MemberDAOImpl(conn) ;
	}
	public static ILogsDAO getILogsDAOInstance(Connection conn) {
		return new LogsDAOImpl(conn) ;
	}
	public static IGroupsDAO getIGroupsDAOInstance(Connection conn) {
		return new GroupsDAOImpl(conn) ;
	}
	public static IActionDAO getIActionDAOInstance(Connection conn) {
		return new ActionDAOImpl(conn) ;
	}
	public static IClientDAO getIClientDAOInstance(Connection conn) {
		return new ClientDAOImpl(conn) ;
	}
	public static ITaskDAO getITaskDAOInstance(Connection conn) {
		return new TaskDAOImpl(conn) ;
	}
	public static INewsDAO getINewsDAOInstance(Connection conn) {
		return new NewsDAOImpl(conn) ;
	}
	public static IMemberNewsDAO getIMemberNewsDAOInstance(Connection conn) {
		return new MemberNewsDAOImpl(conn) ;
	}
	public static IRoleDAO getIRoleDAOInstance(Connection conn) {
		return new RoleDAOImpl(conn) ;
	}
}
