var path = require('path');
var _root = path.resolve(__dirname, '..');

function root(args) {
    args = Array.prototype.slice.call(arguments, 0);
    return path.join.apply(path, [_root].concat(args));
}
function extractEnv(envs) {
    Object.keys(envs).forEach((element) => {
        process.env[element] = envs[element];
        envs[element] = JSON.stringify(envs[element]);
    });
}
exports.root = root;
exports.extractEnv = extractEnv;