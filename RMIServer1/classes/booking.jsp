<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.awt.Label"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Reservation and Booking - Free Hotel Website Template designed by TemplateMonster</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="Feel free to visit a Booking page of a Free Hotel Website Template from TemplateMonster.com."/>
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="layout.css" rel="stylesheet" type="text/css" />
<script src="maxheight.js" type="text/javascript"></script>

</head>
<body id="page5" onload="new ElementMaxHeight();">
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
							<li><a href="Feedback.jsp">Feedback</a></li>
							</ul>	
					</div>
				</div>
<!-- header-box end -->
			</div>
		</div>
	</div>
<!-- content -->
	<div id="content">
			
<!-- box begin -->
				
				<div class="inner" style="height: 337px; ">		
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
						
				<a href ="ShowBooking.jsp" ><font color="maroon" size="3"><b>Show My Bookings</b></font></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
						<a href ="SignOut" ><font color="maroon" size="3">Sign Out</font></a>
						<%
						    }
						       }
						%> 		
						<h3>CheckAvailability:</h3>	
						<form action="Check" id="reservation-form" name="sampleform" style="width: 240px;height: 332px;" >
				      	<fieldset style="width: 289px; ">
				          <!--Code for selecting a particular date	-->				          						
							<label>Check In:</label>
							<select class="select1" name="daySelectI" style="width: 50px; ">
                          	<%
							    for(int i=1;i<=31;i++)
							    {
							%>
								<option><%= i%></option>
								<%
								}
								%>
								</select>
							<!-- Code for selecting a particular month -->
							
							<select class="select2" name="monthSelectI" style="width: 92px; ">
                        		<%
							String arr[]={"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
							    for(int i=0;i<=11;i++)
							    {  
							%>
								<option><%= arr[i]%></option>
								<%
								}
								%>
								</select><select class="select3" name="yearSelectI">
							<%
							    for(int i=2011;i<=2050;i++)
							    {
							%>
								<option><%= i%></option>
								<%

								}
								%>
								</select><br><br><br><br>
								
	<!--Code for selecting the year Check Out Code for selecting a particular date		-->
							
							<label>Check Out:</label>
							<select class="select4" name="daySelectO" style="width: 50px;">	
							<%
							    for(int i=1;i<=31;i++)
							    {
							%>
								<option><%= i%></option>
								<%

								}
								%>
								</select>
								
		<!--   Code for selecting a particular month		-->
							
							<select class="select5" name="monthSelectO" style="width: 92px; ">
							<%
							String arr1[]={"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
							    for(int i=0;i<=11;i++)
							    {
							    
							%>
								<option><%= arr1[i]%></option>
								<%

								}
								%>
								</select><select class="select6" name="yearSelectO">
							
							<%
							    for(int i=2011;i<=2050;i++)
							    {
							%>
								<option><%= i%></option>
								<%
								}
								%>
								</select><br><br><br><br>
								
			<!--		Code for selecting the year 	-->
			
								<label>Room Type:</label><select class="select7" name="roomSelect">
								<option>Business Suite</option>
								<option>Royal Suite</option>
								<option>Economic Suite</option>								
								</select>
								<br><br>
								<div class="field">Persons: &nbsp;<input type="text" value="1" name="personNo">&nbsp; &nbsp; &nbsp; &nbsp;
								                     Rooms:&nbsp; <input type="text" value="1" name="roomNo"><br> <br>
								                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="submit" value="Check Availability" style="width: 100px; "></div>
								<div class="button"></div>
							</fieldset>		
						</form><div class="indent" style="height: 419px; width: 794px">
					<h2>Our location</h2>
					<img class="img-indent png" alt="" src="images/5page-img1.png">
					<div class="extra-wrap">
						<p class="alt-top">Our hotel is located in the most spectacular part of Prague - surrounded by boutiques, restaurants and luxurious shops.</p>
						<p>Please feel free to come visit us at the following adress:</p>
						<dl class="contacts-list">
							<dt>Koregaon Park ,Pune.</dt>
								<dd>+91-020-25459312</dd>
								<dd>+91-020-27652794</dd>
						</dl>
					</div>
					<div class="clear"></div>
				</div>
		</div><div class="wrapper">
			
<!-- box begin -->
				
			
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
		</div>
	</div>
</div>
</body>
</html>