<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script language="javascript">
function validation()
{
  var fname,lname,uname,password,cpassword,address,gender,email,contact,que,ans;
   
  fname=document.form1.fname.value;
  lname=document.form1.lname.value;
  uname=document.form1.uname.value;
  password=document.form1.password.value;
  cpassword=document.form1.cpassword.value;
  address=document.form1.address.value; 
  gender=document.form1.gender.value;
  email=document.form1.email.value;
  contact=document.form1.contact.value;
  que=document.form1.sQues.value;
  ans=document.form1.sAns.value;
  
  if(fname==""||fname==null)
  {
	 alert("First name should not empty..");
	 return false;	 
  }
  if(lname==""||lname==null)
  {
	 alert("Last name should not empty..");
	 return false;	 
  }
 
  if(uname==""||uname==null)
  {
	 alert("Username should not empty..");
	 return false;
	 
  }
   if(password==""||password==null)
  {
	 alert("Password should not empty..");
	 return false;
	 
  }
  if(password.length<6)
	 {
 	   alert("password length should be of minmum 6");
	   return false;
	 }

    if(cpassword==""||cpassword==null)
  {
	 alert("Confirm password should not empty..");
	 return false;	 
  }    
    if(address==""||address==null)
    {
  	 	alert("Address should not empty..");
  		 return false;  	 
    }
       if(document.form1.gender[0].checked==false && document.form1.gender[1].checked==false)
	{
	  alert("Please enter the gender");
	  return false;
	}
	if((email.indexOf('@')<0)||(email.indexOf('.')<0))
	{
		alert("Enter Valid Email Id");		
		return false;
	}
	if(isNaN(contact))
	{
	alert("Enter the valid Mobile Number(Like : 9566137117)");	
	return false;
	}
	if(que==""||que==null)
    {
  	 	alert("Secret Question should not empty..");
  		 return false;  	 
    }
    if(address==""||address==null)
    {
  	 	alert("Secret Answer should not empty..");
  		 return false;  	 
    }	
  return true;
 }
 </script>

</head>
<body>
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
				<div class="box height">
					<div class="inner" style="height: 200px; width: 200px">						
						<h3>User Registration:</h3>						
						<form  action="SignUp"  id="Signup" style="height: 228px;"  form name="form1" onsubmit="return validation()">
					 		<fieldset>
							<table border="1" cellpadding="1" cellspacing="1">
							<tr><td>First Name</td><td><input type="text" name="fname"></td></tr>
							<tr><td>Last Name</td><td><input type="text" name="lname"></td></tr>
							<tr><td>User Name</td><td><input type="text" name="uname"></td></tr>
							<tr><td>Password</td><td><input type="password" name="password"></td></tr>
							<tr><td>Confirm Password</td><td><input type="password" name="cpassword"></td></tr>
							<tr><td>Address</td><td><textarea name="address"></textarea></td></tr>
							<tr><td>Gender</td><td>Male: <input type="radio" name="gender" value="male" />
												   Female: <input type="radio" name="gender" value="female" />
										  </td></tr>
							<tr><td>Date Of Birth</td><td><select class="select1" name="DOB">
                          	<%
							    for(int i=1;i<=31;i++)
							    {
							%>
								<option><%= i%></option>
								<%
								}
								%>
								</select><!--
								
						       Code for selecting a particular month	
						       
						       --><select class="select2" name="monthSelectI" style="width: 50px; ">
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
							    for(int i=1940;i<=2050;i++)
							    {
							%>
								<option><%= i%></option>
								<%

								}
								%>
								</select>			
								</td></tr>
							<tr><td>Email ID</td><td><input type="text" name="email"></td></tr>
							<tr><td>Contact No.</td><td><input type="text" name="contact"></td></tr>
							
							<tr><td>Enter Secret Question</td><td><input type="text" name="sQues"></td></tr>
							<tr><td>Enter Secret Answer</td><td><input type="text" name="sAns"></td></tr>
																				
							</table>
							<input type="submit" value ="Submit" align="center" border="2"/>		
							</fieldset>
							</form>						
					</div>
				</div><!--
 box end 
			--></div><div class="indent" style="width: 623px; ">			
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
						<a href ="SignOut" ><font color="maroon" size="3"><b>Sign Out</b></font></a>
						<%
						    }
						       }
						%> 
					<br/>				
					<h2>FiveStar is happy to welcome you!</h2>
					<img class="img-indent png" alt="" src="images/1page-img1.png">
					<p class="alt-top">Come alone or bring your family with you, stay here for a night or for weeks, stay here while on business trip or at some kind of conference - either way our hotel is the best possible variant.</p>
				 	Feel free to contact us anytime in case you have any questions or concerns.
					<div class="clear"></div>
					<div class="line-hor"></div>
					<div class="wrapper line-ver">
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
			<div class="content">
				
			</div>
		</div>
	</div><!--
 footer 
	--><div id="footer">
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
-->