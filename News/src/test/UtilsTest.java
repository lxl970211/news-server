package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

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
		String aString = "/News";
		System.out.println(aString.replace("/", ""));
       
	}
	
	@Test
	public void collectNewsList(){
		
		
		
	}

}
