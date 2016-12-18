var jokeProvider = (function() {
	"use strict";
	var jokesTold = 0, jokes = [
			"Idzie facet do windy, a tam batman nogi myje!",
			"Idzie zgarbiona baba do lekarza, a lekarz pyta: na chuj sie skradasz",
			"Generic Joke #1", "Generic Joke #2" ];

	function random(from, to) {
		return Math.floor((Math.random() * (to - from + 1)) + from);
	}

	function getNextJoke() {
		jokesTold += 1;
		return jokes[random(0, jokes.length - 1)];
	}

	return {
		next : getNextJoke,
		toldJokesAmount : function() {
			return jokesTold;
		}
	};
}());

var dialogContext = (function(jokeProvider) {
	"use strict";
	var jokeLimit = 3;

	function setJokeLimitTo(limit) {
		jokeLimit = limit;
	}

	function shouldTellJoke() {
		return jokeProvider.toldJokesAmount() < jokeLimit;
	}
	
	function getData(doc, tagName) {
		return doc.getElementsByTagName(tagName).item(0).firstChild.data;
	}

	return {
		setJokeLimitTo : setJokeLimitTo,
		shouldTellJoke : shouldTellJoke,
		toldJokesAmount : jokeProvider.toldJokesAmount,
		getData: getData
	};
}(jokeProvider));

var digitsService = (function() {
	var digits = "";
	function addDigit(digit) {
		digits += digit;
	}
	function getDigits() {
		return digits;
	}
	return {
		add: addDigit,
		get: getDigits
	}
}());