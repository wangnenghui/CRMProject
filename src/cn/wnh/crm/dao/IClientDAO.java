package cn.mldn.crm.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.Client;
import cn.mldn.util.dao.IDAO;

public interface IClientDAO extends IDAO<Integer, Client> {
	/**
	 * 根据指定的用户编号分页显示该用户所有的客户信息
	 * @param mid 查看用户的编号
	 * @param type 用户的类型
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public List<Client> findAllSplitByMemberAndType(String mid,Integer type,String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception ;
	/**
	 * 统计一个用户的所有客户量
	 * @param mid
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByMemberAndType(String mid,Integer type,String column,String keyWord) throws Exception ;
	/**
	 * 将针对于指定用户的信息进行更新处理
	 * @param vo 包含有指定的用户信息
	 * @return 
	 * @throws Exception
	 */
	public boolean doUpdateByMember(Client vo) throws Exception ;
	/**
	 * 必须保证根据指定的用户名来查询出该用户的客户信息
	 * @param mid 用户名
	 * @param id 客户id
	 * @return
	 * @throws Exception
	 */
	public Client findByMemberAndId(String mid,Integer id) throws Exception ;
	/**
	 * 删除指定用户的客户信息
	 * @param mid 当前的用户
	 * @param cids 所有的客户编号
	 * @return 删除成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doRemoveByMember(String mid,Set<Integer> cids) throws Exception ;
	/**
	 * 根据用户类型查看所有的用户信息
	 * @param type
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 * @throws Exception
	 */
	public List<Client> findAllSplitByType(Integer type,String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception ;
	/**
	 * 根据客户类型统计出客户的数量
	 * @param type
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByType(Integer type,String column,String keyWord) throws Exception ;
	/**
	 * 根据指定的用户名以及客户编号，来判断该客户是否属于指定的用户
	 * @param mid 用户ID
	 * @param cid 客户ID
	 * @return 如果该客户属于指定用户，则返回true，否则返回false
	 * @throws Exception
	 */
	public boolean findExistsByMemberAndCid(String mid,Integer cid) throws Exception ;
	/**
	 * 根据一组的客户ID信息查询所有的客户信息
	 * @param id 要查询的客户ID
	 * @return key为cid，value为name
	 * @throws Exception
	 */
	public Map<Integer,String> findByIds(Set<Integer> id) throws Exception ;
	/**
	 * 根据用户名称以及任务的状态统计出数据量
	 * @param mid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByMemberAndStatus(String mid,Integer status) throws Exception ;
	
} 
