package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.LoggingPermission;

import utils.Constant;

import javabean.User;

import com.mysql.jdbc.log.Log;


public class LinkDb {
	private String username = "lxl";
	private String passWord = "liuxiaolong";
	private String linkMySql = "jdbc:mysql://localhost:3306/news?useUnicode=true&characterEncoding=gbk";
	//连接Mysql驱动
	private String mySqlDriver = "com.mysql.jdbc.Driver";
	
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	
	
	/**
	 * 链接MySql数据库
	 * @return
	 */
	public Connection link(){
		
		
		try {
			
			Class.forName(mySqlDriver);
			conn = DriverManager.getConnection(linkMySql, username, passWord);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 查询用户邮箱是否存在 不存在：0 存在：1
	 * @param sql
	 * @return
	 */
	public int findUserEmailExist(String sql){
		int isExist = 0;
		conn = link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if (res.next()) {
				isExist = 1;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(conn, stat, res);
		}
		
		
		return isExist;
	}
	
	
	public int registerUser(String sql){
		
		conn = link();
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.executeUpdate();
			return Constant.REGISTER_AUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			close(conn, stat);
		}
		
		
		return Constant.REGISTER_ERROR;
		
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param stat
	 * @param res
	 */
	public void close(Connection conn, Statement stat, ResultSet res){
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
	
	
	public void close(Connection conn, Statement stat){
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
