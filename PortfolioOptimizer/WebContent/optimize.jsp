<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    <%@page import="java.sql.*"%>
	<%@page import="java.util.*" %>
	<%@page import="fi.aps.CurrencyConversion" %>
	<%@page import="fi.aps.GetCurrentStockPriceFromYahoo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/buyHistory.css" />
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Connection connection = null;
ResultSet result = null;
PreparedStatement stSelect = null; 

ResultSet resultgetbeta = null;
PreparedStatement stSelectgetbeta = null;

ResultSet resultupdatera = null;
PreparedStatement stSelectupdatera = null;


ResultSet resultinvested = null;
PreparedStatement stSelecttotalinvested = null;

ResultSet getTotal = null;
PreparedStatement stSelectgetTotal = null;

try 
	{
	ServletContext context = getServletContext();
	connection = (Connection)context.getAttribute("globalConnection");
	stSelect = connection.prepareStatement("select * from transactions order by erpd desc");
	result = stSelect.executeQuery();
	
	
	
	
	

	
	
	
	/* System.out.println(result);
	while(result.next()){
		System.out.println(result.getString(1));
		
	}  */
		
	}

	catch(NullPointerException e)
	{
		System.out.println("Database Connection Not Established");
	}

%>

    
    <section>
  
  <h1>Summary</h1>
  
    <table cellpadding="0" cellspacing="0" border="1">
      <thead>
        <tr>
       <th>Stock Index</th>
        <th>Stock Name</th>
        <th>Total Price when Bought</th>
      <th>Total No of Stocks Bought</th>
      <th>Current Value of Stocks</th>
      <th>Expected Return per Dollar</th>
      <th>Percentage</th>
        </tr>
      </thead>
    
  </div>
  
      <%
      
      
      
String sname;
Double i = 0.0;
int z = 0 ;
Double totalInperson = 0.0;
Double percent  = 0.0;

stSelectgetTotal = connection.prepareStatement("select totalstockvalue from person");
getTotal = stSelectgetTotal.executeQuery();

while(getTotal.next()){
	totalInperson = getTotal.getDouble(1);
}

    while(result.next()){
        %>
        <tr>
            <td><%=result.getString(1) %></td>
            <td><%=result.getString(2) %></td>
            <td><%=result.getString(3) %></td>
            <td><%=result.getString(4) %></td>
            <td>
            
            <% String StockIndex =  result.getString(1);
            	String tickerName = result.getString(2);
            if (StockIndex.equals("Nifty50")) {
            	
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
    			Double LocalPrice=	currentPrice.InrStockPrice(tickerName + ".BO");
    			CurrencyConversion currency = new CurrencyConversion();
				Double oneInrInUsd = currency.InrConvertor(tickerName);

				Double y = oneInrInUsd * LocalPrice;
				int no = Integer.parseInt(result.getString(4));
				Double total = no * y;
				percent = (total/totalInperson)*100;
				i = i + total;
				/* //PrintWriter out = response.getWriter(); */
				out.println(total);
            	
            }
            else if (StockIndex.equals("Dow30")) {
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
    			Double LocalPrice=	currentPrice.InrStockPrice(tickerName);
    			int no = Integer.parseInt(result.getString(4));
				Double total = no * LocalPrice;
				percent = (total/totalInperson)*100;
				i = i + total;
				out.println(total);
            	
            }
            else {
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
				Double LocalPrice=	currentPrice.InrStockPrice(tickerName + ".SI");
				
				//get uds equivalent of SGD
				CurrencyConversion currency = new CurrencyConversion();
				Double oneSGDInUsd = currency.SgdConvertor(tickerName);//will have usd equivalent of inr here
				
				//converting to usd
				Double x = oneSGDInUsd * LocalPrice;
				int no = Integer.parseInt(result.getString(4));
				Double total = no * x;
				percent = (total/totalInperson)*100;
				i = i + total;
				/* //PrintWriter out = response.getWriter(); */
				out.println(total);
            	
            }
            
           
            %>
             
            </td>
            

  
            <td>
            <%
            Double s = 0.00;
            Double fiftyavg = 0.00;
            String fifty = null;
            Double ra = 0.00;
            String StockIndex1 =  result.getString(1);
        	String tickerName1 = result.getString(2);
        	Double profitperdollor = 0.00;
       
        	if (StockIndex1.equals("Nifty50")) { 
        	GetCurrentStockPriceFromYahoo currentPrice1 = new GetCurrentStockPriceFromYahoo();
			Double LocalPrice1=	currentPrice1.InrStockPrice(tickerName1 + ".BO");
			CurrencyConversion currency1 = new CurrencyConversion();
			Double oneInrInUsd1 = currency1.InrConvertor(tickerName);
			Double y1 = oneInrInUsd1 * LocalPrice1; // this is the current price for nifty stock
        	//out.println(y1);
					
					// now get beta
					
			stSelectgetbeta = connection.prepareStatement("select * from betanifty50 where stockname = ? ");
			stSelectgetbeta.setString(1, tickerName1);
			resultgetbeta = stSelectgetbeta.executeQuery();
			while (resultgetbeta.next()){
				fifty = resultgetbeta.getString(3);//this is the fifyavg value 
				fiftyavg = Double.parseDouble(fifty);
				s = resultgetbeta.getDouble(2); //this is the beta value
			}
			// no calculate ra
			
			ra = ((1 - s) * (fiftyavg - y1))/y1 ;
			
			out.println(ra);
				
			;	
			stSelectupdatera = connection.prepareStatement("update transactions set erpd = ? where stockname = ?");
			stSelectupdatera.setDouble(1, ra);
			stSelectupdatera.setString(2, tickerName1);
			stSelectupdatera.executeQuery();
			connection.commit();
        
        }
        	
        	
        	else if (StockIndex1.equals("Dow30")) { 
            	GetCurrentStockPriceFromYahoo currentPrice1 = new GetCurrentStockPriceFromYahoo();
    			Double y1=	currentPrice1.InrStockPrice(tickerName1);
    			/* CurrencyConversion currency1 = new CurrencyConversion();
    			Double oneInrInUsd1 = currency1.InrConvertor(tickerName);
    			Double y1 = oneInrInUsd1 * LocalPrice1; */ // this is the current price for nifty stock
            	//out.println(y1);
    					
    					// now get beta
    					
    			stSelectgetbeta = connection.prepareStatement("select * from betadow30 where stockname = ? ");
    			stSelectgetbeta.setString(1, tickerName1);
    			resultgetbeta = stSelectgetbeta.executeQuery();
    			while (resultgetbeta.next()){
    				fifty = resultgetbeta.getString(3);//this is the fifyavg value 
    				fiftyavg = Double.parseDouble(fifty);
    				s = resultgetbeta.getDouble(2); //this is the beta value
    			}
    			// no calculate ra
    			
    			ra = ((1 - s) * (fiftyavg - y1))/y1 ;
    			
    			out.println(ra);
    				
    				
    			stSelectupdatera = connection.prepareStatement("update transactions set erpd = ? where stockname = ?");
    			stSelectupdatera.setDouble(1, ra);
    			stSelectupdatera.setString(2, tickerName1);
    			stSelectupdatera.executeQuery();
    			connection.commit();
            
            } 
        	
        	
        	
        	else { 
            	GetCurrentStockPriceFromYahoo currentPrice1 = new GetCurrentStockPriceFromYahoo();
    			Double LocalPrice1=	currentPrice1.InrStockPrice(tickerName1 + ".SI");
    			CurrencyConversion currency1 = new CurrencyConversion();
    			Double oneInrInUsd1 = currency1.SgdConvertor(tickerName);
    			Double y1 = oneInrInUsd1 * LocalPrice1; // this is the current price for nifty stock
            	//out.println(y1);
    					
    					// now get beta
    					
    			stSelectgetbeta = connection.prepareStatement("select * from betasti where stockname = ? ");
    			stSelectgetbeta.setString(1, tickerName1);
    			resultgetbeta = stSelectgetbeta.executeQuery();
    			while (resultgetbeta.next()){
    				fifty = resultgetbeta.getString(3);//this is the fifyavg value 
    				fiftyavg = Double.parseDouble(fifty);
    				s = resultgetbeta.getDouble(2); //this is the beta value
    			}
    			// no calculate ra
    			
    			ra = ((1 - s) * (fiftyavg - y1))/y1 ;
    			
    			out.println(ra);
    				
    			stSelectupdatera = connection.prepareStatement("update transactions set erpd = ? where stockname = ?");
    			stSelectupdatera.setDouble(1, ra);
    			stSelectupdatera.setString(2, tickerName1);
    			stSelectupdatera.executeQuery();
    			connection.commit();	
            
            }
        	
        	
        %>


</td>
            
 
    
    <td><%
    out.println(percent);
    %></td>
        
        </tr>
        
        
        
        
        <%
    }
    %>
    <tr>
    <td><h4>Total value of all the stocks(Current)</h4>
    
     <%
     out.println(i);	
     %>
     </td>
      
     
      
    <td><h4>Total invested</h4>
    
     <%
     String invested = null;
     Double invs = 0.0;
     Double profit = 0.0;
 /*     ResultSet resultinvested = null;
     PreparedStatement stSelecttotalinvested = null; */
     stSelecttotalinvested = connection.prepareStatement("select sum (totalprice) from transactions");
     resultinvested = stSelecttotalinvested.executeQuery();
     while (resultinvested.next())
    	 invested = resultinvested.getString(1);
     invs = Double.parseDouble(invested);
     out.println(invs);
     %>
     </td>
    
     
     
    <td><h4>Profit/loss</h4>
    
    <% profit = i - invs ;
    out.println(profit);
    %>
    
    </td> 
    </tr>
    
    
    <h3>Top 3 stocks: If percentage is more than 20% dont buy or sell. If percentage is less than 20% Bring percentage close to 20%</h3>

<h3>4th and 5th stocks: Bring percentage close to 10%</h3>

<h3>6th and 7th stocks: Bring percentage close to 7.5%</h3>

<h3>8th , 9th and 10th Stocks: Bring sum of percentage close to 5%</h3>

    
    
     <%
    result.close();
    %>
        
      </tbody>
    </table>
  </div>
</section>
    
    
    
    
    </table>
</body>
</html>