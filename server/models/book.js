const jwt = require('jsonwebtoken');
const config = require('config');
const Joi = require('joi');
const mongoose = require('mongoose');
const multer = require('multer');
const validateObjectId = require("../middleware/validateObjectId");


const bookSchema = new mongoose.Schema({
    isbn: {
        type: String,
        required: true,
        minlength: 5,
        maxlength: 500
    },
    title: {
        type: String,
        required: true,
        minlength: 5,
        maxlength: 255
    },
    author : [ { type: String, required: true } ],
    edition: String,
    year : Number,
    condition : String,
    language : String,
    dateAdded:{
        type: Date,
        default: Date.now,
        required:true
    },
    price : Number,
    orgPrice : Number,
    note: String,
    categories:[{type : String}],
    courseCodes:[{type: String}],
    tags:[{type: String}],
    bookPhotos: [{ type: String, required: true }],
    bookExpireDate: Date,
    bookUser: String
    //roles: []
});

const Book = mongoose.model('Book', bookSchema);

Book.createCollection();

exports.Book = Book;
exports.bookSchema = bookSchema;
