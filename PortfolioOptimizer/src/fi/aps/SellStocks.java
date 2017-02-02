package fi.aps;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


@WebServlet("/SellStocks")
public class SellStocks extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SellStocks() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String getStockName = request.getParameter("stockName");
		String indexName = request.getParameter("indexName");
		String getdatePurchased = request.getParameter("date");
		int getNoOfStocks = Integer.parseInt(request.getParameter("noOfStock"));
		String returnedPrice = null;
		int noOfStocksInDb = 0;
		double moneyToAdd = 0.0;
		double totalStockPriceDb = 0.0;
		double calculateMoneyToDeductFromTransactionTb = 0.0;
		double TOTALLOCALCURRENCYPRICE = 0.0;
		double priceofstock = 0.0;
		double localprice = 0.0;
		double calculateMoneyToDeductFromTransactionTbLocal = 0.0;
		PrintWriter out = response.getWriter();
		
		
		Connection connection = null;
		
		
		ResultSet getNoOfStocksResult = null;
		ResultSet getTotalPriceOfStockJustSoldResult =null;
		
		
		PreparedStatement stSelect = null;
		PreparedStatement updateUserBalanceStmt = null;
		PreparedStatement getNoOfStocksSelect = null;
		PreparedStatement updateTotalPriceInTable = null;
		PreparedStatement sellTransaction = null;
		
		PreparedStatement updateTransactionsNoOfStocks = null;
		PreparedStatement deleteZeroStockNameFromAddToCart= null;
		PreparedStatement deleteZeroStockNameFromTransaction= null;
		PreparedStatement getTotalPriceOfStockJustSold= null;
		PreparedStatement updateTransactionsTotalStockPrice= null;
		
		

		ServletContext context = getServletContext();
		connection = (Connection) context.getAttribute("globalConnection");
		try {
			///////////////////////////////////////////////////////
			getNoOfStocksSelect = connection
					.prepareStatement("select noofstocks, totalprice ,TOTALLOCALCURRENCYPRICE, priceofstock, localprice from addtocart where purchasedate = ? ");
			getNoOfStocksSelect.setString(1, getdatePurchased);
			getNoOfStocksResult = getNoOfStocksSelect.executeQuery();
			while (getNoOfStocksResult.next()) {
				noOfStocksInDb = getNoOfStocksResult.getInt(1);
				totalStockPriceDb = getNoOfStocksResult.getDouble(2);
				TOTALLOCALCURRENCYPRICE = getNoOfStocksResult.getDouble(3);
				priceofstock = getNoOfStocksResult.getDouble(4);
				localprice = getNoOfStocksResult.getDouble(5);

			}
			/////////////////////////////////////////////////////

			calculateMoneyToDeductFromTransactionTb = priceofstock* getNoOfStocks;
			calculateMoneyToDeductFromTransactionTbLocal = localprice* getNoOfStocks;

			System.out.println("money to deduct from db = "
					+ calculateMoneyToDeductFromTransactionTb);

			updateTotalPriceInTable = connection
					.prepareStatement("update addtocart set totalprice = totalprice - ? , TOTALLOCALCURRENCYPRICE= TOTALLOCALCURRENCYPRICE- ? where purchasedate = ? ");
			updateTotalPriceInTable.setDouble(1,
					calculateMoneyToDeductFromTransactionTb);
			updateTotalPriceInTable.setDouble(2,
					calculateMoneyToDeductFromTransactionTbLocal);
			updateTotalPriceInTable.setString(3, getdatePurchased);

			Double currentPrice = 0.0;
			Double LocalCurrentPrice = 0.0;
			
			// calculating the money to add

			if (indexName.equals("Nifty50")) {
				// if stock name is nifty50

				GetCurrentStockPriceFromYahoo currentPrice1 = new GetCurrentStockPriceFromYahoo();
				Double LocalPrice = currentPrice1.InrStockPrice(getStockName
						+ ".BO");
				
				LocalCurrentPrice = LocalPrice;

				// get uds equivalent of inr
				CurrencyConversion currency = new CurrencyConversion();
				Double oneInrInUsd = currency.InrConvertor(getStockName);// will get usd equivalent of inr  here
																		

				// converting to usd
				Double y = oneInrInUsd * LocalPrice;
				currentPrice = y;

				if (y == 0.0) {
					returnedPrice = "zero";
				}
				moneyToAdd = y * getNoOfStocks;
				// out.print(y);

			}

			else if (indexName.equals("Dow30")) {
				// if stock name is dow30

				GetCurrentStockPriceFromYahoo currentPrice1 = new GetCurrentStockPriceFromYahoo();
				Double dPrice = currentPrice1.InrStockPrice(getStockName);
				currentPrice = dPrice;
				LocalCurrentPrice = dPrice;

				if (dPrice == 0.0) {
					returnedPrice = "zero";
				}
				moneyToAdd = dPrice * getNoOfStocks;

			}

			else if (indexName.equals("Straits_Times_Index")) {
				// if stock name is sti

				GetCurrentStockPriceFromYahoo currentPrice1 = new GetCurrentStockPriceFromYahoo();
				Double LocalPrice = currentPrice1.InrStockPrice(getStockName
						+ ".SI");
				LocalCurrentPrice = LocalPrice;

				// get uds equivalent of SGD
				CurrencyConversion currency = new CurrencyConversion();
				Double oneSGDInUsd = currency.SgdConvertor(getStockName);

				// converting to usd
				Double x = oneSGDInUsd * LocalPrice;
				currentPrice = x;

				if (x == 0.0) {
					returnedPrice = "zero";
				}
				moneyToAdd = x * getNoOfStocks;
				// out.print(x);

			}

			else {
				System.out.println("coming to final else");
				out.println("Invalid stock name");
			}

			System.out.println("money to add is " + moneyToAdd);
			
			

			if (returnedPrice != "zero") {

				updateUserBalanceStmt = connection
						.prepareStatement("update person set balance =balance + ? ");
				updateUserBalanceStmt.setDouble(1, moneyToAdd);

				updateUserBalanceStmt.executeQuery();
				connection.commit();

				// ////////////////////

				stSelect = connection.prepareStatement("update addtocart set noofstocks = noofstocks - ? where purchasedate = ?"); //updating addtocart
				stSelect.setInt(1, getNoOfStocks);
				stSelect.setString(2, getdatePurchased);
				
				
				 updateTransactionsNoOfStocks = connection.prepareStatement("update transactions set noofstocks = noofstocks - ? where stockname = ?"); //updating transactions
				updateTransactionsNoOfStocks.setInt(1, getNoOfStocks);
				updateTransactionsNoOfStocks.setString(2, getStockName);
				
				
				
				stSelect.executeQuery();
				updateTransactionsNoOfStocks.executeQuery();
				updateTotalPriceInTable.executeQuery();
				connection.commit(); // COMMIT AFTER UPDATES HAVE BEEN DONE SO THAT WE GET THE LATEST RESULT FOR THE DELETE A COMPLTE ROW OPTION NEXT

				
				 deleteZeroStockNameFromAddToCart = connection
						.prepareStatement("delete from addtocart where noofstocks=0");
				deleteZeroStockNameFromAddToCart.executeQuery();
				
				
				 deleteZeroStockNameFromTransaction = connection
						.prepareStatement("delete from transactions where noofstocks=0");
				deleteZeroStockNameFromTransaction.executeQuery();

				connection.commit(); //commit ..now the addtocart is upto date with the sell option...we must update the total price in transaction based on addtocart
				
				double getTotalPriceOfStockJustSoldValue =0.0;
				//1st get the total price of stock that one just sold
				 getTotalPriceOfStockJustSold = connection
						.prepareStatement("select sum(totalprice) from addtocart where STOCKNAME=?");
				getTotalPriceOfStockJustSold.setString(1, getStockName);
				getTotalPriceOfStockJustSoldResult = getTotalPriceOfStockJustSold.executeQuery();
				while(getTotalPriceOfStockJustSoldResult.next()){
					getTotalPriceOfStockJustSoldValue = getTotalPriceOfStockJustSoldResult.getDouble(1);
					System.out.println("Praveen kuamr ujjwal" + getTotalPriceOfStockJustSoldValue);
				}
				
				//update the transaction table's total purchase price 
				 updateTransactionsTotalStockPrice = connection.prepareStatement("update transactions set totalprice = ? where stockname = ? ");
				updateTransactionsTotalStockPrice.setDouble(1, getTotalPriceOfStockJustSoldValue);
				updateTransactionsTotalStockPrice.setString(2, getStockName);
				updateTransactionsTotalStockPrice.executeQuery();
				
				connection.commit(); //save all the latest data to transaction table
				
				
				
				Date dateobj = new Date();
				String sellingDate = dateobj.toString();
				
				sellTransaction = connection
						.prepareStatement("INSERT INTO selltransactions VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); // addint
																									// to
																									// addtocart
				sellTransaction.setString(1, indexName);
				sellTransaction.setString(2, getStockName);
				sellTransaction.setDouble(3, moneyToAdd);//this is the total money added to the person account
				sellTransaction.setDouble(4, priceofstock); //this is the bought price not selling price
				sellTransaction.setFloat(5, getNoOfStocks); // no of stocks that user sold
				sellTransaction.setString(6, sellingDate); //the current date and time
				
				sellTransaction.setDouble(7, priceofstock*getNoOfStocks); //total usd value when bought(ie when selling more than 1 stock)
				sellTransaction.setDouble(8, localprice); // local price at which the stock was bought
				sellTransaction.setDouble(9, currentPrice); // the price sold at
				sellTransaction.setDouble(10, moneyToAdd - (priceofstock*getNoOfStocks)); 
				sellTransaction.setDouble(11,(localprice*getNoOfStocks));//this is the local price when bought tottal
				sellTransaction.setDouble(12,(LocalCurrentPrice*getNoOfStocks));//this is the local price when sold total(current)
				sellTransaction.setDouble(13, (LocalCurrentPrice*getNoOfStocks) - (localprice*getNoOfStocks));
				sellTransaction.setDouble(14, LocalCurrentPrice);//local price sold at
				sellTransaction.executeQuery();

				connection.commit();

				out.print("true");
			}

			else
				out.print("false");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally{
			try {
				stSelect.close();
				getNoOfStocksResult.close();
				updateUserBalanceStmt.close();
				getNoOfStocksSelect.close();
				updateTotalPriceInTable.close();
				sellTransaction.close();
				updateTransactionsNoOfStocks.close();
				 deleteZeroStockNameFromAddToCart.close();
				 deleteZeroStockNameFromTransaction.close();
				 getTotalPriceOfStockJustSold.close();
				 updateTransactionsTotalStockPrice.close();
				 getTotalPriceOfStockJustSoldResult.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
