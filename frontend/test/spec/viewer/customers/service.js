'use strict';

describe('CustomerService', function() {
	var $httpBackend;
	var customerService;

	beforeEach(module('CinemaReservations'));

	beforeEach(inject(function(_$httpBackend_, CustomerService) {
		$httpBackend = _$httpBackend_;
		customerService = CustomerService;
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
		it('should get correct customers', function() {
			$httpBackend.expectGET('customers');
			customerService.getCustomers(function(customers) {
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
			customerService.generateNewCustomer(function(customer) {
				expect(customer).toEqual(dummyCustomer);
			});
			$httpBackend.flush();
		});
	});
});