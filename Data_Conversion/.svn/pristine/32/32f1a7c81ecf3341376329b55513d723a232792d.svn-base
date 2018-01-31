var app = angular.module('app', ['ngTouch', 'ui.grid','ngAnimate']);
app.controller('userassignment', ['$scope', '$http', function($scope, $http) {
	var responseDataReport=[];
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
	                   	{ displayName: 'County ',name: 'county',width:115,enableFiltering: false  },
	                   	{ displayName: 'State',name: 'state' ,width:115,enableFiltering: false },
	                	{ displayName: 'Edition Year',name: 'editionYear' ,width:105,enableFiltering: false },
	                	{ displayName: 'Status',name: 'status',width:115,enableFiltering: false  },
	                	{ displayName: 'Assigned Time',name:'assignedtime',width:175,enableFiltering: false  },
	                	{ displayName: 'Processing Time',name:'processintime',width:175,enableFiltering: false  },
	                   	{ displayName: 'Completed Time ',name: 'completedtime',width:175,enableFiltering: false  },
	                   	{ displayName: 'Comments',name: 'comments',width:115,enableFiltering: false  },
	                   	{ displayName: 'Duration',name: 'duration',width:95,enableFiltering: false  },
	                   	{ displayName: 'Sign Off',name: 'signoff',width:75,enableFiltering: false  },
	                	{ displayName: 'No of times County Reviewed',name: 'count',width:195,enableFiltering: false  }
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
		          minLength: 3,
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
	       	var filteredListArray=[];
	       	if(fieldId=='countselect'){
	       		filteredListArray=countyArray;
	       	}
	       	if(fieldId=='stateselect'){
	       		filteredListArray=stateArray;
	       	}
	         $("#"+fieldId).autocomplete({
	          minLength: 3,
	          source: function(request, response) {
	           var filteredArray = $.map(filteredListArray, function(item) {
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
	$http.get('./showAssignedUsers').success(function(data) {
		 $scope.gridOptions.columnDefs=columnDefs1;
     		$scope.gridOptions.data=data;
     		
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
		  
		  $http.get('./saveAssignedUsers?userName='+users +'&county=' + counties+'&states='+states+'&editionYear='+editionYear+'&assignedTime='+assignedTime).success(function(data) {
			  location.reload(); 
			  alert("task assigned successfully.");
		  });
	  };
	  
}]);