'use strict';

describe('MovieService', function() {
	var movieService;
	var dummyMovies = [ {
		id : 1,
		title : "Koszmar z ulicy wiązów",
		genre: {
			id: 1,
			name: 'Horror'
		}
	}, {
		id : 2,
		title : "Koszmar z ulicy wiązów 2",
		genre: {
			id: 1,
			name: 'Horror'
		}
	} ];

	beforeEach(module('CinemaReservations'));
	beforeEach(function() {
		var utilsServiceMock = {
				genericGet: function(url, callback) {
					callback(dummyMovies);
				}
		};
		module(function($provide) {
			$provide.value('UtilsService', utilsServiceMock);
		});
	});

	beforeEach(inject(function(MovieService) {
		movieService = MovieService;
	}));

	describe('Fetching movies', function() {
		it('should hit movies endpoint when getting movies', function() {
			movieService.getMovies(function(movies) {
				expect(movies).toEqual(dummyMovies);
			});
		});
	});
});