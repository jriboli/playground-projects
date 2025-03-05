import React from "react";
import { Navbar, Blogs, Footer, AboutMe } from "../components";

const Homepage = () => {

  return (
    <div>
      <Navbar />
      <AboutMe />
      <Blogs showPagination={false} customHeader="Recent Blogs"/>
      <Footer />
    </div>
  );
};

export default Homepage;
