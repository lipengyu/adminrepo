const webpack = require('webpack');
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const helpers = require('./helpers');

const modulesFolder = './client/';

module.exports = {
    entry: {
        'polyfills': modulesFolder + 'polyfills.ts',
        'vendor': modulesFolder + 'vendor.ts',
        'app': modulesFolder + 'main.ts'
    },

    resolve: {
        extensions: ['', '.js', '.ts'],
        root: path.resolve('client')
    },

    env: Object.assign(require('./app-config-common.json'), {
        "name": path.basename(path.resolve(__dirname, '..')),
        "version": require('../package.json').version
    }),

    module: {
        loaders: [{
            test: /\.ts$/,
            loaders: ['awesome-typescript-loader', 'angular2-template-loader', 'angular2-router-loader']
        }, {
            test: /\.html$/,
            loader: 'html'
        }, {
            test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)(\?v=[a-z0-9]\.[a-z0-9]\.[a-z0-9])?$/,
            loader: 'file',
            query: {
                name : 'resources/vendors/[name].[ext]'
            }
        }, {
            test: /\.css$/,
            loader: ExtractTextPlugin.extract('style', 'css?sourceMap')
        }, {
            test: /\.css$/,
            include: helpers.root('src', 'app', 'modules'),
            loader: 'raw'
        }, {
            test: /\.scss$/,
            loaders: ["style", "css", "sass"]
        }, {
            test: /\.pug$/,
            loader: 'pug-html-loader'
        }, {
            test: /\.json$/,
            loader: 'json-loader'
        }, {
            test: /vendors\/semanticui\/semantic\.js/,
            loader: 'string-replace',
            query: {
                multiple: [
                    { search: 'module.error(error.pusher);', replace: ''},
                    { search: 'module.error(error.movedSidebar, element);', replace: ''}
                ]
            }
        }],
        noParse: [
            helpers.root("client/vendors/sweetalert/sweetalert.min.js"),
            helpers.root("client/vendors/sockjs.js")
        ]
    },

    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: ['app', 'vendor', 'polyfills']
        }),
        new webpack.ProvidePlugin({
            '$': 'jquery',
            'jQuery': 'jquery',
            'jquery': 'jquery',
            'window.$': 'jquery',
            'window.jQuery': 'jquery',
            'SockJS': helpers.root("client/vendors/sockjs.js")
        }),
        new HtmlWebpackPlugin({
            template: modulesFolder + 'index.pug'
        })
    ]
};