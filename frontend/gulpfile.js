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
	return gulp.src('js/**/*').pipe(jshint()).pipe(
			jshint.reporter('jshint-stylish')).pipe(jshint.reporter('fail'));
});

gulp.task('test', function(done) {
	return new Server({
		configFile : __dirname + '/karma.conf.js',
		singleRun : true
	}, done).start();
});

gulp.task('build', function() {
	runSequence('lint','test');
})

gulp.task('watch', function() {
	gulp.watch('js/**/*', [ 'build' ]);
	gulp.watch('test/**/*', [ 'test' ]);
});