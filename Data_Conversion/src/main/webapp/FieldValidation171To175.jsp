<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">
<link href="css/manual-style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<!-- <link href="css/gips.css" rel="stylesheet" type="text/css" /> -->
<script src="js/bootstrap-multiselect.js"></script>
</head>
<body>
<div id = "countySelect" >
	<label class="control-label" id = "countyTableLabel">Select Code:</label>
	<select id="SelectCodes" style="width: 31% !important;    height: 27px;" class="form-">
		<option value="STG">STG</option>
   		<option value="SH">SH</option>
   		<option value="SA">SA</option>
   		<option value="PB">PB</option>
   		<option value="Begins with 'MB'">Begins with "MB"</option>
   		<option value="GR">GR</option>
   		<option value="BN">BN</option>						    		
	</select>
	</div>
</body>
</html>