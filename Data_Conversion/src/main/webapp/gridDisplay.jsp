<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/angular.js"></script>
    <script src="js/angular-touch.js"></script>
    <script src="js/angular-animate.js"></script>
    <script src="js/csv.js"></script>
    <script src="js/pdfmake.js"></script>
    <script src="js/vfs_fonts.js"></script>
    <script src="js/ui-grid.js"></script>
    <script src="js/angular-sanitize.js"></script>
    <link rel="stylesheet" href="css/ui-grid.css" type="text/css">
 <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="css/main.css" rel="stylesheet" type="text/css"/>
<link href="css/manual-style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/application.js"></script>
<% String baseQuery=request.getParameter("baseQuery");
System.out.println(baseQuery);
String ruleQuery=request.getParameter("ruleQuery");
System.out.println(ruleQuery);
String ruleQuery71 = request.getParameter("ruleQuery71");
System.out.println(ruleQuery);
String ruleQuery72 = request.getParameter("ruleQuery72");
System.out.println(ruleQuery);
String ruleQuery73 = request.getParameter("ruleQuery73");
System.out.println(ruleQuery);
%>
<style>

.ui-grid{
    height: 590px;
  /*  width: 925px;*/
	#loadingImage
    {
        position: fixed;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
        z-index: 9999;
        background: url(./img/ajax-loader.gif) 50% 50% no-repeat #ede9df;
    }
</style>
</head>
<body ng-controller="MainCtrl">
<!-- <div><button id="mybutton"  ng-click="fullSceen()""><span id="fulscreenicon" class="pull-right"><i  class="icon-fullscreen"></i></span></button></div> -->
  <div class="ui-grid ui-grid-responsive" ui-grid="gridOptions" ui-grid-edit ui-grid-pagination ui-grid-resize-columns >
  <div class="well grid-loading" ng-show="grid.rows.length == 0">
    <div id="loadingImage"></div>
 </div>
<pagination boundary-links="true" num-pages="noOfPages" total-items="totalItems" page="currentPage" items-per-page="10" class="pagination-sm nomargin" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"></pagination>
</div> 
<input type=hidden id="baseQuery" value="<%=baseQuery%>"/>
<input type=hidden id="ruleQuery" value="<%=ruleQuery%>"/> 

<input type=hidden id="ruleQuery71" value="<%=ruleQuery71%>"/>
<input type=hidden id="ruleQuery72" value="<%=ruleQuery72%>"/>
<input type=hidden id="ruleQuery73" value="<%=ruleQuery73%>"/>
<!-- 
<input type=hidden id="baseQuery" />
<input type=hidden id="ruleQuery" /> -->
</body>
</html>