function getRulesFieldwise(countryFipsCode,stateFipsCode,countyFipsCode,countryName,stateName,countyName,ruleLevel,stateCode,fieldName,fieldNumber,defaultFlag,mappingFieldName){
	localStorage.setItem('requiredVlauesforDoc',"");
	$("#countryFipsCode").val(countryFipsCode);
	$("#stateFipsCode").val(stateFipsCode);
	$("#countyFipsCode").val(countyFipsCode);
	$("#countryName").val(countryName);
	$("#stateName").val(stateName);
	$("#countyName").val(countyName);
	$("#stateCode").val(stateCode);
	//$("#fieldName").val(fieldName+'-'+fieldNumber);
	$("#fieldNumber").val(fieldNumber);
	$("#defaultFlag").val(defaultFlag);
	$("#mappingFieldName").val(mappingFieldName);
	$("#ruleLevel").val(ruleLevel);
	$("#fieldName").val(fieldName);
	
	var fieldName=$("#fieldName").val();
	var data="countryFipsCode="+countryFipsCode
	+"&stateFipsCode="+stateFipsCode
	+"&countyFipsCode="+countyFipsCode
	+"&ruleLevel="+ruleLevel
	+"&countyName="+countyName
	+"&stateCode="+stateCode
	+"&fieldNumber="+fieldNumber
	+"&pageSize="+5
	+"&fieldName="+fieldName
	+"&pageNumber="+1;
	console.log(data+" "+countryName+" "+stateName+" "+countyName);
	loadingImage('rulesFrame');
	if(fieldName != undefined || fieldName != ""){
		jqueryAjaxfunction('getCountyTables','rulesFrame',data);
	}
	//alert(data+" "+countryName+" "+stateName+" "+countyName);
	//Moves the cursor to top
	//window.location.hash='rulesFrame';
	 $('html, body').animate({
	        scrollTop: $("#rulesFrame").offset().top
	    });
}
$(document).on('change', '#depenndentFieldSelect', function() {
	var fieldData=$("#depenndentFieldSelect").val();
	if(fieldData!=undefined){
	fieldData=fieldData.split("-");
	var fieldNumber=fieldData[0];
///	$("#fields").val(fieldData[1]);
	var countryFipsCode=	$("#countryFipsCode").val();
	var ruleLevel=$("#ruleLevel").val();
	var stateFipsCode=	$("#stateFipsCode").val();
	var countyFipsCode=	$("#countyFipsCode").val();
	var countryName=$("#countryName").val();
	var stateName=	$("#stateName").val();
//	var fieldNumber=$("#fieldNumber").val();
		var stateCode=	$("#stateCode").val();
		var countyName=$("#countyName").val();
	var data="countryFipsCode="+countryFipsCode
	+"&stateFipsCode="+stateFipsCode
	+"&countyFipsCode="+countyFipsCode
	+"&ruleLevel="+ruleLevel
	+"&countyName="+countyName
	+"&stateCode="+stateCode
	+"&fieldNumber="+fieldNumber
	+"&fieldName="+fieldData[1]
	+"&pageSize="+5
	+"&pageNumber="+1;
	loadingImage('rulesFrame');
	jqueryAjaxfunction('getCountyTables','rulesFrame',data);
	}
});

function backtoMainField(){
	var countryFipsCode=	$("#countryFipsCode").val();
	var ruleLevel=$("#ruleLevel").val();
	var stateFipsCode=	$("#stateFipsCode").val();
	var countyFipsCode=	$("#countyFipsCode").val();
	var countryName=$("#countryName").val();
	var stateName=	$("#stateName").val();
	var fieldNumber=$("#fieldNumber").val();
	var stateCode=	$("#stateCode").val();
	var countyName=$("#countyName").val();
	var fieldName=$("#fieldName").val();
	var data="countryFipsCode="+countryFipsCode
	+"&stateFipsCode="+stateFipsCode
	+"&countyFipsCode="+countyFipsCode
	+"&ruleLevel="+ruleLevel
	+"&countyName="+countyName
	+"&stateCode="+stateCode
	+"&fieldNumber="+fieldNumber
	+"&fieldName="+fieldName
	+"&pageSize="+5
	+"&pageNumber="+1;
	loadingImage('rulesFrame');
	jqueryAjaxfunction('getCountyTables','rulesFrame',data);
};
function loadingImage(divId){
	/*$("#"+divId).html("<center><img src='img/ajax-loader.gif'  /></center>");*/
	 $(window).load(function() { $("#spinner").fadeOut("slow"); });
	 /*$("#"+divId).html("<center><img src='img/ajax-loader.gif'  /></center>");*/
}
function deleteTheRule(ruleId){
var countryFipsCode=	$("#countryFipsCode").val();
var ruleLevel=$("#ruleLevel").val();
var stateFipsCode=	$("#stateFipsCode").val();
var countyFipsCode=	$("#countyFipsCode").val();
var countryName=$("#countryName").val();
var stateName=	$("#stateName").val();
var fieldNumber=$("#fieldNumber").val();
	var stateCode=	$("#stateCode").val();
	var countyName=$("#countyName").val();
	var fieldName=$("#fieldName").val();
	//alert(stateCode+"----"+countyName);
	var dataValues="countryFipsCode="+countryFipsCode
	+"&stateFipsCode="+stateFipsCode
	+"&countyFipsCode="+countyFipsCode
	+"&ruleLevel="+ruleLevel
	+"&countyName="+countyName
	+"&stateCode="+stateCode
	+"&fieldNumber="+fieldNumber
	+"&fieldName="+fieldName
	+"&pageSize="+5
	+"&pageNumber="+1;
	var jsonData = {"ruleId": ruleId,
				"stateCode": stateCode,
				"countyName":countyName,
				"countryFipsCode":countryFipsCode,
				"stateFipsCode":stateFipsCode,
				"countyFipsCode":countyFipsCode,
				"stateName":stateName,
				"countryName":countryName,
				"fieldNumber":fieldNumber
					}
	
	$.ajax({
	type: "POST",
	url: 'deleteRule',
	dataType: 'json', 	
	data: JSON.stringify(jsonData),
	contentType: "application/json",
	success: function (response) {
	console.log(response);
	if (response.status == "SUCCESS") {
		//alert("Rule successfully deleted.");
		var msg="Rule successfully deleted.";
		var status="Sucess";
		var style="alert-success";
		showalertMsg(msg,status,style);
		loadingImage('rulesFrame');
		jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
	}
	else {
		var style="alert-warning";
		var msg="Processing Error.Please try after some time.";
		var status="Error";
		showalertMsg(msg,status,style);
		//alert("Processing Error.Please try after some time");
	}
	}
	});
}

function updateRule(ruleQueryId){
	var tableArr = [];
	var colArr = [];
	var colCount = 0;
	var queryString = '';
	var colString = '';
	var tableString = '';
	$('#countyTablesSelect :selected').each(function(i, tableName){ 
	    tableArr[i] = $(tableName).val();
	    tableString = tableString+$(tableName).val()+', ';
	});
	for(i=0; i<tableArr.length; i++){
		$('#'+tableArr[i]+' :selected').each(function(j, colName){ 
		    colArr[colCount] = $(colName).val();
		    var col = $(colName).val().split("-");
		    colString = colString+col[0]+', ';
		    colCount = colCount+1;

		});
	}
	tableString = tableString.slice(0,tableString.length-2);
	colString = colString.slice(0,colString.length-2);
	var countryFipsCode=	$("#countryFipsCode").val();
	var ruleLevel=$("#ruleLevel").val();
	var stateFipsCode=	$("#stateFipsCode").val();
	var countyFipsCode=	$("#countyFipsCode").val();
	var countryName=$("#countryName").val();
	var stateName=	$("#stateName").val();
	var fieldName=$("#fieldName").val();
	var fieldNumber=$("#fieldNumber").val();
		var stateCode=	$("#stateCode").val();
		var countyName=$("#countyName").val();
		//alert(stateCode+"----"+countyName);
		var dataValues="countryFipsCode="+countryFipsCode
		+"&stateFipsCode="+stateFipsCode
		+"&countyFipsCode="+countyFipsCode
		+"&ruleLevel="+ruleLevel
		+"&countyName="+countyName
		+"&stateCode="+stateCode
		+"&fieldNumber="+fieldNumber
		+"&fieldName="+fieldName
		+"&pageSize="+5
		+"&pageNumber="+1;
	var editonNumber = $("#editonNumber").val();
	var ruleQuery = $("#ruleQuery").val();
	var query = $("#query").val();
	var baseQuery = $("#baseQuery").val();
	var jsonData = {"edition": editonNumber,
					"ruleQuery": ruleQuery,
					"query":query,
					"baseQuery":baseQuery,
					"tables":tableString,
					"columns":colString,
					"ruleQueryId": ruleQueryId
					}
	$.ajax({
		type: "POST",
		url: 'editRule',
		dataType: 'json',
		data: JSON.stringify(jsonData),
		contentType: "application/json",
		success: function (response) {
			console.log(response);
			if (response.status == "SUCCESS") {
			//	alert("Rule successfully updated.");
				var msg="Rule successfully updated.";
				var status="Sucess";
				var style="alert-success";
				showalertMsg(msg,status,style);
				loadingImage('rulesFrame');
				jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
			}else {
				var style="alert-warning";
				var msg="Processing Error.Please try after some time.";
				var status="Error";
				showalertMsg(msg,status,style);
			//	alert("Processing Error.Please try after some time");
			}
		}
	});
	
}

function openlegalfieldmodel(){
	var tablecol=[];
	var namesTOshow=[];
	$('.countyTableColumnsValue :selected').each(function(i, colName){ 
		tablecol[i] = $(colName).val();
	});
	for (var j = 0; j < tablecol.length; j++) {
		var columnName=tablecol[j].split("-");
		namesTOshow.push(columnName[0]);
   }
	/*var option='';
    for (var i = 0; i < tablecol.length; i++) {
          option += '<option value="' + tablecol[i] + '">' + tablecol[i] + '</option>';
     }*/
	 var model_dialog = $("#showColumnsDialogForBuildRule").dialog({
			close: function(event, ui) {
		            $('#overlay').hide();
		            buildRuleTableCount = 0;
		        },
		        autoOpen: false,
		        title: "Columns To Cancat",
		        height: 300,
		        width: 700,
		        show: {
		            effect: 'fade',
		            duration: 1000
		        },
		        resizable: true,
		        modal: true,
		        buttons: {
		             Ok: function() {
		            	 var colToCancat=[];
		            	 var queryValue='';
		            	 var table='';
		            	 var values=$('#SelectCols').val();
		            	 for (var m = 0; m< values.length; m++) {
		            		 queryValue=queryValue+"["+values[m]+"]+";
		            	 }
		            	 $('#countyTablesSelect :selected').each(function(i, tableName){ 
		            		    tableArr[i] = $(tableName).val();
		            		    table =$(tableName).val();
		            		});
		            	 queryValue=queryValue.slice(0,-1);
		            	 queryValue=queryValue+"-"+table;
		            	// query=query.slice(0,-1);
		            	 $('#formedQuery').val(queryValue);
		            	    sessionStorage.setItem("fieldQuery", queryValue);
		             },
			        close:function(){
						model_dialog.dialog("close");
					} 
		         }
             
	  	});
	 var option=[];
	 var optionValues=[];
	 for (var i = 0; i < tablecol.length; i++) {
         option.push(tablecol[i].split(" ").join("%20"));
    }
	 for (var k = 0; k < namesTOshow.length; k++) {
		 optionValues.push(namesTOshow[k].split(" ").join("%20"));
    }
	    model_dialog.load("FieldValidation69.jsp?tablecolumns="+option+"&namesTOshow="+optionValues).dialog("open");
	    
}
function getCountyTableColumns() {
	var tableNames = $('#countyTablesSelect').val();
	var fields = $("#fields").val();
	var countyTableNames = "";

	if (tableNames != null && tableNames != "") {
		for (var i = 0; i < tableNames.length; i++) {
			countyTableNames = countyTableNames + "'" + tableNames[i] + "',";
		}
		countyTableNames = countyTableNames.slice(0, -1);
		var data = "countyTableNames=" + countyTableNames;
		loadingImage('countyTableColumnsDiv');
		var tableArr = [];
		$('#countyTablesSelect :selected').each(function(i, tableName){ 
		    tableArr[i] = $(tableName).val();
		});
		jqueryAjaxfunction('getCountyTableColumns','countyTableColumnsDiv', data,fields,tableArr.length);
		
	/*	if(fields=="Legal Brief Description-69"&&tableArr.length==1){
			$('#specificforfield').empty();
			$('#specificforfield').append("<tr><td><button id='modelfileld' class='btn btn-success' onclick = 'openlegalfieldmodel();'>Save</button></td></tr>");
			alert("added");
			$('#modelfileld').hidden="";
			alert("show");
		}
		else{
			$('#modelfileld').hide();
		}*/
		
	
		/*var model_dialog = $("#conformationDialog").dialog(
				{
					close: function(event, ui) {
						$(".index").removeClass("mask");
		             },
					autoOpen : false,
					title : "Confirmation window",
					height : 150,
					width : 400,
					draggable : true,
					resizable : false,
					buttons : {
						Submit : function() {
							$(".index").removeClass("mask");
							jqueryAjaxfunction('getCountyTableColumns','countyTableColumnsDiv', data);
							$(this).dialog("close");
							$("#addRule").prop("disabled",true);
	
						},
					}
				});
		$("#conformationDialog").empty();
		if(fields == "" || fields == null){
			$(".index").addClass("mask");
			$("#conformationDialog").append('<p>Field is not selected.DO you want save this rule in county level</p>');
			model_dialog.dialog("open");
		}else{
			jqueryAjaxfunction('getCountyTableColumns','countyTableColumnsDiv', data);
		}*/
	}
}

function buildQuery(){
	var tableArr = [];
	var colArr = [];
	var colCount = 0;
	var queryString = '';
	var colString = '';
	var tableString = '';
	var tableAlias = '';
	var tableData;
	var tableName;
	var colConcatName;
	var columnInfo;
	var joinsData = "";
    var joinRelation = "";
    var joinCondtion = "";
    var tableNamesSelected = "";
    var selectedTableNames = [];
    var selectedTableNamesWithAlias = [];
	var aliasArray = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
	$('#countyTablesSelect :selected').each(function(i, tableNameCheck){ 
	    tableArr[i] = $(tableNameCheck).val()+"|"+aliasArray[i];
	    selectedTableNamesWithAlias[i] =$(tableNameCheck).val()+" "+aliasArray[i]; 
	    tableString = tableString+$(tableNameCheck).val()+" "+aliasArray[i]+', ' ;
	    if(i == 1){
	    	tableString = tableString.slice(0,-1);
	    }
	});
	
	
	for(var tableArray=0; tableArray < tableArr.length; tableArray++){
		tableData  = tableArr[tableArray].split("|");
		tableName = tableData[0];
		tableAlias = tableData[1];
		selectedTableNames[tableArray] = tableName;
		console.log(selectedTableNames[tableArray]);
		tableNamesSelected += (tableName+",");
		console.log(tableNamesSelected);
		var tableColCountCk = true;
		if(tableArr.length > 1){
			$('#'+tableName+' :selected').each(function(j, colName){ 
				columnInfo = $(colName).val().split("-");
    			colArr[colCount] = columnInfo[0];
    		    colConcatName = tableAlias+"."+"["+columnInfo[0]+"] ["+tableAlias+"_"+"["+columnInfo[0]+"]";
    		    colString = colString+colConcatName+', ';
    		    colCount = colCount+1;
    		    tableColCountCk = false;
			});
			if(tableColCountCk == true){
				var tableNameAsId = document.getElementById(tableName);		
		       	for (var tableArrayCheck = 0; tableArrayCheck < tableNameAsId.length; tableArrayCheck++) { 
		       		colConcatName = tableAlias+"."+"["+tableNameAsId.options[tableArrayCheck].text+"] ["+tableAlias+"_"+tableNameAsId.options[tableArrayCheck].text+"]";
		       		colString = colString+colConcatName+', ';
		       		/*console.log(colString);*/
		       	}
			}
		}
		else{
			$('#'+tableName+' :selected').each(function(j, colName){ 
				var columnInfo = $(colName).val().split("-");
    			colArr[colCount] = columnInfo[0];
    			var colConcatName = "["+columnInfo[0]+"]";
    			colString = colString+colConcatName+', ';
    		    colCount = colCount+1;
			});	
			tableString = tableString.slice(0,-2);
			var concatColumnsData = sessionStorage.getItem("fieldQuery");
			if(concatColumnsData != ""||concatColumnsData !=null){
				concatColumnsData=concatColumnsData.split("-");
            	concatColumnsDataValue=concatColumnsData[0];
				var colConcatName = concatColumnsDataValue +" as FieldlegalDiscription  ";
    		    colString = colString+colConcatName;
			}
		}
	}
	
	if(tableString != ""){
		tableString.slice(0,-2);
	}
	else{
		var status="Error";
		var style="alert-danger";
		var msg="Please select tables and Try agian";
		showalertMsg(msg,status,style);
    	return false;
	}
//	if(colString != ""){
//		colString.slice(0, -2);
//	}
//	else{
//		var status="Error";
//		var style="alert-danger";
//		var msg="Please select columns and Try agian";
//		showalertMsg(msg,status,style);
//    	return false;
//	}
	
	colString.slice(0, -2);
	if(tableArr.length > 1){
	
	if(tableNamesSelected.endsWith(',')){
		tableNamesSelected = tableNamesSelected.slice(0,-1);
	}
	
    	$.ajax({
        	type: "POST",
    	    url: 'executeQueryToGetJoins',
    	    async: false, 
    	    data: {
    	    	'tableNamesSelected' : tableNamesSelected
    	    },
    	     success : function(data) {
    	    	 joinsData = data;
    	    	 joinsData = joinsData.toString().split(",");
    	    	 joinCondtion = joinsData[0];
            }
        });
	}
    setTimeout(function() {
		if(tableArr.length == 1 && colCount == 0){
			queryString = queryString+'SELECT * FROM '+tableString.slice(0,-2);
		}
		else{
			if(joinsData != "" && joinRelation != undefined){
				for(var t=0; t < tableArr.length; t++){
					var columnAlias = selectedTableNames[t] + ".";
					var columnAliasArr = aliasArray[t] +"."; 
					joinCondtion = joinCondtion.replace(selectedTableNames[t], selectedTableNamesWithAlias[t]);
					for(var i = 0;i<6;i++){
						joinCondtion = joinCondtion.replace(columnAlias,columnAliasArr);
					}
				}
        		queryString = queryString+'SELECT '+colString.slice(0, -2) + ' FROM ' +  joinCondtion;
        	} 
			else{
				if(tableArr.length > 1){
					var message = "Please create the join condition and try agian";
					var status="Warning";
	        		var style="alert-warning";
					showalertMsg(message,status,style);
	        		/*queryString = queryString+'SELECT '+colString.slice(0, -2) +' FROM '+tableString.slice(0,-1) ;*/
					return false;
				}
				else{
					queryString = queryString+'SELECT '+colString.slice(0, -2) +' FROM '+tableString.slice(0,-1) ;
				}
        	}
		}
		$("#baseQuery").val(queryString);
		$("#baseQuery").attr('title',queryString);
    },0.1);
		option = "";
		  $('#selected_columns').empty();
			
	      for (var i = 0; i < colArr.length; i++) {
	          option += '<option value="' + colArr[i] + '">' + colArr[i] + '</option>';
	      }
	      $('#selected_columns').append(option);
	      $('#selected_columns').multiselect('rebuild');
	      $('#selected_columns').multiselect({
	            includeSelectAllOption: true,
	            nonSelectedText: '--Please Select--',
	            disableIfEmpty: true,
	            enableCaseInsensitiveFiltering : true
	      });
   
	var baseQuery =$("#baseQuery").val();
	var ruleQuery =$("#query").val();
  	var specialChars = ['%','&','+','#'];
	var codeNumbersForSplChars = ['%25','%26','%2B','%23'];

	for(var sp = 0 ; sp < specialChars.length;sp++){
		if(baseQuery.indexOf(specialChars[sp]) >0){
			baseQuery = baseQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
    	}
		if(ruleQuery.indexOf(specialChars[sp]) >0){
    		ruleQuery = ruleQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
    }
	}	
			if(baseQuery != ""){
				$("#iframecontainer11").empty();
				$("#iframecontainer12").empty();
				var iframe = document.createElement('iframe');
				iframe.frameBorder=0;
				/*iframe.width= $(window).width()+'px';
				iframe.height=$(window).height()+'px';*/
				iframe.className = "col-md-12 col-sm-6 col-xs-12";
				iframe.height="600px";
				iframe.scrool="none";
				iframe.id="GridDisplay";
				iframe.setAttribute("src", "./gridDisplay.jsp?baseQuery="+baseQuery+"&ruleQuery="+ruleQuery);
				$("#iframecontainer12").append(iframe);
			}
	  /*}, 1);*/
	/*var baseQuery =$("#baseQuery").val();
	var ruleQuery =$("#query").val();
  	var specialChars = ['%','&','+','#'];
	var codeNumbersForSplChars = ['%25','%26','%2B','%23'];

	for(var sp = 0 ; sp < specialChars.length;sp++){
		if(baseQuery.indexOf(specialChars[sp]) >0){
			baseQuery = baseQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
    	}
		if(ruleQuery.indexOf(specialChars[sp]) >0){
    		ruleQuery = ruleQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
    }
	}	
	$("#GridDisplay").removeAttr("src");
	$("#GridDisplay2").removeAttr("src");
	$("#GridDisplay2").attr("src","./gridDisplay.jsp?baseQuery="+baseQuery+"&ruleQuery="+ruleQuery);*/
}
/*$(document).on('click', '#temptablecheckbox', function(){
	var name = this.id;
    var check = $(this).prop('checked');
    alert(name+"  "+check);
});*/
$(document).on('click', '#rulerefresh', function(){
	//$("#ruleQuery").val(localStorage.getItem("resultRuleQuery"));
	$("#query").val(localStorage.getItem("resultRuleQuery"));
	$("#query71").val(localStorage.getItem("resultRuleQuery71"));
	$("#query72").val(localStorage.getItem("resultRuleQuery72"));
	$("#query73").val(localStorage.getItem("resultRuleQuery73"));
	
	$("#ruleColumn").val(localStorage.getItem("ruleColumn"));
	$("#buildRuleQuery").val(localStorage.getItem("buildRuleQuery"));
	$("#buildRuleQuery71").val(localStorage.getItem("buildRuleQuery71"));
	$("#buildRuleQuery72").val(localStorage.getItem("buildRuleQuery72"));
	$("#buildRuleQuery73").val(localStorage.getItem("buildRuleQuery73"));
});


function fullSceen(){
	fullScreen();	
};
$(document).keydown(function(e){
	if (e.keyCode==27){
		$("#GridDisplay").removeClass("maximized");
		$("#GridDisplay2").removeClass("maximized");
	}
});
function fullScreen(){
	$("#GridDisplay").addClass("maximized");
	$("#GridDisplay2").addClass("maximized");
}

function buildRule(){
	var tableArr = [];
	var colArr = [];
	var colCount = 0;
	var queryString = '';
	var colString = '';
	var tableString = '';
	/*alert("buildRule");*/
	
	$('#countyTables :selected').each(function(i, tableName){ 
	    tableArr[i] = $(tableName).val();
	    tableString = tableString+$(tableName).val()+', ';
	});
	
	for(i=0; i<tableArr.length; i++){
		$('#'+tableArr[i]+' :selected').each(function(j, colName){ 
		    colArr[colCount] = $(colName).val();
		    colString = colString+$(colName).val()+', ';
		    colCount = colCount+1;

		});
	}
	tableString.slice(0,-2);
	colString.slice(0, -2);
	var selectedColumns=$('#selected_columns').val();
	var operation=$('#column_operators').val();
	if(operation=="CONCAT"){
		queryString = queryString+'SELECT '+operation+"("+selectedColumns+') FROM '+tableString.slice(0,-2) ;
		$("#ruleQuery").val(queryString);
		$("#ruleQuery").attr('title',queryString);
	}
	else if(operation=="REPLACE"){	
		var operationField1=$('#operation_field1').val();
		var operationField2=$('#operation_field2').val();
		queryString = queryString+'SELECT '+operation+"("+selectedColumns+') FROM '+tableString.slice(0,-2);
		$("#ruleQuery").val(queryString);
		console.log(queryString);
		$("#ruleQuery").attr('title',queryString);
    }
	
}

function saveRule(){
	var feiladNameForFile=$("#fieldName").val();
	console.log(feiladNameForFile);
	var tableArr = [];
	var colArr = [];
	var colCount = 0;
	var queryString = '';
	var colString = '';
	var tableString = '';
	var fieldName = $("#fieldName").val();
	$('#countyTablesSelect :selected').each(function(i, tableName){ 
	    tableArr[i] = $(tableName).val();
	    tableString = tableString+$(tableName).val()+', ';
	});
	
	for(i=0; i<tableArr.length; i++){
		$('#'+tableArr[i]+' :selected').each(function(j, colName){ 
		    colArr[colCount] = $(colName).val();
		    var col = $(colName).val().split("-");
		    colString = colString+col[0]+', ';
		    colCount = colCount+1;
		});
	}
	tableString = tableString.slice(0,tableString.length-2);
	colString = colString.slice(0,colString.length-2);
	
	if(tableString == "" || tableString == "undefined"){
		tableString = $("#tables").val();
	}
	if(colString == "" || colString == undefined){
		colString = $("#columns").val();
	}
	
	var editonNumber = $("#editonNumber").val();
	if(editonNumber == "" || editonNumber == undefined){
		editonNumber = $("#editonHidden").val();
	}
	var countryFipsCode = $("#countryFipsCode").val();
	var stateFipsCode = $("#stateFipsCode").val();
	var countyFipsCode = $("#countyFipsCode").val();
	var ruleQuery = $("#query").val();
	var fieldNumber = $("#fieldNumber").val();
	var countryName = $("#countryName").val();
	var stateName = $("#stateName").val();
	var countyName = $("#countyName").val();
	var ruleScope = 'STATE';
	var ruleName = $("#ruleQuery").val();//'Some Rule';
	//alert("ruleName :" +ruleName+" stateName " + stateName+" "+"countryName" + countryName+" " + "countyName" + countyName);
	var fieldName=$("#fieldName").val();
	//ruleName = "Rule For Field No "+fieldNumber;
	if(ruleName == "Please write your rule here.."){
		 $("#warningDiv").show();
         var model_dialog = $("#warningDiv").dialog({
             autoOpen: false,
             title: "Hint",
             height: 200,
             width: 600,
             resizable: true,
             draggable: true,
             modal: true,
             show: {
                 effect: 'fade',
                 duration: 1000
             }
         });
         $("#warningDiv").empty();
         $("#warningDiv").prepend($('<p style="padding-top: 10px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Please write your rule and Try again</p>'));
         model_dialog.dialog("open");
	}
	else{
	var query = $("#query").val();
	var baseQuery = $("#baseQuery").val();
	var resultBaseQuery = $("#resultBaseQuery").val();
	var ruleColumn = $("#ruleColumn").val();
	var buildRuleQuery = $("#buildRuleQuery").val();
	console.log(baseQuery);
	console.log(resultBaseQuery);
	console.log(ruleColumn);
	console.log(buildRuleQuery);
	
	var jsonData = {};
	if(fieldNumber == 71 || fieldNumber == 72 || fieldNumber == 73){
		var ruleQuery71 = $("#query71").val();
    	var ruleQuery72 = $("#query72").val();
    	var ruleQuery73 = $("#query73").val();
    	var ruleName = $("#ruleName").val();
    	
    	var ruleColumn = $("#ruleColumn").val();
    	var buildRuleQuery = $("#buildRuleQuery").val();
    	var buildRuleQuery71 = $("#buildRuleQuery71").val();
    	var buildRuleQuery72 = $("#buildRuleQuery72").val();
    	var buildRuleQuery73 = $("#buildRuleQuery73").val();
    	
    	var rowDataArr_71_72_73 = [];
    	rowDataArr_71_72_73 = JSON.parse(localStorage.getItem('rowDataArr_71_72_73'));
		jsonData = {"edition": editonNumber,
				"countryFipsCode": countryFipsCode, 
				"stateFipsCode": stateFipsCode,
				"countyFipsCode": countyFipsCode,
				"fieldName":feiladNameForFile,
				"countryName": countryName,
				"stateName": stateName,
				"countyName": countyName,
				"rowDataArr_71_72_73": rowDataArr_71_72_73,
				"fieldNumber": fieldNumber,
				"ruleQuery": ruleQuery,
				"ruleQuery71": ruleQuery71,
				"ruleQuery72": ruleQuery72,
				"ruleQuery73": ruleQuery73,
				"ruleName": ruleName,
				"query":query,
				"baseQuery":baseQuery,
				"tables":tableString,
				"columns":colString,
				"buildbasequery" :resultBaseQuery,
				"buildrulequery" :buildRuleQuery,
				"buildRuleQuery71" :buildRuleQuery71,
				"buildRuleQuery72" :buildRuleQuery72,
				"buildRuleQuery73" : buildRuleQuery73,
				"ruleColumn" :ruleColumn
			    }
	}
	
	else if((fieldNumber==8)||(fieldNumber==11)||(fieldNumber==10)||(fieldNumber==20)||(fieldNumber==33)||(fieldNumber==106)||(fieldNumber==107)||(fieldNumber==108)||(fieldNumber==176)||(fieldNumber==86)||(fieldNumber==88)||(fieldNumber==177)||(fieldNumber==178)||(fieldNumber==181)||(fieldNumber==182)||(fieldNumber==183)||(fieldNumber==184)||(fieldNumber==185)||(fieldNumber==186)||(fieldNumber==171)||(fieldNumber==172)||(fieldNumber==173)||(fieldNumber==174)||(fieldNumber==175)||(fieldNumber==163)||(fieldNumber==164)||(fieldNumber==165)||(fieldNumber==166)||(fieldNumber==169)||(fieldNumber==138)||(fieldNumber==137)||(fieldNumber==136)||(fieldNumber==135)){

		var rowDataArr_ForIndividual = [];
		rowDataArr_ForIndividual = JSON.parse(localStorage.getItem('rowDataArr_ForIndividual'));
		jsonData = {"edition": editonNumber,
				"countryFipsCode": countryFipsCode, 
				"stateFipsCode": stateFipsCode,
				"countyFipsCode": countyFipsCode,
				"fieldName":feiladNameForFile,
				"countryName": countryName,
				"stateName": stateName,
				"rowDataArr_ForIndividual": rowDataArr_ForIndividual,
				"countyName": countyName,
				"fieldNumber": fieldNumber,
				"ruleQuery": ruleQuery,
				"ruleName": ruleName,
				"query":query,
				"baseQuery":baseQuery,
				"tables":tableString,
				"columns":colString,
				"buildbasequery":resultBaseQuery,
				"buildrulequery":buildRuleQuery,
				"ruleColumn":ruleColumn
				
	}
	}
	else{
	jsonData = {"edition": editonNumber,
						"countryFipsCode": countryFipsCode, 
						"stateFipsCode": stateFipsCode,
						"countyFipsCode": countyFipsCode,
						"countryName": countryName,
						"stateName": stateName,
						"countyName": countyName,
						"fieldNumber": fieldNumber,
						"fieldName":feiladNameForFile,
						"ruleQuery": ruleQuery,
						"ruleName": ruleName,
						"query":query,
						"baseQuery":baseQuery,
						"tables":tableString,
						"columns":colString,
						"buildbasequery":resultBaseQuery,
						"buildrulequery":buildRuleQuery,
						"ruleColumn":ruleColumn
					
			}
	}

	var countryFipsCode=$("#countryFipsCode").val();
	var ruleLevel=$("#ruleLevel").val();
	var stateFipsCode=	$("#stateFipsCode").val();
	var countyFipsCode=	$("#countyFipsCode").val();
	var countryName=$("#countryName").val();
	var stateName=	$("#stateName").val();
	var fieldNumber=$("#fieldNumber").val();
		var stateCode=	$("#stateCode").val();
		var countyName=$("#countyName").val();
		//alert(stateCode+"----"+countyName);
		var dataValues="countryFipsCode="+countryFipsCode
		+"&stateFipsCode="+stateFipsCode
		+"&countyFipsCode="+countyFipsCode
		+"&ruleLevel="+ruleLevel
		+"&countyName="+countyName
		+"&stateCode="+stateCode
		+"&fieldNumber="+fieldNumber
		+"&pageSize="+5
		+"&fieldName="+fieldName
		+"&pageNumber="+1;
		console.log(dataValues);
	$.ajax({
        type: "POST",
        url: 'saveRule',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        success: function (response) {
        	console.log(response);
        	if(response.status == "EXISTS"){
        		//alert("Rule Name already exists.");
        		var msg="Rule Name already exists.";
        		var status="Warning";
        		var style="alert-warning";
        		showalertMsg(msg,status,style);
        		//loadingImage('rulesFrame');
        		jqueryAjaxfunction('getCountyTables','rulesFrame',data);
        	}else if (response.status == "SUCCESS") {
        		/*if(fieldNumber==18||fieldNumber==19||fieldNumber==22){
        			fieldNumber18To22Validation(fieldName,fieldNumber,countyName,editonNumber,countryFipsCode,stateFipsCode,countyFipsCode,stateName,countryName,dataValues);
        		}
        		else if(fieldNumber==171||fieldNumber==172||fieldNumber==173||fieldNumber==174||fieldNumber==175||fieldNumber==187||fieldNumber==52||fieldNumber==55){
        			fieldNumber171To175Validation(fieldName,fieldNumber,countyName,editonNumber,countryFipsCode,stateFipsCode,countyFipsCode,stateName,countryName,dataValues);
        		}*/
        		/*else if(fieldNumber!=171||fieldNumber!=172||fieldNumber!=173||fieldNumber!=174||fieldNumber!=175||fieldNumber!=187||fieldNumber!=52||fieldNumber!=55||fieldNumber!=22||fieldNumber!=19||fieldNumber!=18){
        			jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
        		}*/
        		jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
        		var msg="Rule successfully saved.";
        		var status="Sucess";
        		var style="alert-success";
        		showalertMsg(msg,status,style);
        		return false;
        		//loadingImage('rulesFrame');
        		//jqueryAjaxfunction('getCountyTables','rulesFrame',data);

        		/*loadingImage('rulesFrame');*/ 
        		
			}else {
				var msg="Processing Error.Please try after some time";
				var status="Error";
				var style="alert-danger";
				showalertMsg(msg,status,style);
				return false;
				//alert("Processing Error.Please try after some time");
			}
        }
	});
	}
}
	function fieldNumber171To175Validation(fieldName,fieldNumber,countyName,editonNumber,countryFipsCode,stateFipsCode,countyFipsCode,stateName,countryName,dataValues){
	var jspToLoad="";	
	var fieldAliasName = "";
	var dependentfieldName="";
	if(fieldNumber==171){
		dependentfieldName='Amenities';
			dependentFieldNumber=163;
			jspToLoad="FieldValidation171To175.jsp";
			fieldAliasName = "OTHERIMPRBUILDINGINDICATOR1";
		}
	if(fieldNumber==55){
		dependentfieldName='Prior Sale Price Code';
		dependentFieldNumber=56;
		jspToLoad="FieldValidation55and56.jsp";
	}
		else if(fieldNumber==172){
			dependentfieldName='OTHER IMPR BLDG IND 2';
			dependentFieldNumber=164;
			jspToLoad="FieldValidation171To175.jsp";
			fieldAliasName = "OTHERIMPRBUILDINGINDICATOR2";
		}
		else if(fieldNumber==173){
			dependentfieldName='OTHER IMPR BLDG IND 3';
			dependentFieldNumber=165;
			jspToLoad="FieldValidation171To175.jsp";
			fieldAliasName = "OTHERIMPRBUILDINGINDICATOR3";
		}
		else if(fieldNumber==174){
			dependentfieldName='OTHER IMPR BLDG IND 4';
			dependentFieldNumber=166;
			jspToLoad="FieldValidation171To175.jsp";
			fieldAliasName = "OTHERIMPRBUILDINGINDICATOR4";
		}
		else if(fieldNumber==175){
			dependentfieldName='OTHER IMPR BLDG IND 5';
			dependentFieldNumber=169;
			jspToLoad="FieldValidation171To175.jsp";
			fieldAliasName = "OTHERIMPRBUILDINGINDICATOR5";
		}
		else if(fieldNumber==187){
			dependentfieldName='Alt/Old APN Indicator';
			dependentFieldNumber=129;
			jspToLoad="fieldValidation187.jsp";
			fieldAliasName = "ALTOLDAPNINDICATOR";
		}
		else if(fieldNumber==52){
			dependentfieldName='Sale Price Code';
			dependentFieldNumber=53;
			jspToLoad="FieldValidation53.jsp";
			fieldAliasName = "SALESPRICE";
		}
		$.ajax({
			type: "POST",
			url: 'saveForFields',
			async: false,
			data: {
			'fieldNumber':dependentFieldNumber,
			'county':countyName
			},
			success: function (data) {
				if(data.length==0){
					var ruleLevel='';
					var ruleLevelNumber='';
					var model_dialog = $("#showDivForRuleLevel171").dialog({
					    autoOpen: false,
					    title: dependentFieldNumber+": "+dependentfieldName,
					    height: 200,
					    width: 577,
					    resizable: true,
					    buttons: {
					            Save: function() {
					            	var ruleLevelNumber=$('#SelectCodes').val();
					            	query = "select " +ruleLevelNumber +" as "+ fieldAliasName;
					        		ruleQuery = "Load as " +ruleLevelNumber;
					        		ruleName = "Rule For " +ruleLevelNumber;
					            	var jsonData = {
					            			"countryFipsCode": countryFipsCode, 
					    					"stateFipsCode": stateFipsCode,
					    					"countyFipsCode": countyFipsCode,
					    					"countryName": countryName,
					    					"stateName": stateName,
					    					"countyName": countyName,
					    					"fieldNumber": dependentFieldNumber,
					    					"ruleQuery": ruleQuery,
					    					"ruleName": ruleName,
					    					"query":query
					    			}
					            	if(ruleLevelNumber!=''){
					    	$.ajax({
					            type: "POST",
					            url: 'saveRule',
					            dataType: 'json',
					            data: JSON.stringify(jsonData),
					            contentType: "application/json",
					            success: function (data) {
					            	console.log(data);
					            	if(data.status == "EXISTS"){
					            		loadingImage('rulesFrame');
					            		jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
					            		//alert("Rule Name already exists.");
					            		var msg="Rule Name already exists.";
					            		var status="Alert";
					            		var style="alert-info";
					            	//	showalertMsg(msg,status,style);
					            	}else if (data.status == "SUCCESS") {
					            		loadingImage('rulesFrame');
					            		jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
					            	//	alert("Rule successfully saved.");
					            		var status="Sucess";
					            		var style="alert-success";
					            		var msg="Rule successfully saved.";
					            	//	showalertMsg(msg,status,style);
					    			}else {
					    				loadingImage('rulesFrame');
					    				jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
					    				var status="Error";
					    				var style="alert-danger";
					    				var msg="Processing Error.Please try after some time.";
					    			//	alert("Processing Error.Please try after some time");
					    				//showalertMsg(msg,status,style);
					    			}
					            	model_dialog.dialog("close");
					            }
					    	});
					            }     	
					    },
					    close:function(){
							model_dialog.dialog("close");
						}
					 }
					  	});
					    model_dialog.load(jspToLoad).dialog("open");
				}
			},
			error:function (data) {
				alert('error');
			}
			});
	}
	function showalertMsg(message,status,style){
		 var model_dialog = $("#alertmsgdivs").dialog({
             autoOpen: false,
             title: status,
             height: 150,
             width: 400,
             resizable: false,
             draggable: false,
             position:{
                 my: 'center',
                 at: 'center',
                 of:window
               },
             modal: true,
             show: {
                 effect: 'fade',
                 duration: 1000
             }
         });
         $("#alertmsgdivs").empty();
         $("#alertmsgdivs").prepend('<div class="alert '+style+' role="alert">'+message+'</div>');
         model_dialog.dialog("open");
       //  $( "#alertmsgdivs" ).dialog( "moveToTop" );
	}
/*function fieldNumber18To22Validation(fieldName,fieldNumber,countyName,editonNumber,countryFipsCode,stateFipsCode,countyFipsCode,stateName,countryName,dataValues){
	var dependentFieldNumber=0;
	var fieldNameToShow;
	if(fieldNumber==18){
		var fieldNameToShow="Assessee(Owner) Name Ind.";
		dependentFieldNumber=124;
		fieldAliasName = "ASSESSEEOWNERNAMEINDICATOR";
	}
	else if(fieldNumber==19){
		var fieldNameToShow="2nd Assessee(Owner) Name Ind.";
		dependentFieldNumber=125;
		fieldAliasName = "SECOND_ASSESSEEOWNER_INDICATOR";
	}
	else if(fieldNumber==22){
		var fieldNameToShow="Mail Care-Of Name Indicator";
		dependentFieldNumber=126;
		fieldAliasName = "MAILCAREOFNAMEINDICATOR";
	}
	$.ajax({
		type: "POST",
		url: 'saveForFields',
		async: false,
		data: {
		'fieldNumber':dependentFieldNumber,
		'county':countyName
		},
		success: function (data) {
			if(data.length==0){
				var ruleLevel='';
				var ruleLevelNumber='';
				var model_dialog = $("#showDivForRuleLevel").dialog({
				    autoOpen: false,
				    title: dependentFieldNumber+": "+fieldNameToShow,
				    height: 200,
				    width: 577,
				    resizable: true,
				    buttons: {
				            Save: function() {
				            	if ($("#1stButton").prop("checked")) {
				            		ruleLevelNumber=1;
				            			ruleLevel=$('#1stButton').val();
				            	}
				            	if ($("#2stButton").prop("checked")) {
				            		ruleLevelNumber=2;
			            			ruleLevel=$('#2stButton').val();
				            	}
				            	if ($("#3stButton").prop("checked")) {
				            		ruleLevelNumber=3;
			            			ruleLevel=$('#3stButton').val();
				            	}
				            	if ($("#4stButton").prop("checked")) {
				            		ruleLevelNumber=4;
			            			ruleLevel=$('#4stButton').val();
				            	}
				           	
				            	query = "select " +ruleLevelNumber + " as " + fieldAliasName;
				        		ruleQuery = "Load as " +ruleLevelNumber;
				        		ruleName = "Rule For " +ruleLevelNumber; 
				            	var jsonData = {
				            			"countryFipsCode": countryFipsCode, 
				    					"stateFipsCode": stateFipsCode,
				    					"countyFipsCode": countyFipsCode,
				    					"countryName": countryName,
				    					"stateName": stateName,
				    					"countyName": countyName,
				    					"fieldNumber": dependentFieldNumber,
				    					"ruleQuery": ruleQuery,
				    					"ruleName": ruleName,
				    					"query":query
				    			}

				            	if(ruleLevelNumber!=''){
				    	$.ajax({
				            type: "POST",
				            url: 'saveRule',
				            dataType: 'json',
				            data: JSON.stringify(jsonData),
				            contentType: "application/json",
				            success: function (data) {
				            	console.log(data);
				            	if(data.status == "EXISTS"){
				            		loadingImage('rulesFrame');
				            		jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
				            	//	alert("Rule Name already exists.");
				            		var msg="Rule Name already exists.";
				            		var status="Alert";
				            		var style="alert-info";
				            	//	showalertMsg(msg,status,style);
				            	}else if (data.status == "SUCCESS") {
				            		loadingImage('rulesFrame');
				            		jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
				            	//	alert("Rule successfully saved.");
				            		var status="Sucess";
				            		var style="alert-success";
				            		var msg="Rule successfully saved.";
				            	//	showalertMsg(msg,status,style);
				            		
				    			}else {
				    				//alert("Processing Error.Please try after some time");
				    				jqueryAjaxfunction('getCountyTables','rulesFrame',dataValues);
				    				var status="Error";
				    				var style="alert-danger";
				    				var msg="Processing Error.Please try after some time.";
				    			//	showalertMsg(msg,status,style);
				    			}
				            	model_dialog.dialog("close");
				            }
				    	});
				            	}	
				    },
				    close:function(){
						model_dialog.dialog("close");
					}
				 }
				
				  	});
				    model_dialog.load("saveRuleLevel.jsp").dialog("open");
			}
		},
		error:function (data) {
			alert('error');
		}
		});
}*/

var patternNumber = 0;
function getPage(page){
	var pageNumber = parseInt($("#pageNumber").val());
	if(page == "NEXT"){
		pageNumber = pageNumber+1;
	}else if(page == "PREVIOUS"){
		pageNumber = pageNumber-1;
	}
	
	$("#ruleQueryGet").css('display','none');
	var countryFipsCode = $("#countryFipsCode").val();
	if($.trim(countryFipsCode) == ""){
		countryFipsCode = "0";
	}
	var stateFipsCode = $("#stateFipsCode").val();
	if($.trim(stateFipsCode) == ""){
		stateFipsCode = "0";
	}
	var countyFipsCode = $("#countyFipsCode").val();
	if($.trim(countyFipsCode) == ""){
		countyFipsCode = "0";
	}
	var countryName = $("#countryName").val();
	var stateName = $("#stateName").val();
	var countyName = $("#countyName").val();
	var fieldNumberValues = $("#fieldNameLabel").text();
	fieldNumberValues=fieldNumberValues.split("|");
	fieldNumber=fieldNumberValues[1];
	if(fieldNumber == "" || fieldNumber == "undefined"){
		fieldNumber = "0";
	}
	
	var ruleLevel = $("#ruleLevel").val();
	var jsonData = {"countryFipsCode": countryFipsCode,
					"stateFipsCode": stateFipsCode,
					"countyFipsCode": countyFipsCode,
					"ruleLevel": ruleLevel,
					"countyName": countyName,
					"fieldNumber": fieldNumber,
					"pageSize": 5,
					"pageNumber": pageNumber
					}
	console.log(jsonData);
	
	$.ajax({
        type: "POST",
        url: 'loadPageRules',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
        success: function (data) {
        	 $("#pageNumber").val(pageNumber);
        	console.log(data);
        	$("#countyTableColumnsDiv").empty();
        	$.each(data, function( index, value ) {
        		  console.log(index + ": " + value);
        		  patternNumber = parseInt(index+1);
        		  $("#countyTableColumnsDiv").append("<script> $('#addRule').show();$('textarea[name=textarea]').webuiPopover();</script>" +
        		  		"<div class='form-group'><label class='control-label col-md-2 text-right margin-top47'>Field-"+value.ruleMaster.fieldNumber+" :: Pattern-"+patternNumber+" <span class='required'></span></label>" +
        		  				"<div class='col-md-4'>" +
        		  				"<textarea rows='3' cols='3' id='"+value.ruleQueryId+"ruleQuery' data-title ='"+value.baseQuery+"'  name='textarea' class='auto form-control fieldRuleQuery margin-top20' style='overflow: hidden; word-wrap: break-word; resize: horizontal; height: 68px;' readOnly>"+value.rule+"</textarea>" +
        		  				"<input id = 'ruleQueryGet' value = '"+value.query+"' class = 'form-control' hidden = true style = 'display:none'/></div>"+
        		  						"<div class='col-md-4'><div class='btn-group margin-top40'>" +
        		  						"<button id='copyRule' class='btn btn-success margin-right' onclick='copyTheRule("+value.ruleQueryId+")'>Copy</button>" +
										"<button id='editRule' class='btn btn-default margin-right' onclick='editTheRule("+value.ruleQueryId+")'>Edit</button>" +
										"<button id='deleteRule' class='delete btn btn-warning' onclick='deleteTheRule("+value.ruleMaster.ruleId+")'>Delete</button>" +
										"<button id='except' class='btn btn-default margin-right' onclick='exceptRun("+value.ruleQueryId+")'>Except</button>"+
        		  						"</div></div></div>");
        		});
        }
	});
}





function editTheText(){
	$('#baseQuery').removeAttr('disabled');	
}

function saveTheText(){
	$('#baseQuery').attr('disabled',true);
}

function editTheRuleText(){
	$('#query').removeAttr('disabled');	
}

function saveTheRuleText(){
	$('#query').attr('disabled',true);
}


function saveHeaderRule(){
	var query = "" 
	var ruleQuery = "";
	var ruleName = "";
	var fieldsName = $("#fields").val();
	/*fieldsName = fieldsName.split("-");*/
	/*fieldsName = fieldsName[0];*/
	var fieldNumber = $("#fieldNumber").val();
	var countryFipsCode = $("#countryFipsCode").val();
	var stateFipsCode = $("#stateFipsCode").val();
	var countyFipsCode = $("#countyFipsCode").val();
	/*var fieldNumber = $("#fields").val().split("-");
	fieldNumber = fieldNumber[1];*/
	var fieldNumber = $("#fieldNumber").val();
	if(fieldNumber == "" || fieldNumber == "undefined"){
		fieldNumber = "0";
	}
	var countryName = $("#countryName").val();
	var stateName = $("#stateName").val();
	var countyName = $("#countyName").val();
	var ruleScope = 'STATE';
	var editionNumber = $("#editonNumber").val();
	//alert(editionNumber);
	if(fieldNumber == 1 || fieldNumber == 2 || fieldNumber == 3 || fieldNumber == 90 || fieldNumber == 109 || fieldNumber == 40 || fieldNumber == 91 || fieldNumber == 130){
		if (fieldsName == "State (Postal Code)"){
			query = "select '" + $("#countyTablesInput").val() + "' as STATEPOSTALCODE" ;
			ruleQuery = "Load as '" + $("#countyTablesInput").val() +"'";
			ruleName = "Rule For " + $("#countyTablesInput").val() ;
		}
		else if(fieldsName == "County Name"){
			query = "select '" + $("#countyTablesInput").val() +"' as COUNTYNAME";
			ruleQuery = "Load as '" + $("#countyTablesInput").val() +"'";
			ruleName = "Rule For " + $("#countyTablesInput").val();
		}
		else if(fieldsName == "Assessment Year"){
			query = "select '" + $("#countyAssessmentYear").val() + "' as ASSESSMENTYEAR";
			ruleQuery = "Load as '" + $("#countyAssessmentYear").val() +"'";
			ruleName = "Rule For " + $("#countyAssessmentYear").val();
		}
		else if(fieldsName == "Edition number"){
			var editionNumber = $("#countyEditionNumber").val();
			if(editionNumber < 10){
				editionNumber = "0"+editionNumber;
				//alert(editionNumber);
				query = "select '" + editionNumber + "' as EDITIONNUMBER";
				ruleQuery = "Load as '" + editionNumber + "'";
				ruleName = "Rule For " + editionNumber;
			}
			else{
				query = "select '" + $("#countyEditionNumber").val() + "' as EDITIONNUMBER";
				ruleQuery = "Load as '" + $("#countyEditionNumber").val() + "'";
				ruleName = "Rule For " + $("#countyEditionNumber").val();
			}
			
		}
		else if(fieldsName == "Certification Date"){
			query = "select '" + $("#countyCertificationDate").val() + "' as CERTIFICATIONDATE";
			ruleQuery = "Load as '" + $("#countyCertificationDate").val() + "'"; 
			ruleName = "Rule For " + $("#countyCertificationDate").val();
		}
		else if(fieldsName == "Tape Cut Date"){
			query = "select '" + $("#countyTapeCutDate").val() + "' as TAPECUTDATE";
			ruleQuery = "Load as '" + $("#countyTapeCutDate").val() +"'";
			ruleName = "Rule For " + $("#countyTapeCutDate").val();
		}
		else if (fieldsName == "FIPS Code"){
			query = "select '" + $("#countyFipsCode").val() +"' as FIPSCODE";
			ruleQuery = "Load as '" + $("#countyFipsCode").val()+"'";
			ruleName = "Rule For " + $("#countyFipsCode").val();
		}
		/*else if(fieldsName == "Old/Alternate APN"){
			query = "select " + $("#countyTablesAtOldAlternate").val();
			ruleQuery = "Load as " + $("#countyTablesAtOldAlternate").val();
			ruleName = "Rule For " + $("#countyTablesAtOldAlternate").val();
		}*/else{}
		
		
		
		var jsonData = {"edition":editionNumber,
						"countryFipsCode": countryFipsCode, 
						"stateFipsCode": stateFipsCode,
						"countyFipsCode": countyFipsCode,
						"countryName": countryName,
						"stateName": stateName,
						"countyName": countyName,
						"fieldNumber": fieldNumber,
						"ruleQuery": ruleQuery,
						"ruleName": ruleName,
						"query":query,
						}
		
		$.ajax({
	        type: "POST",
	        url: 'saveRule',
	        dataType: 'json',
	        data: JSON.stringify(jsonData),
	        contentType: "application/json",
	        success: function (data) {
	        	console.log(data);
	        	if(data.status == "EXISTS"){
	        		loadingImage('rulesFrame');
	        		jqueryAjaxfunction('getCountyTables','rulesFrame',data);
	        		var msg="Rule Name already exists.";
	        		var status="Alert";
	        		var style="alert-info";
	        		showalertMsg(msg,status,style);
	        	}else if (data.status == "SUCCESS") {
	        		loadingImage('rulesFrame');
	        		jqueryAjaxfunction('getCountyTables','rulesFrame',data);
	        		var status="Sucess";
	        		var style="alert-success";
	        		var msg="Rule successfully saved.";
	        		showalertMsg(msg,status,style);
				}else {
					var status="Error";
					var style="alert-danger";
					var msg="Processing Error.Please try after some time.";
					showalertMsg(msg,status,style);
				}
	        }
		});
	}
	
	
	
	
	else if(fieldNumber == 5 || fieldNumber == 36){
		var rowDataArr = JSON.parse(localStorage.getItem('rowDataArr'));
		var feiladNameForFile=$("#fieldName").val();
		//alert(rowDataArr);
		var ruleQuery = "Please Find the values for validations";
		var baseQuery = "";
		var query = "";
		var tableString = "";
		var colString = "";
		var jsonData = {"edition":editionNumber,
				"countryFipsCode": countryFipsCode, 
				"stateFipsCode": stateFipsCode,
				"countyFipsCode": countyFipsCode,
				"fieldName":feiladNameForFile,
				"countryName": countryName,
				"stateName": stateName,
				"countyName": countyName,
				"rowDataArr_ForIndividual": rowDataArr,
				"fieldNumber": fieldNumber,
				"ruleQuery": ruleQuery,
				"ruleName": ruleName,
				"query":query,
				"baseQuery":baseQuery,
				"tables":tableString,
				"columns":colString,
				
			    }
		
		$.ajax({
	        type: "POST",
	        url: 'saveRule',
	        dataType: 'json',
	        data: JSON.stringify(jsonData),
	        contentType: "application/json",
	        success: function (data) {
	        	console.log(data);
	        	if(data.status == "EXISTS"){
	        		loadingImage('rulesFrame');
	        		jqueryAjaxfunction('getCountyTables','rulesFrame',data);
	        		var msg="Rule Name already exists.";
	        		var status="Alert";
	        		var style="alert-info";
	        		showalertMsg(msg,status,style);
	        	}else if (data.status == "SUCCESS") {
	        		loadingImage('rulesFrame');
	        		jqueryAjaxfunction('getCountyTables','rulesFrame',data);
	        		var status="Sucess";
	        		var style="alert-success";
	        		var msg="Rule successfully saved.";
	        		showalertMsg(msg,status,style);
				}else {
					var status="Error";
					var style="alert-danger";
					var msg="Processing Error.Please try after some time.";
					showalertMsg(msg,status,style);
				}
	        }
		});
	}
}


function getCountyName(value){
	console.log(value);
	$("#countyNameSpecific").find("i").removeClass("icon-plus-sign");
	$("#countyNameSpecific").find("i").addClass("icon-minus-sign");
};

function getTheRulesFieldWise(){
	  var countryFipsCode = $("#countryFipsCode").val();
		var stateFipsCode = $("#stateFipsCode").val();
		var countyFipsCode = $("#countyFipsCode").val();
		var countryName = $("#countryName").val();
		var stateName = $("#stateName").val();
		var countyName = $("#countyName").val();
		var stateCode = $("#stateCode").val();
		/*var fieldsData =$("#fieldName").val().split('-');
		console.log(fieldsData);*/
		var ruleLevel  = $("#ruleLevel").val();
		/*alert(ruleLevel);
		console.log(ruleLevel);*/
		var fieldNumber = $("#fieldNumber").val();
		var fieldName=$("#fieldName").val();
		/*alert(fieldNumber);
		console.log(fieldNumber);*/
		if(fieldNumber == "" || fieldNumber == "undefined"){
			fieldNumber = "0";
		}
		var defaultFlag = $("#defaultFlag").val();
		var mappingFieldName = $("#mappingFieldName").val();
		
	var data="countryFipsCode="+countryFipsCode
	+"&stateFipsCode="+stateFipsCode
	+"&countyFipsCode="+countyFipsCode
	+"&ruleLevel="+ruleLevel
	+"&countyName="+countyName
	+"&stateCode="+stateCode
	+"&fieldNumber="+fieldNumber
	+"&fieldName="+fieldName
	+"&pageSize="+5
	+"&pageNumber="+1;
	
	loadingImage('rulesFrame');
	jqueryAjaxfunction('getCountyTables','rulesFrame',data);	
}

var tabRows = $('table#ruleInfoTab').find('tr');
for (var i = 0; i < tabRows.length; i++) {
	var tdCols = $(tabRows[i]).find('td');
	var colDataArr = [];
	for(var j = 0; j < tdCols.length; j++){
		colDataArr[j] = $(tdCols[j]).html();
		//console.log(colDataArr[j]);
	}
	rowDataArr_08[i] = colDataArr;
}