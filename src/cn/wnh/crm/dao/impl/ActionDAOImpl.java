package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.dao.IActionDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.Action;

public class ActionDAOImpl extends AbstractDAOImpl implements IActionDAO {

	public ActionDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Action vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Action vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Action> findAll() throws Exception {
		List<Action> all = new ArrayList<Action>() ;
		String sql = "SELECT actid,title,menu,url FROM action " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Action vo = new Action() ;
			vo.setActid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setMenu(rs.getInt(3));
			vo.setUrl(rs.getString(4));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public Action findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAllSplit(String column, String keyWord,
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
	public List<Action> findAllByGroups(Integer gid) throws Exception {
		List<Action> all = new ArrayList<Action>() ;
		String sql = "SELECT actid,title,menu,url FROM action "
				+ " WHERE actid IN ( "
				+ " SELECT actid FROM groups_action WHERE gid=?) " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, gid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Action vo = new Action() ;
			vo.setActid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setMenu(rs.getInt(3));
			vo.setUrl(rs.getString(4));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public Map<String, Action> findAllByGroups(Set<Integer> gid)
			throws Exception {
		Map<String,Action> all = new HashMap<String,Action>() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append("SELECT actid,title,menu,url FROM action "
				+ " WHERE actid IN ( "
				+ " SELECT actid FROM groups_action WHERE gid IN (") ;
		Iterator<Integer> iter = gid.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length()-1, buf.length()).append("))") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Action vo = new Action() ;
			vo.setActid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setMenu(rs.getInt(3));
			vo.setUrl(rs.getString(4));
			all.put(String.valueOf(vo.getActid()), vo) ;
		}
		return all;
	}

	@Override
	public Action findByRoleAndId(Integer rid, Integer actid) throws Exception {
		Action vo = null ;
		String sql = "SELECT actid,title,menu,url"
				+ " 	 FROM action WHERE actid IN ("
				+ "				SELECT actid FROM groups_action "
				+ "					WHERE gid IN ( "
				+ "						SELECT gid FROM role_groups WHERE rid=?))"
				+ "							 AND actid=? " ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, rid);
		super.pstmt.setInt(2, actid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			vo = new Action() ;
			vo.setActid(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setMenu(rs.getInt(3));
			vo.setUrl(rs.getString(4));
		}
		return vo;
	}

}
