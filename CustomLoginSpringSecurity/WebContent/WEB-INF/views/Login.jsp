<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Please Login</h1>
	<c:if test=" ${empty failure}">
		<p style="color: red;">Please provide correct credentials</p>
	</c:if>
<form action="${pageContext.request.contextPath}/doform.htm" method="Post">
	userName:<input type="text" name="uname"><br>
	password:<input type="text" name="upassword">
	<input type="submit" value="login">
</form>
</body>
</html>