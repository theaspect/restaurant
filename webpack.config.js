const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    context: path.resolve(__dirname),
    mode: 'development',
    entry: {
        main: {
            import: './site/main.js',
            filename: "index.js",
        },
        echo1: {
            import: './echo1/site/echo1.js',
        }
    },
    output: {
        filename: '[name]/index.js',
        path: path.resolve(__dirname, 'dist'),
    },
    "devServer": {
        "static": path.resolve(__dirname, 'dist'),
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: 'site/index.html',
            filename: 'index.html',
            chunks: ['main'],
        }),
        new HtmlWebpackPlugin({
            template: 'echo1/site/index.html',
            filename: 'echo1/index.html',
            chunks: ['echo1']
        }),
    ],
    devtool: 'inline-source-map',
    optimization: {
        runtimeChunk: 'single',
    },
};