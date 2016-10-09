package cn.mldn.crm.dao;

import cn.mldn.crm.vo.Member;
import cn.mldn.util.dao.IDAO;

public interface IMemberDAO extends IDAO<String, Member> {
	/**
	 * 实现用户的登录检测，登录的时候只允许登录活跃用户（locked = 0），但是随后要求将用户的管理员标记取出
	 * 传入的是一个VO类对象，那么可以利用引用传递的概念，通过VO类对象取回flag数据
	 * @param vo 包含有用户名和密码的VO类
	 * @return 如果登录成功返回true，否则返回false
	 * @throws Exception 
	 */
	public boolean findLogin(Member vo) throws Exception ; 
	/**
	 * 更新指定ID的最后一次的登录日期时间
	 * @param id 用户id
	 * @return 
	 * @throws Exception
	 */
	public boolean doUpdateLastdate(String id) throws Exception ;
	/**
	 * 更新用户密码
	 * @param mid 要修改的用户id
	 * @param password 用户的新密码
	 * @return 更新成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdatePassword(String mid,String password) throws Exception ;
}
