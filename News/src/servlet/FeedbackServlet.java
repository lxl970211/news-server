package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javabean.Responsecodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.LinkDb;

public class FeedbackServlet extends HttpServlet {
	private LinkDb linkDb;
	public FeedbackServlet(){
		linkDb = new LinkDb();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String feedbackInfo = request.getParameter("info");
		String contact = request.getParameter("contact");
		
		String sql = "insert into news_feedback(feedbackInfo, contact) values('"+feedbackInfo+"', '"+contact+"');";
		if (linkDb.insertData(sql)) {
			Responsecodes res = new Responsecodes();
			res.setStatus(1);
			out.println(new Gson().toJson(res));
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
