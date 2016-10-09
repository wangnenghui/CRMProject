package cn.mldn.crm.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.crm.vo.Task;
import cn.mldn.util.dao.IDAO;

public interface ITaskDAO extends IDAO<Integer, Task> {
	/**
	 * 根据用户以及指定的客户编号查询出一个客户的所有的任务信息，但是查询的时候是按照任务的完成时间倒序排列
	 * @param mid 用户编号
	 * @param cid 客户编号
	 * @return 所有当前用户的客户的任务信息以集合的方式返回
	 * @throws Exception
	 */
	public List<Task> findAllByMemberAndClient(String mid,Integer cid) throws Exception ;
	/**
	 * 根据用户以及指定的客户编号查询出一个客户的所有的任务信息，但是查询的时候是按照任务的完成时间倒序排列
	 * @param cid 客户编号
	 * @return 所有当前用户的客户的任务信息以集合的方式返回
	 * @throws Exception
	 */
	public List<Task> findAllByClient(Integer cid) throws Exception ; 
	/**
	 * 根据用户的编号、客户编号、任务编号查询详情
	 * @param mid 用户编号
	 * @param cid 客户编号
	 * @param tid 任务编号
	 * @return 任务的信息存在则以VO的形式返回，否则返回null
	 * @throws Exception
	 */
	public Task findById(String mid,Integer cid,Integer tid) throws Exception ;
	/**
	 * 根据指定的用户名以及任务编号查询出任务的具体信息
	 * @param mid
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	public Task findById(String mid,Integer tid) throws Exception ;
	/**
	 * 进行指定用户的任务列表显示处理操作
	 * @param mid 用户编号
	 * @param visit 回访模式
	 * @param type 用户的状态
	 * @param column 列名称
	 * @param keyWord 关键字
	 * @param currentPage 当前所在页
	 * @param lineSize 每页的显示行数
	 * @return 以List集合的方式返回部分查询结果
	 * @throws Exception
	 */
	public List<Task> findAllSplitByMember(String mid, Integer visit,
			Integer type, String column, String keyWord, Integer currentPage,
			Integer lineSize) throws Exception;
	/**
	 * 指定类型任务的数据量统计
	 * @param mid 用户编号
	 * @param visit 回访模式
	 * @param type 用户的状态
	 * @param column 列名称
	 * @param keyWord 关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByMember(String mid, Integer visit,
			Integer type, String column, String keyWord) throws Exception ;
	
	/**
	 * 进行指定用户的任务列表显示处理操作
	 * @param visit 回访模式
	 * @param type 用户的状态
	 * @param column 列名称
	 * @param keyWord 关键字
	 * @param currentPage 当前所在页
	 * @param lineSize 每页的显示行数
	 * @return 以List集合的方式返回部分查询结果
	 * @throws Exception
	 */
	public List<Task> findAllSplit(Integer visit,
			Integer type, String column, String keyWord, Integer currentPage,
			Integer lineSize) throws Exception;
	/**
	 * 指定类型任务的数据量统计
	 * @param mid 用户编号
	 * @param visit 回访模式
	 * @param type 用户的状态
	 * @param column 列名称
	 * @param keyWord 关键字
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCount(Integer visit, 
			Integer type, String column, String keyWord) throws Exception ;
	/**
	 * 更新指定用户的任务信息，更新的时候要求同时判断mid与tid
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateByMember(Task vo) throws Exception ;
	/**
	 * 根据指定的用户编号更新指定任务的状态值
	 * @param mid 用户编号
	 * @param tid 任务编号
	 * @param status 状态（status = 0 表示关闭、status = 2表示完成）
	 * @return
	 * @throws Exception
	 */
	public boolean doUpdateByStatus(String mid, Integer tid, Integer status)
			throws Exception;
	/**
	 * 删除指定用户的任务信息
	 * @param mid
	 * @param tids
	 * @return
	 * @throws Exception
	 */
	public boolean doRemoveByMember(String mid,Set<Integer> tids) throws Exception ;
	/**
	 * 统计出该日期之前未完成的任务量
	 * @param mid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByBeforeUnFinish(String mid,Date date) throws Exception ;
	/**
	 * 统计出该日期之后待完成的任务量
	 * @param mid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByAfterFinish(String mid,Date date) throws Exception ;
}
