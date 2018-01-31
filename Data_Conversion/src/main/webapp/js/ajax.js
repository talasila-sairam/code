
function jqueryAjaxfunction(url,div,data,fields,lenght){
	$.ajax({
	     type: "POST",
	     url: url,
	     data: data,
	     success : function(data) {
             $('#'+div).html(data);
             if(lenght==1){
     			document.getElementById("modelfileld").hidden = "";
     		}
     		else{
     			$('#modelfileld').hide();
     			sessionStorage.setItem("fieldQuery", "");
     		}
         }
	})
}

function jqueryAjaxfunctionForFrame(url,div,data){
	$.ajax({
	     type: "POST",
	     url: url,
	     data: data,
	     success : function(data) {
	    	 $("#"+div).attr("src", data);
         }
	})
}
