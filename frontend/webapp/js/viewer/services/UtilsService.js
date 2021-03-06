angular.module('CinemaReservations').service('UtilsService', function($http) {
	function genericGet(url, onSuccessCallback) {
		$http.get(url).then(function(response) {
			if (onSuccessCallback) {
				onSuccessCallback(response.data);
			}
		});
	}
	
	function getJoke() {
		return "Dobry dowcip.";
	}
	
	return {
		genericGet: genericGet,
		getJoke: getJoke
	};
});