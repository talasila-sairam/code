<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="page-title">Product</div>
	<c:if test="${not empty message }">
		<div class="error-message">${message}</div>
	</c:if>
	<form:form modelAttribute="productForm" method="post" enctype="multipart/form-data">
		<table style="text-align: left;">
			<tr>
				<td>Code</td>
				<td style="color: red;">
				<c:if test="${not empty productForm.code}"> 
					<form:hidden path="code" /> ${productForm.code}
				</c:if>
				<c:if test="${empty productForm.code}">
					<form:input path="code" />
					<form:hidden path="newProduct" />
				</c:if></td>
				<td><form:errors path="code" class="error-message" /></td>
				
			</tr>
			 <tr>
               <td>Name *</td>
               <td><form:input path="name" /></td>
               <td><form:errors path="name" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Price *</td>
               <td><form:input path="price" /></td>
               <td><form:errors path="price" class="error-message" /></td>
           </tr>
           <tr>
               <td>Image</td>
               <td>
               <img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/></td>
               <td> </td>
           </tr>
           <tr>
               <td>Upload Image</td>
               <td><form:input type="file" path="fileData"/></td>
               <td> </td>
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