package database;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.LoggingPermission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.Constant;
import utils.Utils;

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
	PreparedStatement preparedStatement = null;
	
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
	 * 插入数据
	 * @param sql
	 * @return
	 */
	public boolean insertData(String sql){
		
		conn = link();
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, preparedStatement);
		}
		
		
		return false;
		
	}
	/**
	 * 查询数据是否存在
	 * @param sql
	 * @return
	 */
	public boolean queryData(String sql){
		conn = link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if(res.next()){
				return true; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭连接
			CloseDatabase.close(conn, stat, res);
		}
		
		return false;
		
	}
	public boolean deleteData(String sql){
		conn = link();
		try {
			stat = conn.createStatement();
			stat.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, preparedStatement);
		}
		
		return false;
	}
	
	
}
