package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseDatabase {
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param stat
	 * @param res
	 */
	public static void close(Connection conn, Statement stat, ResultSet res){
		try {
			if (conn != null) {
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (stat != null) {
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Connection conn, Statement stat){
		try {
			if (conn != null) {
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (stat != null) {
				stat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	


}
