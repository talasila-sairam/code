$(document).ready(function() {
	var adminMenuAccess=$('#adminmenuAccess').val();
	var userLogied=$('#loginedUser').val();
	if(userLogied=="null"||userLogied==""){
		location.href ="./LoginPage.jsp";
	}
	if(adminMenuAccess=='Y'){
		$('#mainmenu').append('<li id="AssignmentReport"><a href="#">Assignment Reports</a></li>');
		$('#mainmenu').append(' <li id="userAssignment"><a href="#">User Assignment Reports</a></li>');
	}
	else{
		$('#mainmenu').append(' <li id="userAssignment"><a href="#">User Assignment Reports</a></li>');
	}
	$("#AssignmentReport").on('click', function(e){
		e.preventDefault();
		$("#reportPagesDisplay").removeAttr("src");
		$("#reportPagesDisplay").attr("src","./UserAssignmentReports.jsp");
	});
	$("#userAssignment").on('click', function(e){
		e.preventDefault();
		$("#reportPagesDisplay").removeAttr("src");
		$("#reportPagesDisplay").attr("src","./UserReport.jsp");
	});
	$("#DataConversion").on('click', function(e){
		e.preventDefault();
		$("#reportPagesDisplay").removeAttr("src");
		$("#reportPagesDisplay").attr("src","./MainIndex.jsp");
	});
	$("#dashBoard").on('click', function(e){
		e.preventDefault();
		$("#reportPagesDisplay").removeAttr("src");
		$("#reportPagesDisplay").attr("src","./DashBoard.jsp");
	});
	$("#QcReport").on('click', function(e){
		e.preventDefault();
		$("#reportPagesDisplay").removeAttr("src");
		$("#reportPagesDisplay").attr("src","./QcReport.jsp");
	});
});