<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!--<script type="text/javascript">
function fun()

{
	var id=document.fi.roomId.value;
	
	window.location = 'http://localhost:89/OHR/roomModify.java';

	
}

</script>-->



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
								<div class="field"><font size="3"><a href="roomModify.jsp">Modify Room</a></font></div><br/><br/>
								<div class="field"><font size="3"><a href="serviceModify.jsp">Modify Services</a></font></div>	
							</fieldset>						
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
					
					<div class="clear"></div>
					<br/><br/>                 
	                 <table border="1" cellpadding="1" cellspacing="1" align="right">
	                 <tr><td colspan="3" align="center"><b><font color="maroon">Detailed View of Services</font></b></td></tr>
	                 <tr>
	                 <td>Service Name</td>
	                 <td>Service Id</td>
	                 <td>Service Price</td>
	                 </tr>
	                 
	              <% 
	         		 stSelect=connection.prepareStatement("select * from services");
	         		 ResultSet result=stSelect.executeQuery();
	         		 
	         		 while(result.next())
	         		 {
	                 %>
	                 <tr>
	                 <td><%=result.getString(1)%></td>
	                 <td><%= result.getInt(2)%></td>
	                 <td><%=result.getInt(3) %></td>
	                 </tr>
	                 
	                 <%
	                    }
	                 %>
	                 </table>
					
					
	                 <form action="serviceModify" name="fi" method="">
	                 <table border="1" cellspacing="1" cellpadding="1">
	                 <tr><td colspan="1" align="center" ><b><font color="maroon">Modify Services</font></b></td></tr>
	                  <tr>
	                    <td> Service Id &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="service_Id"></td>
	                  </tr>
	                  
	                   <tr>
	                    <td> Service Type  &nbsp;&nbsp;&nbsp;<input type="text" name="service_Name"></td>
	                  </tr>
	                  
	                   <tr>
	                    <td> Service Price&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="price"></td>
	                  </tr>
	                  <tr>
	                  <td>
	                 <input type="submit" value="Update" name="btnUpdate" >
	                   <input type="submit" value="Add" name="btnAdd">
	                  <input type="submit" value="Delete" name="btnDelete" >
	                  
	                  
	                  </td>
	                  </tr>
	                 </table>
	                 </form>
						<div class="col-1">
					</div>
				</div>
			</div>
			
			
		</div>
	</div>
	
	
	
<!-- footer -->
</body>
</html>