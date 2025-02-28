import React, { useState, useEffect } from "react";

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
      setBlogs(data ? data.data : []);
    };

    fetchBlogs();
  }, [category]);

  // For pagination
  const [currentPage, setCurrentPage] = useState(1);
  const blogsPerPage = 3;

  const indexOfLastBlog = currentPage * blogsPerPage;
  const indexOfFirstBlog = indexOfLastBlog - blogsPerPage;
  const currentBlogs = blogs?.slice(indexOfFirstBlog, indexOfLastBlog) || [];

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

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
                  <img
                    className="h-56 w-full object-cover"
                    src={`http://127.0.0.1:1337${blog.coverImg[0].url}`}
                  />
                  <div className="p-8 ">
                    <h3 className="font-bold text-2xl my-1">
                      {blog.blogTitle}
                    </h3>
                    <p className="text-gray-600 text-xl">{blog.blogDesc}</p>
                  </div>
                </div>
              </Link>
            ))
          ) : (
            <p className="text-center">No blog posts available</p>
          )}
        </div>
        {showPagination && (
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
