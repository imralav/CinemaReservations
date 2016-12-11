angular.module('CinemaReservations').service('ViewerService', function($http) {
	'use strict';
	function genericGet(url, onSuccessCallback) {
		$http.get(url).then(function(response) {
			if (onSuccessCallback) {
				onSuccessCallback(response.data);
			}
		});
	}

	var getCustomers = function(onSuccessCallback) {
		genericGet('customers', onSuccessCallback);
	};

	return {
		getCustomers : getCustomers
	};
});
