package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javabean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.LinkDb;
import database.RequestUserInfoDB;
import database.Sql;

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
		
		String name = request.getParameter("name");
		String token = request.getParameter("token");
		String newsId = request.getParameter("newsId");
		String commentTime = request.getParameter("commentTime");
		String content = request.getParameter("content");
		String sql = null;
		if(token != null){
			if (Sql.queryToken(token)) {
				User user = requestUserInfoDB.queryUserInfo(token);
				sql = "insert into news_comment(name, news_email, newsId, commentTime, content) values('"+user.getUserName()+"','"+user.getUserEmail()+"', '"+newsId+"', '"+commentTime+"', '"+content+"');";
			}
		}else{
			sql = "insert into news_comment(name, newsId, commentTime, content) values('"+name+"', '"+newsId+"', '"+commentTime+"', '"+content+"');";
			
		}
		
		if (linkDb.insertData(sql)) {
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
