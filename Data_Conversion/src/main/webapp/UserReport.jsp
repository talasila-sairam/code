<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/ui-grid.css" type="text/css">

<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/angular.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>

<script type="text/javascript" src="js/userReport.js"></script>
<link rel="stylesheet" type="text/css" href="css/manual-style.css">
</head>
<body>
<div class="col-md-12">
<!-- <table class='table table-bordered '  id='table-fixed ' style="margin-top:10px;"/>
<thead><th>CountyName</th><th style="width: 24%;">Current Status</th><th>Selected User Status</th><th>Assigned Time</th>
<th>Processed Time</th>
<th>Completed Time</th>
<th>Duration</th>
<th>Count</th>
</thead>
<tbody>
<tr>
	<td id="assignedCounty"></td>
	<td>Assined
		<span><select id="status" class=" userStatus" style="margin-right: 10px;">
    <option value="">--Please Select--</option>
    <option value="Ready For Processing">Ready For Processing</option>
    <option value="Completed">Completed</option>  
	</select>
	</span>
	</td>
	<td id="userSelected"></td>
	<td id="userTaskAssigned"></td>
	<td id="userProcesed"></td>
	<td id="userTaskCompleted"></td>
	<td id="userTaskDuration"></td>
	<td id="userTaskCount"></td>
</tr>
</tbody>
</table>

</div>
<!-- <div class="col-md-3">
<textarea class="form-control inputstl" id="usercomments" rows="5" style="height: 59px !important; border-color: darkseagreen;margin-top:10px;"></textarea>
<button type="button" id="submitClick" class="btn btn-success"  style="margin-left: 96px;margin-top:10px;float:right;">Submit</button>
	<input type="checkbox" id="signOffStatus" value="Sign Off" style="margin-top: 20px;">Sign Off</input>
</div>  -->

<div id="userReportDetails" style="margin-top: 10px;"></div>
<input type=hidden id="userLoggedin" value="<%=session.getAttribute("username")%>"/>
<div id ="buildJoinConditions"></div>

</body>
</html>