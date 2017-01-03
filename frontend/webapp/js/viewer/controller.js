angular.module('CinemaReservations')
  .controller('ViewerController', ['$scope', 'CustomerService', 'MovieService', 'BookingService', function($scope, customerService, moviesService, bookingService) {
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
	  function refreshBookings() {
		  bookingService.getBookings(function(bookings) {
			  $scope.bookings = bookings;
		  });
	  }
	  refreshCustomers();
	  refreshMovies();
	  refreshBookings();
	  $scope.refreshCustomers = refreshCustomers;
	  $scope.refreshMovies = refreshMovies;
	  $scope.refreshBookings = refreshBookings;
  }]);
