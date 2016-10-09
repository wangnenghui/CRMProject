package cn.mldn.crm.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.crm.vo.Groups;
import cn.mldn.util.dao.IDAO;

public interface IGroupsDAO extends IDAO<Integer, Groups> {
	/**
	 * 根据角色编号查询出所有的权限组信息
	 * @param rid 角色编号
	 * @return 如果该角色存在有权限组内容，则以List集合的方式返回
	 * @throws Exception
	 */
	public List<Groups> findAllByRole(Integer rid) throws Exception ; 
	/**
	 * 查询权限组的全部信息
	 * @return key = 类型、value = Groups的VO对象
	 * @throws Exception
	 */
	public Map<String,List<Groups>> findAllByType() throws Exception ;
}
