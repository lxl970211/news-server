package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.search.FromStringTerm;

import javabean.Responsecodes;
import javabean.User;

public class RequestUserInfoDB {
	
	private LinkDb linkDb;
	private Connection conn;
	private Statement stat;
	private PreparedStatement prestate;
	private ResultSet res;
	public RequestUserInfoDB(){
		linkDb = new LinkDb();
		
	}
	
	public User queryUserInfo(String token){
		User user = new User();
		conn = linkDb.link();
		String sql = "select user_email from token where token='"+token+"';";
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			//�Ȳ�ѯ��token��Ӧ���û����� ͨ�������ѯ���û�������Ϣ
			if (res.next()) {
				String email = res.getString(1); 
				String queryuser = "select user_name, user_headpath from news_user where user_email='"+email+"';";
				res = stat.executeQuery(queryuser);
				if (res.next()) {
					//�û�����
					user.setUserName(res.getString(1));
					//�û�ͷ�񱣴�·��
					user.setHeadPath(res.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	
}
