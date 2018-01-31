$(document).ready(function() {
    $(".userStatus").change(function() {
        var selectedUser = $('#status').val();
        $('#userSelected').text(selectedUser);
    });
    var userLogged = $('#userLoggedin').val();
    $.ajax({
        type: "POST",
        url: 'userReportFetch',
        async: false,
        data: {
            'userLogged': userLogged
        },
        success: function(response) {
            var table = $("<table class='table table-bordered'/>").addClass('tftable');
            table.append('<thead><th>CountyName</th><th style="">Current Status</th><th>Assigned Time</th><th>Processed Time</th><th>Completed Time</th><th>Duration</th><th>Count</th><th>Comment</th></thead>');
            var tbody = $("<tbody></tbody>");
            $.each(response, function(rowIndex, r) {
                var row = $("<tr></tr>");
                row.append($("<td></td>").append(("<span id = 'county" + rowIndex + "'>" + r.countyStateCode) + "</span><br>" + '' + "<span><button id='joinConditionsForTable-" + rowIndex + "' class='btn btn-info joinConditions'>Create Joins</button></span>"));
                row.append($("<td></td>").append(r.status + '' + '<span><select id="status' + rowIndex + '" class="form-control userStatus""><option value="">--Please Select--</option><option value="Start Processing">Start Processing</option><option value="Process Completed">Process Completed</option> </select></span>'));
                row.append($("<td id='assignedtime" + rowIndex + "'></td>").text(r.assignedtime));
                row.append($("<td id='processintime" + rowIndex + "'></td>").text(r.processintime));
                row.append($("<td id='completedtime" + rowIndex + "'></td>").text(r.completedtime));
                row.append($("<td id='duration" + rowIndex + "'></td>").text(r.duration));
                row.append($("<td id='count" + rowIndex + "'></td>").text(r.count));
                row.append($("<td id='" + rowIndex + "'></td>").append('<textarea class="form-control inputstl" id="usercomments' + rowIndex + '" rows="5" style="height: 40px !important;width: 133px; float:left; border-color: darkseagreen;"></textarea><input type="checkbox" id="signOffStatus' + rowIndex + '" value="Sign Off">Sign Off</input><button type="button" id="submitClick-' + rowIndex + '" class="btn btn-success subbutton"  style="height: 30px;">Submit</button>'));
                tbody.append(row);
                table.append(tbody);
                if (r.signoff == "Sign Off") {
                    $('#signOffStatus' + rowIndex).attr('checked', true);
                }
            });
            $("#userReportDetails").append(table);
            /*$('#assignedCounty').text(response[0].county);
        	$('#userSelected').text(response[0].status);
        	$('#userTaskAssigned').text(response[0].assignedtime);
        	$('#userProcesed').text(response[0].processintime);
        	$('#userTaskCompleted').text(response[0].completedtime);
        	$('#userTaskDuration').text(response[0].duration);
        	$('#userTaskCount').text(response[0].count);
        	if(response[0].signoff=="Sign Off"){
        		$('#signOffStatus').attr('checked', true);
        	}
        	*/
        
        },
        error: function() {
        }
    });
    $(".subbutton").click(function() {
        var button = this.id;
        var buttonSplit = button.split("-");
        var reference = buttonSplit[1];
        var county_state = $('#county'+ reference).text();
        var selectedUser = $('#userLoggedin').val();
        var comments = $('#usercomments' + reference).val();
        var currentStatus = $('#status' + reference).val();
        var sifnoffValue = '';
        if ($('#signOffStatus' + reference).is(':checked') == true) {
            sifnoffValue = 'Sign Off';
        } 
        else {
            sifnoffValue = '';
        }
        $.ajax({
            type: "POST",
            url: 'userStatusInsert',
            async: false,
            modal: true,
            data: {
                'comments': comments,
                'currentStatus': currentStatus,
                'selectedUser': selectedUser,
                'signOffStatus': sifnoffValue,
                'county_state': county_state
            },
            success: function(response) {
                alert("Updated successfully.");
                location.reload();
            },
            error: function() {
            }
        });
    });
    
    $(".joinConditions").click(function() {
        var joinButtonId = this.id;
        var joinConditionCount = joinButtonId.split("-");
        var tableInformation = $("#county" + joinConditionCount[1]).text();
        tableInformation = tableInformation.split("-");
        var tableData = tableInformation[0] + "_" + tableInformation[2];
        var editionNumber = tableInformation[3];
        
        var tableNamesData = [];
        
        $.ajax({
            type: "POST",
            url: 'getTableNamesForJoin',
            async: false,
            data: {
                'tableData': tableData
            },
            success: function(response) {
                tableNamesData = response;
                if (tableNamesData.length == 0) {
                    alert("Please insert the tables for that County then Try Agian.")
                }
            },
            error: function() {
            }
        });
        
        var model_dialog = $("#buildJoinConditions").dialog({
            close: function(event, ui) {
                selectedColumnNames = [];
                selectedColumnValues = [];
                window.localStorage.removeItem("selectedColumnValues");
            },
            autoOpen: false,
            title: "Create Join Condition :",
            height: 500,
            width: 1300,
            show: {
                effect: 'fade',
                duration: 1200
            },
            modal: true,
            resizable: true,
            buttons: {
                Validate: function() {
                    var selectedTableColumn = [];
                    var selectedTables = [];
                    var selectedTablesDupCheck = [];
                    var selectedTablesDup = [];
                    var arrInput = [];
                    var alertForSameColumn = false;
                    var alertForDifColumnRow = false;
                    var alertForRevColumnRow = false;
                    var errorFlag = false;
                    for (var l = 0; l <= joinStructureTableCount; l++) {
                        var firstTableColumns = $("#joinTableFirst-" + l).val();
                        var SecondTableColumns = $("#joinTableSecond-" + l).val();
                        if (firstTableColumns != "" && firstTableColumns != undefined && SecondTableColumns != "" && SecondTableColumns != undefined) {
                            if (firstTableColumns == SecondTableColumns) {
                                alertForSameColumn = true;
                            } 
                            else {
                                arrInput.push(firstTableColumns + " " + SecondTableColumns);
                                var sorted_arr = arrInput.sort();
                                var results = [];
                                for (var i = 0; i < arrInput.length - 1; i++) {
                                    if (sorted_arr[i + 1] == sorted_arr[i]) {
                                        results.push(sorted_arr[i]);
                                    }
                                }
                                if (results.length > 0) {
                                    alertForDifColumnRow = true;
                                } 
                                else {
                                    arrInput = arrInput.sort();
                                    sorted_arr = sorted_arr.sort();
                                    results = [];
                                    var sortedValue = [];
                                    for (var i = 0; i < arrInput.length - 1; i++) {
                                        sortedValue = sorted_arr[i].split("|");
                                        sorted_arr[i] = sortedValue[1] + " " + sortedValue[0];
                                        if (arrInput[i + 1] == sorted_arr[i]) {
                                            results.push(sorted_arr[i]);
                                        }
                                    }
                                    if (results.length > 0) {
                                        alertForRevColumnRow = true;
                                    }
                                
                                }
                            }
                        }
                    }
                    var checkingNoOfTables = [];
                    var duplicateCheck = [];
                    var query = "";
                    var checkingQuery = "";
                    if (alertForSameColumn == false && alertForDifColumnRow == false && alertForRevColumnRow == false && errorFlag == false) {
                        for (var l = 0; l <= joinStructureTableCount; l++) {
                            var firstTableColumns = $("#joinTableFirst-" + l).val();
                            var SecondTableColumns = $("#joinTableSecond-" + l).val();
                            var tablesJoinRelationShip = $("#joinRelationShip-" + l).val();
                            if (firstTableColumns != "" && firstTableColumns != undefined && SecondTableColumns != "" && SecondTableColumns != undefined && tablesJoinRelationShip != "" && tablesJoinRelationShip != undefined) {
                                firstTableColumns = firstTableColumns.split("-");
                                var firstTable = firstTableColumns[1];
                                firstTableColumns = "LTRIM(RTRIM(" + firstTableColumns[1] + ".[" + firstTableColumns[0] + "]))";
                                SecondTableColumns = SecondTableColumns.split("-");
                                var secondTable = SecondTableColumns[1];
                                SecondTableColumns = "LTRIM(RTRIM(" + SecondTableColumns[1] + ".[" + SecondTableColumns[0] + "]))";
                                selectedTableColumn.push(firstTableColumns + " = " + SecondTableColumns);
                                var joinRelation = "";
                                if (l > 0) {
                                    if (checkingNoOfTables.length >= 2) {
                                        for (var t = 0; t <= checkingNoOfTables.length - 1; t++) {
                                            if (firstTable != checkingNoOfTables[t] && secondTable != checkingNoOfTables[t] && checkingNoOfTables[t] != undefined && checkingNoOfTables[t] != "") {
                                                duplicateCheck.push(firstTable);
                                                duplicateCheck.push(secondTable);
                                                duplicateCheck = $.unique(duplicateCheck);
                                                checkingNoOfTables = $.unique(checkingNoOfTables);
                                                var newTable = [];
                                                $.grep(duplicateCheck, function(el) {
                                                    if ($.inArray(el, checkingNoOfTables) == -1)
                                                        newTable.push(el);
                                                });
                                                joinRelation = tablesJoinRelationShip + " " + newTable;
                                            }
                                        }
                                    }
                                } 
                                else {
                                    joinRelation = firstTable + " " + tablesJoinRelationShip + " " + secondTable;
                                    checkingNoOfTables.push(firstTable);
                                    checkingNoOfTables.push(secondTable);
                                    duplicateCheck.push(firstTable);
                                    duplicateCheck.push(secondTable);
                                }
                                selectedTables.push(joinRelation);
                                
                                if (l == 0) {
                                    query = query + selectedTables[0] + " on " + selectedTableColumn[0];
                                
                                } 
                                else if (l > 0) {
                                    query = query + selectedTables[l] + " on " + selectedTableColumn[l];
                                }
                            } 
                        }
                        console.log(query);
                        var existingQuery = $("#joinConditionQuery").val();
                        
                        if(query == existingQuery){
                            $("#alertCountOfRows").empty();
                            $("#alertCountOfRows").hide();
                            $("#alertIfFieldIsEmpty").empty();
                            $("#alertIfFieldIsEmpty").show();
                            $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> You are creating Same Join Condition again");
                            $("#alertIfFieldIsEmpty").effect("highlight", 100);
                        }
                        else{
	                        localStorage.setItem("query", query);
	                        checkingQuery = "select count(*) from " + query;
	                        console.log("checkingQuery : " + checkingQuery);
	                        $("#alertIfFieldIsEmpty").empty();
	                        $("#alertIfFieldIsEmpty").hide();
	                        var tablesSelectedForJoins = $("#listTheTableNames").val();
	                        
	                        var tablesSelectedForJoinsString = "";
	                        for (e = 0; e <= tablesSelectedForJoins.length - 1; e++) {
	                            tablesSelectedForJoinsString += tablesSelectedForJoins[e] + ",";
	                        }
	                        tablesSelectedForJoinsString = tablesSelectedForJoinsString.slice(0, -1);
	                        
	                        var countOfJoins = [];
	                        var noOfRows = 0;
	                        $.ajax({
	                            type: "POST",
	                            url: 'countOfJoinCondition',
	                            data: {
	                                'checkingQuery': checkingQuery,
	                                'tablesSelectedForJoinsString': tablesSelectedForJoinsString
	                            },
	                            success: function(data) {
	                                countOfJoins = data;
	                                noOfRows = countOfJoins[0];
	                                countOfJoins = countOfJoins[1];
	                                
	                                if (noOfRows > 0) {
	                                    $("#alertIfFieldIsEmpty").empty();
	                                    $("#alertIfFieldIsEmpty").hide();
	                                    $("#alertCountOfRows").empty();
	                                    $("#alertCountOfRows").show();
	                                    $("#alertCountOfRows").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> Number of Rows effected with this Join Condition = " + noOfRows);
	                                    $("#alertCountOfRows").effect("highlight", 100);
	                                    $(".ui-dialog-buttonpane button:contains('Save')").removeAttr("disabled", true).removeClass("ui-state-disabled");
	                                    
	                                    $("#savingJoinCondition").show();
	                                    var model_dialog = $("#savingJoinCondition").dialog({
	                                        autoOpen: false,
	                                        title: "Save Join Condition",
	                                        height: 200,
	                                        width: 600,
	                                        resizable: false,
	                                        draggable: false,
	                                        modal: true,
	                                        show: {
	                                            effect: 'fade',
	                                            duration: 1000
	                                        },
	                                        buttons: {
	                	                        Yes: function() {
	                	                        	 var selectedQuery = localStorage.getItem("query");
	                	                             var tablesSelectedForJoins = $("#listTheTableNames").val();
	                	                             var tablesSelectedForJoinsString = "";
	                	                             for (e = 0; e <= tablesSelectedForJoins.length - 1; e++) {
	                	                                 tablesSelectedForJoinsString += tablesSelectedForJoins[e] + ",";
	                	                             }
	                	                             tablesSelectedForJoinsString = tablesSelectedForJoinsString.slice(0, -1);
	                	                             $.ajax({
	                	                                 type: "POST",
	                	                                 url: 'insertTheJoinCondition',
	                	                                 data: {
	                	                                     'selectedQuery': selectedQuery,
	                	                                     'tablesSelectedForJoinsString': tablesSelectedForJoinsString
	                	                                 },
	                	                                 success: function(data) {
	                	                                     
	                	                                     model_dialog.dialog("close");
	                	                                 }
	                	                             });
	                	                        	
	                	                        /*	$("#joinConditionsTableRows").hide();
	                	                        	model_dialog.dialog("close");
	                	                        */	/*$("#joinCondition").show();
	                	                	    	$("#joinConditionQuery").val(joinCondition);
	                	                        	$(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");*/
	                	                        },
	                	                        No: function() {
	                	                        	updateFlag = false;
	                	                            model_dialog.dialog("close");
	                	                          /*  $("#joinCondition").hide();
	                	                            $(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
	                	                            $("#joinConditionsTableRows").show();*/
	                	                        }
	                                        }
	                                    });
	                                    $("#savingJoinCondition").empty();
	                                    $("#savingJoinCondition").prepend($('<p style="padding-top: 10px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Do you want to save the condition ?</p>'));
	                                    model_dialog.dialog("open");
	                                    
	                                } 
	                                else {
	                                    $("#alertCountOfRows").empty();
	                                    $("#alertIfFieldIsEmpty").empty();
	                                    $("#alertIfFieldIsEmpty").show();
	                                    $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> *Please Change the Condition and Try again.");
	                                    $("#alertIfFieldIsEmpty").effect("highlight", 100);
	                                    $(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
	                                }
	                            }
	                        });
                        }
                        }
	                    else {
	                        if (alertForSameColumn == true) {
	                            $("#alertIfFieldIsEmpty").empty();
	                            $("#alertCountOfRows").empty();
	                            $("#alertIfFieldIsEmpty").show();
	                            $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> *Please select different Columns");
	                            $("#alertIfFieldIsEmpty").effect("highlight", 100);
	                        } 
	                        else if (alertForDifColumnRow == true) {
	                            $("#alertCountOfRows").empty();
	                            $("#alertIfFieldIsEmpty").empty();
	                            $("#alertIfFieldIsEmpty").show();
	                            $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> *Same Join Condition is selected.");
	                            $("#alertIfFieldIsEmpty").effect("highlight", 100);
	                        } 
	                        else if (alertForRevColumnRow == true) {
	                            $("#alertCountOfRows").empty();
	                            $("#alertIfFieldIsEmpty").empty();
	                            $("#alertIfFieldIsEmpty").show();
	                            $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> *Same Join Condition is selected in Reverse Order.");
	                            $("#alertIfFieldIsEmpty").effect("highlight", 100);
	                        } 
	                        else if (errorFlag == true) {
	                            $("#alertCountOfRows").empty();
	                            $("#alertIfFieldIsEmpty").empty();
	                            $("#alertIfFieldIsEmpty").show();
	                            $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> *Fields should not be empty.");
	                            $("#alertIfFieldIsEmpty").effect("highlight", 100);
	                        }
	                    }
	                    
	               /* }*/
                }
                },
                /*Save: function() {
                    var selectedQuery = localStorage.getItem("query");
                    var tablesSelectedForJoins = $("#listTheTableNames").val();
                    var tablesSelectedForJoinsString = "";
                    for (e = 0; e <= tablesSelectedForJoins.length - 1; e++) {
                        tablesSelectedForJoinsString += tablesSelectedForJoins[e] + ",";
                    }
                    tablesSelectedForJoinsString = tablesSelectedForJoinsString.slice(0, -1);
                    $.ajax({
                        type: "POST",
                        url: 'insertTheJoinCondition',
                        data: {
                            'selectedQuery': selectedQuery,
                            'tablesSelectedForJoinsString': tablesSelectedForJoinsString
                        },
                        success: function(data) {
                            countOfJoins = selectedQuery;
                            model_dialog.dialog("close");
                        }
                    });
                },*/
                Cancel: function() {
                    model_dialog.dialog("close");
                }
        });
        var fixedTablesNameArr = [];
        for (var i = 0; i < tableNamesData.length; i++) {
            var tableName = tableNamesData[i];
            var tableEditionNumber = "";
            tableEditionNumber = tableName.split('_');
            tableEditionNumber = tableEditionNumber[2];
            
            if (editionNumber == tableEditionNumber) {
                fixedTablesNameArr.push(tableNamesData[i]);
            }
        }
        model_dialog.load("JoinCondtionJsp.jsp?tableNamesData=" + fixedTablesNameArr + "&editionNumber=" + editionNumber).dialog("open");
        $(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
    });
});



function getCountyTableColumnsForJoins() {
    $("#createAJoinCondtion").hide();
    var tableNames = $('#listTheTableNames').val();
    var tableDataResults = [];
    var countyTableNames = "";
    if (tableNames != null  && tableNames != "") {
        for (var i = 0; i < tableNames.length; i++) {
            countyTableNames = countyTableNames + "'" + tableNames[i] + "',";
        }
        countyTableNames = countyTableNames.slice(0, -1);
        var data = "countyTableNames=" + countyTableNames;
        
        $.ajax({
            type: "POST",
            url: 'getCountyTableColumnsForJoins',
            async: false,
            data: {
                'countyTableNames': countyTableNames
            },
            success: function(response) {
                $("#joinConditionsTableRows").empty();
                $("#joinConditionsTableRows").show();
                var table = $("<table id = 'joinConditionTableData'/>");
                var tbody = $("<tbody id = 'tableJoinCondition'></tbody>");
                var row = "";
                row = $("<tr></tr>");
                var option = "";
                
                $.each(response, function(index, value) {
                    
                    option = "";
                    for (var i = 0; i < value.length; i++) {
                        option += '<option value="' + value[i].columnName + '">' + value[i].columnName + '</option>';
                    }
                    row.append("<td><label class='control-label'>" + index + " : </label></td><td><select multiple id=" + index + " class='form-control joinConditionTableInfo'><option value=" + option + ">" + option + "</option></select></td>");
                    tbody.append(row);
                    table.append(tbody);
                });
                row.append("<td><label class='control-label'></td><td><button class='btn btn-warning JoinColumnsButtonClass' id = 'JoinColumnsButton'>Join Columns</button></td>");
                tbody.append(row);
                table.append(tbody);
                $("#joinConditionsTableRows").append(table);
                $("#joinConditionsTableRows").hide();
                $('.joinConditionTableInfo').multiselect({
                    /*enableFiltering: true,*/
                    filterBehavior: 'value',
                    disableIfEmpty: true,
                    onChange: function(event) {
                    /* toCreateADynamicColumns();*/
                    },
                    nonSelectedText: '--Please Select--',
                    enableCaseInsensitiveFiltering : true
                });
            },
            error: function() {
            }
        });
    } 
    else {
        $("#joinConditionsTableRows").hide();
    }

}

var selectedColumnNames = [];
var selectedColumnValues = [];
$(document).on('change', '.joinConditionTableInfo', function() {
    var joinConditionTableId = this.id;
    var colCount = 0;
    $('#' + joinConditionTableId + ' :selected').each(function(j, colName) {
        selectedColumnValues.push(colName.text + "-" + joinConditionTableId);
        selectedColumnValues = $.unique(selectedColumnValues)
        colCount = colCount + 1;
    });
    localStorage.setItem("selectedColumnValues", selectedColumnValues);
    $("#joinTableStructure").hide();
});

$(document).on('click', '#JoinColumnsButton', function() {
    var selectedColumnNames = localStorage.getItem("selectedColumnValues");
    $("#joinTableStructure").show();
    selectedColumnNames = selectedColumnNames.split(",");
    var option = "";
    for (var i = 0; i < selectedColumnNames.length; i++) {
        option += '<option value="' + selectedColumnNames[i] + '">' + selectedColumnNames[i] + '</option>';
    }
    $("#joinTableFirst-0").append("");
    $("#joinTableSecond-0").append("");
    $("#joinTableFirst-0").html(option);
    $("#joinTableSecond-0").html(option);
    selectedColumnValues = [];
});

$(document).on('click', '#addJoinCondition', function() {
    var listOfTableNamesValues = $("#listTheTableNames").val();
    var option = "";
    if (listOfTableNamesValues != null ) {
        if (listOfTableNamesValues.length > 1) {
            $("#alertIfFieldIsEmpty").empty();
            $("#alertIfFieldIsEmpty").hide();
            for (var i = 0; i < listOfTableNamesValues.length; i++) {
                option += '<option value="' + listOfTableNamesValues[i] + '">' + listOfTableNamesValues[i] + '</option>';
            }
            $("#joinFirstTable-0").html("");
            $("#joinSecondTable-0").html("");
            $("#joinFirstTable-0").html(option);
            $("#joinSecondTable-0").html(option);
            $("#createAJoinCondtion").show();
        } 
        else {
            $("#alertIfFieldIsEmpty").empty();
            $("#alertIfFieldIsEmpty").show();
            $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> Please select minimum 2 tables to create joins");
            $("#alertIfFieldIsEmpty").effect("highlight", 100);
        }
    } 
    else {
        $("#alertIfFieldIsEmpty").empty();
        $("#alertIfFieldIsEmpty").show();
        $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> Please select tables");
        $("#alertIfFieldIsEmpty").effect("highlight", 100);
    }

});

var joinStructureTableCount = 0;
$(document).on("click", '.addRowForColumnJoinNextCondtionClass', function() {
    
    var columnJoinId = this.id;
    var columnJoinData = columnJoinId.split("-");
    var columnJoinCount = columnJoinData[1];
    columnJoinData = columnJoinData[0];
    
    joinStructureTableCount++;
    
    var joinStructureAddRow = "<tr id = 'closeButtonTableColumn-" + joinStructureTableCount + "'><td><select  id='joinTableFirst-" + joinStructureTableCount + "' class='form-control selectJoinTable1'>" + 
    "<option value=''>--Please Select--</option></select></td>" + 
    "<td><label class='control-label'></td><td><select  id='joinTableSecond-" + joinStructureTableCount + "' class='form-control selectJoinTable2'>" + 
    "<option value=''>--Please Select--</option></select></td><td><label class='control-label'>  </label></td>" + 
    "<td><select  id='joinRelationShip-" + joinStructureTableCount + "' class='form-control joinRelationShipClass'>" + 
    "<option value = ''>--Please Select--</option><option value = 'inner Join'>inner Join</option>" + 
    "<option value = 'outer Join'>outer Join</option><option value = 'outer Left Join'>outerLeftJoin</option>" + 
    "<option value = 'outer Right Join'>outer Right Join</option></select></td>" + 
    "<td><label class='control-label'> </td><td><i class='icon icon-times closeButtonTableColumnClass' id = 'closeButtonTableColumn-" + joinStructureTableCount + "' aria-hidden='true'></i></td></tr>";
    $("#joinTableStructure").append(joinStructureAddRow);
    var optionValues = $("#joinTableFirst-0").html();
    $("#joinTableFirst-" + joinStructureTableCount).append("");
    $("#joinTableFirst-" + joinStructureTableCount).append(optionValues);
    $("#joinTableSecond-" + joinStructureTableCount).append("");
    $("#joinTableSecond-" + joinStructureTableCount).append(optionValues);
});


$(document).off('click', '.closeButtonTableColumnClass').on('click', '.closeButtonTableColumnClass', function() {
    var id = this.id;
    $('#' + id).remove();
});
/*$(document).off('click', '#joinConditionCheck').on('click', '#joinConditionCheck', function() {
    var selectedTableColumns = $("#listTheTableNames").val();
    var selectedColumnNamesString = "";
    for (var i = 0; i <= selectedTableColumns.length-1; i++) {
        selectedColumnNamesString += selectedTableColumns[i] + ',';
    }
    selectedColumnNamesString = selectedColumnNamesString.slice(0, -1);
    var joinCondition = "";
    var updateFlag = false;
    if (selectedColumnNamesString != "") {
        $.ajax({
            type: "POST",
            url: 'getJoinConditionCheck',
            data: {
                'selectedColumnNamesString': selectedColumnNamesString
            },
            success: function(data) {
                joinCondition = data;
                if(joinCondition != ""){
                	
                	$("#warningMsg").show();
                    var model_dialog = $("#warningMsg").dialog({
                        autoOpen: false,
                        title: "Warning",
                        height: 200,
                        width: 600,
                        resizable: false,
                        draggable: false,
                        modal: true,
                        show: {
                            effect: 'fade',
                            duration: 1000
                        },
                        buttons: {
	                        No: function() {
	                        	updateFlag = false;
	                            model_dialog.dialog("close");
	                            $("#joinCondition").hide();
	                            $(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
	                            $("#joinConditionsTableRows").show();
	                        },
	                        Yes: function() {
	                        	updateFlag = true;
	                        	$("#joinConditionsTableRows").hide();
	                        	model_dialog.dialog("close");
	                        	$("#joinCondition").show();
	                	    	$("#joinConditionQuery").val(joinCondition);
	                        	$(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
	                        }
                        }
                    });
                    $("#warningMsg").empty();
                    $("#warningMsg").prepend($('<p style="padding-top: 10px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>This tables already contains the Join Condition.Do you want to see the condition</p>'));
                    model_dialog.dialog("open");
        	    }
                else{
                	$(".ui-dialog-buttonpane button:contains('Save')").removeAttr("disabled", true).removeClass("ui-state-disabled");
                	$("#joinConditionsTableRows").show();
                }
            }
        });
    }
});
*/

$(document).off('click', '#joinConditionCheck').on('click', '#joinConditionCheck', function() {
    var selectedTableColumns = $("#listTheTableNames").val();
    if (selectedTableColumns == null ) {
        $("#alertIfFieldIsEmpty").empty();
        $("#alertIfFieldIsEmpty").show();
        $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> Please select tables");
        $("#alertIfFieldIsEmpty").effect("highlight", 100);
    } 
    else {
        $("#alertIfFieldIsEmpty").empty();
        $("#alertIfFieldIsEmpty").hide();
        
        var selectedColumnNamesString = "";
        for (var i = 0; i <= selectedTableColumns.length - 1; i++) {
            selectedColumnNamesString += selectedTableColumns[i] + ',';
        }
        selectedColumnNamesString = selectedColumnNamesString.slice(0, -1);
        var joinCondition = "";
        if (selectedColumnNamesString != "") {
            $.ajax({
                type: "POST",
                url: 'getJoinConditionCheck',
                data: {
                    'selectedColumnNamesString': selectedColumnNamesString
                },
                success: function(data) {
                    joinCondition = data;
                    if (joinCondition != "") {
                        $("#joinCondition").show();
                        $("#joinConditionQuery").val(joinCondition);
                    } 
                    else {
                        $("#joinCondition").hide();
                        $("#alertIfFieldIsEmpty").empty();
                        $("#alertIfFieldIsEmpty").show();
                        $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> No Join available for selected tables");
                        $("#alertIfFieldIsEmpty").effect("highlight", 100);
                    }
                }
            });
        }
    }
});
$(document).off('click', '#createNewJoinCondition').on('click', '#createNewJoinCondition', function() {
    
	var selectedTableColumns = $("#listTheTableNames").val();
    if (selectedTableColumns == null ) {
        $("#alertIfFieldIsEmpty").empty();
        $("#alertIfFieldIsEmpty").show();
        $("#alertIfFieldIsEmpty").append("<a href='' data-hide='alert' aria-label='close'>&times;</a> Please select tables");
        $("#alertIfFieldIsEmpty").effect("highlight", 100);
    } 
    else {
        $("#alertIfFieldIsEmpty").empty();
        $("#alertIfFieldIsEmpty").hide();
        
        var selectedColumnNamesString = "";
        for (var i = 0; i <= selectedTableColumns.length - 1; i++) {
            selectedColumnNamesString += selectedTableColumns[i] + ',';
        }
        selectedColumnNamesString = selectedColumnNamesString.slice(0, -1);
        var joinCondition = "";
        if (selectedColumnNamesString != "") {
            $.ajax({
                type: "POST",
                url: 'getJoinConditionCheck',
                data: {
                    'selectedColumnNamesString': selectedColumnNamesString
                },
                success: function(data) {
                    joinCondition = data;
                    if (joinCondition != "") {
                            $("#warningMsg").show();
                            var model_dialog = $("#warningMsg").dialog({
                                autoOpen: false,
                                title: "Warning",
                                height: 200,
                                width: 600,
                                resizable: false,
                                draggable: false,
                                modal: true,
                                show: {
                                    effect: 'fade',
                                    duration: 1000
                                },
                                buttons: {
                                    Yes: function() {
                                        $("#joinConditionsTableRows").show();
                                        model_dialog.dialog("close");
                                        $(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
                                        $("#joinCondition").hide();
                                        $("#joinConditionQuery").empty();
                                        $("#joinConditionQuery").append(joinCondition);
                                    },
                                    No: function() {
                                        model_dialog.dialog("close");
                                        $(".ui-dialog-buttonpane button:contains('Save')").attr("disabled", true).addClass("ui-state-disabled");
                                        $("#joinConditionsTableRows").hide();
                                        location.reload();
                                    }
                                }
                            });
                            $("#warningMsg").empty();
                            $("#warningMsg").prepend($('<p style="padding-top: 10px;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Do you want Overwrite existing Joins ?</p>'));
                            model_dialog.dialog("open");    
                    }
                    else{
                    	$("#joinConditionsTableRows").show();
                    }
                }
            });
        }
    }    
});