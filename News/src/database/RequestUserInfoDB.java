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
	
	public User queryUserInfo(String email){
		User user = new User();
		conn = linkDb.link();
		try {
			stat = conn.createStatement();
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
	
	
	/**
	 * ��ȡ�û��ղ������б�
	 * @param email
	 * @return
	 */
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
		String sql = "select name, newsId, title, commentTime, content, zan, lou"; 
		sql += " from news_comment where newsId='"+url+"' ORDER BY commentTime DESC;";
		conn = linkDb.link();
		
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while (res.next()) {
				Comment comment = new Comment();
				comment.setName(res.getString(1));
				comment.setNewsId(res.getString(2));
				comment.setTitle(res.getString(3));
				comment.setCommentTime(res.getString(4));
				comment.setContent(res.getString(5));
				comment.setLike(res.getInt(6));
				comment.setLou(res.getInt(7));
				list.add(comment);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, prestate, res);
		}
		
		return list;
	}
	
	
	
	/**
	 * �õ��޵�����
	 * @param newsId
	 * @param lou
	 * @return
	 */
	public int getLikeCount(String newsId, String lou){
		String sqlString = "SELECT news_comment.zan FROM news_comment WHERE";
		sqlString += " newsId ='"+newsId+"' AND lou = '"+lou+"';";
		conn = linkDb.link();
		
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sqlString);
			if (res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر��������ݿ�
			CloseDatabase.close(conn, prestate, res);
		}
		
		
		return 0;
		
	}
	
	//��ȡ�κα���������
	public int getCount(String sql){
		
		conn = linkDb.link();
		
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if (res.next()) {
				return res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, prestate, res);
		}
		
		
		return 0;
		
	}
	
	//����
	public boolean like(String token, String newsId, String lou){

		//�����۵ĵ�������
		int likecount = getLikeCount(newsId, lou);
		
		String email = queryUserEmail(token);
		
		String update = "update news_comment set zan='"+(++likecount)+"' where newsId='"+newsId+"' and lou='"+lou+"';";
		
		
		
		if (linkDb.insertData(update)) {
			//������޼�¼���ж��û��Ƿ��ѵ���
			String saveannal = "insert into news_like values('"+email+"', '"+newsId+"', '"+lou+"');";
			linkDb.insertData(saveannal);
			return true;
		}
		return false;
		
	}
	
	
	public List<Integer> getuserZanList(String email, String newsId){
		List<Integer> list = new ArrayList<Integer>();
		String sql = "SELECT lou FROM news_like WHERE user_email = '"+email+"' AND newsId = '"+newsId+"' order by lou;";
		conn = linkDb.link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()){
				
				int lou = res.getInt(1);
				list.add(lou);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CloseDatabase.close(conn, prestate, res);
		}
		
		
		return list;
		
	}
	
	
	
	public ResultSet getUserCommentList(String sql){
		
		conn = linkDb.link();
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res;
		
		
	}
	
}
