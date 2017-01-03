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
	  function prepareBookingSeats(bookings) {
		  var bookingSeats = [];
		  bookings.forEach(function(booking) {
			booking.seats.forEach(function(seat) {
				bookingSeats.push({bookingId: booking.id, seatId: seat.id}); 
			});
		  });
		  return bookingSeats;
	  }
	  function refreshBookings() {
		  bookingService.getBookings(function(bookings) {
			  $scope.bookings = bookings;
			  $scope.bookingSeats = prepareBookingSeats(bookings);
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
