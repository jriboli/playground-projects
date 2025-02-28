import React, { useState } from "react";
import { menu, close, logo } from "../assets";
import { FaLinkedin, FaGithub } from "react-icons/fa";

const Navbar = () => {
  const [toggle, setToggle] = useState(false);
  const handleClick = () => setToggle(!toggle);

  return (
    <div className="w-full h-[80px] z-10 bg-white fixed drop-shadow-lg relative">
      <div className="flex justify-between items-center w-full h-full md:max-w-[1240px] m-auto">
        <div className="flex items-center">
          <img
            src={logo}
            alt="logo"
            className="sm:ml-10 ss:ml-10 md:ml-3 opacity-[55%] w-full h-[25px]"
          ></img>
        </div>
        <div className="flex items-center">
          <ul className="hidden md:flex text-3xl font-bold">
            <li>
              <a href="/blogs" className="hover:text-orange-400">
                Blog
              </a>
            </li>
            <li>
              <a href="#contact" className="hover:text-orange-400">
                Contact
              </a>
            </li>
          </ul>
        </div>
        <div className="hidden md:flex space-x-6 sm:mr-10 md:mr-10">
          <a
            href="https://www.linkedin.com/in/yourprofile"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FaLinkedin className="w-6 h-6 text-white hover:text-orange-400" />
          </a>
          <a
            href="https://github.com/yourprofile"
            target="_blank"
            rel="noopener noreferrer"
          >
            <FaGithub className="w-6 h-6 text-white hover:text-orange-400" />
          </a>
        </div>

        <div className="md:hidden" onClick={handleClick}>
          <img
            src={toggle ? close : menu}
            alt="menu"
            className="w-[28px] h-[28px] object-contain mr-10"
          />
        </div>
      </div>

      <ul
        className={
          toggle
            ? "absolute bg-white w-full px-8 md:hidden font-bold"
            : "hidden"
        }
      >
        <li>
          <a href="/blogs" className="hover:text-orange-400">
            Blog
          </a>
        </li>
        <li>
          <a href="#contact" className="hover:text-orange-400">
            Contact
          </a>
        </li>
      </ul>
    </div>
  );
};

export default Navbar;
