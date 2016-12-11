'use strict';

describe('ViewerService', function() {
	var $httpBackend;

	beforeEach(module('CinemaReservations'));

	beforeEach(inject(function($injector) {
		$httpBackend = $injector.get('$httpBackend');
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	describe('Fetching customer', function() {
		var viewerService;
		var dummyCustomers = [ {
			id : 1,
			code : 1233
		}, {
			id : 2,
			code : 4321
		} ];
		beforeEach(inject(function($injector) {
			viewerService = $injector.get('ViewerService');
			$httpBackend.when('GET', 'customers').respond(dummyCustomers);
		}));
		it('should hit customers endpoint when getting customers', function() {
			$httpBackend.expectGET('customers');
			viewerService.getCustomers();
			$httpBackend.flush();
		});

		it('should get correct customers', function() {
			$httpBackend.expectGET('customers');
			viewerService.getCustomers(function(customers) {
				expect(customers).toEqual(dummyCustomers);
			});
			$httpBackend.flush();
		});
	});
});