package cn.mldn.crm.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.News;

public interface INewsServiceBack {
	/**
	 * 实现新闻数据的追加操作
	 * 
	 * @param vo
	 *            包含有新闻数据的VO对象
	 * @return 增加成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean add(News vo) throws Exception;
	/**
	 * 实现数据的分页查询处理操作
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行
	 * @return Map集合中包含如下内容：<br>
	 * <li>key = allNewses、value = INewsDAO.findAllSplit()</li>
	 * <li>key = newsCount、value = INewsDAO.getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String, Object> listSplit(String mid,String column, String keyWord,
			int currentPage, int lineSize) throws Exception; 
	/**
	 * 查看公告信息详情
	 * @param mid 权限验证使用
	 * @param nid 公告信息查询使用
	 * @return 
	 * @throws Exception
	 */
	public News show(String mid,int nid) throws Exception ;
	/**
	 * 数据更新前的查询处理
	 * @param mid
	 * @param nid
	 * @return
	 * @throws Exception
	 */
	public News editPre(String mid,int nid) throws Exception ;
	/**
	 * 数据更新操作
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(News vo) throws Exception ; 
	/**
	 * 执行公告的删除处理操作
	 * @param mid
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean rm(String mid,Set<Integer> ids) throws Exception ;
}
