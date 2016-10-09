package cn.mldn.crm.service.back;

import java.util.Map;

import cn.mldn.crm.vo.Client;

public interface IManagerClientServiceBack {
	/**
	 * 根据客户类型查询客户的信息，并且进行分页显示
	 * @param type
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 返回的数据包含如下内容：<br>
	 * <li>key = allClients、value = IClientDAO.findAllByType()</li>
	 * <li>key = clientCount、value = IClientDAO.getAllCountByType()</li>
	 * @throws Exception 
	 */
	public Map<String, Object> listByType(String mid,int type, String column,
			String keyWord, int currentPage, int lineSize) throws Exception;
	/**
	 * 根据客户编号查询出客户的完整信息
	 * @param mid 需要进行相关的权限检测
	 * @param cid
	 * @return
	 * @throws Exception
	 */
	public Client show(String mid,int cid) throws Exception ;
}
