var app = angular.module('gridapp', ['ngTouch', 'ui.grid', 'ngAnimate', 'ui.grid.resizeColumns', 'ui.grid.edit']);
app.controller('gridcontroller', ['$scope', '$http', '$anchorScroll', function($scope, $http, $anchorScroll) {
    
    var tableCount = 0;
    
    var cancatColumns=sessionStorage.getItem("fieldQuery");
    var columnValues = localStorage.getItem("optionValues");
    var columnOptions = localStorage.getItem("optionsToShow");
    $("#tableNamesSelected").val(localStorage.getItem("tableNamesSelected"));
    
    var selectedColumnValues = [];
    var selectedColumnOptions = [];
    if (columnValues.indexOf(',') > 0) {
        selectedColumnValues = columnValues.split(',');
    }
    if (columnOptions.indexOf(',') > 0) {
        selectedColumnOptions = columnOptions.split(',');
    }
    
    var opt = "<option value = ''>--Please Select--</option>";
    for (var i = 0; i < selectedColumnOptions.length; i++) {
        opt += '<option value="' + selectedColumnValues[i] + '">' + selectedColumnOptions[i] + '</option>';
    }
    
    cancatColumnsoption=cancatColumns.split("-");
    cancatColumnsValue=cancatColumnsoption[0];
    opt=opt+'<option value="' +cancatColumnsValue+ '">' +cancatColumns+ '</option>';
    $('#value-0').html(opt);
    $('#col-0').html(opt);
    $("#columnsForMainQueryGroupBy").html(opt);
    $("#columnsForMainQueryHavingBy").html(opt);
    $("#mainQueryHavingByValueSelect").html(opt);
    $("#columnsForMainQueryOrderBy-0").html(opt);
    
    
    var disableFlag = false;
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
                $("#alertForLikeOperator").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> Please add '%' as prefix or suffix");
                $("#alertForLikeOperator").effect("highlight", 100);
                $('#value-' + count).prop('selectedIndex', 0);
                $("#matchCondition-" + count).removeAttr("disabled");
            } 
            else {
                $("#alertForLikeOperator").empty();
                $("#alertForLikeOperator").hide();
                $("#alertForInOperator").empty();
                $("#alertForInOperator").hide();
                $('#value-' + count).removeAttr("disabled");
                $("#matchCondition-" + count).attr("disabled", true);
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
                    
                    if (operatorValue == "like" || operatorValue == "not like" || operatorValue == "in" || operatorValue == "not in") {
                        
                        if (operatorValue == "like" || operatorValue == "not like") {
                            /*                            $("#value-" + count).attr("disabled", true);
                            $('#value-' + count).prop('selectedIndex', 0);
*/$('#subQueryButton-' + count).css('display', 'none');
                        } 
                        else if (operatorValue == "in" || operatorValue == "not in") {
                            $("#value-" + count).attr("disabled", true);
                            $('#subQueryButton-' + count).css('display', 'block');
                        }
                    } 
                    else {
                        /*$("#value-" + count).removeAttr("disabled");*/
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
    
    $(document).on('keyup', '.form-control', function() {
        
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
    
    $(document).off('change', '.countyTableColumns').on('change', '.countyTableColumns', function() {
        var id = this.id;
        var count = id.split('-');
        count = count[1];
        var value = this.value;
        
        var tableRowSize = $("#plannerTable").find("tr").not("thead tr").length;
        var indexPosition = $(this).closest("tr").index();
        if (id.match('columns') && indexPosition == (tableRowSize - 1)) {
            if ((value == "And" || value == "Or") && id.match("columns-" + count)) {
                dynamicRowsForTable();
            }
            function dynamicRowsForTable() {
                tableCount++;
                
                var columnData = "<tr id = 'closebutton-" + tableCount + "'>" + 
                "<td><input type='checkbox' id='rowStart-" + tableCount + "' class = 'checkBoxStyle' style = 'margin: 0px 14px'/></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><select id='otherFunction-" + tableCount + "' class='form-control countyTableColumns'>" + 
                "<option value=''>--Please Select--</option><option value='Convert'>Convert</option>" + 
                "<option value='Len'>Length</option></select></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><select id='col-" + tableCount + "' class='form-control countyTableCol'></select></td>" + 
                "<td><label class='control-label'>  </label></td><td><select id='operators-" + tableCount + "'  class='form-control operatorInMainQuery'><option value=''>--Please Select--</option><option value='='>=</option><option value='<>'><></option><option value='like'>like</option><option value='not like'>not like</option><option value='<'><</option><option value='>'>></option><option value='>='>>=</option><option value='<'><=</option><option value='is null'>is null</option><option value='is not null'>is not null</option><option value='in'>in</option><option value='not in'>not in</option><option value='between'>between</option></select></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><select id='matchCondition-" + tableCount + "' class='form-control countyTableColumns' disabled>" + 
                "<option value=''>--Please Select--</option><option value='Start'>Start</option>" + 
                "<option value='End'>End</option><option value='Between'>Between</option></select></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><select id='value-" + tableCount + "' class='form-control CountyTableValues' ></select></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><input type='text' id='valueText-" + tableCount + "' class='form-control'></td><td><label class='control-label'>  </label></td>" + 
                "<td><select id='columns-" + tableCount + "' class='form-control countyTableColumns' disabled>" + 
                "<option value=''>--Please Select--</option><option value='And'>And</option><option value='Or'>Or</option><option value='Not'>Not</option></select></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><input type='checkbox'  id='rowEnd-" + tableCount + "' class = 'checkBoxStyle' style = 'margin: 0px 14px'/></td>" + 
                "<td><label class='control-label'>  </label></td>" + 
                "<td><button class='btn btn-warning iframeForSubquery' id = 'subQueryButton-" + tableCount + "' style='display: none;'>subQuery</button></td>" + 
                "<td><div class = 'closeButton' id = 'closebutton-" + tableCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></div></td></tr>"
                
                $("#plannerTable").append(columnData);
                var optionValues = $("#col-0").html();
             //   optionValues=optionValues+'<option value="' +cancatColumns+ '">' +cancatColumns+ '</option>';;
                $("#col-" + tableCount).append("");
                $("#col-" + tableCount).append(optionValues);
                $("#value-" + tableCount).append("");
                $("#value-" + tableCount).append(optionValues);
              
            }
        }
    });
    
    
    $(document).on('click', '#runQueryButton', function() {
        
        
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
        
        var startValue = [];
        var endValue = [];
        var otherFunction = [];
        var matchCondition = [];
        var buildQueryString = '';
        
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
                    tableNamesSelected = "";
                },
                error: function(data) {
                
                }
            });
        }
        /*setTimeout(function() {*/
        if (tableNamesSelectedValue.length > 2) {
            for (var i = 0; i < tableNamesSelectedValue.length - 1; i++) {
                selectedTableNamesWithAlias[i] = tableNamesSelectedValue[i] + " " + aliasArray[i];
                tableArr[i] = tableNamesSelectedValue[i] + "|" + aliasArray[i];
                tableString = tableString + tableNamesSelectedValue[i] + " " + aliasArray[i] + " (nolock) " + ', ';
                alert(tableNamesSelectedValue[i]);
                console.log(tableNamesSelectedValue[i]);
            }
        } 
        else {
            for (var i = 0; i < tableNamesSelectedValue.length - 1; i++) {
                selectedTableNamesWithAlias[i] = tableNamesSelectedValue[i];
                tableArr[i] = tableNamesSelectedValue[i];
                alert(tableNamesSelectedValue[i]);
                console.log(tableNamesSelectedValue[i]);
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
            /*console.log(tableNamesSelected);*/
            
            /*var columnValues = $("#columnValues").val();*/
            //columnValues = columnValues.split(',');
            if (tableArr.length > 1) {
                for (var j = 0; j < selectedColumnValues.length; j++) {
                    if (selectedColumnValues[j].indexOf(tableName) > 0) {
                        var columnInfo = selectedColumnValues[j].split("-");
                        colArr[colCount] = columnInfo[0];
                        //columnInfo[0] = columnInfo[0].slice(1);
                        var colConcatName = tableAlias + "." + "[" + columnInfo[0] + "] [" + tableAlias + "_" + columnInfo[0] + "]";
                        colString = colString + colConcatName + ', ';
                    }
                }
            } 
            else {
                for (var j = 0; j < selectedColumnValues.length; j++) {
                    if (selectedColumnValues[j].indexOf(tableName) > 0) {
                        var columnInfo = selectedColumnValues[j].split("-");
                        colArr[colCount] = columnInfo[0];
                        /*columnInfo[0] = columnInfo[0].slice(1);*/
                        var colConcatName = "[" + columnInfo[0] + "]";
                        colString = colString + colConcatName + ', ';
                    }
                }
                var concatColumnsData = sessionStorage.getItem("fieldQuery");
                if (concatColumnsData != "" && concatColumnsData != null) {
                	concatColumnsData=concatColumnsData.split("-");
                	concatColumnsDataValue=concatColumnsData[0];
                    var colConcatName = concatColumnsDataValue + " as FieldlegalDiscription  ";
                    colString = colString + colConcatName;
                }
            }
        }
        tableString.slice(0, -2);
        colString.slice(0, -2);
        
        var mainQueryGroupByValue = [];
        $('#columnsForMainQueryGroupBy :selected').each(function(l3, subGroupName) {
            if (tableArr.length > 1) {
                mainQueryGroupByValue[mainQueryGroupCount] = $(subGroupName).val();
                var mainQueryTableAliasGroup = mainQueryGroupByValue[mainQueryGroupCount].split("-");
                var groupColMainConcat = mainQueryTableAliasGroup[1] + "." + "[" + mainQueryTableAliasGroup[0] + "]";
                mainQueryGroupColString = mainQueryGroupColString + groupColMainConcat + ', ';
                mainQueryGroupCount = mainQueryGroupCount + 1;
            } 
            else {
                mainQueryGroupByValue[mainQueryGroupCount] = $(subGroupName).val();
                var mainQueryTableAliasGroup = mainQueryGroupByValue[mainQueryGroupCount].split("-");
                var groupColMainConcat = "[" + mainQueryTableAliasGroup[0] + "]";
                mainQueryGroupColString = mainQueryGroupColString + groupColMainConcat + ', ';
                mainQueryGroupCount = mainQueryGroupCount + 1;
            }
        
        });
        mainQueryGroupColString = mainQueryGroupColString.slice(0, -2);
        
        var mainQueryHavingFunctionValue = $("#functionsForMainQuery").val();
        
        var mainQueryHavingColumnName = $("#columnsForMainQueryHavingBy").val();
        if (mainQueryHavingColumnName != "" && mainQueryHavingColumnName != null  && tableArr.length > 1) {
            mainQueryHavingColumnName = mainQueryHavingColumnName.split('-');
            mainQueryHavingColumnName = mainQueryHavingColumnName[1] + '.' + "[" + mainQueryHavingColumnName[0] + "]";
        } 
        else if (mainQueryHavingColumnName != "" && mainQueryHavingColumnName != null ) {
            mainQueryHavingColumnName = mainQueryHavingColumnName.split('-');
            mainQueryHavingColumnName = "[" + mainQueryHavingColumnName[0] + "]";
        }
        
        var mainQueryHavingOperator = $("#mainQueryHavingByOperator").val();
        
        var mainQueryHavingValueSelect = $("#mainQueryHavingByValueSelect").val();
        if (mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != null  && tableArr.length > 1) {
            mainQueryHavingValueSelect = mainQueryHavingValueSelect.split('-');
            mainQueryHavingValueSelect = mainQueryHavingValueSelect[1] + '.' + "[" + mainQueryHavingValueSelect[0] + "]";
        } 
        else if (mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != null  && tableArr.length > 1) {
            mainQueryHavingValueSelect = mainQueryHavingValueSelect.split('-');
            mainQueryHavingValueSelect = "[" + mainQueryHavingValueSelect[0] + "]";
        }
        var mainQueryHavingValueText = $("#mainQueryHavingByValueText").val();
        
        for (var i = 0; i <= joinStructureTableCount; i++) {
            var mainQueryOrderByValueType = $('#mainQueryOrderByColumnType-' + i).val();
            var mainQueryOrderByValues = [];
            if (mainQueryOrderByValueType != "" && mainQueryOrderByValueType != undefined) {
                $('#columnsForMainQueryOrderBy-' + i + ' :selected').each(function(l2, subOrderName) {
                    if (tableArr.length > 1) {
                        mainQueryOrderByValues[mainQueryOrderCount] = $(subOrderName).val();
                        var mainQueryTableAliasOrder = mainQueryOrderByValues[mainQueryOrderCount].split("-");
                        var mainColConcat = mainQueryTableAliasOrder[1] + "." + "[" + mainQueryTableAliasOrder[0] + "]";
                        mainQueryOrderColString = mainQueryOrderColString + mainColConcat + ', ';
                        mainQueryOrderCount = mainQueryOrderCount + 1;
                    } else {
                        mainQueryOrderByValues[mainQueryOrderCount] = $(subOrderName).val();
                        var mainQueryTableAliasOrder = mainQueryOrderByValues[mainQueryOrderCount].split("-");
                        var mainColConcat = "[" + mainQueryTableAliasOrder[0] + "]";
                        mainQueryOrderColString = mainQueryOrderColString + mainColConcat + ', ';
                        mainQueryOrderCount = mainQueryOrderCount + 1;
                    }
                    
                    mainQueryOrderColString = mainQueryOrderColString.slice(0, -2);
                    mainQueryOrderColString = mainQueryOrderColString + " " + mainQueryOrderByValueType + ", ";
                
                });
            }
        
        }
        ;
        mainQueryOrderColString = mainQueryOrderColString.slice(0, -2);
        
        if (joinsData != "" && joinRelation != undefined) {
            for (var t = 0; t < tableArr.length; t++) {
                var columnAlias = selectedTableNames[t] + ".";
                var columnAliasArr = aliasArray[t] + ".";
                joinCondtion = joinCondtion.replace(selectedTableNames[t], selectedTableNamesWithAlias[t]);
                for(var i = 0;i<6;i++){
                	joinCondtion = joinCondtion.replace(columnAlias, columnAliasArr);
				}
            }
            queryString = queryString + 'SELECT ' + colString.slice(0, -2) + ' FROM ' + joinCondtion;
            buildQueryString = buildQueryString + 'SELECT id,' + colString.slice(0, -2) + ' FROM ' + joinCondtion;
        } 
        else {
        	
        	if(tableArr.length > 1){
				var message = "Please create the join condition and try agian";
				var status="Warning";
        		var style="alert-warning";
				showalertMsg(message,status,style);
        		/*queryString = queryString+'SELECT '+colString.slice(0, -2) +' FROM '+tableString.slice(0,-1) ;*/
				return false;
			}
			else{
				queryString = queryString + 'SELECT ' + colString.slice(0, -2) + ' FROM ' + tableString.slice(0, -2);
				buildQueryString = buildQueryString + 'SELECT id,' + colString.slice(0, -2) + ' FROM ' + tableString.slice(0, -2);
			}
        	
        	/*var msg="Please create join condition for selected tables and try again";
			var status="Error";
			var style="alert-danger";
			showalertMsg(msg,status,style);
			return false;*/
            /*queryString = queryString + 'SELECT ' + colString.slice(0, -2) + ' FROM ' + tableString.slice(0, -2);*/
        }
        for (var m = 0; m <= tableCount; m++) {
            alertFlag = true;
            var column = $('#col-' + m).val();
            if (tableArr.length > 1) {
                if (column != undefined && column != "") {
                    column = column.split('-');
                    columns.push(column[1] + '.' + "[" + column[0] + "]");
                } 
                else if (column == "") {
                    columns.push("null");
                }
            } 
            else {
                if (column != undefined && column != "") {
                    column = column.split('-');
                    if (column[0].indexOf("+") > 0) {
                        columns.push(column[0]);
                    } 
                    else {
                        columns.push("[" + column[0] + "]");
                    }
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
            var startValueString = $('#rowStart-' + m).prop("checked");
            
            if (startValueString != undefined && startValueString != "") {
                startValue.push(startValueString);
            } 
            else if (startValueString == false) {
                startValue.push('');
            }
            
            var endValueString = $('#rowEnd-' + m).prop("checked");
            
            if (endValueString != undefined && endValueString != "") {
                endValue.push(endValueString);
            } 
            else if (endValueString == false) {
                endValue.push('');
            }
            
            var otherFunctionString = $('#otherFunction-' + m).val();
            
            if (otherFunctionString != undefined && otherFunctionString != "") {
                otherFunction.push(otherFunctionString);
            } 
            else if (otherFunctionString == "") {
                otherFunction.push("null");
            }
            var matchConditionString = $("#matchCondition-" + m).val();
            if (matchConditionString != undefined && matchConditionString != "") {
                matchCondition.push(matchConditionString);
            } 
            else if (matchConditionString == "") {
                matchCondition.push("null");
            }
            var selectedValue = $("#value-" + m).val();
            if (tableArr.length > 1) {
                if (selectedValue != undefined && selectedValue != "") {
                    selectedValue = selectedValue.split("-");
                    valueSelect.push(selectedValue[1] + '.' + "[" + selectedValue[0] + "]");
                } 
                else if (selectedValue == "") {
                    valueSelect.push("null");
                }
            } 
            else {
                if (selectedValue != undefined && selectedValue != "") {
                    selectedValue = selectedValue.split("-");
                    if (selectedValue[0].indexOf("+") > 0) {
                        valueSelect.push(selectedValue[0]);
                    } 
                    else {
                        valueSelect.push("[" + selectedValue[0] + "]");
                    }
                } 
                else if (selectedValue == "") {
                    valueSelect.push("null");
                }
            }
            
            if (columns[m] == "null" && operators[m] == "null" && (valueSelect[m] == "null" || value[m] == "''")) {
                alertFlag = false;
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
            $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> Please enter all the required fields");
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
                $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter all the required fields at GROUP_BY / ORDER_BY clause");
                $("#alertWhileFieldIsEmpty").effect("highlight", 10);
            }
        }
        if (alertFlag == false && mainQueryAlertFlagForGroupnOrder == false) {
            
            for (var n = 0; n <= matchCondition.length; n++) {
                /*var startValue = $('#rowStart-' + n).prop("checked");
                var endValue = $('#rowEnd-' + n).prop("checked");
                var otherFunction = $('#otherFunction-' + n).val();
                var matchCondition = $("#matchCondition-" +n).val();*/
                /*if(matchCondition == undefined){
                	matchCondition = '';
                }*/
                
                /*if(otherFunction == undefined){
                	otherFunction = ''
                }*/
                /*else{*/
                if (otherFunction[n] == 'Convert') {
                    otherFunction[n] = " Convert(float," + columns[n] + ") ";
                } 
                else if (otherFunction[n] == 'Len') {
                    otherFunction[n] = " Len(" + columns[n] + ") ";
                }
                else{
                	otherFunction[n] = columns[n];
                }
                /*}*/
                var startingBrackets = "";
                var endingBrackets = "";
                if (startValue[n] == true) {
                    startingBrackets = " ( ";
                }
                if (endValue[n] == true) {
                    endingBrackets = " ) ";
                }
                if (n == 0 && valueSelect[n] != undefined && valueSelect[n] != "" && valueSelect[n] != "null") {
                    if (operators[n] == "is null" || operators[n] == "is not null") {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                        buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                        
                    } 
                    else if (operators[n] == "like" || operators[n] == "not like") {
                        if (matchCondition[n] == 'Start') {
                            matchCondition[n] = " Concat('%'," + valueSelect[n] + ") ";
                        } 
                        else if (matchCondition[n] == 'End') {
                            matchCondition[n] = " Concat(" + valueSelect[n] + ",'%') ";
                        } 
                        else if (matchCondition[n] == 'Between') {
                            matchCondition[n] = " Concat('%'," + valueSelect[n] + ",'%') ";
                        } 
                        else if (matchCondition[n] == "") {
                            matchCondition[n] = valueSelect[n];
                        }
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                        buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                    }
                    else if (operators[n] == "<" || operators[n] == ">" || operators[n] == "<=" || operators[n] == ">=" || operators[n] == "<>" || operators[n] == "=") {
                        
                            queryString = queryString + " " + 'where ' + startingBrackets + otherFunction[n] + operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + otherFunction[n] + operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                    } 
             
                    
                    /*else{
                    	queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] +  valueSelect[n] + endingBrackets;
                    }*/
                } 
                else if (n == 0 && (valueSelect[n] == undefined || valueSelect[n] == "" || valueSelect[n] == "null")) {
                    if (operators[n] == "is null" || operators[n] == "is not null") {
                        queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                        buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                    } 
                    else if (operators[n] == "like" || operators[n] == "not like") {
                        if (value[n] == "''") {
                            queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                            buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                        } else {
                            if (matchCondition[n] == 'Start') {
                                matchCondition[n] = " ('%" + value[n] + "') ";
                            } 
                            else if (matchCondition[n] == 'End') {
                                matchCondition[n] = " ('" + value[n] + "%') ";
                            } 
                            else if (matchCondition[n] == 'Between') {
                                matchCondition[n] = " ('%" + value[n] + "%') ";
                            } 
                            else if (matchCondition[n] == "null") {
                                matchCondition[n] = "'" + value[n] + "'";
                            }
                            queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                        }
                    } 
                    
                    else if (operators[n] == "in" || operators[n] == "not in") {
                        queryString = queryString + " " + 'where ' + startingBrackets + " LTRIM(RTRIM( " + columns[n] + ")) " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                        buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + " LTRIM(RTRIM( " + columns[n] + ")) " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                    } 
                    else if (operators[n] == "<" || operators[n] == ">" || operators[n] == "<=" || operators[n] == ">=" || operators[n] == "<>" || operators[n] == "=") {
                        if ($.isNumeric(value[n])) {
                            queryString = queryString + " " + 'where ' + startingBrackets + otherFunction[n] + operators[n] + " " + value[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + otherFunction[n] + operators[n] + " " + value[n] + " " + endingBrackets;
                        } 
                        else {
                            queryString = queryString + " " + 'where ' + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "')" + endingBrackets;
                            buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "')" + endingBrackets;
                        }
                    } 
                    else {
                    	if (columns[n] == "null" && operators[n] == "null") {
                    		queryString = queryString;
                    		buildQueryString = buildQueryString;
                        } 
                        else if (columns[n] != "null" && operators[n] != "null") {
                        	queryString = queryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + value[n] + endingBrackets;
                        	buildQueryString = buildQueryString + " " + 'where ' + startingBrackets + columns[n] + " " + operators[n] + " " + value[n] + endingBrackets;
                        }
                    }
                }
                if (n > 0) {
                    if ((tableColumn[n - 1] == "And" || tableColumn[n - 1] == "Or") && (valueSelect[n] == "" || valueSelect[n] == undefined || valueSelect[n] == "null")) {
                        if (operators[n] == "is null" || operators[n] == "is not null") {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + columns[n] + " " + operators[n] + " " + endingBrackets;
                        } 
                        else if (operators[n] == "in" || operators[n] == "not in") {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " LTRIM(RTRIM( " + columns[n] + ")) " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                            buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " LTRIM(RTRIM( " + columns[n] + ")) " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                        } 
                        else if (operators[n] == "<" || operators[n] == ">" || operators[n] == "<=" || operators[n] == ">=" || operators[n] == "<>" || operators[n] == "=") {
                            if ($.isNumeric(value[n])) {
                                /*queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " CONVERT(float," + columns[n] + ")" + operators[n] + " " + value[n] + " " + endingBrackets;*/
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + otherFunction[n] + operators[n] + " " + value[n] + " " + endingBrackets;
                                buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + otherFunction[n] + operators[n] + " " + value[n] + " " + endingBrackets;
                            } 
                            else {
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "') " + endingBrackets;
                                buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " ('" + value[n] + "') " + endingBrackets;
                            }
                        } 
                        else if (operators[n] == "like" || operators[n] == "not like") {
                            if (value[n] == "''") {
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                                buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " (" + value[n] + ")" + endingBrackets;
                            } else {
                                if (matchCondition[n] == 'Start') {
                                    matchCondition[n] = " ('%" + value[n] + "') ";
                                } 
                                else if (matchCondition[n] == 'End') {
                                    matchCondition[n] = " ('" + value[n] + "%') ";
                                } 
                                else if (matchCondition[n] == 'Between') {
                                    matchCondition[n] = " ('%" + value[n] + "%') ";
                                } 
                                else if (matchCondition[n] == "null") {
                                    matchCondition[n] = "'" + value[n] + "'";
                                }
                                queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                                buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                            }
                        } 
                        /*else {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + columns[n] + " " + operators[n] + " '" + value[n] + "'" + endingBrackets;
                        }*/
                    } 
                    else if ((tableColumn[n - 1] == "And" || tableColumn[n - 1] == "Or") && (valueSelect[n] != "" && valueSelect[n] != undefined && valueSelect[n] != "null")) {
                        if (operators[n] == "is null" || operators[n] == "is not null") {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + endingBrackets;
                        } 
                        else if (operators[n] == "like" || operators[n] == "not like") {
                            
                            if (matchCondition[n] == 'Start') {
                                matchCondition[n] = " Concat('%'," + valueSelect[n] + ") ";
                            } 
                            else if (matchCondition[n] == 'End') {
                                matchCondition[n] = " Concat(" + valueSelect[n] + ",'%') ";
                            } 
                            else if (matchCondition[n] == 'Between') {
                                matchCondition[n] = " Concat('%'," + valueSelect[n] + ",'%') ";
                            } 
                            else if (matchCondition[n] == "null") {
                                matchCondition[n] = valueSelect[n];
                            }
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + matchCondition[n] + " " + endingBrackets;
                        
                        }
                        else if (operators[n] == "<" || operators[n] == ">" || operators[n] == "<=" || operators[n] == ">=" || operators[n] == "<>" || operators[n] == "=") {
                            queryString = queryString + " " + tableColumn[n - 1] +" "+ startingBrackets + otherFunction[n] +" "+ operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                            buildQueryString = buildQueryString + " " + tableColumn[n - 1]+" " + startingBrackets + otherFunction[n] +" "+ operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                        } 
                        /*else {
                            queryString = queryString + " " + tableColumn[n - 1] + " " + startingBrackets + " " + columns[n] + " " + operators[n] + " " + valueSelect[n] + " " + endingBrackets;
                        }*/
                    
                    }
                    else{
                    	if ( columns[n] == "null" && operators[n] == "null") {
                    		queryString = queryString;
                    		buildQueryString = buildQueryString;
                        } 
                        else if ( columns[n] == "null" && operators[n] == "null") {
                        	queryString = queryString + " " + tableColumn[n - 1] + startingBrackets + columns[n] + " " + operators[n] + " " + value[n] + endingBrackets;
                        	buildQueryString = buildQueryString + " " +  tableColumn[n - 1] + startingBrackets + columns[n] + " " + operators[n] + " " + value[n] + endingBrackets;
                        }
                    }
                }
            }
            
            if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null  && mainQueryHavingFunctionValue != "" && mainQueryHavingColumnName != "" && mainQueryHavingOperator != "" && (mainQueryHavingValueSelect != "undefined." || mainQueryHavingValueText != "") && mainQueryOrderByValueType != null  && mainQueryOrderColString != "") {
                if (mainQueryHavingValueSelect != null  && mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != "undefined.") {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueSelect + " Order By " + mainQueryOrderColString + " " + mainQueryOrderByValueType;
                    buildQueryString = buildQueryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueSelect + " Order By " + mainQueryOrderColString + " " + mainQueryOrderByValueType; 
                } 
                else {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueText + " Order By " + mainQueryOrderColString + " " + mainQueryOrderByValueType;
                    buildQueryString = buildQueryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueText + " Order By " + mainQueryOrderColString + " " + mainQueryOrderByValueType; 
                }
            } 
            else if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null  && mainQueryHavingFunctionValue != "" && mainQueryHavingColumnName != "" && mainQueryHavingOperator != "" && (mainQueryHavingValueSelect != "undefined." || mainQueryHavingValueText != "") && mainQueryOrderByValueType == null  && mainQueryOrderColString == "") {
                if (mainQueryHavingValueSelect != null  && mainQueryHavingValueSelect != "" && mainQueryHavingValueSelect != "undefined.") {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueSelect + " ";
                    buildQueryString = buildQueryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueSelect + " "; 
                } 
                else {
                    queryString = queryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueText + " ";
                    buildQueryString = buildQueryString + " Group by " + mainQueryGroupColString + " Having " + mainQueryHavingFunctionValue + " (" + mainQueryHavingColumnName + ") " + mainQueryHavingOperator + " " + mainQueryHavingValueText + " "; 
                }
            } 
            else if (mainQueryGroupByValue != "" && mainQueryGroupByValue != null  && mainQueryHavingFunctionValue == "" && mainQueryHavingColumnName == "" && mainQueryHavingOperator == "" && (mainQueryHavingValueSelect == "undefined." || mainQueryHavingValueText == "")) {
                queryString = queryString + " Group by " + mainQueryGroupColString;
                buildQueryString = buildQueryString + " Group by " + mainQueryGroupColString;
            } 
            else if ((mainQueryGroupByValue == "" || mainQueryGroupByValue == null ) && mainQueryHavingFunctionValue == "" && mainQueryHavingColumnName == "" && mainQueryHavingOperator == "" && (mainQueryHavingValueSelect == "undefined." || mainQueryHavingValueText == "") && mainQueryOrderByValueType != null  && mainQueryOrderByValueType != "" && mainQueryOrderByValueType != undefined) {
                queryString = queryString + " Order by " + mainQueryOrderColString;
                buildQueryString = buildQueryString + " Order by " + mainQueryOrderColString; 
            }
            console.log(buildQueryString);
            $("#resultQuery").val("");
            $("#resultQuery").val(queryString);
            $("#resultQuery").attr('title', queryString);
            $("#alertWhileFieldIsEmpty").hide();
            
            $("#resultBuildQuery").val("");
            $("#resultBuildQuery").val(buildQueryString);
            
            var resultQuery = $('#resultQuery').val();
            localStorage.setItem("pageresultQuery", resultQuery);
            var specialChars = ['%', '&', '+', '#'];
            var codeNumbersForSplChars = ['%25', '%26', '%2B', '%23'];
            for (var sp = 0; sp < specialChars.length; sp++) {
                if (resultQuery.indexOf(specialChars[sp]) > 0) {
                    resultQuery = resultQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
                }
            }
            if (resultQuery != "") {
                $http.get('./executeQuery?input_query=' + resultQuery).success(function(response) {
                    $('.widget-content').hide();
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
                
                if($("#resultQuery").val() !=""){
                	$('html, body').animate({
                		scrollTop: $("#grid1").offset().top
                	});
    	       }
            }
            //  }, 10);
        }
    });
    
    $(document).off('click', '.closeButton').on('click', '.closeButton', function() {
        var id = this.id;
        $('#' + id).remove();
        id = id.split("-");
        var closeButtonCount = id[1];
        closeButtonCount = closeButtonCount - 1;
        $('#columns-' + closeButtonCount).prop('selectedIndex', 0);
    });
    
    $(document).off('click', '[data-hide]').on('click', '[data-hide]', function() {
        
        /*$(this).closest("." + $(this).attr("data-hide")).hide();*/
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
            $("#runQueryButton").removeAttr("disabled");
            $("#saveQueryButton").removeAttr("disabled");
            alertErrorForCount = false;
            startCount = 0;
            endCount = 0;
        } 
        else if (startCount != endCount) {
            /*alert("Count error");*/
            $("#alertForLikeOperator").hide();
            $("#alertForInOperator").hide();
            $("#alertWhileFieldIsEmpty").empty();
            $("#alertWhileFieldIsEmpty").show();
            $("#alertWhileFieldIsEmpty").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
            $("#alertWhileFieldIsEmpty").effect("highlight", 100);
            $("#runQueryButton").attr("disabled", true);
            $("#saveQueryButton").attr("disabled", true);
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
            $("#runQueryButton").attr("disabled", true);
            $("#saveQueryButton").attr("disabled", true);
        
        }
        if (alertErrorForCount == false && alertError == false && alertFlag == false) {
            $("#alertForLikeOperator").hide();
            $("#alertForInOperator").hide();
            $("#alertWhileFieldIsEmpty").empty();
            $("#alertWhileFieldIsEmpty").hide();
            $("#runQueryButton").removeAttr("disabled");
            $("#saveQueryButton").removeAttr("disabled");
        }
    });
    
    
    var joinStructureTableCount = 0;
    $(document).off('click', '#addRowForOrderBy').on('click', '#addRowForOrderBy', function() {
        
        joinStructureTableCount++;
        
        var joinStructureAddRow = "<tr class = 'orderBy' id = 'closeButtonTableColumn-" + joinStructureTableCount + "'><td><label class='control-label'> </label></td><td><label class='control-label'>Types :</label></td><td><select id='mainQueryOrderByColumnType-" + joinStructureTableCount + "' class='form-control mainQueryCountyTableColumns' >" + 
        "<option value=''>--Please Select--</option><option value='ASC'>ASC</option><option value='DESC'>DESC</option></select></td>" + 
        "<td><label class='control-label'>Columns :</label></td>" + 
        "<td><select multiple id='columnsForMainQueryOrderBy-" + joinStructureTableCount + "' class='form-control countyTableColumnsForMainQueryOrderBymul' >" + 
        "<option value=''>--Please Select--</option></select></td>" + 
        "<td><div class = 'closeButtonTableColumnClass' id = 'closeButtonTableColumn-" + joinStructureTableCount + "' style = 'padding:4px'><img src='img/cancel.ico'/></div></td></tr>";
        $("#groupByOrderByHavingFields").append(joinStructureAddRow);
        var optionValues = $("#columnsForMainQueryOrderBy-0").html();
        $("#columnsForMainQueryOrderBy-" + joinStructureTableCount).html("");
        $("#columnsForMainQueryOrderBy-" + joinStructureTableCount).html(optionValues);
        $('.countyTableColumnsForMainQueryOrderBymul').multiselect({
            /*enableFiltering: true,*/
            includeSelectAllOption: true,
            maxHeight: 150,
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true,
            enableCaseInsensitiveFiltering: true
        });
        /*$(".countyTableColumnsForMainQueryOrderBy").multiselect("rebuild");*/
    });
    
    $(document).off('click', '.closeButtonTableColumnClass').on('click', '.closeButtonTableColumnClass', function() {
        var id = this.id;
        $('#' + id).remove();
    });
    
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
    
    
    $scope.editResultQueryfn = function() {
    
    }
    ;
    $scope.saveQueryfn = function() {
        
        if (confirm('You want to close window?')) {
            alert('close window');
            var resultQuery = $('#resultQuery').val();
            localStorage.setItem("resultQuery", resultQuery);
            var resultBuildQuery = $('#resultBuildQuery').val();
            localStorage.setItem("resultBuildQuery", resultBuildQuery);
            window.close();
            tableCount = 0;
            joinStructureTableCount = 0;
        } 
        else {
            alert('stay back');
        }
    }
    ;
    $scope.gridOptions = {
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            /*gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
		            $scope.msg.lastCellEdited = 'edited row i d:' + rowEntity.id + ' Column:' + colDef.name + ' newValue:' + newValue + ' oldValue:' + oldValue;
		            $scope.$apply();
	   		 	});*/
        },
        enableSorting: false,
        headerHeight: 20,
        enableFiltering: true
    };
    var colArr = [];
    $scope.runQuery = function() {
    /* var resultQuery = $('#resultQuery').val();
        localStorage.setItem("pageresultQuery", resultQuery);
        var specialChars = ['%', '&', '+', '#'];
        var codeNumbersForSplChars = ['%25', '%26', '%2B', '%23'];
        for (var sp = 0; sp < specialChars.length; sp++) {
            if (resultQuery.indexOf(specialChars[sp]) > 0) {
                resultQuery = resultQuery.split(specialChars[sp]).join(codeNumbersForSplChars[sp]);
            }
        }
        if (resultQuery != "") {
            $http.get('./executeQuery?input_query=' + resultQuery).success(function(response) {
                $('.widget-content').hide();
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
                    columnDefs: colArr
                };
                $scope.gridOptions.data = response;
                $scope.gridOptions.enableColumnResizing = true;
                var noOfRows = $scope.gridOptions.data.length;
                $("#NoOfRows").text("");
                $("#NoOfRows").text("No Of Rows : " + noOfRows);
            });
*/}
    /*$anchorScroll();
    }    ;*/
}
]);