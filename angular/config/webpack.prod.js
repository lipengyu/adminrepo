var webpack = require('webpack');
var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var CopyWebpackPlugin = require("copy-webpack-plugin");
var commonConfig = require('./webpack.common.js');
var helpers = require('./helpers');

const envs = Object.assign(commonConfig.env, require("./app-config-prod.json"), {
    ENV: 'production'
});

helpers.extractEnv(envs);

module.exports = webpackMerge(commonConfig, {

    // devtool: 'eval',
    devtool: 'source-map',

    output: {
        path: helpers.root(JSON.parse(require("./app-config-common.json").web_folder)),
        publicPath: '/',
        filename: 'resources/[name].js',
        chunkFilename: 'resources/[id].chunk.js'
    },

    htmlLoader: {
        minimize: false // workaround for ng2
    },

    plugins: [
        new webpack.NoErrorsPlugin(),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.UglifyJsPlugin({ // https://github.com/angular/angular/issues/10618
            mangle: {
                keep_fnames: true
            }
        }),
        new ExtractTextPlugin('resources/[name].css', {
            allChunks: true
        }),
        new webpack.DefinePlugin({
            'process.env': envs
        }),
        new CopyWebpackPlugin([{from: 'assets', to: ''}])
    ]
});