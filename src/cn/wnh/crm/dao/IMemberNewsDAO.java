package cn.mldn.crm.dao;

import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.MemberNews;
import cn.mldn.util.dao.IDAO;

public interface IMemberNewsDAO extends IDAO<MemberNews, MemberNews> {
	/**
	 * 统计出指定用户对于指定公告的阅读记录数
	 * @param mid
	 * @param nid
	 * @return
	 * @throws Exception
	 */
	public Integer getAllCountByMemberAndNews(String mid,Integer nid) throws Exception ;
	/**
	 * 统计出指定用户未读取的消息数量
	 * @param mid 用户ID
	 * @return 未读的消息数量
	 * @throws Exception
	 */
	public Integer getAllCountUnread(String mid) throws Exception ;
	/**
	 * 通过member_news表中查询出所有未读的消息的编号，结果通过Map集合保存
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,Boolean> findAllNotId(String mid,Set<Integer> ids) throws Exception ;
}
