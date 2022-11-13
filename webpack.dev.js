const {merge} = require('webpack-merge');
const common = require('./webpack.common.js');
const webpack = require('webpack');
const path = require('path');

module.exports = merge(common, {
    mode: 'development',
    "devServer": {
        "static": path.resolve(__dirname, 'dist'),
    },
    // https://webpack.js.org/configuration/devtool/
    devtool: 'inline-source-map',
    plugins: [
        new webpack.DefinePlugin({
            ECHO1_URL: JSON.stringify('http://localhost:8091'),
            ECHO2_URL: JSON.stringify('http://localhost:8092'),
        })
    ]
});