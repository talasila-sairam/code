var option = "";
/*var tableCount = 0;*/
var checkingCount = 0;
var optionValues = [];
var optionsToShow = [];
var tableNamesSelected = "";
var selectedTableNamesValue = [];
var selectedTableNamesText = [];
var tableArr = [];
var aliasArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
var selectedTableNames = [];
var selectedTableNamesWithAlias = [];
var checkedValue = [];
var tableNameAsId = [];
var columId = [];


function dynamicAddColumns() {
	
    optionValues = [];
    optionsToShow = [];
    var selectedTableCount = 0;
    tableNamesSelected = "";
    selectedTableNamesWithAlias = "";
    var countOfSelectedTables  = 0;
    var listOfSelectedTableNames = "";
    var listOfTableNamesWithAlias = "";
    var listOfTableNames = "";
    $('#countyTablesSelect option').each(function(i) {
        if ($(this).is(":checked")) {
            var checkedTableNames = this.text;
            columId[selectedTableCount] = checkedTableNames;
            selectedTableNamesText.push(checkedTableNames);
            tableNamesSelected += (checkedTableNames + ",");
            tableNamesSelected.slice(0, -1);
            tableNameAsId[selectedTableCount] = document.getElementById(checkedTableNames);
            selectedTableNamesWithAlias += (checkedTableNames +" "+aliasArray[selectedTableCount] +",");
            selectedTableNamesWithAlias.slice(0,-1);
            selectedTableCount = selectedTableCount + 1;
        }
        
  /*      	
        
        localStorage.setItem("selectedTableNamesWithAlias",selectedTableNamesWithAlias);*/
    });
    
    if(tableNamesSelected != ""){
    	localStorage.setItem("tableNamesSelected", tableNamesSelected);
    }
    else{
    	var message = "Please select tables and try again";
    	var status="Error";
		var style="alert-danger";
    	showalertMsg(message,status,style);
    	return false;
    }
    
    $('#countyTablesSelect option').each(function(i) {
        	var listOfTables = this.text;
        	listOfTableNames += (listOfTables + ",");
        	listOfTableNames.slice(0, -1);
            /*listOfTableNamesWithAlias += (listOfTables +" "+aliasArray[countOfSelectedTables] +",");
            listOfTableNamesWithAlias.slice(0,-1);*/
            countOfSelectedTables = countOfSelectedTables + 1;
            console.log(listOfTableNames);
            /*console.log(listOfTableNamesWithAlias);*/
            localStorage.setItem("selectedTableNames", listOfTableNames);
            /*localStorage.setItem("selectedTableNamesWithAlias",listOfTableNamesWithAlias);*/
    });
    
    for (var i = 0; i < tableNameAsId.length; i++) {
    	if(tableNameAsId.length > 1){
    		var j = 0;
    		$('#' + columId[i] + ' option').each(function() {
                if ($(this).is(":checked")) {
                    optionValues.push(tableNameAsId[i].options[j].text + "-" + aliasArray[i] +"-"+columId[i]);
                    optionsToShow.push(tableNameAsId[i].options[j].text +"-"+columId[i]);
                }
                j = j + 1;
            });
    	}
    	else{
    		var j = 0;
    		$('#' + columId[i] + ' option').each(function() {
                if ($(this).is(":checked")) {
                    optionValues.push(tableNameAsId[i].options[j].text + "-" + aliasArray[i] +"-"+columId[i]);
                    optionsToShow.push(tableNameAsId[i].options[j].text +"-"+columId[i]);
                }
                j = j + 1;
            });
    	}
    }
    if(optionValues.length > 0 && optionsToShow.length > 0){
    	localStorage.setItem("optionValues",optionValues);
        localStorage.setItem("optionsToShow",optionsToShow);
    }
    else{
    	var message = "Please select columns and try again";
    	var status="Error";
		var style="alert-danger";
    	showalertMsg(message,status,style);
    	return false;
    }
    
    var columnOptionsText = [];
    var columnValues = [];
    for (var p = 0; p < optionValues.length; p++) {
    	columnValues.push(optionValues[p].split(" ").join("%20").split("#").join("%23"));
    }
    for (var q = 0; q < optionsToShow.length; q++) {
    	columnOptionsText.push(optionsToShow[q].split(" ").join("%20").split("#").join("%23"));
    }
    /*selectedTableNamesText = selectedTableNamesText.split(" ").join("%20").split("#").join("%23");*/
    /*tableNamesSelected = tableNamesSelected.split(" ").join("%20").split("#").join("%23");*/
    var popup;
    /*popup= window.open("BuildQuery.jsp?columnValues=" + columnValues + "&columnOptionsText=" + columnOptionsText + "&tableNamesSelected=" + tableNamesSelected, "_blank");*/
    popup= window.open("BuildQuery.jsp","_blank");
    popup.focus();
}
$(document).on('click', '#refreshQuery', function() {
	$("#baseQuery").val('');
	var value = localStorage.getItem("resultQuery");
	var baseQueryValue = localStorage.getItem("resultBuildQuery");
	/*alert(value);*/
	$("#baseQuery").val(value);
	$("#resultBaseQuery").val(baseQueryValue);
	/*document.getElementById("baseQuery").innerHTML = localStorage.getItem("resultQuery");*/
	/*var baseQuery = $("#baseQuery").val();*/
});
/*$(document).on('click', '#runQueryButton', function() {
 
    
    optionValues = [];
    optionsToShow = [];
    var selectedTableCount = 0;
    tableNamesSelected = "";
    var selectedTablesList = [];
    
    
    var tableNamesSelectedValue = $("#tableNamesSelected").val();
    var tableNamesSelected = $("#tableNamesSelected").val();
    
    if (tableNamesSelectedValue.indexOf(',') > 1) {
        tableNamesSelectedValue = tableNamesSelectedValue.split(',');
        for (var i = 0; i < tableNamesSelectedValue.length - 1; i++) {
            selectedTablesList = tableNamesSelectedValue[i];
            selectedTableCount++;
        }
    }
    var colArr = [];
    var colCount = 0;
    var queryString = '';
    var colString = '';
    var tableString = '';
    var columns = [];
    var operators = [];
    var value = [];
    var valueSelect = [];
    var tableColumn = [];
    var alertFlag = true;
    var tableAliasName;
    var joinsData = "";
    var joinRelation = "";
    var joinCondtion = "";
    var mainQueryGroupCount = 0;
    var mainQueryOrderCount = 0;
    var mainQueryAlertFlagForGroupnOrder = true;
    var mainQueryGroupColString = "";
    var mainQueryOrderColString = "";
    
    if (tableNamesSelected != "" && selectedTableCount > 1) {
        ajaxCallToGetData();
    }
    
    function ajaxCallToGetData() {
        if (tableNamesSelected.endsWith(',')) {
            tableNamesSelected = tableNamesSelected.slice(0, -1);
        }
        $.ajax({
            type: "POST",
            url: 'executeQueryToGetJoins',
            async: false,
            data: {
                'tableNamesSelected': tableNamesSelected
            },
            success: function(data) {
                joinsData = data;
                joinsData = joinsData.toString().split(",");
                joinCondtion = joinsData[0];
                joinRelation = joinsData[1];
                tableNamesSelected = "";
            },
            error: function(data) {
            
            }
        });
    }
    setTimeout(function() {
    	if(tableNamesSelectedValue.length > 1){
    		for (var i = 0; i < tableNamesSelectedValue.length - 1; i++) {
                selectedTableNamesWithAlias[i] = tableNamesSelectedValue[i] + " " + aliasArray[i];
                tableArr[i] = tableNamesSelectedValue[i] + "|" + aliasArray[i];
                tableString = tableString + tableNamesSelectedValue[i] + " " + aliasArray[i] + " (nolock) " + ', ';
            }
    	}
    	else{
    		for (var i = 0; i < tableNamesSelectedValue.length - 1; i++) {
                selectedTableNamesWithAlias[i] = tableNamesSelectedValue[i];
                tableArr[i] = tableNamesSelectedValue[i];
                tableString = tableString + tableNamesSelectedValue[i] + " (nolock) " + ', ';
            }
    	}
        
        for (k = 0; k < tableArr.length; k++) {
            var tableName = tableArr[k];
            tableName = tableName.split("|");
            tableAlias = tableName[1];
            tableName = tableName[0];
            selectedTableNames[k] = tableName;
            tableNamesSelected += (tableName + ",");
            console.log(tableNamesSelected);
            
            var columnValues = $("#columnValues").val();
            columnValues = columnValues.split(',');
            for(var j = 0;j<columnValues.length;j++){
                if(columnValues[j].indexOf(tableName) > 0){
                var columnInfo = columnValues[j].split("-");
                colArr[colCount] = columnInfo[0];
                
                    columnInfo[0] = columnInfo[0].slice(1);
                
                var colConcatName = tableAlias + "." + "[" + columnInfo[0] + "]";
                colString = colString + colConcatName + ', ';
                }
            }
            if(tableArr.length > 1){
            	for(var j = 0;j<columnValues.length;j++){
                    if(columnValues[j].indexOf(tableName) > 0){
                    var columnInfo = columnValues[j].split("-");
                    colArr[colCount] = columnInfo[0];
                    columnInfo[0] = columnInfo[0].slice(1);
                    var colConcatName = tableAlias + "." + "[" + columnInfo[0] + "]";
                    colString = colString + colConcatName + ', ';
                    }
                }                            		
            }
            else{
            	for(var j = 0;j<columnValues.length;j++){
                    if(columnValues[j].indexOf(tableName) > 0){
                    var columnInfo = columnValues[j].split("-");
                    colArr[colCount] = columnInfo[0];
                    
                        columnInfo[0] = columnInfo[0].slice(1);
                    
                    var colConcatName = "[" + columnInfo[0] + "]";
                    colString = colString + colConcatName + ', ';
                    }
                } 
           }
        }
        tableString.slice(0, -2);
        colString.slice(0, -2);
        
        tableCount = localStorage.getItem("tableCount");
        alert(tableCount);
        var mainQueryGroupByValue = [];
        $('#columnsForMainQueryGroupBy :selected').each(function(l3, subGroupName) {
        	 if(tableCount > 1){
        		 mainQueryGroupByValue[mainQueryGroupCount] = $(subGroupName).val();
                 var mainQueryTableAliasGroup = mainQueryGroupByValue[mainQueryGroupCount].split("-");
                 var groupColMainConcat = mainQueryTableAliasGroup[1] + "." + "[" + mainQueryTableAliasGroup[0] + "]";
                 mainQueryGroupColString = mainQueryGroupColString + groupColMainConcat + ', ';
                 mainQueryGroupCount = mainQueryGroupCount + 1;
        	 }
        	 mainQueryGroupByValue[mainQueryGroupCount] = $(subGroupName).val();
             var mainQueryTableAliasGroup = mainQueryGroupByValue[mainQueryGroupCount].split("-");
             var groupColMainConcat = "[" + mainQueryTableAliasGroup[0] + "]";
             mainQueryGroupColString = mainQueryGroupColString + groupColMainConcat + ', ';
             mainQueryGroupCount = mainQueryGroupCount + 1;
            
        });
        mainQueryGroupColString = mainQueryGroupColString.slice(0, -2);
        
        var mainQueryHavingFunctionValue = $("#functionsForMainQuery").val();
        
        var mainQueryHavingColumnName = $("#columnsForMainQueryHavingBy").val();
        if (mainQueryHavingColumnName != "" && mainQueryHavingColumnName != null && tableCount > 1) {
            mainQueryHavingColumnName = mainQueryHavingColumnName.split('-');
            mainQueryHavingColumnName = mainQueryHavingColumnName[1] + '.' + "[" + mainQueryHavingColumnName[0] + "]";
        }
        else if (mainQueryHavingColumnName != "" && mainQueryHavingColumnName != null) {
            mainQueryHavingColumnName = mainQueryHavingColumnName.split('-');
            mainQueryHavingColumnName = "[" + mainQueryHavingColumnName[0] + "]";
        }
        
        var mainQueryHavingOperator = $("#mainQueryHavingByOperator").val();
        
        var mainQueryHavingValueSelect = $("#mainQueryHavingByValueSelect").val();
        if (mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != null && tableCount > 1) {
            mainQueryHavingValueSelect = mainQueryHavingValueSelect.split('-');
            mainQueryHavingValueSelect = mainQueryHavingValueSelect[1] + '.' + "[" + mainQueryHavingValueSelect[0] + "]";
        }
        else if(mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != null && tableCount > 1) {
            mainQueryHavingValueSelect = mainQueryHavingValueSelect.split('-');
            mainQueryHavingValueSelect = "[" + mainQueryHavingValueSelect[0] + "]";
        }
        var mainQueryHavingValueText = $("#mainQueryHavingByValueText").val();
        
        var mainQueryOrderByValueType = $('#mainQueryOrderByColumnType').val();
        var mainQueryOrderByValues = [];
        $('#columnsForMainQueryOrderBy :selected').each(function(l2, subOrderName) {
        	if(tableCount > 1){
        		mainQueryOrderByValues[mainQueryOrderCount] = $(subOrderName).val();
                var mainQueryTableAliasOrder = mainQueryOrderByValues[mainQueryOrderCount].split("-");
                var mainColConcat = mainQueryTableAliasOrder[1] + "." + "[" + mainQueryTableAliasOrder[0] + "]";
                mainQueryOrderColString = mainQueryOrderColString + mainColConcat + ', ';
                mainQueryOrderCount = mainQueryOrderCount + 1;
        	}
        	mainQueryOrderByValues[mainQueryOrderCount] = $(subOrderName).val();
            var mainQueryTableAliasOrder = mainQueryOrderByValues[mainQueryOrderCount].split("-");
            var mainColConcat = "[" + mainQueryTableAliasOrder[0] + "]";
            mainQueryOrderColString = mainQueryOrderColString + mainColConcat + ', ';
            mainQueryOrderCount = mainQueryOrderCount + 1;
        });
        mainQueryOrderColString = mainQueryOrderColString.slice(0, -2);
        if (joinsData != "" && joinRelation != undefined) {
            for (var t = 0; t < tableArr.length; t++) {
                joinRelation = joinRelation.replace(selectedTableNames[t], selectedTableNamesWithAlias[t]);
                joinCondtion = joinCondtion.replace(selectedTableNames[t], aliasArray[t]);
            }
            queryString = queryString + 'SELECT ' + colString.slice(0, -2) + ' FROM ' + joinRelation + " ON " + joinCondtion;
        } 
        else {
            queryString = queryString + 'SELECT ' + colString.slice(0, -2) + ' FROM ' + tableString.slice(0, -2);
        }
        for (var m = 0; m <= tableCount; m++) {
            alertFlag = true;
            var column = $('#col-' + m).val();
            if(tableCount > 1){
            	if (column != undefined && column != "") {
                    column = column.split('-');
                    columns.push(column[1] + '.' + "[" + column[0] + "]");
                }
            	 else if (column == "") {
                     columns.push("null");
                 }
            }
            else{
            	if (column != undefined && column != "") {
                    column = column.split('-');
                    columns.push("[" + column[0] + "]");
                }
            	 else if (column == "") {
                     columns.push("null");
                 }
            }
            
           
            var operator = $('#operators-' + m).val();
            if (operator != undefined && operator != "") {
                operators.push(operator);
            } 
            else if (operator == "") {
                operators.push("null");
            }
            var values = $('#valueText-' + m).val();
            if (values != undefined && values != "") {
                value.push(values);
            } 
            else if (values == "") {
                value.push("''");
            }
            var tColumn = $("#columns-" + m).val();
            if (tColumn != undefined && tColumn != "") {
                tableColumn.push(tColumn);
            } 
            else if (tColumn == "") {
                tableColumn.push("null");
            }
            var selectedValue = $("#value-" + m).val();
            if(tableCount > 1){
            	if (selectedValue != undefined && selectedValue != "") {
                    selectedValue = selectedValue.split("-");
                    valueSelect.push(selectedValue[1] + '.' + "[" + selectedValue[0] + "]");
                } 
                else if (selectedValue == "") {
                    valueSelect.push("null");
                }
            }
            else{
            	if (selectedValue != undefined && selectedValue != "") {
                    selectedValue = selectedValue.split("-");
                    valueSelect.push("[" + selectedValue[0] + "]");
                } 
                else if (selectedValue == "") {
                    valueSelect.push("null");
                }
            }
            
            if (columns[m] == "null" && operators[m] == "null" && (valueSelect[m] == "''" || value[m] == "null")) {
                alertFlag = true;
            } 
            else if (columns[m] != "" && valueSelect[m] != "" && operators[m] != "" && columns[m] != "null" && operators[m] != "null") {
                alertFlag = false;
            } 
            else if (columns[m] != "" && value[m] != "" && operators[m] != "" && columns[m] != "null" && operators[m] != "null" && valueSelect[m] != 'null') {
                alertFlag = false;
            } 
            else if (operators[m] == "is null" || operators[m] == "is not null") {
                if (columns[m] != "" && operators[m] != "" && columns[m] != "null" && operators[m] != "null") {
                    alertFlag = false;
                } 
                else {
                    alertFlag = true;
                }
            } 
            else if (m > 1 && tableColumn[m - 1] == "") {
                alertFlag = true;
            }
        }
        if (alertFlag == true) {
            $("#alertForLikeOperator").hide();
            $("#alertForInOperator").hide();
            $("#alertWhileFieldIsEmpty").empty();
            $("#alertWhileFieldIsEmpty").show();
            $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter the required fields");
            $("#alertWhileFieldIsEmpty").effect("highlight", 100);
        } 
        else if (alertFlag == false) {
            
            if (mainQueryOrderByValueType != "" && mainQueryOrderByValueType != undefined) {
                if (mainQueryOrderByValues.length != 0) {
                    mainQueryAlertFlagForGroupnOrder = false;
                } 
                else {
                    mainQueryAlertFlagForGroupnOrder = true;
                }
            } 
            else if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null ) {
                if (mainQueryHavingFunctionValue != "" && mainQueryHavingColumnName != "" && mainQueryHavingOperator != "" && (mainQueryHavingValueSelect != "undefined." || mainQueryHavingValueText != "")) {
                    mainQueryAlertFlagForGroupnOrder = false;
                } 
                else if (mainQueryHavingFunctionValue == "" && mainQueryHavingColumnName == "" && mainQueryHavingOperator == "" && (mainQueryHavingValueSelect == "undefined." || mainQueryHavingValueText == "")) {
                    mainQueryAlertFlagForGroupnOrder = false;
                } 
                else {
                    mainQueryAlertFlagForGroupnOrder = true;
                }
            
            } 
            else {
                mainQueryAlertFlagForGroupnOrder = false;
            }
            if (mainQueryAlertFlagForGroupnOrder == true) {
                $("#alertForLikeOperator").hide();
                $("#alertForInOperator").hide();
                $("#alertWhileFieldIsEmpty").empty();
                $("#alertWhileFieldIsEmpty").show();
                $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter the required fields at group by or order by clause");
                $("#alertWhileFieldIsEmpty").effect("highlight", 10);
            }
        }
        if (alertFlag == false && mainQueryAlertFlagForGroupnOrder == false) {
            for (var n = 0; n <= tableCount; n++) {
                var specialChars = ['~', '`', '!', '#', '$', '^', '&', '*', '+', '=', '-', '[', ']', ';', '|', '/', '{', '}', ':', '<', '>', '?', '@', '(', ')', '.'];
                var codeNumbersForSplChars = ['%7E', '%80', '%21', '%23', '%24', '%5E', '%26', '%2A', '%2B', '%3D', '%2D', '%5B', '%5D', '%3B', '%7C', '%2F', '%7B', '%7D', '%3A', '%3C', '%3E', '%3F', '%40', '%28', '%29', '%2E'];
                var startValue = $('#rowStart-' + n).prop("checked");
                var endValue = $('#rowEnd-' + n).prop("checked");
                var startingBrackets = "";
                var endingBrackets = "";
                if (startValue == true) {
                    startingBrackets = " ( ";
                }
                if (endValue == true) {
                    endingBrackets = " ) ";
                }
                if (n == 0 && valueSelect[n] != undefined && valueSelect[n] != "" && valueSelect[n] != "null") {
                    if (operators[n] == "is null" || operators[n] == "is not null") {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                    } 
                    else {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                    }
                } 
                else if (n == 0 && (valueSelect[n] == undefined || valueSelect[n] == "" || valueSelect[n] == "null")) {
                    if (operators[n] == "is null" || operators[n] == "is not null") {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                    } 
                    else if (operators[n] == "like" || operators[n] == "not like") {
                        value[n] = value[n].split('%').join('%25');
                                					for(var sp = 0 ; sp < specialChars.length;sp++){
                                						for (var i = 0; i < value[n].length; i++)
                                    					{
                                    					  if (specialChars[sp].indexOf(value[n].charAt(i)) != -1)
                                    					  {
                                    						  value[n] = value[n].split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
                                    					  }
                                    					}
                                					}
                        if (value[n] == "''") {
                            queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                        } else {
                            queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " ('" + value[n] + "')" + endingBrackets;
                        }
                    } 
                    
                    else if (operators[n] == "in") {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                    } 
                    else if (operators[n] == "<" || operators[n] == ">" || operators[n] == "<=" || operators[n] == ">=") {
                        if ($.isNumeric(value[n])) {
                            queryString = queryString + " " + 'where ' + startingBrackets + "  CONVERT(double," + columns[n] + ") " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                        } 
                        else {
                            queryString = queryString + " " + 'where ' + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "')" + endingBrackets;
                        }
                    } 
                    else {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + value[n] + endingBrackets;
                    }
                }
                if (n > 0) {
                    if ((tableColumn[n - 1] == "And" || tableColumn[n - 1] == "Or") && (valueSelect[n] == "" || valueSelect[n] == undefined || valueSelect[n] == "null")) {
                        if (operators[n] == "is null" || operators[n] == "is not null") {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                        } 
                        else if (operators[n] == "in") {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                        } 
                        else if (operators[n] == "<" || operators[n] == ">" || operators[n] == "<=" || operators[n] == ">=") {
                            if ($.isNumeric(value[n])) {
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " CONVERT(double," + columns[n] + ")" + operators[n] + " (" + value[n] + ") " + endingBrackets;
                            } 
                            else {
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "') " + endingBrackets;
                            }
                        } 
                        else if (operators[n] == "like" || operators[n] == "not like") {
                            
                            value[n] = value[n].split('%').join('%25');
                                    					for(var sp = 0 ; sp < specialChars.length;sp++){
                                    						for (var i = 0; i < value[n].length; i++)
                                        					{
                                        					  if (specialChars[sp].indexOf(value[n].charAt(i)) != -1)
                                        					  {
                                        						  value[n] = value[n].split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
                                        					  }
                                        					}
                                    					}
                            if (value[n] == "''") {
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                            } else {
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "')" + endingBrackets;
                            }
                        } 
                        else {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + columns[n] + " " + operators[n] + " '" + value[n] + "'" + endingBrackets;
                        }
                    } 
                    else if ((tableColumn[n - 1] == "And" || tableColumn[n - 1] == "Or") && (valueSelect[n] != "" && valueSelect[n] != undefined && valueSelect[n] != "null")) {
                        if (operators[n] == "is null" || operators[n] == "is not null") {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + endingBrackets;
                        } 
                        else {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                        }
                    }
                }
            }
            
            if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null  && mainQueryHavingFunctionValue != "" && mainQueryHavingColumnName != "" && mainQueryHavingOperator != "" && (mainQueryHavingValueSelect != "undefined." || mainQueryHavingValueText != "") && mainQueryOrderByValueType != null  && mainQueryOrderColString != "") {
                if (mainQueryHavingValueSelect != null  && mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != "undefined.") {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueSelect + " Order By " + mainQueryOrderColString + " " + mainQueryOrderByValueType;
                } 
                else {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueText + " Order By " + mainQueryOrderColString + " " + mainQueryOrderByValueType;
                }
            } 
            else if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null  && mainQueryHavingFunctionValue != "" && mainQueryHavingColumnName != "" && mainQueryHavingOperator != "" && (mainQueryHavingValueSelect != "undefined." || mainQueryHavingValueText != "") && mainQueryOrderByValueType == null  && mainQueryOrderColString == "") {
                if (mainQueryHavingValueSelect != null  && mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != "undefined.") {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueSelect + " ";
                } 
                else {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueText + " ";
                }
            } 
            else if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null  && mainQueryHavingFunctionValue == "" && mainQueryHavingColumnName == "" && mainQueryHavingOperator == "" && (mainQueryHavingValueSelect == "undefined." || mainQueryHavingValueText == "")) {
                queryString = queryString + " Group by " + mainQueryGroupColString;
            } 
            else if ((mainQueryGroupByValue == "" || mainQueryGroupByValue == null ) && mainQueryHavingFunctionValue == "" && mainQueryHavingColumnName == "" && mainQueryHavingOperator == "" && (mainQueryHavingValueSelect == "undefined." || mainQueryHavingValueText == "") && mainQueryOrderByValueType != null  && mainQueryOrderByValueType != "" && mainQueryOrderByValueType != undefined) {
                queryString = queryString + " Order by " + mainQueryOrderColString + " " + mainQueryOrderByValueType;
            }
           
            tableCount = 0;
            $("#resultQuery").val("");
            $("#resultQuery").val(queryString);
            $("#query").val("");
            $("#resultQuery").attr('title', queryString);
            $("#alertWhileFieldIsEmpty").hide();
            
            if($("#resultQuery").val() !=""){
            	$('html, body').animate({
                    scrollTop: $("#grid1").offset().top
                });
            }
            
        }
    }, 0.1);
});

*//*var disableFlag = false;
var isNullCheckFlag = false;
$(document).on('change', '.operatorInMainQuery', function() {
    
    var id = this.id;
    var count = id.split('-');
    count = count[1];
    var value = this.value;
    var operatorValue;
    
    if (id.match("operators")) {
        operatorValue = value;
        if (operatorValue == "like" || operatorValue == "not like") {
            $("#alertWhileFieldIsEmpty").hide();
            $("#alertForInOperator").hide();
            $("#alertForLikeOperator").empty();
            $("#alertForLikeOperator").show();
            $("#alertForLikeOperator").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please add '%' in textfield as per your requirement");
            $("#alertForLikeOperator").effect("highlight", 100);
            $("#value-" + count).attr("disabled", true);
            $('#value-' + count).prop('selectedIndex', 0);
        } 
        else {
            $("#alertForLikeOperator").empty();
            $("#alertForLikeOperator").hide();
            $("#alertForInOperator").empty();
            $("#alertForInOperator").hide();
            
            $('#value-' + count).removeAttr("disabled");
        }
        if (operatorValue == "is null" || operatorValue == "is not null") {
            isNullCheckFlag = true;
        } 
        else {
            isNullCheckFlag = false;
        }
        if (isNullCheckFlag == true) {
            $("#value-" + count).attr("disabled", true);
            $('#value-' + count).prop('selectedIndex', 0);
            $("#valueText-" + count).attr("disabled", true);
            $("#valueText-" + count).val("");
        } 
        else if (isNullCheckFlag == false) {
            if ($('#value-' + count).val() != "" && $("#valueText-" + count).val() != "") {
                $('#value-' + count).removeAttr("disabled");
                $("#valueText-" + count).removeAttr("disabled");
                $('#value-' + count).prop('selectedIndex', 0);
                $("#valueText-" + count).val("");
            } 
            else if ($('#value-' + count).val() == "" && $("#valueText-" + count).val() != "") {
                $("#valueText-" + count).removeAttr("disabled");
            } 
            else if ($('#value-' + count).val() != "" && $("#valueText-" + count).val() == "") {
                $("#value-" + count).removeAttr("disabled");
            } 
            
            else {
                
                if (operatorValue == "like" || operatorValue == "not like" || operatorValue == "in") {
                    
                    if (operatorValue == "like" || operatorValue == "not like") {
                        $("#value-" + count).attr("disabled", true);
                        $('#value-' + count).prop('selectedIndex', 0);
                        $('#subQueryButton-' + count).css('display', 'none');
                    } 
                    else if (operatorValue == "in") {
                        $("#value-" + count).attr("disabled", true);
                        $('#subQueryButton-' + count).css('display', 'block');
                    }
                } 
                else {
                    $("#value-" + count).removeAttr("disabled");
                    $('#subQueryButton-' + count).css('display', 'none');
                }
                $("#valueText-" + count).removeAttr("disabled");
            }
        }
    }
    
    if ($('#col-' + count).val() != "" && $('#operators-' + count).val() != "") {
        disableFlag = false;
    } 
    else {
        disableFlag = true;
    }
    
    if (disableFlag == false) {
        $('#columns-' + count).removeAttr("disabled");
    } 
    else if (disableFlag == true) {
        $('#columns-' + count).attr("disabled", true);
    }
    if (id == "value-" + count) {
        if (value != "") {
            $('#valueText-' + count).attr("disabled", true);
            $('#valueText-' + count).val("");
        } 
        else {
            $('#valueText-' + count).removeAttr("disabled");
        }
    }
    if (id == "valueText-" + count) {
        if (value != "") {
            $('#value-' + count).attr("disabled", true);
            $('#value-' + count).prop('selectedIndex', 0);
        } 
        else {
            $('#value-' + count).removeAttr("disabled");
        }
    }
});
*/
/*$(document).on('keyup', '.form-control', function() {
    
    var seletedId = this.id;
    var selectedCount = seletedId.split('-');
    selectedCount = selectedCount[1];
    var givenValue = this.value;
    if (seletedId == 'valueText-' + selectedCount) {
        if (givenValue != "") {
            $('#value-' + selectedCount).attr("disabled", true);
        } 
        else {
            if ($('#operators-' + selectedCount).val() == 'like' || $('#operators-' + selectedCount).val() == 'not like') {
                $('#value-' + selectedCount).attr("disabled", true);
            } 
            else {
                $('#value-' + selectedCount).removeAttr("disabled");
            }
        }
    }
    if ($('#col-' + selectedCount).val() != "" && $('#operators-' + selectedCount).val() != "") {
        $('#columns-' + selectedCount).removeAttr("disabled");
        $('#value-' + selectedCount).prop('selectedIndex', 0);
    } 
    else {
        $('#columns-' + selectedCount).attr("disabled", true);
    }
});
*/

/*$(document).off('click', '.closeButton').on('click', '.closeButton', function() {
    var id = this.id;
    $('#' + id).remove();
    id = id.split("-");
    var closeButtonCount = id[1];
    closeButtonCount = closeButtonCount - 1;
    $('#columns-' + closeButtonCount).prop('selectedIndex', 0);
});

$(document).off('click', '[data-hide]').on('click', '[data-hide]', function() {
    
    $(this).closest("." + $(this).attr("data-hide")).hide();
    var closeLabelId = this.id;
    console.log(closeLabelId);
    $("." + $('#' + closeLabelId).attr("data-hide")).hide();
});


$(document).off('change', '.checkBoxStyle').on('change', '.checkBoxStyle', function() {
    
    var rowStart = false;
    var rowEnd = false;
    var alertErrorForCount = false;
    var alertError = false;
    var startCount = 0;
    var endCount = 0;
    var alertFlag = false;
    for (var checkBox = 0; checkBox <= tableCount; checkBox++) {
        var checkBoxperv = checkBox - 1;
        var checkBoxNext = checkBox + 1;
        rowStart = $('#rowStart-' + checkBox).prop("checked");
        if (rowStart == true) {
            startCount++;
        }
        rowEnd = $('#rowEnd-' + checkBox).prop("checked");
        if (rowEnd == true) {
            endCount++;
            
            if (startCount < endCount) {
                alertFlag = true
            }
        }
        if (rowEnd == true && checkBox == 0 && rowStart == false) {
            alertError = true;
        }
        var rowEndNext = $('#rowEnd-' + checkBoxperv).prop("checked");
        if (rowEnd == true && rowEndNext == true && rowStart == false) {
            alertError = true;
        }
        var rowStartNext = $('#rowStart-' + checkBoxNext).prop("checked");
        if (rowStart == true && rowEnd == false && rowStartNext == true) {
            alertError = true;
        }
    }
    if (startCount == endCount) {
        $(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass('ui-state-disabled');
        alertErrorForCount = false;
        startCount = 0;
        endCount = 0;
    } 
    else if (startCount != endCount) {
        alert("Count error");
        $("#alertForLikeOperator").hide();
        $("#alertForInOperator").hide();
        $("#alertWhileFieldIsEmpty").empty();
        $("#alertWhileFieldIsEmpty").show();
        $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
        $("#alertWhileFieldIsEmpty").effect("highlight", 100);
        $(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
        alertErrorForCount = true;
        startCount = 0;
        endCount = 0;
    }
    if (alertError == true || alertFlag == true) {
        $("#alertForLikeOperator").hide();
        $("#alertWhileFieldIsEmpty").empty();
        $("#alertWhileFieldIsEmpty").show();
        $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
        $("#alertWhileFieldIsEmpty").effect("highlight", 100);
        $(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
    }
    if (alertErrorForCount == false && alertError == false && alertFlag == false) {
        $("#alertForLikeOperator").hide();
        $("#alertForInOperator").hide();
        $("#alertWhileFieldIsEmpty").empty();
        $("#alertWhileFieldIsEmpty").hide();
        $(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass('ui-state-disabled');
    }
});
*/
$(document).on('change', '.column-operations', function() {
    var operation = $('#column_operators').val();
    $("#ruleQuery").val("");
    if (operation == "CONCAT") {
        $('#operation_fields').empty();
        $('#operation_field').empty();
        $('#selected_columns1').empty();
        $('#selected_columns2').empty();
        var selectedColumns = $('#selected_columns').val();
    } 
    else if (operation == "REPLACE") {
        var field1 = "<label class='control-label'>Operators :</label><input type='text' id='operation_field1' class=form-control'>";
        $('#operation_fields').append(field1);
        var field2 = "<label class='control-label'>Operators :</label><input type='text' id='operation_field2' class=form-control'>";
        $('#operation_field').append(field2);
        var selectionField1 = "<label class='control-label'>SelectedColumns</label><select id='selected_cols1' multiple='multiple' class='form-control countyTableColumns'></select>";
        $('#selected_columns1').append(selectionField1);
        var selectionField2 = "<label class='control-label'>SelectedColumns</label><select id='selected_cols2' multiple='multiple' class='form-control countyTableColumns'></select>";
        $('#selected_columns2').append(selectionField2);
        var tableArr = [];
        var colArr = [];
        var colCount = 0;
        var queryString = '';
        var colString = '';
        var tableString = '';
        
        $('#countyTables :selected').each(function(s, tableName) {
            tableArr[s] = $(tableName).val();
            tableString = tableString + $(tableName).val() + ', ';
        
        });
        for (t = 0; t < tableArr.length; t++) {
            $('#' + tableArr[t] + ' :selected').each(function(u, colName) {
                colArr[colCount] = $(colName).val();
                colString = colString + $(colName).val() + ', ';
                colCount = colCount + 1;
            
            });
        }
        tableString.slice(0, -2);
        colString.slice(0, -2);
        queryString = queryString + 'SELECT ' + colString.slice(0, -2) + ' FROM ' + tableString.slice(0, -2);
        option = "";
        $('#selected_cols1').empty();
        $('#selected_cols2').empty();
        for (var v = 0; v < colArr.length; v++) {
            option += '<option value="' + colArr[v] + '">' + colArr[v] + '</option>';
        }
        $('#selected_cols1').append(option);
        $('#selected_cols1').multiselect('rebuild');
        $('#selected_cols2').append(option);
        $('#selected_cols2').multiselect('rebuild');
        $('#selected_cols2').multiselect({
            includeSelectAllOption: true,
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true,
            enableCaseInsensitiveFiltering : true
        });
    }
});


/*
   * This method  is used to Show the "Having Clause" div while we select any column
   * Logic Start Here
   */
$(document).off('change', '#columnsForMainQueryGroupBy').on('change', '#columnsForMainQueryGroupBy', function() {
    var groupByValue = this.value;
    if (groupByValue != "") {
        $('.Having').css('display', 'block');
        $('.Having').css('display', 'table-row');
    } 
    else {
        $('.Having').css('display', 'none');
    }
});
/*Logic End Here*/



/*
   * This method is used to disable or enable the having function value select and text fields
   * Logic start here
   */

var isNullCheckFlagForMainQueryHaving = false;
$(document).off('change', '.countyTableFunctionMainQuery').on('change', '.countyTableFunctionMainQuery', function() {
    
    var havingValueSelectFieldMainId = this.id;
    var havingValueSelectFieldMainValues = this.value;
    
    if (havingValueSelectFieldMainId.match("mainQueryHavingByOperator")) {
        if (havingValueSelectFieldMainValues == "like" || havingValueSelectFieldMainValues == "not like") {
            $("#alertWhileFieldIsEmpty").hide();
            $("#alertForLikeOperator").empty();
            $("#alertForLikeOperator").show();
            $("#alertForLikeOperator").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please add '%' in textfield as per your requirement");
            $("#alertForLikeOperator").effect("highlight", 100);
            $("#mainQueryHavingByValueSelect").attr("disabled", true);
            $('#mainQueryHavingByValueSelect').prop('selectedIndex', 0);
        } 
        else {
            $("#alertForLikeOperator").empty();
            $("#alertForLikeOperator").hide();
            $('#mainQueryHavingByValueSelect').removeAttr("disabled");
        }
        
        if (havingValueSelectFieldMainValues == "is null" || havingValueSelectFieldMainValues == "is not null") {
            isNullCheckFlagForSubQueryHaving = true;
        } 
        else {
            isNullCheckFlagForSubQueryHaving = false;
        }
        if (isNullCheckFlagForSubQueryHaving == true) {
            $("#mainQueryHavingByValueSelect").attr("disabled", true);
            $('#mainQueryHavingByValueSelect').prop('selectedIndex', 0);
            $("#mainQueryHavingByValueText").attr("disabled", true);
            $("#mainQueryHavingByValueText").val("");
        } 
        else if (isNullCheckFlagForSubQueryHaving == false) {
            if ($('#mainQueryHavingByValueSelect').val() != "" && $("#mainQueryHavingByValueText").val() != "") {
                $('#mainQueryHavingByValueSelect').removeAttr("disabled");
                $("#mainQueryHavingByValueText").removeAttr("disabled");
                $('#mainQueryHavingByValueSelect').prop('selectedIndex', 0);
                $("#mainQueryHavingByValueText").val("");
            } 
            else if ($('#mainQueryHavingByValueSelect').val() == "" && $("#mainQueryHavingByValueText").val() != "") {
                $("#mainQueryHavingByValueText").removeAttr("disabled");
            } 
            else if ($('#mainQueryHavingByValueSelect').val() != "" && $("#mainQueryHavingByValueText").val() == "") {
                $("#mainQueryHavingByValueSelect").removeAttr("disabled");
            } 
            
            else {
                if (havingValueSelectFieldMainValues == "like" || havingValueSelectFieldMainValues == "not like" || havingValueSelectFieldMainValues == "in") {
                    
                    if (havingValueSelectFieldMainValues == "like" || havingValueSelectFieldMainValues == "not like") {
                        $("#mainQueryHavingByValueSelect").attr("disabled", true);
                        $('#mainQueryHavingByValueSelect').prop('selectedIndex', 0);
                    } 
                    else if (havingValueSelectFieldMainValues == "in") {
                        $("#mainQueryHavingByValueSelect").attr("disabled", true);
                    }
                } 
                else {
                    $("#mainQueryHavingByValueSelect").removeAttr("disabled");
                }
                $("#mainQueryHavingByValueText").removeAttr("disabled");
            
            }
        }
    }
    if (havingValueSelectFieldMainId == "mainQueryHavingByValueSelect") {
        if (havingValueSelectFieldMainValues != "") {
            $('#mainQueryHavingByValueText').attr("disabled", true);
            $('#mainQueryHavingByValueText').val("");
        } 
        else {
            $('#mainQueryHavingByValueText').removeAttr("disabled");
        }
    }
    if (havingValueSelectFieldMainId == "mainQueryHavingByValueText") {
        if (havingValueSelectFieldMainValues != "") {
            $('#mainQueryHavingByValueSelect').attr("disabled", true);
            $('#mainQueryHavingByValueSelect').prop('selectedIndex', 0);
        } 
        else {
            $('#mainQueryHavingByValueSelect').removeAttr("disabled");
        }
    }
});

/*
   * Logic End Here
   */

/*
   * This method is used to while value is enter in the field of Having value text then value select field will be disable
   * 
   *  Logic Start Here
   */
$(document).on('keyup', '.countyTableFunctionMainQuery', function() {
    
    var havingValueTextId = this.id;
    var havingValueTextValue = this.value;
    if (havingValueTextId == 'mainQueryHavingByValueText') {
        if (havingValueTextValue != "") {
            $('#mainQueryHavingByValueSelect').attr("disabled", true);
        } 
        else {
            if ($('#mainQueryHavingByOperator').val() == 'like' || $('#mainQueryHavingByOperator').val() == 'not like') {
                $('#mainQueryHavingByValueSelect').attr("disabled", true);
            } 
            else {
                $('#mainQueryHavingByValueSelect').removeAttr("disabled");
            }
        }
    }
});
/*
   * Logic End Here
   */



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
}