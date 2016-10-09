package cn.mldn.crm.dao;

import java.util.Map;

import cn.mldn.crm.vo.Role;
import cn.mldn.util.dao.IDAO;

public interface IRoleDAO extends IDAO<Integer, Role> {
	/**
	 * 取得最后一次增长的rid数据
	 * @return
	 * @throws Exception
	 */
	public Integer findLastId() throws Exception ;
	/**
	 * 传递一个Role对象，里面一定要包含有角色编号以及对应的权限组编号
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean doCreateRoleAndGropus(Role vo) throws Exception ;
	/**
	 * 查询role_groups表，而后根据此表中角色编号查询出所有的权限组编号
	 * @param rid
	 * @return key = gid、value = true
	 * @throws Exception
	 */
	public Map<Integer,Boolean> findGroupsByRole(Integer rid) throws Exception ;
	/**
	 * 删除role_groups表中指定角色对应的所有权限组的关系
	 * @param rid 角色编号
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveRoleAndGroups(Integer rid) throws Exception ;
}
