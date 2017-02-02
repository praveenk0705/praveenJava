package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Servlet implementation class GetPrice
 */
@WebServlet("/GetPrice")
public class GetPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPrice() {
		super();

	}


	/*public JSONObject jsonReturnTwelvePrice(float[] val) {
		JSONObject json = new JSONObject();
		try {
			json.put("price", val[0]);
			json.put("Localprice", val[1]);
			json.put("message", "Buying on Twelveth price");
			System.out.println(json);
			// out.println(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// json.put("txt22", txt2);

		return json;

	}
*/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String indexNames[] = { "Nifty50", "Dow30", "Straits_Times_Index" };
		PrintWriter out = response.getWriter();
		System.out.println("Inside Get price from yahoo");
		String stockName = request.getParameter("name");
		String indexName = request.getParameter("indexName");
		System.out.println(indexName);
		Connection connection = null;
		ServletContext context = getServletContext();
		connection = (Connection) context.getAttribute("globalConnection");
		String stockAlreadyPurchased = null;
		JSONObject json = new JSONObject();

		PreparedStatement checkIfStockAlreadyPurchasedSelect = null;
		PreparedStatement getTwelvethPrice = null;
		ResultSet getTwelvethPriceResult = null;
		ResultSet checkIfStockAlreadyPurchasedResult = null;

		try {
			checkIfStockAlreadyPurchasedSelect = connection
					.prepareStatement("select stockname from buyhistory where stockname = ?");
			checkIfStockAlreadyPurchasedSelect.setString(1, stockName);
			checkIfStockAlreadyPurchasedResult = checkIfStockAlreadyPurchasedSelect
					.executeQuery();
			if (checkIfStockAlreadyPurchasedResult.next())
				stockAlreadyPurchased = "true";
			else
				stockAlreadyPurchased = "false";
			System.out.println(stockAlreadyPurchased);

			

			/*
			 * this code is working don't delete try { json.put("txt11", n);
			 * json.put("txt21", "achal"); System.out.println(json);
			 * //out.println(json); } catch (JSONException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * //json.put("txt22", txt2);
			 * response.setContentType("application/json");
			 * response.getWriter().write(json.toString());
			 */

			if (indexName.equals("Nifty50")) {
				// if stock name is nifty50
				if (stockAlreadyPurchased == "true") {
					// get current price from nifty function
					/*aa_stockmarkets_api aa = new aa_stockmarkets_api();
					Double y = aa.Indian(stockName);*/
					// out.print(y);
					
					//get current inr value of the stocks
					GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
				Double LocalPrice=	currentPrice.InrStockPrice(stockName + ".BO");
					
					
					//get uds equivalent of inr
					CurrencyConversion currency = new CurrencyConversion();
					Double oneInrInUsd = currency.InrConvertor(stockName);//will have usd equivalent of inr here
					
					//converting to usd
					Double y = oneInrInUsd * LocalPrice;
					
					try {
						json.put("price", y);
						json.put("Localprice", LocalPrice);
						json.put("message", "Buying on Current price");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					//json = jsonReturnCurrentPrice(y);
					response.setContentType("application/json");
					response.getWriter().write(json.toString());

				} else {
					// get 12th sept price from nifty function
					getTwelvethPrice = connection
							.prepareStatement("select usdvalue, inrvalue from NIFTY5012THPRICE where stockname = ?");
					
					getTwelvethPrice.setString(1, stockName);
					getTwelvethPriceResult= getTwelvethPrice.executeQuery();
					float z = 0;
					float localValue =0;
					while(getTwelvethPriceResult.next()){
					 z= getTwelvethPriceResult.getFloat("usdvalue");
					 localValue = getTwelvethPriceResult.getFloat("inrvalue");
					
						try {
							json.put("price", z);
							json.put("Localprice", localValue);
							json.put("message", "Buying on Twelveth price");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					response.setContentType("application/json");
					response.getWriter().write(json.toString());
				
				}

			}
			
			////////////////////////////////////////////////////////////////////////////////////////////////////

			else if (indexName.equals("Dow30")) {
				// if stock name is dow30
				if (stockAlreadyPurchased == "true") {
					// get current price from dow30 function
					/*Stock stock = YahooFinance.get(stockName);
					BigDecimal price = stock.getQuote(true).getPrice();
					double dPrice = price.doubleValue();*/
					
					GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
					Double dPrice=	currentPrice.InrStockPrice(stockName);
					
					//get uds equivalent of SGD
					/*CurrencyConversion currency = new CurrencyConversion();
					Double oneSGDInUsd = currency.SgdConvertor(stockName);//will have usd equivalent of inr here
					
					//converting to usd
					Double x = oneSGDInUsd * LocalPrice;*/
					
					
					try {
						json.put("price", dPrice);
						json.put("Localprice", dPrice);
						json.put("message", "Buying on current price");
					} catch (JSONException e) {
						e.printStackTrace();
					}

					//json = jsonReturnCurrentPrice(dPrice);
					response.setContentType("application/json");
					response.getWriter().write(json.toString());
					// out.print(price);

				} else {
					// get 12th sept price from dow30 function
					getTwelvethPrice = connection
							.prepareStatement("select usdvalue from dow3012THPRICE where stockname = ?");
					
					getTwelvethPrice.setString(1, stockName);
					getTwelvethPriceResult= getTwelvethPrice.executeQuery();
					float z = 0;
					float localValue =0;
					while(getTwelvethPriceResult.next()){
					 z= getTwelvethPriceResult.getFloat("usdvalue");
					 //localValue = getTwelvethPriceResult.getFloat("inrvalue");
					
						try {
							json.put("price", z);
							json.put("Localprice", z);
							json.put("message", "Buying on Twelveth price");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					response.setContentType("application/json");
					response.getWriter().write(json.toString());
				}

			}

			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else if (indexName.equals("Straits_Times_Index")) {
				// if stock name is sti
				if (stockAlreadyPurchased == "true") {
					// get current price from sti function
					
					/*aa_stockmarkets_api aa = new aa_stockmarkets_api();
					Double x = aa.Singapore(stockName);*/
					
					//get current SGD value of the stocks
					GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
					Double LocalPrice=	currentPrice.InrStockPrice(stockName + ".SI");
					
					//get uds equivalent of SGD
					CurrencyConversion currency = new CurrencyConversion();
					Double oneSGDInUsd = currency.SgdConvertor(stockName);//will have usd equivalent of inr here
					
					//converting to usd
					Double x = oneSGDInUsd * LocalPrice;
					
					
					try {
						json.put("price", x);
						json.put("Localprice", LocalPrice);
						json.put("message", "Buying on Current price");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					//json = jsonReturnCurrentPrice(x);
					response.setContentType("application/json");
					response.getWriter().write(json.toString());
					// out.print(x);

				} else {
					// get 9th sept price from sti-0 function
					getTwelvethPrice = connection
							.prepareStatement("select usdvalue, sgdvalue from sti12THPRICE where stockname = ?");
					
					getTwelvethPrice.setString(1, stockName);
					getTwelvethPriceResult= getTwelvethPrice.executeQuery();
					float z = 0;
					float localValue =0;
					while(getTwelvethPriceResult.next()){
					 z= getTwelvethPriceResult.getFloat("usdvalue");
					 localValue = getTwelvethPriceResult.getFloat("sgdvalue");
					
						try {
							json.put("price", z);
							json.put("Localprice", localValue);
							json.put("message", "Buying on Twelveth price");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					response.setContentType("application/json");
					response.getWriter().write(json.toString());
				}

			}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else {
				System.out.println("coming to final else");
				out.println("Invalid stock name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			 try {
				 if(checkIfStockAlreadyPurchasedSelect != null)
				checkIfStockAlreadyPurchasedSelect.close();
				 if(getTwelvethPrice != null)
				 getTwelvethPrice.close();
				 if(getTwelvethPriceResult != null)
				 getTwelvethPriceResult.close();
				 if(checkIfStockAlreadyPurchasedResult != null)
				 checkIfStockAlreadyPurchasedResult.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * aa_stockmarkets_api aa = new aa_stockmarkets_api(); Double x=
		 * aa.Singapore("S58"); Double y = aa.Indian("COLPAL");
		 * System.out.println(x); System.out.println(y);
		 * 
		 * SeptemberPrices bb= new SeptemberPrices(); Double z =
		 * bb.IndianCurrency("ASHOKLEY"); System.out.println(z); Double z1 =
		 * bb.SingaporeCurrency("S58"); System.out.println(z1); SeptemberYahoo
		 * cc = new SeptemberYahoo(); Double z2 = cc.USASeptember("AXP");
		 * System.out.println(z2);
		 */

	}

}
