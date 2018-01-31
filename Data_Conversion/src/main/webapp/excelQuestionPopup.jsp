<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Copy Rule</title>
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
		    <td><label class="control-label">1. </label></td>
		    <td>Did you get the same files/tables?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q1" name = 'q1' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q1" name = 'q1' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">2. </label></td>
		    <td>Same Layouts?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q2" name = 'q2' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q2" name = 'q2' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">3. </label></td>
		    <td>Do record counts differ between Editions?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q3" name = 'q3' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q3" name = 'q3' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">4. </label></td>
		    <td>Did you verify the Assessment Year and Values?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q4"  name = 'q4'value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q4" name = 'q4' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">5. </label></td>
		    <td>Does the APN format comply with the current APN format?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q5" name = 'q5' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q5" name = 'q5' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">6(a). </label></td>
		    <td>Does the source file(s) have a PARCEL NUMBER ?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q6_a" name = 'q6_a' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q6_a" name = 'q6_a' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">6(b). </label></td>
		    <td>Does the source file(s) have OWNER NAMES ?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q6_b" name = 'q6_b' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q6_b"  name = 'q6_b' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">7. </label></td>
		    <td>Does the source file(s) have a MAIL FULL STREET ADDRESS ?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q7" name = 'q7' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q7" name = 'q7' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">8. </label></td>
		    <td>Does the source file(s) have LAND USE CODES/DESCRIPTION ?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q8" name = 'q8' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q8" name = 'q8' value="N"/></td>
		</tr>
		<tr>
		    <td><label class="control-label">9. </label></td>
		    <td>Is the source file(s) usable or readable?</td> 
		    <td style="width: 50px;">&nbsp;&nbsp;</td>
		    <td align="right">Yes <input type="radio" id="q9" name = 'q9' value="Y"/> &nbsp;&nbsp;&nbsp;&nbsp;No <input type="radio" id="q9" name = 'q9' value="N"/></td>
		</tr>
    
    </table>
    
    <div style = "margin-top: 20px">
    
    
    <!-- <table id ="buildRuleTable">
    	<tr id = 'cancel-0'>
	    	<td><label class="control-label">Base Query :</label></td>
	    	<td>
	    		<textarea id="baseQueryCopy"  name="textarea" class="auto form-control test"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;width:600px;height:100px;color: slategrey" spellcheck="false" data-toggle="tooltip" disabled></textarea>
	    	</td>
    	</tr> 
    	<tr id = 'cancel-1' ><td colspan="2">&nbsp;</td></tr>
    	<tr id = 'cancel-2'>
	    	<td><label class="control-label">Rule :</label></td>
	    	<td>
	    		<textarea id="ruleQueryCopy"  name="textarea" class="auto form-control test"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;width:600px;height:100px;color: slategrey" spellcheck="false" data-toggle="tooltip" disabled></textarea>
	    	</td>
    	</tr>   	
    </table> -->
    	<!-- <div class="divClass"  style = "position:abosulte" hidden>
    	<a class="cancelRightEnd"></a>
    	<div style="border: 1px solid #e78f08;background: #e78f08 50% 50% repeat-x;color: #ffffff;height: 40px;text-align: left;margin-top: -17px;margin-bottom: 10px;border-radius: 4px;width: 102.4%;margin-left: -15px"><lable style ="float: left;margin: 0.1em 0;font-weight: bold;margin-left: 15px;margin-top: 6px;">When Condition</lable></div>
			<iframe id="submenuPagesDisplay" class = 'iframeForModal' src="whereClauseInBuildRule.jsp" style = "width: 1025px;height: 100px;padding: 4px;border-color: rgba(16, 181, 181, 0.55);background-color: oldlace" ></iframe>
		 </div> -->
    </div>
</div>    
</body>
</html>