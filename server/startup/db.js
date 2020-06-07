const winston = require('winston');
const mongoose = require('mongoose');

module.exports = function (app) {
    mongoose.connect('mongodb+srv://bbaseuser:bbu@1234@cluster0-q8poz.gcp.mongodb.net/bbase?retryWrites=true&w=majority',
     { useNewUrlParser: true, useUnifiedTopology: true })
        .then(() => console.log('Connected to MongoDB...'))
        .catch(err => console.error('Could not connect to MongoDB...'));
}