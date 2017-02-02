<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
    <%@page import="java.io.PrintWriter"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Connection connection = null;
ResultSet result = null;

try 
{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@prophet.njit.edu:1521:course";
	String user = "pku5";
	String password = "KgptDqe0D" ;
	System.out.println(driver+dbUrl+user+password);
	
	Class.forName(driver);
	connection = DriverManager.getConnection(dbUrl, user, password);
	if(connection!= null){
		System.out.println("made connection through doget");
		out.println("made connection through doget");
		connection.setAutoCommit(false);
	}
	if(connection==null)
	{
		System.out.println("Connection Failed");
		return;
	}
	ServletContext context = getServletContext();
	context.setAttribute("globalConnection", connection);
} 

catch (ClassNotFoundException e) 
{
	e.printStackTrace();
} 
catch (SQLException e) 
{
	e.printStackTrace();
}





    %>
    
</body>
</html>