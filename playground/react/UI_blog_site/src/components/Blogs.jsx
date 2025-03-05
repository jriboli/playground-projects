import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import StrapiService from "../services/strapiService";
import { FaClock, FaTag } from "react-icons/fa";

const Blogs = ({
  showPagination = true,
  customHeader = "Recent Blogs",
  category,
}) => {
  const [blogs, setBlogs] = useState([]);

  useEffect(() => {
    const fetchBlogs = async () => {
      const params = category
        ? { "filters[category][$eq]": category.replace("-", " ") }
        : {};

      const data = await StrapiService.get("blogs", params);
      setBlogs(data);
    };

    fetchBlogs();
  }, [category]);

  // For pagination
  const [currentPage, setCurrentPage] = useState(1);
  const blogsPerPage = 6;
  const indexOfLastBlog = currentPage * blogsPerPage;
  const indexOfFirstBlog = indexOfLastBlog - blogsPerPage;
  const currentBlogs = blogs?.slice(indexOfFirstBlog, indexOfLastBlog) || [];

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  // Function to calculate reading time (Assumes 200 words per minute)
  const calculateReadingTime = (text) => {
    const wordsPerMinute = 200;
    const words = text?.split(" ").length || 0;
    return Math.ceil(words / wordsPerMinute);
  };

  return (
    <div className="w-full bg-[#f9f9f9] py-[50px]">
      <div className="max-w-[1240px] mx-auto">
        <h2 className="text-3xl font-bold text-center mb-6">
          {category ? `${category.replace("-", " ")} Blogs` : customHeader}
        </h2>
        <div className="grid lg:grid-cols-3 sm:grid-cols-2 gap-8 px-4 text-black">
          {currentBlogs.length > 0 ? (
            currentBlogs.map((blog) => (
              <Link key={blog.id} to={`/blog/${blog.id}`}>
                <div className="bg-white rounded-xl overflow-hidden drop-shadow-md">
                  <div className="absolute top-4 left-4 bg-orange-500 text-white text-sm font-bold px-3 py-1 rounded-full">
                    {new Date(blog.created_at).toLocaleDateString("en-US", {
                      month: "short",
                      day: "numeric",
                      year: "numeric"}) }
                  </div>
                  <img
                    className="h-56 w-full object-cover"
                    src={`http://127.0.0.1:1337${blog.coverImg[0].url}`}
                  />
                  <div className="p-8 ">
                    <h3 className="font-bold text-2xl my-1">
                      {blog.blogTitle}
                    </h3>
                    {/* <p className="text-gray-600 text-xl">{blog.blogDesc}</p> */}
                    {/* Bottom Section */}
                    <div className="flex justify-between items-center text-gray-500 text-sm">
                      {/* Category Link as Animated Button */}
                      {blog.category && (
                        <Link
                          to={`/blogs/category/${blog.category}`}
                          className="relative group flex items-center px-4 py-2 border border-orange-500 text-orange-500 rounded-full overflow-hidden transition-all duration-300 ease-in-out"
                        >
                          {/* Tag Icon */}
                          <FaTag className="mr-2 text-orange-500 transition-all duration-300 ease-in-out group-hover:text-black" />

                          {/* Category Name with Dynamic Color Change */}
                          <span className="relative z-10 transition-all duration-300 ease-in-out group-hover:text-black">
                            {blog.category}
                          </span>

                          {/* Background Fill Animation */}
                          <span className="absolute left-0 bottom-0 w-full h-0 bg-orange-500 transition-all duration-300 ease-in-out group-hover:h-full"></span>
                        </Link>
                      )}

                      {/* Clock Icon & Reading Time */}
                      <div className="flex items-center">
                        <FaClock className="mr-2" />
                        <span>{calculateReadingTime(blog.blogContent)} min read</span>
                      </div>                      
                    </div>
                  </div>
                </div>
              </Link>
            ))
          ) : (
            <p className="text-center">No blog posts available</p>
          )}
        </div>
        {currentBlogs.length > 0 && showPagination && (
          <div className="flex justify-center mt-4 space-x-2">
            {Array.from(
              { length: Math.ceil(blogs.length / blogsPerPage) },
              (_, i) => (
                <button
                  key={i + 1}
                  onClick={() => setCurrentPage(i + 1)}
                  className={`px-4 py-2 rounded-md ${
                    currentPage === i + 1
                      ? "bg-orange-500 text-white"
                      : "bg-gray-300 text-black"
                  }`}
                >
                  {i + 1}
                </button>
              )
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default Blogs;
