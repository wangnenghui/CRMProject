package cn.mldn.crm.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.Client;

public interface IClientServiceBack {
	/**
	 * 实现客户信息的增加处理操作，本操作要执行如下功能：<br>
	 * <li>判断当前处理用户是否具备有指定的权限</li>
	 * <li>如果权限存在，那么就需要进行数据的保存</li>
	 * @param vo 包含有客户信息的VO类
	 * @return 增加成功返回true，否则返回false 
	 * @throws Exception
	 */
	public boolean add(Client vo) throws Exception;
	/**
	 * 根据用户以及类型实现数据的查询处理操作，本操作将执行如下的调用：<br>
	 * <li>调用IClientDAO.findAllByMemberAndType()方法查询数据</li>
	 * <li>调用IClientDAO.getAllCountByMemberAndType()方法统计数据量</li>
	 * @param mid 用户编号
	 * @param type 客户的类型
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 返回的内容包含以下数据：<br>
	 * <li>key = allClients、value = IClientDAO.findAllByMemberAndType()</li>
	 * <li>key = clientCount、value = IClientDAO.getAllCountByMemberAndType()</li>
	 * @throws Exception
	 */
	public Map<String, Object> listByMemberAndType(String mid, Integer type,
			String column, String keyWord, Integer currentPage, Integer lineSize)
			throws Exception; 
	/**
	 * 查询属于某一个用户的客户信息，在进行查询前一定要针对于当前用户的权限进行验证处理
	 * @param mid 用户的编号
	 * @param id 客户编号
	 * @return 返回该用户的指定的客户信息，如果没有则返回null
	 * @throws Exception
	 */
	public Client editPre(String mid,Integer id) throws Exception ;
	/**
	 * 更新某一个用户的客户信息，需要进行权限的验证处理操作
	 * @param vo 包含有客户信息的内容
	 * @return 如果更新成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean edit(Client vo) throws Exception ; 
	/**
	 * 删除指定用户的客户信息，删除之前需要进行相关权限的判断
	 * @param mid 用户的编号
	 * @param cids 要删除的客户的编号集合
	 * @return 
	 * @throws Exception
	 */
	public boolean rmByMember(String mid,Set<Integer> cids) throws Exception ;
}
