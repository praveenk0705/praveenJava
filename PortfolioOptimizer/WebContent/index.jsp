<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginstyle.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio Optimizer</title>

</head>
<body>

<!-- old method	<form method="post" action="ValidateLogin">
		
				EmailID:
				<input type="text" name="email" id="uid"/></br>
			
				Password:
				<input type="password" name="pass"  id ="pwd"/></br>
			
				<input type="submit" value="login" />
			
	</form> -->
	<div class="login-page">
  <div class="form">
  <!--   <form method="post" action="ValidateLogin">
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form> -->
    <form class="login-form" method="post" action="ValidateLogin">
      <input type="text"  name="email" id="uid" placeholder="username"/>
      <input type="password"  name="pass"  id ="pwd" placeholder="password"/>
      <input type="submit" value="login" />
     <!--  <p class="message">Not registered? <a href="#">Create an account</a></p> -->
    </form>
  </div>
</div>
	
<%-- <%
    if(null!=request.getAttribute("errormsg"))
    {
        out.println(request.getAttribute("errormsg"));
    }
%>	 --%>

<%-- <c:if test="${not empty errormsg}">
   <c:out value="${errormsg}"/>
</c:if> --%>



</body>
</html>