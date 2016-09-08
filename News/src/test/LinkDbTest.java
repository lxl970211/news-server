package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
	


}
