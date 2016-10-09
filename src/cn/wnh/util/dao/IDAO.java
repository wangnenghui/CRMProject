package cn.mldn.util.dao;
import java.util.List;
import java.util.Set;
/**
 * 定义DAO的公共操作接口，其中包含可能出现的常用操作方法
 * @author mldn
 * @param <K> 主键类型
 * @param <V> VO的类型
 */
public interface IDAO<K,V> {
	/**
	 * 实现数据的增加操作，要执行INSERT语句
	 * @param v 包含要增加数据的VO对象
	 * @return 如果增加成功，返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doCreate(V vo) throws Exception ;
	/**
	 * 数据的修改操作，执行UPDATE语句，此数据修改指的是表中的全部字段都要根据ID进行修改
	 * @param v 要修改的数据的VO对象
	 * @return 如果修改成功，返回true，否则返回false
	 * @throws Exception
	 */
	public boolean doUpdate(V vo) throws Exception ;
	/**
	 * 执行数据的删除操作，主要以批量删除为主，利用IN进行SQL的拼凑删除
	 * @param ids 要删除的数据的ID编号，不允许重复
	 * @return 删除成功返回true，如果有数据没有被删除掉返回false
	 * @throws Exception
	 */
	public boolean doRemove(Set<K> ids) throws Exception ;
	/**
	 * 数据的全部列表显示
	 * @return 所有的数据行都要转换为VO类的形式返回，如果没有数据则集合的长度为0（size()==0）
	 * @throws Exception
	 */
	public List<V> findAll() throws Exception ;
	/**
	 * 根据ID查询一条完整的记录，并且将记录信息转换为VO类对象返回
	 * @param id 要查询的数据ID编号
	 * @return 如果有指定ID内容，则将内容以VO的形式返回，如果没有数据则返回null
	 * @throws Exception
	 */
	public V findById(K id) throws Exception ;
	/**
	 * 执行数据的分页查询
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行数
	 * @return 所有的数据行都要转换为VO类的形式返回，如果没有数据则集合的长度为0（size()==0）
	 * @throws Exception
	 */
	public List<V> findAllSplit(String column,String keyWord,Integer currentPage,Integer lineSize) throws Exception ;
	/**
	 * 统计满足于模糊查询的数据量，使用COUNT()函数统计
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询关键字
	 * @return 数据行数，如果没有数据行则返回0
	 * @throws Exception
	 */
	public Integer getAllCount(String column,String keyWord) throws Exception ;
}
