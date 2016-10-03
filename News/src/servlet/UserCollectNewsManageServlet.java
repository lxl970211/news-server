package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javabean.CollectNewsBean;
import javabean.CollectNewsData;
import javabean.Responsecodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.LinkDb;
import database.RequestUserInfoDB;
import database.Sql;

import utils.Utils;

public class UserCollectNewsManageServlet extends HttpServlet {

	private LinkDb linkDb;
	private Responsecodes res;
	
	public UserCollectNewsManageServlet(){
		linkDb = new LinkDb();
		res = new Responsecodes();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String newsUrl = request.getParameter("newsUrl");
		String newsTitle = request.getParameter("newsTitle");
		String collectTime = request.getParameter("collectTime");
		String token = request.getParameter("token");
		String user_email = new RequestUserInfoDB().queryUserEmail(token);
		
		
		//�ղ�����
		if ("collectNews".equals(type)) {
			//token����
			if (Sql.queryToken(token)) {
				
				String saveNewsInfo= "insert into news_user_collectnews(user_email, url, title, collectTime)";
						saveNewsInfo += "values('"+user_email+"', '"+newsUrl+"', '"+newsTitle+"', '"+collectTime+"');";
				//����������״̬		
				if (linkDb.insertData(saveNewsInfo)) {
					res.setStatus(1);
				}else{
					res.setStatus(0);	
				}
				out.println(Utils.returnRequestJson(res));
			}
		}else if("checkIsCollect".equals(type)){	//��������Ƿ����ղ�
			String sql = "SELECT news_user_collectnews.url FROM token ,news_user_collectnews WHERE token.token = '"+token+"' AND news_user_collectnews.url = '"+newsUrl+"';";
			
			if (linkDb.queryData(sql)) {
				//�����򷵻�1 �����ڷ���0
				res.setStatus(1);
				
			}else{
				res.setStatus(0);
			}
			//��json��ʽ���ؽ��
			out.println(Utils.returnRequestJson(res));
			
		}else if ("deleteCollect".equals(type)) {	//ɾ���ղص�����
			
			String sqlString = "delete from news_user_collectnews where user_email='"+user_email+"' and url='"+newsUrl+"';";
			if (linkDb.deleteData(sqlString)) {
				res.setStatus(0);
				out.println(Utils.returnRequestJson(res));
			}
			
		}else if ("getCollectNewsList".equals(type)) {
			 
			
			RequestUserInfoDB requestUserInfoDB = new RequestUserInfoDB();
			String email = requestUserInfoDB.queryUserEmail(token);  
			Gson gson = new Gson();
			List<CollectNewsData> list = requestUserInfoDB.getUserCollectNewsList(email);
			CollectNewsBean collectNewsBean = new CollectNewsBean();

			for (int i = 0; i < list.size(); i++) {
				collectNewsBean.setList(list);
				
			}
			out.println(gson.toJson(collectNewsBean));
			
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
