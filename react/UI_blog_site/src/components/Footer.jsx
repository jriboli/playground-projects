import React from "react";
import {
  FaFacebook,
  FaLinkedin,
  FaGithub,
  FaInstagram,
  FaTwitter,
  FaTwitch,
} from "react-icons/fa";
import ReCAPTCHA from "react-google-recaptcha";

const Footer = () => {
  return (
    <div id="contact" className="w-full bg-gray-900 text-white py-8 px-4 md:px-10">
      <div className="max-w-[1240px] mx-auto flex flex-col md:flex-row justify-between items-center md:items-center">
        {/* Left Side - Text */}
        <div className="w-full md:w-1/2 text-lg font-bold flex justify-center items-center h-full">
          <p>Let's discuss</p>
        </div>

        {/* Right Side - Contact Form */}
        <div className="w-full md:w-1/2 flex flex-col space-y-4">
          <input
            type="email"
            placeholder="Your Email"
            className="p-2 rounded bg-gray-800 text-white border border-gray-700 focus:outline-none focus:ring-2 focus:ring-orange-500"
          />
          <textarea
            placeholder="Your Message"
            className="p-2 rounded bg-gray-800 text-white border border-gray-700 focus:outline-none focus:ring-2 focus:ring-orange-500"
            rows="3"
          ></textarea>
          <div className="flex justify-center">
            <ReCAPTCHA sitekey="YOUR_RECAPTCHA_SITE_KEY" />
          </div>
          <button className="bg-orange-500 text-white py-2 rounded hover:bg-orange-600 transition">
            Submit
          </button>
        </div>
      </div>
      
      {/* Footer Bottom Section */}
      <div className="max-w-[1240px] mx-auto mt-8 flex justify-between items-center">
        <p className="text-sm">© {new Date().getFullYear()} Your Name. All rights reserved.</p>
        <div className="flex space-x-4">
          <a href="https://www.linkedin.com/in/yourprofile" target="_blank" rel="noopener noreferrer">
            <FaLinkedin className="w-6 h-6 text-white hover:text-orange-500" />
          </a>
          <a href="https://github.com/yourprofile" target="_blank" rel="noopener noreferrer">
            <FaGithub className="w-6 h-6 text-white hover:text-orange-500" />
          </a>
        </div>
      </div>
    </div>
  );

  // return (
  //   <div className="w-full bg-[#02044A] text-gray-300 py-8 px-2">
  //     <div className="max-w-[1240px] mx-auto grid grid-cols-2 md:grid-cols-6 border-b-2 border-gray-600 py-8">
  //       <div>
  //         <h6 className="font-bold uppercase py-2">Solutions</h6>
  //         <ol>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //         </ol>
  //       </div>

  //       <div>
  //         <h6 className="font-bold uppercase py-2">Solutions</h6>
  //         <ol>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //         </ol>
  //       </div>
  //       <div>
  //         <h6 className="font-bold uppercase py-2">Solutions</h6>
  //         <ol>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //         </ol>
  //       </div>
  //       <div>
  //         <h6 className="font-bold uppercase py-2">Solutions</h6>
  //         <ol>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //           <li className="py-1">Marketing</li>
  //         </ol>
  //       </div>

  //       <div className="col-span-2 pt-2md:pt-2">
  //         <p className="font-bold uppercase">Subscribe to Our Newsletter</p>
  //         <p className="py-4">
  //           The latest news, articles, and resources sent to your inbox weekly.
  //         </p>
  //         <form className="flex flex-col sm:flex-row">
  //           <input
  //             className="w-full p-2 mr-4 rounded-md mb-2"
  //             type="email"
  //             placeholder="Enter email"
  //           />
  //           <button className="p-2 mb-2 bg-[#00B86E] rounded-md ">
  //             Subscribe
  //           </button>
  //         </form>
  //       </div>
  //     </div>

  //     <div className="flex flex-col max-w-[1240px] px-2 py-4 m-auto justify-between sm:flex-row text-center text-gray-500 items-center">
  //       <p>2022 CWTS, LLC. All rights reserved.</p>
  //       <div className="flex justify-between sm:w-[300px] pt-4 text-2xl gap-2">
  //         <FaFacebook />
  //         <FaGithub />
  //         <FaInstagram />
  //         <FaTwitter />
  //         <FaTwitch />
  //       </div>
  //     </div>
  //   </div>
  // );
};

export default Footer;
