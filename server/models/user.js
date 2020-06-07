const jwt = require('jsonwebtoken');
const config = require('config');
const Joi = require('joi');
const mongoose = require('mongoose');
const multer = require('multer');


const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minlength: 5,
        maxlength: 50
    },
    email: {
        type: String,
        required: true,
        minlength: 5,
        maxlength: 255,
        unique: true
    },
    password: {
        type: String,
        required: true,
        minlength: 5,
        maxlength: 1024,
    },
    isAdmin: Boolean,
    joinDate:{
        type: Date,
        default: Date.now,
        required:true
    },
    token: String,
    wishListBooks: [ {type : mongoose.Schema.Types.ObjectId }]
    //roles: []
});

userSchema.methods.generateAuthToken = function () {
    const token = jwt.sign({ _id: this.id, isAdmin: this.isAdmin }, config.get('jwtPrivateKey'));
    return token;
}

const User = mongoose.model('User', userSchema);

User.createCollection();

function validateUser(user) {
    const schema = {
        name: Joi.string().min(5).max(50).required(),
        email: Joi.string().min(5).max(255).required().email(),
        password: Joi.string().min(5).max(255).required(),
    };
    return Joi.validate(user, schema);
}

// function generateAuthToken() {

// }

exports.User = User;
exports.validate = validateUser;
//exports.generateAuthToken = generateAuthToken;