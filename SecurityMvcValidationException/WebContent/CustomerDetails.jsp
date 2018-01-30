<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div><h1>Please Enter Required Details</h1></div>
	<div>
		<form:form method="POST" action="customersave" modelAttribute="customerdata">
             <table>
                <tr>
                    <td><form:label path="customerName">Name</form:label></td>
                    <td><form:input path="customerName"/></td>
                    <td><form:errors path="customerName" class="error-message" /></td>
                </tr>
                <tr>
                    <td><form:label path="customerAddr">Address</form:label></td>
                    <td><form:input path="customerAddr"/></td>
                    <td><form:errors path="customerAddr" class="error-message" /></td>
                </tr>
                <tr>
                    <td><form:label path="customerPhone">Contact Number</form:label></td>
                    <td><form:input path="customerPhone"/></td>
                    <td><form:errors path="customerAddr" class="error-message" /></td>
                </tr>
                <tr><td><form:hidden path="code"/></td><td><form:hidden path="cost"/></td></tr>
                <tr>
                	<td><label>Select Address Type:</label>
   					</td> <td><label class="radio-inline"><form:radiobutton  path="addressType" value="Home"/>Home</label>
   					<label class="radio-inline"> <form:radiobutton  path="addressType" value="Office"/>Office</label>
   				 </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                    <td><a href="">Skip This<i class="fa fa-share" aria-hidden="true"></i></a></td>
                </tr>
            </table>
        </form:form>
	</div>
</body>
</html>