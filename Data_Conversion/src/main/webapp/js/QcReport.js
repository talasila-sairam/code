var app = angular.module('app', ['ngTouch', 'ui.grid','ui.grid.selection','ngAnimate']);
app.controller('Qcreport', ['$scope', '$http', '$timeout', 'uiGridConstants', function($scope, $http,$timeout,uiGridConstants) {
	$('#columnsForQualityCheck').multiselect({
		includeSelectAllOption: false,
	    enableCaseInsensitiveFiltering : true,
	    filterBehavior: 'value',
	/*	onChange : function(event){
		 // 	getCOlumnData();
			alert("hi");
	    },*/ 
	    nonSelectedText: '--Please Select--',
	    disableIfEmpty: true
	});
	var colArr=[];
/*	var colArr=[
	            {
	            	name: key,
	            	displayName: key,
	            	 enableSorting: true,
                     width: '20%'
	            	
	            },
	            {
	            	name: key,
	            	displayName: key,
	            	 enableSorting: true,
                     width: '20%'
	            	
	            },
	            {
	            	name: key,
	            	displayName: key,
	            	 enableSorting: true,
                     width: '20%'
	            	
	            },
	            {
	            	name: key,
	            	displayName: key,
	            	 enableSorting: true,
                     width: '20%'
	            	
	            }
	            ];*/
	  $scope.gridOptions = {
			  enableRowSelection: true,
    		  onRegisterApi: function(gridApi){
    				$scope.gridApi = gridApi;
    		},
    		  enableSorting: false,
  			headerHeight: 20
      };
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
	 $scope.gridOptions.multiSelect = true;
	 $scope.getDatForQC=function(){
		 var orderByColumn=$("#columnsForQualityCheck").val();
		 var slectedEdition=$("#editionselect").val();
     $http.get('./executeQueryForQC?slectedEdition='+orderByColumn).success(function(response) {
    	/* var  map =[];
    	 for (var j = 0; j < response.length; j++) {
    		 for (var k = j+1; k < response.length-j; k++) {
    			if(response[j].APPRCL==response[k].APPRCL){
    				if(response[j].ASSESSEEOWNERNAME==response[k].ASSESSEEOWNERNAME){
    					if(response[j].SECOND_ASSESSEEOWNERNAME==response[k].SECOND_ASSESSEEOWNERNAME){
    						if(response[j].ASSEMAILCAREOFNAME==response[k].ASSEMAILCAREOFNAME){
    							map.push(response[j].APPRCL+'|'+response[j].ASSESSEEOWNERNAME+'|'+response[j].SECOND_ASSESSEEOWNERNAME+'|'+response[j].ASSEMAILCAREOFNAME);
    							alert(map);
    						}
    	    			}
        			}
    				map.push(response[j].APPRCL);
    			}
    		 }
    	 }*/
    /*	 var uniqueNames = [];
    	 $.each(map, function(i, el){
    	     if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
    	 });
    	 alert(uniqueNames);*/
    	for (var i = 0; i==0; i++) {
              var dataMap = response[i];
              colCount = 0;
              for (var key in dataMap) {
            	  var count=0;
            	  if(key=="APPRCL"){
            		  colArr[colCount] = {
                              name: key,
                              /*cellClass: function(grid, row, col, rowRenderIndex, colRenderIndex) {
                            	  for (var k = 0; k<uniqueNames.length; k++) {
                            	         if (grid.getCellValue(row,col) ==uniqueNames[k]){
                            	            return 'blue';
                            	            break;
                            	         }
                            	  }
                              },*/
                              displayName: key,
                             /* sort: {
                            	         direction: uiGridConstants.DESC,
                            	          priority: 1
                            	       },
*/
                              enableSorting: true,
                              width: '20%'
                          } 
           }
            	  else{
            		  colArr[colCount] = {
                              name: key,
                              displayName: key,
                             /* sort: {
                     	         direction: uiGridConstants.DESC,
                     	          priority: 1
                     	       },
*/
                              enableSorting: true,
                              width: '20%'
                          }  
            	  }
                  colCount = colCount + 1;
              }
          }
          $scope.gridOptions.data = response;
          $scope.gridOptions.columnDefs = colArr;
     //     var noOfRows = $scope.gridOptions.data.length;
     /*     console.log(noOfRows);
          $("#NoOfRows").text("");
          $("#NoOfRows").text("No Of Rows : " + noOfRows);*/
      });
	 }
	 $scope.getSelectedParcelNos=function(){
		 var rowsToDelete=[];
		 var selectedRows=$scope.gridApi.selection.getSelectedRows();
		 for(var i=0;selectedRows.length>i;i++){
			// alert(selectedRows[i].APPRCL);
			 rowsToDelete.push(selectedRows[i].APPRCL);
			
		 }
		  $http.get('./rowsToDelete?rowsToDelete='+rowsToDelete).success(function(response) {
			  
		  });
	 }
}
]).directive('gridLoading', function() {
    return {
        restrict: 'C',
        require: '^uiGrid',
        link: function($scope, $elm, $attrs, uiGridCtrl) {
            $scope.grid = uiGridCtrl.grid;
        }
    }
});;