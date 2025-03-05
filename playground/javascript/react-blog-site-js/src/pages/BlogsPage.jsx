import React from "react";
import { useParams } from "react-router-dom";
import { Navbar, Blogs, Footer } from "../components";

const BlogsPage = () => {
  const { category } = useParams(); // Get category from URL

  return (
    <div>
      <Navbar />
      <Blogs showPagination={true} category={category} />
      <Footer />
    </div>
  );
};

export default BlogsPage;
