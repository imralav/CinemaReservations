'use strict';

angular.module('CinemaReservations')
  .service('ViewerService', function($http) {
	  var getCustomers = function(onSuccessCallback) {
		  $http.get('customers').then(function(response) {
			 onSuccessCallback && onSuccessCallback(response.data); 
		  });
	  };
	  
	  return {
		  getCustomers: getCustomers
	  };
  });
