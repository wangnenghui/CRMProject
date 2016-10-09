package cn.mldn.crm.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.Action;
import cn.mldn.util.dao.IDAO;

public interface IActionDAO extends IDAO<Integer, Action> {
	/**
	 * 根据权限组的编号查询出对应的所有权限信息
	 * @param gid 权限组编号 
	 * @return 所有该权限组对应的权限信息，以List集合返回，如果没有数据则集合长度为0
	 * @throws Exception
	 */
	public List<Action> findAllByGroups(Integer gid) throws Exception ;
	/**
	 * 根据权限组编号查询出所有的权限信息
	 * @param gid 查询一组GID的信息
	 * @return 返回的是Map集合，key为权限编号，而value是权限的内容
	 * @throws Exception
	 */
	public Map<String,Action> findAllByGroups(Set<Integer> gid) throws Exception ;
	/**
	 * 根据指定的角色编号以及权限编号查询该权限内容是否存在
	 * @param rid 角色编号
	 * @param actid 权限编号
	 * @return 如果存在则返回指定的权限对象，如果不存在，则返回null
	 * @throws Exception
	 */
	public Action findByRoleAndId(Integer rid,Integer actid) throws Exception ;
}
