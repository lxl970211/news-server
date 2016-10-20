package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

import javabean.Responsecodes;
import javabean.User;

import javax.mail.internet.NewsAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.Constant;
import utils.Utils;

import database.LinkDb;

public class RegisterServlet extends HttpServlet {
	private LinkDb linkDb;
	private Responsecodes rescode;
	public RegisterServlet(){
		linkDb = new LinkDb();
		rescode = new Responsecodes();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		//请求类型
		String type = request.getParameter("type");
		String user_email = request.getParameter("user_email");
		String password = request.getParameter("user_password");
		
		if ("register".equals(type)) {
				
				
				User user = new User();
				
				user.setUserName(request.getParameter("user_name"));
				user.setUserEmail(user_email);
				user.setUserPassword(password);
				
				String sql = "insert into news_user(user_name,user_email,user_password, user_headpath) values('"+user.getUserName()+"', '"+user.getUserEmail()+"', '"+user.getUserPassword()+"', '');";
				
				int result = linkDb.insertData(sql) ? Constant.REGISTER_AUCCESS : Constant.REGISTER_ERROR;
				
				
				rescode.setStatus(result);
				//返回注册结果给客户端
				out.println(Utils.returnRequestJson(rescode));
			
		}else if("email".equals(type)){
			//验证注册时邮箱是否存在
			if (user_email != null){
				String sql = "select user_email from news_user where user_email='"+user_email+"';";
				
				int resultcode = linkDb.queryData(sql) ? Constant.EMAILL_EXIST : Constant.EMAILL_UNEXIST;
				
				rescode.setStatus(resultcode);
				out.println(Utils.returnRequestJson(rescode));
			}
		}
		
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
	



}
