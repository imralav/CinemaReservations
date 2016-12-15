angular.module('CinemaReservations').service('CustomerService', ['UtilsService', function(utilsService) {
	'use strict';

	var getCustomers = function(onSuccessCallback) {
		utilsService.genericGet('customers', onSuccessCallback);
	};

	var generateNewCustomer = function(onSuccessCallback) {
		utilsService.genericGet('customers/generate', onSuccessCallback);
	};

	return {
		getCustomers : getCustomers,
		generateNewCustomer : generateNewCustomer
	};
}]);
