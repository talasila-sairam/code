var subQueryoption = "";
var subQueryTableCount = 0;
var subQueryCheckingCount = 0;
var subQueryOptionValues = [];
var subQueryOptionsToShow = [];
var subQueryTableNamesSelected = "";
var subQuerySelectedTableNamesValue = [];
var subQuerySelectedTableNamesText = [];
var subColumId = [];
var subTableNameAsId = [];
var subAliasArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
var selectedTableNames = [];

var selectedTableNamesValue = [];

/*
     * This method is used to call the SubQuery while clicking on the subquery button.
     * Logic Start Here
     */
$(document).off('click', '.iframeForSubquery').on('click', '.iframeForSubquery', function() {
    
    var subAliasCheckValue = 0;
    var subqueryButtonId = this.id;
    var subqueryButtonValue = this.value;
    var subqueryIdCount = subqueryButtonId.split("-");
    subqueryIdCount = subqueryIdCount[1];
    console.log(subqueryIdCount);
    if ((subqueryButtonId == "subQueryButton-" + subqueryIdCount) && ($("#col-" + subqueryIdCount).val() != "")) {
        subQueryOptionValues = [];
        subQueryOptionsToShow = [];
        selectedTableNamesText = [];
        selectedTableNamesValue = [];
        $('#countyTablesSelect option').each(function() {
            if ($(this).is(":checked")) {
                var subCheckedTableNames = this.text;
                subColumId[subAliasCheckValue] = subCheckedTableNames;
                selectedTableNamesText.push(subCheckedTableNames + " " + subAliasArray[subAliasCheckValue]);
                selectedTableNamesValue.push(subCheckedTableNames);
                subQueryTableNamesSelected += (subCheckedTableNames + ",");
                subQueryTableNamesSelected.slice(0, -1);
                subTableNameAsId[subAliasCheckValue] = document.getElementById(subCheckedTableNames);
                subQuerySelectedTableNamesValue.push(subCheckedTableNames);
                subAliasCheckValue++;
            }
        });
        for (var subI = 0; subI < subTableNameAsId.length; subI++) {
            var subJ = 0;
            $('#' + subColumId[subI] + ' option').each(function() {
                if ($(this).is(":checked")) {
                    subQueryOptionValues.push(subTableNameAsId[subI].options[subJ].text + "-" + subAliasArray[subI]);
                    subQueryOptionsToShow.push(subTableNameAsId[subI].options[subJ].text);
                }
                subJ = subJ + 1
            });
        }
        ;
        
        var model_dialog = $("#subQueryDiv").dialog({
            close: function(event, ui) {},
            autoOpen: false,
            title: "SubQuery ",
            height: 480,
            width: 1350,
            modal: true,
            resizable: true,
            show: {
                effect: 'fade',
                duration: 1000
            },
            buttons: {
                Ok: function() {
                    var subQueryColArr = [];
                    var subQueryColCount = 0;
                    var subQueryGroupCount = 0;
                    var subQueryOrderCount = 0;
                    var subQueryString = '';
                    var subQueryColString = '';
                    var subQueryGroupColString = '';
                    var subQueryOrderColString = '';
                    var subQueryTableString = '';
                    var subQueryColumns = [];
                    var subQueryOperators = [];
                    var subQueryValue = [];
                    var subQueryValueSelect = [];
                    var subQueryTableColumn = [];
                    var subQueryAlertFlag = true;
                    var subQueryTableAliasName;
                    var subQueryAlertFlagForGroupnOrder = true;
                    var subQueryAlertFlagForTableAndColumns = true;
                    var subQueryGroupByValue = [];
                    var selectedTableSubQuery = "";
                    var tableNameCount = 0;
                    var joinsData = "";
                    var subStartValue = [];
                    var subEndValue = [];
                    var subOtherFunction = [];
                    var subMatchCondition = [];
                    var selectedTableNamesWithAlias = [];
                    /*var selectedTableSubQuery = $('#seletedTableNames').val();*/
                    $('#seletedTableNames :selected').each(function(l1, tableName) {
                        tableNameCount = tableNameCount + 1;
                    });
                    $('#seletedTableNames :selected').each(function(l1, tableName) {
                        if (tableNameCount > 1) {
                            subQueryColArr[subQueryTableCount] = $(tableName).val();
                            selectedTableNamesWithAlias[subQueryTableCount] = subQueryColArr[subQueryTableCount] + " " + subAliasArray[subQueryTableCount];
                            var subQueryTableAlias = subQueryColArr[subQueryTableCount] + " " + subAliasArray[subQueryTableCount];
                            selectedTableSubQuery = selectedTableSubQuery + subQueryTableAlias + " (nolock) " + ',';
                            subQueryTableCount = subQueryTableCount + 1;
                        } 
                        else {
                            subQueryColArr[subQueryTableCount] = $(tableName).val();
                            var subQueryTableAlias = subQueryColArr[subQueryTableCount];
                            selectedTableSubQuery = subQueryTableAlias + " (nolock) ";
                            subQueryTableCount = subQueryTableCount + 1;
                        }
                    });
                    selectedTableSubQuery = selectedTableSubQuery.slice(0, -1);
                    
                    
                    
                    
                    $('#columnsForSubQueryGroupBy :selected').each(function(l3, subGroupName) {
                        if (tableNameCount > 1) {
                            subQueryGroupByValue[subQueryGroupCount] = $(subGroupName).val();
                            var subQueryTableAliasGroup = subQueryGroupByValue[subQueryGroupCount].split("-");
                            var groupColConcat = subQueryTableAliasGroup[1] + "." + "[" + subQueryTableAliasGroup[0] + "]";
                            subQueryGroupColString = subQueryGroupColString + groupColConcat + ', ';
                            subQueryGroupCount = subQueryGroupCount + 1;
                        } else {
                            subQueryGroupByValue[subQueryGroupCount] = $(subGroupName).val();
                            var subQueryTableAliasGroup = subQueryGroupByValue[subQueryGroupCount].split("-");
                            var groupColConcat = "[" + subQueryTableAliasGroup[0] + "]";
                            subQueryGroupColString = subQueryGroupColString + groupColConcat + ', ';
                            subQueryGroupCount = subQueryGroupCount + 1;
                        }
                    });
                    subQueryGroupColString = subQueryGroupColString.slice(0, -2);
                    
                    var subQueryHavingFunctionValue = $("#functionsForSubQuery").val();
                    
                    var subQueryHavingColumnName = $("#columnsForSubQueryHavingBy").val();
                    if (subQueryTableCount > 1) {
                        if (subQueryHavingColumnName != "" && subQueryHavingColumnName != null ) {
                            subQueryHavingColumnName = subQueryHavingColumnName.split('-');
                            subQueryHavingColumnName = subQueryHavingColumnName[1] + '.' + "[" + subQueryHavingColumnName[0] + "]";
                        }
                    } 
                    else {
                        if (subQueryHavingColumnName != "" && subQueryHavingColumnName != null ) {
                            subQueryHavingColumnName = subQueryHavingColumnName.split('-');
                            subQueryHavingColumnName = "[" + subQueryHavingColumnName[0] + "]";
                        }
                    }
                    
                    var subQueryHavingOperator = $("#subQueryHavingOperator").val();
                    var subQueryHavingValueSelect = $("#subQueryHavingValueSelect").val();
                    if (subQueryTableCount > 1) {
                        if (subQueryHavingValueSelect != "" && subQueryHavingValueSelect != null ) {
                            subQueryHavingValueSelect = subQueryHavingValueSelect.split('-');
                            subQueryHavingValueSelect = subQueryHavingValueSelect[1] + '.' + "[" + subQueryHavingValueSelect[0] + "]";
                        }
                    } 
                    else {
                        if (subQueryHavingValueSelect != "" && subQueryHavingValueSelect != null ) {
                            subQueryHavingValueSelect = subQueryHavingValueSelect.split('-');
                            subQueryHavingValueSelect = "[" + subQueryHavingValueSelect[0] + "]";
                        }
                    }
                    var subQueryHavingValueText = $("#subQueryHavingValueText").val();
                    
                    
                    for (var i = 0; i <= joinStructureSubTableCount; i++) {
                        
                        var subQueryOrderByValueType = $('#orderBySubQueryColumnsType-' + i).val();
                        var subQueryOrderByValues = [];
                        if (subQueryOrderByValueType != "" && subQueryOrderByValueType != undefined) {
                            $('#columnsForSubQueryOrderBy -' + i + ' :selected').each(function(l2, subOrderName) {
                                if (tableNameCount > 1) {
                                    subQueryOrderByValues[subQueryOrderCount] = $(subOrderName).val();
                                    var subQueryTableAliasOrder = subQueryOrderByValues[subQueryOrderCount].split("-");
                                    var colConcat = subQueryTableAliasOrder[1] + "." + "[" + subQueryTableAliasOrder[0] + "]";
                                    subQueryOrderColString = subQueryOrderColString + colConcat + ', ';
                                    subQueryOrderCount = subQueryOrderCount + 1;
                                } 
                                else {
                                    subQueryOrderByValues[subQueryOrderCount] = $(subOrderName).val();
                                    var subQueryTableAliasOrder = subQueryOrderByValues[subQueryOrderCount].split("-");
                                    var colConcat = "[" + subQueryTableAliasOrder[0] + "]";
                                    subQueryOrderColString = subQueryOrderColString + colConcat + ', ';
                                    subQueryOrderCount = subQueryOrderCount + 1;
                                }
                            });
                            subQueryOrderColString = subQueryOrderColString.slice(0, -2);
                            subQueryOrderColString = subQueryOrderColString + " " + subQueryOrderByValueType + ", ";
                        }
                    
                    }
                    
                    $('#selectColumns :selected').each(function(l1, subColName) {
                        if (tableNameCount > 1) {
                            subQueryColArr[subQueryColCount] = $(subColName).val();
                            var subQueryTableAlias = subQueryColArr[subQueryColCount].split("-");
                            var colConcatName = "LTRIM(RTRIM(" + subQueryTableAlias[1] + "." + "[" + subQueryTableAlias[0] + "]))";
                            subQueryColString = subQueryColString + colConcatName + ', ';
                            subQueryColCount = subQueryColCount + 1;
                        } 
                        else {
                            subQueryColArr[subQueryColCount] = $(subColName).val();
                            var subQueryTableAlias = subQueryColArr[subQueryColCount].split("-");
                            var colConcatName = "LTRIM(RTRIM([" + subQueryTableAlias[0] + "]))";
                            subQueryColString = subQueryColString + colConcatName + ', ';
                            subQueryColCount = subQueryColCount + 1;
                        }
                    });
                    
                    subQueryColString.slice(0, -2);
                    
                    if (selectedTableSubQuery != "" && subQueryColString != "") {
                        subQueryAlertFlagForTableAndColumns = false;
                    } 
                    else {
                        subQueryAlertFlagForTableAndColumns = true;
                    }
                    
                    if (subQueryAlertFlagForTableAndColumns == true) {
                        $("#alertForLikeOperatorSubQuery").hide();
                        $(alertForInOperatorSubQuery).hide();
                        $("#alertWhileFieldIsEmptySubQuery").empty();
                        $("#alertWhileFieldIsEmptySubQuery").show();
                        $("#alertWhileFieldIsEmptySubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please select the required table and Columns");
                        $("#alertWhileFieldIsEmptySubQuery").effect("highlight", 100);
                    } 
                    else {
                        if (tableNameCount > 1) {
                            
                            var tableNamesSelected = $("#seletedTableNames").val();
                            var tableNamesSelectedValue = $("#seletedTableNames").val();
                            var tableNames = "";
                            if (tableNamesSelectedValue.indexOf(',') > 1) {
                                tableNamesSelectedValue = tableNamesSelectedValue.split(',');
                                for (var i = 0; i < tableNamesSelectedValue.length - 1; i++) {
                                    selectedTablesList = tableNamesSelectedValue[i];
                                    selectedTableCount++;
                                }
                            }
                            
                            for (var j = 0; j < tableNamesSelected.length; j++) {
                                tableNames += tableNamesSelected[j] + ",";
                            
                            }
                            tableNames = tableNames.slice(0, -1);
                            alert(tableNames);
                            console.log(tableNames);
                            $.ajax({
                                type: "POST",
                                url: 'executeQueryToGetJoins',
                                async: false,
                                data: {
                                    'tableNamesSelected': tableNames
                                },
                                success: function(data) {
                                    joinsData = data;
                                    joinsData = joinsData.toString().split(",");
                                    joinCondtion = joinsData[0];
                                    // tableNamesSelected = "";
                                
                                },
                                error: function(data) {
                                    alert("Please Try After Sometime.");
                                    return false;
                                }
                            });
                            
                            if (joinsData != "" && joinsData != undefined) {
                                for (var t = 0; t < tableNameCount; t++) {
                                    var columnAlias = tableNamesSelected[t] + ".";
                                    var columnAliasArr = subAliasArray[t] + ".";
                                    joinCondtion = joinCondtion.replace(tableNamesSelected[t], selectedTableNamesWithAlias[t]);
                                    for (var i = 0; i < 6; i++) {
                                        joinCondtion = joinCondtion.replace(columnAlias, columnAliasArr);
                                    }
                                    /*joinCondtion = joinCondtion.replace(columnAlias, columnAliasArr);*/
                                }
                                subQueryString = subQueryString + 'SELECT ' + subQueryColString.slice(0, -2) + ' FROM ' + joinCondtion;
                            } 
                            else {
                                alert("Please Create Join Condition and Try agian");
                                return false;
                            }
                            /*subQueryString = subQueryString + 'SELECT ' + subQueryColString.slice(0, -2) + ' FROM ' + selectedTableSubQuery.slice(0, -1);*/
                        } 
                        else {
                            subQueryString = subQueryString + 'SELECT ' + subQueryColString.slice(0, -2) + ' FROM ' + selectedTableSubQuery;
                        }
                        for (var subM = 0; subM <= subQueryTableCount; subM++) {
                            subQueryAlertFlag = true;
                            var subQueryColumn = $('#subQueryCol-' + subM).val();
                            if (tableNameCount > 1) {
                                if (subQueryColumn != undefined && subQueryColumn != "") {
                                    subQueryColumn = subQueryColumn.split('-');
                                    subQueryColumns.push(subQueryColumn[1] + '.' + "[" + subQueryColumn[0] + "]");
                                } 
                                else if (subQueryColumn == "") {
                                    subQueryColumns.push("null");
                                }
                            } 
                            else {
                                if (subQueryColumn != undefined && subQueryColumn != "") {
                                    subQueryColumn = subQueryColumn.split('-');
                                    subQueryColumns.push("[" + subQueryColumn[0] + "]");
                                } 
                                else if (subQueryColumn == "") {
                                    subQueryColumns.push("null");
                                }
                            }
                            
                            var subQueryOperator = $('#subQueryOperators-' + subM).val();
                            if (subQueryOperator != undefined && subQueryOperator != "") {
                                subQueryOperators.push(subQueryOperator);
                            } 
                            else if (subQueryOperator == "") {
                                subQueryOperators.push("null");
                            }
                            var subQueryValues = $('#subQueryValueText-' + subM).val();
                            if (subQueryValues != undefined && subQueryValues != "") {
                                subQueryValue.push(subQueryValues);
                            } 
                            else if (subQueryValues == "") {
                                subQueryValue.push("''");
                            }
                            var subQueryTColumn = $("#subQueryColumns-" + subM).val();
                            if (subQueryTColumn != undefined && subQueryTColumn != "") {
                                subQueryTableColumn.push(subQueryTColumn);
                            } 
                            else if (subQueryTColumn == "") {
                                subQueryTableColumn.push("null");
                            }
                            
                            
                            var subStartValueString = $('#subQueryRowStart-' + subM).prop("checked");
                            
                            if (subStartValueString != undefined && subStartValueString != "") {
                                subStartValue.push(subStartValueString);
                            } 
                            else if (subStartValueString == false) {
                                subStartValue.push('');
                            }
                            
                            var subEndValueString = $('#subQueryRowEnd-' + subM).prop("checked");
                            
                            if (subEndValueString != undefined && subEndValueString != "") {
                                subEndValue.push(subEndValueString);
                            } 
                            else if (subEndValueString == false) {
                                subEndValue.push('');
                            }
                            
                            var subOtherFunctionString = $('#subOtherFunction-' + subM).val();
                            
                            if (subOtherFunctionString != undefined && subOtherFunctionString != "") {
                                subOtherFunction.push(subOtherFunctionString);
                            } 
                            else if (subOtherFunctionString == "") {
                                subOtherFunction.push("null");
                            }
                            var subMatchConditionString = $("#subQueryMatchCondition-" + subM).val();
                            if (subMatchConditionString != undefined && subMatchConditionString != "") {
                                subMatchCondition.push(subMatchConditionString);
                            } 
                            else if (subMatchConditionString == "") {
                                subMatchCondition.push("null");
                            }
                            
                            
                            var subQuerySelectedValue = $("#subQueryValueSelect-" + subM).val();
                            if (tableNameCount > 1) {
                                if (subQuerySelectedValue != undefined && subQuerySelectedValue != "") {
                                    subQuerySelectedValue = subQuerySelectedValue.split("-");
                                    subQueryValueSelect.push(subQuerySelectedValue[1] + '.' + "[" + subQuerySelectedValue[0] + "]");
                                } 
                                else if (subQuerySelectedValue == "") {
                                    subQueryValueSelect.push("null");
                                }
                            } 
                            else {
                                if (subQuerySelectedValue != undefined && subQuerySelectedValue != "") {
                                    subQuerySelectedValue = subQuerySelectedValue.split("-");
                                    subQueryValueSelect.push("[" + subQuerySelectedValue[0] + "]");
                                } 
                                else if (subQuerySelectedValue == "") {
                                    subQueryValueSelect.push("null");
                                }
                            }
                            
                            if (subQueryColumns[subM] == "null" && subQueryOperators[subM] == "null" && (subQueryValueSelect[subM] == "''" || subQueryValue[subM] == "null")) {
                                subQueryAlertFlag = false;
                            } 
                            else if (subQueryColumns[subM] != "" && subQueryValueSelect[subM] != "" && subQueryOperators[subM] != "" && subQueryColumns[subM] != "null" && subQueryOperators[subM] != "null") {
                                subQueryAlertFlag = false;
                            } 
                            else if (subQueryColumns[subM] != "" && subQueryValue[subM] != "" && subQueryOperators[subM] != "" && subQueryColumns[subM] != "null" && subQueryOperators[subM] != "null" && subQueryValueSelect[subM] != 'null') {
                                subQueryAlertFlag = false;
                            } 
                            else if (subQueryOperators[subM] == "is null" || subQueryOperators[subM] == "is not null") {
                                if (subQueryColumns[subM] != "" && subQueryOperators[subM] != "" && subQueryColumns[subM] != "null" && subQueryOperators[subM] != "null") {
                                    subQueryAlertFlag = false;
                                } 
                                else {
                                    subQueryAlertFlag = true;
                                }
                            } 
                            else if (subM > 1 && subQueryTableColumn[subM - 1] == "") {
                                subQueryAlertFlag = true;
                            }
                        }
                        if (subQueryAlertFlag == true) {
                            $("#alertForLikeOperatorSubQuery").hide();
                            $("#alertForInOperatorSubQuery").hide();
                            $("#alertWhileFieldIsEmptySubQuery").empty();
                            $("#alertWhileFieldIsEmptySubQuery").show();
                            $("#alertWhileFieldIsEmptySubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter the required fields");
                            $("#alertWhileFieldIsEmptySubQuery").effect("highlight", 100);
                        } 
                        else if (subQueryAlertFlag == false) {
                            /* alert(subQueryOrderByValues);
                            alert(subQueryOrderByValueType);*/
                            if (subQueryOrderByValueType != "" && subQueryOrderByValueType != undefined) {
                                
                                if (subQueryOrderByValues.length != 0) {
                                    subQueryAlertFlagForGroupnOrder = false;
                                } 
                                else {
                                    subQueryAlertFlagForGroupnOrder = true;
                                }
                            } 
                            else if (subQueryGroupByValue != "" && subQueryGroupByValue != null  && subQueryGroupByValue != "null") {
                                if (subQueryHavingFunctionValue != "" && subQueryHavingColumnName != "" && subQueryHavingOperator != "" && (subQueryHavingValueSelect != "undefined." || subQueryHavingValueText != "")) {
                                    subQueryAlertFlagForGroupnOrder = false;
                                } 
                                else if (subQueryHavingFunctionValue == "" && subQueryHavingColumnName == "" && subQueryHavingOperator == "" && (subQueryHavingValueSelect == "undefined." || subQueryHavingValueText == "")) {
                                    subQueryAlertFlagForGroupnOrder = false;
                                } 
                                else {
                                    subQueryAlertFlagForGroupnOrder = true;
                                }
                            } 
                            else {
                                subQueryAlertFlagForGroupnOrder = false;
                            }
                            if (subQueryAlertFlagForGroupnOrder == true) {
                                $("#alertForLikeOperatorSubQuery").hide();
                                $("#alertForInOperatorSubQuery").hide();
                                $("#alertWhileFieldIsEmptySubQuery").empty();
                                $("#alertWhileFieldIsEmptySubQuery").show();
                                $("#alertWhileFieldIsEmptySubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter the required fields at group by or order by clause");
                                $("#alertWhileFieldIsEmptySubQuery").effect("highlight", 10);
                            }
                        }
                        
                        if (subQueryAlertFlag == false && subQueryAlertFlagForGroupnOrder == false) {
                            for (var subN = 0; subN <= subQueryTableCount; subN++) {
                                
                                if (subOtherFunction[subN] == 'Convert') {
                                    subOtherFunction[subN] = " Convert(float," + subQueryColumns[subN] + ") ";
                                } 
                                else if (subOtherFunction[subN] == 'Len') {
                                    subOtherFunction[subN] = " Len(" + subQueryColumns[subN] + ") ";
                                } 
                                else {
                                    subOtherFunction[subN] = subQueryColumns[subN];
                                }
                                
                                var subStartingBrackets = "";
                                var subEndingBrackets = "";
                                if (subStartValue[subN] == true) {
                                    subStartingBrackets = " ( ";
                                }
                                if (subEndValue[subN] == true) {
                                    subEndingBrackets = " ) ";
                                }
                                
                                if (subN == 0 && subQueryValueSelect[subN] != undefined && subQueryValueSelect[subN] != "" && subQueryValueSelect[subN] != "null") {
                                    if (subQueryOperators[subN] == "is null" || subQueryOperators[subN] == "is not null") {
                                        subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subEndingBrackets;
                                    } 
                                    
                                    else if (subQueryOperators[subN] == "like" || subQueryOperators[subN] == "not like") {
                                        if (subMatchCondition[subN] == 'Start') {
                                            subMatchCondition[subN] = " Concat('%'," + subQueryValueSelect[subN] + ") ";
                                        } 
                                        else if (subMatchCondition[subN] == 'End') {
                                            subMatchCondition[subN] = " Concat(" + subQueryValueSelect[subN] + ",'%') ";
                                        } 
                                        else if (subMatchCondition[subN] == 'Between') {
                                            subMatchCondition[subN] = " Concat('%'," + subQueryValueSelect[subN] + ",'%') ";
                                        } 
                                        else if (subMatchCondition[subN] == "null") {
                                            subMatchCondition[subN] = subQueryValueSelect[subN];
                                        }
                                        subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subMatchCondition[subN] + " " + subEndingBrackets;
                                    }
                                    /*else {
                                        subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subQueryValueSelect[subN] + subEndingBrackets;
                                    }*/
                                } 
                                else if (subN == 0 && (subQueryValueSelect[subN] == undefined || subQueryValueSelect[subN] == "" || subQueryValueSelect[subN] == "null")) {
                                    if (subQueryOperators[subN] == "is null" || subQueryOperators[subN] == "is not null") {
                                        subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subEndingBrackets;
                                    } 
                                    else if (subQueryOperators[subN] == "like" || subQueryOperators[subN] == "not like") {
                                        if (subQueryValue[subN] == "''") {
                                            subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " (" + subQueryValue[subN] + ")" + subEndingBrackets;
                                        } else {
                                            if (subMatchCondition[subN] == 'Start') {
                                                subMatchCondition[subN] = " ('%" + subQueryValue[subN] + "') ";
                                            } 
                                            else if (subMatchCondition[subN] == 'End') {
                                                subMatchCondition[subN] = " ('" + subQueryValue[subN] + "%') ";
                                            } 
                                            else if (subMatchCondition[subN] == 'Between') {
                                                subMatchCondition[subN] = " ('%" + subQueryValue[subN] + "%') ";
                                            } 
                                            else if (subMatchCondition[subN] == "null") {
                                                subMatchCondition[subN] = "'" + subQueryValue[subN] + "'";
                                            }
                                            subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subMatchCondition[subN] + " " + subEndingBrackets;
                                        }
                                    } 
                                    else if (subQueryOperators[subN] == "in" || subQueryOperators[subN] == "not in") {
                                        subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + " LTRIM(RTRIM( " + subQueryColumns[subN] + ")) " + subQueryOperators[subN] + " (" + subQueryValue[subN] + ")" + subEndingBrackets;
                                    } 
                                    else if (subQueryOperators[subN] == "<" || subQueryOperators[subN] == ">" || subQueryOperators[subN] == "<=" || subQueryOperators[subN] == "<=" || subQueryOperators[subN] == "<>" || subQueryOperators[subN] == "=") {
                                        if ($.isNumeric(subQueryValue[subN])) {
                                            /*subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + "  CONVERT(float," + subQueryColumns[subN] + ") " + subQueryOperators[subN] + " (" + subQueryValue[subN] + ")" + subEndingBrackets;*/
                                            subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subOtherFunction[subN] + subQueryOperators[subN] + " " + subQueryValue[subN] + " " + subEndingBrackets;
                                        } 
                                        else {
                                            subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " ('" + subQueryValue[subN] + "')" + subEndingBrackets;
                                        }
                                    } 
                                    else {
                                        /*subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subQueryValue[subN] + subEndingBrackets;*/
                                        if (subQueryColumns[subN] == "null" && subQueryOperators[subN] == "null") {
                                            subQueryString = subQueryString;
                                        } 
                                        else if (subQueryColumns[subN] != "null" && subQueryOperators[subN] != "null") {
                                            subQueryString = subQueryString + " " + 'where ' + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " ('" + subQueryValue[subN] + "')" + subEndingBrackets;
                                        }
                                    }
                                }
                                if (subN > 0) {
                                    if ((subQueryTableColumn[subN - 1] == "And" || subQueryTableColumn[subN - 1] == "Or") && (subQueryValueSelect[subN] == "" || subQueryValueSelect[subN] == undefined || subQueryValueSelect[subN] == "null")) {
                                        if (subQueryOperators[subN] == "is null" || subQueryOperators[subN] == "is not null") {
                                            subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subEndingBrackets;
                                        } 
                                        else if (subQueryOperators[subN] == "in" || subQueryOperators[subN] == "not in") {
                                            subQueryString = subQueryString + " LTRIM(RTRIM( " + subQueryTableColumn[subN - 1] + ")) " + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " (" + subQueryValue[subN] + ")" + subEndingBrackets;
                                        } 
                                        else if (subQueryOperators[subN] == "<" || subQueryOperators[subN] == ">" || subQueryOperators[subN] == "<=" || subQueryOperators[subN] == "<=" || subQueryOperators[subN] == "<>" || subQueryOperators[subN] == "=") {
                                            if ($.isNumeric(subQueryValue[subN])) {
                                                /*subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + " CONVERT(float," + subQueryColumns[subN] + ")" + subQueryOperators[subN] + " (" + subQueryValue[subN] + ") " + subEndingBrackets;*/
                                                subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + " " + subOtherFunction[subN] + subQueryOperators[subN] + " " + subQueryValue[subN] + " " + subEndingBrackets;
                                            } 
                                            else {
                                                subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " ('" + subQueryValue[subN] + "') " + subEndingBrackets;
                                            }
                                        } 
                                        else if (subQueryOperators[subN] == "like" || subQueryOperators[subN] == "not like") {
                                            if (subQueryValue[subN] == "''") {
                                                subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " (" + subQueryValue[subN] + ")" + subEndingBrackets;
                                            } else {
                                                if (subMatchCondition[subN] == 'Start') {
                                                    subMatchCondition[subN] = " ('%" + subQueryValue[subN] + "') ";
                                                } 
                                                else if (subMatchCondition[subN] == 'End') {
                                                    subMatchCondition[subN] = " ('" + subQueryValue[subN] + "%') ";
                                                } 
                                                else if (subMatchCondition[subN] == 'Between') {
                                                    subMatchCondition[subN] = " ('%" + subQueryValue[subN] + "%') ";
                                                } 
                                                else if (subMatchCondition[subN] == "null") {
                                                    subMatchCondition[subN] = "'" + subQueryValue[subN] + "'";
                                                }
                                                subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subMatchCondition[subN] + " " + subEndingBrackets;
                                            }
                                        } 
                                        else {
                                            /*else {*/
                                            /*subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subsubStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subQueryValue[subN] + subEndingBrackets;*/
                                            if (subQueryColumns[subN] == "null" && subQueryOperators[subN] == "null") {
                                                subQueryString = subQueryString;
                                            } 
                                            else if (subQueryColumns[subN] != "null" && subQueryOperators[subN] != "null") {
                                                subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " ('" + subQueryValue[subN] + "')" + subEndingBrackets;
                                            }
                                        }
                                    
                                    } 
                                    else if ((subQueryTableColumn[subN - 1] == "And" || subQueryTableColumn[subN - 1] == "Or") && (subQueryValueSelect[subN] != "" && subQueryValueSelect[subN] != undefined && subQueryValueSelect[subN] != "null")) {
                                        if (subQueryOperators[subN] == "is null" || subQueryOperators[subN] == "is not null") {
                                            subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subEndingBrackets;
                                        } 
                                        
                                        else if (subQueryOperators[subN] == "like" || subQueryOperators[subN] == "not like") {
                                            
                                            if (subMatchCondition[subN] == 'Start') {
                                                subMatchCondition[subN] = " Concat('%'," + subQueryValueSelect[subN] + ") ";
                                            } 
                                            else if (subMatchCondition[subN] == 'End') {
                                                subMatchCondition[subN] = " Concat(" + subQueryValueSelect[subN] + ",'%') ";
                                            } 
                                            else if (subMatchCondition[subN] == 'Between') {
                                                subMatchCondition[subN] = " Concat('%'," + subQueryValueSelect[subN] + ",'%') ";
                                            } 
                                            else if (subMatchCondition[subN] == "null") {
                                                subMatchCondition[subN] = subQueryValueSelect[subN];
                                            }
                                            subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subMatchCondition[subN] + " " + subEndingBrackets;
                                            
                                            
                                            /* else {
                                            subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + " " + subStartingBrackets + subQueryColumns[subN] + " " + subQueryOperators[subN] + " " + subQueryValueSelect[subN] + subEndingBrackets;
                                        }*/
                                        } 
                                        else {
                                            if (subQueryColumns[subN] == "null" && subQueryOperators[subN] == "null") {
                                                subQueryString = subQueryString;
                                            } 
                                            else if (subQueryColumns[subN] != "null" && subQueryOperators[subN] != "null") {
                                                subQueryString = subQueryString + " " + subQueryTableColumn[subN - 1] + subStartingBrackets + " " + subQueryColumns[subN] + " " + subQueryOperators[subN] + " ('" + subQueryValue[subN] + "')" + subEndingBrackets;
                                            }
                                        }
                                    }
                                }
                            }
                                if (subQueryGroupColString != "" && subQueryGroupColString != null  && subQueryHavingFunctionValue != "" && subQueryHavingColumnName != "" && subQueryHavingOperator != "" && (subQueryHavingValueSelect != "undefined." || subQueryHavingValueText != "") && subQueryOrderByValueType != "" && subQueryOrderColString != "") {
                                    if (subQueryHavingValueSelect != null  && subQueryHavingValueSelect != "" && subQueryHavingValueSelect != "undefined.") {
                                        subQueryString = subQueryString + " Group by " + subQueryGroupColString + " Having " + subQueryHavingFunctionValue + " (" + subQueryHavingColumnName + ") " + subQueryHavingOperator + " " + subQueryHavingValueSelect + " Order By " + subQueryOrderColString + " " + subQueryOrderByValueType;
                                    } 
                                    else {
                                        subQueryString = subQueryString + " Group by " + subQueryGroupColString + " Having " + subQueryHavingFunctionValue + " (" + subQueryHavingColumnName + ") " + subQueryHavingOperator + " " + subQueryHavingValueText + " Order By " + subQueryOrderColString + " " + subQueryOrderByValueType;
                                    }
                                } 
                                else if (subQueryGroupColString != "" && subQueryGroupColString != null  && subQueryHavingFunctionValue != "" && subQueryHavingColumnName != "" && subQueryHavingOperator != "" && (subQueryHavingValueSelect != "undefined." || subQueryHavingValueText != "") && subQueryOrderByValueType == "" && subQueryOrderColString == "") {
                                    if (subQueryHavingValueSelect != null  && subQueryHavingValueSelect != "" && subQueryHavingValueSelect != "undefined.") {
                                        subQueryString = subQueryString + " Group by " + subQueryGroupColString + " Having " + subQueryHavingFunctionValue + " (" + subQueryHavingColumnName + ") " + subQueryHavingOperator + " " + subQueryHavingValueSelect + " ";
                                    } 
                                    else {
                                        subQueryString = subQueryString + " Group by " + subQueryGroupColString + " Having " + subQueryHavingFunctionValue + " (" + subQueryHavingColumnName + ") " + subQueryHavingOperator + " " + subQueryHavingValueText + " ";
                                    }
                                } 
                                else if (subQueryGroupColString != "" && subQueryGroupColString != null  && subQueryHavingFunctionValue == "" && subQueryHavingColumnName == "" && subQueryHavingOperator == "" && (subQueryHavingValueSelect == "undefined." || subQueryHavingValueText == "")) {
                                    subQueryString = subQueryString + " Group by " + subQueryGroupColString;
                                } 
                                else if ((subQueryGroupColString == "" || subQueryGroupColString == null ) && subQueryHavingFunctionValue == "" && subQueryHavingColumnName == "" && subQueryHavingOperator == "" && (subQueryHavingValueSelect == "undefined." || subQueryHavingValueText == "") && subQueryOrderByValueType != "" && subQueryOrderColString != "") {
                                    subQueryString = subQueryString + " Order by " + subQueryOrderColString + " " + subQueryOrderByValueType;
                                }
                            subQueryTableCount = 0;
                            $("#valueText-" + subqueryIdCount).val(subQueryString);
                            model_dialog.dialog("close");
                        }
                    }
                }
            
            }
        
        });
        var selectedColumnName = $("#col-" + subqueryIdCount).val();
        var subColumnOptionsText = [];
        var subColumnValues = [];
        var selectedTableNamesTexts = [];
        var selectedTableNamesValues = [];
        
        selectedColumnName = selectedColumnName.split(" ").join("%20").split("#").join("%23");
        var selectedTableNames = localStorage.getItem("selectedTableNames");
        console.log(selectedTableNames);
        if (selectedTableNames.indexOf(",") > 0) {
            selectedTableNames = selectedTableNames.split(",");
        }
        /*  var selectedTableNamesWithAlias = localStorage.getItem("selectedTableNamesWithAlias");
        if (selectedTableNamesWithAlias.indexOf(",") > 0) {
            selectedTableNamesWithAlias = selectedTableNamesWithAlias.split(",");
        }
        var subQueryOptionValues = localStorage.getItem("optionValues");
        if (subQueryOptionValues.indexOf(",") > 0) {
            subQueryOptionValues = subQueryOptionValues.split(",")
        }
        var subQueryOptionsToShow = localStorage.getItem("optionsToShow");
        if (subQueryOptionsToShow.indexOf(",") > 0) {
            subQueryOptionsToShow = subQueryOptionsToShow.split(",")
        }
        if (subQueryOptionValues.length > 1) {
            for (var p1 = 0; p1 < subQueryOptionValues.length; p1++) {
            	if (subQueryOptionValues != "") {
            		subColumnValues.push(subQueryOptionValues[p1].split(" ").join("%20").split("#").join("%23"));
            	}
            }
        }
        if (subQueryOptionsToShow.length > 1) {
            for (var q1 = 0; q1 < subQueryOptionsToShow.length; q1++) {
            	if (subQueryOptionsToShow != "") {
            		subColumnOptionsText.push(subQueryOptionsToShow[q1].split(" ").join("%20").split("#").join("%23"));
            	}
            }
        }*/
        if (selectedTableNames.length > 1) {
            for (var tableText = 0; tableText < selectedTableNames.length; tableText++) {
                if (selectedTableNames != "") {
                    selectedTableNamesTexts.push(selectedTableNames[tableText].split(" ").join("%20").split("#").join("%23"));
                }
            }
        }
        /*    if (selectedTableNamesWithAlias.length > 1) {
            for (var tableText = 0; tableText < selectedTableNamesWithAlias.length; tableText++) {
                if (selectedTableNamesWithAlias != "") {
                    selectedTableNamesValues.push(selectedTableNamesWithAlias[tableText].split(" ").join("%20").split("#").join("%23"));
                }
            }
        }*/
        /*model_dialog.load("subQueryInWhereClause.jsp?columnValues=" + subColumnValues + "&columnOptionsText=" + subColumnOptionsText + "&selectedColumnName=" + selectedColumnName + "&selectedTableNamesTexts=" + selectedTableNamesTexts + "&selectedTableNamesValues=" + selectedTableNamesValues).dialog("open");*/
        model_dialog.load("subQueryInWhereClause.jsp?selectedColumnName=" + selectedColumnName + "&selectedTableNamesTexts=" + selectedTableNamesTexts).dialog("open");
    } 
    else {
        $("#alertWhileFieldIsEmpty").hide();
        $("#alertForLikeOperator").hide();
        $("#alertForInOperator").empty();
        $("#alertForInOperator").show();
        $("#alertForInOperator").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please Select The Column and Try agian");
        $("#alertForInOperator").effect("highlight", 100);
    }
});


/*Logic End Here*/


/*
     * This method is used to while changing the operator to "in" it will show the subquery button
     * Logic Start Here
     */

$(document).off('change', '.operatorInSubQuery').on('change', '.operatorInSubQuery', function() {
    
    var subQueryOperatorId = this.id;
    var subQueryOperatorValue = this.value;
    var subQueryOperatorCount = subQueryOperatorId.split('-');
    subQueryOperatorCount = subQueryOperatorCount[1];
    if (subQueryOperatorValue == "in") {
        $("#subQueryValueSelect-" + subQueryOperatorCount).attr("disabled", true);
        $('#innerSubQueryButton-' + subQueryOperatorCount).css('display', 'block');
    } 
    else {
        $("#subQueryValueSelect-" + subQueryOperatorCount).removeAttr("disabled")
        $('#innerSubQueryButton-' + subQueryOperatorCount).css('display', 'none');
    }
});
/*Logic End Here*/



/*
     * This method is used to Show the "Having Clause" div while we select any column 
     * Logic Start Here
     */
$(document).off('change', '#columnsForSubQueryGroupBy').on('change', '#columnsForSubQueryGroupBy', function() {
    var groupByValue = this.value;
    if (groupByValue != "") {
        $('.HavingInSubquery').css('display', 'block');
        $('.HavingInSubquery').css('display', 'table-row');
    } 
    else {
        $('.HavingInSubquery').css('display', 'none');
    }
});
/*Logic End Here*/


/*
     * This method is used to validate the Fields for enable and disable the Logical operator for dynamic fields
     * Logic Start Here
     */
var disableFlagForSubQuery = false;
var isNullCheckFlagForSubQuery = false;

$(document).off('change', '.operatorInSubQuery').on('change', '.operatorInSubQuery', function() {
    
    var subOperatorId = this.id;
    var subOperatorCount = subOperatorId.split('-');
    subOperatorCount = subOperatorCount[1];
    var subOperatorValue = this.value;
    
    if (subOperatorId.match("subQueryOperators")) {
        if (subOperatorValue == "like" || subOperatorValue == "not like") {
            $("#alertWhileFieldIsEmptySubQuery").hide();
            $("#alertForLikeOperatorSubQuery").empty();
            $("#alertForLikeOperatorSubQuery").show();
            $("#alertForLikeOperatorSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please add '%' in textfield as per your requirement");
            $("#alertForLikeOperatorSubQuery").effect("highlight", 100);
            $("#subQueryValueSelect-" + subOperatorCount).attr("disabled", true);
            $('#subQueryValueSelect-' + subOperatorCount).prop(' selectedIndex', 0);
        } 
        else {
            $("#alertForLikeOperatorSubQuery").empty();
            $("#alertForLikeOperatorSubQuery").hide();
            $('#subQueryValueSelect-' + subOperatorCount).removeAttr("disabled");
        }
        if (subOperatorValue == "is null" || subOperatorValue == "is not null") {
            isNullCheckFlagForSubQuery = true;
        } 
        else {
            isNullCheckFlagForSubQuery = false;
        }
        if (isNullCheckFlagForSubQuery == true) {
            $("#subQueryValueSelect-" + subOperatorCount).attr("disabled", true);
            $('#subQueryValueSelect-' + subOperatorCount).prop('selectedIndex', 0);
            $("#subQueryValueText-" + subOperatorCount).attr("disabled", true);
            $("#subQueryValueText-" + subOperatorCount).val("");
        } 
        else if (isNullCheckFlagForSubQuery == false) {
            if ($('#subQueryValueSelect-' + subOperatorCount).val() != "" && $("#subQueryValueText-" + subOperatorCount).val() != "") {
                $('#subQueryValueSelect-' + subOperatorCount).removeAttr("disabled");
                $("#subQueryValueText-" + subOperatorCount).removeAttr("disabled");
                $('#subQueryValueSelect-' + subOperatorCount).prop('selectedIndex', 0);
                $("#subQueryValueText-" + subOperatorCount).val("");
            } 
            else if ($('#subQueryValueSelect-' + subOperatorCount).val() == "" && $("#subQueryValueText-" + subOperatorCount).val() != "") {
                $("#subQueryValueText-" + subOperatorCount).removeAttr("disabled");
            } 
            else if ($('#subQueryValueSelect-' + subOperatorCount).val() != "" && $("#subQueryValueText-" + subOperatorCount).val() == "") {
                $("#subQueryValueSelect-" + subOperatorCount).removeAttr("disabled");
            } 
            
            else {
                if (subOperatorValue == "like" || subOperatorValue == "not like" || subOperatorValue == "in" || subOperatorValue == "not in") {
                    
                    if (subOperatorValue == "like" || subOperatorValue == "not like") {
                        $("#subQueryValueSelect-" + subOperatorCount).attr("disabled", true);
                        $('#subQueryValueSelect-' + subOperatorCount).prop('selectedIndex', 0);
                        $('#innerSubQueryButton-' + subOperatorCount).css('display', 'none');
                    } 
                    else if (subOperatorValue == "in" || subOperatorValue == "not in") {
                        $("#subQueryValueSelect-" + subOperatorCount).attr("disabled", true);
                        $('#innerSubQueryButton-' + subOperatorCount).css('display', 'block');
                        $('#subQueryMatchCondition-' + subOperatorCount).removeAttr("disabled");
                    }
                } 
                else {
                    $("#subQueryValueSelect-" + subOperatorCount).removeAttr("disabled");
                    $('#innerSubQueryButton-' + subOperatorCount).css('display', 'none');
                    $('#subQueryMatchCondition-' + subOperatorCount).attr("disabled", true);
                    $('#subQueryMatchCondition-' + subOperatorCount).prop('selectedIndex', 0);
                }
                $("#subQueryValueText-" + subOperatorCount).removeAttr("disabled");
            }
        }
    }
    if ($('#subQueryCol-' + subOperatorCount).val() != "" && $('#subQueryOperators-' + subOperatorCount).val() != "") {
        disableFlagForSubQuery = false;
    } 
    else {
        disableFlagForSubQuery = true;
    }
    
    if (disableFlagForSubQuery == false) {
        $('#subQueryColumns-' + subOperatorCount).removeAttr("disabled");
    } 
    else if (disableFlagForSubQuery == true) {
        $('#subQueryColumns-' + subOperatorCount).attr("disabled", true);
    }
    if (subOperatorId == "subQueryValueSelect-" + subOperatorCount) {
        if (subOperatorValue != "") {
            $('#subQueryValueText-' + subOperatorCount).attr("disabled", true);
            $('#subQueryValueText-' + subOperatorCount).val("");
        } 
        else {
            $('#subQueryValueText-' + subOperatorCount).removeAttr("disabled");
        }
    }
    if (subOperatorId == "subQueryValueText-" + subOperatorCount) {
        if (subOperatorValue != "") {
            $('#subQueryValueSelect-' + subOperatorCount).attr("disabled", true);
            $('#subQueryValueSelect-' + subOperatorCount).prop('selectedIndex', 0);
        } 
        else {
            
            $('#subQueryValueSelect-' + subOperatorCount).removeAttr("disabled");
        }
    }

});

/*
     * This method is used to validate while entering data in the input fields
     */
$(document).on('keyup', '.operatorInSubQuery', function() {
    
    var seletedKeyUpId = this.id;
    var seletedKeyUpCount = seletedKeyUpId.split('-');
    seletedKeyUpCount = seletedKeyUpCount[1];
    var seletedKeyUpValue = this.value;
    
    if (seletedKeyUpId == 'subQueryValueText-' + seletedKeyUpCount) {
        if (seletedKeyUpValue != "") {
            $('#subQueryValueSelect-' + seletedKeyUpCount).attr("disabled", true);
        } 
        else {
            if ($('#subQueryOperators-' + seletedKeyUpCount).val() == 'like' || $('#subQueryOperators-' + seletedKeyUpCount).val() == 'not like') {
                $('#subQueryValueSelect-' + seletedKeyUpCount).attr("disabled", true);
            } 
            else {
                $('#subQueryValueSelect-' + seletedKeyUpCount).removeAttr("disabled");
            }
        }
    }
    if ($('#subQueryCol-' + seletedKeyUpCount).val() != "" && $('#subQueryOperators-' + seletedKeyUpCount).val() != "") {
        $('#subQueryColumns-' + seletedKeyUpCount).removeAttr("disabled");
        $('#subQueryValueSelect-' + seletedKeyUpCount).prop('selectedIndex', 0);
    } 
    else {
        
        $('#subQueryColumns-' + seletedKeyUpCount).attr("disabled", true);
    }
});
/*
     * Logic End Here
     */


/*
     * This method is used to add the dynamic rows to the table while changing logical operator to  "and/or"
     * Logic Start Here 
     */
$(document).off('change', '.subQueryCountyTableColumns').on('change', '.subQueryCountyTableColumns', function() {
    
    var logicalOperatorId = this.id;
    var logicalOperatorCount = logicalOperatorId.split('-');
    logicalOperatorCount = logicalOperatorCount[1];
    var logicalOperatorValue = this.value;
    
    var subQueryTableRowSize = $("#subQueryInWhereClause").find("tr").not("thead tr").length;
    var subQueryIndexPosition = $(this).closest("tr").index();
    if (logicalOperatorId.match('subQueryColumns') && subQueryIndexPosition == (subQueryTableRowSize - 1)) {
        if ((logicalOperatorValue == "And" || logicalOperatorValue == "Or") && logicalOperatorId.match("subQueryColumns-" + logicalOperatorCount)) {
            dynamicRowsTableForSubQuery();
        }
        function dynamicRowsTableForSubQuery() {
            subQueryTableCount++;
            
            var subQueryColumnData = "<tr id = 'subQueryClosebutton-" + subQueryTableCount + "'><td><input type='checkbox' id='subQueryRowStart-" + subQueryTableCount + "' class='checkBoxStyleForSubQuery' style='margin: 0px 14px'/></td>" + 
            "<td><label class='control-label'>  </label></td><td><select id='subOtherFunction-" + subQueryTableCount + "' class='form-control countyTableColumns'>" + 
            "<option value=''>--Please Select--</option><option value='Convert'>Convert</option><option value='Len'>Length</option></select></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><select id='subQueryCol-" + subQueryTableCount + "' class='form-control subQueryCountyTableCol'>" + 
            "<option value=''>--Please Select--</option></select></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><select id='subQueryOperators-" + subQueryTableCount + "' class='form-control  operatorInSubQuery'>" + 
            "<option value=''>--Please Select--</option><option value='='>=</option><option value='<>'><></option>" + 
            "<option value='like'>like</option><option value='not like'>not like</option><option value='<'><</option>" + 
            "<option value='>'>></option><option value='<='><=</option><option value='>='>>=</option>" + 
            "<option value='is null'>is null</option><option value='is not null'>is not null</option><option value='in'>in</option>" + 
            "<option value='not in'>not in</option><option value='between'>between</option></select></td>" + 
            "<td><label class='control-label'>  </label></td><td><select id='subQueryMatchCondition-" + subQueryTableCount + "' class='form-control countyTableColumns' disabled>" + 
            "<option value=''>--Please Select--</option><option value='Start'>Start</option><option value='End'>End</option>" + 
            "<option value='Between'>Between</option></select></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><select id='subQueryValueSelect-" + subQueryTableCount + "' class='form-control subQueryCountyTableValues'>" + 
            "<option value=''>--Please Select--</option></select></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><input type='text' id='subQueryValueText-" + subQueryTableCount + "' class='form-control operatorInSubQuery'></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><select id='subQueryColumns-" + subQueryTableCount + "' class='form-control subQueryCountyTableColumns' disabled>" + 
            "<option value=''>--Please Select--</option><option value='And'>And</option><option value='Or'>Or</option>" + 
            "<option value='Not'>Not</option></select></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><input type='checkbox' id='subQueryRowEnd-" + subQueryTableCount + "' class='checkBoxStyleForSubQuery' style='margin: 0px 14px'/></td>" + 
            "<td><label class='control-label'></label></td>" + 
            "<td><button class='btn btn-warning iframeForinnerSubquery' id='innerSubQueryButton-" + subQueryTableCount + "' style='display: none;'>subQuery</button></td>" + 
            "<td><div class = 'subQueryClose' id = 'subQueryClosebutton-" + subQueryTableCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></div></td></tr>";
            
            $("#subQueryInWhereClause").append(subQueryColumnData);
            var subQueryColumnOptions = $("#subQueryCol-0").html();
            $('#subQueryCol-' + subQueryTableCount).empty();
            $('#subQueryCol-' + subQueryTableCount).html(subQueryColumnOptions);
            $('#subQueryValueSelect-' + subQueryTableCount).empty();
            $('#subQueryValueSelect-' + subQueryTableCount).html(subQueryColumnOptions);
        }
    }
});

/*
     * Logic End Here
     */


/*
     * This method is used to validate the join conditions based on the count of check boxes
     * 
     * Logic Start Here
     */

$(document).off('change', '.checkBoxStyleForSubQuery').on('change', '.checkBoxStyleForSubQuery', function() {
    
    var subQueryRowStart = false;
    var subQueryRowEnd = false;
    var subQueryAlertErrorForCount = false;
    var subQueryAlertError = false;
    var subQueryStartCount = 0;
    var subQueryEndCount = 0;
    var subQueryAlertFlag = false;
    for (var subCheckBox = 0; subCheckBox <= subQueryTableCount; subCheckBox++) {
        var subCheckBoxperv = subCheckBox - 1;
        var subCheckBoxNext = subCheckBox + 1;
        subQueryRowStart = $('#subQueryRowStart-' + subCheckBox).prop("checked");
        if (subQueryRowStart == true) {
            subQueryStartCount++;
        }
        subQueryRowEnd = $('#subQueryRowEnd-' + subCheckBox).prop("checked");
        if (subQueryRowEnd == true) {
            subQueryEndCount++;
            
            if (subQueryStartCount < subQueryEndCount) {
                subQueryAlertFlag = true
            }
        }
        if (subQueryRowEnd == true && subCheckBox == 0 && subQueryRowStart == false) {
            subQueryAlertError = true;
        }
        var rowEndNext = $('#subQueryRowEnd-' + subCheckBoxperv).prop("checked");
        if (subQueryRowEnd == true && rowEndNext == true && subQueryRowStart == false) {
            subQueryAlertError = true;
        }
        var subQueryRowStartNext = $('#subQueryRowStart-' + subCheckBoxNext).prop("checked");
        if (subQueryRowStart == true && subQueryRowEnd == false && subQueryRowStartNext == true) {
            subQueryAlertError = true;
        }
    }
    if (subQueryStartCount == subQueryEndCount) {
        $(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass('ui-state-disabled');
        subQueryAlertErrorForCount = false;
        subQueryStartCount = 0;
        subQueryEndCount = 0;
    } 
    else if (subQueryStartCount != subQueryEndCount) {
        $("#alertForLikeOperatorSubQuery").hide();
        $("#alertForInOperatorSubQuery").hide();
        $("#alertWhileFieldIsEmptySubQuery").empty();
        $("#alertWhileFieldIsEmptySubQuery").show();
        $("#alertWhileFieldIsEmptySubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
        $("#alertWhileFieldIsEmptySubQuery").effect("highlight", 100);
        $(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
        subQueryAlertErrorForCount = true;
        subQueryStartCount = 0;
        subQueryEndCount = 0;
    }
    if (subQueryAlertError == true || subQueryAlertFlag == true) {
        $("#alertForLikeOperatorSubQuery").hide();
        $("#alertWhileFieldIsEmptySubQuery").empty();
        $("#alertWhileFieldIsEmptySubQuery").show();
        $("#alertWhileFieldIsEmptySubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
        $("#alertWhileFieldIsEmptySubQuery").effect("highlight", 100);
        $(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
    }
    if (subQueryAlertErrorForCount == false && subQueryAlertError == false && subQueryAlertFlag == false) {
        $("#alertForLikeOperatorSubQuery").hide();
        $("#alertForInOperatorSubQuery").hide();
        $("#alertWhileFieldIsEmptySubQuery").empty();
        $("#alertWhileFieldIsEmptySubQuery").hide();
        $(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass('ui-state-disabled');
    }
});

/*
     * Logic End Here
     */

/*
 * This method is used to disable or enable the having function value select and text fields
 * Logic start here
 */

var isNullCheckFlagForSubQueryHaving = false;
$(document).off('change', '.subQueryCountyTableFunction').on('change', '.subQueryCountyTableFunction', function() {
    
    var havingValueSelectFieldId = this.id;
    var havingValueSelectFieldValues = this.value;
    
    if (havingValueSelectFieldId.match("subQueryHavingOperator")) {
        if (havingValueSelectFieldValues == "like" || havingValueSelectFieldValues == "not like") {
            $("#alertWhileFieldIsEmptySubQuery").hide();
            $("#alertForLikeOperatorSubQuery").empty();
            $("#alertForLikeOperatorSubQuery").show();
            $("#alertForLikeOperatorSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please add '%' in textfield as per your requirement");
            $("#alertForLikeOperatorSubQuery").effect("highlight", 100);
            $("#subQueryHavingValueSelect").attr("disabled", true);
            $('#subQueryHavingValueSelect').prop('selectedIndex', 0);
        } 
        else {
            $("#alertForLikeOperatorSubQuery").empty();
            $("#alertForLikeOperatorSubQuery").hide();
            $('#subQueryHavingValueSelect').removeAttr("disabled");
        }
        
        if (havingValueSelectFieldValues == "is null" || havingValueSelectFieldValues == "is not null") {
            isNullCheckFlagForSubQueryHaving = true;
        } 
        else {
            isNullCheckFlagForSubQueryHaving = false;
        }
        if (isNullCheckFlagForSubQueryHaving == true) {
            $("#subQueryHavingValueSelect").attr("disabled", true);
            $('#subQueryHavingValueSelect').prop('selectedIndex', 0);
            $("#subQueryHavingValueText").attr("disabled", true);
            $("#subQueryHavingValueText").val("");
        } 
        else if (isNullCheckFlagForSubQueryHaving == false) {
            if ($('#subQueryHavingValueSelect').val() != "" && $("#subQueryHavingValueText").val() != "") {
                $('#subQueryHavingValueSelect').removeAttr("disabled");
                $("#subQueryHavingValueText").removeAttr("disabled");
                $('#subQueryHavingValueSelect').prop('selectedIndex', 0);
                $("#subQueryHavingValueText").val("");
            } 
            else if ($('#subQueryHavingValueSelect').val() == "" && $("#subQueryHavingValueText").val() != "") {
                $("#subQueryHavingValueText").removeAttr("disabled");
            } 
            else if ($('#subQueryHavingValueSelect').val() != "" && $("#subQueryHavingValueText").val() == "") {
                $("#subQueryHavingValueSelect").removeAttr("disabled");
            } 
            
            else {
                if (havingValueSelectFieldValues == "like" || havingValueSelectFieldValues == "not like" || havingValueSelectFieldValues == "in") {
                    
                    if (havingValueSelectFieldValues == "like" || havingValueSelectFieldValues == "not like") {
                        $("#subQueryHavingValueSelect").attr("disabled", true);
                        $('#subQueryHavingValueSelect').prop('selectedIndex', 0);
                    } 
                    else if (havingValueSelectFieldValues == "in") {
                        $("#subQueryHavingValueSelect").attr("disabled", true);
                    }
                } 
                else {
                    $("#subQueryHavingValueSelect").removeAttr("disabled");
                }
                $("#subQueryHavingValueText").removeAttr("disabled");
            
            }
        }
    }
    if (havingValueSelectFieldId == "subQueryHavingValueSelect") {
        if (havingValueSelectFieldValues != "") {
            $('#subQueryHavingValueText').attr("disabled", true);
            $('#subQueryHavingValueText').val("");
        } 
        else {
            $('#subQueryHavingValueText').removeAttr("disabled");
        }
    }
    if (havingValueSelectFieldId == "subQueryHavingValueText") {
        if (havingValueSelectFieldValues != "") {
            $('#subQueryHavingValueSelect').attr("disabled", true);
            $('#subQueryHavingValueSelect').prop('selectedIndex', 0);
        } 
        else {
            $('#subQueryHavingValueSelect').removeAttr("disabled");
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

$(document).on('keyup', '.HavingValueTextForSubQuery', function() {
    
    var havingValueTextId = this.id;
    
    var havingValueTextValue = this.value;
    if (havingValueTextId == 'subQueryHavingValueText') {
        if (havingValueTextValue != "") {
            $('#subQueryHavingValueSelect').attr("disabled", true);
        } 
        else {
            if ($('#subQueryHavingOperator').val() == 'like' || $('#subQueryHavingOperator').val() == 'not like') {
                $('#subQueryHavingValueSelect').attr("disabled", true);
            } 
            else {
                $('#subQueryHavingValueSelect').removeAttr("disabled");
            }
        }
    }
});
/*
 * Logic End Here
 */

/*
     * This method is used to close the current "tr" while clicking on the close button
     * 
     * Logic Start Here
     */
$(document).off('click', '.subQueryClose').on('click', '.subQueryClose', function() {
    var subQueryCloseButtonId = this.id;
    $('#' + subQueryCloseButtonId).remove();
    subQueryCloseButtonId = subQueryCloseButtonId.split("-");
    var subQueryCloseButtonCount = subQueryCloseButtonId[1];
    subQueryCloseButtonCount = subQueryCloseButtonCount - 1;
    $('#subQueryColumns-' + subQueryCloseButtonCount).prop('selectedIndex', 0);
});

/*
 * Logic End Here
 */


$(document).off("change", "#seletedTableNames").on("change", "#seletedTableNames", function() {
    
    var option = "<option value = ''>--Please Select--</option>";
    var tableNames = $('#seletedTableNames').val();
    
    var countyTableNames = "";
    
    if (tableNames != null  && tableNames != "") {
        for (var i = 0; i < tableNames.length; i++) {
            countyTableNames = countyTableNames + "'" + tableNames[i] + "',";
        }
        countyTableNames = countyTableNames.slice(0, -1);
        
        alert(countyTableNames);
        
        var data = "countyTableNames=" + countyTableNames;
        
        $('.subqueryDefaultColumns').multiselect('refresh');
        $('.subqueryDefaultColumns option').each(function(index, option) {
            $(option).remove();
        });
        
        $.ajax({
            type: "POST",
            url: 'getCountyTableColumnsForJoins',
            async: false,
            data: {
                'countyTableNames': countyTableNames
            },
            success: function(response) {
                var j = 0;
                $.each(response, function(index, value) {
                    if (tableNames.length > 1) {
                        for (var i = 0; i < value.length; i++) {
                            var columnNameValueAlias = value[i].columnName + "-" + subAliasArray[j];
                            option += '<option value="' + columnNameValueAlias + '">' + value[i].columnName + "-" + tableNames[j] + '</option>';
                        }
                        j++;
                    } 
                    else {
                        for (var i = 0; i < value.length; i++) {
                            option += '<option value="' + value[i].columnName + '">' + value[i].columnName + '</option>';
                        }
                    }
                    $('.subqueryDefaultColumns').html(option);
                    $("#subQueryCol-0").html(option);
                    $("#subQueryValueSelect-0").html(option);
                    $("#columnsForSubQueryHavingBy").html(option);
                    $("#subQueryHavingValueSelect").html(option);
                });
            }
        });
    }
    $('.subqueryDefaultColumns').multiselect('rebuild');
});

var joinStructureSubTableCount = 0;
$(document).off('click', '#addRowForOrderByInSubQuery').on('click', '#addRowForOrderByInSubQuery', function() {
    
    joinStructureSubTableCount++;
    
    var joinStructureAddRow = "<tr class = 'orderBy' id = 'closeButtonSubTableColumn-" + joinStructureSubTableCount + "'><td><label class='control-label'> </label></td><td><label class='control-label'>Types :</label></td><td><select id='orderBySubQueryColumnsType-" + joinStructureSubTableCount + "' class='form-control subQueryCountyTableColumns' >" + 
    "<option value=''>--Please Select--</option><option value='ASC'>ASC</option><option value='DESC'>DESC</option></select></td>" + 
    "<td><label class='control-label'>Columns :</label></td>" + 
    "<td><select multiple id='columnsForSubQueryOrderBy-" + joinStructureSubTableCount + "' class='form-control groupByOrderByHavingFieldsForSubQueryOrder' >" + 
    "<option value=''>--Please Select--</option></select></td>" + 
    "<td><div class = 'closeButtonTableColumnClass' id = 'closeButtonSubTableColumn-" + joinStructureSubTableCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></div></td></tr>";
    $("#groupByOrderByHavingFieldsForSubQuery").append(joinStructureAddRow);
    var optionValues = $("#columnsForSubQueryOrderBy-0").html();
    $("#columnsForSubQueryOrderBy-" + joinStructureTableCount).append("");
    $("#columnsForSubQueryOrderBy-" + joinStructureTableCount).append(optionValues);
    $('.groupByOrderByHavingFieldsForSubQueryOrder').multiselect({
        includeSelectAllOption: true,
        maxHeight: 150,
        nonSelectedText: '--Please Select--',
        disableIfEmpty: true,
        enableCaseInsensitiveFiltering: true
    });

});
