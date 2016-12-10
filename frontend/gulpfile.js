var gulp = require('gulp');
var gutil = require('gulp-util');
var mainBowerFiles = require('main-bower-files');

var publicFolderPath = '../src/main/resources/public';

gulp.task('copy', function() {
	gulp.src("js/**/*")
	.pipe(gulp.dest(publicFolderPath));
});

gulp.task('copyBowerDependencies', function() {
	return gulp.src(mainBowerFiles(), { base: 'bower_components'})
	.pipe(gulp.dest(publicFolderPath));
});