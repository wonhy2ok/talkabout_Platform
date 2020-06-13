import mongoose from "mongoose";
import Blog from "./model";

const uri ="mongodb+srv://admin:admin@cluster0-hmewm.mongodb.net/talkabout?retryWrites=true";
module.exports = () => {
  mongoose.connect(uri, { useNewUrlParser: true, useUnifiedTopology: true }, err => {
  if (err) console.error(err);
    console.log("DB connected");
  });
  Blog;
};