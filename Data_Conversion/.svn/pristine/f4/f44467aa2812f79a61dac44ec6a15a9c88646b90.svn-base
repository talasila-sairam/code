<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%Date dNow = new Date( );
SimpleDateFormat ft = 
new SimpleDateFormat ("E yyyy.MM.dd  hh:mm:ss ");
 %>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-animate.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    <script src="http://ui-grid.info/release/ui-grid.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-sanitize.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/UserAssignment.js"></script>
<link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
</head>
<body ng-controller="userassignment">
<div id="userTasksAssignment" class="userTaskAssignment">
<table style="margin-top: 10px;margin-left: 10px;">
  <tr><th>Form Date :</th><th>Date:</th><th><th>State</th><th>County</th><th>Edition Year</th><th style="padding-left: 34px;">User</th></tr>
  <tr>
  <td>  
		<div class="input-group date" id="datetimepicker1">
                    <input id="fromDate" type="text" class="form-control" style="width: 135px;">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
       
     </td>
      <td>
       	<div class="input-group date" id="datetimepicker1">
                    <input id="toDate" type="text" class="form-control" style="width: 135px;">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
     </td>
     <td><button type="button" id="showData" class="form-group btn btn-success" ng-click="showAllUsers()" style="margin-left: 6px;">Submit</button></td>
<td><input type='text' id="stateselect" class='form-control countiessearch' placeholder='search state' style="width: 127px;margin-left: 6px;"></td>
<td><input type='text' id="countselect" class='form-control statesearch' style="margin-left: 6px; width: 127px;" placeholder='search county'>
</td>
 <td> <select id="editionselect" class="form-control userStatus" style="width: 100px;">
    <option value="00">00</option>
    <option value="01">01</option>
    <option value="02">02</option>
    <option value="03">03</option>
    <option value="04">04</option>
    <option value="05">05</option>
    <option value="06">06</option>
    <option value="07">07</option>
    <option value="08">08</option>
    <option value="09">09</option>
    <option value="10">10</option>
    <option value="11">11</option>
    <option value="12">12</option>
    <option value="13">13</option>
    <option value="14">14</option>
    <option value="15">15</option>
     <option value="16">16</option>
     <option value="17">17</option>
	</select></td> 
 <td>
<select id='userSelect' class="form-control" multiple="multiple" style="margin-left: 6px;">
 </select>

</td>
<td><button type="button" id="swapData" class="form-group btn btn-success" ng-click="showAssignUsers()" style="margin-left: 6px;">Assign Task</button></td>
<td><span id="assignedTime" style="background: lightyellow;
    font-family: monospace;
    font-weight: bolder;"></span></td>
    <input type=hidden id="tableCount" value=""/>
</tr>
</table>
<div id="grid1" ui-grid="gridOptions" class="grid" style="width:1322px;  height: 480px;  margin-top: 10px;margin-left: 10px;overflow-x: scroll;"></div>
</div>
</body>
</html>