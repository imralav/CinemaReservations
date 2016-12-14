'use strict';

describe('ViewerService', function() {
	var $httpBackend;
	var viewerService;

	beforeEach(module('CinemaReservations'));

	beforeEach(inject(function(_$httpBackend_, ViewerService) {
		$httpBackend = _$httpBackend_;
		viewerService = ViewerService;
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	describe('Fetching customer', function() {
		var dummyCustomers = [ {
			id : 1,
			code : 1233
		}, {
			id : 2,
			code : 4321
		} ];
		beforeEach(function() {
			$httpBackend.when('GET', 'customers').respond(dummyCustomers);
		});
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
	describe('Generating new customer', function() {
		var dummyCustomer = {
			id : 1,
			code : 1233
		};
		beforeEach(function() {
			$httpBackend.when('GET', 'customers/generate').respond(dummyCustomer);
		});
		it('should get new customer', function() {
			$httpBackend.expectGET('customers/generate');
			viewerService.generateNewCustomer(function(customer) {
				expect(customer).toEqual(dummyCustomer);
			});
			$httpBackend.flush();
		});
	});
});