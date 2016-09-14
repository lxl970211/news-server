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
	private Gson gson;
	public LoginServlet(){
		linkDb = new LinkDb();
		gson = new Gson();
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
		
		
		if (linkDb.loginUser(user_email, user_password)) {
			if (linkDb.saveToken(token)) {
				Responsecodes res = new Responsecodes();
				res.setStatus(String.valueOf(Constant.LOGIN_AUCCESS));
				res.setToken(token);
				out.println(gson.toJson(res, Responsecodes.class));
			}	
			
		}else{
			out.println(Utils.returnRequestJson(Constant.LOGIN_ERROR));
		}
		
		
		

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
