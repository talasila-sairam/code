<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">

</style>
</head>

<body>
		<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/">Home<i class="fa fa-home" aria-hidden="true"></i></a>
   |
   <a href="${pageContext.request.contextPath}/productList.htm">
      Product List
   </a>
   |
   <a href="${pageContext.request.contextPath}/shoppingCart.htm">
      My Cart<i class="fa fa-shopping-bag" aria-hidden="true"></i>
   </a>
   |
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/orderList.htm">
         Order List<i class="fa fa-list" aria-hidden="true"></i>
     </a>
     |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/product.htm">
                        Create Product
         </a>
         |
   </security:authorize>
  
</div>
</body>
</html>