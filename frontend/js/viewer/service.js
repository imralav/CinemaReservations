'use strict';

angular.module('CinemaReservations').service('ViewerService', function($http) {
	function genericGet(url, onSuccessCallback) {
		$http.get(url).then(function(response) {
			onSuccessCallback && onSuccessCallback(response.data);
		});
	}
	
	var getCustomers = function(onSuccessCallback) {
		genericGet('customers', onSuccessCallback);
	};

	return {
		getCustomers : getCustomers
	};
});
