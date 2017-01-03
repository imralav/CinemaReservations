angular.module('CinemaReservations')
  .controller('ViewerController', ['$scope', 'CustomerService', 'MovieService', function($scope, customerService, moviesService) {
	  'use strict';
	  function refreshCustomers() {
		  customerService.getCustomers(function(customers) {
			  $scope.customers = customers;
		  });
	  }
	  function refreshMovies() {
		  moviesService.getMovies(function(movies) {
			  $scope.movies = movies;
		  });
	  }
	  refreshCustomers();
	  refreshMovies();
	  $scope.refreshCustomers = refreshCustomers;
	  $scope.refreshMovies = refreshMovies;
  }]);
