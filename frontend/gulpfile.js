var gulp = require('gulp');
var mainBowerFiles = require('main-bower-files');
var jshint = require('gulp-jshint');
var stylish = require('jshint-stylish');
var Server = require('karma').Server;
var runSequence = require('run-sequence');
var concat = require('gulp-concat');  
var rename = require('gulp-rename');  
var uglify = require('gulp-uglify'); 
var clean = require('gulp-clean');

var publicFolderPath = '../src/main/resources/public';
var appPath = 'app';
var allJsFiles = appPath + '/js/**/*.js';
var baseJsFiles = appPath + '/js/*.js';
var htmlFiles = appPath + '/**/*.html';
var buildPath = 'build';

gulp.task('build:js', function() {  
	var paths = mainBowerFiles();
	paths.push(baseJsFiles);
	paths.push(allJsFiles);
	return gulp.src(paths)
	    .pipe(concat('scripts.js'))
	    .pipe(rename('scripts.min.js'))
	    .pipe(uglify({mangle: false}))
	    .pipe(gulp.dest(buildPath + '/js'));
});

gulp.task('build:html', function() {
	return gulp.src(htmlFiles).pipe(gulp.dest(buildPath));
});

gulp.task('clean', function() {
	return gulp.src(buildPath + '/*', {read: false}).pipe(clean());
})

gulp.task('build', ['lint', 'build:js', 'build:html']);

gulp.task('copy', function() {
	return gulp.src(buildPath + '/**/*').pipe(gulp.dest(publicFolderPath));
});

gulp.task('lint', function() {
	return gulp.src(allJsFiles)
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
	gulp.watch(allJsFiles, watchOptions, ['lint', 'test']);
	gulp.watch('test/**/*.js', watchOptions, ['test']);
});

