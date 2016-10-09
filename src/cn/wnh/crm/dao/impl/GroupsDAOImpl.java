package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.dao.IGroupsDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.Groups;

public class GroupsDAOImpl extends AbstractDAOImpl implements IGroupsDAO {

	public GroupsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Groups vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Groups vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Groups> findAll() throws Exception {
		List<Groups> all = new ArrayList<Groups>() ;
		String sql = "SELECT gid,title,img,type FROM groups";
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Groups vo = new Groups() ;
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setImg(rs.getString(3));
			vo.setType(rs.getString(4));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public Groups findById(Integer id) throws Exception {
		String sql = "SELECT gid,title,img,type FROM groups WHERE gid=?";
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			Groups vo = new Groups() ;
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setImg(rs.getString(3));
			vo.setType(rs.getString(4));
			return vo ;
		}
		return null ;
	}

	@Override
	public List<Groups> findAllSplit(String column, String keyWord,
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
	public List<Groups> findAllByRole(Integer rid) throws Exception {
		List<Groups> all = new ArrayList<Groups>() ;
		String sql = "SELECT gid,title,img,type FROM groups "
				+ " WHERE gid IN ( "
				+ " SELECT gid FROM role_groups WHERE rid=?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, rid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Groups vo = new Groups() ;
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setImg(rs.getString(3));
			vo.setType(rs.getString(4));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public Map<String, List<Groups>> findAllByType() throws Exception {
		Map<String,List<Groups>> all = new HashMap<String,List<Groups>>() ;
		String sql = "SELECT gid,title,img,type FROM groups";
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Groups vo = new Groups() ;
			vo.setGid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setImg(rs.getString(3));
			vo.setType(rs.getString(4));
			if (all.containsKey(vo.getType())) {	// 现在该key已经存在了
				List<Groups> temp = all.get(vo.getType()) ;
				temp.add(vo) ;
				all.put(vo.getType(), temp) ;
			} else {
				List<Groups> temp = new ArrayList<Groups>() ;
				temp.add(vo) ;
				all.put(vo.getType(), temp) ;
			}
		}
		return all;
	}

}
