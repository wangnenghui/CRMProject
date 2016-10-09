package cn.mldn.crm.service.back;

import java.util.List;

import cn.mldn.crm.vo.Groups;

public interface IGroupsServiceBack {
	/**
	 * 列出全部的权限组信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public List<Groups> list(String mid) throws Exception ;
	/**
	 * 根据权限组编号查询权限组的详情以及对应的权限信息，包含如下操作：<br>
	 * <li>调用IGroupsDAO.findById()方法查询权限组的详细信息</li>
	 * <li>根据权限组编号，调用IActionDAO.findAllByGroups()方法，查询此组中的所有权限信息</li>
	 * @param mid
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public Groups show(String mid,int gid) throws Exception ;
}
