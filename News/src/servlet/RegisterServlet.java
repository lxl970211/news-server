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
	//http://localhost:8080/News/servlet/RegisterServlet?type=register&user_email=lxl970@outlook.com&user_name=%E5%88%98%E5%B0%8F%E9%BE%99&user_password=liuxiaolong
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
//				String name = new String(request.getParameter("user_name").getBytes("iso-8859-1"),"utf-8");
				
				user.setName(request.getParameter("user_name"));
				user.setEmail(user_email);
				user.setPassword(password);
				
				String sql = "insert into news_user values('"+user.getName()+"', '"+user.getEmail()+"', '"+user.getPassword()+"');";
				
				int result = linkDb.insertData(sql) ? Constant.REGISTER_AUCCESS : Constant.REGISTER_ERROR;
				
				
				rescode.setStatus(result);
				//返回注册结果给客户端
				out.println(Utils.returnRequestJson(rescode));
			
		}else if("email".equals(type)){
			//验证注册时邮箱是否存在
			if (user_email != null){
				String sql = "select user_email from news_user where user_email='"+user_email+"';";
				
				int resultcode = linkDb.checkDataExists(sql) ? Constant.EMAILL_EXIST : Constant.EMAILL_UNEXIST;
				
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
