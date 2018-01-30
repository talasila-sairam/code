<%@page import="java.util.ArrayList"%>
<%@page import="com.apps.beans.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/fm/fontawesome.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div><h1>Product List</h1></div>
	<c:forEach items="${productList}" var="product">
	
		<div class="product-preview-container">
		<span style="    float: right;"><a href="deleteproduct?code=${product.code}" title="Click to delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a></span>	
			<ul>
			 	<li><img class="product-image" src="productImage?code=${product.code}"/></li> 
				<li>Code: ${product.code}</li>
				<li>Name:${product.name}</li>
				<li>Price:<fmt:formatNumber value="${product.cost}" type="currency"></fmt:formatNumber>
				</li>
				<li><a
                   href="${pageContext.request.contextPath}/buyProduct?code=${product.code}">
                       Buy Now</a></li>
                <security:authorize access="hasRole('ROLE_MANAGER')">
                	<li style="color: red;"><a href="${pageContext.request.contextPath}/product?code=${product.code}">Edit Product</a></li>
                </security:authorize>       
			</ul>
		</div>
	</c:forEach>
</body>
</html>