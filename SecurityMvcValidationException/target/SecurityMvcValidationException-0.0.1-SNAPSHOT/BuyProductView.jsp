<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div>
		<h1>Buy Product</h1>
	</div>
	<div style="color: rgba(60, 95, 130, 0.69); font-size: 19px;">
		<c:if test="${not empty message}">
		${message}
	</c:if>
	</div>
	<div style="float: left;">Product:</div>
	<form:form modelAttribute="productData" method="POST" action="insertorders"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><label path="code">Code:</label></td>
				<td><form:input path="code" type="text"
						value="${productData.code}" readonly="true"
						style="background-color: darkgray" /></td>
			</tr>
			<tr>
				<td><label path="name">Name:</label></td>
				<td><form:input path="name" type="text"
						value="${productData.name}" /></td>
			</tr>
			<tr>
				<td><label path="price">Cost:</label></td>
				<td><form:input path="cost" type="text"
						value="${productData.cost}" /></td>
			</tr>
			<tr>
			<td>Image:</td>
			<td><img src="${pageContext.request.contextPath}/productImage?code=${productData.code}" width="150"/></td>
			</tr>
			 <tr>
				  <td><label>Quantity</label></td>
                 <td><form:input path="quantity" type="number" value="1"/></td> 
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Update Cart" />
						<%-- <a href="Customerview?code=${productCode}&price=${productData.cost}"
							style="float:right;">Enter Customer Details</a> --%>
					
	
					</td>
			</tr>
		</table>
	</form:form>
</body>
</html>