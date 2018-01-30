<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/fm/fontawesome.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>
<script type="text/javascript">
function gotoGateway(){
	$("#gatewaypage").load("PamentGateWay.jsp");
}
</script>
</head>
<body>
<%float subTotal; %>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div>
		<h1>Your Orders Here.</h1>
	</div>
	<div class="col-sm-12" style="    padding-bottom: 22px;">
	<c:forEach items="${cartList}" var="cartItems">
		<div class="col-sm-6">
			<table>
				<tr>
					<td><label>Product Name:</label></td>
					<td>${cartItems[0]}</td>
				</tr>
				<tr>
					<td>Purchase Date:</td>
					<td><fmt:formatDate type = "both" value = "${cartItems[3]}" /></td>
				</tr>
				<tr>
					<td><img path="fileData"
						src="productImage?code=${cartItems[1]}" width="100px"
						height="100px" /></td>
				</tr>
				<tr>
					<td>Quantity:</td>
					<td>${cartItems[5]}</td>
				</tr>
				<tr>
					<td>Product Cost:</td>
					<td><fmt:formatNumber value = "${cartItems[2]}" type = "currency"/></td>
				</tr>
				<tr>
				   <td>  <a href="${pageContext.request.contextPath}/deletecart?productid=${cartItems[4]}">Remove</a></td>
				</tr>
		
			</table>
		</div>
	</c:forEach>
	</div>
	<div><h3>Sub Total:<fmt:formatNumber value = "${subtotal}" type = "currency"/></h3>
		<span><input type="button" value="Proceed To Pay"/ title="Click to pay" onclick="gotoGateway();"></span>
	</div>
	<div id="gatewaypage"></div>
</body>
</html>