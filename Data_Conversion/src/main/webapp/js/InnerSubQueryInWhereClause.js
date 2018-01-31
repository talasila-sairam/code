/*
 * This block is used to get the column names from selected tables
 * logic start here 
 */
	var innerSubQueryoption = "";
    var innerSubQueryTableCount = 0;
    var innerSubQueryCheckingCount = 0;
    var innerSubQueryOptionValues=[];
    var innerSubQueryOptionsToShow=[];
    var innerSubQueryTableNamesSelected = [];
    var innerSubQuerySelectedTableNamesValue = [];
    var innerSubQuerySelectedTableNamesText = [];
    var isubColumId = [];
    var innerSubTableNameAsId = [];
    var innserSubAliasArray = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
    
    
/*	
    $('#countyTablesSelect option').each(function() {
        if ($(this).is(":checked")) {
        	var iSubCheckedTableNames = this.text;
        	isubColumId[innerSubQueryTableCount] = iSubCheckedTableNames;
        	selectedTableNamesText.push(iSubCheckedTableNames);
	       	innerSubQueryTableNamesSelected += (iSubCheckedTableNames+",");
	       	innerSubQueryTableNamesSelected.slice(0,-1);
	       	innerSubTableNameAsId = document.getElementById(iSubCheckedTableNames);		
	       	iSubCheckedTableNames = iSubCheckedTableNames.toLowerCase().replace(/_/g, "");
	       	innerSubQuerySelectedTableNamesValue.push(iSubCheckedTableNames);
	       	for (var i = 0; i < tableNameAsId.length; i++) { 
	       		innerSubQueryOptionValues.push(tableNameAsId.options[i].text+"-"+iSubCheckedTableNames );
	       		innerSubQueryOptionsToShow.push(tableNameAsId.options[i].text)
	       	}
        }
    });
    */
    /*Logic End Here*/
    
    
    /*
     * This method is used to call the innerSubQuery while clicking on the innerSubQuery button.
     * Logic Start Here
     */
    
    
    $(document).off('click', '.iframeForInnerSubquery').on('click', '.iframeForInnerSubquery', function() {
        
    		var innerSubQueryButtonId = this.id;
        	var innerSubQueryButtonValue = this.value;
        	var innerSubQueryIdCount = innerSubQueryButtonId.split("-");
        	innerSubQueryIdCount = innerSubQueryIdCount[1];
        
        	if ((innerSubQueryButtonId == "innerSubQueryButton-"+innerSubQueryIdCount) && ($("#subQueryCol-"+innerSubQueryIdCount).val() != "")){
        		
        		for (var isubI = 0; isubI < innerSubTableNameAsId.length; isubI++) {
		    		var isubJ = 0;
		   			$('#'+isubColumId[isubI]+' option').each(function(){
		   				if ($(this).is(":checked")) {
			   				isubColumId[isubI] = isubColumId[isubI].toLowerCase().replace(/_/g, "");
			       			innerSubQueryOptionValues.push(innerSubTableNameAsId.options[isubJ].value+"-"+isubColumId[isubI] );
			       			innerSubQueryOptionsToShow.push(innerSubTableNameAsId.options[isubJ].text);
		   				}
		       			isubJ = isubJ+1
		       		});
		       	};
		       	var model_dialog = $("#innerSubQueryDiv").dialog({
		       		close: function(event, ui) {
		       		},
                 autoOpen: false,
                 title: "innerSubQuery ",
                 height: 480,
                 width: 1350,
                 modal: true,
                 resizable: true,
                 show: {effect: 'fade', duration: 1000},
                 buttons: {
                     Ok: function() {
							var innerSubQueryColArr = [];
                        	var innerSubQueryColCount = 0;
                        	var innerSubQueryGroupCount = 0;
                        	var innerSubQueryOrderCount = 0;
                        	var innerSubQueryString = '';
                        	var innerSubQueryColString = '';
                        	var innerSubQueryGroupColString = '';
                        	var innerSubQueryOrderColString = '';
                        	var innerSubQueryTableString = '';
                        	var innerSubQueryColumns = [];
                        	var innerSubQueryOperators = [];
                            var innerSubQueryValue = [];
                            var innerSubQueryValueSelect = [];
                            var innerSubQueryTableColumn = [];
                            var innerSubQueryAlertFlag = true;
                            var innerSubQueryTableAliasName ;
                            var innerSubQueryAlertFlagForGroupnOrder = true;
                            var innerSubQueryAlertFlagForTableAndColumns = true;
                            var innerSubQueryTableNameCount = 0;
                            var innerSubQueryJoinsData = "";
                            var innerSubQueryJoinCondtion = "";
                            var selectedInnerSubqTableNamesWithAlias = [];
                            
                            
                            
                            $('#seletedTableNamesInnerSubQuery :selected').each(function(l1, tableName) {
                            	innerSubQueryTableNameCount = innerSubQueryTableNameCount + 1;
                            });
                            
                            console.log(innerSubQueryTableNameCount);
                            
                            var innerSubQueryGroupByValue = [];
	                       	$('#columnsForInnerSubQueryGroupBy :selected').each(function(l3, subGroupName){
	                       		if (innerSubQueryTableNameCount > 1) {
	                       			innerSubQueryGroupByValue[innerSubQueryGroupCount] = $(subGroupName).val();
	                    		    var innerSubQueryTableAliasGroup = innerSubQueryGroupByValue[innerSubQueryGroupCount].split("-"); 
	                    		    var groupColConcat = innerSubQueryTableAliasGroup[1]+".["+innerSubQueryTableAliasGroup[0]+"]";
	                    		    innerSubQueryGroupColString = innerSubQueryGroupColString+groupColConcat+', ';
	                    		    innerSubQueryGroupCount = innerSubQueryGroupCount+1;
	                       		}
	                       		else{
	                       			innerSubQueryGroupByValue[innerSubQueryGroupCount] = $(subGroupName).val();
	                    		    var innerSubQueryTableAliasGroup = innerSubQueryGroupByValue[innerSubQueryGroupCount].split("-"); 
	                    		    var groupColConcat = "["+innerSubQueryTableAliasGroup[0]+"]";
	                    		    innerSubQueryGroupColString = innerSubQueryGroupColString+groupColConcat+', ';
	                    		    innerSubQueryGroupCount = innerSubQueryGroupCount+1;
	                       		}
	                       		
                    		});                        	
	                       	innerSubQueryGroupColString = innerSubQueryGroupColString.slice(0, -2);	                       	
                            
	                       	 var innerSubQueryHavingFunctionValue = $("#functionsForInnerSubQuery").val();
	                       	 
	                       	 var innerSubQueryHavingColumnName = $("#columnsForInnerSubQueryHavingBy").val();
	                       	 if(innerSubQueryHavingColumnName != "" && innerSubQueryHavingColumnName != null){
	                       		if (innerSubQueryTableNameCount > 1) {
	                       			innerSubQueryHavingColumnName = innerSubQueryHavingColumnName.split('-');
			                       	 innerSubQueryHavingColumnName = innerSubQueryHavingColumnName[1] + '.[' + innerSubQueryHavingColumnName[0]+']';
	                       		}
	                       		else{
	                       			innerSubQueryHavingColumnName = innerSubQueryHavingColumnName.split('-');
			                        innerSubQueryHavingColumnName = "["+innerSubQueryHavingColumnName[0]+"]";
	                       		}
		                       	  
	                       	 }
	                       	 
	                       	 var innerSubQueryHavingOperator = $("#innerSubQueryHavingOperator").val();
	                       	 
	                       	 var innerSubQueryHavingValueSelect = $("#innerSubQueryHavingValueSelect").val();
	                       	 if(innerSubQueryHavingValueSelect != "" && innerSubQueryHavingValueSelect != null){
	                       		 if (innerSubQueryTableNameCount > 1) {
	                       			innerSubQueryHavingValueSelect = innerSubQueryHavingValueSelect.split('-');
			                       	innerSubQueryHavingValueSelect = innerSubQueryHavingValueSelect[1] + '.[' + innerSubQueryHavingValueSelect[0]+']';
	                       		 }
	                       		 else{
	                       			innerSubQueryHavingValueSelect = innerSubQueryHavingValueSelect.split('-');
			                       	innerSubQueryHavingValueSelect = "["+innerSubQueryHavingValueSelect[0]+"]";
	                       		 }
	                       		
	                       	 }
	                       	var innerSubQueryHavingValueText = $("#innerSubQueryHavingValueText").val();  	                       	 
	                       	 
	                       	var innerSubQueryOrderByValueType = $('#orderByInnerSubQueryColumnsType').val();
	                       	var innerSubQueryOrderByValues = [];
	                       	$('#columnsForInnerSubQueryOrderBy :selected').each(function(l2, subOrderName){ 
	                       		if (innerSubQueryTableNameCount > 1) {
	                       			innerSubQueryOrderByValues[innerSubQueryOrderCount] = $(subOrderName).val();
	                    		    var innerSubQueryTableAliasOrder = innerSubQueryOrderByValues[innerSubQueryOrderCount].split("-"); 
	                    		    var colConcat = innerSubQueryTableAliasOrder[1]+".["+innerSubQueryTableAliasOrder[0]+"]";
	                    		    innerSubQueryOrderColString = innerSubQueryOrderColString+colConcat+', ';
	                    		    innerSubQueryOrderCount = innerSubQueryOrderCount+1;
	                       		}
	                       		else{
	                       			innerSubQueryOrderByValues[innerSubQueryOrderCount] = $(subOrderName).val();
	                    		    var innerSubQueryTableAliasOrder = innerSubQueryOrderByValues[innerSubQueryOrderCount].split("-"); 
	                    		    var colConcat = "["+innerSubQueryTableAliasOrder[0]+"]";
	                    		    innerSubQueryOrderColString = innerSubQueryOrderColString+colConcat+', ';
	                    		    innerSubQueryOrderCount = innerSubQueryOrderCount+1;
	                       		}
	                       		
                    		});                        	
	                       	innerSubQueryOrderColString = innerSubQueryOrderColString.slice(0, -2);	                       	 
	                       	 
							var selectedTableInnerSubQuery = $('#seletedTableNamesInnerSubQuery').val();
                            
                            	$('#selectColumnsInnerSubQuery :selected').each(function(l1, subColName){
                            		if (innerSubQueryTableNameCount > 1) {
                            			innerSubQueryColArr[innerSubQueryColCount] = $(subColName).val();
                            		    var innerSubQueryTableAlias = innerSubQueryColArr[innerSubQueryColCount].split("-"); 
                            		    var colConcatName = "LTRIM(RTRIM("+innerSubQueryTableAlias[1]+".["+innerSubQueryTableAlias[0]+"]))";
                            		    innerSubQueryColString = innerSubQueryColString+colConcatName+', ';
                            		    innerSubQueryColCount = innerSubQueryColCount+1;
                            		}
                            		else{
                            			innerSubQueryColArr[innerSubQueryColCount] = $(subColName).val();
                            		    var innerSubQueryTableAlias = innerSubQueryColArr[innerSubQueryColCount].split("-"); 
                            		    var colConcatName = "LTRIM(RTRIM(["+innerSubQueryTableAlias[0]+"]))";
                            		    innerSubQueryColString = innerSubQueryColString+colConcatName+', ';
                            		    innerSubQueryColCount = innerSubQueryColCount+1;
                            		}
                        		    
                        		});
                            	
                            	innerSubQueryColString.slice(0, -2);
                            	
                            	if(selectedTableInnerSubQuery != "" && innerSubQueryColString != ""){
                            		innerSubQueryAlertFlagForTableAndColumns = false;
                                }
                                else{
                                	innerSubQueryAlertFlagForTableAndColumns = true;
                                } 
                            	
                            	if(innerSubQueryAlertFlagForTableAndColumns == true){
                            		$("#alertForLikeOperatorinnerSubQuery").hide();
                                	$("#alertForInOperatorInnerSubQuery").hide();
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").empty();
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").show();
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please select the required table and Columns");
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").effect( "highlight",100);
                            	}
                            	else{
                            		
                            		if (innerSubQueryTableNameCount > 1) {
                                		
                               		 var tableNamesSelected = $("#seletedTableNamesInnerSubQuery").val();
                               	     var tableNamesSelectedValue = $("#seletedTableNamesInnerSubQuery").val();
                               	  var innerSubQueryTableNames = "";
                               	  for(var j=0;j<tableNamesSelected.length;j++){
                               		innerSubQueryTableNames += tableNamesSelected[j]+",";
                               	  }
                               	innerSubQueryTableNames = innerSubQueryTableNames.slice(0,-1);
                               	console.log(innerSubQueryTableNames);
                               		$.ajax({
                                           type: "POST",
                                           url: 'executeQueryToGetJoins',
                                           async: false,
                                           data: {
                                               'tableNamesSelected': innerSubQueryTableNames
                                           },
                                           success: function(data) {
                                        	   innerSubQueryJoinsData = data;
                                        	   innerSubQueryJoinsData = innerSubQueryJoinsData.toString().split(",");
                                               innerSubQueryJoinCondtion = innerSubQueryJoinsData[0];
                                               innerSubQueryTableNames = "";
                                           },
                                           error: function(data) {
                                           	alert("Please Try After Some Time.");
                                           }
                                       });
                               		
                               	  if (tableNamesSelected.length > 1) {
                                      for (var i = 0; i < tableNamesSelected.length; i++) {
                                          selectedInnerSubqTableNamesWithAlias.push(tableNamesSelected[i] + " " + innserSubAliasArray[i]);
                                      }
                                  } 
                                  else {
                                      for (var i = 0; i < tableNamesSelected.length; i++) {
                                    	  selectedInnerSubqTableNamesWithAlias.push(tableNamesSelected[i]);
                                      }
                                  }
                               		if (innerSubQueryJoinsData != "" && innerSubQueryJoinsData != undefined) {
                               			 console.log(tableNamesSelected);
                               	            for (var t = 0; t < tableNamesSelected.length; t++) {
                               	                var columnAlias = tableNamesSelected[t] + ".";
                               	                var columnAliasArr = innserSubAliasArray[t] + ".";
                               	                innerSubQueryJoinCondtion = innerSubQueryJoinCondtion.replace(tableNamesSelected[t], selectedInnerSubqTableNamesWithAlias[t]);
                               	             for(var i = 0;i<6;i++){
                               	            	innerSubQueryJoinCondtion = innerSubQueryJoinCondtion.replace(columnAlias, columnAliasArr);
                             				}
                               	            }
                               	         innerSubQueryString = innerSubQueryString + 'SELECT ' + innerSubQueryColString.slice(0, -2) + ' FROM ' + innerSubQueryJoinCondtion;
                               	        } 
                               	        else {
                               	        	alert("Please Create Join Condition and Try agian");
                               	        	return false;
                               	        }
                               	}
                               	else{
                               		selectedTableInnerSubQuery = selectedTableInnerSubQuery.slice(0,-1);
                               		innerSubQueryString = innerSubQueryString+'SELECT '+innerSubQueryColString.slice(0, -2) +' FROM '+selectedTableInnerSubQuery ;
                               	}
                            		                            
	                            	for (var isubM = 0; isubM <= innerSubQueryTableCount; isubM++) {
	                                	innerSubQueryAlertFlag = true;
	                                	var innerSubQueryColumn = $('#innerSubQueryCol-' + isubM).val();
	                                	if(innerSubQueryColumn != undefined && innerSubQueryColumn !=""){
	                                		innerSubQueryColumn = innerSubQueryColumn.split('-');
	                                    	innerSubQueryColumns.push(innerSubQueryColumn[1]+'.'+innerSubQueryColumn[0]) ;
	                                	}
	                                	else if(innerSubQueryColumn == ""){
	                                		innerSubQueryColumns.push("null");	
	                                    }
	                                    var innerSubQueryOperator = $('#innerSubQueryOperators-' + isubM).val();
	                                    if(innerSubQueryOperator != undefined && innerSubQueryOperator !=""){
	                                    	innerSubQueryOperators.push(innerSubQueryOperator) ;
	                                	}
	                                    else if(innerSubQueryOperator == ""){
	                                    	innerSubQueryOperators.push("null");	
	                                    }
	                                    var innerSubQueryValues =  $('#innerSubQueryValueText-' + isubM).val();
	                                    if(innerSubQueryValues != undefined && innerSubQueryValues !=""){
	                                    	innerSubQueryValue.push(innerSubQueryValues);	
	                                    }
	                                    else if(innerSubQueryValues == ""){
	                                    	innerSubQueryValue.push("''");	
	                                    }
	                                    var innerSubQueryTColumn = $("#innerSubQueryColumns-" + isubM).val();
	                                    if(innerSubQueryTColumn != undefined && innerSubQueryTColumn !=""){
	                                    	innerSubQueryTableColumn.push(innerSubQueryTColumn);	
	                                    }
	                                    else if(innerSubQueryTColumn == ""){
	                                    	innerSubQueryTableColumn.push("null");	
	                                    }
	                                    var innerSubQuerySelectedValue = $("#innerSubQueryValueSelect-"+isubM).val();
	                                    if(innerSubQuerySelectedValue != undefined && innerSubQuerySelectedValue !=""){
	                                    	innerSubQuerySelectedValue = innerSubQuerySelectedValue.split("-");
	                                    	innerSubQueryValueSelect.push(innerSubQuerySelectedValue[1]+'.'+innerSubQuerySelectedValue[0]);
	                                    }
	                                    else if(innerSubQuerySelectedValue == ""){
	                                    	innerSubQueryValueSelect.push("null");	
	                                    }
	                                    if(innerSubQueryColumns[isubM] == "null" && innerSubQueryOperators[isubM] == "null" && (innerSubQueryValueSelect[isubM] == "null" || innerSubQueryValue[isubM] ==  "''")){
	                                    	innerSubQueryAlertFlag = false;
	                                    }
	                                    else if( innerSubQueryColumns[isubM] != "" && innerSubQueryValueSelect[isubM] != "" && innerSubQueryOperators[isubM] != "" &&  innerSubQueryColumns[isubM] != "null" && innerSubQueryOperators[isubM] != "null"){
	                                    	innerSubQueryAlertFlag = false;
	                                    }
	                                    else if( innerSubQueryColumns[isubM] != "" && innerSubQueryValue[isubM] != "" && innerSubQueryOperators[isubM] != "" && innerSubQueryColumns[isubM] != "null" && innerSubQueryOperators[isubM] != "null" && innerSubQueryValueSelect[isubM] != 'null'){
	                                    	innerSubQueryAlertFlag = false;
	                                    }
	                                    else if(innerSubQueryOperators[isubM] == "is null" || innerSubQueryOperators[isubM] == "is not null"){
	                                    	if(innerSubQueryColumns[isubM] != "" && innerSubQueryOperators[isubM] != "" && innerSubQueryColumns[isubM] != "null" && innerSubQueryOperators[isubM] != "null"){
	                                			innerSubQueryAlertFlag = false;
	                                    	}
	                                    	else{
	                                    		innerSubQueryAlertFlag = true;
	                                    	}
	                                    }
	                                    else if(isubM>1 && innerSubQueryTableColumn[isubM-1] == ""){
	                                    	innerSubQueryAlertFlag = true;
	                                    }
	                                }
                                if(innerSubQueryAlertFlag == true){
                                	$("#alertForLikeOperatorInnerSubQuery").hide();
                                	$("#alertForInOperatorInnerSubQuery").hide();
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").empty();
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").show();
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter the required fields");
                                	$("#alertWhileFieldIsEmptyInnerSubQuery").effect( "highlight",100);
                                }
                                else if (innerSubQueryAlertFlag == false){
        	                       	 if(innerSubQueryOrderByValueType != "" && innerSubQueryOrderByValueType != undefined){
        	                       		 if(innerSubQueryOrderByValues.length != 0){
        	                       			innerSubQueryAlertFlagForGroupnOrder = false;
        	                       		 }
        	                       		 else{
        	                       			innerSubQueryAlertFlagForGroupnOrder = true;
        	                       		 }
        	                       	 }
        	                       	 else if(innerSubQueryGroupByValue != "" && innerSubQueryGroupByValue != null){
        	                       		if(innerSubQueryHavingFunctionValue != "" && innerSubQueryHavingColumnName != "" && innerSubQueryHavingOperator != "" && (innerSubQueryHavingValueSelect != "undefined." || innerSubQueryHavingValueText != "")){
            	                       		innerSubQueryAlertFlagForGroupnOrder = false;
            	                       	 }
            	                       	 else{
            	                       		innerSubQueryAlertFlagForGroupnOrder = true;
            	                       	 } 
        	                       	 }
        	                       	 else{
        	                       		innerSubQueryAlertFlagForGroupnOrder = false;
        	                       	 }
        	                       	 if(innerSubQueryAlertFlagForGroupnOrder == true){
        	                       		$("#alertForLikeOperatorInnerSubQuery").hide();
                                    	$("#alertForInOperatorInnerSubQuery").hide();
                                    	$("#alertWhileFieldIsEmptyInnerSubQuery").empty();
                                    	$("#alertWhileFieldIsEmptyInnerSubQuery").show();
                                    	$("#alertWhileFieldIsEmptyInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please enter the required fields at group by or order by clause");
                                    	$("#alertWhileFieldIsEmptyInnerSubQuery").effect( "highlight",10);
        	                       	 }
                                }
                            	
                                if(innerSubQueryAlertFlag == false &&  innerSubQueryAlertFlagForGroupnOrder == false){
                                	for(var isubN=0;isubN <= innerSubQueryTableCount; isubN++){
                                		var subStartValue =  $('#innerSubQueryRowStart-' + isubN).prop("checked");
                                		var subEndValue =  $('#innerSubQueryRowEnd-' + isubN).prop("checked");
                                		var subStartingBrackets = "";
                                		var subEndingBrackets = "";
                                			if(subStartValue == true){
                                				subStartingBrackets = " ( "; 
                                			}
                                			if(subEndValue == true){
                                				subEndingBrackets = " ) ";
                                			}
                                			if(isubN == 0 && innerSubQueryValueSelect[isubN] != undefined && innerSubQueryValueSelect[isubN] !="" && innerSubQueryValueSelect[isubN] != "null"){
                                				if(innerSubQueryOperators[isubN] == "is null" || innerSubQueryOperators[isubN] == "is not null"){
                                					innerSubQueryString = innerSubQueryString+" "+'where '+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN] +" "+subEndingBrackets;
                                				}
                                				else {
                                					innerSubQueryString = innerSubQueryString+" "+'where '+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN] +" "+innerSubQueryValueSelect[isubN]+subEndingBrackets;
                                				}
     	                                    }
                                			else if(isubN == 0 && (innerSubQueryValueSelect[isubN] == undefined || innerSubQueryValueSelect[isubN] == "" || innerSubQueryValueSelect[isubN] == "null")){
                                				if(innerSubQueryOperators[isubN] == "is null" || innerSubQueryOperators[isubN] == "is not null"){
                                					innerSubQueryString = innerSubQueryString+" "+'where '+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" "+subEndingBrackets;
                                				}
                                				else if(innerSubQueryOperators[isubN] == "in" || innerSubQueryOperators[isubN] == "not in"){
                                                    innerSubQueryString = innerSubQueryString+" "+'where '+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" ("+innerSubQueryValue[isubN]+")"+subEndingBrackets;
                                                }
                                				else if(innerSubQueryOperators[isubN] == "<" || innerSubQueryOperators[isubN] == ">" || innerSubQueryOperators[isubN] == "<=" || innerSubQueryOperators[isubN] == "<="){
                                					innerSubQueryString = innerSubQueryString+" "+'where '+subStartingBrackets+"  CONVERT(float,"+innerSubQueryColumns[isubN]+") "+innerSubQueryOperators[isubN]+" ("+innerSubQueryValue[isubN]+")"+subEndingBrackets;
                                				}
                                				else{
                                					innerSubQueryString = innerSubQueryString+" "+'where '+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" "+innerSubQueryValue[isubN]+subEndingBrackets;
                                				}
     	                                    }
                                			if(isubN > 0){
     	                                    	if((innerSubQueryTableColumn[isubN-1] == "And" || innerSubQueryTableColumn[isubN-1] == "Or") && (innerSubQueryValueSelect[isubN] == "" || innerSubQueryValueSelect[isubN] == undefined || innerSubQueryValueSelect[isubN] == "null")){
     	                                        	if(innerSubQueryOperators[isubN] == "is null" || innerSubQueryOperators[isubN] == "is not null"){
     	                                        		innerSubQueryString = innerSubQueryString +" "+innerSubQueryTableColumn[isubN-1]+" "+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" "+subEndingBrackets;
     	                                        	}
     	                                        	else if(innerSubQueryOperators[isubN] == "in"|| innerSubQueryOperators[isubN] == "not in"){
                                                        innerSubQueryString = innerSubQueryString +" "+innerSubQueryTableColumn[isubN-1]+" "+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" ("+innerSubQueryValue[isubN]+")"+subEndingBrackets;
                                                    }
     	                                        	else if(innerSubQueryOperators[isubN] == "<" || innerSubQueryOperators[isubN] == ">" || innerSubQueryOperators[isubN] == "<=" || innerSubQueryOperators[isubN] == "<="){
     	                                        		innerSubQueryString = innerSubQueryString +" "+innerSubQueryTableColumn[isubN-1]+" "+subStartingBrackets+" CONVERT(float,"+innerSubQueryColumns[isubN]+")"+innerSubQueryOperators[isubN]+" ("+innerSubQueryValue[isubN]+") "+subEndingBrackets;
     	                                        	}
     	                                        	else{
     	                                        		innerSubQueryString = innerSubQueryString +" "+innerSubQueryTableColumn[isubN-1]+" "+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" "+innerSubQueryValue[isubN]+subEndingBrackets;
     	                                        	}
     	                                        }
     	                                    	else if((innerSubQueryTableColumn[isubN-1] == "And" || innerSubQueryTableColumn[isubN-1] == "Or") && (innerSubQueryValueSelect[isubN] != "" && innerSubQueryValueSelect[isubN] != undefined && innerSubQueryValueSelect[isubN] != "null")){
     	                                        	if(innerSubQueryOperators[isubN] == "is null" || innerSubQueryOperators[isubN] == "is not null"){
     	                                        		innerSubQueryString = innerSubQueryString +" "+innerSubQueryTableColumn[isubN-1]+" "+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" "+subEndingBrackets;
     	                                        	}
     	                                        	else{
     	                                        		innerSubQueryString = innerSubQueryString +" "+innerSubQueryTableColumn[isubN-1]+" "+subStartingBrackets+innerSubQueryColumns[isubN]+" "+innerSubQueryOperators[isubN]+" "+innerSubQueryValueSelect[isubN]+subEndingBrackets;
     	                                        	}
     	                                        }
     	                                    }
                                		}
                                		
                                		if(innerSubQueryGroupByValue != "" && innerSubQueryGroupByValue != null && innerSubQueryHavingFunctionValue != "" && innerSubQueryHavingColumnName != "" && innerSubQueryHavingOperator != "" && (innerSubQueryHavingValueSelect != "undefined." || innerSubQueryHavingValueText != "") && innerSubQueryOrderByValueType != "" && innerSubQueryOrderColString != ""){
                                    		if(innerSubQueryHavingValueSelect != null && innerSubQueryHavingValueSelect != "" && innerSubQueryHavingValueSelect != "undefined."){
                                    			innerSubQueryString = innerSubQueryString + " Group by "+innerSubQueryGroupColString+" Having "+innerSubQueryHavingFunctionValue+" ("+innerSubQueryHavingColumnName+") "+innerSubQueryHavingOperator+" "+innerSubQueryHavingValueSelect+" Order By "+innerSubQueryOrderColString+" "+innerSubQueryOrderByValueType;
                                    		}
                                    		else{
                                    			innerSubQueryString = innerSubQueryString + " Group by "+innerSubQueryGroupColString+" Having "+innerSubQueryHavingFunctionValue+" ("+innerSubQueryHavingColumnName+") "+innerSubQueryHavingOperator+" "+innerSubQueryHavingValueText+" Order By "+innerSubQueryOrderColString+" "+innerSubQueryOrderByValueType;
                                    		}                                		
                                    	}
                                    	else if(innerSubQueryGroupByValue != "" && innerSubQueryGroupByValue != null && innerSubQueryHavingFunctionValue != "" && innerSubQueryHavingColumnName != "" && innerSubQueryHavingOperator != "" && (innerSubQueryHavingValueSelect != "undefined." || innerSubQueryHavingValueText != "") && innerSubQueryOrderByValueType == "" && innerSubQueryOrderColString == ""){
                                    		if(innerSubQueryHavingValueSelect != null && innerSubQueryHavingValueSelect != "" && innerSubQueryHavingValueSelect != "undefined."){
                                    			innerSubQueryString = innerSubQueryString + " Group by "+innerSubQueryGroupColString+" Having "+innerSubQueryHavingFunctionValue+" ("+innerSubQueryHavingColumnName+") "+innerSubQueryHavingOperator+" "+innerSubQueryHavingValueSelect+" ";
                                    		}
                                    		else{
                                    			innerSubQueryString = innerSubQueryString + " Group by "+innerSubQueryGroupColString+" Having "+innerSubQueryHavingFunctionValue+" ("+innerSubQueryHavingColumnName+") "+innerSubQueryHavingOperator+" "+innerSubQueryHavingValueText+" ";
                                    		}          
                                    	}
                                    	else if(innerSubQueryGroupByValue != "" && innerSubQueryGroupByValue != null && innerSubQueryHavingFunctionValue == "" && innerSubQueryHavingColumnName == "" && innerSubQueryHavingOperator == "" && (innerSubQueryHavingValueSelect == "undefined." || innerSubQueryHavingValueText == "")){
                                    		innerSubQueryString = innerSubQueryString + " Group by "+ innerSubQueryGroupColString;
                                    	}
                                    	else if((innerSubQueryGroupByValue == "" || innerSubQueryGroupByValue == null) && innerSubQueryHavingFunctionValue == "" && innerSubQueryHavingColumnName == "" && innerSubQueryHavingOperator == "" && (innerSubQueryHavingValueSelect == "undefined." || innerSubQueryHavingValueText == "") && innerSubQueryOrderByValueType != "" && innerSubQueryOrderColString != ""){
                                    		innerSubQueryString = innerSubQueryString + " Order by "+ innerSubQueryOrderColString +" "+innerSubQueryOrderByValueType;
                                    	}
                                		
                                		innerSubQueryTableCount = 0;
                                		$("#subQueryValueText-"+innerSubQueryIdCount).val(innerSubQueryString);
                                		console.log(innerSubQueryString);
                                   	 	model_dialog.dialog("close");
                                	}
                     }
                        }
                    }
        	 });
        	 var selectedColumnName = $("#subQueryCol-"+innerSubQueryIdCount).val();
        	 var selectedTableNames = localStorage.getItem("selectedTableNames");
        	 var selectedTableNamesTexts = [];
             console.log(selectedTableNames);
             if (selectedTableNames.indexOf(",") > 0) {
                 selectedTableNames = selectedTableNames.split(",");
             }
             if (selectedTableNames.length > 1) {
                 for (var tableText = 0; tableText < selectedTableNames.length; tableText++) {
                 	if (selectedTableNames != "") {
                 		selectedTableNamesTexts.push(selectedTableNames[tableText].split(" ").join("%20").split("#").join("%23"));
                 	}
                 }
             }
             	model_dialog.load("innerSubQueryForWhereClause.jsp?selectedColumnName="+selectedColumnName+"&selectedTableNamesTexts=" + selectedTableNamesTexts).dialog("open");
        	}
        	else{
        		$("#alertForLikeOperatorInnerSubQuery").hide();
        		$("#alertWhileFieldIsEmptyInnerSubQuery").hide();
            	$("#alertForInOperatorInnerSubQuery").empty();
            	$("#alertForInOperatorInnerSubQuery").show();
            	$("#alertForInOperatorInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please Select The Column and Try agian");
            	$("#alertForInOperatorInnerSubQuery").effect("highlight",100);
        	}
        	});
    
    
    /*Logic End Here*/
    
    
    /*
     * This method is used to while changing the operator to "in" it will show the innerSubQuery button
     * Logic Start Here
     */
    
    $(document).off('change', '.operatorInInnerSubQuery').on('change', '.operatorInInnerSubQuery', function() {
    	
    	var innerSubQueryOperatorId = this.id;
    	var innerSubQueryOperatorValue = this.value;
    	var innerSubQueryOperatorCount = innerSubQueryOperatorId.split('-');
    	innerSubQueryOperatorCount = innerSubQueryOperatorCount[1];
    	if(innerSubQueryOperatorValue == "in"){
    		$("#innerSubQueryValueSelect-"+innerSubQueryOperatorCount).attr("disabled", true);
    		$('#innerSubQueryButton-'+innerSubQueryOperatorCount).css('display','block');
    	}
    	else{
    		$("#innerSubQueryValueSelect-"+innerSubQueryOperatorCount).removeAttr("disabled")
    		$('#innerSubQueryButton-'+innerSubQueryOperatorCount).css('display','none');
    	}
    });
    /*Logic End Here*/
    
    
    
    /*
     * This method is used to Show the "Having Clause" div while we select any column 
     * Logic Start Here
     */
    $(document).off('change', '#columnsForInnerSubQueryGroupBy').on('change', '#columnsForInnerSubQueryGroupBy', function() {
  	  var groupByValue = this.value;
  	  if(groupByValue != ""){
  		  $('.HavingInInnerSubQuery').css('display','block');
  		  $('.HavingInInnerSubQuery').css('display','table-row');
  	  }
  	  else{
  		  $('.HavingInInnerSubQuery').css('display','none');
  	  }
    });
    /*Logic End Here*/
    
    
    /**
     * This method is used to validate the Fields for enable and disable the Logical operator for dynamic fields
     * Logic Start Here
     */
    var disableFlagForInnerSubQuery = false;
    var isNullCheckFlagForInnerSubQuery = false;
    
    $(document).off('change', '.operatorInInnerSubQuery').on('change', '.operatorInInnerSubQuery', function() {
        
    	var innerSubOperatorId = this.id;
        var innerSubOperatorCount = innerSubOperatorId.split('-');
        innerSubOperatorCount = innerSubOperatorCount[1];
        var innerSubOperatorValue = this.value;
            
        if( innerSubOperatorId.match("innerSubQueryOperators")){
        if(innerSubOperatorValue == "like" || innerSubOperatorValue == "not like"){
        	$("#alertWhileFieldIsEmptyInnerSubQuery").hide();
        	$("#alertForLikeOperatorInnerSubQuery").empty();
        	$("#alertForLikeOperatorInnerSubQuery").show();
        	$("#alertForLikeOperatorInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please add '%' in textfield as per your requirement");
        	$("#alertForLikeOperatorInnerSubQuery").effect("highlight",100);
        	$("#innerSubQueryValueSelect-" + innerSubOperatorCount).attr("disabled", true);
    		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).prop('selectedIndex',0);
        }
        else{
        	$("#alertForLikeOperatorInnerSubQuery").empty();
        	$("#alertForLikeOperatorInnerSubQuery").hide();
        	$('#innerSubQueryValueSelect-' + innerSubOperatorCount).removeAttr("disabled");
        }
        if(innerSubOperatorValue == "is null" || innerSubOperatorValue == "is not null" ){
        	isNullCheckFlagForInnerSubQuery = true;
        }
        else{
        	isNullCheckFlagForInnerSubQuery = false;
        }
        if(isNullCheckFlagForInnerSubQuery  == true){
        	$("#innerSubQueryValueSelect-" + innerSubOperatorCount).attr("disabled", true);
        	$('#innerSubQueryValueSelect-' + innerSubOperatorCount).prop('selectedIndex',0);
        	$("#innerSubQueryValueText-" + innerSubOperatorCount).attr("disabled", true);
        	$("#innerSubQueryValueText-" + innerSubOperatorCount).val("");
        }
        else if(isNullCheckFlagForInnerSubQuery == false){
        	if($('#innerSubQueryValueSelect-' + innerSubOperatorCount).val() != "" && $("#innerSubQueryValueText-" + innerSubOperatorCount).val() != ""){
        		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).removeAttr("disabled");
        		$("#innerSubQueryValueText-" + innerSubOperatorCount).removeAttr("disabled");
        		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).prop('selectedIndex',0);
        		$("#innerSubQueryValueText-" + innerSubOperatorCount).val("");
        	}
        	else if($('#innerSubQueryValueSelect-' + innerSubOperatorCount).val() == "" && $("#innerSubQueryValueText-" + innerSubOperatorCount).val() != ""){
        		$("#innerSubQueryValueText-" + innerSubOperatorCount).removeAttr("disabled");
        	}
        	else if($('#innerSubQueryValueSelect-' + innerSubOperatorCount).val() != "" && $("#innerSubQueryValueText-" + innerSubOperatorCount).val() == ""){
        		$("#innerSubQueryValueSelect-" + innerSubOperatorCount).removeAttr("disabled");
        	}
        	
        	else{
        		if(innerSubOperatorValue == "like" || innerSubOperatorValue == "not like" || innerSubOperatorValue == "in"){
        			
        			if(innerSubOperatorValue == "like" || innerSubOperatorValue == "not like" ){
	        			$("#innerSubQueryValueSelect-" + innerSubOperatorCount).attr("disabled", true);
	            		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).prop('selectedIndex',0);
	            		$('#innerSubQueryButton-'+ innerSubOperatorCount).css('display','none');
	        		}
	        		else if(innerSubOperatorValue == "in" || innerSubOperatorValue == "not in"){
	            		$("#innerSubQueryValueSelect-" + innerSubOperatorCount).attr("disabled", true);
	            		$('#innerSubQueryButton-'+ innerSubOperatorCount).css('display','block');
	        		}
            	}
        		else{
        			$("#innerSubQueryValueSelect-" + innerSubOperatorCount).removeAttr("disabled");
        			$('#innerSubQueryButton-'+ innerSubOperatorCount).css('display','none');
        		}
        		$("#innerSubQueryValueText-" + innerSubOperatorCount).removeAttr("disabled");
        		
        	}
        } 
        }
        if ($('#innerSubQueryCol-' + innerSubOperatorCount).val() != "" && $('#innerSubQueryOperators-' + innerSubOperatorCount).val() != "") {
        	disableFlagForInnerSubQuery = false;
        }
        else{
        	disableFlagForInnerSubQuery = true;
        }
       
        if (disableFlagForInnerSubQuery == false) {
            $('#innerSubQueryColumns-' + innerSubOperatorCount).removeAttr("disabled");
        } 
        else if(disableFlagForInnerSubQuery == true) {
            $('#innerSubQueryColumns-' + innerSubOperatorCount).attr("disabled", true);
        }
        if(innerSubOperatorId == "innerSubQueryValueSelect-"+innerSubOperatorCount){
        	if(innerSubQueryValueSelect != ""){
        		$('#innerSubQueryValueText-' + innerSubOperatorCount).attr("disabled", true);
        		$('#innerSubQueryValueText-' + innerSubOperatorCount).val("");
        	}
        	else{
        		$('#innerSubQueryValueText-' + innerSubOperatorCount).removeAttr("disabled");
        	}
        }
        if(innerSubOperatorId == "innerSubQueryValueText-"+innerSubOperatorCount){
  	    	if(innerSubQueryValueSelect != ""){
  	    		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).attr("disabled", true);
  	    		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).prop('selectedIndex',0);
  	    	}
  	    	else{
  	    		
  	    		$('#innerSubQueryValueSelect-' + innerSubOperatorCount).removeAttr("disabled");
  	    	}
  	    }
        
    });
    
    /*
     * This method is used to validate while entering data in the input fields
     */
    $(document).on('keyup', '.operatorInInnerSubQuery', function() {
    	
    	var seletedKeyUpId = this.id;
    	var seletedKeyUpCount = seletedKeyUpId.split('-');
    	seletedKeyUpCount = seletedKeyUpCount[1];
    	var seletedKeyUpValue = this.value;
    	
    	if(seletedKeyUpId == 'innerSubQueryValueText-'+seletedKeyUpCount){
    		if(seletedKeyUpValue != ""){
    			$('#innerSubQueryValueSelect-' + seletedKeyUpCount).attr("disabled", true);
    		}
    		else{
    			if($('#innerSubQueryOperators-' + seletedKeyUpCount).val() == 'like' || $('#innerSubQueryOperators-' + seletedKeyUpCount).val() == 'not like'){
    				$('#innerSubQueryValueSelect-' + seletedKeyUpCount).attr("disabled", true);	
    			}
    			else{
    				$('#innerSubQueryValueSelect-' + seletedKeyUpCount).removeAttr("disabled");
    			}
    		}
    	}
    	if($('#innerSubQueryCol-' + seletedKeyUpCount).val() != "" && $('#innerSubQueryOperators-' + seletedKeyUpCount).val() != ""){
    		   $('#innerSubQueryColumns-' + seletedKeyUpCount).removeAttr("disabled");
    		   $('#innerSubQueryValueSelect-' + seletedKeyUpCount).prop('selectedIndex',0);
        } 	
        else{
        	
            $('#innerSubQueryColumns-' + seletedKeyUpCount).attr("disabled", true);
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
    
$(document).off('change','.checkBoxStyleForInnerSubQuery').on('change', '.checkBoxStyleForInnerSubQuery', function() {
    	
    	var innerSubQueryRowStart = false;
    	var innerSubQueryRowEnd = false;
    	var innerSubQueryAlertErrorForCount = false;
    	var innerSubQueryAlertError = false;
    	var innerSubQueryStartCount = 0;
    	var innerSubQueryEndCount = 0;
    	var innerSubQueryAlertFlag = false;
    	for(var subCheckBox = 0; subCheckBox <= innerSubQueryTableCount;subCheckBox++){
    		var subCheckBoxperv = subCheckBox-1;
    		var subCheckBoxNext = subCheckBox+1;
    		innerSubQueryRowStart = $('#innerSubQueryRowStart-'+subCheckBox).prop("checked");
    		if(innerSubQueryRowStart == true){
    			innerSubQueryStartCount++;
    		}
    		innerSubQueryRowEnd = $('#innerSubQueryRowEnd-' + subCheckBox).prop("checked");
    		if(innerSubQueryRowEnd == true){
    			innerSubQueryEndCount++;
    			
    			if(innerSubQueryStartCount < innerSubQueryEndCount){
    				innerSubQueryAlertFlag = true
    			}
    		}
    		if(innerSubQueryRowEnd == true && subCheckBox == 0 && innerSubQueryRowStart == false){
    			innerSubQueryAlertError = true;
    		}
    		var rowEndNext = $('#innerSubQueryRowEnd-' + subCheckBoxperv).prop("checked");
    		if(innerSubQueryRowEnd == true && rowEndNext == true && innerSubQueryRowStart == false){
    			innerSubQueryAlertError = true;
    		}
    		var innerSubQueryRowStartNext = $('#innerSubQueryRowStart-' + subCheckBoxNext).prop("checked");
    		if(innerSubQueryRowStart == true &&  innerSubQueryRowEnd == false && innerSubQueryRowStartNext == true	){
    			innerSubQueryAlertError = true;
    		}
    	}
    	if(innerSubQueryStartCount == innerSubQueryEndCount ){
    		$(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass('ui-state-disabled');
    		innerSubQueryAlertErrorForCount = false;
    		innerSubQueryStartCount = 0;
    		innerSubQueryEndCount = 0;
    	}
    	else if (innerSubQueryStartCount != innerSubQueryEndCount){
    		$("#alertForLikeOperatorInnerSubQuery").hide();
    		$("#alertForInOperatorInnerSubQuery").hide();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").empty();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").show();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
        	$("#alertWhileFieldIsEmptyInnerSubQuery").effect("highlight",100);
    		$(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
    		innerSubQueryAlertErrorForCount = true;
    		innerSubQueryStartCount = 0;
    		innerSubQueryEndCount = 0;
    	}
    	if(innerSubQueryAlertError == true || innerSubQueryAlertFlag == true){
    		$("#alertForLikeOperatorInnerSubQuery").hide();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").empty();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").show();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please make sure Proper selection of CheckBoxes for Grouping");
        	$("#alertWhileFieldIsEmptyInnerSubQuery").effect("highlight",100);
    		$(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
    	}
    	if(innerSubQueryAlertErrorForCount == false && innerSubQueryAlertError == false && innerSubQueryAlertFlag == false){
    		$("#alertForLikeOperatorInnerSubQuery").hide();
    		$("#alertForInOperatorInnerSubQuery").hide();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").empty();
        	$("#alertWhileFieldIsEmptyInnerSubQuery").hide();
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

var isNullCheckFlagForInnerSubQueryHaving = false;
$(document).off('change','.innerSubQueryCountyTableFunction').on('change', '.innerSubQueryCountyTableFunction', function() {
	
	var havingValueSelectFieldId = this.id;
	var havingValueSelectFieldValues = this.value;
	
	
    
    if( havingValueSelectFieldId.match("innerSubQueryHavingOperator")){
	    if(havingValueSelectFieldValues == "like" || havingValueSelectFieldValues == "not like"){
	    	$("#alertWhileFieldIsEmptyInnerSubQuery").hide();
	    	$("#alertForLikeOperatorInnerSubQuery").empty();
	    	$("#alertForLikeOperatorInnerSubQuery").show();
	    	$("#alertForLikeOperatorInnerSubQuery").append("<a href='' class='close' data-hide='alert' aria-label='close'>&times;</a> *Please add '%' in textfield as per your requirement");
	    	$("#alertForLikeOperatorInnerSubQuery").effect("highlight",100);
	    	$("#innerSubQueryHavingValueSelect").attr("disabled", true);
			$('#innerSubQueryHavingValueSelect').prop('selectedIndex',0);
	    }
	    else{
	    	$("#alertForLikeOperatorInnerSubQuery").empty();
	    	$("#alertForLikeOperatorInnerSubQuery").hide();
	    	$('#innerSubQueryHavingValueSelect').removeAttr("disabled");
	    }
    
    if(havingValueSelectFieldValues == "is null" || havingValueSelectFieldValues == "is not null" ){
    	isNullCheckFlagForInnerSubQueryHaving = true;
    }
    else{
    	isNullCheckFlagForInnerSubQueryHaving = false;
    }
    if(isNullCheckFlagForInnerSubQueryHaving  == true){
    	$("#innerSubQueryHavingValueSelect").attr("disabled", true);
    	$('#innerSubQueryHavingValueSelect').prop('selectedIndex',0);
    	$("#innerSubQueryHavingValueText").attr("disabled", true);
    	$("#innerSubQueryHavingValueText").val("");
    }
    else if(isNullCheckFlagForInnerSubQueryHaving == false){
    	if($('#innerSubQueryHavingValueSelect').val() != "" && $("#innerSubQueryHavingValueText").val() != ""){
    		$('#innerSubQueryHavingValueSelect').removeAttr("disabled");
    		$("#innerSubQueryHavingValueText").removeAttr("disabled");
    		$('#innerSubQueryHavingValueSelect').prop('selectedIndex',0);
    		$("#innerSubQueryHavingValueText").val("");
    	}
    	else if($('#innerSubQueryHavingValueSelect').val() == "" && $("#innerSubQueryHavingValueText").val() != ""){
    		$("#innerSubQueryHavingValueText").removeAttr("disabled");
    	}
    	else if($('#innerSubQueryHavingValueSelect').val() != "" && $("#innerSubQueryHavingValueText").val() == ""){
    		$("#innerSubQueryHavingValueSelect").removeAttr("disabled");
    	}
    	
    	else{
    		if(havingValueSelectFieldValues == "like" || havingValueSelectFieldValues == "not like" || havingValueSelectFieldValues == "in"){
    			
    			if(havingValueSelectFieldValues == "like" || havingValueSelectFieldValues == "not like" ){
        			$("#innerSubQueryHavingValueSelect").attr("disabled", true);
            		$('#innerSubQueryHavingValueSelect').prop('selectedIndex',0);
        		}
        		else if(havingValueSelectFieldValues == "in"){
            		$("#innerSubQueryHavingValueSelect").attr("disabled", true);
        		}
        	}
    		else{
    			$("#innerSubQueryHavingValueSelect").removeAttr("disabled");
    		}
    		$("#innerSubQueryHavingValueText").removeAttr("disabled");
    		
    	}
    }
    }
    if(havingValueSelectFieldId == "innerSubQueryHavingValueSelect"){
    	if(havingValueSelectFieldValues != ""){
    		$('#innerSubQueryHavingValueText').attr("disabled", true);
    		$('#innerSubQueryHavingValueText').val("");
    	}
    	else{
    		$('#innerSubQueryHavingValueText').removeAttr("disabled");
    	}
    }
    if(havingValueSelectFieldId == "innerSubQueryHavingValueText"){
    	if(havingValueSelectFieldValues != ""){
    		$('#innerSubQueryHavingValueSelect').attr("disabled", true);
    		$('#innerSubQueryHavingValueSelect').prop('selectedIndex',0);
    	}
    	else{
    		$('#innerSubQueryHavingValueSelect').removeAttr("disabled");
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

$(document).on('keyup', '.havingValueTextForInnerSubQuery', function() {
	
	var havingValueTextId= this.id;
	
	var havingValueTextValue = this.value;
	if(havingValueTextId == 'innerSubQueryHavingValueText'){
		if(havingValueTextValue != ""){
			$('#innerSubQueryHavingValueSelect').attr("disabled", true);
		}
		else{
			if($('#innerSubQueryHavingOperator').val() == 'like' || $('#innerSubQueryHavingOperator').val() == 'not like'){
				$('#innerSubQueryHavingValueSelect').attr("disabled", true);	
			}
			else{
				$('#innerSubQueryHavingValueSelect').removeAttr("disabled");
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
$(document).off('click','.innerSubQueryClose').on('click', '.innerSubQueryClose', function() {
	var innerSubQueryCloseButtonId = this.id;
	$('#'+innerSubQueryCloseButtonId).remove();
	innerSubQueryCloseButtonId = innerSubQueryCloseButtonId.split("-");
	var innerSubQueryCloseButtonCount = innerSubQueryCloseButtonId[1];
	innerSubQueryCloseButtonCount = innerSubQueryCloseButtonCount-1;
	$('#innerSubQueryColumns-'+innerSubQueryCloseButtonCount).prop('selectedIndex',0);
});

/*
 * Logic End Here
 */




/*
 * This method is used to add the dynamic rows to the table while changing logical operator to  "and/or"
 * Logic Start Here 
 */
$(document).off('change', '.innerSubQueryCountyTableColumns').on('change', '.innerSubQueryCountyTableColumns', function() {
   
	var logicalOperatorSubId = this.id;
    var logicalOperatorSubCount = logicalOperatorSubId.split('-');
    logicalOperatorSubCount = logicalOperatorSubCount[1];
    var logicalOperatorSubValue = this.value;
    
    var innerSubQueryTableRowSize = $("#innerSubQueryForWhereClause").find("tr").not("thead tr").length;
    var innerSubQueryIndexPosition = $(this).closest("tr").index();
    if(logicalOperatorSubId.match('innerSubQueryColumns') && innerSubQueryIndexPosition == (innerSubQueryTableRowSize-1)){
        if ((logicalOperatorSubValue == "And" || logicalOperatorSubValue == "Or") && logicalOperatorSubId.match("subQueryColumns-" + logicalOperatorSubCount)) {
            dynamicRowsTableForInnerSubQuery();
        }
        function dynamicRowsTableForInnerSubQuery() {
        	innerSubQueryTableCount++;
        	
        	var subQueryColumnData = "<tr id = 'innerSubQueryClosebutton-"+ innerSubQueryTableCount +"'>" +
        			"<td><input type='checkbox' id='innerSubQueryRowStart-"+innerSubQueryTableCount+"' class='checkBoxStyleForSubQuery' style='margin: 0px 14px'/></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><select id='innerSubQueryCol-"+innerSubQueryTableCount+"' class='form-control subQueryCountyTableCol'>" +
        				"<option value=''>--Please Select--</option></select></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><select id='innerSubQueryOperators-"+innerSubQueryTableCount+"' class='form-control  operatorInSubQuery'>" +
        				"<option value=''>--Please Select--</option><option value='='>=</option><option value='<>'><></option>" +
        				"<option value='like'>like</option><option value='not like'>not like</option><option value='<'><</option>" +
        				"<option value='>'>></option><option value='<='><=</option><option value='>='>>=</option>" +
        				"<option value='is null'>is null</option><option value='is not null'>is not null</option><option value='in'>in</option>" +
        				"<option value='between'>between</option></select></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><select id='innerSubQueryValueSelect-"+innerSubQueryTableCount+"' class='form-control subQueryCountyTableValues'>" +
        				"<option value=''>--Please Select--</option></select></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><input type='text' id='innerSubQueryValueText-"+innerSubQueryTableCount+"' class='form-control operatorInSubQuery'></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><select id='innerSubQueryColumns-"+innerSubQueryTableCount+"' class='form-control subQueryCountyTableColumns' disabled>" +
        				"<option value=''>--Please Select--</option><option value='And'>And</option><option value='Or'>Or</option>" +
        				"<option value='Not'>Not</option></select></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><input type='checkbox' id='innerSubQueryRowEnd-"+innerSubQueryTableCount+"' class='checkBoxStyleForSubQuery' style='margin: 0px 14px'/></td>" +
        			"<td><label class='control-label'></label></td>" +
        			"<td><div class = 'innerSubQueryClose' id = 'innerSubQueryClosebutton-"+ innerSubQueryTableCount +"' style = 'padding:4px'><img src='img/cancel.ico'/></div></td></tr>";

            $("#innerSubQueryForWhereClause").append(subQueryColumnData);
        	var innerSubQueryColumnOptions='';
        	innerSubQueryColumnOptions += "<option value=''>--Please Select--</option>";
        	for (var isubQueryOptions = 0; isubQueryOptions < innerSubQueryOptionValues.length; issubQueryOptions++) {
        		innerSubQueryColumnOptions += '<option value="' + innerSubQueryOptionValues[subQueryOptions] + '">' + innerSubQueryOptionsToShow[subQueryOptions] + '</option>';
            }
        	$('#innerSubQueryCol-'+innerSubQueryTableCount).empty();
        	$('#innerSubQueryCol-'+innerSubQueryTableCount).append(innerSubQueryColumnOptions);
        	$('#innerSubQueryValueSelect-'+innerSubQueryTableCount).empty();
        	$('#innerSubQueryValueSelect-'+innerSubQueryTableCount).append(innerSubQueryColumnOptions);
        }
    }
});

/*
 * Logic End Here
 */

$(document).off("change", "#seletedTableNamesInnerSubQuery").on("change", "#seletedTableNamesInnerSubQuery", function() {
    /*alert("tableNameChanged");*/
    var option = "<option value = ''>--Please Select--</option>";    
    var tableNames = $('#seletedTableNamesInnerSubQuery').val();
	
	var countyTableNames = "";

	if (tableNames != null && tableNames != "") {
		for (var i = 0; i < tableNames.length; i++) {
			countyTableNames = countyTableNames + "'" + tableNames[i] + "',";
		}
		countyTableNames = countyTableNames.slice(0, -1);
		
		var data = "countyTableNames=" + countyTableNames;
		
		$('.innerSubqueryDefaultColumns').multiselect('refresh');
	    $('.innerSubqueryDefaultColumns option').each(function(index, option) {
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
            	var j=0;
            	$.each(response, function(index, value) {
                    if(tableNames.length > 1){
	                    	for (var i = 0; i < value.length; i++) {
	                    		var columnNameValueAlias = value[i].columnName +"-"+ subAliasArray[j];
	                            option += '<option value="' + columnNameValueAlias +'">' + value[i].columnName+"-"+tableNames[j] + '</option>';
	                        }
                    	 j++;
                    }
                    else{
                    	for (var i = 0; i < value.length; i++) {
                            option += '<option value="' + value[i].columnName + '">' + value[i].columnName + '</option>';
                        }
                    }
                    $('.innerSubqueryDefaultColumns').html(option);
                    $("#innerSubQueryCol-0").html(option);
                    $("#innerSubQueryValueSelect-0").html(option);
                    $("#columnsForInnerSubQueryHavingBy").html(option);
                    $("#innerSubQueryHavingValueSelect").html(option);
            	});
            }
        });
	}

	$('.innerSubqueryDefaultColumns').multiselect('rebuild');
});


