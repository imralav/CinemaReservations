angular.module('CinemaReservations').service('ShowingService', ['UtilsService', function(utilsService) {
	'use strict';

	var getShowings = function(onSuccessCallback) {
		utilsService.genericGet('showings', onSuccessCallback);
	};

	return {
		getShowings : getShowings
	};
}]);
