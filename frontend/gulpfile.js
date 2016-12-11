const gulp = require('gulp');
const mainBowerFiles = require('main-bower-files');
const jshint = require('gulp-jshint');
const stylish = require('jshint-stylish');

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