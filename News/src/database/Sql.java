package database;

public class Sql {
	
	public static boolean queryToken(String token){
		
		String querytoken = "select * from token where token='"+token+"'";
		return new LinkDb().queryData(querytoken);
	}
	
	
	
}
