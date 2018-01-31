function verifySourceData(){
	var model_dialog = $("#showDialogForVerification").dialog({
	close: function(event, ui) {
            $('#overlay').hide();
        },
        autoOpen: false,
        title: "Source Data Verification",
        height: 400,
        width: 1300,
        show: {
            effect: 'fade',
            duration: 1000
        },
        resizable: true,
        buttons: {
             Ok: function() {
            	 	$(".ui-dialog-buttonpane button:contains('Ok')").attr("disabled", true).addClass("ui-state-disabled");
            	 	var alertFlag = true;
            	 	var edition = $("#editon").val();
            		var stateFipsCode = $("#stateFipsCode").val();
            		var countyFipsCode = $("#countyFipsCode").val();
            		var countryFipsCode = $("#countryFipsCode").val();
            		var q1 = $('input[name=q1]:checked').val();
            	 	var q2 = $('input[name=q2]:checked').val();
            	 	var q3 = $('input[name=q3]:checked').val();
            	 	var q4 = $('input[name=q4]:checked').val();
            	 	var q5 = $('input[name=q5]:checked').val();
            	 	var q6_a = $('input[name=q6_a]:checked').val();
            	 	var q6_b = $('input[name=q6_b]:checked').val();
            	 	var q7 = $('input[name=q7]:checked').val();
            	 	var q8 = $('input[name=q8]:checked').val();
            	 	var q9 = $('input[name=q9]:checked').val();
            	 	var jsonData = { "edition": edition,
		    						"countryFipsCode": countryFipsCode, 
		    						"stateFipsCode": stateFipsCode,
		    						"countyFipsCode": countyFipsCode,
		    						"q1": q1,
            	 					"q2": q2,
            	 					"q3": q3,
            	 					"q4": q4,
            	 					"q5": q5,
            	 					"q6_a": q6_a,
            	 					"q6_b": q6_b,
            	 					"q7": q7,
            	 					"q8": q8,
            	 					"q9": q9 
	    					}
            	 	console.log(jsonData);
            	 	$.ajax({
            	        type: "POST",
            	        url: 'report/saveSourceVerificationResult',
            	        dataType: 'json',
            	        data: JSON.stringify(jsonData),
            	        contentType: "application/json",
            	        success: function (data) {
            	        	console.log(data);
            	        	if(data.status == "SUCCESS"){
            	        		alertFlag = false;
            	        		alert("SUCCESS");
            	        		model_dialog.dialog("close");
            	        		window.location = 'report/downloadExcelReport?countyName='+countyName+'&editon='+edition+'&countryFipsCode='+countryFipsCode+'&stateFipsCode='+stateFipsCode+'&countyFipsCode='+countyFipsCode+'&stateCode='+stateCode;
            	        	}else if (data.status == "FAILED") {
            	        		
            	        		alert("FAILED");
            	        		alert("Please don't leave any field.");
            	        		$(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass("ui-state-disabled");
            				}else {
            					alert("Processing Error.Please try after some time");
            					$(".ui-dialog-buttonpane button:contains('Ok')").removeAttr("disabled", true).removeClass("ui-state-disabled");
            				}
            	        }
            		});
                 }
              }
        
         });
    model_dialog.load("excelQuestionPopup.jsp").dialog("open");
	/*var height = $(document).height();
	$('#overlay').attr('style','height:'+height+'px');
	$('#overlay').show();*/
	/*if ($("#showDialogForCopyRule").dialog('isOpen') === true) {
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
    }*/
}





$(document).off('click', '.cancelRightEnd').on('click', '.cancelRightEnd', function() {
	$('.divClass').hide();
});



