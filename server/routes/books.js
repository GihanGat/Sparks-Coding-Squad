const auth = require('../middleware/auth');
const bcrypt = require('bcrypt');
const _ = require('lodash');
const { Book } = require('../models/book');
const mongoose = require('mongoose');
const multer = require('multer');
const express = require('express');
const moment = require("moment");
const router = express.Router();

// const bookStorage = multer.diskStorage({
//     destination: function(req, file, cb) {
//       cb(null, './uploads/books/');
//     },
//     filename: function(req, file, cb) {
//       //cb(null, new Date().toISOString() + '_' + file.originalname);
//       cb(null,file.originalname);
//     }
//   });

//   const bookUpload = multer({
//     storage: bookStorage,
//     limits: {
//       fileSize: 1024 * 1024 * 5
//     }
//   });

 router.post("/new", async (req, res) => {
   //const { error } = validate(req.body);
   //if (error) return res.status(400).send(error.details[0].message);

    let book = new Book({
        isbn:  req.body.isbn,
        title: req.body.title,
        author: req.body.author,
        edition : req.body.edition,
        year : req.body.year,
        condition : req.body.condition,
        language : req.body.language,
        note : req.body.note,
        price : req.body.price,
        dateAdded : moment().toJSON()
    });

     book = await book.save();

     //res.send(book);
     res.send({message:"New book has been added successfully"});
 });

router.get("/:id", async (req, res) => {
    const book = await Book.findById(req.params.id).select("-__v");
  
    if (!book)
      return res.status(404).send("The book with the given ID was not found.");
  
    res.send(book);
});

router.post("/buy", async (req, res) => {
});

module.exports = router;

