package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.mail.search.FromStringTerm;

import javabean.CollectNewsBean;
import javabean.CollectNewsData;
import javabean.Comment;
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
				String queryuser = "select user_name, user_email, user_headpath from news_user where user_email='"+email+"';";
				res = stat.executeQuery(queryuser);
				if (res.next()) {
					//�û�����
					user.setUserName(res.getString(1));
					//����
					user.setUserEmail(res.getString(2));
					//�û�ͷ�񱣴�·��
					user.setHeadPath(res.getString(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	//��ѯ�û�����
	public String queryUserEmail(String token){
		String sql = "select user_email from token where token='"+token+"';";
		conn = linkDb.link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if (res.next()) {
				return res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, prestate, res);
		}
		
		return null;
		
	}
	
	
	
	public List<Object> getUserCollectNewsList(String email){
		List<Object> list = new ArrayList<Object>();
		String sql = "select url, title, collectTime from news_user_collectnews where user_email='"+email+"';";
		conn = linkDb.link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()){
				CollectNewsData collectNewsData = new CollectNewsData();
				collectNewsData.setUrl(res.getString(1));
				collectNewsData.setTitle(res.getString(2));
				collectNewsData.setCollectTime(res.getString(3));
				list.add(collectNewsData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, prestate, res);
		}
	
		return list;
		
		
	}
	
	
	/**
	 * ����url��ѯ�����и����ŵ�������Ϣ
	 * @param url
	 * @return
	 */
	public List<Comment> getAllCommentByUrl(String url){
		List<Comment> list = new ArrayList<Comment>();
		String sql = "select name, newsId, commentTime, content, zan, contra from news_comment where newsId='"+url+"';";
		conn = linkDb.link();
		
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				Comment commentBean = new Comment();
				commentBean.setName(res.getString(1));
				commentBean.setNewsId(res.getString(2));
				commentBean.setCommentTime(res.getString(3));
				commentBean.setContent(res.getString(4));
				commentBean.setZan(res.getInt(5));
				commentBean.setContra(res.getInt(6));
				list.add(commentBean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, prestate, res);
		}
		
		return list;
	}
	
}
