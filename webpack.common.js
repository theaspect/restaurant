const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    context: path.resolve(__dirname),
    // https://webpack.js.org/configuration/entry-context/
    entry: {
        main: {
            import: './site/main.tsx',
            filename: "index.js",
        },
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
    ],
    // For tsconfig params see https://www.typescriptlang.org/docs/handbook/tsconfig-json.html
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.s[ac]ss$/i,
                use: [
                    // Creates `style` nodes from JS strings
                    "style-loader",
                    // Translates CSS into CommonJS
                    "css-loader",
                    // Compiles Sass to CSS
                    "sass-loader",
                ],
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