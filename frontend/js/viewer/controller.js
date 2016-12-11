'use strict';

angular.module('CinemaReservations')
  .controller('ViewerController', ['$scope', 'ViewerService', '$log', function($scope, viewerService, logger) {
	  viewerService.getCustomers(function(customers) {
		  logger.info('Fetched ', customers);
		  $scope.customers = customers;
	  });
  }]);
