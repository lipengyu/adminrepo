var webpack = require('webpack');
var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commonConfig = require('./webpack.common.js');
var helpers = require('./helpers');

const envs = Object.assign(commonConfig.env, require("./app-config-dev.json"), {
    ENV: 'development'
});

helpers.extractEnv(envs);

module.exports = webpackMerge(commonConfig, {

    devtool: 'eval',
    // devtool: 'source-map',

    output: {
        path: helpers.root('dist'),
        publicPath: 'http://localhost:3000/',
        filename: '[name].js',
        chunkFilename: '[id].chunk.js',
    },

    stats: {
        colors: true,
        modules: true,
        reasons: true,
        errorDetails: true
    },

    plugins: [
        new ExtractTextPlugin('[name].css'),
        new webpack.DefinePlugin({
            'process.env': envs
        })
    ],

    devServer: {
        historyApiFallback: true,
        stats: 'minimal',
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
        }
    }
});