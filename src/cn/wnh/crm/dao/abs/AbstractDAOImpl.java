package cn.mldn.crm.dao.abs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractDAOImpl {
	protected Connection conn;
	protected PreparedStatement pstmt;

	public AbstractDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public Integer handleCount(String table, String column, String keyWord)
			throws Exception {
		String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column
				+ " LIKE ? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			return 0;
		}
	}

	public boolean handleRemoveNumberType(String tableName, String idColumn,
			Set<Integer> ids) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ").append(tableName).append(" WHERE ")
				.append(idColumn).append(" IN ( ");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")");
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}

	public boolean handleRemoveStringType(String tableName, String idColumn,
			Set<String> ids) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ").append(tableName).append(" WHERE ")
				.append(idColumn).append(" IN ( ");
		Iterator<String> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append("'").append(iter.next()).append("'").append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")");
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	} 
}
