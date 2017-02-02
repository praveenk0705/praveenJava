package fi.pku;

public class testingstring {

	public static void main(String[] args) {
		String dbName = "orcl";
		  String userName = "pku5";
		  String password = "aapd1234";
		  String hostname = "mydb.ck13xpajnawb.us-west-2.rds.amazonaws.com";
		  String port = "1521";
		  //connection = DriverManager.getConnection("jdbc:oracle:thin:@mydb.ck13xpajnawb.us-west-2.rds.amazonaws.com:1521:orcl","pku5","aapd1234");
		  //Connection con = DriverManager.getConnection(jdbcUrl);
		  String jdbcUrl = "\"jdbc:oracle:thin:@" + hostname + ":" + port + ":" + dbName+"\"" +",\""+ userName + "\",\""+password+"\"";
			 
		  System.out.println(jdbcUrl);

	}

}
