<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="Menu.jsp"></jsp:include>
	<div><hr>Edit Product</hr></div>
	<div><c:if test="${not empty message}">
		${message}
	</c:if></div>
	<div style="float:left;">Product:</div>
	<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
		<table>
                <tr>
                    <td><label path="code">Code:</label></td>
                    <td><form:input path="code" type="text" value="${productForm.code}" readonly="true" style="background-color: darkgray"/></td>
                </tr>
               <tr>
                    <td><label path="name">Name:</label></td>
                    <td><form:input path="name" type="text" value="${productForm.name}"/></td>
                </tr>
                <tr>
                    <td><label path="price">Cost:</label></td>
                    <td><form:input path="price" type="text" value="${productForm.price}"/></td>
                </tr>
                <tr>
                    <td><label path="name">Image:</label></td>
                    <td>
                    <c:choose>
   				 <c:when test="${productForm.fileData==null}">
      				<img path="fileData" src="productImage?code=${productForm.code}" width="100px" height="100px"/>
   				 </c:when>    
    			<c:otherwise>
      					  <form:input type="file" path="fileData"/> 
   				 </c:otherwise>
					</c:choose>
                    </td>
                </tr>
               <tr>
               <td>&nbsp;</td>
               <td><input type="submit" value="Submit" /> <input type="reset"
                   value="Reset" /></td>
           </tr>
            </table>
	</form:form>
</body>
</html>