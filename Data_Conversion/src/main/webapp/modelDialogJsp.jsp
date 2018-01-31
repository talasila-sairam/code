<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<%@page import="java.util.*"%>
<%-- <%
String columnValues=request.getParameter("columnValues");
String str[] = {};
List columnValue = new ArrayList();
if(columnValues != ""){
	str=columnValues.split(",");
	columnValue = new ArrayList(Arrays.asList(str));	
}
String columnOptions=request.getParameter("columnOptionsText");
String valuesStr[] = {};
List columnOption = new ArrayList();
if(columnOptions != ""){
	valuesStr=columnOptions.split(",");	
	columnOption = new ArrayList(Arrays.asList(valuesStr));
}

%> --%>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap-multiselect.css">
        <link rel=stylesheet href=css/jquery-ui.css>
        <link href=css/main.css rel=stylesheet type=text/css/>
        <link href=css/manual-style.css rel=stylesheet type=text/css/>
        
        <script type=text/javascript src=js/jquery.min.js></script>
        <script src=js/jquery-ui.js></script>
        <script src="js/bootstrap-multiselect.js"></script>
<!--         <script src=js/subQueryInWhereClause.js></script> -->

<title>DataConversion</title>
		
		<style type = "text/css">
			label{
			 		padding: 4px;
					margin: 4px;
			}
			.alertErrorMain{
				padding: 5px;
    			text-align: center;
			}
			.alert .close {
    			font-size: 24px;
    			line-height: 15px;
			}
			.form-control{
				margin: 4px;
			} 
		</style>
		<script>
		$('.countyTableColumnsForMainQuery').multiselect({
            enableFiltering: true,
            filterBehavior: 'value',
            maxHeight: 100,
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true,
            enableCaseInsensitiveFiltering : true
        });
		</script>
</head>
<body>
    <div class="alert alert-warning alertErrorMain" id = "alertForLikeOperator" hidden ="true" role="alert"></div>
    <div class="alert alert-danger alertErrorMain" id = "alertWhileFieldIsEmpty" hidden = "true" role="alert"></div>
    <div class="alert alert-warning alertErrorMain" id = "alertForInOperator" hidden = "true" role="alert"></div>
    
    <table id ="plannerTable">
    <tr>
    	<th><label class="control-label">Start</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">Columns</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">Operators</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">Value</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">Logical Operator</label></th>
    	<th><label class="control-label">  </label></th>
    	<th><label class="control-label">End</label></th>
    </tr>
    
    <tr>
    	<td>
    		<input type="checkbox" id="rowStart-0" class = "checkBoxStyle" style = "margin: 0px 14px"/>
    	</td>
         
         <!-- <td><select id="function-0" class="form-control countyTableFunction ">
            	<option value="">--Please Select--</option>
            	<option value="COUNT">Count</option>
            	<option value="AVG">Avg</option>
            	<option value="MAX">Max</option>
            	<option value="MIN">Min</option>
            	<option value="CAST">Cast</option>
            	<option value="CONVERT">CONVERT</option>
            </select></td> -->
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
                    <option value="between">between</option>
                  </select></td>
            
            <td><label class="control-label">  </label></td>
            <td><select id="value-0" class="form-control operatorInMainQuery">
            	<option value="">--Please Select--</option>
            <%-- 	<% Iterator iterator2=columnValue.iterator();
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
	 	<td><label class="control-label">Columns :</label></td>
	 	<td><select multiple  id="columnsForMainQueryGroupBy" class="form-control countyTableColumnsForMainQuer ">
		    	<option value="">--Please Select--</option>
		    	<%-- <% Iterator iterator4=columnValue.iterator();
                	Iterator iterator5=columnOption.iterator();
				 	 while(iterator.hasNext()||iterator4.hasNext()){
				 		String columnOptionToShow=(String)iterator5.next();
				 		String columnValuesToShow=(String)iterator4.next();
				 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
				 	}
				 %> --%>
			</select></td>
	 </tr>
	 <tr class = "Having" style="display: none;">
	 <td><label class="control-label">Having</label></td>
	 <td><label class="control-label">Functions :</label></td>
	 <td><select id="functionsForMainQuery" class="form-control countyTableFunctionForMainQuery ">
		            	<option value="">--Please Select--</option>
		            	<option value="COUNT">Count</option>
		            	<option value="AVG">Avg</option>
		            	<option value="MAX">Max</option>
		            	<option value="MIN">Min</option>
		            	<option value="CAST">Cast</option>
		            	<option value="CONVERT">CONVERT</option>
            </select></td>
	<td><label class="control-label">Columns :</label></td>
	<td><select id="columnsForMainQueryHavingBy" class="form-control countyTableFunction ">
		            	<option value="">--Please Select--</option>
		            <%-- 	<% Iterator iterator6=columnValue.iterator();
		                	Iterator iterator7=columnOption.iterator();
						 	 while(iterator.hasNext()||iterator6.hasNext()){
						 		String columnOptionToShow=(String)iterator7.next();
						 		String columnValuesToShow=(String)iterator6.next();
						 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
						 	}
						 %> --%>
	</select></td>        
	<td>
                            <label class='control-label'>Operator :</label>
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
                            <label class='control-label'>Value :</label>
                        </td>
                        <td>
                            <select id="mainQueryHavingByValueSelect" class="form-control countyTableFunctionMainQuery ">
                                <option value="">--Please Select--</option>
                                <%-- <% Iterator iterator12=columnValue.iterator();
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
	 	<td><label class="control-label">Types :</label></td>
	 	<td><select id="mainQueryOrderByColumnType" class="form-control mainQueryCountyTableColumns" >
	                    <option value="">--Please Select--</option>
	                    <option value="ASC">ASC</option>
	                    <option value="DESC">DESC</option>
                  </select></td>
        <td><label class="control-label">Columns :</label></td>
        <td><select multiple id="columnsForMainQueryOrderBy" class="form-control countyTableColumnsForMainQuery " >
		            	<option value="">--Please Select--</option>
		            	<%-- <% Iterator iterator8=columnValue.iterator();
		                	Iterator iterator9=columnOption.iterator();
						 	 while(iterator.hasNext()||iterator8.hasNext()){
						 		String columnOptionToShow=(String)iterator9.next();
						 		String columnValuesToShow=(String)iterator8.next();
						 	%><option value="<%=columnValuesToShow%>"><%=columnOptionToShow%></option><%
						 	}
						 %> --%>
					</select></td>
	 </tr>
	 <tr>
		 <td>
		 	
		 </td>
	 </tr>
	 	
	 </table>
</body>
</html> 