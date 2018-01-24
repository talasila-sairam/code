<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Dashboard.jsp"></jsp:include>
	<h1>Plans for selected provider are</h1>
	<c:forEach items="${plans}" var="plans">
	Plan type:&nbsp;${plans.planName}&nbsp;Recharged Amount:&nbsp;${plans.rechargePlan} &nbsp;Recharged Amount:&nbsp;${plans.balanceAdded}
	<br></c:forEach>
	<form action="search-plans.htm" method="get">
		Plan name: <input type="text" name="pname"><br>
		Validity: <input type="text" name="pvalidity"><br> 
		Cost:<input type="text" name="cost"><br>
		<input type="submit" value="Search">
	</form>
</body>
</html>