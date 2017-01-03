angular.module('CinemaReservations').service('SeatService', ['UtilsService', function(utilsService) {
	'use strict';

	var getSeats = function(onSuccessCallback) {
		utilsService.genericGet('seats', onSuccessCallback);
	};

	return {
		getSeats : getSeats
	};
}]);
