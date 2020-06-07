const jwt = require('jsonwebtoken');
const config = require('config');
const Joi = require('joi');
const mongoose = require('mongoose');
const multer = require('multer');

const userPhotoSchema = new mongoose.Schema({
    userID: {
        type: String,
        required: true},
    userProfilePhoto: { type: String, required: true }
});

const UserPhoto = mongoose.model('UserPhoto',userPhotoSchema);

UserPhoto.createCollection();

exports.UserPhoto = UserPhoto;