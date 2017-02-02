<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
	stSelect = connection.prepareStatement("select * from buyhistory ");
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
		 if(result!= null)
		result.close();
		 if(stSelect!= null)
		 stSelect.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}  */

%>

<section>
  
  <h1>Purchase History</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
       <th>Stock Index</th>
        <th>Stock Name</th>
        <th>Total Price When Purchased(USD)</th>
        <th>Stock Price When Purchased(USD)</th>
        <th>No of Stocks Purchased</th>
        <th>Purchase Transaction Date </th>
         <th>TotalPrice when purchased(Local)</th>
        <th>Stock Price when purchased(Local) </th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <%
			while (result.next()) {
		%>
        <tr>
             <td><%=result.getString(1) %></td>
            <td><%=result.getString(2) %></td>
            <td><%=result.getString(3) %></td>
            <td><%=result.getString(4) %></td>
            <td><%=result.getString(5) %></td>
            <td><%=result.getString(6) %></td>
            <td><%=result.getString(7) %></td>
            <td><%=result.getString(8) %></td>
        </tr>
        <%
    }
    result.close();
    %>
        
      </tbody>
    </table>
  </div>
</section>



</body>


<script type="text/javascript">
$(window).on("load resize ", function() {
	  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
	  $('.tbl-header').css({'padding-right':scrollWidth});
	}).resize();
</script>
</html>