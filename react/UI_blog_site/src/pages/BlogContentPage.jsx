import React from "react";
import { Navbar, BlogContent, Footer } from "../components";

const BlogContentPage = ({blogs}) => {
  return (
    <div>
      <Navbar />
      <BlogContent blogs={blogs ? blogs : ""}/>
      <Footer />
    </div>
  );
};

export default BlogContentPage;
