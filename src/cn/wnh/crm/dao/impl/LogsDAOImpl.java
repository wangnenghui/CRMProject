package cn.mldn.crm.dao.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.crm.dao.ILogsDAO;
import cn.mldn.crm.dao.abs.AbstractDAOImpl;
import cn.mldn.crm.vo.Logs;

public class LogsDAOImpl extends AbstractDAOImpl implements ILogsDAO {

	public LogsDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public boolean doCreate(Logs vo) throws Exception {
		String sql = "INSERT INTO logs(mid,indate) VALUES (?,?)";
		super.pstmt = super.conn.prepareStatement(sql);
		super.pstmt.setString(1, vo.getMember().getMid());
		super.pstmt.setTimestamp(2, new Timestamp(new Date().getTime()));
		return super.pstmt.executeUpdate() > 0;
	} 

	@Override
	public boolean doUpdate(Logs vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doRemove(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Logs> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logs findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Logs> findAllSplit(String column, String keyWord,
			Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
