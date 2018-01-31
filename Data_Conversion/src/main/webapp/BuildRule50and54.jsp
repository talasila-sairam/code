<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="individualFieldApp">
<head>
<% 
	String columnValues=request.getParameter("ruleColumns");
	columnValues=columnValues.trim();
	String fieldAliasName=request.getParameter("fieldAliasNameValue");
	String baseQuery=request.getParameter("baseQuery");
	String optionValues=request.getParameter("optionValues");
	String optionsToShow=request.getParameter("optionsToShow");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
 <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.js"></script>
 <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-touch.js"></script>
 <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-animate.js"></script>
 <script src="http://ui-grid.info/release/ui-grid.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://ui-grid.info/release/ui-grid.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link href=css/main.css rel=stylesheet type=text/css/>
<link href=css/manual-style.css rel=stylesheet type=text/css/>
<title>Build Rule</title>
</head>
<script type="text/javascript">
var app = angular.module('individualFieldApp', ['ngTouch', 'ui.grid','ngAnimate']);
app.controller('individualFieldController', ['$scope', '$http', function($scope, $http) {
	$scope.gridOptions = {
			onRegisterApi: function(gridApi){
			$scope.gridApi = gridApi;
			},
			enableSorting: false,
			headerHeight: 20
			enableFiltering: true
		};
	var ruleColumns=$("#ruleColumns").val();
	$('#commonColumn').append(ruleColumns);
	$('#ruleColumn').append(ruleColumns);
	$scope.loadRule=function(){
	var selectedTableNames = [];
		var selectedTables = localStorage.getItem("setselectedTableNamesText");
		if(selectedTables.indexOf(",") > 0){
			selectedTableNames = selectedTables.split(",");
		}
		selectedTableNames.push(selectedTables);
		console.log(selectedTableNames);
		alert(selectedTableNames);
		var commanColumn=$('#commonColumn').val();
		var selectedColumn=$('#ruleColumn').val();
		var selectedDateformat=$('#loadSelectDateformat').val();
		var selectedRule=$('#loadSelectDate').val();
		var baseQuery = $("#baseQuery").val();
		var fieldAliasName = $("#fieldAliasName").val();
		var baseQueryArr = baseQuery.split("FROM");

		if(selectedTableNames.length > 1){
			commanColumn=commanColumn.split("-");
			commanColumn= commanColumn[1]+".["+commanColumn[0]+"]";
			selectedColumn=selectedColumn.split("-");
			selectedColumn=selectedColumn[1]+".["+selectedColumn[0]+"]";
			selectedColumn = selectedColumn.replace('*',' ');
		}
		else{
			commanColumn=commanColumn.split("-");
			commanColumn=commanColumn[0];
			selectedColumn=selectedColumn.split("-");
			selectedColumn=selectedColumn[0];
			selectedColumn = selectedColumn.replace('*',' ');
/* ||||||| .r565
=======
		//alert(baseQueryArr[1]);
		commanColumn=commanColumn.split("-");
		var commanColumnAlias=commanColumn[1];
		var commanColumnName=commanColumn[0];
		selectedColumn=selectedColumn.split("-");
		selectedColumnName=selectedColumn[0];
		var selectedColumnAllias=selectedColumn[1];
		selectedColumnName = selectedColumnName.replace('*',' '); 
 */	//	baseQueryArr=baseQueryArr[1].trimLeft();
	//	baseQueryArr=baseQueryArr.split(" ");
	var loadDateType=$('#loadSelectDate').val();
	alert(loadDateType);
	if(loadDateType=="Load Date"){
				if(selectedDateformat=="mm-dd-yy"){
					var queryString="select "+commanColumnAlias+".["+commanColumnName+"] APPRCL, case WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16"+
					"THEN STUFF("+selectedColumnAllias+".["+selectedColumnName+"],5,0,'20') WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) > 16 THEN STUFF("+selectedColumnAllias+".["+selectedColumnName+"],5,0,'19')"+
					"END AS  "+fieldAliasName+" from "+baseQueryArr[1];
				}
				else if(selectedDateformat=="dd-mm-yy"){
					var queryString="select "+commanColumnAlias+".["+commanColumnName+"] APPRCL,case WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16"+
					"THEN STUFF(concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), '', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 1, 2), '', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 2)),5,2,'20')"+
					"WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) > 16 THEN STUFF(concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), '', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 1, 2), '',"+
					"SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 2)),5,2,'19')END AS  "+fieldAliasName+" from "+baseQueryArr[1];
				}
				else if(selectedDateformat=="dd-mm-yyyy"){
					var queryString="select "+commanColumnAlias+".["+commanColumnName+"] APPRCL,concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), '',"+
							"SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 1, 2), '', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 4)) "+fieldAliasName+" from "+baseQueryArr[1];
				}
		    }
	else if(loadDateType="Load Max Date"){
		if(selectedDateformat=="mm-dd-yy"){
			var queryString="select "+commanColumnAlias+".["+commanColumnName+"] APPRCL,replace(convert(varchar(10), MAX(recordDate),110), '-', '') recordDateStr"+ 
			" from (select "+selectedColumnAllias+".["+selectedColumnName+"], WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16 THEN STUFF("+selectedColumnAllias+".["+selectedColumnName+"],5,0,'20') WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16"+ 
			" THEN STUFF("+selectedColumnAllias+".["+selectedColumnName+"],5,0,'19') END AS recordDateString,case WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16 THEN convert(datetime, STUFF(concat(SUBSTRING(SRSLDT, 5, 2), '-',"+
			" SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), 1, 0,'20')) WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) > 16"+
			" THEN convert(datetime, STUFF(concat("+selectedColumnAllias+".["+selectedColumnName+"], 5, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"] 1, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"] 3, 2)), 1, 0,'19'))"+
			" END AS recordDatefrom  from "+baseQueryArr[1]+" where "+selectedColumnAllias+".["+selectedColumnName+"] NOT LIKE '000000') a where"+selectedColumnAllias+".["+selectedColumnName+"] in (select "+selectedColumnAllias+".["+selectedColumnName+"] from "+baseQueryArr[1]+" having count("+selectedColumnAllias+".["+selectedColumnName+"]>1) group by "+selectedColumnAllias+".["+selectedColumnName+"]";

		}

		baseQueryArr=baseQueryArr[1].trimLeft();
		
		if(selectedTableNames.length > 1){
		
			if(selectedDateformat=="mm-dd-yy"){
				var queryString="select "+commanColumn+" APPRCL, case WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) < 16 THEN STUFF("+selectedColumn+",5,0,'20') WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) > 16 THEN STUFF("+selectedColumn+",5,0,'19') END AS  "+fieldAliasName+" from "+baseQueryArr;
			}
			else if(selectedDateformat=="dd-mm-yy"){
				var queryString="select "+commanColumn+" APPRCL,case WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) < 16 THEN STUFF(concat(SUBSTRING("+selectedColumn+", 3, 2), '', SUBSTRING("+selectedColumn+", 1, 2), '', SUBSTRING("+selectedColumn+", 5, 2)),5,2,'20') WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) > 16 THEN STUFF(concat(SUBSTRING("+selectedColumn+", 3, 2), '', SUBSTRING("+selectedColumn+", 1, 2), '', SUBSTRING("+selectedColumn+", 5, 2)),5,2,'19')END AS  "+fieldAliasName+" from "+baseQueryArr;
			}
			else if(selectedDateformat=="dd-mm-yyyy"){
				var queryString="select "+commanColumn+" APPRCL ,concat(SUBSTRING("+selectedColumn+", 3, 2), '', SUBSTRING("+selectedColumn+", 1, 2), '', SUBSTRING("+selectedColumn+", 5, 4)) "+fieldAliasName+" from "+baseQueryArr;
			}	
		else if(selectedDateformat=="dd-mm-yy"){
			var queryString="select "+commanColumnAlias+".["+commanColumnName+"] APPRCL,replace(convert(varchar(10), MAX(recordDate),110), '-', '') recordDateStr from"+
			" (select "+selectedColumnAllias+".["+selectedColumnName+"], case WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16 "+
			" THEN STUFF("+selectedColumnAllias+".["+selectedColumnName+"], 5, 0,'20')"+
			" WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) > 16 THEN"+
			" STUFF("+selectedColumnAllias+".["+selectedColumnName+"], 5, 0,'19') END AS recordDateString,"+
		    " case WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) < 16 THEN"+
		    " convert(datetime, STUFF(concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 2), '-',"+
		    " SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 1, 2)), 1, 0,'20'))"+
		    " WHEN convert(int,STUFF("+selectedColumnAllias+".["+selectedColumnName+"],1,4,'')) > 16 THEN"+
		    " convert(datetime, STUFF(concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2), '-',"+
		    " SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3, 2)), 1, 0,'19')) END AS recordDate"+
		    "  from "+baseQueryArr[1]+" where "+selectedColumnAllias+".["+selectedColumnName+"] NOT LIKE '000000') a where "+
		    " "+selectedColumnAllias+".["+selectedColumnName+"] in"+
		    " (select "+selectedColumnAllias+".["+selectedColumnName+"] from  "+baseQueryArr[1]+" group by "+selectedColumnAllias+".["+selectedColumnName+"] having count("+selectedColumnAllias+".["+selectedColumnName+"])>1) group by "+selectedColumnAllias+".["+selectedColumnName+"]";
			
		}
/* <<<<<<< .mine
		else{
/* 			baseQueryArr=baseQueryArr.split(" "); */

			/* if(selectedDateformat=="mm-dd-yy"){
				var queryString="select "+commanColumn+" APPRCL, case WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) < 16 THEN STUFF("+selectedColumn+",5,0,'20') WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) > 16 THEN STUFF("+selectedColumn+",5,0,'19') END AS  "+fieldAliasName+" from "+baseQueryArr;
			}
			else if(selectedDateformat=="dd-mm-yy"){
				var queryString="select "+commanColumn+" APPRCL,case WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) < 16 THEN STUFF(concat(SUBSTRING("+selectedColumn+", 3, 2), '', SUBSTRING("+selectedColumn+", 1, 2), '', SUBSTRING("+selectedColumn+", 5, 2)),5,2,'20') WHEN convert(int,STUFF("+selectedColumn+",1,4,'')) > 16 THEN STUFF(concat(SUBSTRING("+selectedColumn+", 3, 2), '', SUBSTRING("+selectedColumn+", 1, 2), '', SUBSTRING("+selectedColumn+", 5, 2)),5,2,'19')END AS  "+fieldAliasName+" from "+baseQueryArr;
			}
			else if(selectedDateformat=="dd-mm-yyyy"){
				var queryString="select "+commanColumn+" APPRCL ,concat(SUBSTRING("+selectedColumn+", 3, 2), '', SUBSTRING("+selectedColumn+", 1, 2), '', SUBSTRING("+selectedColumn+", 5, 4)) "+fieldAliasName+" from "+baseQueryArr;
			}
		} */
//||||||| .r565
//======= */
		else if(selectedDateformat=="dd-mm-yyyy"){
			var queryString="select "+commanColumnAlias+".["+commanColumnName+"] APPRCL,replace(convert(varchar(10), MAX(recordDate),110), '-', '') recordDateStr"+
			" from (select "+selectedColumnAllias+".["+selectedColumnName+"], concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 1, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"],"+
			" 3, 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 4)) AS recordDateString,"+
			" convert(datetime, concat(SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 5, 4), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 3,"+
			" 2), '-', SUBSTRING("+selectedColumnAllias+".["+selectedColumnName+"], 1, 2))) AS recordDate from "+baseQueryArr[1]+ " where "+
			" "+selectedColumnAllias+".["+selectedColumnName+"] NOT LIKE '000000') a where "+selectedColumnAllias+".["+selectedColumnName+"] in"+
			" (select "+selectedColumnAllias+".["+selectedColumnName+"] from "+baseQueryArr[1]+" group by "+selectedColumnAllias+".["+selectedColumnName+"] "+
			" having count("+selectedColumnAllias+".["+selectedColumnName+"])>1) group by "+selectedColumnAllias+".["+selectedColumnName+"]";
		
			}
	}
			else if(loadDateType="Load 2nd Max Date"){
				
			}
		
		
		 var specialChars = ['%','&','+','#'];
		var codeNumbersForSplChars = ['%25','%26','%2B','%23'];
		for(var sp = 0 ; sp < specialChars.length;sp++){
			if(queryString.indexOf(specialChars[sp]) >0){
				queryString = queryString.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
		    }
		}
	
		var colArr = [];
		 if (queryString != "") {
		    $http.get('./executeQuery?input_query='+queryString).success(function(response) {
		        for (i = 0; i < response.length; i++) {
		            var dataMap = response[i];
		            colCount = 0;
		            for (var key in dataMap) {
		                colArr[colCount] = {
		                    name: key,
		                    displayName: key,
		                    width: '20%'
		                }
		                colCount = colCount + 1;
		            }
		        }
		        $scope.gridOptions = {
		            columnDefs: colArr,
		            enableSorting: false,
		    		headerHeight: 20,
		    		enableFiltering: true
		        };
		        $scope.gridOptions.data = response;
		        $scope.gridOptions.enableColumnResizing = true;
		        selectedTableNames = [];
		        var noOfRows = $scope.gridOptions.data.length;
                $("#NoOfRows").text("");
                $("#NoOfRows").text("No Of Rows : " + noOfRows);
		    });
		    
		} 
		alert(queryString);
		$('#ruleQuery').val(queryString);
		/* selectedTableNames = [];
        window.localStorage.removeItem("setselectedTableNamesText"); */
	}
	$scope.saveRule=function(){
		
	}
}]);
</script>
<body ng-controller="individualFieldController">
<table id = 'fixedColumns' style="margin-left: 10px;margin-left: 10px;">
	    <tr>
		    <td><label class="control-label">Common Column :</label></td>
		    <td><select id="commonColumn" class="form-control Column">
            	<option value="">--Please Select--</option>
            </select></td> 
		    <td><label class="control-label">Rule :</label></td>
		    <td><select id="ruleColumn" class="form-control Column">
            	<option value="">--Please Select--</option>
            </select></td>  
	    	<td><label class="control-label"></label></td>
	    </tr>
    
    </table>
    <div style="width: 99%;    background-color: ghostwhite;">
     <table id ="buildRuleTable" style="margin-left: 10px;    margin-top: 13px;">
    	<tr id = 'cancel-0'>
	    	<td><label class="control-label">Rule :</label></td>
	    	<td>
	    		<select id="loadSelectDate"  class="form-control ruleTypeCheck">
	                    <option value="">--Please Select--</option>
	                    <option value="Load Max Date">Load Max Date</option>
	                    <option value="Load 2nd Max Date">Load 2nd Max Date</option>
	                    <option value="Load Date">Load Date</option>
	                  </select>
	    	</td>
	    	<td><label class="control-label">Format:</label></td>
	    	<td>
	    		<select id="loadSelectDateformat"  class="form-control ruleTypeCheck">
	                    <option value="">--Please Select--</option>
	                    <option value="mm-dd-yy">mm-dd-yy</option>
	                    <option value="dd-mm-yy">dd-mm-yy</option>
	                    <option value="dd-mm-yyyy">dd-mm-yyyy</option>
	            </select>
	    	</td>
    	</tr>  
    	<tr>
    	<button class='btn btn-warning runRuleQuery' id ='runRuleQuerydata' ng-click="saveRule();" style="float: right;margin-right: 18px;">Save</button>
    	<button class='btn btn-warning runRuleQuery' id ='runRuleQuerydata' ng-click="loadRule();" style="float: right;margin-right: 18px;">Run</button>
    	</tr>  	
    </table>
    <div>
    	<textarea id="ruleQuery" rows="5" cols="3" name="textarea" class="auto form-control test"  style="overflow: scroll;margin-left: 10px; word-wrap: break-word; resize: horizontal;height:69px;margin-top:10px; width: 99%;color: slategrey" spellcheck="false" data-toggle="tooltip" title = "" disabled >${rule.baseQuery}</textarea>
    </div>
    
    <div class = "row">
        	<div class="col-md-12">
              <label class="control-label" id = "NoOfRows"></label>
         	</div>
         </div>
    
    <div id="grid1" ui-grid="gridOptions" class="grid" style="width:1322px;  height: 520px;  margin-top: 10px;margin-left: 10px;overflow-x: scroll;"></div>
    </div>
    <input type=hidden id="fieldAliasName" value="<%=fieldAliasName%>"/> 
<input type=hidden id="baseQuery" value="<%=baseQuery%>"/> 
<input type=hidden id="optionValues" value="<%=optionValues%>"/> 
<input type=hidden id="optionsToShow" value="<%=optionsToShow%>"/> 
<input type=hidden id="ruleColumns" value="<%=columnValues%>"/> 
</body>
</html>