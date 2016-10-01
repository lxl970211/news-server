package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Utils;

import database.LinkDb;

public class LinkDbTest {
	private LinkDb linkDb;
	@Before
	public void setUp() throws Exception {
		linkDb = new LinkDb();
	}


	
	@Test
	public void insertDataTest(){
		String name = "¡ı–°¡˙";
		String pwd = "liuxiaolong0211";
		String email = "asklong073324@outlook.com"; 
		String sql = "insert into news_user(user_name, user_email, user_password) values('"+name+"', '"+email+"', '"+pwd+"');";
		
		assertEquals(true, linkDb.insertData(sql));
		
	}
	@Test
	public void imageTest(){
		
	
		File file = new File("E:\\databasesData\\bing.jpg");
	}


}
