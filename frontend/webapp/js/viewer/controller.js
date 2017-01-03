angular.module('CinemaReservations')
  .controller('ViewerController', ['$scope', 'CustomerService', 'MovieService', 'BookingService', 'SeatService', 'ShowingService', 
                                   function($scope, customerService, moviesService, bookingService, seatService, showingService) {
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
	  function refreshSeats() {
		  seatService.getSeats(function(seats) {
			  $scope.seats = seats;
		  });
	  }
	  function refreshShowings() {
		  showingService.getShowings(function(showings) {
			  $scope.showings = showings;
		  });
	  }
	  refreshCustomers();
	  refreshMovies();
	  refreshBookings();
	  refreshSeats();
	  refreshShowings();
	  $scope.refreshCustomers = refreshCustomers;
	  $scope.refreshMovies = refreshMovies;
	  $scope.refreshBookings = refreshBookings;
	  $scope.refreshSeats = refreshSeats;
	  $scope.refreshShowings = refreshShowings;
  }]);
