package cn.mldn.crm.factory;

import cn.mldn.crm.service.back.IActionServiceBack;
import cn.mldn.crm.service.back.IClientServiceBack;
import cn.mldn.crm.service.back.IDefaultServiceBack;
import cn.mldn.crm.service.back.IGroupsServiceBack;
import cn.mldn.crm.service.back.IManagerClientServiceBack;
import cn.mldn.crm.service.back.IMemberServiceBack;
import cn.mldn.crm.service.back.INewsServiceBack;
import cn.mldn.crm.service.back.IRoleServiceBack;
import cn.mldn.crm.service.back.ITaskServiceBack;
import cn.mldn.crm.service.back.impl.ActionServiceBackImpl;
import cn.mldn.crm.service.back.impl.ClientServiceBackImpl;
import cn.mldn.crm.service.back.impl.DefaultServiceBackImpl;
import cn.mldn.crm.service.back.impl.GroupsServiceBackImpl;
import cn.mldn.crm.service.back.impl.ManagerClientServiceBackImpl;
import cn.mldn.crm.service.back.impl.MemberServiceBackImpl;
import cn.mldn.crm.service.back.impl.NewsServiceBackImpl;
import cn.mldn.crm.service.back.impl.RoleServiceBackImpl;
import cn.mldn.crm.service.back.impl.TaskServiceBackImpl;

public class ServiceFactory {
	public static IMemberServiceBack getIMemberServiceBackInstance() {
		return new MemberServiceBackImpl() ; 
	}
	public static IClientServiceBack getIClientServiceBackInstance() {
		return new ClientServiceBackImpl() ;
	}
	public static IManagerClientServiceBack getIManagerClientServiceBackInstance() {
		return new ManagerClientServiceBackImpl() ;
	} 
	public static ITaskServiceBack getITaskServiceBackInstance() {
		return new TaskServiceBackImpl() ;
	}
	public static IDefaultServiceBack getIDefaultServiceBackInstance() {
		return new DefaultServiceBackImpl() ;
	}
	public static INewsServiceBack getINewsServiceBackInstance() {
		return new NewsServiceBackImpl() ;
	}
	public static IActionServiceBack getIActionServiceBackInstance() {
		return new ActionServiceBackImpl() ;
	}
	public static IGroupsServiceBack getIGroupsServiceBackInstance() {
		return new GroupsServiceBackImpl() ;
	}
	public static IRoleServiceBack getIRoleServiceBackInstance() {
		return new RoleServiceBackImpl() ;
	}
}
