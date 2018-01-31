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
        <script src=js/subQueryInWhereClause.js></script>
        <script src=js/InnerSubQueryInWhereClause.js></script>

        <title>Insert title here</title>
        <style type=text/css>
            label {
                padding: 4px;
                margin: 4px;
            }
			.countyTableColumnsForSubQuery{			
			    max-height: 100px !important;
			    overflow-y: auto !important;
			}
			.errorAlerts{
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
        $('.countyTableColumnsForSubQuery').multiselect({
            enableFiltering: true,
            filterBehavior: 'value',
            maxHeight: 100,
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true,
            enableCaseInsensitiveFiltering : true
        });
        $('.countyTableColumnsForSubQueryNew').multiselect({
            enableFiltering: true,
            filterBehavior: 'value',
            maxHeight: 280,
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true,
            enableCaseInsensitiveFiltering : true
           
        });
        
        
        </script>
    </head>
    <body>
    	<div class="alert alert-warning errorAlerts" id = "alertForLikeOperatorSubQuery" hidden ="true" role="alert"></div>
    	<div class="alert alert-danger errorAlerts" id = "alertWhileFieldIsEmptySubQuery" hidden = "true" role="alert"></div>
    	<div class="alert alert-danger errorAlerts" id = "alertForInOperatorSubQuery" hidden = "true" role="alert"></div>
        <div>
        <table id = fixedColumnsForSubQuery>
	        <tr>
	        	<td><label class="control-label">Selected Column  :</label></td>
	        	<td><input type="text" id="seletedColumn" class="form-control" value = <%=selectedColumnName%> readOnly></td>
	        	<td><label class="control-label">TableNames :</label></td>
	        	<td><select multiple id="seletedTableNames" class="form-control countyTableColumnsForSubQueryNew">
			    		<%  Iterator iterator16=tableOption.iterator();
								while(iterator16.hasNext()){
								 	String tableValueToShow=(String)iterator16.next();
								 	%><option value="<%=tableValueToShow%>"><%=tableValueToShow%></option><%
								}
						%>	
		    		</select></td>
		    	<td><label class="control-label">Columns :</label></td>
	        	<td><select multiple id="selectColumns" class="form-control countyTableColumnsForSubQueryNew subqueryDefaultColumns ">
                        <option value= "">--Please Select--</option>
                      <%--   <%  Iterator iterator10=columnValue.iterator();
				        	Iterator iterator11=columnOption.iterator();
								while(iterator10.hasNext()||iterator11.hasNext()){
									String columnOptionToShow=(String	)iterator11.next();
								 	String columnValuesToShow=(String)iterator10.next();
								 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
								}
						%> --%>
		    		</select></td>
	        </tr>
        </table>
            </div>
            <div style="float: left">
                <table id='subQueryInWhereClause'>
                    <tr>
                        <tr>
                        <th>
                            <label class="control-label ">Start</label>
                        </th>
                        
						<th>
                            <label class="control-label"> </label>
                        </th><th>
                            <label class="control-label">Function</label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>
                        <th>
                            <label class="control-label required">Columns</label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>
                        <th>
                            <label class="control-label required">Operators</label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>
                        <th>
                            <label class="control-label required">Value</label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>

						<th>
                            <label class="control-label"> </label>
                        </th><th>
                            <label class="control-label"> </label>
                        </th>
                        <th>
                            <label class="control-label required">Logical Operators</label>
                        </th>
                        <th>
                            <label class="control-label"></label>
                        </th>
                        <th>
                            <label class="control-label">End</label>
                        </th>

                    </tr>
                    <tr id = "subQueryClosebutton-0">
                        <td>
                            <input type='checkbox' id='subQueryRowStart-0' class='checkBoxStyleForSubQuery' style='margin: 0px 14px'/>
                        </td>
                        <td><label class="control-label">  </label></td>
						<td><select id="subOtherFunction-0" class="form-control countyTableColumns">
	                    		<option value="">--Please Select--</option>
	                    		<option value="Convert">Convert</option>
	                    		<option value="Len">Length</option>
                  			</select></td>
                        
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <select id='subQueryCol-0' class='form-control subQueryCountyTableCol '>
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
                            <select id='subQueryOperators-0' class='form-control  operatorInSubQuery'>
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
                        
                        <td><label class="control-label">  </label></td>
						<td><select id="subQueryMatchCondition-0" class="form-control countyTableColumns" disabled>
			                    <option value="">--Please Select--</option>
			                    <option value="Start">Start</option>
			                    <option value="End">End</option>
			                    <option value="Between">Between</option>
			                  </select></td>
			                  
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <select id='subQueryValueSelect-0' class='form-control operatorInSubQuery '>
                                <option value=''>--Please Select--</option>
                               <%--  <% Iterator iterator2=columnValue.iterator();
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
                            <input type='text' id='subQueryValueText-0' class='form-control operatorInSubQuery'>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>	
                        <td>
                            <select id='subQueryColumns-0' class='form-control subQueryCountyTableColumns' disabled>
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
                            <input type='checkbox' id='subQueryRowEnd-0' class='checkBoxStyleForSubQuery' style='margin: 0px 14px'/>
                        </td>
                        <td>
                            <label class='control-label'></label>
                        </td>
                        <td>
                            <button class='btn btn-warning iframeForInnerSubquery' id='innerSubQueryButton-0' style='display: none;'>subQuery</button>
                        </td>
                    </tr>
                </table>
                <div class='innerSubQueryClass' hidden style='margin-bottom: 10px'>
                    <a class='cancelRightEnd' id='cancelForInnerIframeSubQuery'></a>
                    <iframe id='innerSubQueryPage' class='iframeForInnerSubQuery' src='subQueryInWhereClause.jsp' style='width: 1260px;height: 160px;padding: 4px;border-color: rgba(16, 181, 181, 0.55);background-color: oldlace'></iframe>
                </div>
            </div>
            <div style="float:left">
                <table id="groupByOrderByHavingFieldsForSubQuery">
                    <tr class="groupBy">
                        <td>
                            <label class="control-label">Group By</label>
                        </td>
                        <td>
                            <label class="control-label required" >Columns :</label>
                        </td>
                        <td>
                            <select multiple id="columnsForSubQueryGroupBy" class="form-control countyTableColumnsForSubQuery subqueryDefaultColumns">
                                <option value="">--Please Select--</option>
                              <%--   <% Iterator iterator4=columnValue.iterator();
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
                    <tr class="HavingInSubquery">
                        <td>
                            <label class="control-label">Having</label>
                        </td>
                        <td>
                            <label class="control-label required">Functions :</label>
                        </td>
                        <td>
                            <select id="functionsForSubQuery" class="form-control countyTableFunctionForSubQuery ">
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
                            <label class="control-label required">Columns :</label>
                        </td>
                        <td>
                            <select id="columnsForSubQueryHavingBy" class="form-control countyTableFunctionForSubQuery ">
                                <option value="">--Please Select--</option>
                               <%--  <% Iterator iterator6=columnValue.iterator();
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
                            <label class='control-label required'>Operator :</label>
                        </td>
                        <td><select id="subQueryHavingOperator" class="form-control subQueryCountyTableFunction ">
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
                            <select id="subQueryHavingValueSelect" class="form-control subQueryCountyTableFunction ">
                                <option value="">--Please Select--</option>
                              <%--   <% Iterator iterator12=columnValue.iterator();
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
                            <input type='text' id='subQueryHavingValueText' class='form-control HavingValueTextForSubQuery'>
                        </td>
                         
                    </tr>
                    <tr class="orderBy">
                        <td>
                            <label class="control-label">OrderBy</label>
                        </td>
                        <td>
                            <label class="control-label required">Types :</label>
                        </td>
                        <td>
                            <select id="orderBySubQueryColumnsType-0" class="form-control subQueryCountyTableColumns">
                                <option value="">--Please Select--</option>
                                <option value="ASC">ASC</option>
                                <option value="DESC">DESC</option>
                            </select>
                        </td>
                        <td>
                            <label class="control-label required">Columns :</label>
                        </td>
                        <td>
                            <select multiple id="columnsForSubQueryOrderBy-0" class="form-control countyTableColumnsForSubQueryNew subqueryDefaultColumns">
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
                        <td><button type="button" id ="addRowForOrderByInSubQuery" class="btn btn-default">Add Row</button></td>
                    </tr>
                </table>
            </div>
    </body>
</html>