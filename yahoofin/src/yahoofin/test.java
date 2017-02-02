package yahoofin;

import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class test {

	public static void main(String[] args) {
		/*Stock stock = YahooFinance.get("INTC");
		BigDecimal price = stock.getQuote(true).getPrice();
		System.out.println(price);*/

		/*Stock stock = YahooFinance.get("ITC");
		 
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		 
		stock.print();*/
		
		Stock tesla = YahooFinance.get("IBM", true);
		System.out.println(tesla.getHistory());
	}

}
