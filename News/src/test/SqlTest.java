package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import database.Sql;

public class SqlTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryToken() {
		System.out.println(Sql.queryToken("Android SDK built for x86null9cedf6ada8c6cf9cd86d6c2b1072537e"));
	}

}
