package test;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import javabean.CollectNewsBean;
import javabean.CollectNewsData;
import javabean.Comment;
import javabean.CommentBean;
import javabean.User;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import database.LinkDb;
import database.RequestUserInfoDB;

public class RequestUserInfoDBTest {
	private RequestUserInfoDB requestUserInfoDB;
	@Before
	public void setUp() throws Exception {
		requestUserInfoDB = new RequestUserInfoDB();
	}

	@Test
	public void testQueryUserInfo() {
		requestUserInfoDB.queryUserInfo("Android SDK built for x86null9cedf6ada8c6cf9cd86d6c2b1072537e");
	}
	
	@Test
	public void queryEmailTest(){
		String emailString = requestUserInfoDB.queryUserEmail("Moto G 2014 LTEf8:cf:c5:df:44:bc5bb7d523170017ec97e052ebce5684b9");
		System.out.println(emailString);
		
	}
	
	@Test
	public void getUserCollectNewsListTest(){
		Gson gson = new Gson();
		List<Object> list = requestUserInfoDB.getUserCollectNewsList("root@outlook.com");
		CollectNewsBean collectNewsBean = new CollectNewsBean();

		for (int i = 0; i < list.size(); i++) {
			collectNewsBean.setList(list);
			
		}
		System.out.println(gson.toJson(collectNewsBean));
	}
	
	
	@Test
	public void getAllCommentByUrlTest(){
			String sqlString = "SELECT news_comment.zan FROM news_comment WHERE name = 'root' AND newsId ='http://mini.eastday.com/mobile/161010114101555.html?qid=juheshuju' AND lou = 1";
			System.out.println(requestUserInfoDB.getCount(sqlString));
	}
	
	@Test 
	public void getuserZanListTest(){
		List<Integer> list = requestUserInfoDB.getuserZanList("root@outlook.com", "http://mini.eastday.com/mobile/161010170720853.html?qid=juheshuju");
//		System.out.println(new Gson().toJson(list));
		
		String json = new Gson().toJson(list);
		int[] i = new Gson().fromJson(json, int[].class);
		for (int j = 0; j < i.length; j++) {
			System.out.println(i[j]);
		}
		
	}
	
	@Test
	public void getUserCommentListTest(){
		String updateUser = "update news_user set user_headpath='IMG_20161020_214143.jpg' where user_email='lxl@outlook.com';";
        System.out.println(new LinkDb().insertData(updateUser));
	}
	

	
}
