package test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

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
	public void MD5Test(){
		
		String a = "liuxiaolong0211";
        String pwd = "long0211";
        System.out.println(utils.MD5(a+pwd));
		
		
	}
	


}
