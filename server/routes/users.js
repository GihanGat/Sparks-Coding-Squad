const auth = require('../middleware/auth');
const bcrypt = require('bcrypt');
const _ = require('lodash');
const { User, validate } = require('../models/user');
const { Book } = require('../models/book');
const { UserPhoto } = require('../models/userPhoto');
const mongoose = require('mongoose');
const express = require('express');
const router = express.Router();
const multer = require('multer');


const storage = multer.diskStorage({
    destination: function(req, file, cb) {
      cb(null, './uploads/');
    },
    filename: function(req, file, cb) {
      //cb(null, new Date().toISOString() + '_' + file.originalname);
      cb(null,file.originalname);
    }
  });

//const upload = multer({dest:'uploads/'});
const upload = multer({
    storage: storage,
    limits: {
      fileSize: 1024 * 1024 * 5
    }
  });

router.get('/me', async (req, res) => {
    const user = await User.findById(req.user._id).select('-password');
    res.send(user)
})

router.get('/', async (req, res) => {
    const users = await User.find();
    res.send(users)
})

router.post('/', async (req, res) => {
    const { error } = validate(req.body);
    if (error) return res.status(400).send(error.details[0].message);

    let user = await User.findOne({ email: req.body.email });
    if (user) return res.status(400).send('User already registered.');

    user = new User(_.pick(req.body, ['name', 'email', 'password' , 'isAdmin','joinDate', 'token']));
    const salt = await bcrypt.genSalt(10);
    user.password = await bcrypt.hash(user.password, salt);

    // user = new User({
    //     name:  req.body.name,
    //     email: req.body.email,
    //     password: req.body.password
    // });

    //await user.save();
    
    //const token = jwt.sign({ _id:user.id }, config.get('jwtPrivateKey'));
    
    const token = user.generateAuthToken();

    user.isAdmin = false;
    user.token = token;

    //console.log("Token :" + user.token );
    let newUser = await user.save();

    res.header('x-auth-token', token).send({"user" : _.pick(newUser, ['id', 'name', 'email' ,'isAdmin','joinDate', 'token' ])});

    //res.send(_.pick(user, ['id','name','email']));
});


router.put('/updatepassword', async (req, res) => {
    const { error } = validate(req.body);
    if (error) return res.status(400).send(error.details[0].message);

    let user = await User.findOne({ email: req.body.email });
    if (!user) return res.status(400).send('Invalid User');

    const validPassword = await  bcrypt.compare(req.body.currentpassword, user.password);
    if (!validPassword) return res.status(400).send('Invalid email or pssword');

    const salt = await bcrypt.genSalt(10);
    user.password = await bcrypt.hash(req.body.newpassword, salt);

    await user.save();

    //const token = jwt.sign({ _id:user.id }, config.get('jwtPrivateKey'));

    const token = user.generateAuthToken();
    res.header('x-auth-token', token).send({"user" : _.pick(user, ['id', 'name', 'email'])});

    //res.send(_.pick(user, ['id','name','email']));
});

router.post( "/userphoto" , upload.single('userProfilePhoto'),async (req, res) => {
    const reqToken = req.header("x-auth-token");
    let loginUser = await User.findOne({token : reqToken});
    console.log("Login User :" + loginUser.name + "  " + loginUser._id );
    //let user = await User.findOne({email : req.body.email });

    const userPhoto = new UserPhoto({
        userID : loginUser._id ,
        userProfilePhoto : req.file.path 
    });

    await userPhoto.save();

    res.send("Image has been uploaded successfully");
});

router.put("/addtowishlist", async (req, res) => {
 // const { error } = validate(req.body);
 // if (error) return res.status(400).send(error.details[0].message);

  const user = await User.findById(req.body.userID).select("-__v");
 
  user.wishListBooks.push(req.body.bookID);
  user.save();
  res.send(user);
});

router.put("/removefromwishlist", async (req, res) => {
  // const { error } = validate(req.body);
  // if (error) return res.status(400).send(error.details[0].message);
 
   const user = await User.findById(req.body.userID).select("-__v");
   user.wishListBooks.remove(req.body.bookID);
   user.save();
   res.send(user);
 });

router.get('/wishlistbooks/:id', async (req, res) => {
  const result = await User.findById(req.params.id).select('wishListBooks');
  const bookList = result.wishListBooks;
  const wishListBooks = [{}];
  //res.send(bookList);
   for(let book of bookList){
     const bookResult = await Book.findById(book).select('-__v');
     wishListBooks.push(bookResult);
   }
  res.send({wishListBooks : wishListBooks});
})

module.exports = router;