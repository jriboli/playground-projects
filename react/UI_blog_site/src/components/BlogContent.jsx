import React from "react";
import { coding_background } from "../assets";

const BlogContent = () => {
  const blog = {
    id: 1,
    title: "Blog 1",
    desc: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magni temporibus ipsa veniam modi aliquam dignissimos obcaecati, labore culpa quasi eum optio reiciendis eligendi animi nihil ab beatae nam! Voluptatibus, eveniet.",
    coverImg: coding_background,
    content:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Veniam porro adipisci, placeat et alias iste, quas sed minus delectus deleniti, dignissimos rem tenetur enim facere dolore qui totam provident distinctio?",

    authorName: "Rocket Raccoon",
    authorImg: "",
    authorDesc: "Software Developer",
  };

  return (
    <div className="w-full pb-10 bg-[#f9f9f9]">
      <div className="max-w-[1240px] mx-auto">
        <div className="grid md:grid-cols-3 grid-cols-1 md:gap-8 gap-y-8 px-4 sm:pt-20 md:mt-0 ss:pt-20 text-black">
          <div className="col-span-2 gap-x-8 gap-y-8">
            <img className="h-56 w-full object-cover" src={blog.coverImg} />
            <h1 className="font-bold text-2xl my-1 pt-5">{blog.title}</h1>
            <div className="pt-5">
              <p>{blog.content}</p>
            </div>
          </div>

          <div className="w-full bg-white rounded-xl overflow-hidden drop-shadow-md py-5 max-h-[250px]">
            <div>
              <img
                className="p-2 w-32 h-32 rounded-full mx-auto object-cover"
                src=""
              />
              <h1 className="font-bold text-2xl text-center text-gray-900 pt-3">
                {blog.authorName}
              </h1>
              <p className="text-center text-gray-900 font-medium">
                {blog.authorDesc}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BlogContent;
