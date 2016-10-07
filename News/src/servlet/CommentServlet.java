package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		String sql = null;
		System.out.println(type);
		if(type.equals("sendComment")){
			if(token != null){
				if (Sql.queryToken(token)) {
					User user = requestUserInfoDB.queryUserInfo(token);
					sql = "insert into news_comment(name, news_email, newsId, commentTime, content) values('"+user.getUserName()+"','"+user.getUserEmail()+"', '"+newsId+"', '"+commentTime+"', '"+content+"');";
				}
			}else{
				sql = "insert into news_comment(name, newsId, commentTime, content) values('"+name+"', '"+newsId+"', '"+commentTime+"', '"+content+"');";
				
			}
			Responsecodes res = new Responsecodes();
			if (linkDb.insertData(sql)) {
				
				res.setStatus(Constant.COMMENT_AUCCESS);
				out.println(Utils.returnRequestJson(res));
			}else{
				res.setStatus(Constant.COMMENT_ERROR);
				out.println(Utils.returnRequestJson(res));
			}
			
		}else if(type.equals("commentList")){
			
			List<Comment> list = requestUserInfoDB.getAllCommentByUrl(newsId);
			if (list != null) {
				
				Gson gson = new Gson();
				CommentBean commentBean = new CommentBean();
				commentBean.setList(list);
				out.println(gson.toJson(commentBean));
				
			}else{
				out.println(0);
				System.out.println("0");
			}
		}
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
