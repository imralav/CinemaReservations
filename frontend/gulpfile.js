const gulp = require('gulp');
const mainBowerFiles = require('main-bower-files');
const jshint = require('gulp-jshint');
const stylish = require('jshint-stylish');
const Server = require('karma').Server;

var publicFolderPath = '../src/main/resources/public';

gulp.task('copy', function() {
	return gulp.src("js/**/*")
	.pipe(gulp.dest(publicFolderPath));
});

gulp.task('copyBowerDependencies', function() {
	return gulp.src(mainBowerFiles(), { base: 'bower_components'})
	.pipe(gulp.dest(publicFolderPath));
});

gulp.task('lint', function() {
	return gulp.src('js/**/*')
	.pipe(jshint())
	.pipe(jshint.reporter('jshint-stylish'))
	.pipe(jshint.reporter('fail'));
});

gulp.task('test', function(done) {
	return new Server({
		configFile : __dirname + '/karma.conf.js',
		singleRun : true
	}, done).start();
});

gulp.task('watch', function() {
	gulp.watch('js/**/*', ['lint', 'test']);
	gulp.watch('test/**/*', ['test']);
});