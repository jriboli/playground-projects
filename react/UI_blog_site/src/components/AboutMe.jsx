import React, { useState, useEffect } from "react";

const words = ["Code Alchemist.", "Automation Enthusiast.", "Lifelong Learner.", "Pawsome Adventurer.", "Weekend Maker.", "Pixel Warrior."];

function AboutMe() {
  const [index, setIndex] = useState(0);
  const [text, setText] = useState("");
  const [isDeleting, setIsDeleting] = useState(false);

  useEffect(() => {
    const handleTyping = () => {
      const currentWord = words[index];
      if (isDeleting) {
        setText((prev) => prev.slice(0, -1));
        if (text === "") {
          setIsDeleting(false);
          setIndex((prev) => (prev + 1) % words.length);
        }
      } else {
        setText((prev) => currentWord.slice(0, prev.length + 1));
        if (text === currentWord) {
          setTimeout(() => setIsDeleting(true), 1000);
        }
      }
    };

    const typingSpeed = isDeleting ? 100 : 200;
    const timer = setTimeout(handleTyping, typingSpeed);
    return () => clearTimeout(timer);
  }, [text, isDeleting, index]);

  return (
    <div className="w-full h-[50vh] flex flex-col justify-center items-center bg-gray-800 text-white px-4">
      {/* Changing Text Section */}
      <div className="flex items-center md:text-6xl text-4xl font-bold">
        <span className="md:mr-4 mr-2">I am a</span>
        <span className="text-orange-400 underline">{text}</span>
        <span className="text-white">|</span>
      </div>

      {/* Summary Text - Now inside the same background */}
      <p className="mt-6 text-lg md:text-xl text-gray-300 max-w-2xl text-center">
        Welcome to my corner of the internet, where curiosity leads the way! 
        Too often, the <span className="text-orange-400 font-bold">novice perspective</span> is overlooked—but here, we embrace the beginner’s mindset, where every challenge is an opportunity, every mistake is a lesson, and every success is worth celebrating.
      </p>
    </div>
  );
}

export default AboutMe;
