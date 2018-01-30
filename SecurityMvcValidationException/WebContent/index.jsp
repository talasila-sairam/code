<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  http-equiv="content-type">
<title>Insert title here</title>
<link href="<c:url value='resources/css/style1.css' />" rel="stylesheet"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/GataWay.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<jsp:include page="Menu.jsp" />

	<div class="page-title">Shopping Cart Demo</div>

	<div class="demo-container">
		<h3>Demo content</h3>

		<ul>
			<li>Buy online</li>
			<li>Admin pages</li>
			<li>Reports</li>
		</ul>
	</div>
</body>
</html>