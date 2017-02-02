<%@page import="java.sql.ResultSet"%>
<%@page import="com.sun.corba.se.spi.orbutil.fsm.Guard.Result"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Free Hotel Website Template - designed by TemplateMonster</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="This is a wonderful homepage of the Free Hotel Website Template provided by TemplateMonster."/>
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="layout.css" rel="stylesheet" type="text/css" />
<script src="maxheight.js" type="text/javascript"></script>

</head>
<body id="page1" onload="new ElementMaxHeight();">
<div id="main">
<!-- header -->
	<div id="header">
		<div class="row-1">
			<div class="wrapper">
				<div class="logo">
					<h1><a href="Home.jsp">Five Star</a></h1>
					<em>Hotel</em>
					<strong>True Luxury</strong>
				</div>
				<div class="phones">
					+91-020-25459312<br />
					+91-020-27652794
				</div>
			</div>
		</div>
		<div class="row-2">
	 		<div class="indent">
<!-- header-box begin -->
				<div class="header-box">
					<div class="inner">
						
					</div>
				</div>
<!-- header-box end -->
			</div>
		</div>
	</div>
<!-- content -->
	<div id="content">
		<div class="wrapper">
			<div class="aside maxheight">
<!-- box begin -->
				<div class="box maxheight">
					<div class="inner">
					
					
					
						<h3>Admin:</h3>
					 		<fieldset>
					 		<br/>
					 		<br/>
								<div class="field"><font size="3"><a href="roomModify.jsp">Modify Room</a></font></div><br/><br/>
								<div class="field"><font size="3"><a href="serviceModify.jsp">Modify Services</a></font></div>	
								<br/>
								<br/><br/><br/>
							</fieldset>						
					</div>
				</div>
<!-- box end -->
			</div>
			<div class="content">
			
			
					<%
					Connection connection=null; 
					PreparedStatement stSelect=null;
					
					String fname;
					
					ServletContext cxt=getServletContext();
					connection = (Connection) cxt.getAttribute("global");
					
					stSelect=connection.prepareStatement("select firstname from registration where username=?");
					
								if(session!=null)
								{
									String user = (String)(session.getAttribute("userName"));
							if(user!=null)
							{
								stSelect.setString(1,user);
								ResultSet result=stSelect.executeQuery();
								if(result.next())
								{
									 fname=result.getString(1);
								}
								System.out.println("true");
							%>	
							<br/><h4><b><font size="4" color="maroon">Welcome <%=result.getString(1) %></font></b></h4>						
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
						<a href ="SignOut" ><font color="maroon" size="3">Sign Out</font></a>
						<%
						    }
						       }
						%> 
			
				</div>
			</div>
		</div>
	</div>
</body>
</html>