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
try 
	{
	ServletContext context = getServletContext();
	connection = (Connection)context.getAttribute("globalConnection");
	stSelect = connection.prepareStatement("select * from transactions ");
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
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
       <th>Stock Index</th>
        <th>Stock Name</th>
        <th>Total Price when Bought</th>
      <th>Total No of Stocks Bought</th>
      <th>Current Value of Stocks</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <%
String sname;
Double i = 0.0;
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
				i = i + total;
				/* //PrintWriter out = response.getWriter(); */
				out.println(total);
            	
            }
            else if (StockIndex.equals("Dow30")) {
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
    			Double LocalPrice=	currentPrice.InrStockPrice(tickerName);
    			int no = Integer.parseInt(result.getString(4));
				Double total = no * LocalPrice;
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
				i = i + total;
				/* //PrintWriter out = response.getWriter(); */
				out.println(total);
            	
            }
            
           
            %>
             
            </td>
           
        
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
     </tr> 
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