const winston = require('winston');
require('winston-mongodb');
require('express-async-errors');


module.exports = function () {
    winston.handleException(
        new winston.transports.Console({colorize: true, prettyPrint: true}),
        new winston.transports.File({ filename: "uncaughtException.log" }));

    // process.on('uncaughtException',(ex) => {
    //   //console.log('UNCAUGHT EXCEPTION');
    //   winston.error(ex.message,ex);
    //   process.exit(1);
    // });

    process.on('unhandledRejection', (ex) => {
        throw ex;
        //console.log('UNHANDLED REJECTION');
        // winston.error(ex.message,ex);
        // process.exit(1);
    });

    winston.add(winston.transports.File, { filename: './logfile.log' });
    winston.add(winston.transports.MongoDB, { db: 'mongodb+srv://bbaseuser:bbu@1234@cluster0-q8poz.gcp.mongodb.net/bbase?retryWrites=true&w=majority', level: 'info' });

}