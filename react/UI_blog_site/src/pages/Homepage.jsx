import React from "react";
import { Navbar, Blogs, Footer } from "../components";

const Homepage = ({ blogs }) => {
  // console.log("HomePage");
  // console.log(blogs);

  return (
    <div>
      <Navbar />
      <Blogs blogs={blogs ? blogs : ""} />
      <Footer />
    </div>
  );
};

export default Homepage;
