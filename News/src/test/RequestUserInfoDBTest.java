package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}
