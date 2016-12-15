var gulp = require('gulp');
var mainBowerFiles = require('main-bower-files');
var jshint = require('gulp-jshint');
var stylish = require('jshint-stylish');
var Server = require('karma').Server;
var runSequence = require('run-sequence');
var concat = require('gulp-concat');  
var rename = require('gulp-rename');  
var uglify = require('gulp-uglify'); 

var publicFolderPath = '../src/main/resources/public';
var jsFiles = 'js/**/*.js';
var jsDest = 'build';

gulp.task('build:js', function() {  
	var paths = mainBowerFiles();
	paths.push(jsFiles);
	return gulp.src(paths)
	    .pipe(concat('scripts.js'))
	    .pipe(rename('scripts.min.js'))
	    .pipe(uglify())
	    .pipe(gulp.dest(jsDest));
});

gulp.task('build', ['build:js']);

gulp.task('copy', function() {
	return gulp.src(jsDest + '/**/*.js').pipe(gulp.dest(publicFolderPath + '/js'));
});

gulp.task('lint', function() {
	return gulp.src(jsFiles)
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
	gulp.watch(jsFiles, watchOptions, ['lint', 'test']);
	gulp.watch('test/**/*.js', watchOptions, ['test']);
});

