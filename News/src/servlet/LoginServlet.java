package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javabean.Responsecodes;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import utils.Constant;
import utils.Utils;

import database.LinkDb;

public class LoginServlet extends HttpServlet {
	private LinkDb linkDb;
	private Responsecodes responsecodes;
	public LoginServlet(){
		linkDb = new LinkDb();	
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_password");
		String token = request.getParameter("token");
		
		String usersql = "select * from news_user where user_email='"+user_email+"' and user_password='"+user_password+"';"; 
		//查询数据是否存在
		if (linkDb.queryData(usersql)) {
			String querytoken = "select * from token where token='"+token+"'";
			//如果token不存在则保存一条新的token
			if (!linkDb.queryData(querytoken)) {
				String insertToken = "insert into token values('"+user_email+"', '"+token+"');";
				linkDb.insertData(insertToken);
			}
			
			
			responsecodes = new Responsecodes();
			responsecodes.setStatus(Constant.LOGIN_AUCCESS);
			responsecodes.setToken(token);
			
			out.println(Utils.returnRequestJson(responsecodes));
			
		}else{
			responsecodes = new Responsecodes();
			responsecodes.setStatus(Constant.LOGIN_ERROR);
			
			out.println(Utils.returnRequestJson(responsecodes));
		}
		
		
		

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
