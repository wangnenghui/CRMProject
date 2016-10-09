package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.mldn.crm.dao.IMemberDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.Member;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {

	public MemberDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Member vo) throws Exception {
		String sql = "INSERT INTO member(mid,rid,password,tel,photo,locked,flag) VALUES (?,?,?,?,?,?,?)" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid());
		super.pstmt.setInt(2, vo.getRole().getRid());
		super.pstmt.setString(3, vo.getPassword());
		super.pstmt.setString(4,vo.getTel());
		super.pstmt.setString(5, vo.getPhoto());
		super.pstmt.setInt(6, vo.getLocked());
		super.pstmt.setInt(7, vo.getFlag());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doUpdate(Member vo) throws Exception {
		String sql = "UPDATE member SET rid=?,tel=?,locked=?,photo=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setInt(1, vo.getRole().getRid());
		super.pstmt.setString(2, vo.getTel());
		super.pstmt.setInt(3, vo.getLocked());
		super.pstmt.setString(4, vo.getPhoto());
		super.pstmt.setString(5, vo.getMid());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemove(Set<String> ids) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ").append("member").append(" WHERE ")
				.append("mid").append(" IN ( ");
		Iterator<String> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append("'").append(iter.next()).append("'").append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")");
		buf.append(" AND flag=0") ;	// 只能够删除普通用户
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}

	@Override
	public List<Member> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member findById(String id) throws Exception {
		Member vo = null ;
		String sql = "SELECT mid,rid,tel,lastdate,photo,flag,locked FROM member WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, id);
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			vo = new Member() ;
			vo.setMid(rs.getString(1));
			vo.getRole().setRid(rs.getInt(2));
			vo.setTel(rs.getString(3));
			vo.setLastdate(rs.getDate(4));
			vo.setPhoto(rs.getString(5));
			vo.setFlag(rs.getInt(6));
			vo.setLocked(rs.getInt(7));
		}
		return vo;
	}

	@Override
	public List<Member> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		List<Member> all = new ArrayList<Member>() ;
		String sql = "SELECT mid,rid,tel,lastdate,photo,flag,locked FROM member WHERE " + column + " LIKE ? LIMIT ?,?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery() ;
		while (rs.next()) {
			Member vo = new Member() ;
			vo.setMid(rs.getString(1));
			vo.getRole().setRid(rs.getInt(2));
			vo.setTel(rs.getString(3));
			vo.setLastdate(rs.getDate(4));
			vo.setPhoto(rs.getString(5));
			vo.setFlag(rs.getInt(6));
			vo.setLocked(rs.getInt(7));
			all.add(vo) ;
		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		return super.handleCount("member", column, keyWord);
	}

	@Override
	public boolean findLogin(Member vo) throws Exception {
		String sql = "SELECT flag,rid FROM member WHERE mid=? AND password=? AND locked=0" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMid()); 
		super.pstmt.setString(2, vo.getPassword());
		ResultSet rs = super.pstmt.executeQuery() ;
		if (rs.next()) {
			vo.setFlag(rs.getInt(1));
			vo.getRole().setRid(rs.getInt(2));
			return true ;
		} 
		return false;
	}

	@Override
	public boolean doUpdateLastdate(String id) throws Exception {
		String sql = "UPDATE member SET lastdate=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
		super.pstmt.setString(2, id);
		return super.pstmt.executeUpdate() > 0; 
	}

	@Override
	public boolean doUpdatePassword(String mid, String password)
			throws Exception {
		String sql = "UPDATE member SET password=? WHERE mid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, password);
		super.pstmt.setString(2, mid);
		return super.pstmt.executeUpdate() > 0 ; 
	}

}
