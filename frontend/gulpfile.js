var gulp = require('gulp');
var mainBowerFiles = require('main-bower-files');
var jshint = require('gulp-jshint');
var stylish = require('jshint-stylish');
var Server = require('karma').Server;
var runSequence = require('run-sequence');

var publicFolderPath = '../src/main/resources/public';

gulp.task('copy', function() {
	return gulp.src("js/**/*").pipe(gulp.dest(publicFolderPath));
});

gulp.task('copyBowerDependencies', function() {
	return gulp.src(mainBowerFiles(), {
		base : 'bower_components'
	}).pipe(gulp.dest(publicFolderPath));
});

gulp.task('lint', function() {
	return gulp.src('js/**/*')
			.pipe(jshint())
			.pipe(jshint.reporter('jshint-stylish'))
			.pipe(jshint.reporter('fail'));
});

function runTests(singleRun, done) {
	var karmaOptions = {
			configFile : __dirname + '/karma.conf.js',
			singleRun : singleRun,
			autoWatch: !singleRun
		};
	new Server(karmaOptions, function(failCount) {
		done(failCount ? new Error("Failed " + failCount + " tests.") : null);
	}).start();
}

gulp.task('test', function(done) {
	runTests(true, done);
});

gulp.task('watch', function(done) {
	var watchOptions = { debounceDelay: 1000 };
	gulp.watch('js/**/*.js', watchOptions, ['lint', 'test']);
	gulp.watch('test/**/*.js', watchOptions, ['test']);
});

