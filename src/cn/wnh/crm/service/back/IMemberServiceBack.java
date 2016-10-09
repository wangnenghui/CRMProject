package cn.mldn.crm.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.crm.vo.Member;

public interface IMemberServiceBack {
	/**
	 * 实现用户的登录操作，本操作要执行如下调用：<br>
	 * <li>调用IMemberDAO.findLogin()方法验证用户名和密码，同时取出管理员标记</li>
	 * <li>调用IMemberDAO.doUpdateLastdate()方法更新最后一次登录日期</li>
	 * <li>...</li>
	 * @param vo 提交的密码一定要进行MD5的加密处理
	 * @return 返回的Map集合有两个组成部分：<br>
	 * <li>key = flag，value = 登录成功或失败的结果。</li>
	 * <li>key = allActions，value = 所有的权限组的权限信息。</li>
	 * @throws Exception
	 */
	public Map<String,Object> login(Member vo) throws Exception ;
	/**
	 * 密码修改操作，本操作要执行如下的调用：<br>
	 * <li>调用IMemberDAO.findLogin()方法来判断原始密码是否正确。</li>
	 * <li>调用IMemberDAO.doUpdatePassword()方法来进行新密码的修改。</li>
	 * @param mid 用户的id
	 * @param newPass 新的密码（MD5加密）
	 * @param oldPass 旧的密码（MD5加密）
	 * @return 密码修改成功返回true，否则返回false
	 * @throws Exception
	 */ 
	public boolean editPassword(String mid,String newPass,String oldPass) throws Exception ;
	/**
	 * 增加前的数据查询处理
	 * @param mid 权限验证
	 * @return 包含如下返回内容：<br>
	 * <li>key = allRoles、value = IRoleDAO.findAll()，返回的类型为List<Role></li>
	 * @throws Exception
	 */
	public Map<String,Object> addPre(String mid) throws Exception ; 
	/**
	 * 用户数据的添加处理，本操作的执行如下：<br>
	 * <li>利用IMemberDAO.findById()方法判断指定的id数据是否存在</li>
	 * <li>利用IMemberDAO.doCreate()保存用户信息</li>
	 * @param mid 权限验证
	 * @param vo 包含了新的用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean add(String mid, Member vo) throws Exception;
	/**
	 * 数据的分页列表显示
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return 返回数据包含如下内容：<br>
	 * <li>key = allMembers、value = IMemberDAO.findAllSplit()</li>
	 * <li>key = memberCount、value = IMemberDAO.getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String,Object> list(String mid,String column,String keyWord,int currentPage,int lineSize) throws Exception ;
	/**
	 * 修改前的数据查询处理
	 * @param mid 权限验证
	 * @return 包含如下返回内容：<br>
	 * <li>key = allRoles、value = IRoleDAO.findAll()，返回的类型为List<Role></li>
	 * <li>key = member、value = IMemberDAO.findById()，返回的类型为Member<Role></li>
	 * @throws Exception
	 */
	public Map<String,Object> editPre(String mid,String umid) throws Exception ;
	/**
	 * 数据的修改操作，调用IMemberDAO.doUpdate()方法
	 * @param mid
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(String mid,Member vo) throws Exception ;
	/**
	 * 修改密码
	 * @param mid 权限验证
	 * @param vo 包含有要修改密码的用户名和密码
	 * @return
	 * @throws Exception
	 */
	public boolean editPasswordByAdmin(String mid,Member vo) throws Exception ;
	/**
	 * 删除指定的用户信息
	 * @param mid 权限验证
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean rm(String mid,Set<String> ids ) throws Exception ;
}
