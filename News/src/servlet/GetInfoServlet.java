package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javabean.Responsecodes;
import javabean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.Utils;

import database.LinkDb;
import database.RequestUserInfoDB;
import database.Sql;

public class GetInfoServlet extends HttpServlet {

	private LinkDb linkDb;
	private RequestUserInfoDB requestUserInfoDB;
	
	public GetInfoServlet() {
		linkDb = new LinkDb();
		requestUserInfoDB = new RequestUserInfoDB();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	
		
		String token = request.getParameter("token");
		String type = request.getParameter("type"); 
		String path = "E:\\Graduation\\News\\WebRoot\\img\\userhead\\head.jpg";
		if (type.equals("updateName")) {
			Responsecodes res = new Responsecodes();
			String newName = request.getParameter("newName");
			String email = requestUserInfoDB.queryUserEmail(token);
			
			String sql = "update news_user set user_name='"+newName+"' where user_email='"+email+"';";
			if (linkDb.insertData(sql)) {
				res.setStatus(1);
				
			}else {
				res.setStatus(0);
			}
			
			out.println(new Gson().toJson(res));
			
			
			
		}else if(type.equals("BasicInfo")){
			if (Sql.queryToken(token)) {
					User user = requestUserInfoDB.queryUserInfo(token);
					out.println(Utils.returnRequestJson(user));
				
			
			}
			
		}
		
	  
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
