<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">
<link href="css/manual-style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/jquery.webui-popover.min.css">
<script src="js/bootstrap-multiselect.js"></script>
<script src="js/jquery.webui-popover.min.js"></script>
<!-- <script src="js/gips.js" type="text/javascript"></script> -->
<script src="js/app.js"></script>
<script src="js/modelJavaScript.js"></script>
<script src="js/buildRulePopup.js"></script>
<script src=js/buildQueryJavaScript.js></script>
<script src="js/dataConversion.js"></script> 
<script src="js/buildrulepopupgrid.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
    	function blink(selector){
    	    $(selector).fadeOut('slow', function(){
    	        $(this).fadeIn('slow', function(){
    	            blink(this);
    	        });
    	    });
    	}
		blink('#blinker');
		document.getElementById("blinker").style.visibility = "visible";
    	//$("#generatedocbutton").attr('disabled', 'disabled');
    	$("#countyName1").val($("#countyName").val());
    	$("#stateName1").val($("#stateName").val());
    	$('#iframecontainer11').hide();
    	
        $('.countyTableColumnsValue').multiselect({
        	/* enableFiltering: true, */
        	filterBehavior: 'value',
            includeSelectAllOption: true,
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true,
            enableCaseInsensitiveFiltering : true
        });
        
        $('#wrapper').dialog({
            autoOpen: false,
            title: 'Basic Dialog'
        });
        $('#opener').click(function() {
            $('#wrapper').dialog('open');
        });
        runQueryExcept();
    });	
    
    function runQuery(){
    //	$("#generatedocbutton").removeAttr('disabled');
    	document.getElementById("blinker").style.visibility = "hidden";
    	/* 
    	alert("runQuery");
    	console.log("runQuery"); */
     var countryName = $("#countyName1").val();
    	var stateValue = $("#stateName1").val();
    		var editon = $("#editonNumber").val();
    		var fieldNumber = $("#fieldNumber").val();
    		var fieldName = $("#fieldName").val();
    		var requiredVlaues=countryName+"|"+stateValue+"|"+editon+"|"+fieldNumber+"|"+fieldName;
    		//alert(requiredVlaues);

    	$('#countydetails').val(requiredVlaues);
    	$("#iframecontainer12").empty();
   	localStorage.setItem('requiredVlauesforDoc', requiredVlaues);
    	//	$('#countydetails').val(requiredVlaues);
    	$('#iframecontainer11').show();
    	var baseQuery =$("#baseQuery").val();
    	var ruleQuery =$("#query").val();
    	
    	var ruleQuery71 = $("#query71").val();
    	var ruleQuery72 = $("#query72").val();
    	var ruleQuery73 = $("#query73").val();
    	
    	var specialChars = ['%','&','+','#'];
		var codeNumbersForSplChars = ['%25','%26','%2B','%23'];
		for(var sp = 0 ; sp < specialChars.length;sp++){
			if(ruleQuery.indexOf(specialChars[sp]) >0){
		    		ruleQuery = ruleQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
		    }
			if(baseQuery.indexOf(specialChars[sp]) >0){
				baseQuery = baseQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
	    	}
			if(ruleQuery71.indexOf(specialChars[sp]) >0){
				ruleQuery71 = ruleQuery71.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
			}
			if(ruleQuery72.indexOf(specialChars[sp]) >0){
				ruleQuery72 = ruleQuery72.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
			}
			if(ruleQuery73.indexOf(specialChars[sp]) >0){
				ruleQuery73 = ruleQuery73.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
			}
		}
    $("#iframecontainer11").empty();
	$("#iframecontainer12").empty();
    	var iframe = document.createElement('iframe');
    	iframe.frameBorder=0;
    	iframe.className = "col-md-12 col-sm-6 col-xs-12";
		iframe.height="600px"; 
    	iframe.scrool="none";
    	iframe.id="GridDisplay";
		if(fieldNumber == 71 || fieldNumber == 72 || fieldNumber == 73){
			iframe.setAttribute("src", "./gridDisplay.jsp?baseQuery="+baseQuery+"&ruleQuery71="+ruleQuery71+"&ruleQuery72="+ruleQuery72+"&ruleQuery73="+ruleQuery73);
		}else{
			iframe.setAttribute("src", "./gridDisplay.jsp?baseQuery="+baseQuery+"&ruleQuery="+ruleQuery);
		}
    	
    	$("#iframecontainer11").append(iframe);
    	$("#exportData").show();
    }
    
    function runQueryExcept(){
    	$("#iframecontainer12").empty();
    	$('#iframecontainer11').show();
    	var baseQuery =$("#baseQuery").val();
    	var ruleQuery =$("#query").val();
    	var specialChars = ['%','&','+','#'];
		var codeNumbersForSplChars = ['%25','%26','%2B','%23'];
		for(var sp = 0 ; sp < specialChars.length;sp++){
			if(ruleQuery.indexOf(specialChars[sp]) >0){
		    		ruleQuery = ruleQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
		    }
			if(baseQuery.indexOf(specialChars[sp]) >0){
				baseQuery = baseQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
	    	}
		}
		var sampleQuery = "";
		var queryWhereSplit = baseQuery.split("where");
		var queryFromPart = queryWhereSplit[0].split("FROM");
		sampleQuery = queryWhereSplit[0].replace(queryFromPart[0], "select * ");
		var afterWhere = baseQuery.replace(queryWhereSplit[0], "");
		var exceptQuery = sampleQuery+" except "+sampleQuery+afterWhere;
		
		ruleQuery = "";
    
    	$("#iframecontainer11").empty();
    	$("#iframecontainer12").empty();
    	var iframe = document.createElement('iframe');
    	iframe.frameBorder=0;
    	iframe.className = "col-md-12 col-sm-6 col-xs-12";
		iframe.height="600px";
    	iframe.scrool="none";
    	iframe.id="GridDisplay";
    	iframe.setAttribute("src", "./gridDisplay.jsp?baseQuery="+baseQuery+"&ruleQuery="+ruleQuery);
    	$("#iframecontainer11").append(iframe);
    	$("#exportData").show();
    }
    function applyRule(ruleQueryId)
    {
    	if($("#ruleQuery").val() == "")
		{
	    	alert("Kindly provide rule query");
		}
    	if($("#baseQuery").val() == ""){
    		alert("Kindly provide base query");
    	}
    	if($("#ruleQuery").val() != "" && $("#fields").val() != "")
		{
    		if($("#ruleOperation").val() == "EDIT"){
    			updateRule(ruleQueryId);
    		}else{
    			saveRule();
    		}
    	}
    }
    
  	/*  $('textarea[name=textarea]').tooltip({
    	placement: "top",
    	trigger: "focus",
    	width:'auto',
    	height:'auto'
    }); 
  	  */
  	$("#showColumnsDialog").hide();
  	function passValueOnNewWindow(){
  		var territoryValue = $("#countyName1").val();
  		var stateValue = $("#stateName1").val();
  		var editon = $("#editonNumber").val();
  		var countryFipsCode = $("#countryFipsCode").val();
  		var countryName = $("#countryName").val();
  		var stateFipsCode = $("#stateFipsCode").val();
  		var countyFipsCode = $("#countyFipsCode").val();
  		var stateCode = $("#stateCode").val();
  		if(editon != ""){
  			sessionStorage.setItem("territory", territoryValue); 
  	        sessionStorage.setItem("stateValue", stateValue); 
  	        sessionStorage.setItem("editon", editon); 
  	        sessionStorage.setItem("stateFipsCode", stateFipsCode); 
  	        sessionStorage.setItem("countyFipsCode", countyFipsCode); 
  	        sessionStorage.setItem("countryFipsCode", countryFipsCode);
  	        sessionStorage.setItem("countryName", countryName);
  	        sessionStorage.setItem("stateCode", stateCode);
  	        window.open("ExportData.jsp","_blank");	
  	  	    
  		}
  		else{
  			$("#errorMsg").show();
  		    var model_dialog = $("#errorMsg").dialog({
                close: function(event, ui) {
                   
                },
                autoOpen: false,
                title: "Warning",
                height: 200,
                width: 600,
                resizable: false,
                draggable:false,
                modal: true,
                show: {effect: 'fade', duration: 1000}
           });
	        $("#errorMsg").empty();
            $("#errorMsg").prepend($('<p style="padding-top: 10px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>*Please Select The Edition Number from Dropdown And Try Again</p>'));
            model_dialog.dialog("open");
  		}
  	}
  	if($("#ruleName").val() == ""){
  		$("#ruleName").val("");
  		$("#ruleName").val("Please write your rule here..")
  	}  	
  	 </script>
  	 
  	 <script>
  	$(document).on('click', '#saveImgRuleChange', function() {
  		
  		$('.testarea').attr('disabled',true);
  	});
  	
	$(document).on('click', '#editImgRuleChange', function() {
  		$('.testarea').removeAttr('disabled');  		
  	});
	
	$('textarea[name=textarea]').webuiPopover();
	
	
  	 </script>
  	 <style>
  	 
  	 #editImgChange:hover {
    	content: url('img/edit.png');
    	margin-top: 15px;
    	height: 19px;
    	margin-right: 10px;
	 }
	 #saveImgChange:hover{
	 	content: url('img/save-hover.png');
    	margin-top: 15px;
    	height: 19px;
	 }
	 #saveImgRuleChange:hover{
	 	content: url('img/save-hover.png');
    	margin-top: 15px;
    	height: 19px;
	 }
	 #editImgRuleChange:hover{
	 	content: url('img/edit.png');
    	margin-top: 15px;
    	height: 19px;
    	margin-right: 10px;
	 }
	 
  	 </style>
</head>

<body>

<div class="widget-content">
        <div class="height250">
          <table class="table table-hover">
            <tbody >
            <tr>
            <c:forEach items="${tableMapWithColumns}" var="tableNames">
              <td>${tableNames.key}</td>
            </c:forEach>
            </tr>
            <tr >
            <c:forEach items="${tableMapWithColumns}" var="tableNames">
                <td> <select id="${tableNames.key}" class="form-control countyTableColumnsValue col-md-3" multiple="multiple">
					    <c:forEach items="${tableNames.value}" var="columnNames">
					       <option value="${columnNames.columnName}-${columnNames.columnType}-${columnNames.columnSize}">${columnNames.columnName}</option>
					    </c:forEach>
					</select>
				</td>
				</c:forEach>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="form-actions">
        <div id="specificforfield"><button id='modelfileld'  hidden="hidden" class='btn btn-success pull-right' onclick = 'openlegalfieldmodel();'> Concat Columns </button></div>
          <button type="button" class="btn btn-warning pull-right" onclick="buildRuleQuery();" id="build_rule_btn">Build Rule </button>
          <button type="button" id="sampleQuery" class="btn btn-default pull-right" onclick="buildQuery();"  title="Double click">Sample query </button>
          <button type="button" class="btn btn-default pull-right" onclick="dynamicAddColumns();">Build Where Clause </button>
        </div>
      <div class="row">
            <div class="col-md-12">
              <label class="control-label">Build Query :</label>
              <span id = "refreshQuery" ><img src="img/refreshImage.png"  title='Refresh'></span>
              <span id = "saveImgChange" class="pull-right" onclick = "saveTheText();" ><img src="img/save.png"  title='Save'  style = "margin-top: 15px;height: 19px;"></span>
              <span id = "editImgChange" class="pull-right" onclick = "editTheText();" ><img src="img/edit_normal.png"  title='Edit'  style = "margin-top: 15px;height: 19px;margin-right: 10px;"></span>
              <textarea id="baseQuery" rows="5" cols="3" name="textarea" class="auto form-control test"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;height:100px;color: slategrey" spellcheck="false" data-toggle="tooltip" data-title = "${rule.baseQuery}" disabled >${rule.baseQuery}</textarea>
            </div>
		</div>
		<div class="row">
			<div class="col-md-12">
              <label class="control-label">Rule :</label>
             <textarea id="ruleQuery" rows="5" cols="3" name="textarea" class="auto form-control test"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;height:100px;color: slategrey" spellcheck="false" data-toggle="tooltip" data-title = "${rule.rule}" >${rule.rule}</textarea>
            </div>
        </div>
            <div class="row">
			<div class="col-md-12">
              <label class="control-label">Rule Query :</label>
              <span id = "rulerefresh" ><img src="img/refreshImage.png"  title='Refresh'></span>
              <span id = "saveImgRuleChange" class="pull-right" onclick = "saveTheRuleText();" ><img src="img/save.png"  title='Save'  style = "margin-top: 15px;height: 19px;"></span>
              <span id = "editImgRuleChange" class="pull-right" onclick = "editTheRuleText();" ><img src="img/edit_normal.png"  title='Edit'  style = "margin-top: 15px;height: 19px;margin-right: 10px;"></span>
             <textarea id="query" rows="5" cols="3" name="textarea"   class="auto form-control testarea"  style="overflow: scroll; word-wrap: break-word; resize: horizontal;height:100px;color: slategrey" spellcheck="false" data-toggle="tooltip" data-title = "${rule.query}"  disabled>${rule.query}</textarea>
            </div>
         </div>
         <div class="row">
			 <div class="col-md-12 margin-top20">
              <span id="fulscreenicon" onclick="fullSceen()"><i  class="icon-fullscreen"></i></span>
              <button type="button" class="btn btn-default" onclick="runQuery();">Run</button>
              <button type="button" class="btn btn-default" onclick="runQueryExcept();">Run with EXCEPT</button>
			</div>
			
			<div class="row">
             <div class="col-md-2 " id="operation_fields" >
             </div>
             <div class="col-md-2 " id="operation_field" style="margin-left: 26px;">
             </div>
             </div>
             <div class="col-md-2 " id="selected_columns1">
             </div>
             <div class="col-md-2 " id="selected_columns2">
             </div>
          </div>
          <div class = "row">
        	<div class="col-md-12">
              <label class="control-label" id = "NoOfRows"></label>
         	</div>
         </div>
          
          <div class="row">
		                <div class="col-md-12"> </div>
		                <div id="iframecontainer11">
		              </div>
          
      
		<!-- <iframe id="GridDisplay"  src = "" name="gridDisplay" scrool="none" style=" text-align:center;width: 1130px;height: 500px; border: none;"></iframe> -->
	  </div>
	      <div class="row">
		                <div class="col-md-12"> </div>
		
	   <div id="iframecontainer12">
		<!-- <iframe id="GridDisplay2"  src = "" name="gridDisplay" scrool="none" style=" text-align:center;width: 1130px;height: 500px;border: none;"></iframe> -->
	  </div>
	  </div>	
	  <div class="row">	
<!-- 	  <div class="col-sm-9">
			 <form  action="report/dwnloadIndividualFiedDoc" id="loginForm">
		            <div class="form-group">
		              <input type="hidden" name="requiredVlaues" id="countydetails">
		              	<button type="submit" id="generatedocbutton" class="btn btn-warning" style="color:#ffffff;/* margin-top:20px; */"> Generate Document  </button>
		              	<span id="blinker" style="color:red;">Please run query before generating document.</span>
		  			</div>
		     </form>
		     </div> -->
		     <div class="col-sm-12">
		     <button type="button" class="btn btn-warning" id="opener1" onclick="applyRule(${rule.ruleQueryId});">Save Rule</button>
		 		<span id="blinker" style="color:red;">Please run query before saving Rule.</span>
			 <button type="button" id = "exportData" class="btn btn-warning pull-right" onclick="passValueOnNewWindow()" >ExportData</button>
			</div>
	</div>
			<span id = "showColumnsDialogForBuildRule"></span>
			<!-- <input type="hidden" id="ruleName"/>  -->
			<%-- <input type="hidden" id="query" value="${rule.query}"/> --%>
			<input type="hidden" id="query71"/>
			<input type="hidden" id="query72"/>
			<input type="hidden" id="query73"/>
			<input type="hidden" id="query73"/>
			<input type="hidden" id="query163"/>
			<input type="hidden" id="query164"/>
			<input type="hidden" id="query165"/>
			<input type="hidden" id="query166"/>
			<input type="hidden" id="query169"/>
			<input type="hidden" id="countyName1"/>
			<input type="hidden" id="stateName1"/>
			<input type="hidden" id="tables" value="${rule.tables}"/>
			<input type="hidden" id="columns" value="${rule.columns}"/>
			<input type="hidden" id="editonHidden" value="${rule.ruleMaster.edition}"/>
			<input type="hidden" id="ruleQueryId" value="${rule.ruleQueryId}"/>
	  	    <div id = "openDialogConformationBox"></div>
	  	     <div id = "showDivForRuleLevel"></div>
	  	     <div id = "showDivForRuleLevel171"></div>
	  	     <div id = "errorMsg"></div>
	  	     <div id = "warningDiv"></div>
	  	     <input type="hidden" id="ruleColumn"/>
	  	     <input type="hidden" id="buildRuleQuery"/>
	  	     <input type="hidden" id="buildRuleQuery71"/>
	  	     <input type="hidden" id="buildRuleQuery72"/>
	  	     <input type="hidden" id="buildRuleQuery73"/>
	  	     <!-- <div id="spinner"></div> -->
	  	     <input type="hidden" id="resultBaseQuery" value=""/>
	  	     
</body>
</html>