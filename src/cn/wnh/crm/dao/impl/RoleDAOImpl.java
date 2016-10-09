package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.dao.IRoleDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.Groups;
import cn.mldn.crm.vo.Role;

public class RoleDAOImpl extends AbstractDAOImpl implements IRoleDAO {

	public RoleDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Role vo) throws Exception {
		String sql = "INSERT INTO role(title) VALUES (?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Role vo) throws Exception {
		String sql = "UPDATE role SET title=? WHERE rid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setInt(2, vo.getRid());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		return super.handleRemoveNumberType("role", "rid", ids);
	} 

	@Override
	public List<Role> findAll() throws Exception {
		List<Role> all = new ArrayList<Role>();
		String sql = "SELECT rid,title FROM role";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Role vo = new Role();
			vo.setRid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Role findById(Integer id) throws Exception {
		String sql = "SELECT rid,title FROM role WHERE rid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Role vo = new Role();
			vo.setRid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			return vo ;
		}
		return null ;
	}

	@Override
	public List<Role> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findLastId() throws Exception {
		String sql = "SELECT LAST_INSERT_ID()";
		super.pstmt = super.conn.prepareStatement(sql);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public boolean doCreateRoleAndGropus(Role vo) throws Exception {
		String sql = "INSERT INTO role_groups(rid,gid) VALUES (?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		Iterator<Groups> iter = vo.getGroups().iterator();
		while (iter.hasNext()) {
			super.pstmt.setInt(1, vo.getRid());
			super.pstmt.setInt(2, iter.next().getGid());
			super.pstmt.addBatch(); // 追加批处理
		}
		int result[] = super.pstmt.executeBatch(); // 执行批处理的处理模式
		for (int x = 0; x < result.length; x++) {
			if (result[x] == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Map<Integer, Boolean> findGroupsByRole(Integer rid) throws Exception {
		Map<Integer,Boolean> map = new HashMap<Integer,Boolean>() ;
		String sql = "SELECT gid FROM role_groups WHERE rid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, rid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getInt(1), true) ;
		}
		return map;
	}

	@Override
	public boolean doRemoveRoleAndGroups(Integer rid) throws Exception {
		String sql = "DELETE FROM role_groups WHERE rid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, rid);
		return super.pstmt.executeUpdate() > 0 ;
	}

}
