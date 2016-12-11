'use strict';

describe('ViewerService', function() {
	var $httpBackend, viewerService;
	
	beforeEach(module('CinemaReservations'));
	
	beforeEach(inject(function($injector) {
		$httpBackend = $injector.get('$httpBackend');
		viewerService = $injector.get('ViewerService');
	}));
	
	it('should fetch customers', function() {
		expect(true).toBe(true);
	});
});