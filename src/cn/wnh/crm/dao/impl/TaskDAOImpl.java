package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.crm.dao.ITaskDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.Task;

public class TaskDAOImpl extends AbstractDAOImpl implements ITaskDAO {

	public TaskDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Task vo) throws Exception {
		String sql = "INSERT INTO task(mid,cid,title,tdate,visit,type,note,status,level) VALUES (?,?,?,?,?,?,?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getMember().getMid());
		super.pstmt.setInt(2, vo.getClient().getCid());
		super.pstmt.setString(3, vo.getTitle());
		super.pstmt.setDate(4, new java.sql.Date(vo.getTdate().getTime()));
		super.pstmt.setInt(5, vo.getVisit());
		super.pstmt.setInt(6, vo.getType());
		super.pstmt.setString(7, vo.getNote());
		super.pstmt.setInt(8, vo.getStatus());
		super.pstmt.setInt(9, vo.getLevel());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(Task vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Task> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task findById(Integer id) throws Exception {
		String sql = "SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE tid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			return vo;
		}
		return null;
	}

	@Override
	public List<Task> findAllSplit(String column, String keyWord,
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
	public List<Task> findAllByMemberAndClient(String mid, Integer cid)
			throws Exception {
		List<Task> all = new ArrayList<Task>();
		String sql = "SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE mid=? AND cid=? ORDER BY tdate DESC,level ASC";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, cid);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Task> findAllByClient(Integer cid) throws Exception {
		List<Task> all = new ArrayList<Task>();
		String sql = "SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE cid=? ORDER BY tdate DESC,level ASC";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, cid);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Task findById(String mid, Integer cid, Integer tid) throws Exception {
		String sql = "SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE mid=? AND cid=? AND tid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, cid);
		super.pstmt.setInt(3, tid);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			return vo;
		}
		return null;
	}

	@Override
	public Task findById(String mid, Integer tid) throws Exception {
		String sql = "SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE mid=? AND tid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, tid);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			return vo;
		}
		return null;
	}

	@Override
	public List<Task> findAllSplitByMember(String mid, Integer visit,
			Integer type, String column, String keyWord, Integer currentPage,
			Integer lineSize) throws Exception {
		List<Task> all = new ArrayList<Task>();
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE cid IN (");
		buf.append("SELECT cid FROM client WHERE ");
		buf.append(column).append(" LIKE ? ");
		buf.append(" AND mid=? ").append(") ");
		if (visit != -1) { // 访问模式受限制
			buf.append(" AND visit=").append(visit);
		}
		if (type != -1) { // 访问模式受限制
			buf.append(" AND type=").append(type);
		}
		buf.append(" ORDER BY tdate DESC ,level ");
		buf.append(" LIMIT ?,? ");
		super.pstmt = super.conn.prepareStatement(buf.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setString(2, mid);
		super.pstmt.setInt(3, (currentPage - 1) * lineSize);
		super.pstmt.setInt(4, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			all.add(vo);
		}
		return all;
	}

	@Override
	public List<Task> findAllSplit(Integer visit, Integer type, String column,
			String keyWord, Integer currentPage, Integer lineSize)
			throws Exception {
		List<Task> all = new ArrayList<Task>();
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT tid,mid,cid,title,tdate,visit,type,note,status,level FROM task WHERE cid IN (");
		buf.append("SELECT cid FROM client WHERE ");
		buf.append(column).append(" LIKE ?)");
		if (visit != -1) { // 访问模式受限制
			buf.append(" AND visit=").append(visit);
		}
		if (type != -1) { // 访问模式受限制
			buf.append(" AND type=").append(type);
		}
		buf.append(" ORDER BY tdate DESC ,level ");
		buf.append(" LIMIT ?,? ");
		super.pstmt = super.conn.prepareStatement(buf.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			Task vo = new Task();
			vo.setTid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.getClient().setCid(rs.getInt(3));
			vo.setTitle(rs.getString(4));
			vo.setTdate(rs.getDate(5));
			vo.setVisit(rs.getInt(6));
			vo.setType(rs.getInt(7));
			vo.setNote(rs.getString(8));
			vo.setStatus(rs.getInt(9));
			vo.setLevel(rs.getInt(10));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCountByMember(String mid, Integer visit, Integer type,
			String column, String keyWord) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT COUNT(*) FROM task WHERE cid IN ( ");
		buf.append("SELECT cid FROM client WHERE ");
		buf.append(column).append(" LIKE ? ");
		buf.append(" AND mid=? ").append(") ");
		if (visit != -1) { // 访问模式受限制
			buf.append(" AND visit=").append(visit);
		}
		if (type != -1) { // 访问模式受限制
			buf.append(" AND type=").append(type);
		}
		super.pstmt = super.conn.prepareStatement(buf.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setString(2, mid);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public Integer getAllCount(Integer visit, Integer type, String column,
			String keyWord) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT COUNT(*) FROM task WHERE cid IN ( ");
		buf.append("SELECT cid FROM client WHERE ");
		buf.append(column).append(" LIKE ?)  ");
		if (visit != -1) { // 访问模式受限制
			buf.append(" AND visit=").append(visit);
		}
		if (type != -1) { // 访问模式受限制
			buf.append(" AND type=").append(type);
		}
		super.pstmt = super.conn.prepareStatement(buf.toString());
		super.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public boolean doUpdateByMember(Task vo) throws Exception {
		String sql = "UPDATE task SET title=?,tdate=?,visit=?,type=?,note=?,level=? WHERE mid=? AND tid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getTitle());
		super.pstmt.setDate(2, new java.sql.Date(vo.getTdate().getTime()));
		super.pstmt.setInt(3, vo.getVisit());
		super.pstmt.setInt(4, vo.getType());
		super.pstmt.setString(5, vo.getNote());
		super.pstmt.setInt(6, vo.getLevel());
		super.pstmt.setString(7, vo.getMember().getMid());
		super.pstmt.setInt(8, vo.getTid());
		return this.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdateByStatus(String mid, Integer tid, Integer status)
			throws Exception {
		String sql = "UPDATE task SET status=? WHERE mid=? AND tid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, status);
		super.pstmt.setString(2, mid);
		super.pstmt.setInt(3, tid);
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doRemoveByMember(String mid, Set<Integer> tids)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM task WHERE mid=? AND tid IN (");
		Iterator<Integer> iter = tids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		super.pstmt.setString(1, mid);
		return super.pstmt.executeUpdate() == tids.size() ;
	}

	@Override
	public Integer getAllCountByBeforeUnFinish(String mid, Date date)
			throws Exception {
		String sql = "SELECT COUNT(*) FROM task WHERE tdate<? AND status=1 AND mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setDate(1, new java.sql.Date(date.getTime()));
		super.pstmt.setString(2, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0;
	}

	@Override
	public Integer getAllCountByAfterFinish(String mid, Date date)
			throws Exception {
		String sql = "SELECT COUNT(*) FROM task WHERE tdate>=? AND status=1 AND mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setDate(1, new java.sql.Date(date.getTime()));
		super.pstmt.setString(2, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			return rs.getInt(1) ;
		}
		return 0;
	}
}
