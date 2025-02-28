import React, { useState, useEffect } from "react";

const words = ["Programmer.", "Tester.", "Thinker.", "Dog Lover.", "DIYer.", "Gamer."];

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
    <div>
      {/* Top Section with Changing Text */}
      <div className="w-full h-[40vh] flex justify-center items-center bg-gray-800 text-white md:text-6xl text-4xl font-bold">
        <span className="md:mr-4 mr-2">I am a</span>
        <span className="text-orange-400 underline">{text}</span>
        <span className="text-white">|</span>
      </div>
    </div>
  );
}

export default AboutMe;
