var exec = require('cordova/exec');

exports.nomedia = function(arg0, success, error) {
    exec(success, error, "nomediaPlugin", "nomedia", [arg0]);
};
