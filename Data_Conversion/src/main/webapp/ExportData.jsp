<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="ExportData">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-animate.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
    <script src="http://ui-grid.info/release/ui-grid.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-sanitize.js"></script>
  <!--  <script type="text/javascript" src="js/jquery.min.js"></script> -->
   <script type="text/javascript" src="js/ExportData.js"></script>
   <script type="text/javascript" src="js/excelQuestionPopup.js"></script>   
   <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <link href="css/manual-style.css" rel="stylesheet" type="text/css"/>
   <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
   <link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css">
   <link href="css/main.css" rel="stylesheet" type="text/css"/>
   <link rel="stylesheet" href="css/jquery-ui.css">
   <%
     String territoryName="abc";
     System.out.println("my value" + territoryName);

   %>
   <script>
   $(document).ready(function() {
	   var a = sessionStorage.getItem("territory");
	   territoryName = a;
	   var sv = sessionStorage.getItem("stateValue");
	   var editon = sessionStorage.getItem("editon");
	   var stateFipsCode = sessionStorage.getItem("stateFipsCode");
	   var countyFipsCode =  sessionStorage.getItem("countyFipsCode"); 
       var countryFipsCode = sessionStorage.getItem("countryFipsCode");
       var countryName = sessionStorage.getItem("countryName");
       var stateCode = sessionStorage.getItem("stateCode");
       
	   
	   $("#hid").val(a);
	   $("#state").val(sv);
	   $("#editon").val(editon);
	   $("#stateFipsCode").val(stateFipsCode);
	   $("#countyFipsCode").val(countyFipsCode);
	   $("#countryFipsCode").val(countryFipsCode);
	   $("#countryName").val(countryName);
	   $("#stateCode").val(stateCode);
   });
   
  function linkCall(){
	   var countyName = $("#hid").val();
	   var stateName = $("#state").val();
	   var edition = $("#editon").val();
	   var stateFipsCode = $("#stateFipsCode").val();
	   var countyFipsCode = $("#countyFipsCode").val();
	   var countryFipsCode = $("#countryFipsCode").val();
	   var countryName = $("#countryName").val();
	   var stateCode = $("#stateCode").val();
	   var jsonData = { "edition": edition,
						"countryFipsCode": countryFipsCode, 
						"stateFipsCode": stateFipsCode,
						"countyFipsCode": countyFipsCode
				}
	 	console.log(jsonData);
	 	$.ajax({
	        type: "POST",
	        url: 'report/checkSourceVerificationResult',
	        dataType: 'json',
	        data: JSON.stringify(jsonData),
	        contentType: "application/json",
	        success: function (data) {
	        	console.log(data);
	        	if(data.status == "EXISTS"){
	        		//window.location = 'report/downloadExcelReport?countyName=' + $("#hid").val()+'&editon='+$("#editon").val();
	        		window.location = 'report/downloadExcelReport?countyName='+countyName+'&editon='+edition+'&countryFipsCode='+countryFipsCode+'&stateFipsCode='+stateFipsCode+'&countyFipsCode='+countyFipsCode+'&stateCode='+stateCode+'&state='+stateName+'&edition='+edition;
	        	}else if (data.status == "NOT EXISTS") {
	        		verifySourceData();
	        		
				}else {
					alert("Processing Error.Please try after some time");
				}
	        }
		});
	}
   function ajaxCallForSaveData()
   {
	   $.ajax({
		   //url: 'saveData?countyName=' + $("#hid").val()+'&stateName='+$("#state").val(),
		   url: 'saveData?countyName=' + $("#hid").val()+'&stateName='+$("#state").val()+'&edition='+$("#editon").val(),
		   success: function(result){
			   $("#saveDataMSG").show();
		       $("#saveDataMSGSPAN").html(result);
	    }});

   }
   </script>
</head>
<body >

<div data-ng-controller="ExportDataController">
      <button ng-click="exportCsv()" class="btn btn-warning" style="float:right;">Export CSV</button>
      <button ng-click="exportXl()" class="btn btn-warning" style="float:right;">Export XL</button>
       <a href="" class="btn btn-warning" style="float:right;" onclick="ajaxCallForSaveData()">Save Data</a>
     <!--  <a href="report/downloadExcelReport" class="btn btn-warning" style="float:right;">Generate Excel Report</a> -->
       <a id = "lnk" href = "javascript:;" onclick="linkCall()" class="btn btn-warning" style="float:right;">Generate Excel Report</a>
        <input type="hidden" id="hid">
        <input type="hidden" id="state">
        <input type="hidden" id="editon">
        <input type="hidden" id="stateFipsCode">
        <input type="hidden" id="countyFipsCode">
        <input type="hidden" id="countryName">
        <input type="hidden" id="countryFipsCode">
        <input type="hidden" id="stateCode">
      
      <a href="report/downloadTextReport"  class="btn btn-warning" style="float:right;">Generate Text Report</a>
      <div id="saveDataMSG" style="display:hidden">
        <span id="saveDataMSGSPAN" class="ui-grid-exporter-csv-link"></span>
      </div>
      
 <!-- 
  <a href="report/downloadExcelReport" class="btn btn-default">Generate Excel Report</a> 
  <a href="report/downloadTextReport" class="btn btn-default">Generate Text Report</a>
  -->     
      <div class="custom-csv-link-location">
        <span class="ui-grid-exporter-csv-link">&nbsp</span>
      </div>
<div ui-grid="ExportDataGridOptions" ui-grid-edit ui-grid-pagination ui-grid-exporter ui.grid.resizeColumns class="grid" style="height:555px;margin-top: 17px;">
<div class="well grid-loading" ng-show="grid.rows.length == 0">
    <img src="img/ajax-loader.gif " id="loadingImage" style="display: block; margin: 0 auto; width: 100px; height:100px;">
 </div>
 
</div>
</div>
<span id="showDialogForVerification"></span>
</body>
</html>