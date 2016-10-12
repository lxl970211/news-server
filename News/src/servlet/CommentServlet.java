package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.CollectNewsBean;
import javabean.Comment;
import javabean.CommentBean;
import javabean.Responsecodes;
import javabean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.LinkDb;
import database.RequestUserInfoDB;
import database.Sql;

import utils.Constant;
import utils.Utils;

public class CommentServlet extends HttpServlet {
	private RequestUserInfoDB requestUserInfoDB;
	private LinkDb linkDb;
	public CommentServlet() {
		requestUserInfoDB = new RequestUserInfoDB();
		linkDb = new LinkDb();
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String token = request.getParameter("token");
		String newsId = request.getParameter("newsId");
		String commentTime = request.getParameter("commentTime");
		String content = request.getParameter("content");
		String lou = request.getParameter("lou");
		String title = request.getParameter("title");
		
		
		Responsecodes res = new Responsecodes();
		Gson gson = new Gson();
		//ͨ��token��ȡ�û�����id
		String email = requestUserInfoDB.queryUserEmail(token);
		ResultSet resSet;
	
		String queryAnnal = "select * from news_like where user_email='"+email+"' and lou='"+lou+"' and newsId='"+newsId+"'; ";
		
		if(type.equals("writeComment")){
			String sql = null;
			//��������url��ȡ��������������
			String querysql = "select count(newsId) from news_comment where newsId='"+newsId+"';";
			int loucoumt = requestUserInfoDB.getCount(querysql);
			
			if(token != null){
				if (Sql.queryToken(token)) {
					User user = requestUserInfoDB.queryUserInfo(token);
					sql = "insert into news_comment(name, news_email, newsId, title, commentTime, content, lou)"; 
					sql += "values('"+user.getUserName()+"','"+user.getUserEmail()+"', '"+newsId+"', '"+title+"', '"+commentTime+"', '"+content+"', '"+(++loucoumt)+"');";
				}
			}else{
				sql = "insert into news_comment(name, newsId, title, commentTime, content, lou)"; 
				sql += "values('"+name+"', '"+newsId+"', '"+title+"', '"+commentTime+"', '"+content+"', '"+(++loucoumt)+"');";
				
			}
			
			if (linkDb.insertData(sql)) {
				//���۳ɹ�
				res.setStatus(Constant.COMMENT_AUCCESS);
				out.println(Utils.returnRequestJson(res));
			}else{
				//����ʧ��
				res.setStatus(Constant.COMMENT_ERROR);
				out.println(Utils.returnRequestJson(res));
			}
			
		}else if(type.equals("commentList")){	//��ȡ���������б�
			
			List<Comment> list = requestUserInfoDB.getAllCommentByUrl(newsId);
				
				CommentBean commentBean = new CommentBean();
				commentBean.setList(list);
				commentBean.setCommentCount(list.size());
				out.println(gson.toJson(commentBean));
		}else if (type.equals("like")) {	//����
			
	
			//��ѯ�Ƿ��ѵ���  δ���޾���ӵ��޵����� �ѵ�����ȡ������
			if (!linkDb.queryData(queryAnnal)) {//δ����
				
				if (requestUserInfoDB.like(token, newsId, lou)) {
					//���޳ɹ�
					res.setStatus(1);
					
				}
			}else{
				int likecount = requestUserInfoDB.getLikeCount(newsId, lou);
				//ȡ������
				String dellike = "delete from news_like where user_email='"+email+"' and newsId='"+newsId+"' and lou='"+lou+"';";
				String update = "update news_comment set zan='"+(--likecount)+"' where newsId='"+newsId+"' and lou='"+lou+"';";
				if (linkDb.deleteData(dellike) && linkDb.insertData(update)){
					res.setStatus(2);
				}
				
			}
			out.println(gson.toJson(res));
		}else if (type.equals("userCommentList")) {
			String sql = "select newsId, title, commentTime, content, zan, lou from news_comment where news_email = '"+email+"';";
			
			resSet = requestUserInfoDB.getUserCommentList(sql);
			List<Comment> list = new ArrayList<Comment>();
			try {
				while(resSet.next()){
					Comment comment = new Comment();
					comment.setNewsId(resSet.getString(1));
					comment.setTitle(resSet.getString(2));
					comment.setCommentTime(resSet.getString(3));
					comment.setContent(resSet.getString(4));
					comment.setLike(resSet.getInt(5));
					comment.setLou(resSet.getInt(6));
					
					list.add(comment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					resSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				CommentBean commentBean = new CommentBean();
				commentBean.setList(list);
				out.println(gson.toJson(commentBean));

		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	
}
