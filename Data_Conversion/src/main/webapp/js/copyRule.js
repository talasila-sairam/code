var options = "";
var countryOptions = "";
var stateOptions = "";
var countyOptions = "";
var rule = "";
var baseQuery = "";
function copyTheRule(ruleQueryId){
	$("#ruleOperation").val("COPY");
	options = "<option value=''>Field</option>";
	countryOptions = "<option value=''>Country</option>";
	var model_dialog = $("#showDialogForCopyRule").dialog({
	close: function(event, ui) {
            $('#overlay').hide();
        },
        autoOpen: false,
        title: "Copy Rule",
        height: 400,
        width: 1300,
        modal:true,
        show: {
            effect: 'fade',
            duration: 1000
        },
        resizable: true,
        buttons: {
             Ok: function() {
            	 	var alertFlag = true;
            	 	var countryFipsCode = $("#countryForCopyRul").val();
            	 	var stateFipsCode = $("#stateForCopyRul").val();
            	 	var countyFipsCode = $("#countyForCopyRul").val();
            	 	var countryName = $("#countryForCopyRul option:selected").html();
            	 	var stateName = $("#stateForCopyRul option:selected").html();
            	 	var countyName = $("#countyForCopyRul option:selected").html();
            	 	var fieldNumber = $("#field").val();
            	 	var ruleQuery = $("#ruleQueryCopy").val();
            	 	var ruleName = $("#ruleName").val();
            	 	var query = $("#queryCopy").val();
            	 	var baseQuery = $("#baseQueryCopy").val();
            	 	var tableString = $("#tableString").val();
            	 	var colString = $("#columnString").val();
            	 	var editonNumber = $("#editionForCopyRul").val();
            	 	if(fieldNumber == "" || fieldNumber == "undefined"){
            	 		alert("Please select field.");
            	 		return false;
            	 	}else if(countryFipsCode == "" || countryFipsCode == "undefined"){
            	 		alert("Please select country.");
            	 		return false;
            	 	}else if(stateFipsCode == "" || stateFipsCode == "undefined"){
            	 		alert("Please select state.");
            	 		return false;
            	 	}else if(countyFipsCode == "" || countyFipsCode == "undefined"){
            	 		alert("Please select county.");
            	 		return false;
            	 	}else if(editonNumber == "" || editonNumber == "undefined"){
            	 		alert("Please select edition year.");
            	 		return false;
            	 	}else{
            	 		
            	 	}
            	 	var jsonData = {"edition": editonNumber,
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
        					"baseQuery":baseQuery,
        					"tables":tableString,
        					"columns":colString
        					}
            	 	console.log(jsonData);
            	 	$.ajax({
            	        type: "POST",
            	        url: 'saveRule',
            	        dataType: 'json',
            	        data: JSON.stringify(jsonData),
            	        contentType: "application/json",
            	        success: function (data) {
            	        	console.log(data);
            	        	if(data.status == "EXISTS"){
            	        		alert("Rule Name already exists.");
            	        	}else if (data.status == "SUCCESS") {
            	        		alertFlag = false;
            	        		alert("Rule successfully saved.");
            	        		model_dialog.dialog("close");
                                $('#overlay').hide();
            				}else {
            					alert("Processing Error.Please try after some time");
            				}
            	        }
            		});
            	 	/*if(alertFlag == true){
                            alert("Please don't leave any one field as empty");
                    }
                    else{
                    	model_dialog.dialog("close");
                                //buildRuleTableCount = 0;
                                $('#overlay').hide();
                    }*/
                 }
              }
        
         });
    model_dialog.load("copyRule.jsp").dialog("open");
	var height = $(document).height();
	$('#overlay').attr('style','height:'+height+'px');
	$('#overlay').show();
	if ($("#showDialogForCopyRule").dialog('isOpen') === true) {
		$.ajax({
	    	type: "POST",
		    url: 'getFieldsWithCountries',
		    async: false, 
		    data: "ruleQueryId="+ruleQueryId,
		     success : function(data) {
		    	 //alert(JSON.stringify(data));
		    	 $.each(data.fieldList, function( index, value ) {
		    		 options += "<option value='"+value.fieldNumber+"'>"+value.fieldName+"</option>";
		    	 });
		    	 
		    	 $.each(data.countryList, function( index, value ) {
			    	 countryOptions += "<option value='"+value.countryCode+"'>"+value.countryName+"</option>";
			    });
		    	 
		    	$("#queryCopy").val(data.rule.query); 
		    	$("#tableString").val(data.rule.tables);  
		    	$("#columnString").val(data.rule.columns);  
		    	$("#editonCopy").val(data.rule.ruleMaster.edition);
		    	
		    	baseQuery = data.rule.baseQuery;
		    	rule = data.rule.rule;
		    	
		    },
		    error : function(data){
		    	
		    }
	    });
    } else {
        alert("Popup has been closed.");
    }
}

$(document).on('focusin', "#field", function() {
	$('#field').empty();
    $('#field').html("");
    $('#field').append("");
    
    $('#field').append(options);
    $("#ruleQueryCopy").val(rule);
    $("#baseQueryCopy").val(baseQuery);
    
    /*options.*/
});

$(document).on('focusin', "#countryForCopyRul", function() {
	$('#countryForCopyRul').empty();
    $('#countryForCopyRul').html("");
    $('#countryForCopyRul').append("");
    
    $('#countryForCopyRul').append(countryOptions);
    /*options.*/
});

$(document).on('change', "#countryForCopyRul", function() {
	//alert(countryFipsCode);
	var countryFipsCode = $("#countryForCopyRul").val();
	stateOptions = "<option value=''>State</option>";
	var jsonData = {"countryFipsCode":countryFipsCode};
	console.log(jsonData);
	$("#stateForCopyRul").empty();
	$("#stateForCopyRul").html("");
	$.ajax({
    	type: "POST",
	    url: 'getStatesForCountry',
	    async: false,
	    dataType: 'json',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
	    
	    success : function(data) {
	    	 //alert(JSON.stringify(data));
	    	 $.each(data, function( index, value ) {
	    		 stateOptions += "<option value='"+value.fipsCode+"'>"+value.stateName+"</option>";
	    	 });
	    	 $("#stateForCopyRul").append(stateOptions);
	    	 
	    },
	    error : function(data){
	    	
	    }
    });
});

$(document).on('change', "#stateForCopyRul", function() {
	var countryFipsCode = $("#countryForCopyRul").val();
	var stateFipsCode = $("#stateForCopyRul").val();
	countyOptions = "<option value=''>County</option>";
	var jsonData = {"countryFipsCode":countryFipsCode,"stateFipsCode":stateFipsCode};
	console.log(jsonData);
	$.ajax({
    	type: "POST",
	    url: 'getCountiesForState',
	    async: false, 
	    dataType: 'json',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
	    
	     success : function(data) {
	    	 //alert(JSON.stringify(data));
	    	 $.each(data, function( index, value ) {
	    		 countyOptions += "<option value='"+value.countyFipsCode+"'>"+value.countyName+"</option>";
	    	 });
	    	 $("#countyForCopyRul").append(countyOptions);
	    	 
	    },
	    error : function(data){
	    	
	    }
    });
});

/*function getStatesForCountry(countryFipsCode){
	//alert(countryFipsCode);
	stateOptions = "<option value=''>State</option>";
	var jsonData = {"countryFipsCode":countryFipsCode};
	console.log(jsonData);
	$.ajax({
    	type: "POST",
	    url: 'getStatesForCountry',
	    async: false,
	    dataType: 'json',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
	    
	    success : function(data) {
	    	 alert(JSON.stringify(data));
	    	 $.each(data, function( index, value ) {
	    		 stateOptions += "<option value='"+value.fipsCode+"'>"+value.stateName+"</option>";
	    	 });
	    	 $("#stateForCopyRul").append(stateOptions);
	    	 
	    },
	    error : function(data){
	    	
	    }
    });
	
}
function getCountiesForState(stateFipsCode){
	var countryFipsCode = $("#countryForCopyRul").val();
	countyOptions = "<option value=''>County</option>";
	var jsonData = {"countryFipsCode":countryFipsCode,"stateFipsCode":stateFipsCode};
	console.log(jsonData);
	$.ajax({
    	type: "POST",
	    url: 'getCountiesForState',
	    async: false, 
	    dataType: 'json',
        data: JSON.stringify(jsonData),
        contentType: "application/json",
	    
	     success : function(data) {
	    	 alert(JSON.stringify(data));
	    	 $.each(data, function( index, value ) {
	    		 countyOptions += "<option value='"+value.countyFipsCode+"'>"+value.countyName+"</option>";
	    	 });
	    	 $("#countyForCopyRul").append(countyOptions);
	    	 
	    },
	    error : function(data){
	    	
	    }
    });
	
}*/


/*function copyTheRule() {
    $('.btn-group').removeClass("open");
    var model_dialog = $("#showDialogForBuildRule").dialog({
	close: function(event, ui) {
            $('#overlay').hide();
        },
        autoOpen: false,
        title: "Copy Rule",
        height: 400,
        width: 1300,
        show: {
            effect: 'fade',
            duration: 1000
        },
        resizable: true,
        buttons: {
             Ok: function() {
            	 	 var alertFlag = true;
            	 	if(alertFlag == true){
                            alert("Please don't leave any one field as empty");
                    }
                    else{
                                $(this).dialog("close");
                                //buildRuleTableCount = 0;
                                $('#overlay').hide();
                    }
                 }
              }
        
         });
    model_dialog.load("copyRule.jsp").dialog("open");
	var height = $(document).height();
	$('#overlay').attr('style','height:'+height+'px');
	$('#overlay').show();
}*/
            
        	
        	/*if ($("#showColumnsDialogForBuildRule").dialog('isOpen') === true) {
                var tableArr = [];
                var colArr = [];
                var colCount = 0;
                var queryString = '';
                var colString = '';
                options = "<option value=''>select</option>";
                $('#countyTablesSelect :selected').each(function(i, tableName) {
                    //alert( $(tableName).val());
                    tableArr[i] = $(tableName).val();
                });
                console.log("tableArr size "+tableArr.length);
                for(i=0; i<tableArr.length; i++){
                	
                  	$('#'+tableArr[i]+' :selected').each(function(j, colName){ 
                  		checkedTableName = tableArr[i].toLowerCase().replace(/_/g, "");
                  		colArr[colCount] = $(colName).val();
                  		var colInfo = $(colName).val().split("-");
                  		var columnDesc = colInfo[0].replace(/ /g, '*');
                  		options = options+"<option value="+columnDesc+"-"+aliasArray[i]+">"+colInfo[0]+"</option>";
                  		colCount = colCount+1;
                  		});
                  	}
            } else {
                alert("Popup has been closed.");
            }*/
      // });
 	
/*$(document).on('focusin', '.Column', function() {
    var colId = this.id;
    alert(colId);
    colId = colId.split('-');
    var colCount = colId[1];
    if (colCount == undefined) {
        $('#commonColumn').empty();
        $('#commonColumn').html("");
        $('#commonColumn').html(options);
        $('#ruleColumn').empty();
        $('#ruleColumn').html("");
        $('#ruleColumn').html(options);
    }
    $('#commonColumn-' + colCount).empty();
    $('#commonColumn-' + colCount).html("");
    $('#commonColumn-' + colCount).html(options);
    $('#ruleColumn-' + colCount).empty();
    $('#ruleColumn-' + colCount).html("");
    $('#ruleColumn-' + colCount).html(options);
    $('#concatColumn-'+colCount).empty();
	$('#concatColumn-'+colCount).html("");
	$('#concatColumn-'+colCount).html(options);
});
$(document).on('focusin', "#commonColumn", function() {
	$('#commonColumn').empty();
    $('#commonColumn').html("");
    $('#commonColumn').append("");
    
    $('#commonColumn').append(options);
    options.
});
$(document).on('focusin', "#ruleColumn", function() {
	$('#ruleColumn').empty();
    $('#ruleColumn').html("");
    $('#ruleColumn').append("");
    $('#ruleColumn').append(options);
    console.log(options);	
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
        "<td><select id='concatColumn-" + ruleCount + "' class='form-control Column'>"+options+"</select></td>" + 
        "<td><label class='control-label'>Concat Value :</label></td>" + 
        "<td><input type='text' id='concatValue-" + ruleCount + "' class='form-control' ></td>" +
        "<td><label class='control-label'>Separated By :</label></td>" + 
        "<td><input type='text' id='separationOperator-" + ruleCount + "' class='form-control' ></td>" +
        "<td><label class='control-label'>When Condition:</label></td>" + 
        "<td><input type='text' id='where-" + ruleCount + "' class='form-control iframeForModal' ></td>" + 
        "<td><label class='control-label'> </label></td><td><div class = 'cancel' id = 'cancelButton-" + ruleCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></td></div>";
        
        $(tableWithTr).append(appendColumns);
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
	else{
        $(trid).remove();
    }
});

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
        "<option value=''>--select--</option>" + 
        "<option value='CONCAT'>Concat</option>" + 
        "<option value='REPLACE'>Replace</option>" +
        "<option value='REPLACE FIRST'>Replace First</option>" +
        "<option value='REPLACE LAST'>Replace Last</option>" +
        "<option value='DIVIDE BY'>Divide By</option>" +
        "<option value='MULTIPLY BY'>Multiply By</option>" +
        "<option value='PUT DECIMAL'>Put Decimal</option></select></td></tr>";
        $("#buildRuleTable").append(columnsData);
        var columnOptionsSelect = '';
        columnOptionsSelect += "<option value=''>--select--</option>";
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

$(document).off('click', '.iframeForModal').on('click', '.iframeForModal', function(e) {
    var tableColumn = this.id;
    var tableColumnCount = tableColumn.split("-");
    tableColumnCount = tableColumnCount[1];
    var tableColumnData = "";
    var position = ($(this).closest('td').parent()[0].sectionRowIndex)+1; 
    
    var uiDialogHeight = 350;
    var leftX = 23;
    var topY = 90;
    var lastIndex = $('#buildRuleTable tr:last').index();
    
    topY = ((topY)+(20*(position-1)+(15*(position-1))));
    uiDialogHeight = uiDialogHeight+((33)*(lastIndex+1));
    $('.divClass').css({left:leftX+"px",top:topY+"px",position: 'absolute'});
    $('.divClass').show();
    $(".ui-dialog").css({height:uiDialogHeight+'px'});
        
        tableColumnData = "<tr><th><label class='control-label'></label></th><th></th><th></th><th></th><th></th>" + 
        "<th></th><th></th><th></th><th></th><th></th><th></th><th></th>" + 
        "<th><label class='control-label'></label></th></tr>" +
        "<tr><td><label class='control-label'>columns :</label></td>" + 
        "<td><select id='buildColoumn-" + tableColumnCount + "' class='form-control buildCountyWhereClass'>" + 
        "<option value=''>--select--</option></select></td>" + 
        "<td><label class='control-label'>Operators :</label></td>" + 
        "<td><select id='buildOperators-" + tableColumnCount + "'  class='form-control buildCountyWhereClass '>" + 
        "<option value=''>--select--</option><option value='='>=</option>" + 
        "<option value='<>'><></option><option value='like'>like</option>" + 
        "<option value='not like'>not like</option><option value='<'><</option>" + 
        "<option value='>'>></option><option value='is null'>is null</option>" + 
        "<option value='is not null'>is not null</option><option value='in'>in</option>" + 
        "<option value='between'>between</option></select></td>" + 
        "<td><label class='control-label'>Value :</label></td>" + 
        "<td><select id='buildValueSelect-" + tableColumnCount + "' class='form-control buildCountyWhereClass '>" + 
        "<option value=''>--select--</option></select></td>" + 
        "<td><label class='control-label'>  </label></td>" + 
        "<td><input type='text' id='buildValueText-" + tableColumnCount + "' class='form-control buildCountyWhereClass'></td>" + 
        "<td><label class='control-label'>  </label></td>" + 
        "<td><button class='btn btn-warning applyButton' id = 'apply-" + tableColumnCount + "'>apply</button>" +
        		"<input type='hidden' id='fieldAliasName' value='"+fieldAliasName+"'></td></tr>";
        
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").empty();
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").append(tableColumnData);
        
        var columnOptionsToAppend = "<option value=''>--select--</option>";
        var tableNamesSelected = "";
        var selectedTableNamesValue = [];
        var selectedTableNamesText = [];
        var optionValues=[];
        var optionsToShow=[];
    	var tableNameAsId = [];
        var columId = [];
        var selectedTableCount = 0;
    	$('#countyTablesSelect option').each(function() {
            if ($(this).is(":checked")) {
            	var checkedTableNames = this.text;
            	columId[selectedTableCount] = checkedTableNames;
            	selectedTableNamesText.push(checkedTableNames);
    	       	tableNamesSelected += (checkedTableNames+",");
    	       	tableNamesSelected.slice(0,-1);
    	       	tableNameAsId[selectedTableCount] = document.getElementById(checkedTableNames);		
    	       	checkedTableNames = checkedTableNames.toLowerCase().replace(/_/g, "");
    	       	selectedTableNamesValue.push(checkedTableNames);
    	       	selectedTableCount = selectedTableCount+1;
            }
        });
    	
    	for (var i = 0; i < tableNameAsId.length; i++) {
		    	var j = 0;
		    	$('#'+columId[i]+' option').each(function(){
		   			if ($(this).is(":checked")) {
		   				columId[i] = columId[i].toLowerCase().replace(/_/g, "");
		       			optionValues.push(tableNameAsId[i].options[j].value+"-"+aliasArray[i]);
		    	       	optionsToShow.push(tableNameAsId[i].options[j].text);
		   			}
		       		j = j+1
		       	})
		}
    	
    	for (var i = 0; i < optionValues.length; i++) {
        	columnOptionsToAppend += '<option value="' + optionValues[i] + '">' + optionsToShow[i] + '</option>';
        }
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildColoumn-" + tableColumnCount).empty();
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildColoumn-" + tableColumnCount).html(columnOptionsToAppend);
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildValueSelect-" + tableColumnCount).empty();
        $('#submenuPagesDisplay').contents().find('body').find("#whereClauseTableForBuildRule").find("#buildValueSelect-" + tableColumnCount).html(columnOptionsToAppend);
        $('#submenuPagesDisplay').contents().find('body').find("#alertCheck").empty();
        
});

$(document).off('click', '.cancel').on('click', '.cancel', function() {
    
    var cancelId = this.id;
    cancelId = cancelId.split("-");
    var cancelCount = cancelId[1];
    $('#cancel-' + cancelCount).remove();
    $(".divClass").hide();
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
    if ($('#buildColoumn-' + applyButtonCount).val() != "" && $('#buildOperators-' + applyButtonCount).val() != "" && ($('#buildValueSelect-' + applyButtonCount).val() != "" || $('#buildValueText-' + applyButtonCount).val() != "")) {
        alertFieldEmptyCheck = false;
    } 
    else if ($('#buildOperators-' + applyButtonCount).val() == "is null" || $('#buildOperators-' + applyButtonCount).val() == "is not null") {
        if ($('#buildColoumn-' + applyButtonCount).val() != "") {
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
        var buildValueSelect = "";
        var buildSelectValue = "";
        var buildColoumn = "";
        var buildColoumnValue = "";
        var buildValueText = "";
        var buildTextValue = "";
        if ($('#buildValueSelect-' + applyButtonCount).val() != "" && ($('#buildOperators-' + applyButtonCount).val() != 'is null' && $('#buildOperators-' + applyButtonCount).val() != 'is not null')) {
            buildValueSelect = $('#buildValueSelect-' + applyButtonCount).val();
            buildSelectValue = buildValueSelect.split('-');
            buildValueSelect = buildSelectValue[1] + '.' + buildSelectValue[0];
            buildColoumn = $('#buildColoumn-' + applyButtonCount).val();
            buildColoumnValue = buildColoumn.split('-');
            buildColoumn = buildColoumnValue[1] + "." + buildColoumnValue[0];
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + $('#buildOperators-' + applyButtonCount).val() + " " + buildValueSelect);
        } 
        else if ($('#buildOperators-' + applyButtonCount).val() == 'is null' || $('#buildOperators-' + applyButtonCount).val() == 'is not null') {
            buildColoumn = $('#buildColoumn-' + applyButtonCount).val();
            buildColoumnValue = buildColoumn.split('-');
            buildColoumn = buildColoumnValue[1] + "." + buildColoumnValue[0];
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + $('#buildOperators-' + applyButtonCount).val());
        }
        else if ($('#buildValueSelect-' + applyButtonCount).val() == "") {
            buildValueText = $('#buildValueText-' + applyButtonCount).val();
            buildColoumn = $('#buildColoumn-' + applyButtonCount).val();
            buildColoumnValue = buildColoumn.split('-');
            buildColoumn = buildColoumnValue[1] + "." + buildColoumnValue[0];
            window.parent.$("#buildRuleTable").find('#where-' + applyButtonCount).val(fieldAliasName + " " + $('#buildOperators-' + applyButtonCount).val() + " " + "'" + buildValueText + "'");
        } 
        
    }
});*/

$(document).off('click', '.cancelRightEnd').on('click', '.cancelRightEnd', function() {
	$('.divClass').hide();
});



