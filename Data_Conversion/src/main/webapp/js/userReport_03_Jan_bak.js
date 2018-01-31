$(document).ready(function() {
    $(".userStatus").change(function() {
    	var selectedUser=$('#status').val();
    	$('#userSelected').text(selectedUser);
 	});
    var userLogged=$('#userLoggedin').val();
    $.ajax({
    	type: "POST",
		url: 'userReportFetch',
		async: false,
		data: {
		'userLogged':userLogged
		},
        success: function(response) {
        	$('#assignedCounty').text(response[0].county);
        	$('#userSelected').text(response[0].status);
        	$('#userTaskAssigned').text(response[0].assignedtime);
        	$('#userProcesed').text(response[0].processintime);
        	$('#userTaskCompleted').text(response[0].completedtime);
        	$('#userTaskDuration').text(response[0].duration);
        },
        error:function(){
    	}
    });
    $("#submitClick").click(function(){
    	var selectedUser=$('#userLoggedin').val();
    	var comments=$('#usercomments').val();
    	var currentStatus=$('#status').val();
    	var sifnoffValue=$('#signOffStatus').val();
        $.ajax({
        	type: "POST",
    		url: 'userStatusInsert',
    		async: false,
    		data: {
    		'comments':comments,
    		'currentStatus':currentStatus,
    		'selectedUser':selectedUser,
    		'signOffStatus':sifnoffValue
    		},
            success: function(response) {
            	alert("Updated successfully.");
            },
            error:function(){
        	}
        });
    });
});