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
	//����Mysql����
	private String mySqlDriver = "com.mysql.jdbc.Driver";
	
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	
	
	/**
	 * ����MySql���ݿ�
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
	 * ��ѯ�û������Ƿ���� �����ڣ�0 ���ڣ�1
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
	
	/**
	 * ע���û�
	 * @param sql
	 * @return
	 */
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
	
	public boolean loginUser(String useremail, String userPassword){
		String sql = "select * from news_user where user_email='"+useremail+"' and user_password='"+userPassword+"';";
		
		conn = link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if(res.next()){
				
				String name = res.getString(1);
				return true; 
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر�����
			close(conn, stat, res);
		}
		
		
		return false;
		
	}
	/**
	 * ����token
	 * @param token
	 * @return
	 */
	public boolean saveToken(String token){
		String insertToken = "insert into token values('"+token+"');";
		String selectToken = "select token from token where token='"+token+"'";
		conn = link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(selectToken);
			if (!res.next()) {
				stat.execute(insertToken);
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//�ر����ݿ�����
			close(conn, stat);
		}
		
		return false;
		
	}
	
	
	
	/**
	 * �ر����ݿ�����
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
