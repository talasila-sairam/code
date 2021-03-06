<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*"%>
<html ng-app="ruleapp">
<head>
<% 
	String columnValues=request.getParameter("ruleColumns");
	columnValues=columnValues.trim();
	String fieldAliasName=request.getParameter("fieldAliasNameValue");
	/* String baseQuery=request.getParameter("baseQuery"); */
	String optionValues=request.getParameter("optionValues");
	String optionsToShow=request.getParameter("optionsToShow");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular-animate.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
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
        <script src="js/buildrulepopupgrid.js"></script>
<title>Insert title here</title>
<script>
var baseQuery = sessionStorage.getItem("baseQuery");
alert(baseQuery);
$("#baseQuery").val(baseQuery);
</script>
</head>

<body ng-controller="ruleQueryController"> 
<div>
<table id = 'fixedColumns' style="margin-left: 10px;">
	    <tr>
		    <td><label class="control-label">Common Column :</label></td>
		    <td><select id="commonColumn" class="form-control Column">
            	<!-- <option value="">--Please Select--</option> -->
            </select></td> 
		    <td><label class="control-label">Rule :</label></td>
		    <td><select id="ruleColumn" class="form-control Column">
            	<!-- <option value="">--Please Select--</option> -->
            </select></td>  
	    	<td><label class="control-label"></label></td>
  			<td><button class='btn btn-warning addRow' id = 'addRow'>addRow</button></td>
	    </tr>
    
    </table>
      <div id="operationsDiv" style = "margin-top: 10px;height: 153px;overflow-y: scroll;background-color: #eee;    margin-left: 10px;width: 98%;">
    <table id ="buildRuleTable" style="margin-left: 10px;">
    	<tr id = 'cancel-0'>
	    	<td><label class="control-label">Rule :</label></td>
	    	<td>
	    		<select id="ruleType-0"  class="form-control ruleTypeCheck">
	                    <option value="">--Please Select--</option>
	                    <option value="CONCAT">Concat</option>
	                    <option value="REPLACE">Replace</option>
	                    <option value="REPLACE FIRST">Replace First</option>
	                    <option value="REPLACE LAST">Replace Last</option>
	                    <option value="REPLACE INDEX">Replace Index</option>
	                    <option value="DIVIDE BY">Divide By</option>
	                    <option value="MULTIPLY BY">Multiply  By</option>
	                    <option value="PUT DECIMAL">Put Decimal</option>
	                    <option value="UPLOAD EXCEL">UPLOAD EXCEL</option>
	                    <!-- <option value='PUT LENGTH'>Put Length</option> -->
	                    <!--<option value="STRIP OUT">Strip Out</option>
	                    <option value="ENCLOSE PARENTHESES">Enclose Parentheses</option> -->
	                  </select>
	    	</td>
    	</tr>    	
    </table>
    	<div id="ruleInfo"></div>
    	<div class="divClass"  style = "position:abosulte" hidden>
    	<div style="border: 1px solid #e78f08;background: #e78f08 50% 50% repeat-x;color: #ffffff;height: 40px;text-align: left;margin-top: -17px;margin-bottom: 10px;border-radius: 4px;width: 100.1%;">
    	<lable style ="float: left;margin: 0.1em 0;font-weight: bold;margin-left: 15px;margin-top: 6px;">When Condition</lable>
    	<img class="cancelRightEnd" src="img/cancel.ico" style="float: right;" />
    	</div>
			<iframe id="submenuPagesDisplay" class = 'iframeForModal' src="whereClauseInBuildRule.jsp" style = "width: 1025px;height: 100px;padding: 4px;margin:5px;border-color: rgba(16, 181, 181, 0.55);background-color: oldlace" ></iframe>
		</div>
    </div>
 	<div id = "buttons" style = "margin:5px;">
    <button class='btn btn-warning runRuleQuery' id ='runRuleQuery' style="float: right;margin-right: 18px;">Save</button>
    <button class='btn btn-warning getRule' id ='getRuleQuery' style="float: right;">Run</button>
    </div>
      <textarea id="ModifiedRuleQuery" rows="5" cols="3" name="textarea" class="auto form-control test"  style="overflow: scroll;margin-left: 10px; word-wrap: break-word; resize: horizontal;height:69px;margin-top:10px; width: 98%;color: slategrey" spellcheck="false" data-toggle="tooltip" title = "" disabled >${rule.baseQuery}</textarea>
<input type=hidden id="fieldAliasName" value="<%=fieldAliasName%>"/> 
<input type=hidden id="baseQuery" value=""/> 
<input type=hidden id="optionValues" value="<%=optionValues%>"/> 
<input type=hidden id="optionsToShow" value="<%=optionsToShow%>"/> 
<input type=hidden id="ruleColumns" value="<%=columnValues%>"/> 
<input type="hidden" id="ModifiedBuildRuleQuery" value=""/>
<input type="hidden" id="buildRuleQuery" value=""/>
<input type="hidden" id="buildRuleQuery71" value=""/>
<input type="hidden" id="buildRuleQuery72" value=""/>
<input type="hidden" id="buildRuleQuery73" value=""/>


 		<div class = "row">
        	<div class="col-md-12">
              <label class="control-label" id = "NoOfRows"></label>
         	</div>
         </div>

<div id="grid1" ui-grid="gridOptions" class="grid" style="width:1322px;  height: 520px;  margin-top: 10px;margin-left: 10px;overflow-x: scroll;"></div>
</div>
<div id = "alertmsg"></div>
</body>
</html>