package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.crm.dao.IMemberNewsDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.MemberNews;

public class MemberNewsDAOImpl extends AbstractDAOImpl implements
		IMemberNewsDAO {

	public MemberNewsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(MemberNews vo) throws Exception {
		String sql = "INSERT INTO member_news(mid,nid,rdate) VALUES (?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getMember().getMid());
		super.pstmt.setInt(2, vo.getNews().getNid());
		super.pstmt.setTimestamp(3, new java.sql.Timestamp(vo.getRdate()
				.getTime()));
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(MemberNews vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<MemberNews> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberNews> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberNews findById(MemberNews id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberNews> findAllSplit(String column, String keyWord,
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
	public Integer getAllCountByMemberAndNews(String mid, Integer nid)
			throws Exception {
		String sql = "SELECT COUNT(*) FROM member_news WHERE mid=? AND nid=?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, mid);
		super.pstmt.setInt(2, nid);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public Integer getAllCountUnread(String mid) throws Exception {
		String sql = " SELECT COUNT(*) FROM news " + " WHERE nid NOT IN ( "
				+ " 		SELECT nid FROM member_news WHERE mid=?) ";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public Map<Integer, Boolean> findAllNotId(String mid,Set<Integer> ids)
			throws Exception {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		StringBuffer buf = new StringBuffer() ;
		buf.append("SELECT nid FROM news WHERE nid NOT IN (") ;
		buf.append("	SELECT nid FROM member_news WHERE mid=?) ") ;
		buf.append(" AND nid IN ( ") ;
		Iterator<Integer> iter = ids.iterator() ;
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length() - 1, buf.length()).append(")") ;
		super.pstmt = super.conn.prepareStatement(buf.toString()) ;
		super.pstmt.setString(1, mid);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			map.put(rs.getInt(1),true) ;
		}
		return map;
	}

}
