package cn.mldn.crm.service.back;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.Role;

public interface IRoleServiceBack {
	/**
	 * 角色增加处理，本操作要执行如下的步骤：<br>
	 * <li>要使用IRoleDAO.doCreate()保存角色信息</li>
	 * <li>利用IRoleDAO.findLastId()方法取得当前保存的角色的rid数据，并且将其设置到VO类中</li>
	 * <li>利用IRoleDAO.doCreateRoleAndGropus()方法进行role_groups关系表的保存处理</li>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(String mid,Role vo) throws Exception ;
	/**
	 * 角色创建前的准备操作
	 * @param mid 权限验证使用
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> addPre(String mid) throws Exception ;
	/**
	 * 实现所有角色的列表显示
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public List<Role> list(String mid) throws Exception ;
	/**
	 * 查看角色详情信息，将调用IRoleDAO.findById()查询角色信息
	 * 以及调用IGroupsDAO.findAllByRole()方法查看角色对应的所有权限组信息
	 * @param mid
	 * @param rid
	 * @return
	 * @throws Exception
	 */
	public Role show(String mid,int rid) throws Exception ;
	/**
	 * 角色更新前的查询处理操作
	 * @param mid 权限验证使用
	 * @param rid 角色编号
	 * @return 包含如下内容：<br>
	 * <li>key = role、value = IRoleDAO.findById()，保存的类型是Role</li>
	 * <li>key = allGroupses、value = IGroupsDAO.findByType()，保存的类型是Map<String, List<Groups>></li>
	 * <li>key = roleGroups、value = IRoleDAO.findGroupsByRole，保存的类型是Map<Integer,Boolean></li>
	 * @throws Exception
	 */
	public Map<String,Object> editPre(String mid,int rid) throws Exception ;
	/**
	 * 执行角色的更新处理操作，本功能将执行如下的操作：<br>
	 * <li>调用IRoleDAO.doUpdate()方法进行更新处理</li>
	 * <li>role更新完成之后调用IRoleDAO.doRemoveRoleAndGroups()方法删除role_groups表中的角色对应信息</li>
	 * <li>利用IRoleDAO.doCreateRoleAndGropus()方法进行role_groups关系表的保存处理</li>
	 * @param mid
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(String mid,Role vo) throws Exception ; 
	/**
	 * 进行数据的删除处理操作，角色删除之后会自动删除对应的用户以及对应的权限组信息
	 * @param mid 权限验证使用
	 * @param rid 要删除的角色
	 * @return
	 * @throws Exception
	 */
	public boolean rm(String mid,Set<Integer> rid) throws Exception ;

}
