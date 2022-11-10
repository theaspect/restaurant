const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    context: path.resolve(__dirname),
    mode: 'production',
    // https://webpack.js.org/configuration/entry-context/
    entry: {
        main: {
            import: './site/main.ts',
            filename: "index.js",
        },
        echo1: {
            import: './echo1/site/echo1.ts',
        }
    },
    output: {
        filename: '[name]/index.js',
        path: path.resolve(__dirname, 'dist'),
    },
    plugins: [
        // https://github.com/jantimon/html-webpack-plugin
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
    // For tsconfig params see https://www.typescriptlang.org/docs/handbook/tsconfig-json.html
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    optimization: {
        runtimeChunk: 'single',
    },
};