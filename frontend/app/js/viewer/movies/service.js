angular.module('CinemaReservations').service('MovieService', ['UtilsService', function(utilsService) {
	'use strict';

	var getMovies= function(onSuccessCallback) {
		utilsService.genericGet('movies', onSuccessCallback);
	};

	return {
		getMovies : getMovies
	};
}]);
