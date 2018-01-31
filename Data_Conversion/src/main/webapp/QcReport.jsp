<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<script type="text/javascript" src="js/QcReport.js"></script>
<link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<style>
.blue{ 
	color: blue;  
}
.ui-grid{
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
<script>
$(document).ready(function() {

	$.ajax({
		type : "POST",
		url : "getCountriesList",
	success : function(countries) {
			$("#countrySelect").empty();
			$("#countrySelect").append("<option value=''>--Please Select--</option>");
			for ( var i in countries) {
				$("#countrySelect").append(function() {
					return $("<option />", {
						value : countries[i].countryCode,
						text : countries[i].countryName
					})
				});
			}

		}

	})
	var stateArray=[];
		 $.ajax({
				type: "POST",
				url: 'getStates',
				dataType: 'json', 	
				contentType: "application/json",
				success: function (response) {
				 for(var i=0;response.length>i;i++){
					 stateArray.push(response[i][1]+"-"+response[i][0]);
				 }
				}
		 });
	  $(document).on('focus','.countiessearch',function(){
	       	var fieldId=this.id;
	         $("#"+fieldId).autocomplete({
	          minLength: 1,
	          source: function(request, response) {
	           var filteredArray = $.map(stateArray, function(item) {
	           if( item.startsWith(request.term.toUpperCase())){
	           return item;
	           }
	           else{
	           return null;
	           }
	           });
	           response(filteredArray);
	           },
	           select: function(event, ui) {
	            var searchResult = ui.item.label;
	            $("#"+fieldId).val(searchResult);
	            },
	          });
	 
	          });
});
</script>
<body ng-controller="Qcreport">
<div style="padding-left: 10px;margin-top: 10px;">
<table>
<tr>
<td><select id="countrySelect" class="form-control">
</select></td>
<td><input type='text' id="stateselect" class='form-control countiessearch' placeholder='search state' style="width: 127px;margin-left: 6px;"></td>
<td><input type='text' id="countselect" class='form-control statesearch' style="margin-left: 6px; width: 127px;" placeholder='search county'></td>
<td><label>Edition Year</label></td>
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
 <td><select id="columnsForQualityCheck" class="form-control  col-md-3" multiple="multiple">
	 <option value="APPRCL">APPRCL</option>
    <option value="ASSESSEEOWNERNAME">ASSESSEEOWNERNAME</option>
     <option value="SECOND_ASSESSEEOWNERNAME">SECOND_ASSESSEEOWNERNAME</option>
     <option value="ASSEMAILCAREOFNAME">ASSEMAILCAREOFNAME</option>	
     <option value="APNORPINNUMBER">APNORPINNUMBER</option>				    
</select>
</td>
 <td><button type="button" id="swapData" class="form-group btn btn-success" ng-click="getDatForQC()" style="margin-left: 6px;">Submit</button></td>					
<td><button type="button" class="btn btn-success" ng-click="getSelectedParcelNos()" style="float:right;">Remove</button></td>
</tr></table>
</div>
<div id="grid1" ui-grid-selection ui-grid="gridOptions" class="ui-grid" style="width:1322px;  height: 485px;  margin-top: 10px;margin-left: 10px;overflow-x: scroll;">
	<div class="well grid-loading" ng-show="grid.rows.length == 0">
	    <div id="loadingImage"></div>
	</div> 
</div>
</body>
</html>