package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Utils;

import database.LinkDb;
import database.RequestUserInfoDB;

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
	
	@Test
	public void deletedataTest(){
		String user_email = new RequestUserInfoDB().queryUserEmail("Moto G 2014 LTEf8:cf:c5:df:44:bc5bb7d523170017ec97e052ebce5684b9");
		String sqlString = "delete from news_user_collectnews where user_email='"+user_email+"' and url='http://mini.eastday.com/mobile/161001194103288.html?qid=juheshuju';";
		System.out.println(linkDb.deleteData(sqlString));
	}


}
