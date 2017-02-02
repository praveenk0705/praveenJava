<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <%@page import="java.sql.*"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/sellHistory.css" />

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
	stSelect = connection.prepareStatement("select * from selltransactions ");
	result = stSelect.executeQuery();
	/* System.out.println(result);
	while(result.next()){
		System.out.println(result.getString(1));
		
	}  */
		
	}
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	catch(NullPointerException e)
	{
		System.out.println("Database Connection Not Established");
	}
/* finally{
	 try {
		result.close();
		 stSelect.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} */

%>
<div class="caption">Selling History</div>	
<div id="table">
	<div class="header-row row">
    <span class="cell primary">Stock Index</span>
    <span class="cell">Stock Name</span>
     <span class="cell">Selling Date</span>
    <span class="cell">No of Stocks Sold</span>
    <span class="cell">PriceWhenBought USD</span>
    <span class="cell">Price Sold At USD</span>
    <span class="cell">Price When Bought Local</span>
    <span class="cell">Price Sold At Local</span>
    <span class="cell">Total Price When Bought(USD)</span>
    <span class="cell">Total Price when Sold USD</span>
    <span class="cell">Total Price When Bought(Local)</span>
    <span class="cell">Total Price when Sold Local</span>
    <span class="cell">Profit/Loss $</span>
    <span class="cell">Profit/Loss(Local)</span>
  </div>
  
  <%
			while (result.next()) {
		%>
  
  <div class="row">
	
    <span class="cell primary" ><%=result.getString(1)%></span>
    <span class="cell primary" ><%=result.getString(2)%></span>
    <span class="cell primary" ><%=result.getString(6)%></span>
    <span class="cell primary" ><%=result.getString(5)%></span>
    <span class="cell primary" ><%=result.getString(4)%></span>
    <span class="cell primary" ><%=result.getString(9)%></span>
    <span class="cell primary" ><%=result.getString(8)%></span>
    <span class="cell primary" ><%=result.getString(14)%></span>
    <span class="cell primary" ><%=result.getString(7)%></span>
    <span class="cell primary" ><%=result.getString(3)%></span>
    <span class="cell primary" ><%=result.getString(11)%></span>
    <span class="cell primary" ><%=result.getString(12)%></span>
    <span class="cell primary" ><%=result.getString(10)%></span>
    <span class="cell primary" ><%=result.getString(13)%></span>
    
    
  </div>
 
 <%
    }
    result.close();
    %>


</body>
</html>