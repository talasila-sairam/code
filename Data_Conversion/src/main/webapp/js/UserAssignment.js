var app = angular.module('app', ['ngTouch', 'ui.grid','ngAnimate']);
app.controller('userassignment', ['$scope', '$http', function($scope, $http) {
	var responseDataReport=[];
	$(function() {
		var date=new Date();
		  $( "#fromDate" ).datepicker({ 
		  dateFormat: "dd/mm/yy",
		  timeFormat:  "hh:mm:ss",
		  yearRange:'2000:'+date,
		  maxDate: "+0m +0w",
		  onClose: function(selectedDate) {
			  $("#toDate").datepicker("option", "minDate", selectedDate);
		    }
		  });
	});
	$(function() {
		var date=new Date();
		  $( "#toDate" ).datepicker({ 
		  dateFormat: "dd/mm/yy",
		  timeFormat:  "hh:mm:ss",
		  maxDate: "+0m +0w",
		  onClose: function(selectedDate) {
			  $("#fromDate").datepicker("option", "maxDate", selectedDate);
		    }
		  });
	});
	$scope.gridOptions = {
			onRegisterApi: function(gridApi){
			$scope.gridApi = gridApi;
			},
			enableSorting: false,
			headerHeight: 20,
			enableFiltering: true
		};
	var myVar = setInterval(myTimer, 1000);
	function myTimer() {
		var d = new Date();
		$('#assignedTime').text(d.toLocaleString());
	}
	var columnDefs1 = [
	                   	{ displayName: 'USER Name',name:'userName' ,width:115 },
	                 //  	{ displayName: 'County ',name: 'county',width:115,enableFiltering: false  },
	                	{ displayName: 'County-state',name: 'countyStateCode' ,width:215,enableFiltering: false },
	                  	{ displayName: 'NoOfTables',name: 'tableCount' ,width:115,enableFiltering: false },
	                	{ displayName: 'Status',name: 'status',width:175,enableFiltering: false  },
	                	{ displayName: 'Assigned Time',name:'assignedtime',width:175,enableFiltering: false  },
	                	{ displayName: 'Processing Time',name:'processintime',width:175,enableFiltering: false  },
	                   	{ displayName: 'Completed Time ',name: 'completedtime',width:175,enableFiltering: false  },
	                   	{ displayName: 'Comments',name: 'comments',width:115,enableFiltering: false  },
	                   	{ displayName: 'Duration',name: 'duration',width:95,enableFiltering: false  },
	                   	{ displayName: 'Sign Off',name: 'signoff',width:75,enableFiltering: false  },
	                	{ displayName: 'No of times County Reviewed',name:'count',width:195,enableFiltering: false  }
	               ];
	 $http.get('./getusers').success(function(data) {
		 var option='';
		 for (var v = 0; v < data.length; v++) {
             option += '<option value="' + data[v] + '">' + data[v] + '</option>';
         }
		 $('#userSelect').append(option);
         $('#userSelect').multiselect('rebuild');
	  });
	 var stateArray=[];
	var countyArray=[];
	 $http.get('./getStates').success(function(data) {
		 for(var i=0;data.length>i;i++){
			 stateArray.push(data[i][1]+"-"+data[i][0]);
		 }
	 });
	 $(document).on('focus','.statesearch',function(){
		 var statesSelected=$('#stateselect').val();
		 var stateCode=statesSelected.split("-");
		 var stateFipsCode=stateCode[1];
		// alert(statesSelected);
		 var fieldId=this.id;
		  $http.get('./getCounties?stateSelected='+stateFipsCode).success(function(data) {
				 
		         $("#"+fieldId).autocomplete({
		          minLength:3,
		          source: function(request, response) {
		           var filteredArray = $.map(data, function(item) {
		           if( item.startsWith(request.term.toUpperCase())){
		           return item;
		           }
		           else{
		           return null;
		           }
		           });
		           response(filteredArray);
		           },
		           select: function(event, ui) {
		            var searchResult = ui.item.label;
		            $("#"+fieldId).val(searchResult);
		            },
		          });
			 });
		});
	  $(document).on('focus','.countiessearch',function(){
	       	var fieldId=this.id;
	         $("#"+fieldId).autocomplete({
	          minLength: 1,
	          source: function(request, response) {
	           var filteredArray = $.map(stateArray, function(item) {
	           if( item.startsWith(request.term.toUpperCase())){
	           return item;
	           }
	           else{
	           return null;
	           }
	           });
	           response(filteredArray);
	           },
	           select: function(event, ui) {
	            var searchResult = ui.item.label;
	            $("#"+fieldId).val(searchResult);
	            },
	          });
	 
	          });
	  $scope.showAllUsers = function() {
		  var currentdate = new Date();
		  var fromDate=$("#fromDate").val();
			var toDate=$("#toDate").val();
	  		$http.get('./showAssignedUsers?fromDate='+fromDate+'&toDate='+toDate).success(function(data) {
	  			$scope.gridOptions.columnDefs=columnDefs1;
	  			$scope.gridOptions.data=data;
	  		});
	   };
    $("#editionselect").change(function() {
    	  var counties=$('#countselect').val();
		  var states=$('#stateselect').val();
		  var statename=states.split('-');
		  var statenametosend=statename[0];
		  var editionYear=$('#editionselect').val();
		  var stateCounty=statenametosend+"_"+counties+'_'+editionYear;
		  $http.get('./getNumberTablesToUser?stateCountyEditionYear='+stateCounty).success(function(data) {
			  $('#tableCount').val(data);
		  });
 	});
	  $scope.showAssignUsers = function() {
		  var username=$('#userSelect').val();
		  var users=username[0];
		  for(var i=1;i<username.length;i++){
			  users=users+','+username[i];
		  }
		  var counties=$('#countselect').val();
		  var states=$('#stateselect').val();
		  var editionYear=$('#editionselect').val();
		  var assignedTime=$('#assignedTime').text();
		  counties=counties.split(' ').join('%20');
		  var noOfTables=$('#tableCount').val();
		  $http.get('./saveAssignedUsers?userName='+users +'&county=' + counties+'&states='+states+'&editionYear='+editionYear+'&assignedTime='+assignedTime+'&noOfTables='+noOfTables).success(function(data) {
			  location.reload(); 
			  alert("task assigned successfully.");
		  });
	  };
	  
	  $("#createUser").on('click', function(e){
		  	e.preventDefault();
		  	window.location = "SignUp.jsp";
		  });
	  
}]);