var tbtfBankApp = angular.module('tbtfBankApp', []);

tbtfBankApp.controller('bankSearchController', function($scope, $http) {
	$scope.users = [];

	$scope.search = function() {
		//remove current results
		$scope.users = [];
		$scope.messageText="";
		
		//search
		$http.get('/tbtf-bank4/rest/users',		
			{params: {
				firstName:$scope.searchFirstName, 
				lastName:$scope.searchLastName}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="Found "+data.length;
				$scope.users = data;
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});		
	};
});

tbtfBankApp.controller('bankAddUserController', function($scope, $http) {	
	
	$scope.create = function() {
		$scope.messageText="";
		
		$http.post('/tbtf-bank4/rest/users',
				{"user":{"firstName":$scope.createFirstName,"lastName":$scope.createLastName,"pin":$scope.createPin}})
			.success(function(data, status) {
				$scope.httpStatus = status;
				$scope.httpData = data;
				$scope.errorStatus=false;
				$scope.messageText="Created new user with ID "+data.user.id;				
			})
			.error(function(data, status) {
				$scope.httpStatus = status;				
				$scope.httpData = data;
				$scope.errorStatus=true;
				$scope.messageText=data.error.code+ " "+ data.error.message;
			});
	};
});