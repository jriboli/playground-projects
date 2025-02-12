import React from "react";
import { coding_background } from "../assets";
import {Link} from 'react-router-dom'

const Blogs = () => {
  const blogs = [
    {
      id: 1,
      title: "Blog 1",
      desc: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magni temporibus ipsa veniam modi aliquam dignissimos obcaecati, labore culpa quasi eum optio reiciendis eligendi animi nihil ab beatae nam! Voluptatibus, eveniet.",
      coverImg: coding_background,
    },
    {
      id: 2,
      title: "Blog 2",
      desc: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magni temporibus ipsa veniam modi aliquam dignissimos obcaecati, labore culpa quasi eum optio reiciendis eligendi animi nihil ab beatae nam! Voluptatibus, eveniet.",
      coverImg: coding_background,
    },
    {
      id: 3,
      title: "Blog 3",
      desc: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Magni temporibus ipsa veniam modi aliquam dignissimos obcaecati, labore culpa quasi eum optio reiciendis eligendi animi nihil ab beatae nam! Voluptatibus, eveniet.",
      coverImg: coding_background,
    },
  ];

  return (
    <div className="w-full bg-[#f9f9f9] py-[50px]">
      <div className="max-w-[1240px] mx-auto">
        <div className="grid lg:grid-cols-3 sm:grid-cols-2 gap-8 px-4 text-black">
          {blogs.map((blog) => 

            <Link key={blog.id} to={`/blog/${blog.id}`}>
              <div className="bg-white rounded-xl overflow-hidden drop-shadow-md">
                <img className="h-56 w-full object-cover" src={blog.coverImg} />
                <div className="p-8 ">
                  <h3 className="font-bold text-2xl my-1">{blog.title}</h3>
                  <p className="text-gray-600 text-xl">{blog.desc}</p>
                </div>
              </div>
            </Link>
            
          )}
        </div>
      </div>
    </div>
  );
};

export default Blogs;
