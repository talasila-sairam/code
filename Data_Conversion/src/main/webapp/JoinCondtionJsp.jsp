<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="./css/font-awesome.min.css">

<%@page import="java.util.*"%>
<%
String tableNamesData=request.getParameter("tableNamesData");
String str[] = {};
List tableNames = new ArrayList();
if(tableNamesData != ""){
	str=tableNamesData.split(",");
	tableNames = new ArrayList(Arrays.asList(str));	
};

String editionNumber =  request.getParameter("editionNumber"); 
%>


<script>
$('.listTheTableNamesClass').multiselect({
    enableFiltering: true,
    filterBehavior: 'value',
    disableIfEmpty: true,
    onChange : function(event){
    	getCountyTableColumnsForJoins();
    },
    nonSelectedText: '--Please Select--',
    enableCaseInsensitiveFiltering : true
    
});
</script>
<style>

label{
	margin:4px;
	padding:2px;
}

.error{
				padding: 5px;
    			text-align: center;
			}

</style>
</head>
<body>

<div class="alert alert-danger error" id = "alertIfFieldIsEmpty" hidden = "true" role="alert"></div>
<div class="alert alert-info error" id = "alertCountOfRows" hidden = "true" role="alert"></div>
	<table id = "joinConditionTable">
		<tr>
		<td><label class="control-label">Edition Number : <%=editionNumber%></label></td>
		<td><label class="control-label"> </label></td>
		<td><label class="control-label"> </label></td>
		<td><label class="control-label"> </label></td>
		<td><input id = "editionForJoinCondition" value = "<%=editionNumber%>"class = "form-control" readOnly style = "display:none" /></td>
  			
		    <td><label class="control-label">Tables :</label></td>
		    <td><select multiple id="listTheTableNames" class="form-control listTheTableNamesClass">
	    		 	  <% Iterator iterator=tableNames.iterator();
				 	 	while(iterator.hasNext()){
					 		String columnValuesToShow=(String)iterator.next();
				 		%><option value="<%=columnValuesToShow%>"><%=columnValuesToShow%></option><%
				 		}
				 	  %>
	    		</select></td>
	    	<td><label class="control-label">  </label></td> 
	    	<td><button class='btn btn-warning joinConditionChecking' id = 'joinConditionCheck'>View</button></td>
	    	<td><label class="control-label">  </label></td>
	    	<td><button class='btn btn-warning createNewJoinConditionClass' id = 'createNewJoinCondition'>Create</button></td>	
	    		</tr> 
	    	
	    	
	    	
	</table>
<div id="joinConditionsTableRows" style="margin-top: 10px;"></div>

<table id = "joinTableStructure" hidden = true>
<tr>
<th>
<label class="control-label">Join Table Columns :</label>
</th>
<th>

</th>
<th> 

</th>
<th> 

</th>
<th><label class="control-label">Relation : </label></th>
</tr>
<tr>
		    <td><select  id="joinTableFirst-0" class="form-control selectJoinTable1">
	    		 	  <option value="">--Please Select--</option>
	    		 	  
	    		</select></td>
	    	<td><label class="control-label"> </label></td>
	    	
		    <td><select  id="joinTableSecond-0" class="form-control selectJoinTable2">
	    		 	  <option value="">--Please Select--</option>
	    		 	  
	    		</select></td>
<td><label class="control-label">  </label></td>	    		
	    		
<td>

<select  id="joinRelationShip-0" class="form-control joinRelationShipClass">
<option value = "">--Please Select--</option>
<option value = "inner Join">inner Join</option>
<option value = "outer Join">outer Join</option>
<option value = "outer Left Join">outer Left Join</option>
<option value = "outer Right Join">outer Right Join</option>
</select>
</td>
<td><label class="control-label">  </label></td>	    		
<td><i class="icon icon-plus addRowForColumnJoinNextCondtionClass"  id = 'addRowForColumnJoinNextCondtion-0' aria-hidden="true"></i><!-- <div  class = 'addRowForColumnJoinNextCondtionClass' id = 'addRowForColumnJoinNextCondtion-0' style = 'padding:4px'><img src='img/addImage.png'/></div> --></td>
<!-- <button class='btn btn-warning addRowForColumnJoinNextCondtionClass' id = 'addRowForColumnJoinNextCondtion-0'>addRow</button></td>	  -->   		
	    		</tr>
</table>	
<div id = "joinCondition" hidden = "true"><label class="control-label">JoinCondition :</label><textarea id="joinConditionQuery" rows="5" cols="3" name="textarea" class="auto form-control test"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;height:100px;color: slategrey;margin:10px;" spellcheck="false" data-toggle="tooltip"  title = ""  readonly></textarea></div>
<div id = "warningMsg"></div>
<div id = "savingJoinCondition"></div>
<!-- <table id = "createAJoinCondtion" hidden = true>

<tr>
<th>
<label class="control-label">Join Relation Of Tables :</label>
</th>

</tr>
<tr>
<td>
<select  id="joinFirstTable-0" class="form-control joinFirstTableClass">
<option value = "">--Please Select--</option>
</select>

</td>
<td><label class="control-label">  </label></td>

<td>

<select  id="joinSecondTable-0" class="form-control joinSecondTableClass">
<option value = "">--Please Select--</option>
</select>

</td>
<td><label class="control-label">  </label></td>
<td>

<select  id="joinRelationShip-0" class="form-control joinRelationShipClass">
<option value = "">--Please Select--</option>
<option value = "innerJoin">innerJoin</option>
<option value = "outerJoin">outerJoin</option>
<option value = "outerLeftJoin">outerLeftJoin</option>
<option value = "outerRightJoin">outerRightJoin</option>
</select>

</td>
<td><label class="control-label">  </label></td>
<td><button class='btn btn-warning addRowForJoinNextCondtionClass' id = 'addRowForJoinNextCondtion-0'>addRow</button></td>
</tr>
</table> -->
</body>

</html>