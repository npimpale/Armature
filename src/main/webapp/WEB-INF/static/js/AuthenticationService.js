angular
	.module('ArmatureApp')
	.factory('AuthenticationService', ['$http', '$cookieStore', '$rootScope', '$timeout',
	                                   function($http, $cookieStore, $rootScope, $timeout){
			var service = {};
			service.Login = function (username, password) {
				var user = {};
				user.userName = username;
				user.hashedPassword = password;
				var response = $http.post('/login/authenticate', user);
				return response;
			};
			
			service.SetCredentials = function (username, password) {
				// TODO call csrf token service instead of BASE64
	            var authdata = '';
	            
	            $rootScope.globals = {
	                currentUser: {
	                    username: username,
	                    authdata: authdata
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
			return service;
		}
	]);