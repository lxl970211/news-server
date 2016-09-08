package utils;

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
	public static String returnRequestJson(int isExist){
		Gson gson = new Gson();
		Responsecodes responsecodes = new Responsecodes();
		responsecodes.setStatus(String.valueOf(isExist));
		
		return gson.toJson(responsecodes, Responsecodes.class);
		
	}
	
	
	public static String registeruser(User user){
		System.out.println(user.getName());
		String sql = "insert into news_user values('"+user.getName()+"', '"+user.getEmail()+"', md5('"+user.getPassword()+"'));";
		System.out.println(sql);
		Gson gson = new Gson();
		Responsecodes res = new Responsecodes();
		
		res.setStatus(String.valueOf(new LinkDb().registerUser(sql)));
		
	
		return gson.toJson(res, Responsecodes.class);
		
		
		
	}
}
