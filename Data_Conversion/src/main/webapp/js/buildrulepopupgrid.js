var app = angular.module('ruleapp', ['ngTouch', 'ui.grid','ngAnimate']);
app.controller('ruleQueryController', ['$scope', '$http', function($scope, $http) {
var options="";
var ruleQuery = "";
var buildRuleQuery = "";
var buildRuleTableCount = 0;

var ruleQuery71 = "";
var ruleQuery72 = "";
var ruleQuery73 = "";
var buildRuleQuery71 = "";
var buildRuleQuery72 = "";
var buildRuleQuery73 = "";

var rowDataArr_71_72_73 = [];
var rowDataArr_08=[];
var rowDataArr_singleExcel=[];
$scope.gridOptions = {
		onRegisterApi: function(gridApi){
		$scope.gridApi = gridApi;
		},
		enableSorting: false,
		headerHeight: 20,
		enableFiltering: true
	};
var colArr = [];
var fieldAliasName = $("#fieldAliasName").val();
    var ruleColumns = "<option value= ''>--Please Select--</option>";
 ruleColumns+=$("#ruleColumns").val();
var cancatColumns=sessionStorage.getItem("fieldQuery");
var buildBaseQuery  = localStorage.getItem("resultBuildQuery");
alert("buildBaseQuery : " +buildBaseQuery);
//var cancatColumns='<option value='+cancatColumns+'>'+cancatColumns+'</option>';
$('#commonColumn').append(ruleColumns);
$('#ruleColumn').append(ruleColumns);
/*$('#commonColumn').append(cancatColumns);
$('#ruleColumn').append(cancatColumns);*/
var opt = document.createElement('option');
opt.value = cancatColumns;
opt.innerHTML = cancatColumns;
$('#commonColumn').append(opt);
var opt = document.createElement('option');
opt.value = cancatColumns;
opt.innerHTML = cancatColumns;
$('#ruleColumn').append(opt);

$(document).off('click', '.addRow').on('click', '.addRow', function() {
    
    var addRowId = this.id;
    if (addRowId.match('addRow')) {
        $('.divClass').hide();
        dynamicRowsForBuildRule();
    }
    function dynamicRowsForBuildRule() {
        buildRuleTableCount++;
        var columnsData = "<tr id = 'cancel-" + buildRuleTableCount + "'><td><label class='control-label'>Rule :</label></td>" + 
        "<td><select id='ruleType-" + buildRuleTableCount + "'  class='form-control ruleTypeCheck' >" + 
        "<option value=''>--Please Select--</option>" + 
        "<option value='CONCAT'>Concat</option>" + 
        "<option value='REPLACE'>Replace</option>" +
        "<option value='REPLACE FIRST'>Replace First</option>" +
        "<option value='REPLACE LAST'>Replace Last</option>" +
        "<option value='REPLACE INDEX'>Replace Index</option>"+
        "<option value='DIVIDE BY'>Divide By</option>" +
        "<option value='MULTIPLY BY'>Multiply By</option>" +
        "<option value='PUT DECIMAL'>Put Decimal</option></select></td></tr>";
        $("#buildRuleTable").append(columnsData);
        var columnOptionsSelect = '';
        columnOptionsSelect += "<option value=''>--Please Select--</option>";
        for (var i = 0; i < options.length; i++) {
            columnOptionsSelect += '<option value="' + options[i].replace(' ' , '*') + '">' + options[i] + '</option>';
        }
        $('#commonColumn-' + buildRuleTableCount).empty();
        $('#commonColumn-' + buildRuleTableCount).append(columnOptionsSelect);
        $('#ruleColumn-' + buildRuleTableCount).empty();
        $('#ruleColumn-' + buildRuleTableCount).append(columnOptionsSelect);
        
        $("#showColumnsDialogForBuildRule").dialog({height: 'auto'});
        
    }
});

$(document).off('change', '.ruleTypeCheck').on('change', '.ruleTypeCheck', function() {
    var ruleTypeId = this.id;
    var ruleType = ruleTypeId.split("-");
    var ruleCount = ruleType[1];
    var ruleTypeValue = this.value;
    var appendColumns = "";
    var trid = $(this).closest('tr').attr('id');
    trid = '#' + trid;
    var tableWithTr = '#buildRuleTable' + ' ' + trid;
    
    $("#where-" + ruleCount).closest("td").prev('td').remove();
    $("#where-" + ruleCount).closest("td").remove();
    $("#commonColumn-" + ruleCount).closest("td").prev('td').remove();
    $("#commonColumn-" + ruleCount).closest("td").remove();
    $("#ruleColumn-" + ruleCount).closest("td").prev('td').remove();
    $("#ruleColumn-" + ruleCount).closest("td").remove();
    $("#cancelButton-" + ruleCount).closest("td").prev('td').remove();
    $("#cancelButton-" + ruleCount).closest("td").remove();
    $("#toReplace-" + ruleCount).closest("td").prev('td').remove();
    $("#toReplace-" + ruleCount).closest("td").remove();
    $("#replaceBy-" + ruleCount).closest("td").prev('td').remove();
    $("#replaceBy-" + ruleCount).closest("td").remove();
    $("#concatColumn-" + ruleCount).closest("td").prev('td').remove();
    $("#concatColumn-" + ruleCount).closest("td").remove();
    $("#concatValue-" + ruleCount).closest("td").prev('td').remove();
    $("#concatValue-" + ruleCount).closest("td").remove();
    $("#divideByValue-" + ruleCount).closest("td").prev('td').remove();
    $("#divideByValue-" + ruleCount).closest("td").remove();
    $("#multiplyByValue-" + ruleCount).closest("td").prev('td').remove();
    $("#multiplyByValue-" + ruleCount).closest("td").remove();
    $("#digitsAfterDecimal-" + ruleCount).closest("td").prev('td').remove();
    $("#digitsAfterDecimal-" + ruleCount).closest("td").remove();
    $("#replaceType-" + ruleCount).closest("td").prev('td').remove();
    $("#replaceType-" + ruleCount).closest("td").remove();
    $("#separationOperator-" + ruleCount).closest("td").prev('td').remove();
    $("#separationOperator-" + ruleCount).closest("td").remove();
    $("#conditionType-" + ruleCount).closest("td").prev('td').remove();
        $("#conditionType-" + ruleCount).closest("td").remove();
        $("#excel").closest("td").prev('td').remove();
        $("#excel").closest("td").remove();
        $("#fixNoOfDigits-" + ruleCount).closest("td").prev('td').remove();
        $("#fixNoOfDigits-" + ruleCount).closest("td").remove();
        $("#opertorType-" + ruleCount).closest("td").prev('td').remove();
        $("#opertorType-" + ruleCount).closest("td").remove();    
    
    if (ruleTypeValue == "REPLACE" && $("#toReplace-" + ruleCount).length == 0) {
        
        appendColumns = "<td><label class='control-label'>Type : </label></td>" +
        	"<td><select id='replaceType-" + ruleCount + "' class='form-control Column' >" +
			"<option value=''>select</option>" +
			"<option value='After Pattern'>After Pattern</option>" +
			"<option value='Before Pattern'>Before Pattern</option>" +
			"<option value='Pattern Onwards'>Pattern Onwards</option>" +
			"<option value='Upto Pattern'>Upto Pattern</option></select></td>" +
        "<td><label class='control-label'>Pattern : </label></td>" + 
        "<td><input id = 'toReplace-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>Replace : </label></td>" + 
        "<td><input id = 'replaceBy-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
    
    } else if (ruleTypeValue == "CONCAT" && $("#commonColumn-" + ruleCount).length == 0) {
        
        appendColumns = "<td><label class='control-label'>Concat Column :</label></td>" + 
        "<td><select id='concatColumn-" + ruleCount + "' class='form-control DynamicColumn'><option value = ''>select</option>"+options+"</select></td>" + 
        "<td><label class='control-label'>Concat Value :</label></td>" + 
        "<td><input type='text' id='concatValue-" + ruleCount + "' class='form-control' ></td>" +
        "<td><label class='control-label'>Separated By :</label></td>" + 
        "<td><input type='text' id='separationOperator-" + ruleCount + "' class='form-control' ></td>" +
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
            $('#concatColumn-' + ruleCount).html("--Please Select--");
            $('#concatColumn-' + ruleCount).html(ruleColumns);
    } 
    else if (ruleTypeValue == "REPLACE FIRST" && $("#replace-" + ruleCount).length == 0){
    	
    	appendColumns = "<td><label class='control-label'>Pattern : </label></td>" + 
        "<td><input id = 'toReplace-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>Replace : </label></td>" + 
        "<td><input id = 'replaceBy-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
    }
    else if (ruleTypeValue == "REPLACE LAST" && $("#commonColumn-" + ruleCount).length == 0){
    	
    	appendColumns = "<td><label class='control-label'>Pattern : </label></td>" + 
        "<td><input id = 'toReplace-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>Replace : </label></td>" + 
        "<td><input id = 'replaceBy-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
    }
    else if (ruleTypeValue == "REPLACE INDEX" && $("#commonColumn-" + ruleCount).length == 0){
    	
    	appendColumns = "<td><label class='control-label'>Index : </label></td>" + 
        "<td><input id = 'fromIndex-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>Length : </label></td>" + 
        "<td><input id = 'replaceLength-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>Replace : </label></td>" + 
        "<td><input id = 'replacePattern-" + ruleCount + "' type='text' class='form-control '></td>" +
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
    }
    else if (ruleTypeValue == "DIVIDE BY" && $("#replace-" + ruleCount).length == 0){
    	
    	appendColumns = "<td><label class='control-label'>Value : </label></td>" + 
        "<td><input id = 'divideByValue-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
    }
    else if (ruleTypeValue == "MULTIPLY BY" && $("#replace-" + ruleCount).length == 0){
    	
    	appendColumns = "<td><label class='control-label'>Value : </label></td>" + 
        "<td><input id = 'multiplyByValue-" + ruleCount + "' type='text' class='form-control '></td>" + 
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
    }
	else if (ruleTypeValue == "PUT DECIMAL" && $("#replace-" + ruleCount).length == 0){
		
		appendColumns = "<td><label class='control-label'>No of Digits : </label></td>" + 
	    "<td><input id = 'digitsAfterDecimal-" + ruleCount + "' type='text' class='form-control '></td>" + 
	    "<td><label class='control-label'>When Condition:</label></td>" + 
	    "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
	    "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
	    
	    $(tableWithTr).append(appendColumns);
	}
	else if (ruleTypeValue == "UPLOAD EXCEL" && $("#replace-" + ruleCount).length == 0){
		appendColumns = "<td><label class='control-label'>Upload Excel : </label></td>" + 
	    "<td><form action='' id='excel' enctype='multipart/form-data'><input id = 'ruleDataExcel' name = 'ruleDataExcel' type='file' class='form-control ' /></form></td>" + 
	    "<td><label class='control-label'>Condition Type:</label></td>" + 
	    "<td><select id='conditionType-" + ruleCount + "' class='form-control DynamicColumn'>" +
  "<option value = ''>--Please Select--</option>" + 
	    		"<option value = 'Equals'>Equals</option>" +
	    		"<option value = 'Starts With'>Starts With</option>" +
	    		"<option value = 'Ends With'>Ends With</option>" +
	    		"<option value = 'Contains'>Contains</option></select></td>" + 
	    "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></div></td>";
		//<form action='' id='excel' enctype='multipart/form-data'>
		$(tableWithTr).append(appendColumns);
	}
	else{
        $(trid).remove();
    }
});

$(document).on('change', '#ruleDataExcel', function() {
   var data = new FormData($("#excel")[0]);
	//alert(data);
	$.ajax({
	     type: "POST",
	     url: 'showExcelData',
	     data: data,
	     enctype: 'multipart/form-data',
	     processData: false,
	     contentType: false,
	     cache: false, 
	     success : function(data) {
	    	$('#ruleInfo').html(data);
        }
	});
	
});


$(document).off('click', '.cancel').on('click', '.cancel', function() {
    
    var cancelId = this.id;
    cancelId = cancelId.split("-");
    var cancelCount = cancelId[1];
    $('#cancel-' + cancelCount).remove();
    $(".divClass").hide();
});


$(document).off('click', '.getRule').on('click', '.getRule', function() {
	ruleQuery = '';
	var queryString = '';
    var colString = '';
    var rule = "";
    var baseQuery = localStorage.getItem("baseQuery");
    //alert(baseQuery);
    $("#baseQuery").val(baseQuery);
    var tablesNamesSeleted = localStorage.getItem("buildRuleSelectedTables");
  /*  alert(tablesNamesSeleted);*/
    if(tablesNamesSeleted.indexOf(",")){
    	tablesNamesSeleted = tablesNamesSeleted.split(",")
    }
    /*alert(tablesNamesSeleted);*/
    
 console.log("bulidRule--------");
 var commonColumn = $("#commonColumn").val();
var fieldColumn = $("#ruleColumn").val();
var tableAlias = "";
var fieldColumnAlias = "";
var fieldColumnName = "";
if(commonColumn != ""){
	 commonColumn = commonColumn.split('-');
	 for(var i=0;i<6;i++){
		 commonColumn[0] = commonColumn[0].replace('*',' '); 
	 }
	 if(tablesNamesSeleted.length > 1){
		 commonColumn = commonColumn[1] + '.' + "["+commonColumn[0]+"]";
	 }
	 else{
		 if(commonColumn[0].indexOf('+') > 0){
			 commonColumn = "["+commonColumn[0]+"]";
		 }
		 else{
                    commonColumn = "[" + commonColumn[0] + "]";
		 }
	 }
}
if(fieldColumn != ""){
	 fieldColumn = fieldColumn.split('-');
    tableAlias = fieldColumn[1];
    fieldColumnAlias = fieldAliasName;
	 for(var i=0;i<6;i++){
    fieldColumn[0] = fieldColumn[0].replace('*',' ');
	 }
    fieldColumnName = "["+fieldColumn[0]+"]";
    if(tablesNamesSeleted.length > 1){
    	fieldColumn = fieldColumn[1] + '.' +"["+fieldColumn[0]+"]";
    }
    else{
    	 if(fieldColumn[0].indexOf('+') > 0){
    		 fieldColumn = fieldColumn[0];
		 }
		 else{
			 fieldColumn = "["+fieldColumn[0]+"]";
		 }
    }
}
var queryCondition;
var baseQuery = $("#baseQuery").val();
var baseQueryArr = baseQuery.split("FROM");
 var operationRowsCount = $("#buildRuleTable tr").length;
 var operationTypeArr = [];
 var operationArr = [];
 var rulePattern = "";
 var conditionRulePattern = "";
 rule = "";
 if(operationRowsCount == 1 && $("#ruleType-0").val() == ""){
	 rulePattern = "RTRIM(LTRIM("+fieldColumn+"))";
            /*rule = "Load " + fieldColumn + " as is";*/
 }
 for(i=0; i<operationRowsCount; i++){
	var whereCondition = $("#where-"+i).val();
	if($("#ruleType-"+i).val() == "REPLACE"){
		var toReplacePattern = $("#toReplace-"+i).val();
       var replaceByPattern = $("#replaceBy-"+i).val();
       var replaceType = $("#replaceType-"+i).val();
       if(toReplacePattern == ""){
       	alert("Fill the values properly into the fields.");
       	return false;
       }
       if(replaceType == "After Pattern"){
    	   if(conditionRulePattern == ""){
          		conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN('"+toReplacePattern+"'), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
              }else{
              	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN('"+toReplacePattern+"'), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
              }
       }else if (replaceType == "Before Pattern"){
    	   if(conditionRulePattern == ""){
          		conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))-1, '"+replaceByPattern+"') ";
              }else{
              	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))-1, '"+replaceByPattern+"') ";
              }
       }else if (replaceType == "Pattern Onwards"){
    	   if(conditionRulePattern == ""){
          		conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+"))), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
              }else{
              	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+"))), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
              }
       }else if (replaceType == "Upto Pattern"){
    	   if(conditionRulePattern == ""){
          		conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN(RTRIM(LTRIM('"+toReplacePattern+"')))-1, '"+replaceByPattern+"') ";
              }else{
              	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '%"+toReplacePattern+"%' THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN(RTRIM(LTRIM('"+toReplacePattern+"')))-1, '"+replaceByPattern+"') ";
              }
       }else{
           if(whereCondition == ""){
               if(rulePattern == ""){
                   rulePattern = "REPLACE(RTRIM(LTRIM("+fieldColumn+")), '"+toReplacePattern+"', '"+replaceByPattern+"')";
               }else{
                   rulePattern = "REPLACE("+rulePattern+", '"+toReplacePattern+"', '"+replaceByPattern+"')";
               }
           }else {
           	if(conditionRulePattern == ""){
           		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN REPLACE(RTRIM(LTRIM("+fieldColumnAlias+")), '"+toReplacePattern+"', '"+replaceByPattern+"') ";
               }else{
               	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN REPLACE(RTRIM(LTRIM("+fieldColumnAlias+")), '"+toReplacePattern+"', '"+replaceByPattern+"') ";
               }
			}
       }
       
       /*if(rule == ""){
       	rule = "Replace '"+toReplacePattern+"' Pattern with '"+replaceByPattern+"' Pattern in Selected "+fieldColumnName+" Column Values, ";
       }else{
       	rule = rule+" then Replace '"+toReplacePattern+"' Pattern with '"+replaceByPattern+"' Pattern in the Result Values, "
       }*/
   }
	else if($("#ruleType-"+i).val() == "CONCAT"){
		var concatColumn = $("#concatColumn-"+i).val();
		var concatColumnValue = "";
		var concatColumnName = "";
                if (concatColumn != "" && concatColumn != null ) {
			
	 		concatColumn = concatColumn.split('-');
	 		concatColumnValue = concatColumn[0];
	 		concatColumn[0] = concatColumn[0].replace("*"," ");
                    if (tablesNamesSeleted.length > 1) {
                        /*concatColumnName = "["+concatColumn[0]+"]";*/
                        concatColumn = concatColumn[1] + '.' + "[" + concatColumn[0] + "]";
                    } 
                    else {
                        /*concatColumnName = "["+concatColumn[0]+"]";*/
                        concatColumn = "[" + concatColumn[0] + "]";
                    }
                
                }
                var concatValue = $("#concatValue-" + i).val();
                var separationOperator = $("#separationOperator-" + i).val();
                if (concatValue == "" && concatColumn != "") {
                    if (whereCondition == "") {
                        if (rulePattern == "") {
                            rulePattern = "CONCAT(RTRIM(LTRIM(" + fieldColumn + ")), '" + separationOperator + "', RTRIM(LTRIM(" + concatColumn + ")))";
                        } else {
                            rulePattern = "CONCAT(" + rulePattern + ", '" + separationOperator + "', " + concatColumn + ")";
                        }
                    } else {
                        if (conditionRulePattern == "") {
                            conditionRulePattern = conditionRulePattern + "CASE WHEN " + whereCondition + " THEN CONCAT(RTRIM(LTRIM(" + fieldColumnAlias + ")), '" + separationOperator + "', RTRIM(LTRIM(" + concatColumn + "))) ";
                        } else {
                            conditionRulePattern = conditionRulePattern + " WHEN " + whereCondition + " THEN CONCAT(RTRIM(LTRIM(" + fieldColumnAlias + ")), '" + separationOperator + "', RTRIM(LTRIM(" + concatColumn + "))) ";
                        }
                    }
                    
                    /*if(rule == ""){
           	rule = " Concat '"+fieldColumnName+"' Column & '"+concatColumnName+"' Column with "+separationOperator+", ";
           }else{
           	rule = rule+" then Concat the Result Values with '"+concatColumnName+"' Column with "+separationOperator+", ";
           }*/
                } 
                else if (concatValue != "" && (concatColumn == "" || concatColumn == null )) {
                    if (whereCondition == "") {
                        if (rulePattern == "") {
                            rulePattern = "CONCAT(RTRIM(LTRIM(" + fieldColumn + ")), '" + separationOperator + "', '" + concatValue + "')";
                        } else {
                            rulePattern = "CONCAT(" + rulePattern + ", '" + separationOperator + "', '" + concatValue + "')";
                        }
                    } else {
                        if (conditionRulePattern == "") {
                            conditionRulePattern = conditionRulePattern + "CASE WHEN " + whereCondition + " THEN CONCAT(RTRIM(LTRIM(" + fieldColumnAlias + ")), '" + separationOperator + "', '" + concatValue + "') ";
                        } else {
                            conditionRulePattern = conditionRulePattern + " WHEN " + whereCondition + " THEN CONCAT(RTRIM(LTRIM(" + fieldColumnAlias + ")), '" + separationOperator + "', '" + concatValue + "') ";
                        }
                    }
                    
                    /*if(rule == ""){
           	rule = " Concatenate '"+fieldColumnName+"' & '"+concatValue+"' with "+separationOperator+", ";
           }else{
           	rule = rule+" then Concatenate the Result Value & '"+concatValue+"' with "+separationOperator+", ";
           }*/
       }
       else if((concatValue == "" && concatColumn == "") || (concatValue != "" && concatColumn != "")){
       	alert("Wrong Inputs");
       	return false;
       }
   }
	else if($("#ruleType-"+i).val() == "REPLACE LAST"){
		var toReplaceLastPattern = $("#toReplace-"+i).val();
       var replaceByLastPattern = $("#replaceBy-"+i).val();
   //    toReplaceLastPattern.length 
       if(conditionRulePattern == ""){
       	conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '%"+toReplaceLastPattern+"' THEN STUFF("+fieldColumnAlias+",LEN("+fieldColumnAlias+")-LEN('"+toReplaceLastPattern+"')+1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
       }else{
       	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '%"+toReplaceLastPattern+"' THEN STUFF("+fieldColumnAlias+",LEN("+fieldColumnAlias+")-LEN('"+toReplaceLastPattern+"')+1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
       }
       
       /*if(rule == ""){
       	rule = "Replace the last '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in Selected "+fieldColumnName+" Column Values, ";
       }else{
       	rule = rule+" then Replace the last '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in the Result Values, ";
       }*/
	}
	else if($("#ruleType-"+i).val() == "REPLACE FIRST"){
		var toReplaceLastPattern = $("#toReplace-"+i).val();
       var replaceByLastPattern = $("#replaceBy-"+i).val();
    //   toReplaceLastPattern.length 
       if(conditionRulePattern == ""){
       	conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '"+toReplaceLastPattern+"%' THEN STUFF("+fieldColumnAlias+",1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
       }else{
       	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '"+toReplaceLastPattern+"%' THEN STUFF("+fieldColumnAlias+",1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
       }
       
       /*if(rule == ""){
       	rule = "Replace the initial '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in Selected "+fieldColumnName+" Column Values, ";
       }else{
       	rule = rule+" then Replace the initial '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in the Result Values, "
       }*/
	}
	else if($("#ruleType-"+i).val() == "REPLACE INDEX"){
		//var toReplaceLastPattern = $("#toReplace-"+i).val();
       //var replaceByLastPattern = $("#replaceBy-"+i).val();
       //toReplaceLastPattern.length
		var fromIndex = $("#fromIndex-"+i).val();
		var patternLengthToreplace = $("#replaceLength-"+i).val();
		var replacePattern = $("#replacePattern-"+i).val();
		if(whereCondition == ""){
            if(rulePattern == ""){
                rulePattern = "STUFF(RTRIM(LTRIM("+fieldColumn+")), "+fromIndex+", "+patternLengthToreplace+", '"+replacePattern+"') ";
            }else{
                rulePattern = "STUFF(RTRIM(LTRIM("+rulePattern+")), "+fromIndex+", "+patternLengthToreplace+", '"+replacePattern+"') ";
            }
        }else {
        	if(conditionRulePattern == ""){
        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), "+fromIndex+", "+patternLengthToreplace+", '"+replacePattern+"') ";
            }else{
            	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), "+fromIndex+", "+patternLengthToreplace+", '"+replacePattern+"') ";
            }
		}
       /*if(rule == ""){
       	rule = "Replace from "+fromIndex+"th index onwards with pattern '"+replacePattern+"' upto length "+patternLengthToreplace+" in Selected "+fieldColumnName+" Column Values, ";
       }else{
       	rule = rule+" then Replace from "+fromIndex+"th index onwards with pattern '"+replacePattern+"' upto length "+patternLengthToreplace+" in Selected "+fieldColumnName+" Column Values, "
       }*/
	}
	else if($("#ruleType-"+i).val() == "DIVIDE BY") {
		var divideByValue = $("#divideByValue-"+i).val();
		if(whereCondition == ""){
           if(rulePattern == ""){
               rulePattern = "CONVERT(Float,LTRIM(RTRIM("+fieldColumn+")))/"+divideByValue;
           }else{
               rulePattern = "CONVERT(Float,LTRIM(RTRIM("+rulePattern+")))/"+divideByValue;
           }
       }else {
       	if(conditionRulePattern == ""){
       		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN CONVERT(Float,LTRIM(RTRIM("+fieldColumnAlias+")))/"+divideByValue;
           }else{
           	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN CONVERT(Float,LTRIM(RTRIM("+fieldColumnAlias+")))/"+divideByValue;
           }
		}
		
		/*if(rule == ""){
       	rule = " Divide '"+fieldColumnName+"' Column by "+divideByValue+", ";
       }else{
       	rule = rule+" then Divide the Result Values by "+divideByValue+", ";
       }*/
	}
	else if($("#ruleType-"+i).val() == "MULTIPLY BY") {
		var multiplyByValue = $("#multiplyByValue-"+i).val();
		if(whereCondition == ""){
           if(rulePattern == ""){
               rulePattern = "CONVERT(Float,LTRIM(RTRIM("+fieldColumn+")))*"+multiplyByValue;
           }else{
               rulePattern = "CONVERT(Float,LTRIM(RTRIM("+rulePattern+")))*"+multiplyByValue;
           }
       }else {
       	if(conditionRulePattern == ""){
       		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN CONVERT(Float,LTRIM(RTRIM("+fieldColumnAlias+")))*"+multiplyByValue;
           }else{
           	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN CONVERT(Float,LTRIM(RTRIM("+fieldColumnAlias+")))*"+multiplyByValue;
           }
		}

		/*if(rule == ""){
       	rule = " Multiply '"+fieldColumnName+"' Column by "+multiplyByValue+", ";
       }else{
       	rule = rule+" then Multiply the Result Values by "+multiplyByValue+", ";
       }*/
	}
	else if($("#ruleType-"+i).val() == "PUT DECIMAL") {
		var digitsAfterDecimal = $("#digitsAfterDecimal-"+i).val();
		if(whereCondition == ""){
           if(rulePattern == ""){
               rulePattern = "CONVERT(Float,LTRIM(RTRIM("+fieldColumn+")))/POWER(10,"+digitsAfterDecimal+")";
           }else{
               rulePattern = "CONVERT(Float,LTRIM(RTRIM("+rulePattern+")))/POWER(10,"+digitsAfterDecimal+")";
           }
       }else {
       	if(conditionRulePattern == ""){
       		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN CONVERT(Float,LTRIM(RTRIM("+fieldColumnAlias+")))/POWER(10,"+digitsAfterDecimal+")";
           }else{
           	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN CONVERT(Float,LTRIM(RTRIM("+fieldColumnAlias+")))/POWER(10,"+digitsAfterDecimal+")";
           }
		}
	}
            else if ($("#ruleType-" + i).val() == "PUT LENGTH") {
                var noOfDigits = $("#fixNoOfDigits-" + i).val();
                var opertorType = $("#opertorType-" + i).val();
                if (whereCondition == "") {
                    if (rulePattern == "") {
                        rulePattern = "Len(" + fieldColumn + ")"+opertorType+"(" + noOfDigits + ")";
                        
                    } else {
                        rulePattern = "Len(" + fieldColumn + ")"+opertorType+"(" + noOfDigits + ")";
                    }
                } else {
                    if (conditionRulePattern == "") {
                        conditionRulePattern = conditionRulePattern + "CASE WHEN " + whereCondition + " THEN Len(" + fieldColumn + ")"+operator+"(" + noOfDigits + ")";
                    } else {
                        conditionRulePattern = conditionRulePattern + " WHEN " + whereCondition + " THEN Len(" + fieldColumn + ")"+operator+"(" + noOfDigits + ")";
                    }
                }
            } 
            
	else if($("#ruleType-"+i).val() == "UPLOAD EXCEL"){
		var conditionType = $("#conditionType-"+i).val();
                if ((conditionType == '')&&(fieldColumnAlias == "COUNTYLANDUSEDESCRIPTION" || fieldColumnAlias == "COUNTYLANDUSECODE" || fieldColumnAlias == "STANDARDIZEDLANDUSECODE")) {
                    alert("Please Select the Condition and Try again.");
                    return false;
                } 
                else {
                	
                	if(fieldColumnAlias == "COUNTYLANDUSEDESCRIPTION" || fieldColumnAlias == "COUNTYLANDUSECODE" || fieldColumnAlias == "STANDARDIZEDLANDUSECODE"){
			var conditionRulePattern71 = "";
			var conditionRulePattern72 = "";
			var conditionRulePattern73 = "";
			var tabRows = $('table#ruleInfoTab').find('tr');
			for (var i = 0; i < tabRows.length; i++) {
				var tdCols = $(tabRows[i]).find('td');
				var colDataArr = [];
				for(var j = 0; j < tdCols.length; j++){
					colDataArr[j] = $(tdCols[j]).html();
					//console.log(colDataArr[j]);
				}
				rowDataArr_71_72_73[i] = colDataArr;
			}
			var heraderColArr = rowDataArr_71_72_73[0];
			for(var i=1; i < rowDataArr_71_72_73.length; i++){
				var colDataArr = rowDataArr_71_72_73[i];
				var conditionWithColumnData = "";
				if(conditionType == "Equals"){
					conditionWithColumnData = "= '"+colDataArr[0]+"'";
				}else if(conditionType == "Starts With"){
					conditionWithColumnData = "LIKE '"+colDataArr[0]+"%'";
				}else if(conditionType == "Ends With"){
					conditionWithColumnData = "LIKE '%"+colDataArr[0]+"'";
				}else if(conditionType == "Contains"){
					conditionWithColumnData = "LIKE '%"+colDataArr[0]+"%'";
				}else{
					
				}
				if(conditionRulePattern71 == ""){
					conditionRulePattern71 = conditionRulePattern71+"CASE WHEN LTRIM(RTRIM(COUNTYLANDUSEDESCRIPTION)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern71 = conditionRulePattern71+" WHEN LTRIM(RTRIM(COUNTYLANDUSEDESCRIPTION)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
				
				if(conditionRulePattern72 == ""){
					conditionRulePattern72 = conditionRulePattern72+"CASE WHEN LTRIM(RTRIM(COUNTYLANDUSECODE)) "+conditionWithColumnData+" THEN '"+colDataArr[2]+"'";
				}else{
					conditionRulePattern72 = conditionRulePattern72+" WHEN LTRIM(RTRIM(COUNTYLANDUSECODE)) "+conditionWithColumnData+" THEN '"+colDataArr[2]+"'";
				}
				
				if(conditionRulePattern73 == ""){
					conditionRulePattern73 = conditionRulePattern73+"CASE WHEN LTRIM(RTRIM(STANDARDIZEDLANDUSECODE)) "+conditionWithColumnData+" THEN '"+colDataArr[3]+"'";
				}else{
					conditionRulePattern73 = conditionRulePattern73+" WHEN LTRIM(RTRIM(STANDARDIZEDLANDUSECODE)) "+conditionWithColumnData+" THEN '"+colDataArr[3]+"'";
				}
				
				
				if(fieldColumnAlias == "COUNTYLANDUSEDESCRIPTION"){
					if(conditionRulePattern == ""){
					    conditionRulePattern = conditionRulePattern+"CASE WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
					}else{
					    conditionRulePattern = conditionRulePattern+" WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
					}
				}else if(fieldColumnAlias == "COUNTYLANDUSECODE"){
					if(conditionRulePattern == ""){
					    conditionRulePattern = conditionRulePattern+"CASE WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[2]+"'";
					}else{
					    conditionRulePattern = conditionRulePattern+" WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[2]+"'";
					}
				}else if(fieldColumnAlias == "STANDARDIZEDLANDUSECODE"){
					if(conditionRulePattern == ""){
					    conditionRulePattern = conditionRulePattern+"CASE WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[3]+"'";
					}else{
					    conditionRulePattern = conditionRulePattern+" WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[3]+"'";
					}
				}else{
					
				}
				
			}
			
			ruleQuery = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE '' END AS "+fieldColumnAlias+" FROM " +
						"("+baseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
			
			buildRuleQuery = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE '' END AS "+fieldColumnAlias+" FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
			
			ruleQuery71 = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern71+" ELSE '' END AS COUNTYLANDUSEDESCRIPTION FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" COUNTYLANDUSEDESCRIPTION ")+") "+tableAlias;
			buildRuleQuery71 = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern71+" ELSE '' END AS COUNTYLANDUSEDESCRIPTION FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" COUNTYLANDUSEDESCRIPTION ")+") "+tableAlias;
			ruleQuery72 = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern72+" ELSE '' END AS COUNTYLANDUSECODE FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" COUNTYLANDUSECODE ")+") "+tableAlias;
			buildRuleQuery72 = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern72+" ELSE '' END AS COUNTYLANDUSECODE FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" COUNTYLANDUSECODE ")+") "+tableAlias;
			ruleQuery73 = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern73+" ELSE '' END AS STANDARDIZEDLANDUSECODE FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" STANDARDIZEDLANDUSECODE ")+") "+tableAlias;
			buildRuleQuery73 = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern73+" ELSE '' END AS STANDARDIZEDLANDUSECODE FROM " +
							"("+baseQuery.replace(fieldColumn, fieldColumn+" STANDARDIZEDLANDUSECODE ")+") "+tableAlias;
		}
		else if((fieldColumnAlias == "PROPSTREETDIRECTIONLEFT")||(fieldColumnAlias== "PROPSTREETDIRECTIONRIGHT")||(fieldColumnAlias== "PROPSTREETSUFFIX")||(fieldColumnAlias== "ASSEVESTINGIDCODE")||(fieldColumnAlias== "ASSEMAILZIPCODE")||(fieldColumnAlias== "BASEMENT")||(fieldColumnAlias== "FIREPLACE")||(fieldColumnAlias== "ELEVATOR")||(fieldColumnAlias== "OTHERROOMS")||(fieldColumnAlias== "GARAGETYPEPARKING")||(fieldColumnAlias== "POOL")){
			var conditionRulePattern08= "";
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
			var heraderColArr = rowDataArr_08[0];
			for(var i=1; i < rowDataArr_08.length; i++){
				var colDataArr = rowDataArr_08[i];
				var conditionWithColumnData = "";
				if(conditionType == "Equals"){
					conditionWithColumnData = "= '"+colDataArr[0]+"'";
				}else if(conditionType == "Starts With"){
					conditionWithColumnData = "LIKE '"+colDataArr[0]+"%'";
				}else if(conditionType == "Ends With"){
					conditionWithColumnData = "LIKE '%"+colDataArr[0]+"'";
				}else if(conditionType == "Contains"){
					conditionWithColumnData = "LIKE '%"+colDataArr[0]+"%'";
				}else{
					
				}
				if(conditionRulePattern08 == ""){
					conditionRulePattern08 = conditionRulePattern08+"CASE WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern08 = conditionRulePattern08+" WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
		}
			ruleQuery = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern08+" ELSE '' END AS "+fieldColumnAlias+" FROM " +
			"("+baseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
			
			buildRuleQuery = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern08+" ELSE '' END AS "+fieldColumnAlias+" FROM " +
			"("+baseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
			
		}
		else if(fieldColumnAlias=="EXTRAFEATURES1AREA"||fieldColumnAlias=="EXTRAFEATURES1INDICATOR"||fieldColumnAlias=="EXTRAFEATURES2AREA"||fieldColumnAlias=="EXTRAFEATURES2INDICATOR"||fieldColumnAlias=="EXTRAFEATURES3AREA"||fieldColumnAlias=="EXTRAFEATURES3INDICATOR"||fieldColumnAlias=="EXTRAFEATURES4AREA"||fieldColumnAlias=="EXTRAFEATURES4INDICATOR"||fieldColumnAlias=="OTHERIMPRBUILDINGAREA1"||fieldColumnAlias=="OTHERIMPRBUILDINGAREA2"||fieldColumnAlias=="OTHERIMPRBUILDINGAREA3"||fieldColumnAlias=="OTHERIMPRBUILDINGAREA4"||fieldColumnAlias=="OTHERIMPRBUILDINGAREA5"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR1"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR2"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR3"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR4"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR5"||fieldColumnAlias=="BUILDINGAREA1"||fieldColumnAlias=="BUILDINGAREA1INDICATOR"||fieldColumnAlias=="BUILDINGAREA2"||fieldColumnAlias=="BUILDINGAREA2INDICATOR"){
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
			/*alert(rowDataArr_08);*/
			ruleQuery="";
			buildRuleQuery = "";
		}
    }
/*		else if(fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR1"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR2"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR3"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR4"||fieldColumnAlias=="OTHERIMPRBUILDINGINDICATOR5"){
			var conditionRulePattern163= "";
			var conditionRulePattern164= "";
			var conditionRulePattern165= "";
			var conditionRulePattern166= "";
			var conditionRulePattern169= "";
			var tabRows = $('table#ruleInfoTab').find('tr');
			for (var i = 0; i < tabRows.length; i++) {
				var tdCols = $(tabRows[i]).find('td');
				var colDataArr = [];
				for(var j = 0; j < tdCols.length; j++){
					colDataArr[j] = $(tdCols[j]).html();
					//console.log(colDataArr[j]);
				}
				rowDataArr163_169[i] = colDataArr;
			}
			var heraderColArr = rowDataArr163_169[0];
			for(var i=1; i < rowDataArr163_169.length; i++){
				var colDataArr = rowDataArr163_169[i];
				var conditionWithColumnData = "";
				if(conditionType == "Equals"){
					conditionWithColumnData = "= '"+colDataArr[0]+"'";
				}else if(conditionType == "Starts With"){
					conditionWithColumnData = "LIKE '"+colDataArr[0]+"%'";
				}else if(conditionType == "Ends With"){
					conditionWithColumnData = "LIKE '%"+colDataArr[0]+"'";
				}else if(conditionType == "Contains"){
					conditionWithColumnData = "LIKE '%"+colDataArr[0]+"%'";
				}else{
					
				}
				if(conditionRulePattern163 == ""){
					conditionRulePattern163 = conditionRulePattern163+"CASE WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR1)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern163 = conditionRulePattern163+" WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR1)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
				
				if(conditionRulePattern164 == ""){
					conditionRulePattern164 = conditionRulePattern164+"CASE WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR2)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern164 = conditionRulePattern164+" WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR2)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
				
				if(conditionRulePattern165 == ""){
					conditionRulePattern165 = conditionRulePattern165+"CASE WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR3)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern165 = conditionRulePattern165+" WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR3)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
				if(conditionRulePattern166 == ""){
					conditionRulePattern166 = conditionRulePattern166+"CASE WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR4)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern166 = conditionRulePattern166+" WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR4)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
				if(conditionRulePattern169 == ""){
					conditionRulePattern169 = conditionRulePattern169+"CASE WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR5)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}else{
					conditionRulePattern169 = conditionRulePattern169+" WHEN LTRIM(RTRIM(OTHERIMPRBUILDINGINDICATOR5)) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
				}
				if(fieldColumnAlias == "OTHERIMPRBUILDINGINDICATOR1"||fieldColumnAlias == "OTHERIMPRBUILDINGINDICATOR2"||fieldColumnAlias == "OTHERIMPRBUILDINGINDICATOR3"||fieldColumnAlias == "OTHERIMPRBUILDINGINDICATOR4"||fieldColumnAlias == "OTHERIMPRBUILDINGINDICATOR5")
				{
					if(conditionRulePattern == ""){
					    conditionRulePattern = conditionRulePattern+"CASE WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
					}else{
					    conditionRulePattern = conditionRulePattern+" WHEN LTRIM(RTRIM("+fieldColumnAlias+")) "+conditionWithColumnData+" THEN '"+colDataArr[1]+"'";
					}
					
				}
				else {}
			}*/
	
	
	operationTypeArr[i] = $("#ruleType-"+i).val();
 }}
 var queryFirstPart = "";
 var buildQueryFirstPart = "";
if(ruleQuery == ""){
	 if(conditionRulePattern == "" && $.trim(rulePattern) != ""){
		queryFirstPart = "SELECT "+commonColumn+" APPRCL , "+rulePattern+" "+fieldColumnAlias+" ";
		buildQueryFirstPart = "SELECT id,"+commonColumn+" APPRCL , "+rulePattern+" "+fieldColumnAlias+" ";
		baseQuery=baseQuery;
		ruleQuery = baseQuery.replace(baseQueryArr[0],queryFirstPart);
		buildRuleQuery = baseQuery.replace(baseQueryArr[0],buildQueryFirstPart);
	 }else if(conditionRulePattern != "" && $.trim(rulePattern) != ""){
		ruleQuery = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
				"("+baseQuery.replace(baseQueryArr[0],"SELECT "+commonColumn+" , "+rulePattern+" "+fiel-dColumnAlias+" ")+") "+tableAlias;
		
		buildRuleQuery = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
		"("+baseQuery.replace(baseQueryArr[0],"SELECT id,"+commonColumn+" , "+rulePattern+" "+fiel-dColumnAlias+" ")+") "+tableAlias;
		
	 }else if(conditionRulePattern != "" && $.trim(rulePattern) == ""){
		/*ruleQuery = "SELECT "+commonColumn+" , "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
		"("+baseQuery.replace(baseQueryArr[0],"SELECT "+commonColumn+" , "+fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;*/
		ruleQuery = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
		"("+baseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
		
		buildRuleQuery = "SELECT id,"+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
		"("+buildBaseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
	 }
	 else{
		 
	 }
 }

//ruleQuery = baseQuery.replace(baseQueryArr[0],queryFirstPart);
console.log(ruleQuery);
console.log(buildRuleQuery);
$("#ModifiedRuleQuery").val(ruleQuery);
$("#ModifiedBuildRuleQuery").val(buildRuleQuery);
$("#ModifiedRuleQuery").attr('title',queryString);
$("#query").val(ruleQuery);
$("#buildRuleQuery").val(buildRuleQuery);
$("#query71").val(ruleQuery71);
$("#buildRuleQuery71").val(buildRuleQuery71);
$("#query72").val(ruleQuery72);
$("#buildRuleQuery72").val(buildRuleQuery72);
$("#query73").val(ruleQuery73);
$("#buildRuleQuery73").val(buildRuleQuery73);
/*$("#query163").val(ruleQuery163);
$("#query164").val(ruleQuery164);
$("#query165").val(ruleQuery165);
$("#query166").val(ruleQuery166);
$("#query169").val(ruleQuery169);*/
var specialChars = ['%','&','+','#'];
var codeNumbersForSplChars = ['%25','%26','%2B','%23'];
if(ruleQuery!=''){
for(var sp = 0 ; sp < specialChars.length;sp++){
	if(ruleQuery.indexOf(specialChars[sp]) >0){
    		ruleQuery = ruleQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
    }
}
}
 if (ruleQuery != "") {
	/*alert(ruleQuery);*/
    $http.get('./executeQuery?input_query='+ruleQuery).success(function(response) {
        for (i = 0; i < response.length; i++) {
            var dataMap = response[i];
            colCount = 0;
            for (var key in dataMap) {
                colArr[colCount] = {
                    name: key,
                    displayName: key,
                    width: '20%'
                }
                colCount = colCount + 1;
            }

        }
        $scope.gridOptions = {
                    columnDefs: colArr,
                    enableSorting: false,
                    headerHeight: 20,
                    enableFiltering: true
        };
        $scope.gridOptions.data = response;
        $scope.gridOptions.enableColumnResizing = true;
        var noOfRows = $scope.gridOptions.data.length;
        $("#NoOfRows").text("");
        $("#NoOfRows").text("No Of Rows : " + noOfRows);
    });
}
});
$(document).off('click', '.runRuleQuery').on('click', '.runRuleQuery', function() {
	if (confirm("Rule saved you want to close window!") == true) {
            var modifiedRule = $("#ModifiedRuleQuery").val();
            var ruleColumn = $("#ruleColumn").val().split("-");
            ruleColumn = ruleColumn[0];

		localStorage.setItem('resultRuleQuery', modifiedRule);
		localStorage.setItem('resultRuleQuery71', ruleQuery71);
		localStorage.setItem('resultRuleQuery72', ruleQuery72);
		localStorage.setItem('resultRuleQuery73', ruleQuery73);
		

		localStorage.setItem('rowDataArr_ForIndividual', JSON.stringify(rowDataArr_08));
		localStorage.setItem('rowDataArr_71_72_73', JSON.stringify(rowDataArr_71_72_73));
		
		localStorage.setItem('ruleColumn', ruleColumn);
		localStorage.setItem('buildRuleQuery', buildRuleQuery);
		localStorage.setItem('buildRuleQuery71', buildRuleQuery71);
		localStorage.setItem('buildRuleQuery72', buildRuleQuery72);
		localStorage.setItem('buildRuleQuery73', buildRuleQuery73);
		
		/*alert(ruleColumn +" "+buildRuleQuery +" "+buildRuleQuery71+" "+buildRuleQuery72+" "+buildRuleQuery73);*/
		
        window.close();
    } else {
       
    }
});
$(document).off('click', '.iframeForModal').on('click', '.iframeForModal', function(e) {
    var tableColumn = this.id;
    var tableColumnCount = tableColumn.split("-");
    tableColumnCount = tableColumnCount[1];
    var tableColumnData = "";
    var position = ($(this).closest('td').parent()[0].sectionRowIndex)+1; 
    
    var uiDialogHeight = 350;
    var leftX = 125;
    var topY = 60;
    var lastIndex = $('#buildRuleTable tr:last').index();
    
    /*topY = ((topY)+(20*(position-1)+(15*(position-1))));
    uiDialogHeight = uiDialogHeight+((33)*(lastIndex+1));*/
    $('.divClass').css({left:leftX+"px",top:topY+"px",position: 'absolute',backgroundColor : 'burlywood'});
    $('.divClass').show();
    /*$(".ui-dialog").css({height:uiDialogHeight+'px'});*/
        
        tableColumnData = "<tr><th><label class='control-label'></label></th><th></th><th></th><th></th><th></th>" + 
        "<th></th><th></th><th></th><th></th><th></th><th></th><th></th>" + 
        "<th><label class='control-label'></label></th></tr>" +
        "<tr><td><label class='control-label'>Operation :</label></td>" + 
        "<td><select id='functionColoumn-" + tableColumnCount + "' class='form-control buildCountyWhereClass'>" + 
        "<option value=''>--Please Select--</option><option value='INDEX_REPLACE'>Index Replace</option></select></td>" + 
        "<td id='1td-"+tableColumnCount+"'></td>" + 
        "<td id='2td-"+tableColumnCount+"'></td>" + 
        "<td id='3td-"+tableColumnCount+"'></td>" + 
        "<td id='4td-"+tableColumnCount+"'></td>" + 
        "<td id='5td-"+tableColumnCount+"'></td>" + 
        "<td id='6td-"+tableColumnCount+"'></td>" + 
        "<td id='7td-"+tableColumnCount+"'></td>" + 
        "<td id='8td-"+tableColumnCount+"'></td></tr>"+
        "<tr><td></td></tr>"+
        "<tr><td><label class='control-label'>columns :</label></td>" + 
        "<td><select id='buildColoumn-" + tableColumnCount + "' class='form-control buildCountyWhereClass'>" + 
        "<option value=''>--Please Select--</option></select></td>" + 
        "<td><label class='control-label'>Operators :</label></td>" + 
        "<td><select id='buildOperators-" + tableColumnCount + "'  class='form-control buildCountyWhereClass '>" + 
        "<option value=''>--Please Select--</option><option value='='>=</option>" + 
        "<option value='<>'><></option><option value='like'>like</option>" + 
        "<option value='not like'>not like</option><option value='<'><</option>" + 
        "<option value='>'>></option><option value='is null'>is null</option>" + 
        "<option value='is not null'>is not null</option><option value='in'>in</option>" + 
        "<option value='between'>between</option></select></td>" + 
        "<td><label class='control-label'>Value :</label></td>" + 
        "<td><select id='buildValueSelect-" + tableColumnCount + "' class='form-control buildCountyWhereClass '>" + 
        "<option value=''>--Please Select--</option></select></td>" + 
        "<td><label class='control-label'>  </label></td>" + 
        "<td><input type='text' id='buildValueText-" + tableColumnCount + "' class='form-control buildCountyWhereClass'></td>" + 
        "<td><label class='control-label'>  </label></td>" + 
        "<td><button class='btn btn-warning applyButton' id = 'apply-" + tableColumnCount + "'>apply</button>" +
        		"<input type='hidden' id='fieldAliasName' value='"+fieldAliasName+"'></td></tr>";
        
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").empty();
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").append(tableColumnData);
        
        var columnOptionsToAppend = "<option value=''>--Please Select--</option>";
       
       var optionValues = $("#optionValues").val().split(',');
       var optionsToShow= $("#optionsToShow").val().split(',');
    	for (var i = 0; i < optionValues.length; i++) {
        	columnOptionsToAppend += '<option value="' + optionValues[i] + '">' + optionsToShow[i] + '</option>';
        }    	
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildColoumn-" + tableColumnCount).empty();
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildColoumn-" + tableColumnCount).html(columnOptionsToAppend);
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildValueSelect-" + tableColumnCount).empty();
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildValueSelect-" + tableColumnCount).html(columnOptionsToAppend);
        $('#submenuPagesDisplay').contents().find('body').find("#alertCheck").empty();
        
});



var flagToCheckIsnull = false;
$(document).off('change', '.buildCountyWhereClass').on('change', '.buildCountyWhereClass', function() {
    
    var whereConditionId = this.id;
    var whereConditionCount = whereConditionId.split('-');
    whereConditionCount = whereConditionCount[1];
    var resultValue = this.value;
    
    if (whereConditionId.match("buildOperators")) {
        if (resultValue == "like" || resultValue == "not like") {
            $("#alertCheckEmptyField").hide();
            $("#alertCheckEmptyField").css('display','none');
            $("#alertCheck").empty();
            $("#alertCheck").show();
            $("#alertCheck").append("*Please add '%' in textfield as per your requirement");
            $("#alertCheck").css('display','block');
            $("#buildValueSelect-" + whereConditionCount).attr("disabled", true);
            $('#buildValueSelect-' + whereConditionCount).prop('selectedIndex', 0);
        } 
        else {
            $("#alertCheck").empty();
            $("#alertCheck").hide();
            $('#buildValueSelect-' + whereConditionCount).removeAttr("disabled");
        }
        if (resultValue == "is null" || resultValue == "is not null") {
            flagToCheckIsnull = true;
        } 
        else {
            flagToCheckIsnull = false;
        }
        if (flagToCheckIsnull == true) {
            $("#buildValueSelect-" + whereConditionCount).attr("disabled", true);
            $('#buildValueSelect-' + whereConditionCount).prop('selectedIndex', 0);
            $("#buildValueText-" + whereConditionCount).attr("disabled", true);
            $("#buildValueText-" + whereConditionCount).val("");
        } 
        else if (flagToCheckIsnull == false) {
            if ($('#buildValueSelect-' + whereConditionCount).val() != "" && $("#buildValueText-" + whereConditionCount).val() != "") {
                $('#buildValueSelect-' + whereConditionCount).removeAttr("disabled");
                $("#buildValueText-" + whereConditionCount).removeAttr("disabled");
                $('#buildValueSelect-' + whereConditionCount).prop('selectedIndex', 0);
                $("#buildValueText-" + whereConditionCount).val("");
            } 
            else if ($('#buildValueSelect-' + whereConditionCount).val() == "" && $("#buildValueText-" + whereConditionCount).val() != "") {
                $("#buildValueText-" + whereConditionCount).removeAttr("disabled");
            } 
            else if ($('#buildValueSelect-' + whereConditionCount).val() != "" && $("#buildValueText-" + whereConditionCount).val() == "") {
                $("#buildValueSelect-" + whereConditionCount).removeAttr("disabled");
            } 
            
            else {
                if (resultValue == "like" || resultValue == "not like") {
                    $("#buildValueSelect-" + whereConditionCount).attr("disabled", true);
                    $('#buildValueSelect-' + whereConditionCount).prop('selectedIndex', 0);
                } 
                else {
                    $("#buildValueSelect-" + whereConditionCount).removeAttr("disabled");
                }
                $("#buildValueText-" + whereConditionCount).removeAttr("disabled");
            }
        }
    }
    if (whereConditionId == "buildValueSelect-" + whereConditionCount) {
        if (resultValue != "") {
            $('#buildValueText-' + whereConditionCount).attr("disabled", true);
            $('#buildValueText-' + whereConditionCount).val("");
        } 
        else {
            $('#buildValueText-' + whereConditionCount).removeAttr("disabled");
        }
    }
    if (whereConditionId == "buildValueText-" + whereConditionCount) {
        if (resultValue != "") {
            $('#buildValueSelect-' + whereConditionCount).attr("disabled", true);
            $('#buildValueSelect-' + whereConditionCount).prop('selectedIndex', 0);
        } 
        else {
            $('#buildValueSelect-' + whereConditionCount).removeAttr("disabled");
        }
    }
    if(whereConditionId == "functionColoumn-"+whereConditionCount){
    	/*alert(whereConditionId);*/
    	var operation = $('#functionColoumn-'+whereConditionCount).val();
    	/*alert(operation);*/
    	if(operation == "INDEX_REPLACE"){
	    	$('#1td-'+whereConditionCount).html("<label class='control-label'>Index :</label>");
	    	$('#2td-'+whereConditionCount).html("<input type='text' id='index-"+whereConditionCount+"' class='form-control buildCountyWhereClass'>");
	    	$('#3td-'+whereConditionCount).html("<label class='control-label'>Length :</label>");
	    	$('#4td-'+whereConditionCount).html("<input type='text' id='length-"+whereConditionCount+"' class='form-control buildCountyWhereClass'>");
	    	$('#5td-'+whereConditionCount).html("<label class='control-label'>Pattern :</label>");
	    	$('#6td-'+whereConditionCount).html("<input type='text' id='pattern-"+whereConditionCount+"' class='form-control buildCountyWhereClass'>");
    	}
    }
});

$(document).on('keyup', '.buildCountyWhereClass', function() {
    var seletedId = this.id;
    var selectedCount = seletedId.split('-');
    selectedCount = selectedCount[1];
    var givenValue = this.value;
    if (seletedId == 'buildValueText-' + selectedCount) {
        if (givenValue != "") {
            $('#buildValueSelect-' + selectedCount).attr("disabled", true);
        } 
        else {
            if ($('#buildOperators-' + selectedCount).val() == 'like' || $('#buildOperators-' + selectedCount).val() == 'not like') {
                $('#buildValueSelect-' + selectedCount).attr("disabled", true);
            } 
            else {
                $('#buildValueSelect-' + selectedCount).removeAttr("disabled");
            }
        }
    }
});
$(document).off('click', '.applyButton').on('click', '.applyButton', function() {
	fieldAliasName = $("#fieldAliasName").val();
	var applyButtonId = this.id;
    var applyButtonCount = applyButtonId.split("-");
    applyButtonCount = applyButtonCount[1];
    var alertFieldEmptyCheck = true;
    
    var buildValueSelect = $('#buildValueSelect-' + applyButtonCount).val();
    var buildSelectValue = "";
    var buildColoumn = $('#buildColoumn-' + applyButtonCount).val();
    var buildColoumnValue = "";
    var buildValueText = $('#buildValueText-' + applyButtonCount).val();
    var buildTextValue = "";
    var buildOperator = $('#buildOperators-' + applyButtonCount).val();
    var operation = $('#functionColoumn-'+applyButtonCount).val();
    if (buildColoumn != "" && buildOperator != "" && (buildValueSelect != "" || buildValueText != "")) {
        alertFieldEmptyCheck = false;
    } 
    else if (buildOperator == "is null" || buildOperator == "is not null") {
        if (buildColoumn != "") {
            alertFieldEmptyCheck = false;
        } 
        else {
            alertFieldEmptyCheck = true;
        }
    }
    if (alertFieldEmptyCheck == true) {
        $("#alertCheck").hide();
        $("#alertCheck").css('display','none');
        $("#alertCheckEmptyField").empty();
        $("#alertCheckEmptyField").show();
        $("#alertCheckEmptyField").append("*Please Enter the all Fields.");
        $("#alertCheckEmptyField").css('display','block');
    } 
    else {
        $("#alertCheckEmptyField").empty();
        $("#alertCheckEmptyField").hide();
        if(operation == "INDEX_REPLACE"){
	        var index = $('#index-' + applyButtonCount).val();
	        var length = $('#length-' + applyButtonCount).val();
	        var pattern = $('#pattern-' + applyButtonCount).val();
	        fieldAliasName = "STUFF("+fieldAliasName+", "+index+", "+length+", '"+pattern+"')";
	    }else{
	    	
        }
        
        if (buildValueSelect != "" && (buildOperator != 'is null' && buildOperator != 'is not null')) {
            
            buildSelectValue = buildValueSelect.split('-');
            buildValueSelect = buildSelectValue[1] + '.' + buildSelectValue[0];
            buildColoumnValue = buildColoumn.split('-');
            buildColoumn = buildColoumnValue[1] + "." + buildColoumnValue[0];
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + buildOperator + " " + buildValueSelect);
        }
        else if (buildOperator == 'is null' || buildOperator == 'is not null') {
            buildColoumnValue = buildColoumn.split('-');
            buildColoumn = buildColoumnValue[1] + "." + buildColoumnValue[0];
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + buildOperator);
        }else if (buildOperator == '<' || buildOperator == '>' || buildOperator == '<=' || buildOperator == '>=') {
            fieldAliasName = 'CONVERT(float, '+fieldAliasName+')';
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + buildOperator + "  "+ buildValueText);
        }else if (buildValueSelect == "") {
            buildColoumnValue = buildColoumn.split('-');
            buildColoumn = buildColoumnValue[1] + "." + buildColoumnValue[0];
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + buildOperator + " " + "'" + buildValueText + "'");
        } 
        
        
        }
    });
    
    
/*    $(document).on('focusin', '.DynamicColumn', function() {
        var colId = this.id;
        colId = colId.split('-');
        var colCount = colId[1];
        
          $('#concatColumn-'+colCount).empty();
	$('#concatColumn-'+colCount).html("");
	$('#concatColumn-'+colCount).html(ruleColumns);
});*/
$(document).off('click', '.cancelRightEnd').on('click', '.cancelRightEnd', function() {
	$('.divClass').hide();
});
}]);