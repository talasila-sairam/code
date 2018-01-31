<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Build Rule</title>
<style> 

.divClass
{
   margin-top: 19px;
    padding: 20px;
    text-align: center;
    height: 175px;
    width: 1065px;
    box-shadow: rgb(91, 91, 91) 5px 4px 7px, rgb(136, 136, 136) 0px 0px 25px, rgb(136, 136, 136) 0px 0px 5px;
    background-color: #f5f5f5;
    z-index: 10;
    margin-left: -14px
}
label
{
	padding: 4px;
	margin: 5px;
}
 .cancelRightEnd
{
    display:block;
    position:absolute;
    top:-10px;
    right:-10px;
    width:27px;
    height:27px;
    background:url('http://cdn-sg1.pgimgs.com/images/pg/close-button.png') no-repeat center center;
} 
	
        </style>
</head>
<body>
<div>
    <table id = fixedColumns>
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
  			<td><button class='btn btn-warning addRow' id = 'addRow'>addRow</button></td>
	    </tr>
    
    </table>
    
    <div style = "margin-top: 20px">
    <table id ="buildRuleTable">
    	<tr id = 'cancel-0'>
	    	<td><label class="control-label">Rule :</label></td>
	    	<td>
	    		<select id="ruleType-0"  class="form-control ruleTypeCheck">
	                    <option value="">--Please Select--</option>
	                    <option value="CONCAT">Concat</option>
	                    <option value="REPLACE">Replace</option>
	                    <option value="REPLACE FIRST">Replace First</option>
	                    <option value="REPLACE LAST">Replace Last</option>
	                    <option value="DIVIDE BY">Divide By</option>
	                    <option value="MULTIPLY BY">Multiply  By</option>
	                    <option value="PUT DECIMAL">Put Decimal</option>
	                    <!--<option value="STRIP OUT">Strip Out</option>
	                    <option value="ENCLOSE PARENTHESES">Enclose Parentheses</option> -->
	                  </select>
	    	</td>
    	</tr>    	
    </table>
    	<div class="divClass"  style = "position:abosulte" hidden>
    	<a class="cancelRightEnd"></a>
    	<div style="border: 1px solid #e78f08;background: #e78f08 50% 50% repeat-x;color: #ffffff;height: 40px;text-align: left;margin-top: -17px;margin-bottom: 10px;border-radius: 4px;width: 102.4%;margin-left: -15px"><lable style ="float: left;margin: 0.1em 0;font-weight: bold;margin-left: 15px;margin-top: 6px;">When Condition</lable></div>
			<iframe id="submenuPagesDisplay" class = 'iframeForModal' src="whereClauseInBuildRule.jsp" style = "width: 1025px;height: 100px;padding: 4px;border-color: rgba(16, 181, 181, 0.55);background-color: oldlace" ></iframe>
		 </div>
    </div>
</div>    
</body>
</html>