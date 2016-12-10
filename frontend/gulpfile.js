var gulp = require('gulp');
var gutil = require('gulp-util');

var publicFolderPath = '../src/main/resources/public';

gulp.task('copy', function() {
	gutil.log('Copying js resources to destination folder ' + publicFolderPath);
	gulp.src("js/**/*")
	.pipe(gulp.dest(publicFolderPath));
});