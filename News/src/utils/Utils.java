package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

import javax.jms.Session;
import javax.mail.internet.HeaderTokenizer.Token;
import javax.servlet.http.HttpServletResponse;

import javabean.Responsecodes;
import javabean.User;

import com.google.gson.Gson;

import database.LinkDb;

public class Utils {
	
	/**
	 * 返回客户端请求结果存在为1，不存在为0
	 * @param isExist
	 * @return
	 */
	public static String returnRequestJson(Responsecodes res){
		Gson gson = new Gson();
		
		return gson.toJson(res, Responsecodes.class);
		
	}
	
	public static String returnRequestJson(User user){
		Gson gson = new Gson();
		
		return gson.toJson(user, User.class);
		
	}
	

	
	public static byte[] getImage(String filepath){
		FileInputStream fileInputStream;
		byte [] bytes = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		
	    int size = fileInputStream.available();  
	    bytes = new byte[size];  
	    fileInputStream.read(bytes);  
	    fileInputStream.close();  
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bytes;
		
	}
	

	
	
}
