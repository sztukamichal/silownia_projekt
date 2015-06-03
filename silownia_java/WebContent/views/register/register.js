(function() {
	'use strict';

	angular.module('gym.register', [])
		.config(['$routeProvider', function($routeProvider) {
			$routeProvider.when('/register', {
			templateUrl: 'views/register/register.html',
			controller: 'RegisterController'
		});
		}])
		.controller('RegisterController', RegisterController);

	RegisterController.$inject = ['$scope', '$location', '$http', 'AuthService'];
	function RegisterController($scope, $location, $http, AuthService) {
		$scope.errorMessage = false;
		
		console.log(AuthService.isAuthenticated());
		
		$.fn.serializeObject = function()
		{
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name] !== undefined) {
		            if (!o[this.name].push) {
		                o[this.name] = [o[this.name]];
		            }
		            o[this.name].push(this.value || '');
		        } else {
		            o[this.name] = this.value || '';
		        }
		    });
		    return o;
		};
		
		$('#submit-button').on('click', function () {
			if ( $('#input-password').val() !== $('#input-password-rep').val() ){
				$scope.errorMessage = 'Wprowadzono rozne hasla';
				return;
			}
			
			var credencials = ($(".form-register").serializeObject());
			credencials.userId = null;
			credencials.create_on = null;
			delete credencials["password-rep"];


			$http.post('./rest/user/registerUser', credencials)
				.success(function(data, status) {
					console.log("Registration success");
					console.log(credencials);
					$location.url('/login');
				})
			  	.error(function(data, status) {
				    console.log("Registration error");
				    $scope.errorMessage = 'Bledna rejestracja';
				  });
		});
	}
})();