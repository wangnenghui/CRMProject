package cn.mldn.crm.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.Task;

public interface ITaskServiceBack {
	/**
	 * 进行指定客户的任务创建，需要客户的编号，在此操作里面需要执行如下的功能：<br>
	 * <li>必须要保证创建任务的用户具备有此类的权限</li> <li>必须判断指定的客户编号是否存在（保证客户的编号就是该用户的客户）</li>
	 * <li>创建任务</li>
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(Task vo) throws Exception;

	/**
	 * 根据用户名称查询指定客户的所有回访任务信息，本操作要执行如下的功能：<br>
	 * <li>调用ITaskDAO.findAllByMemberAndClient()方法查询全部的任务信息</li> <li>
	 * 调用IClientDAO.findByMemberAndId()方法，查询客户的信息</li>
	 * 
	 * @param mid
	 * @param cid
	 * @return 返回的数据包含如下内容：<br>
	 *         <li>key = allTasks、value = ITaskDAO.findAllByMemberAndClient()。</li>
	 *         <li>key = client、value = IClientDAO.findByMemberAndId()。</li>
	 * @throws Exception
	 */
	public Map<String, Object> listByMemberAndClient(String mid, int cid)
			throws Exception;

	/**
	 * 根据用户名称查询指定客户的所有回访任务信息，本操作要执行如下的功能：<br>
	 * <li>调用ITaskDAO.findAllByMemberAndClient()方法查询全部的任务信息</li> <li>
	 * 调用IClientDAO.findByMemberAndId()方法，查询客户的信息</li>
	 * 
	 * @param mid 权限验证使用
	 * @param cid
	 * @return 返回的数据包含如下内容：<br>
	 *         <li>key = allTasks、value = ITaskDAO.findAllByMemberAndClient()。</li>
	 *         <li>key = client、value = IClientDAO.findByMemberAndId()。</li>
	 * @throws Exception
	 */
	public Map<String, Object> listByClient(String mid, int cid)
			throws Exception;

	/**
	 * 根据用户名称查询指定客户的所有回访任务信息
	 * 
	 * @param mid
	 * @param cid
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public Task show(String mid, int cid, int tid) throws Exception;
	/**
	 * 查询指定任务编号的任务详情
	 * @param mid 是进行权限验证使用的
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public Task show(String mid,int tid) throws Exception ;
	/**
	 * 查询指定用户的全部数据，分页显示控制
	 * @param mid
	 * @param visit
	 * @param type
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize 
	 * @return 返回的结果包含如下数据：<br>
	 * <li>key = allTasks、value = ITaskDAO.findAllSplitByMember()</li>
	 * <li>key = taskCount、value = ITaskDAO.getAllCountByMember()</li> 
	 * @throws Exception
	 */
	public Map<String, Object> listByMember(String mid, int visit, int type,
			String column, String keyWord, int currentPage, int lineSize)
			throws Exception;
	
	/**
	 * 查询指定用户的全部数据，分页显示控制
	 * @param mid 管理员的权限确认使用
	 * @param visit
	 * @param type
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize 
	 * @return 返回的结果包含如下数据：<br>
	 * <li>key = allTasks、value = ITaskDAO.findAllSplitByMember()</li>
	 * <li>key = taskCount、value = ITaskDAO.getAllCountByMember()</li> 
	 * @throws Exception
	 */
	public Map<String, Object> list(String mid, int visit, int type,
			String column, String keyWord, int currentPage, int lineSize)
			throws Exception;
	/**
	 * 数据更新前的显示查询，在查询的过程之中一定只能够查询指定用户的任务
	 * @param mid
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public Task editPre(String mid,int tid) throws Exception ;
	/**
	 * 任务的更新处理操作，依然需要考虑到时间问题
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(Task vo) throws Exception ;
	/**
	 * 任务关闭操作，调用ITaskDAO.doUpdateByStatus()方法
	 * @param mid
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public boolean editOver(String mid,int tid) throws Exception ;
	/**
	 * 任务完成操作，调用ITaskDAO.doUpdateByStatus()方法
	 * @param mid
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public boolean editFinish(String mid,int tid) throws Exception ;
	/**
	 * 执行任务信息的删除处理操作，调用的ITaskDAO.doRemoveByMember()方法
	 * @param mid
	 * @param tids
	 * @return
	 * @throws Exception
	 */
	public boolean rmByMember(String mid,Set<Integer> tids) throws Exception ;
}
