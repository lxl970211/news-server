package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javabean.Update;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class UpdateServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		File file = new File(getServletConfig().getServletContext().getRealPath("")+"/download");
		File[] files = file.listFiles();
		List<File> list = new ArrayList<File>();  
		if (file.exists()) {
			for (File file2 : files) {
				list.add(file2);
			}
		}
		File file2 = new File(getServletConfig().getServletContext().getRealPath("")+"/download/"+list.get(0).getName());
		String name = file2.getName().split("_")[0];
		String version = file2.getName().split("_")[1];
		
		Update update = new Update();
		update.setName(name);
		update.setVersion(version);
		update.setPath("/News/download/"+file2.getName());
		out.println(new Gson().toJson(update));
			

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
