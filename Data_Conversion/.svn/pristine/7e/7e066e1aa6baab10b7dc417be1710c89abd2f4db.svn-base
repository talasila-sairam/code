<!DOCTYPE html PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN http://www.w3.org/TR/html4/loose.dtd>
<html>
<%@page import="java.util.*"%>
<%

String selectedColumnName=request.getParameter("selectedColumnName");


String selectedTableNamesText = request.getParameter("selectedTableNamesTexts");
String tableNameStr[] = {};
List tableOption = new ArrayList();
if(selectedTableNamesText != ""){
	tableNameStr = selectedTableNamesText.split(",");	
	tableOption = new ArrayList(Arrays.asList(tableNameStr));
	System.out.println(tableOption);
}
%>
   <head>
        <meta http-equiv=Content-Type content=text/html; charset=ISO-8859-1>
        <link href=css/bootstrap.min.css rel=stylesheet type=text/css/>
        <link rel="stylesheet" href="css/bootstrap-multiselect.css">
        <link rel=stylesheet href=css/jquery-ui.css>
        <link href=css/main.css rel=stylesheet type=text/css/>
        <link href=css/manual-style.css rel=stylesheet type=text/css/>
        <script type=text/javascript src=js/jquery.min.js></script>
        <script src=js/jquery-ui.js></script>
        <script src="js/bootstrap-multiselect.js"></script>
        <script src=js/InnerSubQueryInWhereClause.js></script>
        
        <style type=text/css>
            label {
                padding: 4px;
                margin: 4px;
            }
			.countyTableColumnsForInnerSubQuery{			
			    max-height: 100px !important;
			    overflow-y: auto !important;
			}
			.errorAlertsInnerSubQuery{
				padding: 5px;
    			text-align: center;
    			margin: 0px;
			}
			.alert .close {
    			font-size: 24px;
    			line-height: 15px;
			}
        </style>
        <script>
        $('.countyTableColumnsForInnerSubQuery').multiselect({
            enableFiltering: true,
            filterBehavior: 'value',
            maxHeight: 100,
            nonSelectedText: '--Please Select--',
            enableCaseInsensitiveFiltering : true
        });
        $('.countyTableColumnsForInnerSubQueryNew').multiselect({
            enableFiltering: true,
            filterBehavior: 'value',
            maxHeight: 280,
            nonSelectedText: '--Please Select--',
            enableCaseInsensitiveFiltering : true
        });
        </script>
    </head>
    <body>
    	<div class="alert alert-warning errorAlertsInnerSubQuery" id = "alertForLikeOperatorInnerSubQuery" hidden ="true" role="alert"></div>
    	<div class="alert alert-danger errorAlertsInnerSubQuery" id = "alertWhileFieldIsEmptyInnerSubQuery" hidden = "true" role="alert"></div>
    	<div class="alert alert-danger errorAlertsInnerSubQuery" id = "alertForInOperatorInnerSubQuery" hidden = "true" role="alert"></div>
        <div>
        <table id = fixedColumnsForSubQuery>
	        <tr>
	        	<td><label class="control-label">Selected Column  :</label></td>
	        	<td><input type="text" id="seletedColumnInnerSubQuery" class="form-control" value = <%=selectedColumnName%> readOnly></td>
	        	<td><label class="control-label">TableNames :</label></td>
	        	<td><select multiple id="seletedTableNamesInnerSubQuery" class="form-control countyTableColumnsForInnerSubQueryNew">
		    			<option value="">--Please Select--</option>
			    		<%  Iterator iterator16=tableOption.iterator();
								while(iterator16.hasNext()){
								 	String tableValueToShow=(String)iterator16.next();
								 	%><option value="<%=tableValueToShow%>"><%=tableValueToShow%></option><%
								}
						%>	
		    		</select></td>
		    	<td><label class="control-label">Columns :</label></td>
	        	<td><select multiple id="selectColumnsInnerSubQuery" class="form-control countyTableColumnsForInnerSubQueryNew innerSubqueryDefaultColumns">
                        <option value= "">--Please Select--</option>
                        <%-- <%  Iterator iterator10=columnValue.iterator();
				        	Iterator iterator11=columnOption.iterator();
								while(iterator10.hasNext()||iterator11.hasNext()){
									String columnOptionToShow=(String)iterator11.next();
								 	String columnValuesToShow=(String)iterator10.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								}
						%> --%>
		    		</select></td>
	        </tr>
        </table>
            </div>
            <div style="float: left">
                <table id='innerSubQueryInWhereClause'>
                    <tr>
                        <th>
                            <label class='control-label'>Start</label>
                        </th>
                      <!--   <th>
                            <label class='control-label'>Function</label>
                        </th> -->
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'>Columns</label>
                        </th>
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'>Operators</label>
                        </th>
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'>Value</label>
                        </th>
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'>Logical Operators</label>
                        </th>
                        <th>
                            <label class='control-label'></label>
                        </th>
                        <th>
                            <label class='control-label'>End</label>
                        </th>
                    </tr>
                    <tr id = "innerSubQueryClosebutton-0">
                        <td>
                            <input type='checkbox' id='innerSubQueryRowStart-0' class='checkBoxStyleForInnerSubQuery' style='margin: 0px 14px'/>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <select id='innerSubQueryCol-0' class='form-control innerSubQueryCountyTableCol'>
                                <option value=''>--Please Select--</option>
                               <%--  <% Iterator iterator=columnValue.iterator();
				                	Iterator iterator1=columnOption.iterator();
								 	 while(iterator.hasNext()||iterator1.hasNext()){
								 		String columnOptionToShow=(String)iterator1.next();
								 		String columnValuesToShow=(String)iterator.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								 	}
								 %> --%>
    
                            </select>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <select id='innerSubQueryOperators-0' class='form-control  operatorInInnerSubQuery'>
                                <option value=''>--Please Select--</option>
                                <option value='='>=</option>
                                <option value='<>'><></option>
                                <option value='like'>like</option>
                                <option value='not like'>not like</option>
                                <option value='<'><</option>
                                <option value='>'>></option>
                                <option value="<="><=</option>
                    			<option value=">=">>=</option>
                                <option value='is null'>is null</option>
                                <option value='is not null'>is not null</option>
                                <option value='in'>in</option>
                                <option value='not in'>not in</option>
                                <option value='between'>between</option>
                            </select>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <select id='innerSubQueryValueSelect-0' class='form-control operatorInInnerSubQuery'>
                                <option value=''>--Please Select--</option>
                              <%--   <% Iterator iterator2=columnValue.iterator();
				                	Iterator iterator3=columnOption.iterator();
								 	 while(iterator.hasNext()||iterator3.hasNext()){
								 		String columnOptionToShow=(String)iterator3.next();
								 		String columnValuesToShow=(String)iterator2.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								 	}
								 %> --%>
    
                            </select>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <input type='text' id='innerSubQueryValueText-0' class='form-control operatorInInnerSubQuery'>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>	
                        <td>
                            <select id='innerSubQueryColumns-0' class='form-control innerSubQueryCountyTableColumns' disabled>
                                <option value=''>--Please Select--</option>
                                <option value='And'>And</option>
                                <option value='Or'>Or</option>
                                <option value='Not'>Not</option>
                            </select>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <input type='checkbox' id='innerSubQueryRowEnd-0' class='checkBoxStyleForInnerSubQuery' style='margin: 0px 14px'/>
                        </td>
                    </tr>
                </table>
                             <!-- <div id=subQueryDiv></div> -->
            </div>
            <div style="float:left">
                <table id="groupByOrderByHavingFieldsForInnerSubQuery">
                    <tr class="groupBy">
                        <td>
                            <label class="control-label">Group By</label>
                        </td>
                        <td>
                            <label class="control-label">Columns :</label>
                        </td>
                        <td>
                            <select multiple id="columnsForInnerSubQueryGroupBy" class="form-control countyTableColumnsForInnerSubQueryNew innerSubqueryDefaultColumns">
                                <option value="">--Please Select--</option>
                             <%--    <% Iterator iterator4=columnValue.iterator();
				                	Iterator iterator5=columnOption.iterator();
								 	 while(iterator.hasNext()||iterator5.hasNext()){
								 		String columnOptionToShow=(String)iterator5.next();
								 		String columnValuesToShow=(String)iterator4.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								 	}
								 %> --%>
                            </select>
                        </td>
                    </tr>
                    <tr class="HavingInInnerSubQuery" style="display: none;">
                        <td>
                            <label class="control-label">Having</label>
                        </td>
                        <td>
                            <label class="control-label">Functions :</label>
                        </td>
                        <td>
                            <select id="functionsForInnerSubQuery" class="form-control countyTableFunctionForInnerSubQuery ">
                                <option value="">--Please Select--</option>
                                <option value="COUNT">Count</option>
                                <option value="AVG">Avg</option>
                                <option value="MAX">Max</option>
                                <option value="MIN">Min</option>
                                <option value="CAST">Cast</option>
                                <option value="CONVERT">CONVERT</option>
                            </select>
                        </td>
                        <td>
                            <label class="control-label">Columns :</label>
                        </td>
                        <td>
                            <select id="columnsForInnerSubQueryHavingBy" class="form-control countyTableFunctionForInnerSubQuery ">
                                <option value="">--Please Select--</option>
                              <%--   <% Iterator iterator6=columnValue.iterator();
				                	Iterator iterator7=columnOption.iterator();
								 	 while(iterator.hasNext()||iterator7.hasNext()){
								 		String columnOptionToShow=(String)iterator7.next();
								 		String columnValuesToShow=(String)iterator6.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								 	}
								 %> --%>
                            </select>
                        </td>
                        <td>
                            <label class='control-label'>Operator :</label>
                        </td>
                        <td><select id="innerSubQueryHavingOperator" class="form-control innerSubQueryCountyTableFunction ">
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
                            <label class='control-label'>Value :</label>
                        </td>
                        <td>
                            <select id="innerSubQueryHavingValueSelect" class="form-control innerSubQueryCountyTableFunction ">
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
                            <input type='text' id='innerSubQueryHavingValueText' class='form-control havingValueTextForInnerSubQuery'>
                        </td>
                         
                    </tr>
                    <tr class="orderBy">
                        <td>
                            <label class="control-label">OrderBy</label>
                        </td>
                        <td>
                            <label class="control-label">Types :</label>
                        </td>
                        <td>
                            <select id="orderByInnerSubQueryColumnsType" class="form-control innerSubQueryCountyTableColumnsOrder" >
                                <option value="">--Please Select--</option>
                                <option value="ASC">ASC</option>
                                <option value="DESC">DESC</option>
                            </select>
                        </td>
                        <td>
                            <label class="control-label">Columns :</label>
                        </td>
                        <td>
                            <select multiple id="columnsForInnerSubQueryOrderBy" class="form-control countyTableColumnsForInnerSubQueryNew innerSubqueryDefaultColumns">
                                <option value="">--Please Select--</option>
                               <%--  <% Iterator iterator8=columnValue.iterator();
				                	Iterator iterator9=columnOption.iterator();
								 	 while(iterator8.hasNext()||iterator9.hasNext()){
								 		String columnOptionToShow=(String)iterator9.next();
								 		String columnValuesToShow=(String)iterator8.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								 	}
								 %> --%>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
    </body>
</html>
