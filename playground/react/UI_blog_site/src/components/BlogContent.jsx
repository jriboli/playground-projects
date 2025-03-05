import React from "react";
import { coding_background } from "../assets";
import { useParams } from "react-router-dom";
import ReactMarkdown from "react-markdown"

const BlogContent = ({ blogs }) => {
  const { id } = useParams();

  let blog = {};
  if (blog) {
    let arr = blogs.filter((blog) => blog.id == id);
    blog = arr[0];
  } else {
    blog = {};
  }

  console.log(blog);

  return (
    <div className="w-full pb-10 bg-[#f9f9f9]">
      <div className="max-w-[1240px] mx-auto">
        <div className="grid md:grid-cols-3 grid-cols-1 md:gap-8 gap-y-8 px-4 sm:pt-20 md:mt-0 ss:pt-20 text-black">
          <div className="col-span-2 gap-x-8 gap-y-8">
            <img
              className="h-56 w-full object-cover"
              src={`http://127.0.0.1:1337${blog.coverImg[0].url}`}
            />
            <h1 className="font-bold text-2xl my-1 pt-5">
              {id}--{blog.blogTitle}
            </h1>
            <p className="text-sm text-gray-400">
              Published: {new Date(blog.createdAt).toLocaleDateString()}
            </p>
            <div className="pt-5">
              <ReactMarkdown>{blog.blogContent}</ReactMarkdown>
            </div>
          </div>

          <div className="w-full bg-white rounded-xl overflow-hidden drop-shadow-md py-5 max-h-[250px]">
            <div>
              <img
                className="p-2 w-32 h-32 rounded-full mx-auto object-cover"
                src=""
              />
              <h1 className="font-bold text-2xl text-center text-gray-900 pt-3">
                Rocket Raccoon
              </h1>
              <p className="text-center text-gray-900 font-medium">
                "Guardian of the Galaxy"
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BlogContent;
