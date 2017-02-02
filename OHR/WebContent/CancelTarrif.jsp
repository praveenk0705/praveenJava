
<%@page import="java.sql.Date"%>
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
<meta name="description"
	content="This is a wonderful homepage of the Free Hotel Website Template provided by TemplateMonster." />
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
<div id="main"><!-- header -->
<div id="header">
<div class="row-1">
<div class="wrapper">
<div class="logo">
<h1><a href="Home.jsp">Five Star</a></h1>
<em>Hotel</em> <strong>True Luxury</strong></div>
<div class="phones">+91-020-25459312<br />
+91-020-27652794</div>
</div>
</div>
<div class="row-2">
<div class="indent"><!-- header-box begin -->
<div class="header-box">
<div class="inner">
<ul class="nav">
	<li><a href="Home.jsp" class="current">Home page</a></li>
	<li><a href="ServiceInfo.jsp">Services</a></li>
	<li><a href="gallery.jsp">Gallery</a></li>
	<li><a href="booking.jsp">Booking</a></li>
	<li><a href="AboutUs.html">AboutUs</a>
	<li><a href="Feedback.jsp">Feedback</a></li>		
</ul>
</div>
</div>
<!-- header-box end --></div>
</div>
</div>
<!-- content -->
<div id="content">
<div class="wrapper">
<div class="aside maxheight"><!-- box begin -->
<div class="">
<div class="inner">

<form action="" name="cancelForm">

<h5>Your Booking Detail is</h5>



<table border="1" cellspacing="1" cellpadding="1" style="width: 500px;">
	<tr>
		<th>BOOKINGID</th>
		<th>ROOMID</th>
		<th>USERNAME</th>
		<th>DATE</th>
		<th>TOTAL ROOM</th>
		<th>CHECKINDATE</th>
		<th>CHECKOUTDATE</th>
		<th>TOTALCHARGES</th>
		<th>NOOFPERSON</th>

	</tr>
	<%
		float totalAmt = 0;
		Date checkInDate = null;
		float refundAmt = 0;
		int dateDiff = 0;

		Connection connection = null;
		PreparedStatement stSelect = null;
		PreparedStatement stSelect1 = null;

		String fname;

		ServletContext cxt = getServletContext();
		connection = (Connection) cxt.getAttribute("global");
		String uName = (String) session.getAttribute("userName");

		String bookingId = request.getParameter("bookId");
		System.out.println(bookingId);

		stSelect1 = connection
				.prepareStatement("select * from room_booking where bookingid=?");

		stSelect1.setString(1, bookingId);

		ResultSet result1 = stSelect1.executeQuery();

		//int totalAmt = result1.getInt(8);
		//result1.getString(6);
		if (result1.next()) {

			totalAmt = result1.getInt(8);
			//System.out.println(result1.getDate("CHECKINDATE"));
			checkInDate = result1.getDate("CHECKINDATE");
	%>
	<tr>
		<td><input type="text" value="<%=result1.getInt(1)%>"
			name="txtCheckIn" disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getInt(2)%>"
			name="txtCheckOut" disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getString(3)%>"
			disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getString(4)%>"
			disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getInt(5)%>"
			disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getString(6)%>"
			disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getString(7)%>"
			disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getInt(8)%>"
			disabled="disabled" /></td>
		<td><input type="text" value="<%=result1.getInt(9)%>"
			disabled="disabled" /></td>

	</tr>
	<%
		}
	%>
</table>





<br>
<br>
<h4><b><font size="4" color="maroon">Cancellation Tarrif
</font></b></h4>

<br>
<table border="1" cellspacing="1" cellpadding="1" style="width: 683px;">
	<tr>
		<th>If the Cancellation is done (20&gt;) days before CheckIn the
		customer will be Charged 10% of the booking amount<br>
		If the Cancellation is done 15 days before CheckIn the customer will
		be Charged 15% of the booking amount<br>
		If the Cancellation is done 210 days before CheckIn the customer will
		be Charged 20% of the booking amount<br>
		</th>



	</tr>
</table>

<%
	//at this point i have the date difference amt paid and amt to refund
	//plan for the cardno to payto
	System.out.println("total amt " + totalAmt);
	System.out.println(checkInDate);

	//String checkInDate=result1.getString("CHECKINDATE");
	//System.out.println(checkInDate);
	PreparedStatement stDateDiff = null;
	stDateDiff = connection
			.prepareStatement("select to_date(?) - sysdate from dual");

	//stDateDiff = connection
	//.prepareStatement("select to_char((sysdate),'dd-MON-yy') from dual");		
	stDateDiff.setDate(1,checkInDate);		
	ResultSet dateDiffResult = stDateDiff.executeQuery();
	if (dateDiffResult.next()) {

		dateDiff = dateDiffResult.getInt(1);
		System.out.println("We have the diff of date"
				+ dateDiffResult.getInt(1));

	}

	if (dateDiff >= 0 && dateDiff <= 10) {
		refundAmt = totalAmt - (0.2f * totalAmt);
		//refundAmt = totalAmt - 1000;
		System.out.println("1st" + refundAmt);
	} else if (dateDiff > 10 && dateDiff <= 20) {
		refundAmt = totalAmt - (0.15f * totalAmt);
		//refundAmt = totalAmt - 700;

		System.out.println("2nd" + refundAmt);

	} else if (dateDiff > 20) {
		refundAmt = totalAmt - (0.1f * totalAmt);
		
		//refundAmt = totalAmt - 500;
		
		System.out.println("3rd" + refundAmt);

	}
%> <br>
The above Cancellation will cost you:<input type="text"
	value="<%=totalAmt-refundAmt%>" name="txtLoss" disabled="disabled" /><br>


<br>
You'll be refunded:<input type="text" value="<%=refundAmt%>"
	name="txtRefund" disabled="disabled" /><br>
<br>
<a href="Cancellation?bookingId=<%=bookingId%>&refundAmt=<%=refundAmt%>">Continue
with cancellation</a><br>
<br>
<a href="ShowBooking.jsp">Do not cancel my booking</a></form>
</div>
</div>
</form>
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