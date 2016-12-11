angular.module('CinemaReservations')
  .controller('ViewerController', ['$scope', 'ViewerService', '$log', function($scope, viewerService, logger) {
	  'use strict';
	  function refreshCustomers() {
		  viewerService.getCustomers(function(customers) {
			  $scope.customers = customers;
		  });
	  }
	  refreshCustomers();
	  $scope.refreshCustomers = refreshCustomers;
  }]);
