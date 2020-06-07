const express = require('express');
const books = require('../routes/books');
const users   = require('../routes/users');
const auth    = require('../routes/auth');
const error   = require('../middleware/error');

module.exports = function(app){
    app.use(express.json());
    app.use('/uploads', express.static('uploads'));
    app.use('/api/users',users);
    app.use('/api/auth',auth);
    app.use('/api/books',books);
    app.use(error);
}