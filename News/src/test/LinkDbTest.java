package test;

import static org.junit.Assert.*;

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
	public void testLink() {
		linkDb.link();
	
	}
	@Test
	public void findUserEmailExistTest(){
		String sql = "select user_email from news_user where user_email='lxl970211@outlook.com'";
		System.out.println(linkDb.findUserEmailExist(sql));
		
	}
	
	@Test
	public void loginUserTest(){
		System.out.println(linkDb.loginUser("long@outlook.com", "5fec3afb828aeda2e7d82d8fa449c03b"));
	}
	


}
