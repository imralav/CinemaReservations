describe("JokeProvider test", function() {
	it('Should start with 0 jokes told', function() {
		expect(jokeProvider.toldJokesAmount()).toBe(0);
	});
	it('Should properly increment jokes told', function() {
		jokeProvider.next();
		jokeProvider.next();
		expect(jokeProvider.toldJokesAmount()).toBe(2);
	});
});