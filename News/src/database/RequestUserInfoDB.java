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
			//先查询出token对应的用户邮箱 通过邮箱查询出用户其他信息
			if (res.next()) {
				String email = res.getString(1); 
				String queryuser = "select user_name, user_email, user_headpath from news_user where user_email='"+email+"';";
				res = stat.executeQuery(queryuser);
				if (res.next()) {
					//用户姓名
					user.setUserName(res.getString(1));
					//邮箱
					user.setUserEmail(res.getString(2));
					//用户头像保存路径
					user.setHeadPath(res.getString(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	//查询用户邮箱
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
	 * 根据url查询出所有该新闻的评论信息
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
