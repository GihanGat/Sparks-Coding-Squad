const jwt = require('jsonwebtoken');
const config = require('config');
const Joi = require('joi');
const mongoose = require('mongoose');
const multer = require('multer');

const bookPhotoSchema = new mongoose.Schema({
    bookID: {
        type: String,
        required: true},
    bookPhotos: [{ type: String, required: true }]
});

const BookPhoto = mongoose.model('BookPhoto',bookPhotoSchema);

BookPhoto.createCollection();

exports.BookPhoto = BookPhoto;