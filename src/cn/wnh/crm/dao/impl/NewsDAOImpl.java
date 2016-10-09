package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.mldn.crm.dao.INewsDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.News;

public class NewsDAOImpl extends AbstractDAOImpl implements INewsDAO {

	public NewsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(News vo) throws Exception {
		String sql = "INSERT INTO news(mid,title,type,pubdate,note) VALUES (?,?,?,?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getMember().getMid());
		super.pstmt.setString(2, vo.getTitle());
		super.pstmt.setInt(3, vo.getType());
		super.pstmt.setTimestamp(4, new java.sql.Timestamp(vo.getPubdate()
				.getTime()));
		super.pstmt.setString(5, vo.getNote());
		return super.pstmt.executeUpdate() > 0;
	}

	@Override
	public boolean doUpdate(News vo) throws Exception {
		String sql = "UPDATE news SET mid=?,title=?,type=?,note=? WHERE nid=?" ;
		super.pstmt = super.conn.prepareStatement(sql) ;
		super.pstmt.setString(1, vo.getMember().getMid());
		super.pstmt.setString(2, vo.getTitle());
		super.pstmt.setInt(3, vo.getType());
		super.pstmt.setString(4, vo.getNote());
		super.pstmt.setInt(5, vo.getNid());
		return super.pstmt.executeUpdate() > 0 ;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		return super.handleRemoveNumberType("news", "nid", ids); 
	}

	@Override
	public List<News> findAll() throws Exception {
		return null;
	}

	@Override
	public News findById(Integer id) throws Exception {
		String sql = "SELECT nid,mid,title,type,pubdate,note FROM news WHERE nid=?" ;
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setInt(1, id);
		ResultSet rs = super.pstmt.executeQuery();
		if (rs.next()) {
			News vo = new News();
			vo.setNid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.setTitle(rs.getString(3));
			vo.setType(rs.getInt(4));
			vo.setPubdate(rs.getDate(5));
			vo.setNote(rs.getString(6));
			return vo ;
		}
		return null;
	}

	@Override
	public List<News> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		List<News> all = new ArrayList<News>();
		String sql = "SELECT nid,mid,title,type,pubdate,note FROM news WHERE "
				+ column + " LIKE ? ORDER BY pubdate DESC LIMIT ?,?";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, "%" + keyWord + "%");
		super.pstmt.setInt(2, (currentPage - 1) * lineSize);
		super.pstmt.setInt(3, lineSize);
		ResultSet rs = super.pstmt.executeQuery();
		while (rs.next()) {
			News vo = new News();
			vo.setNid(rs.getInt(1));
			vo.getMember().setMid(rs.getString(2));
			vo.setTitle(rs.getString(3));
			vo.setType(rs.getInt(4));
			vo.setPubdate(rs.getDate(5));
			vo.setNote(rs.getString(6));
			all.add(vo);
		}
		return all;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		return super.handleCount("news", column, keyWord);
	}

}
