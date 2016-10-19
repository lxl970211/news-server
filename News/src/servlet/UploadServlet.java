package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javabean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.Utils;

import com.jspsmart.upload.SmartUpload;

import database.LinkDb;
import database.RequestUserInfoDB;

public class UploadServlet extends HttpServlet {
	
	private LinkDb linkDb;
	private RequestUserInfoDB requestUserInfoDB;
	public UploadServlet(){
		linkDb = new LinkDb();
		requestUserInfoDB = new RequestUserInfoDB();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�ļ��ϴ�
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		SmartUpload smartUpload = new SmartUpload();
		String msg=request.getParameter("msg");
		 
		 String token =request.getHeader("token");
		String email = new RequestUserInfoDB().queryUserEmail(token);
//		out.print(msg);  
        try {  
        	
            smartUpload.initialize(this.getServletConfig(), request, response); 
            smartUpload.upload();  
            com.jspsmart.upload.File smartFile = smartUpload.getFiles().getFile(0);  
            if (!smartFile.isMissing()) {  
            	//�ļ���
            	String imgName = new Utils().createPhotoFileName();
            	//����·��
                String saveFileName = getServletConfig().getServletContext().getRealPath("")+"/images/";  
                //����ͷ��
                smartFile.saveAs(saveFileName+imgName, smartUpload.SAVE_PHYSICAL); 
                
                updateUserHeadInfoToDataBase(imgName, email);
                
                System.out.print("ok:" + saveFileName+ ", msg:" + smartUpload.getRequest().getParameter("msg"));
            } else {  
            	System.out.print("missing...");  
            }  
        } catch (Exception e) {  
        	System.out.print(e+","+msg);  
        } 
		out.flush();
		out.close();
	}
	
	/**
	 * ����ͷ�����Ƶ��û���
	 */
	public void updateUserHeadInfoToDataBase(String imgName, String email){
		//ɾ���û���һ��ͷ���ļ�
		deleteLastHead(email);
		//�����û�����Ϣ
        String updateUser = "update news_user set user_headpath='"+imgName+"' where user_email='"+email+"'";
        linkDb.insertData(updateUser);
	}
	 
    
	public boolean deleteLastHead(String email){
		//��ѯ���û�����һ��ͷ�����Ʋ�ɾ���ļ�
		String quyseruserHead = "select user_headpath from news_user where user_email='"+email+"';";
		User user = requestUserInfoDB.queryUserInfo(email);
		if (!user.getHeadPath().equals(null)) {
			File file = new File(getServletConfig().getServletContext().getRealPath("")+"/images/"+user.getHeadPath());
			if (file.exists()) {
				file.delete();
				return true;
			}
		}
		
		return false;
		
		
	}
}


