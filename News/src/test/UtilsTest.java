package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Utils;

public class UtilsTest {
	private Utils utils;
	@Before
	public void setUp() throws Exception {
		utils = new Utils();
	}

	@Test
	public void testReturnRequestJson() {
		System.out.println(utils.returnRequestJson(1));
	}

}
