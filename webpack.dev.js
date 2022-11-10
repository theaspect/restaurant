const {merge} = require('webpack-merge');
const common = require('./webpack.common.js');
const path = require('path');

module.exports = merge(common, {
    mode: 'development',
    "devServer": {
        "static": path.resolve(__dirname, 'dist'),
    },
    // https://webpack.js.org/configuration/devtool/
    devtool: 'inline-source-map'
});