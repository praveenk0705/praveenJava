<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Photos Gallery - Free Hotel Website Template designed by TemplateMonster</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta name="description" content="This stunning web page is Gallery of a Free Hotel Website Template from Template Monster."/>
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
<body id="page3" onload="new ElementMaxHeight();">
<div id="main">
<!-- header -->
	<div id="header" class="small">
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
		<div class="row-2 alt">
	 		<div class="indent">
<!-- header-box-small begin -->
				<div class="header-box-small">
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
<!-- header-box-small end -->
			</div>
		</div>
	</div><div class="inner_copy"><div class="inner_copy">Find best free and premium <a href="http://www.design4magento.com" title="Magento templates">Magento themes</a> at Design4Magento.com</div></div>
<!-- content -->
	<div id="content">
  		<div class="gallery">
			<ul>
				<li><a href="#"><img alt="" src="images/2page-img1.jpg" /></a></li>
				<li><a href="#"><img alt="" src="images/2page-img2.jpg" /></a></li>
				<li><a href="#"><img alt="" src="images/2page-img3.jpg" /></a></li>
				<li><a href="#"><img alt="" src="images/2page-img4.jpg" /></a></li>
				<li><a href="#"><img alt="" src="images/2page-img5.jpg" /></a></li>
				<li><a href="#"><img alt="" src="images/2page-img6.jpg" /></a></li>
			</ul>
		</div>
		<div class="container">
			<div class="aside maxheight">
<!-- box begin -->
				<div class="box maxheight">
					<div class="inner">
						<h3>Browse Images</h3>
						<div class="gallery-images">
							<ul>
								<li><a href="#"><img alt="" src="images/3page-img1.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img2.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img3.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img4.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img5.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img6.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img7.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img8.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img9.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img10.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img11.jpg" /></a></li>
								<li><a href="#"><img alt="" src="images/3page-img12.jpg" /></a></li>
							</ul>
						</div>
					</div>
				</div>
<!-- box end -->
			</div>
			<div class="content">
				<div class="indent">
				
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
				
					<h2>Hotels picture gallery</h2>
					<div class="gallery-main png">
						<div class="inner">
				 			<img alt="" src="images/3page-img13.jpg" />
							<div class="prev"><a href="#"><img alt="" src="images/prev.png" class="png" /></a></div>
							<div class="next"><a href="#"><img alt="" src="images/next.png" class="png" /></a></div>
						</div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
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