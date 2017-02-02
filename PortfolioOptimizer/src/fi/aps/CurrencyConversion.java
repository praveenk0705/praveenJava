package fi.aps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CurrencyConversion {
	public double InrConvertor(String SYMBOL){
		
			try {
				URL BaseURL10 = new URL("http://api.fixer.io/latest?base=INR&symbols=USD");
				URLConnection APIConnection10 = BaseURL10.openConnection();
				
				
				InputStreamReader is10 = new InputStreamReader(APIConnection10.getInputStream());
				BufferedReader br10 = new BufferedReader(is10);
				
				String Value10= br10.readLine();
				
				String[] part10=Value10.split("\"USD\":");
				String Value20= part10[1];
				String[] part11=Value20.split("}");
				
				double FinalConversion= Double.parseDouble(part11[0]);
				System.out.println(FinalConversion);
				return FinalConversion;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0.00;
		
		
		
	}
	
	public double SgdConvertor(String SYMBOL){
		
		try {
			URL BaseURL10 = new URL("http://api.fixer.io/latest?base=SGD&symbols=USD");
			URLConnection APIConnection10 = BaseURL10.openConnection();
			
			
			InputStreamReader is10 = new InputStreamReader(APIConnection10.getInputStream());
			BufferedReader br10 = new BufferedReader(is10);
			
			String Value10= br10.readLine();
			
			String[] part10=Value10.split("\"USD\":");
			String Value20= part10[1];
			String[] part11=Value20.split("}");
			
			double FinalConversion= Double.parseDouble(part11[0]);
			System.out.println(FinalConversion);
			return FinalConversion;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0.00;
	
	
	
}

	

}
