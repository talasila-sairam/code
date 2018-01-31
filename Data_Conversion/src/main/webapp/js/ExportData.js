var app = angular.module('ExportData', ['ngAnimate','ngTouch', 'ui.grid', 'ui.grid.edit','ui.grid.pagination','ui.grid.exporter','ui.grid.resizeColumns']);

app.controller('ExportDataController', ['$scope', '$http', '$timeout','uiGridExporterConstants','uiGridExporterService', function ($scope,$http,uiGridExporterService,uiGridExporterService,$timeout) {
	var colArr=[];
	$http.get('./executeExportDataQuery')
    .success(function(response) {
    	for(i = 0; i < response.length; i++){
    	  var dataMap = response[i];
    	  colCount = 0;
    	  for(var key in dataMap){
    	colArr[colCount] = { 
    				  field: key, 
    				  displayName: key , 
    				  width: 150
    				 
    				  }
    		  colCount = colCount+1;
    	  }
        
      }
    	$scope.ExportDataGridOptions.data = response;
    });
	$scope.ExportDataGridOptions = {
			columnDefs: colArr,
			paginationPageSizes: [15, 50, 100],
			paginationPageSize: 15,
		    exporterLinkLabel: 'get your csv here',
		    onRegisterApi: function(gridApi){ 
		        $scope.gridApi = gridApi;
		      },
		      exporterCsvFile : true,
		      exporterCsvFilename: 'FNF_LAN23.csv',
		      
			
			};
	  $scope.storeFile = function( gridRow, gridCol, files ) {
		    // ignore all but the first file, it can only select one anyway
		    // set the filename into this column
		    gridRow.entity.filename = files[0].name;

		    // read the file and set it into a hidden column, which we may do stuff with later
		    var setFile = function(fileContent){
		      gridRow.entity.file = fileContent.currentTarget.result;
		      // put it on scope so we can display it - you'd probably do something else with it
		      $scope.lastFile = fileContent.currentTarget.result;
		      $scope.$apply();
		    };
		    var reader = new FileReader();
		    reader.onload = setFile;
		    reader.readAsText( files[0] );
		  };
		  $scope.exportCsv = function(){
			    
			      var myElement = angular.element(document.querySelectorAll(".custom-csv-link-location"));
			      $scope.gridApi.exporter.csvExport("all","all", myElement );
			   
			  };
	$scope.exportXl=function(){
		$http.get('./executeExportDataXl').success(function(response) {
	    	
	    });
	}		  
}]).directive('gridLoading', function () {
	  return {
		    restrict: 'C',
		    require: '^uiGrid',
		    link: function ($scope, $elm, $attrs, uiGridCtrl) {
		      $scope.grid = uiGridCtrl.grid;
		    }
		  }
		});;