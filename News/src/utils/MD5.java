package utils;

import java.security.MessageDigest;

public class MD5 {
	
	
	public static String MD5(String inStr) {
	    MessageDigest md5 = null;
	    try {
	        md5 = MessageDigest.getInstance("MD5");
	    } catch (Exception e) {
	        System.out.println(e.toString());
	        e.printStackTrace();
	        return "";
	    }
	    char[] charArray = inStr.toCharArray();
	    byte[] byteArray = new byte[charArray.length];
	
	    for (int i = 0; i < charArray.length; i++)
	        byteArray[i] = (byte) charArray[i];
	
	    byte[] md5Bytes = md5.digest(byteArray);
	
	    StringBuffer hexValue = new StringBuffer();
	
	    for (int i = 0; i < md5Bytes.length; i++) {
	        int val = ((int) md5Bytes[i]) & 0xff;
	        if (val < 16)
	            hexValue.append("0");
	        hexValue.append(Integer.toHexString(val));
	    }
	
	    return hexValue.toString();
	}
	
	
	public static String KL(String inStr) {
	    // String s = new String(inStr);
	    char[] a = inStr.toCharArray();
	    for (int i = 0; i < a.length; i++) {
	        a[i] = (char) (a[i] ^ 't');
	    }
	    String s = new String(a);
	    return s;
	}
	
	public static String JM(String inStr) {
	    char[] a = inStr.toCharArray();
	    for (int i = 0; i < a.length; i++) {
	        a[i] = (char) (a[i] ^ 't');
	    }
	    String k = new String(a);
	    return k;
	}
	
	
	
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   } 
	
}
