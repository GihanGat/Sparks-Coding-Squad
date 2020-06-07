const config = require('config');


module.exports = function () {
    console.log('jwtPrivateKey: ' + config.util.getEnv('bbase_jwtPrivateKey'));
    console.log(config.get('jwtPrivateKey'));
    if (!config.get('jwtPrivateKey')){
        throw new Error('FATAL ERROR: jwtPrivateKey is not defined' );
       // process.exit(1);
    }    
}