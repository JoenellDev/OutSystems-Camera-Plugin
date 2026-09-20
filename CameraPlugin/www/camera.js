var exec = require('cordova/exec');

var CameraPlugin = {

    takePhoto: function (success, error, options) {

        options = options || {};

        exec(
            success,
            error,
            'CameraPlugin',
            'takePhoto',
            [options]  
        );
    }

};

module.exports = CameraPlugin;