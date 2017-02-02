<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
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
<!--[if lt IE 7]>
	<link href="ie_style.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="ie_png.js"></script>
   <script type="text/javascript">
	   ie_png.fix('.png, #header .row-2, #header .nav li a, #content, .gallery li');
   </script>
<![endif]-->
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
						<ul class="nav">
					 		<li><a href="Home.jsp" class="current">Home page</a></li>
							<li><a href="ServiceInfo.jsp">Services</a></li>
							<li><a href="gallery.jsp">Gallery</a></li>
							<li><a href="booking.jsp">Booking</a></li>							
							<li><a href="AboutUs.jsp">AboutUs</a></li>
							<li><a href="Feedback.">Feedback</a></li>
						</ul>
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
				<div class="">
					<div class="inner" style="height: 8px; ">
					
					
					<form action="">
						<table border="1" cellspacing="1" cellpadding="1">
						   <tr>
						    <th>BookingID</th>
						     <th>Check In Date</th>
						     <th>Check Out Date </th>
						     <th> Total Charges </th>
						     </tr>
						     <%
						     
						     Connection connection=null; 
							PreparedStatement stSelect=null;
							PreparedStatement stSelect1=null;
								
								String fname;
								
								ServletContext cxt=getServletContext();
								connection = (Connection) cxt.getAttribute("global");
								String uName=(String)session.getAttribute("userName");
								System.out.println(uName);
						     
						       stSelect1=connection.prepareStatement("select bookingid ,checkInDate,checkOutDate,totalcharges from room_booking where username=? and checkInDate > sysdate");
						       
						       
						       stSelect1.setString(1,uName);
						       ResultSet result1=stSelect1.executeQuery();
						       
						       while(result1.next())
						     {   
						     %>
						     <tr>
						       <td><input type="text" value="<%=result1.getInt(1) %>" name="txtBooking" disabled="disabled"/></td>
						       <td><input type="text"  value="<%=result1.getString(2)%>" name="txtCheckIn" disabled="disabled" /></td>
						       <td><input type="text"  value="<%=result1.getString(3)%>" name="txtCheckOut" disabled="disabled" /></td>
						       <td><input type="text" value="<%=result1.getFloat(4) %>" disabled="disabled"/></td>
						       <td></td>
						      <td> <a href = "CancelTarrif.jsp?bookId=<%= result1.getInt(1)%>">Cancel</a></td>
						     </tr>
						     <%
						     }
						     %>
						</table>
						</form>
					</div>
				</div>
<!-- box end -->
			</div>
			<div class="content">
				<div class="indent" style="height: 97px; ">
				
				<%
				
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
						
						<a href ="SignOut" ><font color="maroon" size="3"><b>Sign Out</b></font></a>
						<%
						    }
						       }
						%> 						
							
							
			
					
						
<br/>
					<h2></h2>
					
					
				 	Feel free to contact us anytime in case you have any questions or concerns.
					<div class="clear"></div>
					<div class="line-hor"></div>
					<div class="wrapper line-ver">
						<div class="col-1">
				 			<h3></h3>
						</div>
						<div class="col-2">
				 			
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- footer -->
	<div id="footer">
  		<ul class="nav">
	 		<li><a href="Home.jsp">Home</a>|</li>
			<li><a href="ServiceInfo.jsp">Services</a>|</li>
			<li><a href="gallery.jsp">Gallery</a>|</li>
			<li><a href="booking.jsp">Booking</a>|</li>			
			<li><a href="AboutUs.jsp">AboutUs</a>|</li>
			<li><a href="Feedback.jsp">Feedback</a>|</li>		
		</ul>
		<div class="wrapper">
			<div class="fleft">Copyright (c) 2009 FiveStar Hotel</div>
			<!--<div class="fright">More <a href="http://www.websitetemplatesonline.com" title="WTO - website templates and Flash templates">Free Website Templates</a> at WTO</div>
		--></div>
	</div>
</div>
</body>
</html>