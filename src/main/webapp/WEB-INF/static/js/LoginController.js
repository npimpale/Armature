angular
	.module('ArmatureApp')
	.controller('LoginController',['$scope', '$location', 'AuthenticationService',
	                               function($scope, $location, AuthenticationService) {
			$scope.user = {};
			$scope.error = false;
			$scope.login = function() {
				AuthenticationService.Login($scope.user.username, $scope.user.hashedPassword).then(success);
			};
			function success(response){
					if (response.success) {
						AuthenticationService.SetCredentials($scope.user.username, $scope.user.hashedPassword);
						$location.path('/dashboard');
					} else {
						$scope.error = true;
						$scope.message = 'Username or Password is incorrect!';
						$location.path('/login');
					}
			}
		}
	]);
