angular.module('CinemaReservations')
  .controller('ViewerController', ['$scope', 'CustomerService', function($scope, customerService) {
	  'use strict';
	  function refreshCustomers() {
		  customerService.getCustomers(function(customers) {
			  $scope.customers = customers;
		  });
	  }
	  refreshCustomers();
	  $scope.refreshCustomers = refreshCustomers;
  }]);
