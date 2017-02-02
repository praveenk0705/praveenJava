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
							<li><a href="AboutUs.html">AboutUs</a></li>
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
					<div class="inner">
						<h3>Forgot Password</h3>
						
						<%
						
					 Connection connection=null;
					 PreparedStatement stSelect=null;
					 String pass= null;
					
					ServletContext cxt=getServletContext();
					connection = (Connection) cxt.getAttribute("global");
					
					stSelect=connection.prepareStatement("select * from registration where username=? and sQuestion=? and sAnswer=?");
				   String uName=request.getParameter("txtName");
				   String sQues=request.getParameter("sQues");
				   String sAns=request.getParameter("sAnswer");
				   
				   stSelect.setString(1,uName);
				   stSelect.setString(2,sQues);
				   stSelect.setString(3,sAns);
				   
				   
				    ResultSet result=stSelect.executeQuery();
				     
				    if(result.next())
				    {
				    	pass=result.getString("Password");
				    	
				    }
				    
				    else
				    {
				    
				    response.sendRedirect("ForgotPassword2.jsp"); 
				    }%>
				    
						
						
						
						
						
						<form  action="ForgotPassword3.jsp" id="ForgotPassword" style="height: 228px; width: 343px">
					 		<fieldset style="width: 463px; ">
								<div class="field"><label>
								UserName:</label><input type="text" name="txtName" value=<%=uName %> style="width: 209px; height: 17px"/><br><br>
								<br>Password : <input type="text" name="txtName" value="<%=pass %>" style="width: 209px; height: 17px"><br><br><br></div>
									
								<a href="Home.jsp">Goto HomePage</a>
						
												
						
						
					</div>
				</div>
<!-- box end -->
			</div>
			<div class="content">
				
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