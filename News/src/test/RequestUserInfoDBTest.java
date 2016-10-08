package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javabean.CollectNewsBean;
import javabean.CollectNewsData;
import javabean.Comment;

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
			String urlString = "http://mini.eastday.com/mobile/16100815552.html?qid=juheshuju";
			System.out.println(requestUserInfoDB.getCommentCountByUrl(urlString));
			int i = 0;
			int a = 0;
			System.out.println(i++);
			System.out.println(++a);
			System.out.println(i);
	}

}
