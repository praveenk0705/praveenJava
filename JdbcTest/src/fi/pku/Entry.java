package fi.pku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Entry {

	public static void main (String args []){
		       
		      // System.out.println("HelloWorld");
    Connection connection=null;
    PreparedStatement stStatement=null;
    ResultSet result=null;

    try
    {
            Class.forName("oracle.jdbc.driver.OracleDriver");
          /*  connection = DriverManager.getConnection("jdbc:oracle:thin:@mydb.ck13xpajnawb.us-west-2.rds.amazonaws.com:1521:orcl",
"pku5","aapd1234");*/
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
            		"praveen","aapd");
            

/*String[] stocksName ={"TATASTEEL","TATAMTRDVR","ZEEL","TATAPOWER",
		"TATAMOTORS","BAJAJ-AUTO","GRASIM","ADANIPORTS","LUPIN","HCLTECH",
		"ITC","AXISBANK","SBIN","AMBUJACEM","INDUSINDBK","LT","COALINDIA",
		"IDEA","BOSCHLTD","TECHM","HDFCBANK","HEROMOTOCO","KOTAKBANK","ICICIBANK",
		"M&M","AUROPHARMA","WIPRO","POWERGRID","HINDALCO","RELIANCE","ACC","SUNPHARMA",
		"ONGC","GAIL","YESBANK","MARUTI","BANKBARODA","HINDUNILVR","ULTRACEMCO","NTPC",
		"DRREDDY","TCS","BHEL","BHARTIARTL","BPCL","CIPLA","INFY","EICHERMOT","HDFC","ASIANPAINT","INFRATEL","AXP",
		"AAPL","BA","CAT","CSCO","CVX","DD","XOM","GE","GS","HD","IBM","INTC","JNJ","KO","JPM","MCD","MMM",
		"MRK","MSFT","NKE","PFE","PG","TRV","UNH","UTX","VZ", "V", "WMT", "DIS","NS8U", "Y92", "A17U", "O39", "BN4", 
		"C6L", "D05", "S58", "C09", "S59", "U11", "MC0", "F34", "C31", "S51", "U96", "C38U", "S68", "S63", "C52", 
		"C07", "BS6", "G13", "T39", "H78", "E5H", "Z74", "U14", "CC3", "C61U"}; 
			
for (int i = 0; i < stocksName.length; i++) {
	stStatement=connection.prepareStatement("insert into STOCK12SEPTVALUE (stockName)values(?)" );
	stStatement.setString(1, stocksName[i]);
    stStatement.executeQuery();
	
}*/
           
            
            
            /*stStatement=connection.prepareStatement("select * from tab");
            result=stStatement.executeQuery();
            while(result.next()){
                    System.out.println(result.getString(1));
            }
            System.out.println("success");*/
            
            
            
            
            
			String[] Nifty50 = { "TATASTEEL", "TATAMTRDVR", "ZEEL",
					"TATAPOWER", "TATAMOTORS", "BAJAJ-AUTO", "GRASIM",
					"ADANIPORTS", "LUPIN", "HCLTECH", "ITC", "AXISBANK",
					"SBIN", "AMBUJACEM", "INDUSINDBK", "LT", "COALINDIA",
					"IDEA", "BOSCHLTD", "TECHM", "HDFCBANK", "HEROMOTOCO",
					"KOTAKBANK", "ICICIBANK", "M&M", "AUROPHARMA", "WIPRO",
					"POWERGRID", "HINDALCO", "RELIANCE", "ACC", "SUNPHARMA",
					"ONGC", "GAIL", "YESBANK", "MARUTI", "BANKBARODA",
					"HINDUNILVR", "ULTRACEMCO", "NTPC", "DRREDDY", "TCS",
					"BHEL", "BHARTIARTL", "BPCL", "CIPLA", "INFY", "EICHERMOT",
					"HDFC", "ASIANPAINT", "INFRATEL" };

			String[] Dow30 = { "AXP", "AAPL", "BA", "CAT", "CSCO", "CVX", "DD",
					"XOM", "GE", "GS", "HD", "IBM", "INTC", "JNJ", "KO", "JPM",
					"MCD", "MMM", "MRK", "MSFT", "NKE", "PFE", "PG", "TRV",
					"UNH", "UTX", "VZ", "V", "WMT", "DIS" };

			String[] Straits_Times_Index = { "NS8U", "Y92", "A17U", "O39",
					"BN4", "C6L", "D05", "S58", "C09", "S59", "U11", "MC0",
					"F34", "C31", "S51", "U96", "C38U", "S68", "S63", "C52",
					"C07", "BS6", "G13", "T39", "H78", "E5H", "Z74", "U14",
					"CC3", "C61U" };
			
			/* aa_stockmarkets_api aa =  new aa_stockmarkets_api();
		        Double x= aa.Singapore("S58");
		        Double y = aa.Indian("COLPAL");
		        System.out.println(x);
		        System.out.println(y);*/

			SeptemberPrices bb = new SeptemberPrices();
			SeptemberYahoo cc = new SeptemberYahoo();
	        Double z2;
	       
			Double z1;
			String n;
			/*
			 * Double z = bb.IndianCurrency("ASHOKLEY"); System.out.println(z);
			 */
			for (int j = 0; j < Dow30.length; j++) {
				//z1 = bb.SingaporeCurrency(Dow30[j]);
				z2 = cc.USASeptember(Dow30[j]);
				n = z2.toString();
				System.out.println(n);
				stStatement = connection
						.prepareStatement("update STOCK12SEPTVALUE set stockprice = ? where stockname = ? ");
				stStatement.setString(1, n);
				stStatement.setString(2, Dow30[j]);
				stStatement.executeQuery();
				
				/*System.out.println(Straits_Times_Index[j]);*/
				Thread.sleep(15000);
			}
		       
		        /*SeptemberYahoo cc = new SeptemberYahoo();
		        Double z2 = cc.USASeptember("AXP");
		        System.out.println(z2);*/

    }
    catch (ClassNotFoundException e)
    {
            e.printStackTrace();
    }
    catch (SQLException e)
    {
            e.printStackTrace();
            //log("Datdabase connection failed " ,e);
    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    finally
    {
            try
            {
                    if(result!=null)
                            result.close();
                    if(connection!=null)
                            connection.close();
                    if(stStatement!=null)
                            stStatement.close();
            }
            catch (SQLException e)
            {
                    e.printStackTrace();
            }
    }

		  }
}