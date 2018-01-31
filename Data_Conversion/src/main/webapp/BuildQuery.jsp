<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="gridapp">
<head>
<%@page import="java.util.*"%>


<%-- <%
String columnValues=request.getParameter("columnValues");
String str[] = {};
List columnValue = new ArrayList();
if(columnValues != ""){
	str=columnValues.split(",");
	columnValue = new ArrayList(Arrays.asList(str));	
	System.out.println(columnValue);
}
String columnOptions=request.getParameter("columnOptionsText");
String valuesStr[] = {};
List columnOption = new ArrayList();
if(columnOptions != ""){
	valuesStr=columnOptions.split(",");	
	columnOption = new ArrayList(Arrays.asList(valuesStr));
	System.out.println(columnOption);
}

/* String selectedTableNamesText=request.getParameter("selectedTableNamesText"); */
String tableNamesSelected=request.getParameter("tableNamesSelected");
%> --%>

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
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-multiselect.css">
	<link rel="stylesheet" href="css/jquery-ui.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-multiselect.js"></script>
	<script src="js/jquery-ui.js"></script>
	<link href="css/main.css" rel="stylesheet" type="text/css"/>
	<link href="css/manual-style.css" rel="stylesheet" type="text/css"/>
	<!-- <script type="text/javascript" src="js/app.js"></script> -->
	<script src=js/modelJavaScript.js></script>
	<script src=js/subQueryInWhereClause.js></script>
	<script src=js/buildQueryJavaScript.js></script>

	<script>
	
	$(function () {
            $('.countyTableColumnsForMainQuery').multiselect({
            	enableFiltering: true,
            	includeSelectAllOption: true,
            	maxHeight: 150,
            	nonSelectedText: '--Please Select--',
            	disableIfEmpty: true,
            	enableCaseInsensitiveFiltering : true
            });
    
            $(".countyTableColumnsForMainQuery").multiselect('rebuild');
            
            
            $('.countyTableColumnsForMainQueryOrderBy').multiselect({
            	enableFiltering: true,
            	includeSelectAllOption: true,
            	maxHeight: 150,
            	nonSelectedText: '--Please Select--',
            	disableIfEmpty: true,
            	enableCaseInsensitiveFiltering : true
            });
            $(".countyTableColumnsForMainQueryOrderBy").multiselect('rebuild');
        });
	
	
	
	$('textarea[name=textarea]').tooltip({
    	placement: "top",
    	trigger: "focus",
    	width:'auto',
    	height:'auto'
    }); 
	
	   $(document).ready(function(){ 
	        $(window).scroll(function(){ 
	            if ($(this).scrollTop() > 100) { 
	                $('#scroll').fadeIn(); 
	            } else { 
	                $('#scroll').fadeOut(); 
	            } 
	        }); 
	        $('#scroll').click(function(){ 
	            $("html, body").animate({ scrollTop: 0 }, 600); 
	            return false; 
	        }); 
	    });
	</script>
	
	<style type = "text/css">
#plannerTable {
    border-collapse: collapse;
    width: 103% !important;
    
} 

th, td {
    
    padding: 3px;
} 

			.alertErrorMain{
				padding: 5px;
    			text-align: center;
			}
			.alert .close {
    			font-size: 24px;
    			line-height: 15px;
			}
			 #editResultQuery:hover {
    			content: url('img/edit.png');
		    	margin-top: 15px;
		    	height: 19px;
		    	margin-right: 10px;
			 }
			#saveResultQuery:hover{
	 			content: url('img/save-hover.png');
    			margin-top: 15px;
    			height: 19px;
	 		}
		</style>
</head>
<body ng-controller="gridcontroller">

	<div class="alert alert-warning alertErrorMain" id = "alertForLikeOperator" hidden ="true" role="alert"></div>
    <div class="alert alert-danger alertErrorMain" id = "alertWhileFieldIsEmpty" hidden = "true" role="alert"></div>
    <div class="alert alert-warning alertErrorMain" id = "alertForInOperator" hidden = "true" role="alert"></div>
    
<%-- <input id = "columnValues" value = "<%=columnValue%>" hidden = true>
<input id = "columnOption" value = "<%=columnOption%>" hidden = true>

<input id = "selectedTableNamesText" value = "<%=selectedTableNamesText%>" hidden = true>
<input id = "tableNamesSelected" value = "<%=tableNamesSelected%>" hidden = true> --%>



<input id = "columnValues" value = "" hidden = true>
<input id = "columnOption" value = "" hidden = true>
<input id = "tableNamesSelected" value = "" hidden = true>


<div id = "buildQueryDiv" style = "max-height:300px;overflow:scroll; border: 1px solid black;">
	<table id ="plannerTable">
    <tr class="border_bottom">
    	<th><label class="control-label ">Start</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">Functions</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label required">Columns</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label required">Operators</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label required">Value</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label required">Logical Operator</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">End</label></th>
    </tr>
    
    <tr class="border_bottom">
    	<td>
    		<input type="checkbox" id="rowStart-0" class = "checkBoxStyle" style = "margin: 0px 14px"/>
    	</td>
    	
    	<td><label class="control-label">  </label></td>
			<td><select id="otherFunction-0" class="form-control countyTableColumns">
                    <option value="">--Please Select--</option>
                    <option value="Convert">Convert</option>
                    <option value="Len">Length</option>
                    <!-- <option value="Between">Between</option> -->
                  </select></td>
            
            <td><label class="control-label">  </label></td>
            <td><select id="col-0" class="form-control countyTableCol">
            	<option value="">--Please Select--</option>
            	<%-- <% Iterator iterator=columnValue.iterator();
                	Iterator iterator1=columnOption.iterator();
				 	 while(iterator.hasNext()||iterator1.hasNext()){
				 		String columnOptionToShow=(String)iterator1.next();
				 		String columnValuesToShow=(String)iterator.next();
				 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
				 	}
				 %> --%>
            </select></td>
	
	      	<td><label class="control-label">  </label></td>
            <td><select id="operators-0"  class="form-control operatorInMainQuery">
                    <option value="">--Please Select--</option>
                    <option value="=">=</option>
                    <option value="<>"><></option>
                    <option value="like">like</option>
                    <option value="not like">not like</option>
                    <option value="<"><</option>
                    <option value=">">></option>
                    <option value="<="><=</option>
                    <option value=">=">>=</option>
                    <option value="is null">is null</option>
                    <option value="is not null">is not null</option>
                    <option value="in">in</option>
                    <option value='not in'>not in</option>
                    <option value="between">between</option>
                  </select></td>
            
            <td><label class="control-label">  </label></td>
			<td><select id="matchCondition-0" class="form-control countyTableColumns" disabled>
                    <option value="">--Please Select--</option>
                    <option value="Start">Start</option>
                    <option value="End">End</option>
                    <option value="Between">Between</option>
                  </select></td>
            
            
            <td><label class="control-label">  </label></td>
            <td><select id="value-0" class="form-control operatorInMainQuery">
            	<option value="">--Please Select--</option>
            	<%-- <% Iterator iterator2=columnValue.iterator();
                	Iterator iterator3=columnOption.iterator();
				 	 while(iterator.hasNext()||iterator2.hasNext()){
				 		String columnOptionToShow=(String)iterator3.next();
				 		String columnValuesToShow=(String)iterator2.next();
				 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
				 	}
				 %> --%>
            </select></td>
            
            <td><label class="control-label">  </label></td>
    		<td><input type="text" id="valueText-0" class="form-control"></td> 
         	
            <td><label class="control-label">  </label></td>
			<td><select id="columns-0" class="form-control countyTableColumns" disabled>
                    <option value="">--Please Select--</option>
                    <option value="And">And</option>
                    <option value="Or">Or</option>
                    <option value="Not">Not</option>
                  </select></td>
                  
            <td><label class="control-label">  </label></td>
			<td>
    			<input type="checkbox"  id="rowEnd-0" class = "checkBoxStyle" style = "margin: 0px 14px"/>
    		</td>
    		
    		<td><label class="control-label">  </label></td>
    		<td>
    			<button class='btn btn-warning iframeForSubquery' id = 'subQueryButton-0' style="display: none;">subQuery</button>
    		</td>
        </tr>
      </table>
      
      <div class="subQueryClass" hidden style = "margin-bottom: 10px;position: relative;border-radius: 4px">
    	<a class="cancelRightEnd" id = "cancelForIframeSubQuery"></a>
    	<div style="border: 1px solid #e78f08;background: #e78f08 50% 50% repeat-x;color: #ffffff;height: 40px;text-align: left;margin-top: -17px;margin-bottom: 10px;border-radius: 4px;width: 102.4%;margin-left: -15px"><lable style ="float: left;margin: 0.1em 0;font-weight: bold;margin-left: 15px;margin-top: 6px;">SubQuery</lable></div>
	 </div>
	 
	 <div id="subQueryDiv"></div>
	 <div id = "innerSubQueryDiv"></div>
	 
	 <table id = "groupByOrderByHavingFields">
	 <tr class = "groupBy">
	 	<td><label class="control-label">Group By</label></td>
	 	<td><label class="control-label required">Columns :</label></td>
	 	<td><select multiple  id="columnsForMainQueryGroupBy" class="form-control countyTableColumnsForMainQuery">
		    	<%-- <% Iterator iterator4=columnValue.iterator();
                	Iterator iterator5=columnOption.iterator();
				 	 while(iterator.hasNext()||iterator4.hasNext()){
				 		String columnOptionToShow=(String)iterator5.next();
				 		String columnValuesToShow=(String)iterator4.next();
				 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
				 	}
				 %>  --%>
			</select></td>
	 </tr>
	 <tr class = "Having" style="display: none;">
	 <td><label class="control-label">Having</label></td>
	 <td><label class="control-label required">Functions :</label></td>
	 <td><select id="functionsForMainQuery" class="form-control countyTableFunctionForMainQuery ">
		            	<option value="">--Please Select--</option>
		            	<option value="COUNT">Count</option>
		            	<option value="AVG">Avg</option>
		            	<option value="MAX">Max</option>
		            	<option value="MIN">Min</option>
		            	<option value="CAST">Cast</option>
		            	<option value="CONVERT">CONVERT</option>
            </select></td>
	<td><label class="control-label required">Columns :</label></td>
	<td><select id="columnsForMainQueryHavingBy" class="form-control countyTableFunction ">
		            	<option value="">--Please Select--</option>
		            <%-- <% Iterator iterator6=columnValue.iterator();
		                	Iterator iterator7=columnOption.iterator();
						 	 while(iterator.hasNext()||iterator6.hasNext()){
						 		String columnOptionToShow=(String)iterator7.next();
						 		String columnValuesToShow=(String)iterator6.next();
						 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
						 	}
						 %> --%> 
	</select></td>        
	<td>
                            <label class='control-label required'>Operator :</label>
                        </td>
                        <td><select id="mainQueryHavingByOperator" class="form-control countyTableFunctionMainQuery ">
				            	<option value="">--Please Select--</option>
				            	<option value='='>=</option>
                                <option value='>='>>=</option>
                                <option value='like'>like</option>
                                <option value='not like'>not like</option>
                                <option value='<='><=</option>
                                <option value='>'>></option>
                                <option value='<'><</option>
				            </select></td>
                        <td>
                            <label class='control-label required'>Value :</label>
                        </td>
                        <td>
                            <select id="mainQueryHavingByValueSelect" class="form-control countyTableFunctionMainQuery ">
                                <option value="">--Please Select--</option>
                               <%--  <% Iterator iterator12=columnValue.iterator();
				                	Iterator iterator13=columnOption.iterator();
								 	 while(iterator12.hasNext()||iterator13.hasNext()){
								 		String columnOptionToShow=(String)iterator13.next();
								 		String columnValuesToShow=(String)iterator12.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								 	}
								 %> --%>
                            </select>
                        </td>
                        <td>
                            <label class='control-label'> </label>
                        </td>
                        <td>
                            <input type='text' id='mainQueryHavingByValueText' class='form-control countyTableFunctionMainQuery'>
                        </td>    
	 </tr>
	 <tr class = "orderBy">
	 	<td><label class="control-label">OrderBy</label></td>
	 	<td><label class="control-label required">Types :</label></td>
	 	<td><select id="mainQueryOrderByColumnType-0" class="form-control mainQueryCountyTableColumns" >
	                    <option value="">--Please Select--</option>
	                    <option value="ASC">ASC</option>
	                    <option value="DESC">DESC</option>
                  </select></td>
        <td><label class="control-label required">Columns :</label></td>
        <td><select multiple id="columnsForMainQueryOrderBy-0" class="form-control countyTableColumnsForMainQueryOrderBy" >
		            	<%--  <% Iterator iterator8=columnValue.iterator();
		                	Iterator iterator9=columnOption.iterator();
						 	 while(iterator.hasNext()||iterator8.hasNext()){
						 		String columnOptionToShow=(String)iterator9.next();
						 		String columnValuesToShow=(String)iterator8.next();
						 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
						 	}
						 %> --%>
					</select></td>
		<td><button type="button" id ="addRowForOrderBy" class="btn btn-default">Add Row</button></td>
	 </tr>
	 </table>
	 <table id ="ButtonsTable" class = "pull-right">
		<tr class="border_bottom">
		 	<td>
		 		<button class='btn btn-warning' id = 'runQueryButton' ng-click="runQuery();" scroll-on-click>RunQuery</button>
		 	</td>
		 	<td>
		 		<button class='btn btn-warning' id = 'saveQueryButton' ng-click = "saveQueryfn();">Save</button>
		 	</td>
	 	</tr>
	</table>
		</div>
		<div class="row">
			<div class="col-md-12">
              <label class="control-label">Result Query:</label>
             	<!-- <span id = "saveResultQuery" class="pull-right" ng-click = "saveResultQueryfn();" ><img src="img/save.png"  title='Save'  style = "margin-top: 15px;height: 19px;"></span>
              	<span id = "editResultQuery" class="pull-right" ng-click = "editResultQueryfn();" ><img src="img/edit_normal.png"  title='Edit'  style = "margin-top: 15px;height: 19px;margin-right: 10px;"></span> -->
            	<textarea id="resultQuery" rows="5" cols="3" name="textarea" class="auto form-control test"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;height:60px;color: slategrey;margin-bottom: 10px" spellcheck="false" data-toggle="tooltip"   disabled></textarea>
            </div>
         </div>
         <div class = "row">
        	<div class="col-md-12">
              <label class="control-label" id = "NoOfRows"></label>
         	</div>
         </div>
         
         	<div id="grid1" ui-grid="gridOptions" class="grid" ui.grid.resizeColumns  style="height: 600px;margin : 5px;"></div>
         	
         	
         	<div id="alertmsgdivs"></div>
         
       <!--   <div class="row">
			     <div id = "resultQueryDiv" class="col-md-12">
					<iframe class="col-md-12" id="GridDisplay"  src = "gridDisplay.jsp" name="gridDisplay" style=" text-align:center;height: 600px; scroll:auto"></iframe>
				</div>			
		</div> -->
		<input type='text' id='resultBuildQuery' class='form-control' hidden>
		<div id = "likeConditionDiv"></div>
		<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
</body>
</html>