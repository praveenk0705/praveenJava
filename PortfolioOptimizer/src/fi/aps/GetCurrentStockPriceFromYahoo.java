package fi.aps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentStockPriceFromYahoo {
	public double InrStockPrice(String SYMBOL){

		Date date = new Date(); // your date
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) ;
	    System.out.println(month);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
            System.out.println(day);
            int day1 = day - 6;
		
		String stockname = SYMBOL;
		//System.out.println("Symbol is" + SYMBOL);
			try {
				
				URL url = new URL("http://chart.finance.yahoo.com/table.csv?s=" + stockname + "&a="+month+"&b="+day1+"&c="+year+"&d="+month+"&e="+day+"&f="+year+"&g=d&ignore=.csv");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				//System.out.println(connection);
				InputStreamReader inStream = new InputStreamReader(connection.getInputStream());
		        BufferedReader buff = new BufferedReader(inStream);
		        String cvsSplitBy = ",";
		        String content2 = buff.readLine();
		       // System.out.println(content2);
		        for(int j=0;j<1;j++) {
		            //System.out.println(content2);
		            //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
	               
		            content2 = buff.readLine();
                           // System.out.println(content2);
		            String[] country = content2.split(cvsSplitBy);
                            ///System.out.println(country[1]);
		           System.out.println(country[4]);
		           
		           double value =Double.parseDouble(country[4]) ;
		           return value;
		            ///content2 = buff.readLine();
		        }
			
				
			} catch (IOException ex) {
                            System.out.println("Error");
        }
		
		return 0.0;
	
		
	}

}
