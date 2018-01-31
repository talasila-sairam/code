	var buildRuleTableCount = 0;
var options = "";
var rule = "";
var ruleQuery = "";
var fieldAliasName = $("#fieldAliasName").val();
var aliasArray = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];

 function  buildRuleQuery() {
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
 //	var selectedTables=selectedTableNamesText.split(",");
 	for (var i = 0; i < tableNameAsId.length; i++) {
		    	var j = 0;
		    	$('#'+columId[i]+' option').each(function(){
		   			if ($(this).is(":checked")) {
		   				columId[i] = columId[i].toLowerCase().replace(/_/g, "");
		       			optionValues.push(tableNameAsId[i].options[j].value+"-"+aliasArray[i]);
		    	       	optionsToShow.push(tableNameAsId[i].options[j].text+"-"+aliasArray[i]);
		   			}
		       		j = j+1
		       	})
		}
    $('.btn-group').removeClass("open");
    var baseQuery = $("#baseQuery").val();
    if (baseQuery == null  || baseQuery == "") {
    	
    	var style="alert-warning";
		var msg="base query required";
		var status="Error";
		showalertMsg(msg,status,style)
        alert("base query required");
        return false;
    }
    var tableArr = [];
    var colArr = [];
    var colCount = 0;
    var queryString = '';
    var colString = '';
    options = "";
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
      		options = options+"<option value="+colInfo[0].split(" ").join("*").split("#").join("%23")+"-"+aliasArray[i]+">"+colInfo[0].split(" ").join("%20").split("#").join("%23")+"-"+tableArr[i]+"</option>";
      		colCount = colCount+1;
      		});
      	}
    
    var columnOptionsText = [];
    var columnValues = [];
    
    for (var p = 0; p < optionValues.length; p++) {
        columnValues.push(optionValues[p].split(" ").join("%20").split("#").join("%23"));
    }
    for (var q = 0; q < optionsToShow.length; q++) {
        columnOptionsText.push(optionsToShow[q].split(" ").join("%20").split("#").join("%23"));
    }
    localStorage.setItem("baseQuery", baseQuery);
    if(fieldAliasName=="RECORDINGDATE"||fieldAliasName=="PRIORSALEDATE"){
    	localStorage.setItem("setselectedTableNamesText", selectedTableNamesText);
    	var popup;
    	popup=window.open("BuildRule50and54.jsp?ruleColumns="+options+"&fieldAliasNameValue="+fieldAliasName+"&optionValues="+columnValues+"&optionsToShow="+columnOptionsText,"_blank");
    	popup.focus();
    }
    else{
    	var popup;
    	popup= window.open("BuildRuleNewWindow.jsp?ruleColumns="+options+"&fieldAliasNameValue="+fieldAliasName+"&optionValues="+columnValues+"&optionsToShow="+columnOptionsText,"_blank");
    	popup.focus();
    }
    localStorage.setItem("buildRuleSelectedTables", selectedTableNamesText);
    
    
    function showalertMsg(message,status,style){
		 var model_dialog = $("#alertmsg").dialog({
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
        $("#alertmsg").empty();
        $("#alertmsg").prepend('<div class="alert '+style+' role="alert">'+message+'</div>');
        model_dialog.dialog("open");
      //  $( "#alertmsgdivs" ).dialog( "moveToTop" );
	}

};
/*    var model_dialog = $("#showColumnsDialogForBuildRule").dialog({
	close: function(event, ui) {
            $('#overlay').hide();
            buildRuleTableCount = 0;
        },
        autoOpen: false,
        title: "Column Names :",
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
            	 	 console.log("bulidRule--------");
            	 	 var commonColumn = $("#commonColumn").val();
	                 var fieldColumn = $("#ruleColumn").val();
	                 var tableAlias = "";
	                 var fieldColumnAlias = "";
	                 var fieldColumnName = "";
	                 if(commonColumn != ""){
	                	 commonColumn = commonColumn.split('-');
	                	 commonColumn[0] = commonColumn[0].replace('*',' ');
		                 commonColumn = commonColumn[1] + '.' + "["+commonColumn[0]+"]";
	                 }
	                 if(fieldColumn != ""){
	                	 fieldColumn = fieldColumn.split('-');
		                 tableAlias = fieldColumn[1];
		                 fieldColumnAlias = fieldAliasName;
		                 fieldColumn[0] = fieldColumn[0].replace('*',' ');
		                 fieldColumnName = "["+fieldColumn[0]+"]";
		                 fieldColumn = fieldColumn[1] + '.' +"["+fieldColumn[0]+"]";
	                 }
	                 var queryCondition;
	                 var baseQueryArr = baseQuery.split("FROM");
            	 	 var operationRowsCount = $("#buildRuleTable tr").length;
            	 	 var operationTypeArr = [];
            	 	 var operationArr = [];
            	 	 var rulePattern = "";
            	 	 var conditionRulePattern = "";
            	 	 rule = "";
            	 	 if(operationRowsCount == 1 && $("#ruleType-0").val() == ""){
            	 		 rulePattern = "RTRIM(LTRIM("+fieldColumn+"))";
            	 		 rule = "Load "+fieldColumn+" as is";
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
	                        	if(whereCondition == ""){
			                        if(rulePattern == ""){
			                            rulePattern = "STUFF(RTRIM(LTRIM("+fieldColumn+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumn+")))+LEN('"+toReplacePattern+"'), LEN(RTRIM(LTRIM("+fieldColumn+"))), '"+replaceByPattern+"') ";
			                        }else{
			                            rulePattern = "STUFF(RTRIM(LTRIM("+rulePattern+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+rulePattern+")))+LEN('"+toReplacePattern+"'), LEN(RTRIM(LTRIM("+rulePattern+"))), '"+replaceByPattern+"') ";
				                    }
		                        }else {
		                        	if(conditionRulePattern == ""){
		                        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN('"+toReplacePattern+"'), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
			                        }else{
			                        	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN('"+toReplacePattern+"'), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
			                        }
								}
	                        }else if (replaceType == "Before Pattern"){
	                        	if(whereCondition == ""){
			                        if(rulePattern == ""){
			                            rulePattern = "STUFF(RTRIM(LTRIM("+fieldColumn+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumn+")))-1, '"+replaceByPattern+"') ";
			                        }else{
			                            rulePattern = "STUFF(RTRIM(LTRIM("+rulePattern+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+rulePattern+")))-1, '"+replaceByPattern+"') ";
				                    }
		                        }else {
		                        	if(conditionRulePattern == ""){
		                        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))-1, '"+replaceByPattern+"') ";
			                        }else{
			                        	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))-1, '"+replaceByPattern+"') ";
			                        }
								}
	                        }else if (replaceType == "Pattern Onwards"){
	                        	if(whereCondition == ""){
			                        if(rulePattern == ""){
			                            rulePattern = "STUFF(RTRIM(LTRIM("+fieldColumn+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumn+"))), LEN(RTRIM(LTRIM("+fieldColumn+"))), '"+replaceByPattern+"') ";
			                        }else{
			                            rulePattern = "STUFF(RTRIM(LTRIM("+rulePattern+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+rulePattern+"))), LEN(RTRIM(LTRIM("+rulePattern+"))), '"+replaceByPattern+"') ";
				                    }
		                        }else {
		                        	if(conditionRulePattern == ""){
		                        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+"))), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
			                        }else{
			                        	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+"))), LEN(RTRIM(LTRIM("+fieldColumnAlias+"))), '"+replaceByPattern+"') ";
			                        }
								}
	                        }else if (replaceType == "Upto Pattern"){
	                        	if(whereCondition == ""){
			                        if(rulePattern == ""){
			                            rulePattern = "STUFF(RTRIM(LTRIM("+fieldColumn+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumn+")))+LEN(RTRIM(LTRIM("+toReplacePattern+")))-1, '"+replaceByPattern+"') ";
			                        }else{
			                            rulePattern = "STUFF(RTRIM(LTRIM("+rulePattern+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+rulePattern+")))+LEN(RTRIM(LTRIM("+toReplacePattern+")))-1, '"+replaceByPattern+"') ";
				                    }
		                        }else {
		                        	if(conditionRulePattern == ""){
		                        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN(RTRIM(LTRIM("+toReplacePattern+")))-1, '"+replaceByPattern+"') ";
			                        }else{
			                        	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN STUFF(RTRIM(LTRIM("+fieldColumnAlias+")), 1, PATINDEX('%"+toReplacePattern+"%', RTRIM(LTRIM("+fieldColumnAlias+")))+LEN(RTRIM(LTRIM("+toReplacePattern+")))-1, '"+replaceByPattern+"') ";
			                        }
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
	                        
	                        if(rule == ""){
	                        	rule = "Replace '"+toReplacePattern+"' Pattern with '"+replaceByPattern+"' Pattern in Selected "+fieldColumnName+" Column Values, ";
	                        }else{
	                        	rule = rule+" then Replace '"+toReplacePattern+"' Pattern with '"+replaceByPattern+"' Pattern in the Result Values, "
	                        }
	                    }
            	 		else if($("#ruleType-"+i).val() == "CONCAT"){
	            	 		var concatColumn = $("#concatColumn-"+i).val();
	            	 		var concatColumnValue = "";
	            	 		var concatColumnName = "";
	            	 		if(concatColumn != ""){
	            	 			
		            	 		concatColumn = concatColumn.split('-');
		            	 		concatColumnValue = concatColumn[0];
		            	 		concatColumn[0] = concatColumn[0].replace("*"," ");
		            	 		concatColumnName = "["+concatColumn[0]+"]";
		            	 		concatColumn = concatColumn[1] + '.' +"["+concatColumn[0]+"]";
	            	 		}
	                        var concatValue = $("#concatValue-"+i).val();
	                        var separationOperator = $("#separationOperator-"+i).val();
	                        //var operator_name = separationOperator[0];
	                        //var operator = separationOperator[1];
	                        if(concatValue == "" && concatColumn != ""){
		                        if(whereCondition == ""){
			                        if(rulePattern == ""){
			                            rulePattern = "CONCAT(RTRIM(LTRIM("+fieldColumn+")), '"+separationOperator+"', RTRIM(LTRIM("+concatColumn+")))";
			                        }else{
			                            rulePattern = "CONCAT("+rulePattern+", '"+separationOperator+"', "+concatColumn+")";
			                        }
		                        }else {
		                        	if(conditionRulePattern == ""){
		                        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN CONCAT(RTRIM(LTRIM("+fieldColumnAlias+")), '"+separationOperator+"', RTRIM(LTRIM("+concatColumn+"))) ";
			                        }else{
			                        	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN CONCAT(RTRIM(LTRIM("+fieldColumnAlias+")), '"+separationOperator+"', RTRIM(LTRIM("+concatColumn+"))) ";
			                        }
								}
		                        
		                        if(rule == ""){
		                        	rule = " Concat '"+fieldColumnName+"' Column & '"+concatColumnName+"' Column with "+separationOperator+", ";
		                        }else{
		                        	rule = rule+" then Concat the Result Values with '"+concatColumnName+"' Column with "+separationOperator+", ";
		                        }
	                        }
	                        else if(concatValue != "" && concatColumn == ""){
	                        	if(whereCondition == ""){
			                        if(rulePattern == ""){
			                            rulePattern = "CONCAT(RTRIM(LTRIM("+fieldColumn+")), '"+separationOperator+"', '"+concatValue+"')";
			                        }else{
			                            rulePattern = "CONCAT("+rulePattern+", '"+separationOperator+"', '"+concatValue+"')";
			                        }
		                        }else {
		                        	if(conditionRulePattern == ""){
		                        		conditionRulePattern = conditionRulePattern+"CASE WHEN "+whereCondition+" THEN CONCAT(RTRIM(LTRIM("+fieldColumnAlias+")), '"+separationOperator+"', '"+concatValue+"') ";
			                        }else{
			                        	conditionRulePattern = conditionRulePattern+" WHEN "+whereCondition+" THEN CONCAT(RTRIM(LTRIM("+fieldColumnAlias+")), '"+separationOperator+"', '"+concatValue+"') ";
			                        }
								}
	                        	
	                        	if(rule == ""){
		                        	rule = " Concatenate '"+fieldColumnName+"' & '"+concatValue+"' with "+separationOperator+", ";
		                        }else{
		                        	rule = rule+" then Concatenate the Result Value & '"+concatValue+"' with "+separationOperator+", ";
		                        }
	                        }
	                        else if((concatValue == "" && concatColumn == "") || (concatValue != "" && concatColumn != "")){
	                        	alert("Wrong Inputs");
	                        	return false;
	                        }
	                    }
            	 		else if($("#ruleType-"+i).val() == "REPLACE LAST"){
            	 			var toReplaceLastPattern = $("#toReplace-"+i).val();
	                        var replaceByLastPattern = $("#replaceBy-"+i).val();
	                        toReplaceLastPattern.length 
	                        if(conditionRulePattern == ""){
	                        	conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '%"+toReplaceLastPattern+"' THEN STUFF("+fieldColumnAlias+",LEN("+fieldColumnAlias+")-LEN('"+toReplaceLastPattern+"')+1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
		                    }else{
		                    	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '%"+toReplaceLastPattern+"' THEN STUFF("+fieldColumnAlias+",LEN("+fieldColumnAlias+")-LEN('"+toReplaceLastPattern+"')+1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
			                }
	                        
	                        if(rule == ""){
	                        	rule = "Replace the last '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in Selected "+fieldColumnName+" Column Values, ";
	                        }else{
	                        	rule = rule+" then Replace the last '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in the Result Values, ";
	                        }
            	 		}
            	 		else if($("#ruleType-"+i).val() == "REPLACE FIRST"){
            	 			var toReplaceLastPattern = $("#toReplace-"+i).val();
	                        var replaceByLastPattern = $("#replaceBy-"+i).val();
	                        toReplaceLastPattern.length 
	                        if(conditionRulePattern == ""){
	                        	conditionRulePattern = conditionRulePattern+"CASE WHEN "+fieldColumnAlias+" LIKE '"+toReplaceLastPattern+"%' THEN STUFF("+fieldColumnAlias+",1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
		                    }else{
		                    	conditionRulePattern = conditionRulePattern+" WHEN "+fieldColumnAlias+" LIKE '"+toReplaceLastPattern+"%' THEN STUFF("+fieldColumnAlias+",1, LEN('"+toReplaceLastPattern+"'), '"+replaceByLastPattern+"') ";
			                }
	                        
	                        if(rule == ""){
	                        	rule = "Replace the initial '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in Selected "+fieldColumnName+" Column Values, ";
	                        }else{
	                        	rule = rule+" then Replace the initial '"+toReplaceLastPattern+"' Pattern with '"+replaceByLastPattern+"' Pattern in the Result Values, "
	                        }
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
							
							if(rule == ""){
	                        	rule = " Divide '"+fieldColumnName+"' Column by "+divideByValue+", ";
	                        }else{
	                        	rule = rule+" then Divide the Result Values by "+divideByValue+", ";
	                        }
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

							if(rule == ""){
	                        	rule = " Multiply '"+fieldColumnName+"' Column by "+multiplyByValue+", ";
	                        }else{
	                        	rule = rule+" then Multiply the Result Values by "+multiplyByValue+", ";
	                        }
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
            	 			
            	 			if(rule == ""){
	                        	rule = " Put Decimal after '"+digitsAfterDecimal+"' digits from right in '"+fieldColumnName+"' Column value, ";
	                        }else{
	                        	rule = rule+" then Put Decimal after '"+digitsAfterDecimal+"' digits from right in result value, ";
	                        }
						}
            	 		
            	 		operationTypeArr[i] = $("#ruleType-"+i).val();
            	 	 }
            	 	 var queryFirstPart = "";
            	 	 if(conditionRulePattern == "" && rulePattern != ""){
            	 		queryFirstPart = "SELECT "+commonColumn+" APPRCL , "+rulePattern+" "+fieldColumnAlias+" "; 
            	 		ruleQuery = baseQuery.replace(baseQueryArr[0],queryFirstPart);
            	 	 }else if(conditionRulePattern != "" && rulePattern != ""){
            	 		ruleQuery = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
            	 				"("+baseQuery.replace(baseQueryArr[0],"SELECT "+commonColumn+" , "+rulePattern+" "+fieldColumnAlias+" ")+") "+tableAlias;
            	 	 }else if(conditionRulePattern != "" && rulePattern == ""){
            	 		ruleQuery = "SELECT "+commonColumn+" , "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
    	 				"("+baseQuery.replace(baseQueryArr[0],"SELECT "+commonColumn+" , "+fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
            	 		ruleQuery = "SELECT "+commonColumn+" APPRCL, "+conditionRulePattern+" ELSE "+fieldColumnAlias+" END AS "+fieldColumnAlias+" FROM " +
    	 				"("+baseQuery.replace(fieldColumn, fieldColumn+" "+fieldColumnAlias+" ")+") "+tableAlias;
            	 	 }else{
            	 		 
            	 	 }
            	 	
                    //ruleQuery = baseQuery.replace(baseQueryArr[0],queryFirstPart);
                    console.log(ruleQuery);
                    $("#ruleQuery").val(rule);
                    $("#ruleQuery").attr('title',queryString);
                    $("#query").val(ruleQuery);
                    alertFlag = false;
                    if(alertFlag == true){
                            alert("Please don't leave any one field as empty");
                    }
                    else{
                                $(this).dialog("close");
                                buildRuleTableCount = 0;
                                $('#overlay').hide();
                    }
                 }
              }
         });
            model_dialog.load("buildRulePopup.jsp").dialog("open");
       		var height = $(document).height();
        	$('#overlay').attr('style','height:'+height+'px');
        	$('#overlay').show();
        	
        	if ($("#showColumnsDialogForBuildRule").dialog('isOpen') === true) {
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
            }
       });*/
 	
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
});*/
/*$(document).on('focusin', "#commonColumn", function() {
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
        "<option value=''>--Please Select--</option>" + 
        "<option value='CONCAT'>Concat</option>" + 
        "<option value='REPLACE'>Replace</option>" +
        "<option value='REPLACE FIRST'>Replace First</option>" +
        "<option value='REPLACE LAST'>Replace Last</option>" +
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
});

$(document).off('click', '.cancelRightEnd').on('click', '.cancelRightEnd', function() {
	$('.divClass').hide();
});*/



