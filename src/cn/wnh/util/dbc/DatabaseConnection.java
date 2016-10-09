package cn.mldn.util.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver" ;
	private static final String DBURL = "jdbc:mysql://localhost:3306/crmdb" ;
	private static final String DBUSER = "root" ;
	private static final String PASSWORD = "mysqladmin" ;
	private Connection conn ;
	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD) ;
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * 取得数据库连接对象
	 * @return 如果连接成功则返回连接对象，如果连接失败返回null
	 */
	public Connection getConnection() {
		return this.conn ;
	}
	/**
	 * 关闭连接
	 */
	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
}
