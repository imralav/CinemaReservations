angular.module('CinemaReservations').service('BookingService', ['UtilsService', function(utilsService) {
	'use strict';

	var getBookings = function(onSuccessCallback) {
		utilsService.genericGet('bookings', onSuccessCallback);
	};

	return {
		getBookings : getBookings
	};
}]);
