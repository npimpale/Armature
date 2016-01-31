var app = angular
	.module('ArmatureApp', ['ngCookies', 'ngRoute', 'ngResource']);

	// Route configure
	app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		$routeProvider
		// DashBoard
			.when('/dashboard', {
				templateUrl : 'pages/dashboard.jsp',
				controller : 'DashboardController'
			})
			
			// Login
			.when('/login', {
				templateUrl : 'pages/login.jsp',
				controller : 'LoginController'
			})
			
			.otherwise({
				redirectTo : '/login'
			});
	}]);

	app.run(['$rootScope', '$location', '$cookieStore', '$http',
	      function($rootScope, $location, $cookieStore, $http) {
		// keep user logged in after page refresh
		$rootScope.globals = $cookieStore.get('globals') || {};
		if ($rootScope.globals.currentUser) {
			$http.defaults.headers.common['Authorization'] = 'Basic '
				+ $rootScope.globals.currentUser.authdata; // jshint ignore:line
		}

		$rootScope.$on('$locationChangeStart', function(event, next, current) {
			// redirect to login page if not logged in and trying to
			// access a
			// restricted page
			var restrictedPage = $.inArray($location.path(),[ '/login' ]) === -1;
			var loggedIn = $rootScope.globals.currentUser;
			if (restrictedPage && !loggedIn) {
				$location.path('/login');
			}
		});
	}]);
	
	// Authentication Service
	app.factory('AuthenticationService', ['$http', '$cookieStore', '$rootScope', '$timeout',
		                                   function($http, $cookieStore, $rootScope, $timeout){
		var service = {};
		service.user = {};
		service.Login = function (user) {
			var response = $http.post('/Armature/login/authenticate', user);
			return response;
		};
		
		service.SetCredentials = function (user) {
			// TODO call csrf token service instead of BASE64
			service.user = user;
            var authdata = service.getCSRFToken();
            $rootScope.globals = {
                currentUser: {
                	userName: service.user.userName,
                	hashedPassword: service.user.hashedPassword,
                    token: authdata
                }
            };
 
            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        };
        
		service.ClearCredentials = function() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic';
        };
        
        service.getCSRFToken = function(){
        	var token = null;
        	var response = $http.post('/Armature/login/token', service.user);
        	if(response != null){
        		token = response;
        	}
        	return token;
        };
		return service;
	}
]);
	// User Service
	app.factory('UserService', ['$http','$rootScope', function($http, $rootScope) {
		var service = {};
		var commonPath = '/Armature/users/';
		service.GetAll = GetAll;
	    service.GetById = GetById;
	    service.GetByUsername = GetByUsername;
	    service.Create = Create;
	    service.Update = Update;
	    //service.Delete = Delete;
		
	    function GetAll() {
	        return $http.get(commonPath  ).then(handleSuccess, handleError('Error getting all users'));
	    }
	
	    function GetById(id) {
	        return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
	    }
	
	    function GetByUsername(username) {
	        return $http.get('/api/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
	    }
	
	    function Create(user) {
	        return $http.post('/api/users', user).then(handleSuccess, handleError('Error creating user'));
	    }
	
	    function Update(user) {
	        return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
	    }
	
	    // private functions
	
	    function handleSuccess(res) {
	        return res.data;
	    }
	
	    function handleError(error) {
	        return function () {
	            return { success: false, message: error };
	        };
	    }
	    
	  return service;
}]);
	
	//Login Controller
	app.controller('LoginController',['$scope', '$location', 'AuthenticationService',
	                               function($scope, $location, AuthenticationService) {
			$scope.error = false;
			$scope.login = function() {
				AuthenticationService.Login($scope.user).then(success);
			};
			function success(response){
					if (response) {
						AuthenticationService.SetCredentials($scope.user);
						$location.path('/dashboard');
					} else {
						$scope.error = true;
						$scope.message = 'Username or Password is incorrect!';
						$location.path('/login');
					}
			}
		}
	]);

	//DashBoard Controller
	app.controller('DashboardController',['$scope', '$location', 'UserService',
	                               function($scope, $location, UserService) {
			$scope.error = false;
			$scope.message = 'This is your Dahsboard';
			
		}
	]);
