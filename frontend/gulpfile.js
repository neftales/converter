var _ = require('lodash');
var gulp = require('gulp');
var uglify = require('gulp-uglify');
var reactify = require('reactify');
var notify = require('gulp-notify');
var browserify = require('browserify')
var source = require('vinyl-source-stream');
var streamify = require('gulp-streamify')

var _uglify = {
  mangle: false,
  output: {
    beautify: true
  }
}

gulp.task('build', function() {
  browserify({entries: ['js/app.js']})
    .transform(reactify)
    .bundle()
    .pipe(source('app.js'))
    .pipe(streamify(uglify(_uglify)))
    .pipe(gulp.dest('build'))
    .pipe(notify("Build completo"));
});

gulp.task('watch', function() {
  gulp.watch('js/**', ['build']);
});
