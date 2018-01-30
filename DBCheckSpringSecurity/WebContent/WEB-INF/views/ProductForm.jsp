<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Sucessfully login<a style="float: right;"  href="${pageContext.request.contextPath}/logout.htm">logout</a></h1>
	<form:form modelAttribute="productForm" action="${pageContext.request.contextPath}/saveform.htm" method="Post" >
	<div style="color: red;"><form:errors path="*" /></div>
	<table>
	<h>From Address:::</h>
	<tr>
		<td>Source:<form:input  path="source"/></td></tr>
		<tr><td>address:<form:input  path="faddress" /></td></tr>
	    <tr><td>Mobile:<form:input  path="fmobile" /></td></tr>
	</table>
	<h>To Sddress::</h>
	<table>
		<tr>
		<td>Destination:<form:input  path="destination" /></td></tr>
		<tr><td>address:<form:input  path="taddress" /></td></tr>
	    <tr><td>Mobile:<form:input  path="tmobile" /></td></tr>
	</table><br>
	<table style="position: absolute;">
		<tr><td>Product name:</td><td><form:input  path="pname" />
		</td>
		</tr>
		<tr><td>Price:</td><td><form:input  path="pprice" />
		</td>
		</tr>
		<tr><td>Weight:</td><td><form:input  path="pweight" />
		
		</tr>
		<tr><input type="submit" value="Save"></tr>
	</table>
	
</form:form>
</body>
</html>