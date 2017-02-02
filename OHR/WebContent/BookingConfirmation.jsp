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
		<div class="wrapper">
			<div class="aside maxheight">
<!-- box begin -->
				<div class="box maxheight">
					<div class="inner" style="height: 609px; ">
						<h3>Reservation:</h3>
						
					<form  action="" id="FinalBooking" style="height: 462px; width: 299px">
					
					<%
						int id=0;
					 Connection connection=null;
					 PreparedStatement stSelect=null;
					
					ServletContext cxt=getServletContext();
					connection = (Connection) cxt.getAttribute("global");
					
					stSelect=connection.prepareStatement("select max(bookingId) from room_booking");
				    ResultSet result=stSelect.executeQuery();
				     
				    if(result.next())
				    {
				    	id=Integer.parseInt(result.getString(1));
				    	
				    }
					
				        session.setAttribute("book_Id",id);
						String name=session.getAttribute("userName").toString();
						String checkIn=session.getAttribute("checkIn").toString();
						String checkOut=session.getAttribute("checkOut").toString();
						String roomType=session.getAttribute("roomType").toString();
						String person=session.getAttribute("person").toString();
						String noRoom=session.getAttribute("noRoom").toString();
						String cost=session.getAttribute("cost").toString();
						
						//int Rcost=Integer.parseInt(cost);
						//System.out.println(Rcost);
						
						//String Nservice1 = session.getAttribute("LaundaryN").toString();
						String Pservice1 = session.getAttribute("LaundaryP").toString();
						String Pservice2 = session.getAttribute("CabP").toString();
						String Pservice3 = session.getAttribute("InternetP").toString();					
						String Pservice4 = session.getAttribute("GymP").toString();					
						String Pservice5 = session.getAttribute("CityP").toString();
						String Ptotal=session.getAttribute("totalService").toString();
						
						float total=(Float)session.getAttribute("tc");
						
						//int Scost=Integer.parseInt(Ptotal);
						//System.out.println(Scost);
						
						//int Tcost=Rcost+Scost;
						//System.out.println(Tcost);

					%>

<table border="1" cellspacing="1" cellpadding="1" style="height: 489px; ">
<tr><td> Booking Id</td><td> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" value="<%=id%>" disabled="disabled"  name = "bookId"/></td></tr>
<tr><td> Room Type</td><td>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<%=roomType %>" disabled="disabled" /> </td></tr>
<tr><td> User Name </td><td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<%=name%>" name="txtName" disabled="disabled" />  </td></tr>
<tr><td> Check In Date</td><td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" value="<%=checkIn%>" disabled="disabled" name="txtCheckIn"/> </td></tr>
<tr><td> Check Out Date</td><td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" value="<%=checkOut %>" disabled="disabled" name="txtCheckOut"/></td></tr>
<tr><td> Total No Of Room</td><td> &nbsp; &nbsp;<input type="text" value="<%=noRoom %>" disabled="disabled" /></td></tr>
<tr><td> Total No Of Person</td><td> &nbsp;<input type="text" value="<%=person %>" disabled="disabled" />  </td></tr>
<tr><td> Room Cost</td><td> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;     <input type="text" value="<%=cost %>" disabled="disabled" />   </td></tr>


<tr><td><h4>Services</h4></td></tr>
<tr><td>Laundary </td><td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=Pservice1%>" disabled="disabled" /></td></tr>
<tr><td>Cab Facility</td><td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=Pservice2%>" disabled="disabled" /></td></tr>
<tr><td>Internet</td><td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=Pservice3%>" disabled="disabled" /></td></tr>
<tr><td>Gym & Spa</td><td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=Pservice4%>" disabled="disabled" /></td></tr>
<tr><td>City Darshan</td><td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=Pservice5%>" disabled="disabled" /></td></tr>
<tr><td>Services Total</td><td>&nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=Ptotal%>" disabled="disabled" /></td></tr><br/>
<tr><td> Total Payment</td><td> &nbsp;&nbsp;&nbsp; &nbsp;<input type="text" value="<%=total%>" disabled="disabled" /></td></tr>

<tr><td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="CancelTarrif.jsp?bookId=<%=id %>">Cancel Current Booking</a><br><br>
<tr><td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="Home.jsp">Continue Browsing</a>

</td></tr>

</table>
</form>
						
					</div>
				</div>
<!-- box end -->
			</div>
			<div class="content">
				<div class="indent">
				<%
	
					String fname;
					stSelect=connection.prepareStatement("select firstname from registration where username=?");
					
								if(session!=null)
								{
									String user = (String)(session.getAttribute("userName"));
							if(user!=null)
							{
								stSelect.setString(1,user);
								ResultSet result1=stSelect.executeQuery();
								if(result1.next())
								{
									 fname=result1.getString(1);
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
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					
						<a href ="SignOut" ><font color="maroon" size="3"><b>Sign Out</b></font></a>
						<%
						    }
						       }
						%> 
<br/>
				
				
					<h2>FiveStar is happy to welcome you!</h2>
					<img class="img-indent png" alt="" src="images/1page-img1.png" />
					<p class="alt-top">Come alone or bring your family with you, stay here for a night or for weeks, stay here while on business trip or at some kind of conference - either way our hotel is the best possible variant.</p>
				 	Feel free to contact us anytime in case you have any questions or concerns.
					<div class="clear"></div>
					<div class="line-hor"></div>
					<div class="wrapper line-ver">
						<div class="col-1">
				 			<h3>Special Offers</h3>
							<ul>
								<li>FREE wide-screen TV</li>
								<li>50% Discount for Restaraunt service</li>
								<li>30% Discount for 3 days+ orders</li>
								<li>FREE drinks and beverages in rooms</li>
								<li>Exclusive souvenirs</li>
							</ul>
							
						</div>
						<div class="col-2">
				 			<h3>Location</h3>
							<p>We are located in the center of Prague surrounded by malls and boutiques.</p>
							<dl class="contacts-list">
								<dt>Koregaon Park ,Pune.</dt>
								<dd>+91-020-25459312</dd>
					<dd>+91-020-27652794</dd>
							</dl>
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
			</div>
	</div>
</div>
</body>
</html>