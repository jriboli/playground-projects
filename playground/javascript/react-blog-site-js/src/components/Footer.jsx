import React, {useState}  from "react";
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
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [status, setStatus] = useState(""); // To display success/error messages

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!email || !message) {
      setStatus("Please fill in all the fields.");
      return;
    }

    try {
      const response = await fetch("https://your-backend-url.com/send-email", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, message }),
      });

      if (response.ok) {
        setStatus("Message sent successfully!");
        setEmail(""); // Clear input fields
        setMessage("");
      } else {
        setStatus("Failed to send message. Try again later.");
      }
    } catch (error) {
      console.error("Error sending email:", error);
      setStatus("An error occurred. Please try again.");
    }
  };

  return (
    <div id="contact" className="w-full bg-gray-900 text-white py-4 px-4 md:px-10">
      <div className="max-w-[1240px] mx-auto flex flex-col md:flex-row justify-between items-center md:items-center py-10 px-4">
        {/* Left Side - Text (35%) */}
        <div className="w-full md:w-3/5 text-lg font-bold flex justify-center items-center h-full text-center p-10">
          <p>
            Have questions, thoughts, or topics to discuss? Send us an email — we’d love to hear from you!
          </p>
        </div>

        {/* Right Side - Contact Form */}
        <form className="w-full md:w-2/5 flex flex-col space-y-4" onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Your Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="p-2 rounded bg-gray-800 text-white border border-gray-700 focus:outline-none focus:ring-2 focus:ring-orange-500"
          />
          <textarea
            placeholder="Your Message"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            className="p-2 rounded bg-gray-800 text-white border border-gray-700 focus:outline-none focus:ring-2 focus:ring-orange-500"
            rows="3"
          ></textarea>
          <div className="flex justify-left">
            <ReCAPTCHA sitekey="YOUR_RECAPTCHA_SITE_KEY" />
          </div>

          {/* Submit Button */}
          <div className="flex justify-left">
            <button
              type="submit"
              className="bg-orange-500 text-white py-2 px-6 rounded hover:bg-orange-600 transition"
            >
              Submit
            </button>
          </div>

          {/* Status Message */}
          {status && <p className="text-center text-sm text-gray-300 mt-2">{status}</p>}
        </form>
      </div>
      
      {/* Footer Bottom Section */}
      <div className="max-w-[1240px] mx-auto mt-8 flex justify-between items-center">
        <p className="text-sm">© {new Date().getFullYear()} The Novice Blog. All rights reserved.</p>
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
};

export default Footer;
