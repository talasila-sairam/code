<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table width="60%" align="center" border="1px solid" id="ruleInfoTab">
	<c:forEach items="${ruleInfo}" var="listItem">
		<tr>
		<c:forEach items="${listItem}" var="headers">
			<td align="center" contenteditable='true'>${headers}</td>
        </c:forEach>
        </tr>
    </c:forEach>
    </table>
</body>
</html>